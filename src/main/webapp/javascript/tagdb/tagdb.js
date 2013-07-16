self.addEventListener('message', function (e) {
    if (e.data.lines) {
        var lines = e.data.lines;

        var retValue = [];
        for (var lineIndex = 0, linesLength = lines.length; lineIndex < linesLength; ++lineIndex) {

            var line = lines[lineIndex];

            // match only xml/html elements
            var tagRe = /<\s*([^\s]+).*?>/g;
            var tagMatch = null;
            while (tagMatch = tagRe.exec(line)) {
                var tag = tagMatch[1];
                retValue.push([tagMatch.index, tag.length]);
            }
        }

        postMessage(retValue);
    }
});