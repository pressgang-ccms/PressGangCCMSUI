var TOPIC_ID_RE = /\[\s*(\d+)/g;
var TOPIC_DETAILS_CACHE = {};

/**
 * Making an AJAX call old style (because we can't use jQuery in a WebWorker)
 * @param url The url to call
 * @param done The callback to call when the response comes back
 * @param error The callback to call when the http request failed
 */
function ajax(url, done, error) {
	var xmlhttp;

	if (XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				done(xmlhttp.responseText);
			} else {
				error(xmlhttp.status);
			}

		}
	}

	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}

self.addEventListener('message', function(e) {
	try {
		var message = JSON.parse(e.data);
		if (message.event == "text") {

			// clear the old lines
			for(var key in TOPIC_DETAILS_CACHE)
			{
				if(TOPIC_DETAILS_CACHE.hasOwnProperty(key))
				{
					TOPIC_DETAILS_CACHE[key].lines = [];
				}
			}

			var lines = message.data.split("\n");

			for (var linesIndex = 0, linesCount = lines.length; linesIndex < linesCount; ++linesIndex) {
				var line = lines[linesIndex];
				var match = null;
				while (match = TOPIC_ID_RE.exec(line)) {
					var topicID = match[1];

					if (!TOPIC_DETAILS_CACHE[topicID]) {
						TOPIC_DETAILS_CACHE[topicID] = {date: null, lines: []};
					}

					TOPIC_DETAILS_CACHE[topicID].lines.push(linesIndex);
				}
			}

			var newTopicCount = 0;
			var processedTopics = 0;

			finished = function() {
				if (newTopicCount == processedTopics) {
					self.postMessage(JSON.stringify({event: 'topicDetails', data: TOPIC_DETAILS_CACHE}));
				}
			}

			// find out how many topics we need to get data on
			for(var key in TOPIC_DETAILS_CACHE)
			{
				if(TOPIC_DETAILS_CACHE.hasOwnProperty(key))
				{
					if (TOPIC_DETAILS_CACHE[key].date == null) {
						++newTopicCount;
					}
				}
			}

			// nothing has changed, so just post the message back
			if (newTopicCount == 0) {
				finished();
			}

			// get the topic details
			for(var key in TOPIC_DETAILS_CACHE)
			{
				if(TOPIC_DETAILS_CACHE.hasOwnProperty(key))
				{
					if (TOPIC_DETAILS_CACHE[key].date == null) {
						var url = "http://topika.ecs.eng.bne.redhat.com:8080/pressgang-ccms/rest/1/topic/get/json/" + key;

						ajax(url, function(key) {
							return function(text) {
								++processedTopics;
								var topicDetails = JSON.parse(text);
								TOPIC_DETAILS_CACHE[key].date = new Date(topicDetails.lastModified);
								finished();
							}
						}(key), function(text) {
							++processedTopics;
							finished();
						});
					}
				}
			}


		}
	} catch (ex) {
		//probably a message that was not valid JSON
	}

}, false);