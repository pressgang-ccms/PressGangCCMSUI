/*
 This script scans an array of lines of text looking for content spec metadata elements
 */

var tags = null;

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
			var metadataRe = /^(\S*)\s*=\s*/g;
			var metadataMatch = null;
			while (metadataMatch = metadataRe.exec(line)) {
				var tag = metadataMatch[1];

				if (tags.indexOf(tag) != -1) {
					var start = metadataMatch.index;
					var end = start + tag.length;
					tagDetails.push([start, end]);
				}
			}
		}

		postMessage(retValue);
	}
});