define(
	"ace/mode/csp",
	["require","exports","module","ace/lib/oop","ace/mode/text","ace/tokenizer", "ace/mode/csp_highlighter", "ace/mode/matching_brace_outdent", "ace/mode/folding/csp_style"],
	function(require, exports, module) {
		"use strict";
		var oop = require("../lib/oop");
		// defines the parent mode
		var TextMode = require("./text").Mode;
		var Tokenizer = require("../tokenizer").Tokenizer;
		var MatchingBraceOutdent = require("./matching_brace_outdent").MatchingBraceOutdent;
		// defines the language specific highlighters and folding rules
		var MyNewHighlightRules = require("./csp_highlighter").HighlightRules;
		var MyNewFoldMode = require("./folding/csp_style").FoldMode;
		var Mode = function() {
			// set everything up
			var highlighter = new MyNewHighlightRules();
			this.$outdent = new MatchingBraceOutdent();
			this.foldingRules = new MyNewFoldMode();
			this.$tokenizer = new Tokenizer(highlighter.getRules());
		};
		oop.inherits(Mode, TextMode);
		(function() {
			// Extra logic goes here--we won't be covering all of this
			/* These are all optional pieces of code!
			 this.getNextLineIndent = function(state, line, tab) {
			 var indent = this.$getIndent(line);
			 return indent;
			 };
			 this.checkOutdent = function(state, line, input) {
			 return this.$outdent.checkOutdent(line, input);
			 };
			 this.autoOutdent = function(state, doc, row) {
			 this.$outdent.autoOutdent(doc, row);
			 };
			 this.createWorker = function(session) {
			 var worker = new WorkerClient(["ace"], "ace/mode/mynew_worker", "NewWorker");
			 worker.attachToDocument(session.getDocument());
			 return worker;
			 };
			 */
		}).call(Mode.prototype);
	exports.Mode = Mode;
});

define(
	"ace/mode/csp_highlighter",
	["require","exports","module","ace/lib/oop","ace/mode/text_highlight_rules"], function(require, exports, module) {
	"use strict";
	var oop = require("../lib/oop");
	var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;
	var HighlightRules = function() {
		// regexp must not have capturing parentheses. Use (?:) instead.
		// regexps are ordered -> the first match is used
		this.$rules = {
			"start" : [
				{
					token: "comment",
					regex: "^\\s*#.*$"
				}, {
					// tokens that define metadata
					token: "keyword",
					regex: "^CHECKSUM\\s*=|^ID\\s*=|^Title\\s*=|^Subtitle\\s*=|^Edition\\s*=|^Book Version\\s*=|^Pubsnumber\\s*=|^Product\\s*=|^Abstract\\s*=|^Description\\s*=|^Copyright Holder\\s*=|^Copyright Year\\s*=|^Version\\s*=|^Brand\\s*=|^Bug Links\\s*=|^BZProduct\\s*=|^BZComponent\\s*=|^BZVersion\\s*=|^BZKeywords\\s*=|^BZServer\\s*=|^BZURL\\s*=|^Type\\s*=|^Brand Logo\\s*=|^publican.cfg\\s*=|^Inline Injection\\s*=|^DTD\\s*=|^Revision History\\s*=|^Feedback\\s*=|^Legal Notice\\s*=|^BZ Assignee\\s*=|^GroupId\\s*=|^ArtifactId\\s*=|^Additional Files\\s*=|^Files\\s*=|^JIRAProject\\s*=|^JIRAComponent\\s*=|^JIRAVersion\\s*=|^JIRALabels\\s*=|^JIRAServer\\s*=|^BZPRODUCT\\s*=|^BZCOMPONENT\\s*=|^BZVERSION\\s*=|^BZURL\\s*=|^Output Style\\s*=|^spaces\\s*=|^Debug\\s*=|^Author Group\\s*=|^Entities\\s*=|^[-\\w\\d]+-publican.cfg\\s*=|^Default publican.cfg\\s*="
				}, {
					// tokens that define structure
					token: "constant.language",
					regex: "^\\s*Chapter:|^\\s*Section:|^\\s*Appendix:|^\\s*Part:|^\\s*Process:|^\\s*Preface:|Refer-to:|Prerequisite:|Link-List:|T\\d+|R:\\d+|P:\\d+|\\bN\\d*\\b|X\\d+"
				},  {
					token : "constant.numeric",
					regex : "\\b[0-9]+\\b"
				},  {
					token : "lparen",
					regex : "[[({]"
				}, {
					token : "rparen",
					regex : "[\\])}]"
				},  {
					// tokens that appear in brackets as values
					token : "constant.language",
                    regex : "condition\\s*=|writer\\s*=|URL\\s*=|Description\\s*=|rev:"
//					regex : "Test Topic|Concept|Reference|Task|condition\\s*=|writer\\s*=|URL\\s*=|Description\\s*=|rev:"
				}
			]
		};
	};
	oop.inherits(HighlightRules, TextHighlightRules);
	exports.HighlightRules = HighlightRules;
});

