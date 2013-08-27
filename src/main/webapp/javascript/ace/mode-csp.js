define(function(require, exports, module) {
	"use strict";
	var oop = require("../lib/oop");
	var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;
	var MyNewHighlightRules = function() {
		// regexp must not have capturing parentheses. Use (?:) instead.
		// regexps are ordered -> the first match is used
		this.$rules = {
			"start" : [
				{
					token: "comment",
					regex: /^#.*$/
				}, {
					// tokens that define metadata
					token: "keyword",
					regex: "^CHECKSUM\\s*=|^ID\\s*=|^Title\\s*=|^Subtitle\\s*=|^Abstract\\s*=|^Product\\s*=|^Version\\s*=|^Edition\\s*=" +
						"|^DTD\\s*=|^Copyright Holder\\s*=|^Brand\\s*=|^publican\\.cfg\\s*=|^BZProduct\\s*=|^BZComponent\\s*=|^BZVersion\\s*=" +
						"|^Pubsnumber\\s*=|^Bug Links\\s*=|^BZPRODUCT\\s*=|^BZCOMPONENT\\s*=|^BZVERSION\\s*=|^BZURL\\s*=|^Type\\s*=" +
						"|^Book Version\\s*=|^Copyright\\s*=|^Output Style\\s*="
				}, {
					// tokens that define structure
					token: "entity.name.section",
					regex: "^\\s*Chapter:|^\\s*Section:|^\\s*Appendix:|^\\s*Part:|^\\s*Procedure:|Refer-to:|Prerequisite:|T\\d+|R:\\d+|P:\\d+|\\bN\\d*\\b|X\\d+"
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
					regex : "Test Topic|Concept|Reference|Task|condition\\s*=|writer\\s*=|URL\\s*=|Description\\s*=|rev:"
				},
			]
		};
	};
	oop.inherits(MyNewHighlightRules, TextHighlightRules);
	exports.MyNewHighlightRules = MyNewHighlightRules;
});