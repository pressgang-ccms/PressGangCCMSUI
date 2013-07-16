/*
    This script scans an array of lines of text looking for xml tags, and passes a message
    back with the start and end points of the tags.
 */

self.addEventListener('message', function (e) {
    if (e.data.lines) {
        var lines = e.data.lines;

        var retValue = [];
        for (var lineIndex = 0, linesLength = lines.length; lineIndex < linesLength; ++lineIndex) {

            var line = lines[lineIndex];
            var tags = [];

            // match only xml/html elements
            var tagRe = /(<\s*)([^\s/>]+)/g;
            var tagMatch = null;
            while (tagMatch = tagRe.exec(line)) {
                var prefix = tagMatch[1];
                var tag = tagMatch[2];
                tags.push([tagMatch.index + prefix.length, tag.length]);
            }

            retValue.push(tags);
        }

        postMessage(retValue);
    }
});