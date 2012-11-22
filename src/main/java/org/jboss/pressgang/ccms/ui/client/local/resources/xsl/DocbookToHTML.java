package org.jboss.pressgang.ccms.ui.client.local.resources.xsl;

public final class DocbookToHTML {
    public static final String XSL = "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" + 
            "    <xsl:output method=\"html\" encoding=\"UTF-8\" indent=\"yes\" />\n" + 
            "    <xsl:strip-space elements=\"*\" />\n" + 
            "    <xsl:preserve-space elements=\"screen programlisting\" />\n" + 
            "    <xsl:param name=\"ulink.target\" />\n" + 
            "    <xsl:template match=\"/\">\n" + 
            "        <xsl:apply-templates select=\"section\" />\n" + 
            "    </xsl:template>\n" + 
            "    <!--<xsl:template match=\"article\"> <div id=\"{@id}\" class=\"docbookArticle\"> <h1 class=\"title\"> <xsl:value-of select=\"title\" /> </h1> <xsl:apply-templates select=\"section\"/> </div> </xsl:template> -->\n" + 
            "    <xsl:template match=\"section\">\n" + 
            "        <xsl:variable name=\"secdepth\">\n" + 
            "            <xsl:value-of select=\"count(ancestor::*)\" />\n" + 
            "        </xsl:variable>\n" + 
            "        <div id=\"{@id}\" class=\"section\">\n" + 
            "            <xsl:choose>\n" + 
            "                <xsl:when test=\"$secdepth = 1\">\n" + 
            "                    <h2 class=\"docbookSectionTitle\">\n" + 
            "                        <xsl:value-of select=\"title\" />\n" + 
            "                    </h2>\n" + 
            "                </xsl:when>\n" + 
            "                <xsl:when test=\"$secdepth = 2\">\n" + 
            "                    <h3 class=\"docbookSectionTitle\">\n" + 
            "                        <xsl:value-of select=\"title\" />\n" + 
            "                    </h3>\n" + 
            "                </xsl:when>\n" + 
            "                <xsl:when test=\"$secdepth = 3\">\n" + 
            "                    <h4 class=\"docbookSectionTitle\">\n" + 
            "                        <xsl:value-of select=\"title\" />\n" + 
            "                    </h4>\n" + 
            "                </xsl:when>\n" + 
            "                <xsl:when test=\"$secdepth = 4\">\n" + 
            "                    <h5 class=\"docbookSectionTitle\">\n" + 
            "                        <xsl:value-of select=\"title\" />\n" + 
            "                    </h5>\n" + 
            "                </xsl:when>\n" + 
            "                <!-- Following should never execute. Here as a fail safe. -->\n" + 
            "                <xsl:otherwise>\n" + 
            "                    <h2 class=\"docbookSectionTitle\">\n" + 
            "                        <xsl:value-of select=\"title\" />\n" + 
            "                    </h2>\n" + 
            "                </xsl:otherwise>\n" + 
            "            </xsl:choose>\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </div>\n" + 
            "    </xsl:template>\n" + 
            "   <xsl:template match=\"formalpara\">\n" + 
            "        <div id=\"{@id}\" class=\"formalpara\">\n" + 
            "           <h3 class=\"formalParaTitle\">\n" + 
            "               <xsl:value-of select=\"title\" />\n" + 
            "           </h3>                \n" + 
            "           <xsl:apply-templates />\n" + 
            "        </div>\n" + 
            "    </xsl:template>\n" + 
            "    <!-- Bad hack. Need to remove later. -->\n" + 
            "    <xsl:template match=\"title\"></xsl:template>\n" + 
            "    <xsl:template match=\"para\">\n" + 
            "        <p class=\"docbookPara\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </p>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"remark\">\n" + 
            "        <p class=\"docbookRemark\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </p>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"note\">\n" + 
            "        <div class=\"note\">\n" + 
            "            <h2 class=\"label docbookNoteTitle\">\n" + 
            "                <xsl:value-of select=\"title\" />\n" + 
            "            </h2>\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </div>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"important\">\n" + 
            "        <div class=\"important docbookImportant\">\n" + 
            "            <h2 class=\"label docbookImportantTitle\">\n" + 
            "                <xsl:value-of select=\"title\" />\n" + 
            "            </h2>\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </div>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"warning\">\n" + 
            "        <div class=\"warning docbookWarning\">\n" + 
            "            <h2 class=\"label docbookWarningTitle\">\n" + 
            "                <xsl:value-of select=\"title\" />\n" + 
            "            </h2>\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </div>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"itemizedlist\">\n" + 
            "        <div class=\"docbookItemizedList\">\n" + 
            "            <p class=\"itemizedlistitle docbookItemizedListTitle\">\n" + 
            "                <xsl:value-of select=\"title\" />\n" + 
            "            </p>\n" + 
            "            <ul class=\"docbookItemizedListContainer itemizedlist\">\n" + 
            "                <xsl:apply-templates />\n" + 
            "            </ul>\n" + 
            "        </div>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"listitem\">\n" + 
            "        <li class=\"docbookListItem\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </li>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"procedure\">\n" + 
            "        <div class=\"docbookProcedure\">\n" + 
            "            <p class=\"procedurelistitle docbookProcedureTitle\">\n" + 
            "                <xsl:value-of select=\"title\" />\n" + 
            "            </p>\n" + 
            "            <ol class=\"docbookProcedureContainer procedure\">\n" + 
            "                <xsl:apply-templates />\n" + 
            "            </ol>\n" + 
            "        </div>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"step\">\n" + 
            "        <li class=\"docbookStep\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </li>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"screen\">\n" + 
            "        <pre class=\"docbookScreen screen\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </pre>\n" + 
            "    </xsl:template>\n" + 
            "    <!-- jwulf 5 July 2012 -->\n" + 
            "    <xsl:template match=\"programlisting\">\n" + 
            "        <pre class=\"docbookProgramlisting programlisting\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </pre>\n" + 
            "    </xsl:template>\n" + 
            "    <!-- ********************** -->\n" + 
            "    <!-- Inline tags below this -->\n" + 
            "    <!-- ********************** -->\n" + 
            "    <xsl:template match=\"sgmltag\">\n" + 
            "        <span class=\"docbookSGMLTag sgmltag-{@class}\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </span>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"emphasis\">\n" + 
            "        <span class=\"docbookEmphasis emphasis\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </span>\n" + 
            "    </xsl:template>\n" + 
            "    <!-- jwulf 5 July 2012 -->\n" + 
            "    <xsl:template match=\"firstterm\">\n" + 
            "        <span class=\"docbookFirstTerm firstterm\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </span>\n" + 
            "    </xsl:template>\n" + 
            "    <!-- jwulf 5 July 2012 -->\n" + 
            "    <xsl:template match=\"literal\">\n" + 
            "        <span class=\"docbookLiteral literal\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </span>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"filename\">\n" + 
            "        <code class=\"docbookFileName filename\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"classname\">\n" + 
            "        <code class=\"docbookClassName classname\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"constant\">\n" + 
            "        <code class=\"docbookConstant constant\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"function\">\n" + 
            "        <code title=\"docbookFunction\" class=\"function\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"parameter\">\n" + 
            "        <code class=\"docbookParameter parameter\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"replaceable\">\n" + 
            "        <code class=\"docbookReplaceable replaceable\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"varname\">\n" + 
            "        <code class=\"docbookVarname varname\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"structfield\">\n" + 
            "        <code class=\"docbookStructfield structfield\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"systemitem\">\n" + 
            "        <code title=\"docbookSystemItem\" class=\"systemitem\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"package\">\n" + 
            "        <span title=\"docbookPackage\" class=\"package\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </span>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"command\">\n" + 
            "        <span class=\"docbookCommand command\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </span>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"option\">\n" + 
            "        <span class=\"docbookOption option\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </span>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"userinput\">\n" + 
            "        <code title=\"docbookUserInput\" class=\"userinput\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"computeroutput\">\n" + 
            "        <code title=\"docbookComputerOutput\" class=\"computeroutput\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"prompt\">\n" + 
            "        <code title=\"docbookPrompt\" class=\"prompt\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </code>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"subscript\">\n" + 
            "        <sub title=\"docbookSubscript\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </sub>\n" + 
            "    </xsl:template>\n" + 
            "    <xsl:template match=\"superscript\">\n" + 
            "        <sup title=\"docbookSuperscript\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </sup>\n" + 
            "    </xsl:template>\n" + 
            "    <!-- jwulf 5 July 2012 -->\n" + 
            "    <xsl:template match=\"code\">\n" + 
            "        <span class=\"docbookCode docbookCode\">\n" + 
            "            <xsl:apply-templates />\n" + 
            "        </span>\n" + 
            "    </xsl:template>\n" + 
            "    <!-- jwulf 5 July 2012 copied from publican xhtml-common -->\n" + 
            "    <xsl:template match=\"ulink\" name=\"ulink\">\n" + 
            "        <xsl:param name=\"url\" select=\"@url\" />\n" + 
            "        <xsl:variable name=\"link\">\n" + 
            "            <a xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
            "                <xsl:if test=\"@id or @xml:id\">\n" + 
            "                    <xsl:attribute name=\"id\">\n" + 
            "                        <xsl:value-of select=\"(@id|@xml:id)[1]\" />\n" + 
            "                    </xsl:attribute>\n" + 
            "                </xsl:if>\n" + 
            "                <xsl:attribute name=\"href\">\n" + 
            "                    <xsl:value-of select=\"$url\" />\n" + 
            "                </xsl:attribute>\n" + 
            "                <xsl:if test=\"$ulink.target != ''\">\n" + 
            "                    <xsl:attribute name=\"target\">\n" + 
            "                        <xsl:value-of select=\"$ulink.target\" />\n" + 
            "                    </xsl:attribute>\n" + 
            "                </xsl:if>\n" + 
            "                <xsl:if test=\"@role\">\n" + 
            "                    <xsl:apply-templates select=\".\" mode=\"class.attribute\">\n" + 
            "                        <xsl:with-param name=\"class\" select=\"@role\" />\n" + 
            "                    </xsl:apply-templates>\n" + 
            "                </xsl:if>\n" + 
            "                <xsl:choose>\n" + 
            "                    <xsl:when test=\"count(child::node())=0\">\n" + 
            "                        <xsl:value-of select=\"$url\" />\n" + 
            "                    </xsl:when>\n" + 
            "                    <xsl:otherwise>\n" + 
            "                        <xsl:apply-templates />\n" + 
            "                    </xsl:otherwise>\n" + 
            "                </xsl:choose>\n" + 
            "            </a>\n" + 
            "        </xsl:variable>\n" + 
            "        <xsl:copy-of select=\"$link\" />\n" + 
            "    </xsl:template>\n" + 
            "    <!-- ******************** -->\n" + 
            "    <!-- Meta tags below this -->\n" + 
            "    <!-- ******************** -->\n" + 
            "    <!-- Do nothing for now -->\n" + 
            "    <xsl:template match=\"indexterm\"></xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"figure\">\n" + 
            "       <div id=\"{@id}\" class=\"formalpara\">\n" + 
            "           <h2 class=\"figureTitle\">\n" + 
            "               <xsl:value-of select=\"title\" />\n" + 
            "           </h2>                \n" + 
            "           <xsl:apply-templates />\n" + 
            "        </div>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"mediaobject\">\n" + 
            "       <div id=\"{@id}\" class=\"formalpara\">                       \n" + 
            "           <xsl:apply-templates />\n" + 
            "        </div>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"imageobject\">\n" + 
            "       <xsl:apply-templates />\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"imagedata\">\n" + 
            "       <xsl:variable name=\"imageid\">\n" + 
            "           <xsl:call-template name=\"string-replace-all\">\n" + 
            "               <xsl:with-param name=\"text\" select=\"@fileref\"/>\n" + 
            "               <xsl:with-param name=\"replace\" select=\"'images/'\"/>\n" + 
            "               <xsl:with-param name=\"by\" select=\"''\"/>\n" + 
            "           </xsl:call-template>\n" + 
            "       </xsl:variable>\n" + 
            "       <xsl:variable name=\"imageidNoPng\">\n" + 
            "           <xsl:call-template name=\"string-replace-all\">\n" + 
            "               <xsl:with-param name=\"text\" select=\"$imageid\"/>\n" + 
            "               <xsl:with-param name=\"replace\" select=\"'.png'\"/>\n" + 
            "               <xsl:with-param name=\"by\" select=\"''\"/>\n" + 
            "           </xsl:call-template>\n" + 
            "       </xsl:variable>\n" + 
            "       <xsl:variable name=\"imageidNoSvg\">\n" + 
            "           <xsl:call-template name=\"string-replace-all\">\n" + 
            "               <xsl:with-param name=\"text\" select=\"$imageidNoPng\"/>\n" + 
            "               <xsl:with-param name=\"replace\" select=\"'.svg'\"/>\n" + 
            "               <xsl:with-param name=\"by\" select=\"''\"/>\n" + 
            "           </xsl:call-template>\n" + 
            "       </xsl:variable>\n" + 
            "       <xsl:variable name=\"imageidNoJpg\">\n" + 
            "           <xsl:call-template name=\"string-replace-all\">\n" + 
            "               <xsl:with-param name=\"text\" select=\"$imageidNoSvg\"/>\n" + 
            "               <xsl:with-param name=\"replace\" select=\"'.jpg'\"/>\n" + 
            "               <xsl:with-param name=\"by\" select=\"''\"/>\n" + 
            "           </xsl:call-template>\n" + 
            "       </xsl:variable>\n" + 
            "       <xsl:variable name=\"imageidNoGif\">\n" + 
            "           <xsl:call-template name=\"string-replace-all\">\n" + 
            "               <xsl:with-param name=\"text\" select=\"$imageidNoJpg\"/>\n" + 
            "               <xsl:with-param name=\"replace\" select=\"'.gif'\"/>\n" + 
            "               <xsl:with-param name=\"by\" select=\"''\"/>\n" + 
            "           </xsl:call-template>\n" + 
            "       </xsl:variable>\n" + 
            "       <img src=\"https://skynet.usersys.redhat.com:8443/TopicIndex/ImageFileDisplay.seam?imageFileId={$imageidNoGif}\"/>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"textobject\">\n" + 
            "       <div>\n" + 
            "           <xsl:apply-templates />\n" + 
            "       </div>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"phrase\">\n" + 
            "       <xsl:apply-templates />\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "  <xsl:template name=\"string-replace-all\">\n" + 
            "     <xsl:param name=\"text\" />\n" + 
            "     <xsl:param name=\"replace\" />\n" + 
            "     <xsl:param name=\"by\" />\n" + 
            "     <xsl:choose>\n" + 
            "       <xsl:when test=\"contains($text, $replace)\">\n" + 
            "         <xsl:value-of select=\"substring-before($text,$replace)\" />\n" + 
            "         <xsl:value-of select=\"$by\" />\n" + 
            "         <xsl:call-template name=\"string-replace-all\">\n" + 
            "           <xsl:with-param name=\"text\"\n" + 
            "           select=\"substring-after($text,$replace)\" />\n" + 
            "           <xsl:with-param name=\"replace\" select=\"$replace\" />\n" + 
            "           <xsl:with-param name=\"by\" select=\"$by\" />\n" + 
            "         </xsl:call-template>\n" + 
            "       </xsl:when>\n" + 
            "       <xsl:otherwise>\n" + 
            "         <xsl:value-of select=\"$text\" />\n" + 
            "       </xsl:otherwise>\n" + 
            "     </xsl:choose>\n" + 
            "   </xsl:template>\n" + 
            "</xsl:stylesheet>\n" + 
            "\n" ;

    private DocbookToHTML() {

    }
}
