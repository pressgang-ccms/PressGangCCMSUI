define('ace/mode/docbook', ['require', 'exports', 'module' , 'ace/lib/oop', 'ace/mode/xml'], function(require, exports, module) {

    var oop = require("../lib/oop");
    var XMLMode = require("./xml").Mode;
    var DocBookCompletions = require("./docbook_completions").DocBookCompletions;

    var Mode = function() {
        XMLMode.call(this);
        this.$completer = new DocBookCompletions();

        this.getCompletions = function(state, session, pos, prefix) {
            return this.$completer.getCompletions(state, session, pos, prefix);
        };
    };
    oop.inherits(Mode, XMLMode);

    (function() {
        this.$id = "ace/mode/docbook";
    }).call(Mode.prototype);

    exports.Mode = Mode;
});

// Modified from the HTML completions
define('ace/mode/docbook_completions', ['require', 'exports', 'module'], function(require, exports, module) {
    "use strict";

    var TokenIterator = require("../token_iterator").TokenIterator;

    var globalAttributes = [
        "arch",
        "condition",
        "conformance",
        "dir",
        "id",
        "lang",
        "os",
        "remap",
        "role",
        "revision",
        "revisionflag",
        "security",
        "userlevel",
        "vendor",
        "xml:base",
        "xreflabel",
        "wordsize"
    ];

    var htmlAttributes = ["ondblclick",
        "onkeydown",
        "onkeyup",
        "onmouseup",
        "onmouseover",
        "onkeypress",
        "onmousedown",
        "onclick",
        "title",
        "class",
        "style",
        "onmousemove",
        "onmouseout",
        "xml:lang"];

    var synopsisinfo = ["linenumbering", "language", "xml:space", "continuation", "format", "startinglinenumber"];
    var graphicData = ["width", "srccredit", "contentdepth", "entityref", "scalefit", "contentwidth", "align", "valign", "depth", "fileref", "format", "scale"];
    var figure = ["floatstyle", "float", "pgwide", "label"];
    var moreinfo = ["moreinfo"];

    var attributeMap = {
        "abbrev": null,
        "abstract": null,
        "accel": null,
        "ackno": null,
        "acronym": null,
        "action": moreinfo,
        "address": synopsisinfo,
        "affiliation": null,
        "alt": null,
        "anchor": ["pagenum"],
        "answer": null,
        "appendix": ["label", "status"],
        "appendixinfo": null,
        "application": ["class", "moreinfo"],
        "area": ["coords", "label", "linkends", "otherunits", "units"],
        "areaset": ["coords", "label", "otherunits", "units"],
        "areaspec": ["otherunits", "units"],
        "arg": ["choice", "rep"],
        "article": ["class", "status", "parentbook"],
        "articleinfo": null,
        "artpagenums": null,
        "attribution": null,
        "audiodata": ["srccredit", "entityref", "fileref", "format"],
        "audioobject": null,
        "author": null,
        "authorblurb": null,
        "authorgroup": null,
        "authorinitials": null,
        "beginpage": ["pagenum"],
        "bibliocoverage": ["othertemporal", "spatial", "otherspatial", "temporal"],
        "bibliodiv": ["status"],
        "biblioentry": null,
        "bibliography": ["status"],
        "bibliographyinfo": null,
        "biblioid": ["otherclass", "class"],
        "bibliolist": null,
        "bibliomisc": null,
        "bibliomixed": null,
        "bibliomset": ["relation"],
        "biblioref": ["xrefstyle", "endterm", "begin", "end", "linkend", "units"],
        "bibliorelation": ["othertype", "otherclass", "type", "class"],
        "biblioset": ["relation"],
        "bibliosource": ["otherclass", "class"],
        "blockinfo": null,
        "blockquote": null,
        "book": ["status", "fpi", "label"],
        "bookinfo": ["contents"],
        "bridgehead": ["renderas"],
        "callout": ["arearefs"],
        "calloutlist": null,
        "caption": htmlAttributes.concat(["align"]),
        "caution": null,
        "chapter": ["status", "label"],
        "chapterinfo": null,
        "citation": null,
        "citebiblioid": ["otherclass", "class"],
        "citerefentry": null,
        "citetitle": ["pubwork"],
        "city": null,
        "classname": null,
        "classsynopsis": ["language", "class"],
        "classsynopsisinfo": synopsisinfo,
        "cmdsynopsis": ["sepchar", "cmdlength", "label"],
        "co": ["label", "linkends"],
        "code": ["language"],
        "col": htmlAttributes.concat(["align", "valign", "span", "width", "charoff"]),
        "colgroup": htmlAttributes.concat(["align", "valign", "span", "width", "charoff"]),
        "collab": null,
        "collabname": null,
        "colophon": ["status"],
        "colspec": ["rowsep", "align", "colwidth", "colname", "colnum", "colsep", "charoff"],
        "command": moreinfo,
        "computeroutput": moreinfo,
        "confdates": null,
        "confgroup": null,
        "confnum": null,
        "confsponsor": null,
        "conftitle": null,
        "constant": ["class"],
        "constructorsynopsis": ["language"],
        "contractnum": null,
        "contractsponsor": null,
        "contrib": null,
        "copyright": null,
        "coref": ["label", "linkend"],
        "corpauthor": null,
        "corpcredit": ["class"],
        "corpname": null,
        "country": null,
        "database": ["moreinfo", "class"],
        "date": null,
        "dedication": ["status"],
        "destructorsynopsis": ["language"],
        "edition": null,
        "editor": null,
        "email": null,
        "emphasis": null,
        "entry": htmlAttributes.concat(["rowsep", "align", "valign", "colname", "rotate", "nameend", "colsep", "charoff", "namest", "char", "morerows"]),
        "entrytbl": htmlAttributes.concat(["rowsep", "align", "spanname", "colname", "cols", "colsep", "nameend", "tgroupstyle", "charoff", "namest", "char"]),
        "envar": null,
        "epigraph": null,
        "equation": ["floatstyle", "label"],
        "errorcode": moreinfo,
        "errorname": null,
        "errortext": null,
        "errortype": null,
        "example": ["width", "floatstyle", "label"],
        "exceptionname": null,
        "fax": null,
        "fieldsynopsis": ["language"],
        "figure": figure,
        "filename": ["path", "moreinfo", "class"],
        "firstname": null,
        "firstterm": ["baseform", "linkend"],
        "footnote": ["label"],
        "footnoteref": ["label", "linkend"],
        "foreignphrase": null,
        "formalpara": null,
        "funcdef": null,
        "funcparams": null,
        "funcprototype": null,
        "funcsynopsis": ["label"],
        "funcsynopsisinfo": synopsisinfo,
        "function": moreinfo,
        "glossary": ["status"],
        "glossaryinfo": null,
        "glossdef": ["subject"],
        "glossdiv": ["status"],
        "glossentry": ["sortas"],
        "glosslist": null,
        "glosssee": ["otherterm"],
        "glossseealso": ["otherterm"],
        "glossterm": ["baseform", "linkend"],
        "graphic": graphicData,
        "graphicco": null,
        "group": ["choice", "rep"],
        "guibutton": moreinfo,
        "guiicon": moreinfo,
        "guilabel": moreinfo,
        "guimenu": moreinfo,
        "guimenuitem": moreinfo,
        "guisubmenu": moreinfo,
        "hardware": moreinfo,
        "highlights": null,
        "holder": null,
        "honorific": null,
        "imagedata": graphicData,
        "imageobject": null,
        "imageobjectco": null,
        "important": null,
        "index": ["type"],
        "indexdiv": null,
        "indexentry": null,
        "indexinfo": null,
        "indexterm": ["significance", "zone", "pagenum", "scope", "type", "startref", "class"],
        "informalequation": ["floatstyle"],
        "informalexample": ["width", "floatstyle"],
        "informalfigure": figure,
        "informaltable": htmlAttributes.concat(["rowsep", "floatstyle", "pgwide", "orient", "frame", "rule", "bgcolor", "summary", "tabstyle", "cellspacing", "colspec", "shortentry", "border", "rowheader", "cellpadding", "tocentry"]),
        "initializer": null,
        "inlineequation": null,
        "inlinegraphic": graphicData,
        "inlinemediaobject": null,
        "interface": moreinfo,
        "interfacename": null,
        "invpartnumber": null,
        "isbn": null,
        "issn": null,
        "issuenum": null,
        "itemizedlist": ["mark", "spacing"],
        "itermset": null,
        "jobtitle": null,
        "keycap": ["function", "moreinfo", "otherfunction"],
        "keycode": null,
        "keycombo": ["otheraction", "moreinfo", "action"],
        "keysym": null,
        "keyword": null,
        "keywordset": null,
        "label": null,
        "legalnotice": null,
        "lineage": null,
        "lineannotation": null,
        "link": ["xrefstyle", "endterm", "type", "linkend"],
        "listitem": ["override"],
        "literal": moreinfo,
        "literallayout": synopsisinfo.concat(["width", "class"]),
        "lot": ["label"],
        "lotentry": ["srccredit", "pagenum", "linkend"],
        "manvolnum": null,
        "markup": null,
        "mathphrase": null,
        "medialabel": ["class"],
        "mediaobject": null,
        "mediaobjectco": null,
        "member": null,
        "menuchoice": moreinfo,
        "methodname": null,
        "methodparam": ["choice", "rep"],
        "methodsynopsis": ["language"],
        "modespec": ["application"],
        "modifier": null,
        "mousebutton": moreinfo,
        "msg": null,
        "msgaud": null,
        "msgentry": null,
        "msgexplan": null,
        "msginfo": null,
        "msglevel": null,
        "msgmain": null,
        "msgorig": null,
        "msgrel": null,
        "msgset": null,
        "msgsub": null,
        "msgtext": null,
        "note": null,
        "objectinfo": null,
        "olink": ["xrefstyle", "targetdoc", "localinfo", "linkmode", "targetdoc", "targetptr", "type"],
        "ooclass": null,
        "ooexception": null,
        "oointerface": null,
        "option": null,
        "optional": null,
        "orderedlist": ["inheritnum", "continuation", "numeration", "spacing"],
        "orgdiv": null,
        "orgname": ["otherclass", "class"],
        "otheraddr": null,
        "othercredit": ["class"],
        "othername": null,
        "package": null,
        "pagenums": null,
        "para": null,
        "paramdef": ["choice"],
        "parameter": ["moreinfo", "class"],
        "part": ["status", "label"],
        "partinfo": null,
        "partintro": ["label"],
        "personblurb": null,
        "personname": null,
        "phone": null,
        "phrase": null,
        "pob": null,
        "postcode": null,
        "preface": ["status"],
        "prefaceinfo": null,
        "primary": ["sortas"],
        "primaryie": ["linkends"],
        "printhistory": null,
        "procedure": null,
        "productname": ["class"],
        "productnumber": null,
        "programlisting": synopsisinfo.concat(["width"]),
        "programlistingco": null,
        "prompt": moreinfo,
        "property": moreinfo,
        "pubdate": null,
        "publisher": null,
        "publishername": null,
        "pubsnumber": null,
        "qandadiv": null,
        "qandaentry": null,
        "qandaset": ["defaultlabel"],
        "question": null,
        "quote": null,
        "refclass": null,
        "refdescriptor": null,
        "refentry": ["status"],
        "refentryinfo": null,
        "refentrytitle": null,
        "reference": ["status", "label"],
        "referenceinfo": null,
        "refmeta": null,
        "refmiscinfo": ["class"],
        "refname": null,
        "refnamediv": null,
        "refpurpose": null,
        "refsect1": ["status"],
        "refsect1info": null,
        "refsect2": ["status"],
        "refsect2info": null,
        "refsect3": ["status"],
        "refsect3info": null,
        "refsection": ["status"],
        "refsectioninfo": null,
        "refsynopsisdiv": null,
        "refsynopsisdivinfo": null,
        "releaseinfo": null,
        "remark": null,
        "replaceable": ["class"],
        "returnvalue": null,
        "revdescription": null,
        "revhistory": null,
        "revision": null,
        "revnumber": null,
        "revremark": null,
        "row": htmlAttributes.concat(["rowsep", "valign"]),
        "sbr": null,
        "screen": synopsisinfo.concat(["width"]),
        "screenco": null,
        "screeninfo": null,
        "screenshot": null,
        "secondary": ["sortas"],
        "secondaryie": ["linkends"],
        "sect1": ["status", "renderas", "label"],
        "sect1info": null,
        "sect2": ["status", "renderas", "label"],
        "sect2info": null,
        "sect3": ["status", "renderas", "label"],
        "sect3info": null,
        "sect4": ["status", "renderas", "label"],
        "sect4info": [],
        "sect5": ["status", "renderas", "label"],
        "sect5info": null,
        "section": ["status", "label"],
        "sectioninfo": null,
        "see": null,
        "seealso": null,
        "seealsoie": ["linkends"],
        "seeie": ["linkend"],
        "seg": null,
        "seglistitem": null,
        "segmentedlist": null,
        "segtitle": null,
        "seriesvolnums": null,
        "set": ["status", "fpi"],
        "setindex": null,
        "setindexinfo": null,
        "setinfo": ["contents"],
        "sgmltag": ["namespace", "class"],
        "shortaffil": null,
        "shortcut": ["otheraction", "moreinfo", "action"],
        "sidebar": null,
        "sidebarinfo": null,
        "simpara": null,
        "simplelist": ["columns", "type"],
        "simplemsgentry": ["audience", "origin", "level"],
        "simplesect": null,
        "spanspec": ["rowsep", "align", "spanname", "namest", "char", "nameend", "colsep", "charoff"],
        "state": null,
        "step": ["performance"],
        "stepalternatives": ["performance"],
        "street": null,
        "structfield": null,
        "structname": null,
        "subject": ["weight"],
        "subjectset": ["scheme"],
        "subjectterm": null,
        "subscript": null,
        "substeps": ["performance"],
        "subtitle": null,
        "superscript": null,
        "surname": null,
        "symbol": ["class"],
        "synopfragment": null,
        "synopfragmentref": ["linkend"],
        "synopsis": synopsisinfo.concat(["label"]),
        "systemitem": ["moreinfo", "class"],
        "table": htmlAttributes.concat(["align", "rowsep", "floatstyle", "pgwide", "orient", "label", "width", "frame", "rules", "summary", "bgcolor", "tabstyle", "cellspacing", "colsep", "shortentry", "border", "rowheader", "cellpadding", "tocentry"]),
        "task": null,
        "taskprerequisites": null,
        "taskrelated": null,
        "tasksummary": null,
        "tbody": htmlAttributes.concat(["valign"]),
        "td": htmlAttributes.concat(["axis", "abbr", "scope", "align", "valign", "width", "headers", "nowrap", "bgcolor", "colspan", "rowspan", "charoff", "height", "char"]),
        "term": null,
        "termdef": null,
        "tertiary": ["sortas"],
        "tertiaryie": ["linkends"],
        "textdata": ["srccredit", "entityref", "encoding", "fileref", "format"],
        "textobject": null,
        "tfoot": htmlAttributes.concat(["valign"]),
        "tgroup": htmlAttributes.concat(["rowsep", "cols", "align", "charoff", "colsep", "char"]),
        "th": htmlAttributes.concat(["axis", "abbr", "scope", "align", "valign", "width", "headers", "nowrap", "bgcolor", "colspan", "rowspan", "charoff", "height", "char"]),
        "thead": htmlAttributes.concat(["valign"]),
        "tip": null,
        "title": ["pagenum"],
        "titleabbrev": null,
        "toc": ["pagenum"],
        "tocback": ["pagenum", "label", "linkend"],
        "tocchap": ["label"],
        "tocentry": ["pagenum", "linkend"],
        "tocfront": ["pagenum", "label", "linkend"],
        "toclevel1": null,
        "toclevel2": null,
        "toclevel3": null,
        "toclevel4": null,
        "toclevel5": null,
        "tocpart": null,
        "token": null,
        "tr": htmlAttributes.concat(["align", "valign", "bgcolor", "charoff", "char"]),
        "trademark": ["class"],
        "type": null,
        "ulink": ["xrefstyle", "url", "type"],
        "uri": ["type"],
        "userinput": moreinfo,
        "varargs": null,
        "variablelist": ["termlength", "spacing"],
        "varlistentry": null,
        "varname": null,
        "videodata": graphicData,
        "videoobject": null,
        "void": null,
        "volumenum": null,
        "warning": null,
        "wordasword": null,
        "xref": ["xrefstyle", "endterm", "linkend"],
        "year": null
    };

    var allElements = Object.keys(attributeMap);

    function hasType(token, type) {
        var tokenTypes = token.type.split('.');
        return type.split('.').every(function(type){
            return (tokenTypes.indexOf(type) !== -1);
        });
    }

    function findTagName(session, pos) {
        var iterator = new TokenIterator(session, pos.row, pos.column);
        var token = iterator.getCurrentToken();
        if (!token || !hasType(token, 'tag') && !(hasType(token, 'text') && token.value.match('/'))){
            do {
                token = iterator.stepBackward();
            } while (token && (hasType(token, 'string') || hasType(token, 'operator') || hasType(token, 'attribute-name') || hasType(token, 'text')));
        }
        if (token && hasType(token, 'tag.name') && !iterator.stepBackward().value.match('/'))
            return token.value;
    }

    var DocBookCompletions = function() {

    };

    (function() {

        this.getCompletions = function(state, session, pos, prefix) {
            var token = session.getTokenAt(pos.row, pos.column);

            if (!token)
                return [];

            // tag name
            if (hasType(token, "tag.name") || (token.value == '<' && hasType(token, "text")))
                return this.getTagCompletions(state, session, pos, prefix);

            // tag attribute
            if (hasType(token, 'text') || hasType(token, 'attribute-name'))
                return this.getAttributeCompletions(state, session, pos, prefix);

            return [];
        };

        this.getTagCompletions = function(state, session, pos, prefix) {
            var elements = allElements;
            if (prefix) {
                elements = elements.filter(function(element){
                    return element.indexOf(prefix) === 0;
                });
            }
            return elements.map(function(element){
                return {
                    value: element,
                    meta: "tag"
                };
            });
        };

        this.getAttributeCompletions = function(state, session, pos, prefix) {
            var tagName = findTagName(session, pos);
            if (!tagName)
                return [];
            var attributes = globalAttributes;
            if (tagName in attributeMap) {
                var tagAttributes = attributeMap[tagName];
                if (tagAttributes) {
                    attributes = attributes.concat(tagAttributes);
                }
            }
            if (prefix) {
                attributes = attributes.filter(function(attribute){
                    return attribute.indexOf(prefix) === 0;
                });
            }
            return attributes.map(function(attribute){
                return {
                    caption: attribute,
                    snippet: attribute + '="$0"',
                    meta: "attribute"
                };
            });
        };

    }).call(DocBookCompletions.prototype);

    exports.DocBookCompletions = DocBookCompletions;
});
