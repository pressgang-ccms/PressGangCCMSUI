/*
    This JavaScript takes xml and scans it for xml elements that do not match the given condition.
    Since it uses regular expressions, it is somewhat tolerant of badly formatted XML.
 */

/**
 * Take the start and end positions of characters in a block of text that includes line breaks,
 * and convert it to start and end positions across a number of lines. This is required because
 * markings in the ace editor are determined by line number, start and end position.
 * @param text The text being analysed
 * @param startEnd The start and end positions to be converted to start and end positions over multiple lines
 * @returns {Array}
 */
function convert(text, startEnd) {
    var retValue = [];

    // this tells us how many lines preceed the element
    var beforeText = text.substr(0, startEnd.start);
    var beforeLines = beforeText.split("\n");

    // this tells us how many lines are included in the element
    var includingText = text.substr(startEnd.start, startEnd.end - startEnd.start);
    var includingLines = includingText.split("\n");

    // determine the details of the marker in the first line
    var line = beforeLines.length - 1;
    var start = beforeLines[beforeLines.length - 1].length;
    var end = includingLines.length > 1 ?
        start + includingLines[0].length :
        startEnd.end - (startEnd.start - start);

    // push a marker from the start of the element to the end of the element, or the end
    // of the line if the element spans multiple lines
    retValue.push({line: line, start: start, end: end});

    // Account for elements that span multiple lines
    if (includingLines.length > 1) {
        // keep a track of how much of the element's contents we "consume" in the loop,
        // so we know how much is left in the final line
        var remainingLength = includingText.length - includingLines[0].length;

        // push a marker for each of the lines between the first and the last. these
        // markers are from the start of the line to the end.
        for (var includingLinesIndex = 1, includingLinesCount = includingLines.length - 1; includingLinesIndex < includingLinesCount; ++includingLinesIndex) {
            retValue.push({line: line + includingLinesIndex, start: 0, end: includingLines[includingLinesIndex].length});
            remainingLength -= includingLines[includingLinesIndex].length;
        }

        // push a marker for the last line. This is from the start of the line to the end
        // of the element/
        retValue.push({line: line + includingLines.length - 1, start: 0, end: remainingLength});
    }

    return retValue;
}

self.addEventListener('message', function (e) {

    // return an empty array if there is no condition selected
    if (!e.data.condition || !e.data.text) {
        postMessage([]);
        return;
    }

    try {
        var specCondition = new RegExp(e.data.condition);
    } catch (e) {
        // bad regex, so don't process anything
        postMessage([]);
        return;
    }

    /**
     * A regex to match an XML element with a condition attribute
     * @type {RegExp}
     */
    var startTagRegex = /<\s*([^>]*?)\s+[^>]*?condition\s*=\s*['"](.*?)['"][^>]*?(\/)?\s*>/;
    var text = e.data.text;
    var totalLength = 0;
    var retValue = [];

    outerloop:
    while (text.length != 0) {
        var match = startTagRegex.exec(text);
        if (match == null) {
            break outerloop;
        }

        var conditions = match[2].split(";:,");

        var include = false;
        for (var conditionIndex = 0, conditionCount = conditions.length; conditionIndex < conditionCount; ++conditionIndex) {
            var condition = conditions[conditionIndex];
            if(specCondition.test(condition)) {
                include = true;
                break;
            }
        }

        var start = match.index + totalLength;
        totalLength += match.index + match[0].length;
        text = text.substr(match.index + match[0].length);

        // This conditional element does not meet the condition. So we need to find the
        // end tag, and note the space that the conditional element takes up.
        if (!include) {
            // not a self closing element
            if (!match[3]) {
                // a regex to match any opening tag of the same type as the conditional one we are processing
                var openTag = new RegExp("<\s*" + match[1] + "\s*.*?>");
                // a regex to match any closing tag of the same type as the conditial one we are processing
                var closeTag = new RegExp("<\s*\/\s*" + match[1] + "\s*>");

                var childCount = 0;

                while (childCount >= 0) {
                    var openMatch = openTag.exec(text);
                    var closeMatch = closeTag.exec(text);

                    if (closeMatch == null) {
                        // no close tag exists. this is bad xml, but we will highlight it
                        // like the open tag is spread to the end of the text
                        retValue.push({start: start, end: totalLength + text.length});
                        break outerloop;
                    } else if (openMatch == null || openMatch.index > closeMatch.index) {
                        // we found a closing tag without finding an opening tag, or
                        // where the closing tag was before the next opening one
                        --childCount;
                        totalLength += closeMatch.index + closeMatch[0].length;
                        text = text.substr(closeMatch.index + closeMatch[0].length);
                    } else if (openMatch != null && openMatch.index < closeMatch.index) {
                        // we found an opening tag before the next closing one. This
                        // means the opening tag is a child of the conditional one
                        ++childCount;
                        totalLength += openMatch.index + openMatch[0].length;
                        text = text.substr(openMatch.index + openMatch[0].length);
                    }
                }

                retValue = retValue.concat(convert(e.data.text, {start: start, end: totalLength}));
            } else {
                retValue = retValue.concat(convert(e.data.text, {start: start, end: totalLength}));
            }
        }
    }

    postMessage(retValue);
});