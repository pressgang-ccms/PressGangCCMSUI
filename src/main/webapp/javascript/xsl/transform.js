var xsl;

self.addEventListener('message', function (e) {

	if (e.data.xsl) {
		xsl = e.data.xsl;
	}

	if (xsl && e.data.xml) {
		var xml = textToXML(e.data.xml);

		if (xml == null) {
			console.log("xml could not be parsed");
			postMessage(null);
			return;
		}

		try {
			// code for IE
			if (window.ActiveXObject) {
				postMessage(xml.transformNode(xsl));
				return;
			}
			// code for Mozilla, Firefox, Opera, etc.
			else if (document.implementation && document.implementation.createDocument) {
				xsltProcessor = new XSLTProcessor();
				xsltProcessor.importStylesheet(xsl);
				postMessage(xsltProcessor.transformToFragment(xml, document));
				return;
			}
		} catch (ex) {
			postMessage(null);
		}
	}
});

function textToXML(text) {
	try {
		var xml = null;

		if (window.DOMParser) {

			var parser = new DOMParser();
			xml = parser.parseFromString(text, "text/xml");

			var found = xml.getElementsByTagName("parsererror");

			if (!found || !found.length || !found[ 0 ].childNodes.length) {
				return xml;
			}

			return null;
		} else {

			xml = new ActiveXObject("Microsoft.XMLDOM");

			xml.async = false;
			xml.loadXML(text);

			return xml;
		}
	} catch (ex) {
		// suppress
	}
}