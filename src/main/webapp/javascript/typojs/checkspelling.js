importScripts('typo_proto.js');

var positiveDictionary, negativeDictionary, negativePhraseDictionary;

var TypoClone = function(source) {
	this.platform = source.platform;
	this.dictionary = source.dictionary;
	this.rules = source.rules;
	this.dictionaryTable = source.dictionaryTable;
	this.compoundRules = source.compoundRules;
	this.compoundRuleCodes = source.compoundRuleCodes;
	this.replacementTable = source.replacementTable;
	this.flags = source.flags;
};

TypoClone.prototype = typoPrototype;

// Check the spelling of a line, and return [start, end]-pairs for misspelled words.
self.addEventListener('message', function(e) {
	/*
	 Web workers can only pass strings as messages. That means we can pass the state of a dictionary (loaded and parsed
	 dictionary and affix files), but not the class structure itself. To save us parsing that data again while also
	 retaining the logic, the Typo prototype has been moved into a seperate file (typo_proto.js). This is then
	 reattached to the object states passed in from the main thread.
	 */
	if (e.data.positiveDictionary) {
		positiveDictionary = new TypoClone(e.data.positiveDictionary);
	}

	if (e.data.negativeDictionary) {
		negativeDictionary = new TypoClone(e.data.negativeDictionary);
	}

	if (e.data.negativePhraseDictionary) {
		negativePhraseDictionary = new TypoClone(e.data.negativePhraseDictionary);
	}

	if (e.data.lines) {

		var lines = e.data.lines;

		var retValue = [];
		for (var lineIndex = 0, linesLength = lines.length; lineIndex < linesLength; ++lineIndex) {

			var line = lines[lineIndex];

			// remove all xml/html elements
			var tagRe = /<.*?>/;
			var tagMatch = null;
			while ((tagMatch = line.match(tagRe)) != null) {
				var tagLength = tagMatch[0].length;
				var replacementString = "";
				for (var i = 0; i < tagLength; ++i) {
					replacementString += " ";
				}
				line = line.replace(tagRe, replacementString);
			}

			// remove all xml/html entities
			var entityRe = /&.*?;/;
			var entityMatch = null;
			while ((entityMatch = line.match(entityRe)) != null) {
				var entityLength = entityMatch[0].length;
				var replacementString = "";
				for (var i = 0; i < entityLength; ++i) {
					replacementString += " ";
				}
				line = line.replace(entityRe, replacementString);
			}

			// remove all urls
			var urlRe = /\b((?:https?:\/\/|www\d{0,3}[.]|[a-z0-9.\-]+[.][a-z]{2,4}\/)(?:[^\s()<>]+|\(([^\s()<>]+|(\([^\s()<>]+\)))*\))+(?:\(([^\s()<>]+|(\([^\s()<>]+\)))*\)|[^\s`!()\[\]{};:'".,<>?«»“”‘’]))/i;
			var urlMatch = null;
			while ((urlMatch = line.match(urlRe)) != null) {
				var urlLength = urlMatch[0].length;
				var replacementString = "";
				for (var i = 0; i < urlLength; ++i) {
					replacementString += " ";
				}
				line = line.replace(urlRe, replacementString);
			}

			// remove all numbers
			var numberRe = /\b\d+\b/;
			var numberMatch = null;
			while ((numberMatch = line.match(numberRe)) != null) {
				var numberLength = numberMatch[0].length;
				var replacementString = "";
				for (var i = 0; i < numberLength; ++i) {
					replacementString += " ";
				}
				line = line.replace(numberRe, replacementString);
			}

			// replace any character that doesn't make up a word with a space, and then split on space
			var phraseWords = line.split(/\s/);
			var words = line.replace(/[^a-zA-Z0-9'\\-]/g, ' ').split(/\s/);

			var misspelled = [];
			var badWords = [];
			var badPhrases = [];
			var testedWords = [];

			for (var wordIndex = 0, wordCount = phraseWords.length; wordIndex < wordCount; ++wordIndex) {
				testedWords.push(false);
			}

			// How many words can appear in a phrase that will be checked against
			// the dictionaries
			var maxWordsInPhrase = 7;

			outerloop:
			for (var wordGroupIndex = maxWordsInPhrase; wordGroupIndex > 0; --wordGroupIndex) {
				var i = 0;

				// When checking single words, use the words array. Otherwise use the phraseWords array.
				var checkArray =  wordGroupIndex == 1 ? words : phraseWords;

				var lastCheckedWord = 0;

				innerloop:
				for (var wordIndex = 0, wordCount = checkArray.length - wordGroupIndex + 1; wordIndex < wordCount; ++wordIndex) {

					// do this here so the continues down below don't stop us incrementing the value
					var firstWordLengthWithSpace = checkArray[wordIndex].length + 1;
					i += firstWordLengthWithSpace;

					if (wordIndex < lastCheckedWord) {
						continue;
					}

					var checkWord = "";

					for (var checkWordIndex = wordIndex, checkWordIndexMax = wordIndex + wordGroupIndex; checkWordIndex < checkWordIndexMax; ++checkWordIndex) {

						if (testedWords[checkWordIndex]) {
							continue innerloop;
						}

						if (checkArray[checkWordIndex].length == 0) {
							continue innerloop;
						}

						if (checkWordIndex != wordIndex) {
							checkWord += " ";
						}
						checkWord += checkArray[checkWordIndex];
					}

					// skip non word characters at the start and end of the word or phrase
					var match = checkWord.match(/^[^a-zA-Z0-9]+/);
					var startingWhitespace = match != null ? match[0].length : 0;

					var endMatch = checkWord.match(/[^a-zA-Z0-9]+$/);
					var endingWhitespace = endMatch != null ? endMatch[0].length : 0;

					// subtract firstWordLengthWithSpace to account for the fact that it was added
					// at the start of the loop
					var start = i + startingWhitespace - firstWordLengthWithSpace;
					var end = i + checkWord.length - firstWordLengthWithSpace - endingWhitespace;

					if (start < end && checkWord.trim().length != 0) {

						var wordConsumed = false;

						if (negativePhraseDictionary != null && negativePhraseDictionary.check(checkWord.trim())) {
							wordConsumed = true;
							badPhrases[badPhrases.length] = [start, end];
						} else if (negativeDictionary != null && negativeDictionary.check(checkWord.trim())) {
							wordConsumed = true;
							badWords[badWords.length] = [start, end];
						} else if (wordGroupIndex == 1) {
							// check for double words
							if (wordIndex < wordCount - 1 && checkArray[wordIndex+1] == checkWord)  {
								// don't test the next word
								testedWords[wordIndex] = testedWords[wordIndex + 1] = true;
								// this is a bad phrase
								badPhrases[badPhrases.length] = [start, end + checkArray[wordIndex+1].length + 1];
							} else if (!positiveDictionary.check(checkWord.trim())) {
								misspelled[misspelled.length] = [start, end];
							}
						}

						if (wordConsumed) {
							// Words will only fall into one dictionary item. Here we make sure that any words in this negative
							// match don't get used again.
							for (var checkWordIndex = wordIndex, checkWordIndexMax = wordIndex + wordGroupIndex; checkWordIndex < checkWordIndexMax; ++checkWordIndex) {
								testedWords[checkWordIndex] = true;
							}

							lastCheckedWord = wordIndex + wordGroupIndex;
						}
					}
				}
			}

			retValue.push({misspelled: misspelled, badWords: badWords, badPhrases: badPhrases});
		}

		postMessage(retValue);
	}
});