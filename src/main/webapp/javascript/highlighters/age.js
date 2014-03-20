var TOPIC_ID_RE = /\[\s*(\d+)/g;
var TOPIC_DETAILS_CACHE = {};
var TOPIC_DETAILS_MESSAGE = 'topicDetails';
var INIT_EVENT = 'init';
var TEXT_EVENT = 'text';
var baseRESTUrl = null;
var initialised = false;

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

    xmlhttp.onreadystatechange = function () {
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

self.addEventListener('message', function (e) {
    try {
        var message = JSON.parse(e.data);
        if (message.event == INIT_EVENT) {
            baseRESTUrl = message.restUrl;
            initialised = true
        } else if (message.event == TEXT_EVENT) {
            // Don't do anything until we are initialised
            if (!initialised) return;

            // clear the old lines
            for (var key in TOPIC_DETAILS_CACHE) {
                if (TOPIC_DETAILS_CACHE.hasOwnProperty(key)) {
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

            var topicsIds = "";

            // get the topic details
            for (var key in TOPIC_DETAILS_CACHE) {
                if (TOPIC_DETAILS_CACHE.hasOwnProperty(key)) {
                    if (TOPIC_DETAILS_CACHE[key].date == null) {
                        if (topicsIds.length != 0) {
                            topicsIds += ",";
                        }

                        topicsIds += key;
                    }
                }
            }

            // nothing changed, so just post back what we have already
            if (topicsIds.length == 0) {
                self.postMessage(JSON.stringify({event: TOPIC_DETAILS_MESSAGE, data: TOPIC_DETAILS_CACHE}));
                return;
            }

            var url = baseRESTUrl + "/1/topics/get/json/query;topicIds=" + topicsIds + "?expand=%7B%22branches%22%3A%5B%7B%22trunk%22%3A%7B%22name%22%3A%20%22topics%22%7D%7D%5D%7D";
            ajax(url, function (text) {
                    try {
                        var topicDetails = JSON.parse(text);

                        for (var topicIndex = 0, topicCount = topicDetails.items.length; topicIndex < topicCount; ++topicIndex) {
                            var topic = topicDetails.items[topicIndex].item;
                            TOPIC_DETAILS_CACHE[topic.id].date = new Date(topic.lastModified);
                        }
                    } catch (ex) {
                        // on the off chance the server returns something that isn't JSON
                    }

                    self.postMessage(JSON.stringify({event: TOPIC_DETAILS_MESSAGE, data: TOPIC_DETAILS_CACHE}));
                }, function (text) {
                    self.postMessage(JSON.stringify({event: TOPIC_DETAILS_MESSAGE, data: TOPIC_DETAILS_CACHE}));
                }
            );
        }
    } catch (ex) {
        //probably a message that was not valid JSON
    }

}, false);