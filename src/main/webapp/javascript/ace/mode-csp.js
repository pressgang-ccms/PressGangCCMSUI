define(
    "ace/mode/csp",
    ["require", "exports", "module", "ace/lib/oop", "ace/mode/text", "ace/tokenizer", "ace/mode/csp_highlighter", "ace/mode/matching_brace_outdent", "ace/mode/folding/csp_style"],
    function (require, exports, module) {
        "use strict";
        var oop = require("../lib/oop");
        // defines the parent mode
        var TextMode = require("./text").Mode;
        var Tokenizer = require("../tokenizer").Tokenizer;
        var MatchingBraceOutdent = require("./matching_brace_outdent").MatchingBraceOutdent;
        // defines the language specific highlighters and folding rules
        var CSPHighlightRules = require("./csp_highlighter").CSPHighlightRules;
        var CSPFoldMode = require("./folding/csp_style").FoldMode;
        var CSPCompletions = require("./csp_completions").CSPCompletions;
        var CSPMode = function () {
            // set everything up
            var highlighter = new CSPHighlightRules();
            this.$outdent = new MatchingBraceOutdent();
            this.foldingRules = new CSPFoldMode();
            this.$tokenizer = new Tokenizer(highlighter.getRules());
            this.$completer = new CSPCompletions();

            this.getCompletions = function(state, session, pos, prefix) {
                return this.$completer.getCompletions(state, session, pos, prefix);
            };
        };
        oop.inherits(CSPMode, TextMode);
        (function () {
            // Extra logic goes here--we won't be covering all of this
        }).call(CSPMode.prototype);
        exports.Mode = CSPMode;
    });

define(
    "ace/mode/csp_highlighter",
    ["require", "exports", "module", "ace/lib/oop", "ace/mode/text_highlight_rules"], function (require, exports, module) {
        "use strict";
        var oop = require("../lib/oop");
        var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;
        var CSPHighlightRules = function (normalize) {
            // regexp must not have capturing parentheses. Use (?:) instead.
            // regexps are ordered -> the first match is used
            this.$rules = {
                "start": [
                    {
                        token: "comment",
                        regex: "^\\s*#.*$"
                    },
                    {
                        include: "metadata"
                    },
                    {
                        include: "container"
                    },
                    {
                        token: ["text", "keyword.reference", "keyword.operator.separator"],
                        regex: "(\\[\\s*)(Refer-to|Prerequisite|Link-List|R|P|L)(:)",
                        push: "reference"
                    },
                    {
                        token: "lparen",
                        regex: "\\[",
                        push: "attributes"
                    }
                ],

                "space": [
                    {
                        token: "text",
                        regex: "\\s+"
                    }
                ],

                "attributes": [
                    {
                        token: "entity.other.attribute-name",
                        regex: "(W|w)riter|URL|(D|d)escription|rev"
                    },
                    {
                        token: "entity.other.attribute-name",
                        regex: "condition",
                        push: [
                            {
                                include: "space"
                            },
                            {
                                token: "keyword.operator.separator",
                                regex: "="
                            },
                            {
                                token: "text",
                                regex: "(?=,|]$|]\\s*\\[)",
                                next: "pop"
                            },
                            {
                                defaultToken : "string.regexp"
                            }
                        ]
                    },
                    {
                        include: "id"
                    },
                    {
                        token: "keyword.operator.separator",
                        regex: "=|:"
                    },
                    {
                        include: "space"
                    },
                    {
                        token: "rparen",
                        regex: "]",
                        next: "pop"
                    },
                    {
                        defaultToken : "text.attribute"
                    }
                ],

                "id" : [
                    {
                        token: "constant.numeric",
                        regex: "\\bN\\b|\\b(N|X|C|T)\\d+\\b|\\b\\d+\\b|\\bT-\\w+\\b"
                    }
                ],

                "reference" : [
                    {
                        token: "lparen",
                        regex: "\\[",
                        push: "attributes"
                    },
                    {
                        include: "id"
                    },
                    {
                        token: "rparen",
                        regex: "]",
                        next: "pop"
                    },
                    {
                        defaultToken : "text.reference"
                    }
                ],

                "metadata": [
                    {
                        token: "keyword.metadata",
                        regex: "^(CHECKSUM|ID|Title|Subtitle|Edition|Book Version|Pubsnumber|Product|Description|Copyright Holder|Copyright Year|Version|Bug Links|BZProduct|BZComponent|BZVersion|BZKeywords|BZServer|BZURL|Type|Brand Logo|Brand|publican.cfg|Inline Injection|Format|BZ Assignee|GroupId|ArtifactId|Additional Files|Files|JIRAProject|JIRAComponent|JIRAVersion|JIRALabels|JIRAServer|BZPRODUCT|BZCOMPONENT|BZVERSION|Output Style|spaces|Debug|Entities|[-\\w\\d]+-publican.cfg|Default publican.cfg|Index|POM Version|Locale)",
                        next: [
                            {
                                include: "space"
                            },
                            {
                                token: "keyword.operator.separator",
                                regex: "="
                            },
                            {
                                token: "constant.language",
                                regex: "ON|OFF"
                            },
                            {
                                token: "text",
                                regex: "$|^",
                                next: "start"
                            }
                        ]
                    },
                    {
                        token: "keyword.metadata",
                        regex: "^(Abstract|Revision History|Feedback|Legal Notice|Author Group)",
                        next: [
                            {
                                include: "space"
                            },
                            {
                                token: "keyword.operator.separator",
                                regex: "="
                            },
                            {
                                token: "lparen",
                                regex: "\\[",
                                next: "attributes"
                            },
                            {
                                token: "text",
                                regex: "$|^",
                                next: "start"
                            }
                        ]
                    }
                ],

                "container": [
                    {
                        token: ["text", "keyword.container", "keyword.operator.separator"],
                        regex: "^(\\s*)(Chapter|Section|Appendix|Part|Process|Preface|Initial Text)(:)"
                    }
                ]
            };

            if (this.constructor === CSPHighlightRules)
                this.normalizeRules();
        };
        oop.inherits(CSPHighlightRules, TextHighlightRules);
        exports.CSPHighlightRules = CSPHighlightRules;
    });

