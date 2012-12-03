package org.jboss.pressgang.ccms.ui.client.local.resources.xsl;

public final class DocbookToHTML {
    public static final String XSL = "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" + 
            "    <xsl:output method=\"html\" encoding=\"UTF-8\" indent=\"yes\" />\n" + 
            "    <xsl:strip-space elements=\"*\" />\n" + 
            "    <xsl:preserve-space elements=\"screen programlisting\" />\n" + 
            "    <xsl:param name=\"ulink.target\" />\n" + 
            "    \n" + 
            "    <!-- If true, img elements will be output with external urls. If false, a standard placeholder will be used -->\n" + 
            "    <xsl:param name=\"externalImages\" select=\"'false'\"/>\n" + 
            "    \n" + 
            "    <!-- The start of the url that is used to get the images from the server -->\n" + 
            "    <xsl:param name=\"externalImagesUrlPrefix\" select=\"'http://skynet.usersys.redhat.com:8080/TopicIndex/seam/resource/rest/1/image/get/raw/'\"/>\n" + 
            "    \n" +
            "    <!-- The end of the url that is used to get the images from the server -->\n" + 
            "    <xsl:param name=\"externalImagesUrlSuffix\" select=\"''\"/>\n" + 
            "    \n" + 
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
            "           <h5 class=\"formalParaTitle\">\n" + 
            "               <xsl:value-of select=\"title\" />\n" + 
            "           </h5>                \n" + 
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
            "        <div class=\"docbookNote\">\n" + 
            "            <h2 class=\"label docbookNoteTitle\">\n" + 
            "                <xsl:value-of select=\"title\" />\n" + 
            "            </h2>\n" + 
            "            <div class=\"docbookNoteContents\">\n" + 
            "               <xsl:apply-templates />\n" + 
            "            </div>\n" + 
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
            "       <div id=\"{@id}\" class=\"docbookFigure\">\n" + 
            "           <h6 class=\"docbookFigureTitle\">\n" + 
            "               <xsl:value-of select=\"title\" />\n" + 
            "           </h6>                \n" + 
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
            "       <xsl:choose>\n" + 
            "           <xsl:when test=\"$externalImages='true'\">\n" + 
            "               <img src=\"{$externalImagesUrlPrefix}{$imageidNoGif}{$externalImagesUrlSuffix}\"/>\n" + 
            "           </xsl:when>\n" + 
            "           <xsl:otherwise>\n" + 
            "               <!-- http://www.iconspedia.com/icon/image-12570.html - free license -->\n" + 
            "               <img src=\"data:;base64,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\"/>\n" + 
            "           </xsl:otherwise>\n" + 
            "       </xsl:choose>\n" + 
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
            "    <xsl:template match=\"table\">\n" + 
            "       <xsl:variable name=\"borderTop\">\n" + 
            "           <xsl:if test=\"@frame='all' or @frame='top' or @frame='topbot'\">\n" + 
            "               border-top-style:solid;\n" + 
            "           </xsl:if>\n" + 
            "       </xsl:variable>\n" + 
            "       \n" + 
            "       <xsl:variable name=\"borderBottom\">\n" + 
            "           <xsl:if test=\"@frame='all' or @frame='bottom' or @frame='topbot'\">\n" + 
            "               border-bottom-style:solid;\n" + 
            "           </xsl:if>\n" + 
            "       </xsl:variable>\n" + 
            "       \n" + 
            "       <xsl:variable name=\"borderSides\">\n" + 
            "           <xsl:if test=\"@frame='all' or @frame='sides'\">\n" + 
            "               border-left-style:solid; border-right-style:solid;\n" + 
            "           </xsl:if>\n" + 
            "       </xsl:variable>\n" + 
            "           \n" + 
            "       <table class=\"docbookTable\" style=\"{$borderTop} {$borderBottom} {$borderSides}\">\n" + 
            "           <xsl:apply-templates />\n" + 
            "       </table>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"tgroup\">\n" + 
            "       <xsl:apply-templates />\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"thead\">\n" + 
            "       <thead>\n" + 
            "           <xsl:apply-templates />\n" + 
            "       </thead>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"tfoot\">\n" + 
            "       <tfoot>\n" + 
            "           <xsl:apply-templates />\n" + 
            "       </tfoot>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"tbody\">\n" + 
            "       <tbody>\n" + 
            "           <xsl:apply-templates />\n" + 
            "       </tbody>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"row\">\n" + 
            "       <tr>\n" + 
            "           <xsl:apply-templates />\n" + 
            "       </tr>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"entry\">\n" + 
            "       <td>\n" + 
            "           <xsl:apply-templates />\n" + 
            "       </td>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"thead/row/entry\">\n" + 
            "       <th>\n" + 
            "           <xsl:apply-templates />\n" + 
            "       </th>\n" + 
            "    </xsl:template>\n" + 
            "    \n" + 
            "    <xsl:template match=\"keycap | mousebutton | keysym\">\n" + 
            "        <xsl:apply-templates />\n" + 
            "       <xsl:if test=\"following-sibling::node()[1][self::keycap] or following-sibling::node()[1][self::mousebutton] or following-sibling::node()[1][self::keysym]\">\n" + 
            "           <xsl:text>-</xsl:text>\n" + 
            "       </xsl:if>\n" + 
            "   </xsl:template>\n" + 
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
            "    <xsl:template match=\"guibutton\">\n" + 
            "       <span class=\"docbookGuiButton\">\n" + 
            "           <xsl:apply-templates />\n" + 
            "       </span>\n" + 
            "    </xsl:template>\n" +
            "</xsl:stylesheet>\n" + 
            "\n";

    private DocbookToHTML() {

    }
}