define(
	"ace/mode/folding/csp_style",
	["require","exports","module","ace/lib/oop","ace/range","ace/mode/folding/fold_mode"],
	function(require, exports, module) {
		"use strict";
		var oop = require("../../lib/oop");
		var Range = require("../../range").Range;
		var BaseFoldMode = require("./fold_mode").FoldMode;
		var FoldMode = exports.FoldMode = function() {};
		oop.inherits(FoldMode, BaseFoldMode);
		(function() {
            this.foldingStartMarker = /^((([\w\d-]+-)?publican.cfg|Entities)\s*=\s*\[|\s*(Chapter:|Section:|Appendix:|Part:|Process:|Preface:))(?:.*$)/;
            this.getFoldWidgetRange = function(session, foldStyle, row) {
                var metaDataRe = /^\w[-\w\.\s]+=\s*\[.*$/;
                var levelRe = /^\s*(Chapter:|Section:|Appendix:|Part:|Process:|Preface:).*$/
                var line = session.getLine(row);
                if (line.match(metaDataRe)) {
                    return this._getFoldMetaDataWidgetRange(session, foldStyle, row);
                } else if (line.match(levelRe)) {
                    return this._getFoldLevelWidgetRange(session, foldStyle, row);
                } else {
                    return;
                }
            };

            this._getFoldMetaDataWidgetRange = function(session, foldStyle, row) {
                var reNext = /^.*\[.*$/;
                var endRe = /^.*?\]\s*$/;
                var line = session.getLine(row);

                var startColumn = line.indexOf('[') + 1;
                var endColumn = startColumn;
                var maxRow = session.getLength();
                var startRow = row;
                var endRow = row;

                while (++row < maxRow) {
                    line = session.getLine(row);
                    if (reNext.test(line)) {
                        break;
                    } else if (endRe.test(line)) {
                        endRow = row;
                        endColumn = line.indexOf(']');
                        break;
                    }
                }

                if (endRow > startRow) {
                    return new Range(startRow, startColumn, endRow, endColumn);
                }
            }

            this._getFoldLevelWidgetRange = function(session, foldStyle, row) {
                var commentRe = /^\s*(#.*)?$/;
                var blankLineRe = /^\s*$/;
                var spaceCountRe = /^\s*/;
                var line = session.getLine(row);

                var startColumn = line.length;
                var maxRow = session.getLength();
                var startRow = row;
                var endRow = row;
                var indentationCount = line.match(spaceCountRe)[0].length;

                while (++row < maxRow) {
                    line = session.getLine(row);
                    var lineIndentationCount = line.match(spaceCountRe)[0].length;

                    if (line.match(blankLineRe)) {
                        continue;
                    } else if (lineIndentationCount <= indentationCount && !line.match(commentRe)) {
                        break;
                    }

                    endRow = row;
                }

                if (endRow > startRow) {
                    var endColumn = session.getLine(endRow).length;
                    return new Range(startRow, startColumn, endRow, endColumn);
                }
            }
		}).call(FoldMode.prototype);
});

define("ace/mode/matching_brace_outdent",["require","exports","module","ace/range"],function(e,t,n){var r=e("../range").Range,i=function(){};(function(){this.checkOutdent=function(e,t){return/^\s+$/.test(e)?/^\s*\}/.test(t):!1},this.autoOutdent=function(e,t){var n=e.getLine(t),i=n.match(/^(\s*\})/);if(!i)return 0;var s=i[1].length,o=e.findMatchingBracket({row:t,column:s});if(!o||o.row==t)return 0;var u=this.$getIndent(e.getLine(o.row));e.replace(new r(t,0,t,s-1),u)},this.$getIndent=function(e){return e.match(/^\s*/)[0]}}).call(i.prototype),t.MatchingBraceOutdent=i});