define(
    "ace/mode/folding/csp_style",
    ["require", "exports", "module", "ace/lib/oop", "ace/range", "ace/mode/folding/fold_mode"],
    function (require, exports, module) {
        "use strict";
        var oop = require("../../lib/oop");
        var Range = require("../../range").Range;
        var BaseFoldMode = require("./fold_mode").FoldMode;
        var FoldMode = exports.FoldMode = function () {
        };
        oop.inherits(FoldMode, BaseFoldMode);
        (function () {
            this.foldingStartMarker = /^((([\w\d-]+-)?publican.cfg|Entities)\s*=\s*\[|\s*(Chapter:|Section:|Appendix:|Part:|Process:|Preface:|Initial Text:))(?:.*$)/;
            this.getFoldWidgetRange = function (session, foldStyle, row) {
                var metaDataRe = /^\w[-\w\.\s]+=\s*\[.*$/;
                var levelRe = /^\s*(Chapter:|Section:|Appendix:|Part:|Process:|Preface:|Initial Text:).*$/
                var line = session.getLine(row);
                if (line.match(metaDataRe)) {
                    return this._getFoldMetaDataWidgetRange(session, foldStyle, row);
                } else if (line.match(levelRe)) {
                    return this._getFoldLevelWidgetRange(session, foldStyle, row);
                } else {
                    return;
                }
            };

            this._getFoldMetaDataWidgetRange = function (session, foldStyle, row) {
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

            this._getFoldLevelWidgetRange = function (session, foldStyle, row) {
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

define('ace/mode/csp_completions', ['require', 'exports', 'module'], function(require, exports, module) {
    "use strict";

    var TokenIterator = require("../token_iterator").TokenIterator;

    var metadata = [
        "ID",
        "Title",
        "Subtitle",
        "Edition",
        "Book Version",
        "Pubsnumber",
        "Product",
        "Description",
        "Copyright Holder",
        "Copyright Year",
        "Version",
        "Brand",
        "Bug Links",
        "BZProduct",
        "BZComponent",
        "BZVersion",
        "BZKeywords",
        "BZServer",
        "BZURL",
        "Type",
        "Brand Logo",
        "publican.cfg",
        "Inline Injection",
        "Format",
        "BZ Assignee",
        "GroupId",
        "ArtifactId",
        "Additional Files",
        "Files",
        "JIRAProject",
        "JIRAComponent",
        "JIRAVersion",
        "JIRALabels",
        "JIRAServer",
        "Output Style",
        "spaces",
        "Debug",
        "Entities",
        "Default publican.cfg",
        "Index",
        "POM Version",
        "Locale",
        "Abstract",
        "Revision History",
        "Feedback",
        "Legal Notice",
        "Author Group"
    ];
    
    var containers = [
        "Part",
        "Chapter",
        "Appendix",
        "Preface",
        "Process",
        "Section",
        "Initial Text"
    ];

    var attributes = [
        "condition",
        "writer",
        "description",
        "URL",
        "rev"
    ];

    var references = [
        "Refer-to",
        "Prerequisite",
        "Link-List"
    ];

    function hasType(token, type) {
        var tokenTypes = token.type.split('.');
        return type.split('.').every(function(type){
            return (tokenTypes.indexOf(type) !== -1);
        });
    }

    var CSPCompletions = function() {

    };

    (function() {
        this.getCompletions = function(state, session, pos, prefix) {
            var token = session.getTokenAt(pos.row, pos.column);
            var line = session.getLine(pos.row);
            var startCol = pos.column - prefix.length;

            // root element (metadata + containers)
            if (startCol === 0)
                return this.getRootCompletions(state, session, pos, prefix);

            // containers
            if (line.slice(0, pos.column).trim() === prefix)
                return this.getContainerCompletions(state, session, pos, prefix);

            if (!token)
                return [];

            var retValue = []
            var c = line.charAt(startCol - 1);

            // attribute
            if (hasType(token, 'attribute') && (startCol === 1 || c !== '['))
                retValue = retValue.concat(this.getAttributeCompletions(state, session, pos, prefix));

            // reference (this will be "attribute" until a colon is typed)
            if (hasType(token, 'reference') || (hasType(token, 'attribute') && c === '['))
                retValue = retValue.concat(this.getReferenceCompletions(state, session, pos, prefix));

            return retValue;
        };

        this.getRootCompletions = function(state, session, pos, prefix) {
            var allMetadata = metadata.map(function(element){
                return {
                    value: element,
                    snippet: element + " = $0",
                    meta: "metadata"
                };
            });
            var allContainers = containers.map(function(element){
                return {
                    value: element,
                    snippet: element + ": $0",
                    meta: "container"
                };
            });

            return allMetadata.concat(allContainers);
        };

        this.getContainerCompletions = function(state, session, pos, prefix) {
            var allContainers;
            if (pos.column > (2 + prefix.length)) {
                // section, process and initial text can be the only 2nd level child containers
                allContainers = containers.slice(4);
            } else {
                allContainers = containers.slice(1);
            }
            return allContainers.map(function(element){
                return {
                    value: element,
                    snippet: element + ": $0",
                    meta: "container"
                };
            });
        };

        this.getAttributeCompletions = function(state, session, pos, prefix) {
            return attributes.map(function(attribute){
                if (attribute == "rev") {
                    return {
                        caption: attribute,
                        snippet: attribute + ": $0",
                        meta: "attribute"
                    };
                } else {
                    return {
                        caption: attribute,
                        snippet: attribute + " = $0",
                        meta: "attribute"
                    };
                }
            });
        };

        this.getReferenceCompletions = function(state, session, pos, prefix) {
            return references.map(function(reference){
                return {
                    caption: reference,
                    snippet: reference + ": $0",
                    meta: "reference"
                };
            });
        };
    }).call(CSPCompletions.prototype);

    exports.CSPCompletions = CSPCompletions;
});

define("ace/mode/matching_brace_outdent", ["require", "exports", "module", "ace/range"], function (e, t, n) {
    var r = e("../range").Range, i = function () {
    };
    (function () {
        this.checkOutdent = function (e, t) {
            return/^\s+$/.test(e) ? /^\s*\]/.test(t) : !1
        }, this.autoOutdent = function (e, t) {
            var n = e.getLine(t), i = n.match(/^(\s*\])/);
            if (!i)return 0;
            var s = i[1].length, o = e.findMatchingBracket({row: t, column: s});
            if (!o || o.row == t)return 0;
            var u = this.$getIndent(e.getLine(o.row));
            e.replace(new r(t, 0, t, s - 1), u)
        }, this.$getIndent = function (e) {
            return e.match(/^\s*/)[0]
        }
    }).call(i.prototype), t.MatchingBraceOutdent = i
});