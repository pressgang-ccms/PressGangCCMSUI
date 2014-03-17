/*
 This script scans an array of lines of text looking for content spec metadata elements
 */

var tags = null;

var reverse = function (input) {
    return input.split('').reverse().join('');
};

self.addEventListener('message', function (e) {
    if (e.data.tagDB) {
        var database = JSON.parse(e.data.tagDB);
        tags = [];

        for (var property in database) {
            if (database.hasOwnProperty(property)) {
                tags.push(property);
            }
        }
    }

    if (e.data.lines && tags != null) {
        var lines = e.data.lines;

        var retValue = [];
        for (var lineIndex = 0, linesLength = lines.length; lineIndex < linesLength; ++lineIndex) {

            var line = lines[lineIndex];
            var tagDetails = [];

            // Match anything that looks like metadata
            var metadataRe = /^(.+?)\s*=\s*/g;
            var metadataMatch = null;
            while (metadataMatch = metadataRe.exec(line)) {
                var tag = metadataMatch[1];

                if (tags.indexOf(tag) != -1) {
                    var start = metadataMatch.index;
                    var end = start + tag.length;
                    tagDetails.push([start, end]);
                }
            }

            // match anything that looks like a container
            var containerRE = /^((\s{2})*)([A-Za-z ]+)\s*:/g;
            var containerMatch = null;
            while (containerMatch = containerRE.exec(line)) {
                var container = containerMatch[3];

                if (tags.indexOf(container) != -1) {
                    var prefix = containerMatch[1] ? containerMatch[1].length : 0;
                    var start = containerMatch.index + prefix;
                    var end = start + container.length;
                    tagDetails.push([start, end]);
                }
            }

            // match anything that looks like element relationship metadata
            var referenceMetadataRE = /(\s*\[)((R|P|L).*?)\s*:\s*/g;
            var referenceMatch = null;
            while (referenceMatch = referenceMetadataRE.exec(line)) {
                var element = referenceMatch[2];

                if (tags.indexOf(element) != -1) {
                    var prefix = referenceMatch[1].length;
                    var start = referenceMatch.index + prefix;
                    var end = start + element.length;
                    tagDetails.push([start, end]);
                }
            }

            // match anything that looks like a element attribute

            // find a block that encloses text in two unescaped square brackets.
            // we need to reverse the string to work around the fact that javascript does
            // not do look behinds
            var attributeMetadataRE = /\](?!\\)(.*?)(\[(?!\\).*?$)/g;
            var attributeMatch = null;
            while (attributeMatch = attributeMetadataRE.exec(reverse(line))) {
                var block = attributeMatch[1];
                var prefix = attributeMatch[2].length;

                var keyValuePairs = block.split(/,(?!\\)/g);
                keyValuePairs.reverse();

                for (var index = 0, count = keyValuePairs.length; index < count; ++index) {
                    var keyValue = keyValuePairs[index];
                    var keyAndValue = keyValue.split(/=(?!\\)/g, 2);
                    if (keyAndValue.length == 2) {
                        var key = reverse(keyAndValue[1]);
                        var trimmedKey = key.trim();
                        var numLeadingWS = key.indexOf(trimmedKey.charAt(0));

                        if (tags.indexOf(trimmedKey) != -1) {
                            var start = prefix + numLeadingWS;
                            var end = start + trimmedKey.length;
                            tagDetails.push([start, end]);
                        }
                    }
                    prefix += keyValue.length + 1;
                }
            }

            retValue.push(tagDetails);
        }

        postMessage(retValue);
    }
});