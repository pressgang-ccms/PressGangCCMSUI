package org.jboss.pressgang.ccms.ui.client.local.constants;

/**
 * Created with IntelliJ IDEA.
 * User: matthew
 * Date: 17/06/13
 * Time: 10:28 AM
 * To change this template use File | Settings | File Templates.
 */
public final class DTDConstants {
    public static final String DOCBOOK_45_DTD = "<!-- ...................................................................... -->\n" + 
"<!-- DocBook XML DTD V4.5 ................................................. -->\n" + 
"<!-- File docbookx.dtd .................................................... -->\n" + 
"\n" + 
"<!-- Copyright 1992-2006 HaL Computer Systems, Inc.,\n" + 
"     O'Reilly & Associates, Inc., ArborText, Inc., Fujitsu Software\n" + 
"     Corporation, Norman Walsh, Sun Microsystems, Inc., and the\n" + 
"     Organization for the Advancement of Structured Information\n" + 
"     Standards (OASIS).\n" + 
"\n" + 
"     See also http://docbook.org/specs/\n" + 
"\n" + 
"     $Id: docbookx.dtd 6340 2006-10-03 13:23:24Z nwalsh $\n" + 
"\n" + 
"     Permission to use, copy, modify and distribute the DocBook XML DTD\n" + 
"     and its accompanying documentation for any purpose and without fee\n" + 
"     is hereby granted in perpetuity, provided that the above copyright\n" + 
"     notice and this paragraph appear in all copies.  The copyright\n" + 
"     holders make no representation about the suitability of the DTD for\n" + 
"     any purpose.  It is provided \"as is\" without expressed or implied\n" + 
"     warranty.\n" + 
"\n" + 
"     If you modify the DocBook DTD in any way, except for declaring and\n" + 
"     referencing additional sets of general entities and declaring\n" + 
"     additional notations, label your DTD as a variant of DocBook.  See\n" + 
"     the maintenance documentation for more information.\n" + 
"\n" + 
"     Please direct all questions, bug reports, or suggestions for\n" + 
"     changes to the docbook@lists.oasis-open.org mailing list. For more\n" + 
"     information, see http://www.oasis-open.org/docbook/.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- This is the driver file for V4.5 of the DocBook DTD.\n" + 
"     Please use the following formal public identifier to identify it:\n" + 
"\n" + 
"     \"-//OASIS//DTD DocBook XML V4.5//EN\"\n" + 
"\n" + 
"     For example, if your document's top-level element is Book, and\n" + 
"     you are using DocBook directly, use the FPI in the DOCTYPE\n" + 
"     declaration:\n" + 
"\n" + 
"     <!DOCTYPE book PUBLIC \"-//OASIS//DTD DocBook XML V4.5//EN\"\n" + 
"                    \"http://www.oasis-open.org/docbook/xml/4.5/docbookx.dtd\"\n" + 
"                    [...]>\n" + 
"\n" + 
"     Or, if you have a higher-level driver file that customizes DocBook,\n" + 
"     use the FPI in the parameter entity declaration:\n" + 
"\n" + 
"     <!ENTITY % DocBookDTD PUBLIC \"-//OASIS//DTD DocBook XML V4.5//EN\"\n" + 
"                \"http://www.oasis-open.org/docbook/xml/4.5/docbookx.dtd\">\n" + 
"     %DocBookDTD;\n" + 
"\n" + 
"     See the documentation for detailed information on the parameter\n" + 
"     entity and module scheme used in DocBook, customizing DocBook and\n" + 
"     planning for interchange, and changes made since the last release\n" + 
"     of DocBook.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Enable SGML features ................................................. -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Notation declarations ................................................ -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- DocBook notations module V4.5 ........................................ -->\n" + 
"<!-- File dbnotnx.mod ..................................................... -->\n" + 
"\n" + 
"<!-- Copyright 1992-2004 HaL Computer Systems, Inc.,\n" + 
"     O'Reilly & Associates, Inc., ArborText, Inc., Fujitsu Software\n" + 
"     Corporation, Norman Walsh, Sun Microsystems, Inc., and the\n" + 
"     Organization for the Advancement of Structured Information\n" + 
"     Standards (OASIS).\n" + 
"\n" + 
"     $Id: dbnotnx.mod 6340 2006-10-03 13:23:24Z nwalsh $\n" + 
"\n" + 
"     Permission to use, copy, modify and distribute the DocBook DTD\n" + 
"     and its accompanying documentation for any purpose and without fee\n" + 
"     is hereby granted in perpetuity, provided that the above copyright\n" + 
"     notice and this paragraph appear in all copies.  The copyright\n" + 
"     holders make no representation about the suitability of the DTD for\n" + 
"     any purpose.  It is provided \"as is\" without expressed or implied\n" + 
"     warranty.\n" + 
"\n" + 
"     If you modify the DocBook DTD in any way, except for declaring and\n" + 
"     referencing additional sets of general entities and declaring\n" + 
"     additional notations, label your DTD as a variant of DocBook.  See\n" + 
"     the maintenance documentation for more information.\n" + 
"\n" + 
"     Please direct all questions, bug reports, or suggestions for\n" + 
"     changes to the docbook@lists.oasis-open.org mailing list. For more\n" + 
"     information, see http://www.oasis-open.org/docbook/.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- This module contains the notation declarations used by DocBook.\n" + 
"\n" + 
"     In DTD driver files referring to this module, please use an entity\n" + 
"     declaration that uses the public identifier shown below:\n" + 
"\n" + 
"     <!ENTITY % dbnotn PUBLIC\n" + 
"     \"-//OASIS//ENTITIES DocBook Notations V4.5//EN\"\n" + 
"     \"dbnotnx.mod\">\n" + 
"     %dbnotn;\n" + 
"\n" + 
"     See the documentation for detailed information on the parameter\n" + 
"     entity and module scheme used in DocBook, customizing DocBook and\n" + 
"     planning for interchange, and changes made since the last release\n" + 
"     of DocBook.\n" + 
"-->\n" + 
"\n" + 
"<!NOTATION BMP		PUBLIC\n" + 
"\"+//ISBN 0-7923-94.2-1::Graphic Notation//NOTATION Microsoft Windows bitmap//EN\">\n" + 
"<!NOTATION CGM-CHAR	PUBLIC \"ISO 8632/2//NOTATION Character encoding//EN\">\n" + 
"<!NOTATION CGM-BINARY	PUBLIC \"ISO 8632/3//NOTATION Binary encoding//EN\">\n" + 
"<!NOTATION CGM-CLEAR	PUBLIC \"ISO 8632/4//NOTATION Clear text encoding//EN\">\n" + 
"<!NOTATION DITROFF	SYSTEM \"DITROFF\">\n" + 
"<!NOTATION DVI		SYSTEM \"DVI\">\n" + 
"<!NOTATION EPS		PUBLIC\n" + 
"\"+//ISBN 0-201-18127-4::Adobe//NOTATION PostScript Language Ref. Manual//EN\">\n" + 
"<!NOTATION EQN		SYSTEM \"EQN\">\n" + 
"<!NOTATION FAX		PUBLIC\n" + 
"\"-//USA-DOD//NOTATION CCITT Group 4 Facsimile Type 1 Untiled Raster//EN\">\n" + 
"<!NOTATION GIF		SYSTEM \"GIF\">\n" + 
"<!NOTATION GIF87a               PUBLIC\n" + 
"\"-//CompuServe//NOTATION Graphics Interchange Format 87a//EN\">\n" + 
"\n" + 
"<!NOTATION GIF89a               PUBLIC\n" + 
"\"-//CompuServe//NOTATION Graphics Interchange Format 89a//EN\">\n" + 
"<!NOTATION JPG		SYSTEM \"JPG\">\n" + 
"<!NOTATION JPEG		SYSTEM \"JPG\">\n" + 
"<!NOTATION IGES		PUBLIC\n" + 
"\"-//USA-DOD//NOTATION (ASME/ANSI Y14.26M-1987) Initial Graphics Exchange Specification//EN\">\n" + 
"<!NOTATION PCX		PUBLIC\n" + 
"\"+//ISBN 0-7923-94.2-1::Graphic Notation//NOTATION ZSoft PCX bitmap//EN\">\n" + 
"<!NOTATION PIC		SYSTEM \"PIC\">\n" + 
"<!NOTATION PNG          SYSTEM \"http://www.w3.org/TR/REC-png\">\n" + 
"<!NOTATION PS		SYSTEM \"PS\">\n" + 
"<!NOTATION SGML		PUBLIC\n" + 
"\"ISO 8879:1986//NOTATION Standard Generalized Markup Language//EN\">\n" + 
"<!NOTATION TBL		SYSTEM \"TBL\">\n" + 
"<!NOTATION TEX		PUBLIC\n" + 
"\"+//ISBN 0-201-13448-9::Knuth//NOTATION The TeXbook//EN\">\n" + 
"<!NOTATION TIFF		SYSTEM \"TIFF\">\n" + 
"<!NOTATION WMF		PUBLIC\n" + 
"\"+//ISBN 0-7923-94.2-1::Graphic Notation//NOTATION Microsoft Windows Metafile//EN\">\n" + 
"<!NOTATION WPG		SYSTEM \"WPG\"> <!--WordPerfect Graphic format-->\n" + 
"<!NOTATION SVG		SYSTEM \"http://www.w3.org/TR/SVG/\">\n" + 
"<!NOTATION PDF		SYSTEM \"http://www.adobe.com/products/acrobat/adobepdf.html\">\n" + 
"<!NOTATION SWF          SYSTEM \"http://www.macromedia.com/software/flash\">\n" + 
"<!NOTATION linespecific	SYSTEM \"linespecific\">\n" + 
"\n" + 
"<!-- End of DocBook notations module V4.5 ................................. -->\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- ISO character entity sets ............................................ -->\n" + 
"\n" + 
"<!ENTITY euro \"&#x20AC;\"><!-- euro sign, U+20AC NEW -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- DocBook character entities module V4.5 ............................... -->\n" + 
"<!-- File dbcentx.mod ..................................................... -->\n" + 
"\n" + 
"<!-- Copyright 1992-2004 HaL Computer Systems, Inc.,\n" + 
"     O'Reilly & Associates, Inc., ArborText, Inc., Fujitsu Software\n" + 
"     Corporation, Norman Walsh, Sun Microsystems, Inc., and the\n" + 
"     Organization for the Advancement of Structured Information\n" + 
"     Standards (OASIS).\n" + 
"\n" + 
"     $Id: dbcentx.mod 6340 2006-10-03 13:23:24Z nwalsh $\n" + 
"\n" + 
"     Permission to use, copy, modify and distribute the DocBook DTD\n" + 
"     and its accompanying documentation for any purpose and without fee\n" + 
"     is hereby granted in perpetuity, provided that the above copyright\n" + 
"     notice and this paragraph appear in all copies.  The copyright\n" + 
"     holders make no representation about the suitability of the DTD for\n" + 
"     any purpose.  It is provided \"as is\" without expressed or implied\n" + 
"     warranty.\n" + 
"\n" + 
"     If you modify the DocBook DTD in any way, except for declaring and\n" + 
"     referencing additional sets of general entities and declaring\n" + 
"     additional notations, label your DTD as a variant of DocBook.  See\n" + 
"     the maintenance documentation for more information.\n" + 
"\n" + 
"     Please direct all questions, bug reports, or suggestions for\n" + 
"     changes to the docbook@lists.oasis-open.org mailing list. For more\n" + 
"     information, see http://www.oasis-open.org/docbook/.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- This module contains the entity declarations for the standard ISO\n" + 
"     entity sets used by DocBook.\n" + 
"\n" + 
"     In DTD driver files referring to this module, please use an entity\n" + 
"     declaration that uses the public identifier shown below:\n" + 
"\n" + 
"     <!ENTITY % dbcent PUBLIC\n" + 
"     \"-//OASIS//ENTITIES DocBook Character Entities V4.5//EN\"\n" + 
"     \"dbcentx.mod\">\n" + 
"     %dbcent;\n" + 
"\n" + 
"     See the documentation for detailed information on the parameter\n" + 
"     entity and module scheme used in DocBook, customizing DocBook and\n" + 
"     planning for interchange, and changes made since the last release\n" + 
"     of DocBook.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!--end of ISOamsa.module-->\n" + 
"\n" + 
"<!--end of ISOamsb.module-->\n" + 
"\n" + 
"<!--end of ISOamsc.module-->\n" + 
"\n" + 
"<!--end of ISOamsn.module-->\n" + 
"\n" + 
"<!--end of ISOamso.module-->\n" + 
"\n" + 
"<!--end of ISOamsr.module-->\n" + 
"\n" + 
"<!--end of ISObox.module-->\n" + 
"\n" + 
"<!--end of ISOcyr1.module-->\n" + 
"\n" + 
"<!--end of ISOcyr2.module-->\n" + 
"\n" + 
"<!--end of ISOdia.module-->\n" + 
"\n" + 
"<!--end of ISOgrk1.module-->\n" + 
"\n" + 
"<!--end of ISOgrk2.module-->\n" + 
"\n" + 
"<!--end of ISOgrk3.module-->\n" + 
"\n" + 
"<!--end of ISOgrk4.module-->\n" + 
"\n" + 
"<!--end of ISOlat1.module-->\n" + 
"\n" + 
"<!--end of ISOlat2.module-->\n" + 
"\n" + 
"<!--end of ISOnum.module-->\n" + 
"\n" + 
"<!--end of ISOpub.module-->\n" + 
"\n" + 
"<!--end of ISOtech.module-->\n" + 
"\n" + 
"<!--end of xml.features-->\n" + 
"\n" + 
"<!--\n" + 
"     File isoamsa.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isoamsa.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Added Math Symbols: Arrow Relations//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isoamsa.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isoamsa PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Added Math Symbols: Arrow Relations//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isoamsa.ent\"\n" + 
"       >\n" + 
"       %isoamsa;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY cularr           \"&#x021B6;\" ><!--ANTICLOCKWISE TOP SEMICIRCLE ARROW -->\n" + 
"<!ENTITY curarr           \"&#x021B7;\" ><!--CLOCKWISE TOP SEMICIRCLE ARROW -->\n" + 
"<!ENTITY dArr             \"&#x021D3;\" ><!--DOWNWARDS DOUBLE ARROW -->\n" + 
"<!ENTITY darr2            \"&#x021CA;\" ><!--DOWNWARDS PAIRED ARROWS -->\n" + 
"<!ENTITY dharl            \"&#x021C3;\" ><!--DOWNWARDS HARPOON WITH BARB LEFTWARDS -->\n" + 
"<!ENTITY dharr            \"&#x021C2;\" ><!--DOWNWARDS HARPOON WITH BARB RIGHTWARDS -->\n" + 
"<!ENTITY dlarr            \"&#x02199;\" ><!--SOUTH WEST ARROW -->\n" + 
"<!ENTITY drarr            \"&#x02198;\" ><!--SOUTH EAST ARROW -->\n" + 
"<!ENTITY hArr             \"&#x021D4;\" ><!--LEFT RIGHT DOUBLE ARROW -->\n" + 
"<!ENTITY harr             \"&#x02194;\" ><!--LEFT RIGHT ARROW -->\n" + 
"<!ENTITY harrw            \"&#x021AD;\" ><!--LEFT RIGHT WAVE ARROW -->\n" + 
"<!ENTITY lAarr            \"&#x021DA;\" ><!--LEFTWARDS TRIPLE ARROW -->\n" + 
"<!ENTITY Larr             \"&#x0219E;\" ><!--LEFTWARDS TWO HEADED ARROW -->\n" + 
"<!ENTITY larr2            \"&#x021C7;\" ><!--LEFTWARDS PAIRED ARROWS -->\n" + 
"<!ENTITY larrhk           \"&#x021A9;\" ><!--LEFTWARDS ARROW WITH HOOK -->\n" + 
"<!ENTITY larrlp           \"&#x021AB;\" ><!--LEFTWARDS ARROW WITH LOOP -->\n" + 
"<!ENTITY larrtl           \"&#x021A2;\" ><!--LEFTWARDS ARROW WITH TAIL -->\n" + 
"<!ENTITY lhard            \"&#x021BD;\" ><!--LEFTWARDS HARPOON WITH BARB DOWNWARDS -->\n" + 
"<!ENTITY lharu            \"&#x021BC;\" ><!--LEFTWARDS HARPOON WITH BARB UPWARDS -->\n" + 
"<!ENTITY lrarr2           \"&#x021C6;\" ><!--LEFTWARDS ARROW OVER RIGHTWARDS ARROW -->\n" + 
"<!ENTITY lrhar2           \"&#x021CB;\" ><!--LEFTWARDS HARPOON OVER RIGHTWARDS HARPOON -->\n" + 
"<!ENTITY lsh              \"&#x021B0;\" ><!--UPWARDS ARROW WITH TIP LEFTWARDS -->\n" + 
"<!ENTITY map              \"&#x021A6;\" ><!--RIGHTWARDS ARROW FROM BAR -->\n" + 
"<!ENTITY mumap            \"&#x022B8;\" ><!--MULTIMAP -->\n" + 
"<!ENTITY nearr            \"&#x02197;\" ><!--NORTH EAST ARROW -->\n" + 
"<!ENTITY nhArr            \"&#x021CE;\" ><!--LEFT RIGHT DOUBLE ARROW WITH STROKE -->\n" + 
"<!ENTITY nharr            \"&#x021AE;\" ><!--LEFT RIGHT ARROW WITH STROKE -->\n" + 
"<!ENTITY nlArr            \"&#x021CD;\" ><!--LEFTWARDS DOUBLE ARROW WITH STROKE -->\n" + 
"<!ENTITY nlarr            \"&#x0219A;\" ><!--LEFTWARDS ARROW WITH STROKE -->\n" + 
"<!ENTITY nrArr            \"&#x021CF;\" ><!--RIGHTWARDS DOUBLE ARROW WITH STROKE -->\n" + 
"<!ENTITY nrarr            \"&#x0219B;\" ><!--RIGHTWARDS ARROW WITH STROKE -->\n" + 
"<!ENTITY nwarr            \"&#x02196;\" ><!--NORTH WEST ARROW -->\n" + 
"<!ENTITY olarr            \"&#x021BA;\" ><!--ANTICLOCKWISE OPEN CIRCLE ARROW -->\n" + 
"<!ENTITY orarr            \"&#x021BB;\" ><!--CLOCKWISE OPEN CIRCLE ARROW -->\n" + 
"<!ENTITY rAarr            \"&#x021DB;\" ><!--RIGHTWARDS TRIPLE ARROW -->\n" + 
"<!ENTITY Rarr             \"&#x021A0;\" ><!--RIGHTWARDS TWO HEADED ARROW -->\n" + 
"<!ENTITY rarr2            \"&#x021C9;\" ><!--RIGHTWARDS PAIRED ARROWS -->\n" + 
"<!ENTITY rarrhk           \"&#x021AA;\" ><!--RIGHTWARDS ARROW WITH HOOK -->\n" + 
"<!ENTITY rarrlp           \"&#x021AC;\" ><!--RIGHTWARDS ARROW WITH LOOP -->\n" + 
"<!ENTITY rarrtl           \"&#x021A3;\" ><!--RIGHTWARDS ARROW WITH TAIL -->\n" + 
"<!ENTITY rarrw            \"&#x0219D;\" ><!--RIGHTWARDS WAVE ARROW -->\n" + 
"<!ENTITY rhard            \"&#x021C1;\" ><!--RIGHTWARDS HARPOON WITH BARB DOWNWARDS -->\n" + 
"<!ENTITY rharu            \"&#x021C0;\" ><!--RIGHTWARDS HARPOON WITH BARB UPWARDS -->\n" + 
"<!ENTITY rlarr2           \"&#x021C4;\" ><!--RIGHTWARDS ARROW OVER LEFTWARDS ARROW -->\n" + 
"<!ENTITY rlhar2           \"&#x021CC;\" ><!--RIGHTWARDS HARPOON OVER LEFTWARDS HARPOON -->\n" + 
"<!ENTITY rsh              \"&#x021B1;\" ><!--UPWARDS ARROW WITH TIP RIGHTWARDS -->\n" + 
"<!ENTITY uArr             \"&#x021D1;\" ><!--UPWARDS DOUBLE ARROW -->\n" + 
"<!ENTITY uarr2            \"&#x021C8;\" ><!--UPWARDS PAIRED ARROWS -->\n" + 
"<!ENTITY uharl            \"&#x021BF;\" ><!--UPWARDS HARPOON WITH BARB LEFTWARDS -->\n" + 
"<!ENTITY uharr            \"&#x021BE;\" ><!--UPWARDS HARPOON WITH BARB RIGHTWARDS -->\n" + 
"<!ENTITY vArr             \"&#x021D5;\" ><!--UP DOWN DOUBLE ARROW -->\n" + 
"<!ENTITY varr             \"&#x02195;\" ><!--UP DOWN ARROW -->\n" + 
"<!ENTITY xhArr            \"&#x027FA;\" ><!--LONG LEFT RIGHT DOUBLE ARROW -->\n" + 
"<!ENTITY xharr            \"&#x027F7;\" ><!--LONG LEFT RIGHT ARROW -->\n" + 
"<!ENTITY xlArr            \"&#x027F8;\" ><!--LONG LEFTWARDS DOUBLE ARROW -->\n" + 
"<!ENTITY xrArr            \"&#x027F9;\" ><!--LONG RIGHTWARDS DOUBLE ARROW -->\n" + 
"\n" + 
"<!--\n" + 
"     File isoamsb.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isoamsb.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Added Math Symbols: Binary Operators//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isoamsb.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isoamsb PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Added Math Symbols: Binary Operators//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isoamsb.ent\"\n" + 
"       >\n" + 
"       %isoamsb;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY amalg            \"&#x02A3F;\" ><!--AMALGAMATION OR COPRODUCT -->\n" + 
"<!ENTITY Barwed           \"&#x02306;\" ><!--PERSPECTIVE -->\n" + 
"<!ENTITY barwed           \"&#x02305;\" ><!--PROJECTIVE -->\n" + 
"<!ENTITY Cap              \"&#x022D2;\" ><!--DOUBLE INTERSECTION -->\n" + 
"<!ENTITY coprod           \"&#x02210;\" ><!--N-ARY COPRODUCT -->\n" + 
"<!ENTITY Cup              \"&#x022D3;\" ><!--DOUBLE UNION -->\n" + 
"<!ENTITY cuvee            \"&#x022CE;\" ><!--CURLY LOGICAL OR -->\n" + 
"<!ENTITY cuwed            \"&#x022CF;\" ><!--CURLY LOGICAL AND -->\n" + 
"<!ENTITY diam             \"&#x022C4;\" ><!--DIAMOND OPERATOR -->\n" + 
"<!ENTITY divonx           \"&#x022C7;\" ><!--DIVISION TIMES -->\n" + 
"<!ENTITY intcal           \"&#x022BA;\" ><!--INTERCALATE -->\n" + 
"<!ENTITY lthree           \"&#x022CB;\" ><!--LEFT SEMIDIRECT PRODUCT -->\n" + 
"<!ENTITY ltimes           \"&#x022C9;\" ><!--LEFT NORMAL FACTOR SEMIDIRECT PRODUCT -->\n" + 
"<!ENTITY minusb           \"&#x0229F;\" ><!--SQUARED MINUS -->\n" + 
"<!ENTITY oast             \"&#x0229B;\" ><!--CIRCLED ASTERISK OPERATOR -->\n" + 
"<!ENTITY ocir             \"&#x0229A;\" ><!--CIRCLED RING OPERATOR -->\n" + 
"<!ENTITY odash            \"&#x0229D;\" ><!--CIRCLED DASH -->\n" + 
"<!ENTITY odot             \"&#x02299;\" ><!--CIRCLED DOT OPERATOR -->\n" + 
"<!ENTITY ominus           \"&#x02296;\" ><!--CIRCLED MINUS -->\n" + 
"<!ENTITY oplus            \"&#x02295;\" ><!--CIRCLED PLUS -->\n" + 
"<!ENTITY osol             \"&#x02298;\" ><!--CIRCLED DIVISION SLASH -->\n" + 
"<!ENTITY otimes           \"&#x02297;\" ><!--CIRCLED TIMES -->\n" + 
"<!ENTITY plusb            \"&#x0229E;\" ><!--SQUARED PLUS -->\n" + 
"<!ENTITY plusdo           \"&#x02214;\" ><!--DOT PLUS -->\n" + 
"<!ENTITY prod             \"&#x0220F;\" ><!--N-ARY PRODUCT -->\n" + 
"<!ENTITY rthree           \"&#x022CC;\" ><!--RIGHT SEMIDIRECT PRODUCT -->\n" + 
"<!ENTITY rtimes           \"&#x022CA;\" ><!--RIGHT NORMAL FACTOR SEMIDIRECT PRODUCT -->\n" + 
"<!ENTITY sdot             \"&#x022C5;\" ><!--DOT OPERATOR -->\n" + 
"<!ENTITY sdotb            \"&#x022A1;\" ><!--SQUARED DOT OPERATOR -->\n" + 
"<!ENTITY setmn            \"&#x02216;\" ><!--SET MINUS -->\n" + 
"<!ENTITY sqcap            \"&#x02293;\" ><!--SQUARE CAP -->\n" + 
"<!ENTITY sqcup            \"&#x02294;\" ><!--SQUARE CUP -->\n" + 
"<!ENTITY ssetmn           \"&#x02216;\" ><!--SET MINUS -->\n" + 
"<!ENTITY sstarf           \"&#x022C6;\" ><!--STAR OPERATOR -->\n" + 
"<!ENTITY sum              \"&#x02211;\" ><!--N-ARY SUMMATION -->\n" + 
"<!ENTITY timesb           \"&#x022A0;\" ><!--SQUARED TIMES -->\n" + 
"<!ENTITY top              \"&#x022A4;\" ><!--DOWN TACK -->\n" + 
"<!ENTITY uplus            \"&#x0228E;\" ><!--MULTISET UNION -->\n" + 
"<!ENTITY wreath           \"&#x02240;\" ><!--WREATH PRODUCT -->\n" + 
"<!ENTITY xcirc            \"&#x025EF;\" ><!--LARGE CIRCLE -->\n" + 
"<!ENTITY xdtri            \"&#x025BD;\" ><!--WHITE DOWN-POINTING TRIANGLE -->\n" + 
"<!ENTITY xutri            \"&#x025B3;\" ><!--WHITE UP-POINTING TRIANGLE -->\n" + 
"\n" + 
"<!--\n" + 
"     File isoamsc.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isoamsc.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Added Math Symbols: Delimiters//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isoamsc.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isoamsc PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Added Math Symbols: Delimiters//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isoamsc.ent\"\n" + 
"       >\n" + 
"       %isoamsc;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY dlcorn           \"&#x0231E;\" ><!--BOTTOM LEFT CORNER -->\n" + 
"<!ENTITY drcorn           \"&#x0231F;\" ><!--BOTTOM RIGHT CORNER -->\n" + 
"<!ENTITY lceil            \"&#x02308;\" ><!--LEFT CEILING -->\n" + 
"<!ENTITY lfloor           \"&#x0230A;\" ><!--LEFT FLOOR -->\n" + 
"<!ENTITY lpargt           \"&#x029A0;\" ><!--SPHERICAL ANGLE OPENING LEFT -->\n" + 
"<!ENTITY rceil            \"&#x02309;\" ><!--RIGHT CEILING -->\n" + 
"<!ENTITY rfloor           \"&#x0230B;\" ><!--RIGHT FLOOR -->\n" + 
"<!ENTITY rpargt           \"&#x02994;\" ><!--RIGHT ARC GREATER-THAN BRACKET -->\n" + 
"<!ENTITY ulcorn           \"&#x0231C;\" ><!--TOP LEFT CORNER -->\n" + 
"<!ENTITY urcorn           \"&#x0231D;\" ><!--TOP RIGHT CORNER -->\n" + 
"\n" + 
"<!--\n" + 
"     File isoamsn.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     References to the VARIANT SELECTOR 1 character (&#x0FE00;)\n" + 
"     should match the uses listed in Unicode Technical Report 25.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isoamsn.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Added Math Symbols: Negated Relations//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isoamsn.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isoamsn PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Added Math Symbols: Negated Relations//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isoamsn.ent\"\n" + 
"       >\n" + 
"       %isoamsn;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY gnap             \"&#x02A8A;\" ><!--GREATER-THAN AND NOT APPROXIMATE -->\n" + 
"<!ENTITY gnE              \"&#x02269;\" ><!--GREATER-THAN BUT NOT EQUAL TO -->\n" + 
"<!ENTITY gne              \"&#x02A88;\" ><!--GREATER-THAN AND SINGLE-LINE NOT EQUAL TO -->\n" + 
"<!ENTITY gnsim            \"&#x022E7;\" ><!--GREATER-THAN BUT NOT EQUIVALENT TO -->\n" + 
"<!ENTITY gvnE             \"&#x02269;&#x0FE00;\" ><!--GREATER-THAN BUT NOT EQUAL TO - with vertical stroke -->\n" + 
"<!ENTITY lnap             \"&#x02A89;\" ><!--LESS-THAN AND NOT APPROXIMATE -->\n" + 
"<!ENTITY lnE              \"&#x02268;\" ><!--LESS-THAN BUT NOT EQUAL TO -->\n" + 
"<!ENTITY lne              \"&#x02A87;\" ><!--LESS-THAN AND SINGLE-LINE NOT EQUAL TO -->\n" + 
"<!ENTITY lnsim            \"&#x022E6;\" ><!--LESS-THAN BUT NOT EQUIVALENT TO -->\n" + 
"<!ENTITY lvnE             \"&#x02268;&#x0FE00;\" ><!--LESS-THAN BUT NOT EQUAL TO - with vertical stroke -->\n" + 
"<!ENTITY nap              \"&#x02249;\" ><!--NOT ALMOST EQUAL TO -->\n" + 
"<!ENTITY ncong            \"&#x02247;\" ><!--NEITHER APPROXIMATELY NOR ACTUALLY EQUAL TO -->\n" + 
"<!ENTITY nequiv           \"&#x02262;\" ><!--NOT IDENTICAL TO -->\n" + 
"<!ENTITY ngE              \"&#x02267;&#x00338;\" ><!--GREATER-THAN OVER EQUAL TO with slash -->\n" + 
"<!ENTITY nge              \"&#x02271;\" ><!--NEITHER GREATER-THAN NOR EQUAL TO -->\n" + 
"<!ENTITY nges             \"&#x02A7E;&#x00338;\" ><!--GREATER-THAN OR SLANTED EQUAL TO with slash -->\n" + 
"<!ENTITY ngt              \"&#x0226F;\" ><!--NOT GREATER-THAN -->\n" + 
"<!ENTITY nlE              \"&#x02266;&#x00338;\" ><!--LESS-THAN OVER EQUAL TO with slash -->\n" + 
"<!ENTITY nle              \"&#x02270;\" ><!--NEITHER LESS-THAN NOR EQUAL TO -->\n" + 
"<!ENTITY nles             \"&#x02A7D;&#x00338;\" ><!--LESS-THAN OR SLANTED EQUAL TO with slash -->\n" + 
"<!ENTITY nlt              \"&#x0226E;\" ><!--NOT LESS-THAN -->\n" + 
"<!ENTITY nltri            \"&#x022EA;\" ><!--NOT NORMAL SUBGROUP OF -->\n" + 
"<!ENTITY nltrie           \"&#x022EC;\" ><!--NOT NORMAL SUBGROUP OF OR EQUAL TO -->\n" + 
"<!ENTITY nmid             \"&#x02224;\" ><!--DOES NOT DIVIDE -->\n" + 
"<!ENTITY npar             \"&#x02226;\" ><!--NOT PARALLEL TO -->\n" + 
"<!ENTITY npr              \"&#x02280;\" ><!--DOES NOT PRECEDE -->\n" + 
"<!ENTITY npre             \"&#x02AAF;&#x00338;\" ><!--PRECEDES ABOVE SINGLE-LINE EQUALS SIGN with slash -->\n" + 
"<!ENTITY nrtri            \"&#x022EB;\" ><!--DOES NOT CONTAIN AS NORMAL SUBGROUP -->\n" + 
"<!ENTITY nrtrie           \"&#x022ED;\" ><!--DOES NOT CONTAIN AS NORMAL SUBGROUP OR EQUAL -->\n" + 
"<!ENTITY nsc              \"&#x02281;\" ><!--DOES NOT SUCCEED -->\n" + 
"<!ENTITY nsce             \"&#x02AB0;&#x00338;\" ><!--SUCCEEDS ABOVE SINGLE-LINE EQUALS SIGN with slash -->\n" + 
"<!ENTITY nsim             \"&#x02241;\" ><!--NOT TILDE -->\n" + 
"<!ENTITY nsime            \"&#x02244;\" ><!--NOT ASYMPTOTICALLY EQUAL TO -->\n" + 
"<!ENTITY nsmid            \"&#x02224;\" ><!--DOES NOT DIVIDE -->\n" + 
"<!ENTITY nspar            \"&#x02226;\" ><!--NOT PARALLEL TO -->\n" + 
"<!ENTITY nsub             \"&#x02284;\" ><!--NOT A SUBSET OF -->\n" + 
"<!ENTITY nsubE            \"&#x02AC5;&#x00338;\" ><!--SUBSET OF ABOVE EQUALS SIGN with slash -->\n" + 
"<!ENTITY nsube            \"&#x02288;\" ><!--NEITHER A SUBSET OF NOR EQUAL TO -->\n" + 
"<!ENTITY nsup             \"&#x02285;\" ><!--NOT A SUPERSET OF -->\n" + 
"<!ENTITY nsupE            \"&#x02AC6;&#x00338;\" ><!--SUPERSET OF ABOVE EQUALS SIGN with slash -->\n" + 
"<!ENTITY nsupe            \"&#x02289;\" ><!--NEITHER A SUPERSET OF NOR EQUAL TO -->\n" + 
"<!ENTITY nVDash           \"&#x022AF;\" ><!--NEGATED DOUBLE VERTICAL BAR DOUBLE RIGHT TURNSTILE -->\n" + 
"<!ENTITY nVdash           \"&#x022AE;\" ><!--DOES NOT FORCE -->\n" + 
"<!ENTITY nvDash           \"&#x022AD;\" ><!--NOT TRUE -->\n" + 
"<!ENTITY nvdash           \"&#x022AC;\" ><!--DOES NOT PROVE -->\n" + 
"<!ENTITY prnap            \"&#x02AB9;\" ><!--PRECEDES ABOVE NOT ALMOST EQUAL TO -->\n" + 
"<!ENTITY prnE             \"&#x02AB5;\" ><!--PRECEDES ABOVE NOT EQUAL TO -->\n" + 
"<!ENTITY prnsim           \"&#x022E8;\" ><!--PRECEDES BUT NOT EQUIVALENT TO -->\n" + 
"<!ENTITY scnap            \"&#x02ABA;\" ><!--SUCCEEDS ABOVE NOT ALMOST EQUAL TO -->\n" + 
"<!ENTITY scnE             \"&#x02AB6;\" ><!--SUCCEEDS ABOVE NOT EQUAL TO -->\n" + 
"<!ENTITY scnsim           \"&#x022E9;\" ><!--SUCCEEDS BUT NOT EQUIVALENT TO -->\n" + 
"<!ENTITY subnE            \"&#x02ACB;\" ><!--SUBSET OF ABOVE NOT EQUAL TO -->\n" + 
"<!ENTITY subne            \"&#x0228A;\" ><!--SUBSET OF WITH NOT EQUAL TO -->\n" + 
"<!ENTITY supnE            \"&#x02ACC;\" ><!--SUPERSET OF ABOVE NOT EQUAL TO -->\n" + 
"<!ENTITY supne            \"&#x0228B;\" ><!--SUPERSET OF WITH NOT EQUAL TO -->\n" + 
"<!ENTITY vsubnE           \"&#x02ACB;&#x0FE00;\" ><!--SUBSET OF ABOVE NOT EQUAL TO - variant with stroke through bottom members -->\n" + 
"<!ENTITY vsubne           \"&#x0228A;&#x0FE00;\" ><!--SUBSET OF WITH NOT EQUAL TO - variant with stroke through bottom members -->\n" + 
"<!ENTITY vsupnE           \"&#x02ACC;&#x0FE00;\" ><!--SUPERSET OF ABOVE NOT EQUAL TO - variant with stroke through bottom members -->\n" + 
"<!ENTITY vsupne           \"&#x0228B;&#x0FE00;\" ><!--SUPERSET OF WITH NOT EQUAL TO - variant with stroke through bottom members -->\n" + 
"\n" + 
"<!--\n" + 
"     File isoamso.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isoamso.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Added Math Symbols: Ordinary//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isoamso.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isoamso PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Added Math Symbols: Ordinary//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isoamso.ent\"\n" + 
"       >\n" + 
"       %isoamso;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY ang              \"&#x02220;\" ><!--ANGLE -->\n" + 
"<!ENTITY angmsd           \"&#x02221;\" ><!--MEASURED ANGLE -->\n" + 
"<!ENTITY beth             \"&#x02136;\" ><!--BET SYMBOL -->\n" + 
"<!ENTITY bprime           \"&#x02035;\" ><!--REVERSED PRIME -->\n" + 
"<!ENTITY comp             \"&#x02201;\" ><!--COMPLEMENT -->\n" + 
"<!ENTITY daleth           \"&#x02138;\" ><!--DALET SYMBOL -->\n" + 
"<!ENTITY ell              \"&#x02113;\" ><!--SCRIPT SMALL L -->\n" + 
"<!ENTITY empty            \"&#x02205;\" ><!--EMPTY SET -->\n" + 
"<!ENTITY gimel            \"&#x02137;\" ><!--GIMEL SYMBOL -->\n" + 
"<!ENTITY inodot           \"&#x00131;\" ><!--LATIN SMALL LETTER DOTLESS I -->\n" + 
"<!ENTITY jnodot           \"&#x0006A;\" ><!--LATIN SMALL LETTER J -->\n" + 
"<!ENTITY nexist           \"&#x02204;\" ><!--THERE DOES NOT EXIST -->\n" + 
"<!ENTITY oS               \"&#x024C8;\" ><!--CIRCLED LATIN CAPITAL LETTER S -->\n" + 
"<!ENTITY planck           \"&#x0210F;\" ><!--PLANCK CONSTANT OVER TWO PI -->\n" + 
"<!ENTITY real             \"&#x0211C;\" ><!--BLACK-LETTER CAPITAL R -->\n" + 
"<!ENTITY sbsol            \"&#x0FE68;\" ><!--SMALL REVERSE SOLIDUS -->\n" + 
"<!ENTITY vprime           \"&#x02032;\" ><!--PRIME -->\n" + 
"<!ENTITY weierp           \"&#x02118;\" ><!--SCRIPT CAPITAL P -->\n" + 
"\n" + 
"<!--\n" + 
"     File isoamsr.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isoamsr.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Added Math Symbols: Relations//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isoamsr.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isoamsr PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Added Math Symbols: Relations//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isoamsr.ent\"\n" + 
"       >\n" + 
"       %isoamsr;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY ape              \"&#x0224A;\" ><!--ALMOST EQUAL OR EQUAL TO -->\n" + 
"<!ENTITY asymp            \"&#x02248;\" ><!--ALMOST EQUAL TO -->\n" + 
"<!ENTITY bcong            \"&#x0224C;\" ><!--ALL EQUAL TO -->\n" + 
"<!ENTITY bepsi            \"&#x003F6;\" ><!--GREEK REVERSED LUNATE EPSILON SYMBOL -->\n" + 
"<!ENTITY bowtie           \"&#x022C8;\" ><!--BOWTIE -->\n" + 
"<!ENTITY bsim             \"&#x0223D;\" ><!--REVERSED TILDE -->\n" + 
"<!ENTITY bsime            \"&#x022CD;\" ><!--REVERSED TILDE EQUALS -->\n" + 
"<!ENTITY bump             \"&#x0224E;\" ><!--GEOMETRICALLY EQUIVALENT TO -->\n" + 
"<!ENTITY bumpe            \"&#x0224F;\" ><!--DIFFERENCE BETWEEN -->\n" + 
"<!ENTITY cire             \"&#x02257;\" ><!--RING EQUAL TO -->\n" + 
"<!ENTITY colone           \"&#x02254;\" ><!--COLON EQUALS -->\n" + 
"<!ENTITY cuepr            \"&#x022DE;\" ><!--EQUAL TO OR PRECEDES -->\n" + 
"<!ENTITY cuesc            \"&#x022DF;\" ><!--EQUAL TO OR SUCCEEDS -->\n" + 
"<!ENTITY cupre            \"&#x0227C;\" ><!--PRECEDES OR EQUAL TO -->\n" + 
"<!ENTITY dashv            \"&#x022A3;\" ><!--LEFT TACK -->\n" + 
"<!ENTITY ecir             \"&#x02256;\" ><!--RING IN EQUAL TO -->\n" + 
"<!ENTITY ecolon           \"&#x02255;\" ><!--EQUALS COLON -->\n" + 
"<!ENTITY eDot             \"&#x02251;\" ><!--GEOMETRICALLY EQUAL TO -->\n" + 
"<!ENTITY efDot            \"&#x02252;\" ><!--APPROXIMATELY EQUAL TO OR THE IMAGE OF -->\n" + 
"<!ENTITY egs              \"&#x02A96;\" ><!--SLANTED EQUAL TO OR GREATER-THAN -->\n" + 
"<!ENTITY els              \"&#x02A95;\" ><!--SLANTED EQUAL TO OR LESS-THAN -->\n" + 
"<!ENTITY erDot            \"&#x02253;\" ><!--IMAGE OF OR APPROXIMATELY EQUAL TO -->\n" + 
"<!ENTITY esdot            \"&#x02250;\" ><!--APPROACHES THE LIMIT -->\n" + 
"<!ENTITY fork             \"&#x022D4;\" ><!--PITCHFORK -->\n" + 
"<!ENTITY frown            \"&#x02322;\" ><!--FROWN -->\n" + 
"<!ENTITY gap              \"&#x02A86;\" ><!--GREATER-THAN OR APPROXIMATE -->\n" + 
"<!ENTITY gE               \"&#x02267;\" ><!--GREATER-THAN OVER EQUAL TO -->\n" + 
"<!ENTITY gEl              \"&#x02A8C;\" ><!--GREATER-THAN ABOVE DOUBLE-LINE EQUAL ABOVE LESS-THAN -->\n" + 
"<!ENTITY gel              \"&#x022DB;\" ><!--GREATER-THAN EQUAL TO OR LESS-THAN -->\n" + 
"<!ENTITY ges              \"&#x02A7E;\" ><!--GREATER-THAN OR SLANTED EQUAL TO -->\n" + 
"<!ENTITY Gg               \"&#x022D9;\" ><!--VERY MUCH GREATER-THAN -->\n" + 
"<!ENTITY gl               \"&#x02277;\" ><!--GREATER-THAN OR LESS-THAN -->\n" + 
"<!ENTITY gsdot            \"&#x022D7;\" ><!--GREATER-THAN WITH DOT -->\n" + 
"<!ENTITY gsim             \"&#x02273;\" ><!--GREATER-THAN OR EQUIVALENT TO -->\n" + 
"<!ENTITY Gt               \"&#x0226B;\" ><!--MUCH GREATER-THAN -->\n" + 
"<!ENTITY lap              \"&#x02A85;\" ><!--LESS-THAN OR APPROXIMATE -->\n" + 
"<!ENTITY ldot             \"&#x022D6;\" ><!--LESS-THAN WITH DOT -->\n" + 
"<!ENTITY lE               \"&#x02266;\" ><!--LESS-THAN OVER EQUAL TO -->\n" + 
"<!ENTITY lEg              \"&#x02A8B;\" ><!--LESS-THAN ABOVE DOUBLE-LINE EQUAL ABOVE GREATER-THAN -->\n" + 
"<!ENTITY leg              \"&#x022DA;\" ><!--LESS-THAN EQUAL TO OR GREATER-THAN -->\n" + 
"<!ENTITY les              \"&#x02A7D;\" ><!--LESS-THAN OR SLANTED EQUAL TO -->\n" + 
"<!ENTITY lg               \"&#x02276;\" ><!--LESS-THAN OR GREATER-THAN -->\n" + 
"<!ENTITY Ll               \"&#x022D8;\" ><!--VERY MUCH LESS-THAN -->\n" + 
"<!ENTITY lsim             \"&#x02272;\" ><!--LESS-THAN OR EQUIVALENT TO -->\n" + 
"<!ENTITY Lt               \"&#x0226A;\" ><!--MUCH LESS-THAN -->\n" + 
"<!ENTITY ltrie            \"&#x022B4;\" ><!--NORMAL SUBGROUP OF OR EQUAL TO -->\n" + 
"<!ENTITY mid              \"&#x02223;\" ><!--DIVIDES -->\n" + 
"<!ENTITY models           \"&#x022A7;\" ><!--MODELS -->\n" + 
"<!ENTITY pr               \"&#x0227A;\" ><!--PRECEDES -->\n" + 
"<!ENTITY prap             \"&#x02AB7;\" ><!--PRECEDES ABOVE ALMOST EQUAL TO -->\n" + 
"<!ENTITY pre              \"&#x02AAF;\" ><!--PRECEDES ABOVE SINGLE-LINE EQUALS SIGN -->\n" + 
"<!ENTITY prsim            \"&#x0227E;\" ><!--PRECEDES OR EQUIVALENT TO -->\n" + 
"<!ENTITY rtrie            \"&#x022B5;\" ><!--CONTAINS AS NORMAL SUBGROUP OR EQUAL TO -->\n" + 
"<!ENTITY samalg           \"&#x02210;\" ><!--N-ARY COPRODUCT -->\n" + 
"<!ENTITY sc               \"&#x0227B;\" ><!--SUCCEEDS -->\n" + 
"<!ENTITY scap             \"&#x02AB8;\" ><!--SUCCEEDS ABOVE ALMOST EQUAL TO -->\n" + 
"<!ENTITY sccue            \"&#x0227D;\" ><!--SUCCEEDS OR EQUAL TO -->\n" + 
"<!ENTITY sce              \"&#x02AB0;\" ><!--SUCCEEDS ABOVE SINGLE-LINE EQUALS SIGN -->\n" + 
"<!ENTITY scsim            \"&#x0227F;\" ><!--SUCCEEDS OR EQUIVALENT TO -->\n" + 
"<!ENTITY sfrown           \"&#x02322;\" ><!--FROWN -->\n" + 
"<!ENTITY smid             \"&#x02223;\" ><!--DIVIDES -->\n" + 
"<!ENTITY smile            \"&#x02323;\" ><!--SMILE -->\n" + 
"<!ENTITY spar             \"&#x02225;\" ><!--PARALLEL TO -->\n" + 
"<!ENTITY sqsub            \"&#x0228F;\" ><!--SQUARE IMAGE OF -->\n" + 
"<!ENTITY sqsube           \"&#x02291;\" ><!--SQUARE IMAGE OF OR EQUAL TO -->\n" + 
"<!ENTITY sqsup            \"&#x02290;\" ><!--SQUARE ORIGINAL OF -->\n" + 
"<!ENTITY sqsupe           \"&#x02292;\" ><!--SQUARE ORIGINAL OF OR EQUAL TO -->\n" + 
"<!ENTITY ssmile           \"&#x02323;\" ><!--SMILE -->\n" + 
"<!ENTITY Sub              \"&#x022D0;\" ><!--DOUBLE SUBSET -->\n" + 
"<!ENTITY subE             \"&#x02AC5;\" ><!--SUBSET OF ABOVE EQUALS SIGN -->\n" + 
"<!ENTITY Sup              \"&#x022D1;\" ><!--DOUBLE SUPERSET -->\n" + 
"<!ENTITY supE             \"&#x02AC6;\" ><!--SUPERSET OF ABOVE EQUALS SIGN -->\n" + 
"<!ENTITY thkap            \"&#x02248;\" ><!--ALMOST EQUAL TO -->\n" + 
"<!ENTITY thksim           \"&#x0223C;\" ><!--TILDE OPERATOR -->\n" + 
"<!ENTITY trie             \"&#x0225C;\" ><!--DELTA EQUAL TO -->\n" + 
"<!ENTITY twixt            \"&#x0226C;\" ><!--BETWEEN -->\n" + 
"<!ENTITY Vdash            \"&#x022A9;\" ><!--FORCES -->\n" + 
"<!ENTITY vDash            \"&#x022A8;\" ><!--TRUE -->\n" + 
"<!ENTITY vdash            \"&#x022A2;\" ><!--RIGHT TACK -->\n" + 
"<!ENTITY veebar           \"&#x022BB;\" ><!--XOR -->\n" + 
"<!ENTITY vltri            \"&#x022B2;\" ><!--NORMAL SUBGROUP OF -->\n" + 
"<!ENTITY vprop            \"&#x0221D;\" ><!--PROPORTIONAL TO -->\n" + 
"<!ENTITY vrtri            \"&#x022B3;\" ><!--CONTAINS AS NORMAL SUBGROUP -->\n" + 
"<!ENTITY Vvdash           \"&#x022AA;\" ><!--TRIPLE VERTICAL BAR RIGHT TURNSTILE -->\n" + 
"\n" + 
"<!--\n" + 
"     File isobox.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isobox.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Box and Line Drawing//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isobox.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isobox PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Box and Line Drawing//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isobox.ent\"\n" + 
"       >\n" + 
"       %isobox;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY boxDL            \"&#x02557;\" ><!--BOX DRAWINGS DOUBLE DOWN AND LEFT -->\n" + 
"<!ENTITY boxDl            \"&#x02556;\" ><!--BOX DRAWINGS DOWN DOUBLE AND LEFT SINGLE -->\n" + 
"<!ENTITY boxdL            \"&#x02555;\" ><!--BOX DRAWINGS DOWN SINGLE AND LEFT DOUBLE -->\n" + 
"<!ENTITY boxdl            \"&#x02510;\" ><!--BOX DRAWINGS LIGHT DOWN AND LEFT -->\n" + 
"<!ENTITY boxDR            \"&#x02554;\" ><!--BOX DRAWINGS DOUBLE DOWN AND RIGHT -->\n" + 
"<!ENTITY boxDr            \"&#x02553;\" ><!--BOX DRAWINGS DOWN DOUBLE AND RIGHT SINGLE -->\n" + 
"<!ENTITY boxdR            \"&#x02552;\" ><!--BOX DRAWINGS DOWN SINGLE AND RIGHT DOUBLE -->\n" + 
"<!ENTITY boxdr            \"&#x0250C;\" ><!--BOX DRAWINGS LIGHT DOWN AND RIGHT -->\n" + 
"<!ENTITY boxH             \"&#x02550;\" ><!--BOX DRAWINGS DOUBLE HORIZONTAL -->\n" + 
"<!ENTITY boxh             \"&#x02500;\" ><!--BOX DRAWINGS LIGHT HORIZONTAL -->\n" + 
"<!ENTITY boxHD            \"&#x02566;\" ><!--BOX DRAWINGS DOUBLE DOWN AND HORIZONTAL -->\n" + 
"<!ENTITY boxHd            \"&#x02564;\" ><!--BOX DRAWINGS DOWN SINGLE AND HORIZONTAL DOUBLE -->\n" + 
"<!ENTITY boxhD            \"&#x02565;\" ><!--BOX DRAWINGS DOWN DOUBLE AND HORIZONTAL SINGLE -->\n" + 
"<!ENTITY boxhd            \"&#x0252C;\" ><!--BOX DRAWINGS LIGHT DOWN AND HORIZONTAL -->\n" + 
"<!ENTITY boxHU            \"&#x02569;\" ><!--BOX DRAWINGS DOUBLE UP AND HORIZONTAL -->\n" + 
"<!ENTITY boxHu            \"&#x02567;\" ><!--BOX DRAWINGS UP SINGLE AND HORIZONTAL DOUBLE -->\n" + 
"<!ENTITY boxhU            \"&#x02568;\" ><!--BOX DRAWINGS UP DOUBLE AND HORIZONTAL SINGLE -->\n" + 
"<!ENTITY boxhu            \"&#x02534;\" ><!--BOX DRAWINGS LIGHT UP AND HORIZONTAL -->\n" + 
"<!ENTITY boxUL            \"&#x0255D;\" ><!--BOX DRAWINGS DOUBLE UP AND LEFT -->\n" + 
"<!ENTITY boxUl            \"&#x0255C;\" ><!--BOX DRAWINGS UP DOUBLE AND LEFT SINGLE -->\n" + 
"<!ENTITY boxuL            \"&#x0255B;\" ><!--BOX DRAWINGS UP SINGLE AND LEFT DOUBLE -->\n" + 
"<!ENTITY boxul            \"&#x02518;\" ><!--BOX DRAWINGS LIGHT UP AND LEFT -->\n" + 
"<!ENTITY boxUR            \"&#x0255A;\" ><!--BOX DRAWINGS DOUBLE UP AND RIGHT -->\n" + 
"<!ENTITY boxUr            \"&#x02559;\" ><!--BOX DRAWINGS UP DOUBLE AND RIGHT SINGLE -->\n" + 
"<!ENTITY boxuR            \"&#x02558;\" ><!--BOX DRAWINGS UP SINGLE AND RIGHT DOUBLE -->\n" + 
"<!ENTITY boxur            \"&#x02514;\" ><!--BOX DRAWINGS LIGHT UP AND RIGHT -->\n" + 
"<!ENTITY boxV             \"&#x02551;\" ><!--BOX DRAWINGS DOUBLE VERTICAL -->\n" + 
"<!ENTITY boxv             \"&#x02502;\" ><!--BOX DRAWINGS LIGHT VERTICAL -->\n" + 
"<!ENTITY boxVH            \"&#x0256C;\" ><!--BOX DRAWINGS DOUBLE VERTICAL AND HORIZONTAL -->\n" + 
"<!ENTITY boxVh            \"&#x0256B;\" ><!--BOX DRAWINGS VERTICAL DOUBLE AND HORIZONTAL SINGLE -->\n" + 
"<!ENTITY boxvH            \"&#x0256A;\" ><!--BOX DRAWINGS VERTICAL SINGLE AND HORIZONTAL DOUBLE -->\n" + 
"<!ENTITY boxvh            \"&#x0253C;\" ><!--BOX DRAWINGS LIGHT VERTICAL AND HORIZONTAL -->\n" + 
"<!ENTITY boxVL            \"&#x02563;\" ><!--BOX DRAWINGS DOUBLE VERTICAL AND LEFT -->\n" + 
"<!ENTITY boxVl            \"&#x02562;\" ><!--BOX DRAWINGS VERTICAL DOUBLE AND LEFT SINGLE -->\n" + 
"<!ENTITY boxvL            \"&#x02561;\" ><!--BOX DRAWINGS VERTICAL SINGLE AND LEFT DOUBLE -->\n" + 
"<!ENTITY boxvl            \"&#x02524;\" ><!--BOX DRAWINGS LIGHT VERTICAL AND LEFT -->\n" + 
"<!ENTITY boxVR            \"&#x02560;\" ><!--BOX DRAWINGS DOUBLE VERTICAL AND RIGHT -->\n" + 
"<!ENTITY boxVr            \"&#x0255F;\" ><!--BOX DRAWINGS VERTICAL DOUBLE AND RIGHT SINGLE -->\n" + 
"<!ENTITY boxvR            \"&#x0255E;\" ><!--BOX DRAWINGS VERTICAL SINGLE AND RIGHT DOUBLE -->\n" + 
"<!ENTITY boxvr            \"&#x0251C;\" ><!--BOX DRAWINGS LIGHT VERTICAL AND RIGHT -->\n" + 
"\n" + 
"<!--\n" + 
"     File isocyr1.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isocyr1.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Russian Cyrillic//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isocyr1.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isocyr1 PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Russian Cyrillic//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isocyr1.ent\"\n" + 
"       >\n" + 
"       %isocyr1;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY Acy              \"&#x00410;\" ><!--CYRILLIC CAPITAL LETTER A -->\n" + 
"<!ENTITY acy              \"&#x00430;\" ><!--CYRILLIC SMALL LETTER A -->\n" + 
"<!ENTITY Bcy              \"&#x00411;\" ><!--CYRILLIC CAPITAL LETTER BE -->\n" + 
"<!ENTITY bcy              \"&#x00431;\" ><!--CYRILLIC SMALL LETTER BE -->\n" + 
"<!ENTITY CHcy             \"&#x00427;\" ><!--CYRILLIC CAPITAL LETTER CHE -->\n" + 
"<!ENTITY chcy             \"&#x00447;\" ><!--CYRILLIC SMALL LETTER CHE -->\n" + 
"<!ENTITY Dcy              \"&#x00414;\" ><!--CYRILLIC CAPITAL LETTER DE -->\n" + 
"<!ENTITY dcy              \"&#x00434;\" ><!--CYRILLIC SMALL LETTER DE -->\n" + 
"<!ENTITY Ecy              \"&#x0042D;\" ><!--CYRILLIC CAPITAL LETTER E -->\n" + 
"<!ENTITY ecy              \"&#x0044D;\" ><!--CYRILLIC SMALL LETTER E -->\n" + 
"<!ENTITY Fcy              \"&#x00424;\" ><!--CYRILLIC CAPITAL LETTER EF -->\n" + 
"<!ENTITY fcy              \"&#x00444;\" ><!--CYRILLIC SMALL LETTER EF -->\n" + 
"<!ENTITY Gcy              \"&#x00413;\" ><!--CYRILLIC CAPITAL LETTER GHE -->\n" + 
"<!ENTITY gcy              \"&#x00433;\" ><!--CYRILLIC SMALL LETTER GHE -->\n" + 
"<!ENTITY HARDcy           \"&#x0042A;\" ><!--CYRILLIC CAPITAL LETTER HARD SIGN -->\n" + 
"<!ENTITY hardcy           \"&#x0044A;\" ><!--CYRILLIC SMALL LETTER HARD SIGN -->\n" + 
"<!ENTITY Icy              \"&#x00418;\" ><!--CYRILLIC CAPITAL LETTER I -->\n" + 
"<!ENTITY icy              \"&#x00438;\" ><!--CYRILLIC SMALL LETTER I -->\n" + 
"<!ENTITY IEcy             \"&#x00415;\" ><!--CYRILLIC CAPITAL LETTER IE -->\n" + 
"<!ENTITY iecy             \"&#x00435;\" ><!--CYRILLIC SMALL LETTER IE -->\n" + 
"<!ENTITY IOcy             \"&#x00401;\" ><!--CYRILLIC CAPITAL LETTER IO -->\n" + 
"<!ENTITY iocy             \"&#x00451;\" ><!--CYRILLIC SMALL LETTER IO -->\n" + 
"<!ENTITY Jcy              \"&#x00419;\" ><!--CYRILLIC CAPITAL LETTER SHORT I -->\n" + 
"<!ENTITY jcy              \"&#x00439;\" ><!--CYRILLIC SMALL LETTER SHORT I -->\n" + 
"<!ENTITY Kcy              \"&#x0041A;\" ><!--CYRILLIC CAPITAL LETTER KA -->\n" + 
"<!ENTITY kcy              \"&#x0043A;\" ><!--CYRILLIC SMALL LETTER KA -->\n" + 
"<!ENTITY KHcy             \"&#x00425;\" ><!--CYRILLIC CAPITAL LETTER HA -->\n" + 
"<!ENTITY khcy             \"&#x00445;\" ><!--CYRILLIC SMALL LETTER HA -->\n" + 
"<!ENTITY Lcy              \"&#x0041B;\" ><!--CYRILLIC CAPITAL LETTER EL -->\n" + 
"<!ENTITY lcy              \"&#x0043B;\" ><!--CYRILLIC SMALL LETTER EL -->\n" + 
"<!ENTITY Mcy              \"&#x0041C;\" ><!--CYRILLIC CAPITAL LETTER EM -->\n" + 
"<!ENTITY mcy              \"&#x0043C;\" ><!--CYRILLIC SMALL LETTER EM -->\n" + 
"<!ENTITY Ncy              \"&#x0041D;\" ><!--CYRILLIC CAPITAL LETTER EN -->\n" + 
"<!ENTITY ncy              \"&#x0043D;\" ><!--CYRILLIC SMALL LETTER EN -->\n" + 
"<!ENTITY numero           \"&#x02116;\" ><!--NUMERO SIGN -->\n" + 
"<!ENTITY Ocy              \"&#x0041E;\" ><!--CYRILLIC CAPITAL LETTER O -->\n" + 
"<!ENTITY ocy              \"&#x0043E;\" ><!--CYRILLIC SMALL LETTER O -->\n" + 
"<!ENTITY Pcy              \"&#x0041F;\" ><!--CYRILLIC CAPITAL LETTER PE -->\n" + 
"<!ENTITY pcy              \"&#x0043F;\" ><!--CYRILLIC SMALL LETTER PE -->\n" + 
"<!ENTITY Rcy              \"&#x00420;\" ><!--CYRILLIC CAPITAL LETTER ER -->\n" + 
"<!ENTITY rcy              \"&#x00440;\" ><!--CYRILLIC SMALL LETTER ER -->\n" + 
"<!ENTITY Scy              \"&#x00421;\" ><!--CYRILLIC CAPITAL LETTER ES -->\n" + 
"<!ENTITY scy              \"&#x00441;\" ><!--CYRILLIC SMALL LETTER ES -->\n" + 
"<!ENTITY SHCHcy           \"&#x00429;\" ><!--CYRILLIC CAPITAL LETTER SHCHA -->\n" + 
"<!ENTITY shchcy           \"&#x00449;\" ><!--CYRILLIC SMALL LETTER SHCHA -->\n" + 
"<!ENTITY SHcy             \"&#x00428;\" ><!--CYRILLIC CAPITAL LETTER SHA -->\n" + 
"<!ENTITY shcy             \"&#x00448;\" ><!--CYRILLIC SMALL LETTER SHA -->\n" + 
"<!ENTITY SOFTcy           \"&#x0042C;\" ><!--CYRILLIC CAPITAL LETTER SOFT SIGN -->\n" + 
"<!ENTITY softcy           \"&#x0044C;\" ><!--CYRILLIC SMALL LETTER SOFT SIGN -->\n" + 
"<!ENTITY Tcy              \"&#x00422;\" ><!--CYRILLIC CAPITAL LETTER TE -->\n" + 
"<!ENTITY tcy              \"&#x00442;\" ><!--CYRILLIC SMALL LETTER TE -->\n" + 
"<!ENTITY TScy             \"&#x00426;\" ><!--CYRILLIC CAPITAL LETTER TSE -->\n" + 
"<!ENTITY tscy             \"&#x00446;\" ><!--CYRILLIC SMALL LETTER TSE -->\n" + 
"<!ENTITY Ucy              \"&#x00423;\" ><!--CYRILLIC CAPITAL LETTER U -->\n" + 
"<!ENTITY ucy              \"&#x00443;\" ><!--CYRILLIC SMALL LETTER U -->\n" + 
"<!ENTITY Vcy              \"&#x00412;\" ><!--CYRILLIC CAPITAL LETTER VE -->\n" + 
"<!ENTITY vcy              \"&#x00432;\" ><!--CYRILLIC SMALL LETTER VE -->\n" + 
"<!ENTITY YAcy             \"&#x0042F;\" ><!--CYRILLIC CAPITAL LETTER YA -->\n" + 
"<!ENTITY yacy             \"&#x0044F;\" ><!--CYRILLIC SMALL LETTER YA -->\n" + 
"<!ENTITY Ycy              \"&#x0042B;\" ><!--CYRILLIC CAPITAL LETTER YERU -->\n" + 
"<!ENTITY ycy              \"&#x0044B;\" ><!--CYRILLIC SMALL LETTER YERU -->\n" + 
"<!ENTITY YUcy             \"&#x0042E;\" ><!--CYRILLIC CAPITAL LETTER YU -->\n" + 
"<!ENTITY yucy             \"&#x0044E;\" ><!--CYRILLIC SMALL LETTER YU -->\n" + 
"<!ENTITY Zcy              \"&#x00417;\" ><!--CYRILLIC CAPITAL LETTER ZE -->\n" + 
"<!ENTITY zcy              \"&#x00437;\" ><!--CYRILLIC SMALL LETTER ZE -->\n" + 
"<!ENTITY ZHcy             \"&#x00416;\" ><!--CYRILLIC CAPITAL LETTER ZHE -->\n" + 
"<!ENTITY zhcy             \"&#x00436;\" ><!--CYRILLIC SMALL LETTER ZHE -->\n" + 
"\n" + 
"<!--\n" + 
"     File isocyr2.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isocyr2.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Non-Russian Cyrillic//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isocyr2.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isocyr2 PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Non-Russian Cyrillic//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isocyr2.ent\"\n" + 
"       >\n" + 
"       %isocyr2;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY DJcy             \"&#x00402;\" ><!--CYRILLIC CAPITAL LETTER DJE -->\n" + 
"<!ENTITY djcy             \"&#x00452;\" ><!--CYRILLIC SMALL LETTER DJE -->\n" + 
"<!ENTITY DScy             \"&#x00405;\" ><!--CYRILLIC CAPITAL LETTER DZE -->\n" + 
"<!ENTITY dscy             \"&#x00455;\" ><!--CYRILLIC SMALL LETTER DZE -->\n" + 
"<!ENTITY DZcy             \"&#x0040F;\" ><!--CYRILLIC CAPITAL LETTER DZHE -->\n" + 
"<!ENTITY dzcy             \"&#x0045F;\" ><!--CYRILLIC SMALL LETTER DZHE -->\n" + 
"<!ENTITY GJcy             \"&#x00403;\" ><!--CYRILLIC CAPITAL LETTER GJE -->\n" + 
"<!ENTITY gjcy             \"&#x00453;\" ><!--CYRILLIC SMALL LETTER GJE -->\n" + 
"<!ENTITY Iukcy            \"&#x00406;\" ><!--CYRILLIC CAPITAL LETTER BYELORUSSIAN-UKRAINIAN I -->\n" + 
"<!ENTITY iukcy            \"&#x00456;\" ><!--CYRILLIC SMALL LETTER BYELORUSSIAN-UKRAINIAN I -->\n" + 
"<!ENTITY Jsercy           \"&#x00408;\" ><!--CYRILLIC CAPITAL LETTER JE -->\n" + 
"<!ENTITY jsercy           \"&#x00458;\" ><!--CYRILLIC SMALL LETTER JE -->\n" + 
"<!ENTITY Jukcy            \"&#x00404;\" ><!--CYRILLIC CAPITAL LETTER UKRAINIAN IE -->\n" + 
"<!ENTITY jukcy            \"&#x00454;\" ><!--CYRILLIC SMALL LETTER UKRAINIAN IE -->\n" + 
"<!ENTITY KJcy             \"&#x0040C;\" ><!--CYRILLIC CAPITAL LETTER KJE -->\n" + 
"<!ENTITY kjcy             \"&#x0045C;\" ><!--CYRILLIC SMALL LETTER KJE -->\n" + 
"<!ENTITY LJcy             \"&#x00409;\" ><!--CYRILLIC CAPITAL LETTER LJE -->\n" + 
"<!ENTITY ljcy             \"&#x00459;\" ><!--CYRILLIC SMALL LETTER LJE -->\n" + 
"<!ENTITY NJcy             \"&#x0040A;\" ><!--CYRILLIC CAPITAL LETTER NJE -->\n" + 
"<!ENTITY njcy             \"&#x0045A;\" ><!--CYRILLIC SMALL LETTER NJE -->\n" + 
"<!ENTITY TSHcy            \"&#x0040B;\" ><!--CYRILLIC CAPITAL LETTER TSHE -->\n" + 
"<!ENTITY tshcy            \"&#x0045B;\" ><!--CYRILLIC SMALL LETTER TSHE -->\n" + 
"<!ENTITY Ubrcy            \"&#x0040E;\" ><!--CYRILLIC CAPITAL LETTER SHORT U -->\n" + 
"<!ENTITY ubrcy            \"&#x0045E;\" ><!--CYRILLIC SMALL LETTER SHORT U -->\n" + 
"<!ENTITY YIcy             \"&#x00407;\" ><!--CYRILLIC CAPITAL LETTER YI -->\n" + 
"<!ENTITY yicy             \"&#x00457;\" ><!--CYRILLIC SMALL LETTER YI -->\n" + 
"\n" + 
"<!--\n" + 
"     File isodia.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isodia.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Diacritical Marks//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isodia.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isodia PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Diacritical Marks//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isodia.ent\"\n" + 
"       >\n" + 
"       %isodia;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY acute            \"&#x000B4;\" ><!--ACUTE ACCENT -->\n" + 
"<!ENTITY breve            \"&#x002D8;\" ><!--BREVE -->\n" + 
"<!ENTITY caron            \"&#x002C7;\" ><!--CARON -->\n" + 
"<!ENTITY cedil            \"&#x000B8;\" ><!--CEDILLA -->\n" + 
"<!ENTITY circ             \"&#x002C6;\" ><!--MODIFIER LETTER CIRCUMFLEX ACCENT -->\n" + 
"<!ENTITY dblac            \"&#x002DD;\" ><!--DOUBLE ACUTE ACCENT -->\n" + 
"<!ENTITY die              \"&#x000A8;\" ><!--DIAERESIS -->\n" + 
"<!ENTITY dot              \"&#x002D9;\" ><!--DOT ABOVE -->\n" + 
"<!ENTITY grave            \"&#x00060;\" ><!--GRAVE ACCENT -->\n" + 
"<!ENTITY macr             \"&#x000AF;\" ><!--MACRON -->\n" + 
"<!ENTITY ogon             \"&#x002DB;\" ><!--OGONEK -->\n" + 
"<!ENTITY ring             \"&#x002DA;\" ><!--RING ABOVE -->\n" + 
"<!ENTITY tilde            \"&#x002DC;\" ><!--SMALL TILDE -->\n" + 
"<!ENTITY uml              \"&#x000A8;\" ><!--DIAERESIS -->\n" + 
"\n" + 
"<!--\n" + 
"     File isogrk1.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isogrk1.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Greek Letters//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isogrk1.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isogrk1 PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Greek Letters//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isogrk1.ent\"\n" + 
"       >\n" + 
"       %isogrk1;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY Agr              \"&#x00391;\" ><!--GREEK CAPITAL LETTER ALPHA -->\n" + 
"<!ENTITY agr              \"&#x003B1;\" ><!--GREEK SMALL LETTER ALPHA -->\n" + 
"<!ENTITY Bgr              \"&#x00392;\" ><!--GREEK CAPITAL LETTER BETA -->\n" + 
"<!ENTITY bgr              \"&#x003B2;\" ><!--GREEK SMALL LETTER BETA -->\n" + 
"<!ENTITY Dgr              \"&#x00394;\" ><!--GREEK CAPITAL LETTER DELTA -->\n" + 
"<!ENTITY dgr              \"&#x003B4;\" ><!--GREEK SMALL LETTER DELTA -->\n" + 
"<!ENTITY EEgr             \"&#x00397;\" ><!--GREEK CAPITAL LETTER ETA -->\n" + 
"<!ENTITY eegr             \"&#x003B7;\" ><!--GREEK SMALL LETTER ETA -->\n" + 
"<!ENTITY Egr              \"&#x00395;\" ><!--GREEK CAPITAL LETTER EPSILON -->\n" + 
"<!ENTITY egr              \"&#x003B5;\" ><!--GREEK SMALL LETTER EPSILON -->\n" + 
"<!ENTITY Ggr              \"&#x00393;\" ><!--GREEK CAPITAL LETTER GAMMA -->\n" + 
"<!ENTITY ggr              \"&#x003B3;\" ><!--GREEK SMALL LETTER GAMMA -->\n" + 
"<!ENTITY Igr              \"&#x00399;\" ><!--GREEK CAPITAL LETTER IOTA -->\n" + 
"<!ENTITY igr              \"&#x003B9;\" ><!--GREEK SMALL LETTER IOTA -->\n" + 
"<!ENTITY Kgr              \"&#x0039A;\" ><!--GREEK CAPITAL LETTER KAPPA -->\n" + 
"<!ENTITY kgr              \"&#x003BA;\" ><!--GREEK SMALL LETTER KAPPA -->\n" + 
"<!ENTITY KHgr             \"&#x003A7;\" ><!--GREEK CAPITAL LETTER CHI -->\n" + 
"<!ENTITY khgr             \"&#x003C7;\" ><!--GREEK SMALL LETTER CHI -->\n" + 
"<!ENTITY Lgr              \"&#x0039B;\" ><!--GREEK CAPITAL LETTER LAMDA -->\n" + 
"<!ENTITY lgr              \"&#x003BB;\" ><!--GREEK SMALL LETTER LAMDA -->\n" + 
"<!ENTITY Mgr              \"&#x0039C;\" ><!--GREEK CAPITAL LETTER MU -->\n" + 
"<!ENTITY mgr              \"&#x003BC;\" ><!--GREEK SMALL LETTER MU -->\n" + 
"<!ENTITY Ngr              \"&#x0039D;\" ><!--GREEK CAPITAL LETTER NU -->\n" + 
"<!ENTITY ngr              \"&#x003BD;\" ><!--GREEK SMALL LETTER NU -->\n" + 
"<!ENTITY Ogr              \"&#x0039F;\" ><!--GREEK CAPITAL LETTER OMICRON -->\n" + 
"<!ENTITY ogr              \"&#x003BF;\" ><!--GREEK SMALL LETTER OMICRON -->\n" + 
"<!ENTITY OHgr             \"&#x003A9;\" ><!--GREEK CAPITAL LETTER OMEGA -->\n" + 
"<!ENTITY ohgr             \"&#x003C9;\" ><!--GREEK SMALL LETTER OMEGA -->\n" + 
"<!ENTITY Pgr              \"&#x003A0;\" ><!--GREEK CAPITAL LETTER PI -->\n" + 
"<!ENTITY pgr              \"&#x003C0;\" ><!--GREEK SMALL LETTER PI -->\n" + 
"<!ENTITY PHgr             \"&#x003A6;\" ><!--GREEK CAPITAL LETTER PHI -->\n" + 
"<!ENTITY phgr             \"&#x003C6;\" ><!--GREEK SMALL LETTER PHI -->\n" + 
"<!ENTITY PSgr             \"&#x003A8;\" ><!--GREEK CAPITAL LETTER PSI -->\n" + 
"<!ENTITY psgr             \"&#x003C8;\" ><!--GREEK SMALL LETTER PSI -->\n" + 
"<!ENTITY Rgr              \"&#x003A1;\" ><!--GREEK CAPITAL LETTER RHO -->\n" + 
"<!ENTITY rgr              \"&#x003C1;\" ><!--GREEK SMALL LETTER RHO -->\n" + 
"<!ENTITY sfgr             \"&#x003C2;\" ><!--GREEK SMALL LETTER FINAL SIGMA -->\n" + 
"<!ENTITY Sgr              \"&#x003A3;\" ><!--GREEK CAPITAL LETTER SIGMA -->\n" + 
"<!ENTITY sgr              \"&#x003C3;\" ><!--GREEK SMALL LETTER SIGMA -->\n" + 
"<!ENTITY Tgr              \"&#x003A4;\" ><!--GREEK CAPITAL LETTER TAU -->\n" + 
"<!ENTITY tgr              \"&#x003C4;\" ><!--GREEK SMALL LETTER TAU -->\n" + 
"<!ENTITY THgr             \"&#x00398;\" ><!--GREEK CAPITAL LETTER THETA -->\n" + 
"<!ENTITY thgr             \"&#x003B8;\" ><!--GREEK SMALL LETTER THETA -->\n" + 
"<!ENTITY Ugr              \"&#x003A5;\" ><!--GREEK CAPITAL LETTER UPSILON -->\n" + 
"<!ENTITY ugr              \"&#x003C5;\" ><!--GREEK SMALL LETTER UPSILON -->\n" + 
"<!ENTITY Xgr              \"&#x0039E;\" ><!--GREEK CAPITAL LETTER XI -->\n" + 
"<!ENTITY xgr              \"&#x003BE;\" ><!--GREEK SMALL LETTER XI -->\n" + 
"<!ENTITY Zgr              \"&#x00396;\" ><!--GREEK CAPITAL LETTER ZETA -->\n" + 
"<!ENTITY zgr              \"&#x003B6;\" ><!--GREEK SMALL LETTER ZETA -->\n" + 
"\n" + 
"<!--\n" + 
"     File isogrk2.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isogrk2.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Monotoniko Greek//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isogrk2.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isogrk2 PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Monotoniko Greek//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isogrk2.ent\"\n" + 
"       >\n" + 
"       %isogrk2;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY Aacgr            \"&#x00386;\" ><!--GREEK CAPITAL LETTER ALPHA WITH TONOS -->\n" + 
"<!ENTITY aacgr            \"&#x003AC;\" ><!--GREEK SMALL LETTER ALPHA WITH TONOS -->\n" + 
"<!ENTITY Eacgr            \"&#x00388;\" ><!--GREEK CAPITAL LETTER EPSILON WITH TONOS -->\n" + 
"<!ENTITY eacgr            \"&#x003AD;\" ><!--GREEK SMALL LETTER EPSILON WITH TONOS -->\n" + 
"<!ENTITY EEacgr           \"&#x00389;\" ><!--GREEK CAPITAL LETTER ETA WITH TONOS -->\n" + 
"<!ENTITY eeacgr           \"&#x003AE;\" ><!--GREEK SMALL LETTER ETA WITH TONOS -->\n" + 
"<!ENTITY Iacgr            \"&#x0038A;\" ><!--GREEK CAPITAL LETTER IOTA WITH TONOS -->\n" + 
"<!ENTITY iacgr            \"&#x003AF;\" ><!--GREEK SMALL LETTER IOTA WITH TONOS -->\n" + 
"<!ENTITY idiagr           \"&#x00390;\" ><!--GREEK SMALL LETTER IOTA WITH DIALYTIKA AND TONOS -->\n" + 
"<!ENTITY Idigr            \"&#x003AA;\" ><!--GREEK CAPITAL LETTER IOTA WITH DIALYTIKA -->\n" + 
"<!ENTITY idigr            \"&#x003CA;\" ><!--GREEK SMALL LETTER IOTA WITH DIALYTIKA -->\n" + 
"<!ENTITY Oacgr            \"&#x0038C;\" ><!--GREEK CAPITAL LETTER OMICRON WITH TONOS -->\n" + 
"<!ENTITY oacgr            \"&#x003CC;\" ><!--GREEK SMALL LETTER OMICRON WITH TONOS -->\n" + 
"<!ENTITY OHacgr           \"&#x0038F;\" ><!--GREEK CAPITAL LETTER OMEGA WITH TONOS -->\n" + 
"<!ENTITY ohacgr           \"&#x003CE;\" ><!--GREEK SMALL LETTER OMEGA WITH TONOS -->\n" + 
"<!ENTITY Uacgr            \"&#x0038E;\" ><!--GREEK CAPITAL LETTER UPSILON WITH TONOS -->\n" + 
"<!ENTITY uacgr            \"&#x003CD;\" ><!--GREEK SMALL LETTER UPSILON WITH TONOS -->\n" + 
"<!ENTITY udiagr           \"&#x003B0;\" ><!--GREEK SMALL LETTER UPSILON WITH DIALYTIKA AND TONOS -->\n" + 
"<!ENTITY Udigr            \"&#x003AB;\" ><!--GREEK CAPITAL LETTER UPSILON WITH DIALYTIKA -->\n" + 
"<!ENTITY udigr            \"&#x003CB;\" ><!--GREEK SMALL LETTER UPSILON WITH DIALYTIKA -->\n" + 
"\n" + 
"<!--\n" + 
"     File isogrk3.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isogrk3.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Greek Symbols//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isogrk3.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isogrk3 PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Greek Symbols//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isogrk3.ent\"\n" + 
"       >\n" + 
"       %isogrk3;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY alpha            \"&#x003B1;\" ><!--GREEK SMALL LETTER ALPHA -->\n" + 
"<!ENTITY beta             \"&#x003B2;\" ><!--GREEK SMALL LETTER BETA -->\n" + 
"<!ENTITY chi              \"&#x003C7;\" ><!--GREEK SMALL LETTER CHI -->\n" + 
"<!ENTITY Delta            \"&#x00394;\" ><!--GREEK CAPITAL LETTER DELTA -->\n" + 
"<!ENTITY delta            \"&#x003B4;\" ><!--GREEK SMALL LETTER DELTA -->\n" + 
"<!ENTITY epsi             \"&#x003F5;\" ><!--GREEK LUNATE EPSILON SYMBOL -->\n" + 
"<!ENTITY epsis            \"&#x003F5;\" ><!--GREEK LUNATE EPSILON SYMBOL -->\n" + 
"<!ENTITY epsiv            \"&#x003B5;\" ><!--GREEK SMALL LETTER EPSILON -->\n" + 
"<!ENTITY eta              \"&#x003B7;\" ><!--GREEK SMALL LETTER ETA -->\n" + 
"<!ENTITY Gamma            \"&#x00393;\" ><!--GREEK CAPITAL LETTER GAMMA -->\n" + 
"<!ENTITY gamma            \"&#x003B3;\" ><!--GREEK SMALL LETTER GAMMA -->\n" + 
"<!ENTITY gammad           \"&#x003DD;\" ><!--GREEK SMALL LETTER DIGAMMA -->\n" + 
"<!ENTITY iota             \"&#x003B9;\" ><!--GREEK SMALL LETTER IOTA -->\n" + 
"<!ENTITY kappa            \"&#x003BA;\" ><!--GREEK SMALL LETTER KAPPA -->\n" + 
"<!ENTITY kappav           \"&#x003F0;\" ><!--GREEK KAPPA SYMBOL -->\n" + 
"<!ENTITY Lambda           \"&#x0039B;\" ><!--GREEK CAPITAL LETTER LAMDA -->\n" + 
"<!ENTITY lambda           \"&#x003BB;\" ><!--GREEK SMALL LETTER LAMDA -->\n" + 
"<!ENTITY mu               \"&#x003BC;\" ><!--GREEK SMALL LETTER MU -->\n" + 
"<!ENTITY nu               \"&#x003BD;\" ><!--GREEK SMALL LETTER NU -->\n" + 
"<!ENTITY Omega            \"&#x003A9;\" ><!--GREEK CAPITAL LETTER OMEGA -->\n" + 
"<!ENTITY omega            \"&#x003C9;\" ><!--GREEK SMALL LETTER OMEGA -->\n" + 
"<!ENTITY Phi              \"&#x003A6;\" ><!--GREEK CAPITAL LETTER PHI -->\n" + 
"<!ENTITY phis             \"&#x003D5;\" ><!--GREEK PHI SYMBOL -->\n" + 
"<!ENTITY phiv             \"&#x003C6;\" ><!--GREEK SMALL LETTER PHI -->\n" + 
"<!ENTITY Pi               \"&#x003A0;\" ><!--GREEK CAPITAL LETTER PI -->\n" + 
"<!ENTITY pi               \"&#x003C0;\" ><!--GREEK SMALL LETTER PI -->\n" + 
"<!ENTITY piv              \"&#x003D6;\" ><!--GREEK PI SYMBOL -->\n" + 
"<!ENTITY Psi              \"&#x003A8;\" ><!--GREEK CAPITAL LETTER PSI -->\n" + 
"<!ENTITY psi              \"&#x003C8;\" ><!--GREEK SMALL LETTER PSI -->\n" + 
"<!ENTITY rho              \"&#x003C1;\" ><!--GREEK SMALL LETTER RHO -->\n" + 
"<!ENTITY rhov             \"&#x003F1;\" ><!--GREEK RHO SYMBOL -->\n" + 
"<!ENTITY Sigma            \"&#x003A3;\" ><!--GREEK CAPITAL LETTER SIGMA -->\n" + 
"<!ENTITY sigma            \"&#x003C3;\" ><!--GREEK SMALL LETTER SIGMA -->\n" + 
"<!ENTITY sigmav           \"&#x003C2;\" ><!--GREEK SMALL LETTER FINAL SIGMA -->\n" + 
"<!ENTITY tau              \"&#x003C4;\" ><!--GREEK SMALL LETTER TAU -->\n" + 
"<!ENTITY Theta            \"&#x00398;\" ><!--GREEK CAPITAL LETTER THETA -->\n" + 
"<!ENTITY thetas           \"&#x003B8;\" ><!--GREEK SMALL LETTER THETA -->\n" + 
"<!ENTITY thetav           \"&#x003D1;\" ><!--GREEK THETA SYMBOL -->\n" + 
"<!ENTITY Upsi             \"&#x003D2;\" ><!--GREEK UPSILON WITH HOOK SYMBOL -->\n" + 
"<!ENTITY upsi             \"&#x003C5;\" ><!--GREEK SMALL LETTER UPSILON -->\n" + 
"<!ENTITY Xi               \"&#x0039E;\" ><!--GREEK CAPITAL LETTER XI -->\n" + 
"<!ENTITY xi               \"&#x003BE;\" ><!--GREEK SMALL LETTER XI -->\n" + 
"<!ENTITY zeta             \"&#x003B6;\" ><!--GREEK SMALL LETTER ZETA -->\n" + 
"\n" + 
"<!--\n" + 
"     File isogrk4.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isogrk4.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Alternative Greek Symbols//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isogrk4.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isogrk4 PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Alternative Greek Symbols//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isogrk4.ent\"\n" + 
"       >\n" + 
"       %isogrk4;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY b.alpha          \"&#x1D6C2;\" ><!--MATHEMATICAL BOLD SMALL ALPHA -->\n" + 
"<!ENTITY b.beta           \"&#x1D6C3;\" ><!--MATHEMATICAL BOLD SMALL BETA -->\n" + 
"<!ENTITY b.chi            \"&#x1D6D8;\" ><!--MATHEMATICAL BOLD SMALL CHI -->\n" + 
"<!ENTITY b.Delta          \"&#x1D6AB;\" ><!--MATHEMATICAL BOLD CAPITAL DELTA -->\n" + 
"<!ENTITY b.delta          \"&#x1D6C5;\" ><!--MATHEMATICAL BOLD SMALL DELTA -->\n" + 
"<!ENTITY b.epsi           \"&#x1D6C6;\" ><!--MATHEMATICAL BOLD SMALL EPSILON -->\n" + 
"<!ENTITY b.epsiv          \"&#x1D6DC;\" ><!--MATHEMATICAL BOLD EPSILON SYMBOL -->\n" + 
"<!ENTITY b.eta            \"&#x1D6C8;\" ><!--MATHEMATICAL BOLD SMALL ETA -->\n" + 
"<!ENTITY b.Gamma          \"&#x1D6AA;\" ><!--MATHEMATICAL BOLD CAPITAL GAMMA -->\n" + 
"<!ENTITY b.gamma          \"&#x1D6C4;\" ><!--MATHEMATICAL BOLD SMALL GAMMA -->\n" + 
"<!ENTITY b.Gammad         \"&#x003DC;\" ><!--GREEK LETTER DIGAMMA -->\n" + 
"<!ENTITY b.gammad         \"&#x003DD;\" ><!--GREEK SMALL LETTER DIGAMMA -->\n" + 
"<!ENTITY b.iota           \"&#x1D6CA;\" ><!--MATHEMATICAL BOLD SMALL IOTA -->\n" + 
"<!ENTITY b.kappa          \"&#x1D6CB;\" ><!--MATHEMATICAL BOLD SMALL KAPPA -->\n" + 
"<!ENTITY b.kappav         \"&#x1D6DE;\" ><!--MATHEMATICAL BOLD KAPPA SYMBOL -->\n" + 
"<!ENTITY b.Lambda         \"&#x1D6B2;\" ><!--MATHEMATICAL BOLD CAPITAL LAMDA -->\n" + 
"<!ENTITY b.lambda         \"&#x1D6CC;\" ><!--MATHEMATICAL BOLD SMALL LAMDA -->\n" + 
"<!ENTITY b.mu             \"&#x1D6CD;\" ><!--MATHEMATICAL BOLD SMALL MU -->\n" + 
"<!ENTITY b.nu             \"&#x1D6CE;\" ><!--MATHEMATICAL BOLD SMALL NU -->\n" + 
"<!ENTITY b.Omega          \"&#x1D6C0;\" ><!--MATHEMATICAL BOLD CAPITAL OMEGA -->\n" + 
"<!ENTITY b.omega          \"&#x1D6DA;\" ><!--MATHEMATICAL BOLD SMALL OMEGA -->\n" + 
"<!ENTITY b.Phi            \"&#x1D6BD;\" ><!--MATHEMATICAL BOLD CAPITAL PHI -->\n" + 
"<!ENTITY b.phi            \"&#x1D6D7;\" ><!--MATHEMATICAL BOLD SMALL PHI -->\n" + 
"<!ENTITY b.phiv           \"&#x1D6DF;\" ><!--MATHEMATICAL BOLD PHI SYMBOL -->\n" + 
"<!ENTITY b.Pi             \"&#x1D6B7;\" ><!--MATHEMATICAL BOLD CAPITAL PI -->\n" + 
"<!ENTITY b.pi             \"&#x1D6D1;\" ><!--MATHEMATICAL BOLD SMALL PI -->\n" + 
"<!ENTITY b.piv            \"&#x1D6E1;\" ><!--MATHEMATICAL BOLD PI SYMBOL -->\n" + 
"<!ENTITY b.Psi            \"&#x1D6BF;\" ><!--MATHEMATICAL BOLD CAPITAL PSI -->\n" + 
"<!ENTITY b.psi            \"&#x1D6D9;\" ><!--MATHEMATICAL BOLD SMALL PSI -->\n" + 
"<!ENTITY b.rho            \"&#x1D6D2;\" ><!--MATHEMATICAL BOLD SMALL RHO -->\n" + 
"<!ENTITY b.rhov           \"&#x1D6E0;\" ><!--MATHEMATICAL BOLD RHO SYMBOL -->\n" + 
"<!ENTITY b.Sigma          \"&#x1D6BA;\" ><!--MATHEMATICAL BOLD CAPITAL SIGMA -->\n" + 
"<!ENTITY b.sigma          \"&#x1D6D4;\" ><!--MATHEMATICAL BOLD SMALL SIGMA -->\n" + 
"<!ENTITY b.sigmav         \"&#x1D6D3;\" ><!--MATHEMATICAL BOLD SMALL FINAL SIGMA -->\n" + 
"<!ENTITY b.tau            \"&#x1D6D5;\" ><!--MATHEMATICAL BOLD SMALL TAU -->\n" + 
"<!ENTITY b.Theta          \"&#x1D6AF;\" ><!--MATHEMATICAL BOLD CAPITAL THETA -->\n" + 
"<!ENTITY b.thetas         \"&#x1D6C9;\" ><!--MATHEMATICAL BOLD SMALL THETA -->\n" + 
"<!ENTITY b.thetav         \"&#x1D6DD;\" ><!--MATHEMATICAL BOLD THETA SYMBOL -->\n" + 
"<!ENTITY b.Upsi           \"&#x1D6BC;\" ><!--MATHEMATICAL BOLD CAPITAL UPSILON -->\n" + 
"<!ENTITY b.upsi           \"&#x1D6D6;\" ><!--MATHEMATICAL BOLD SMALL UPSILON -->\n" + 
"<!ENTITY b.Xi             \"&#x1D6B5;\" ><!--MATHEMATICAL BOLD CAPITAL XI -->\n" + 
"<!ENTITY b.xi             \"&#x1D6CF;\" ><!--MATHEMATICAL BOLD SMALL XI -->\n" + 
"<!ENTITY b.zeta           \"&#x1D6C7;\" ><!--MATHEMATICAL BOLD SMALL ZETA -->\n" + 
"\n" + 
"<!--\n" + 
"     File isolat1.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isolat1.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Added Latin 1//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isolat1.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isolat1 PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Added Latin 1//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isolat1.ent\"\n" + 
"       >\n" + 
"       %isolat1;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY Aacute           \"&#x000C1;\" ><!--LATIN CAPITAL LETTER A WITH ACUTE -->\n" + 
"<!ENTITY aacute           \"&#x000E1;\" ><!--LATIN SMALL LETTER A WITH ACUTE -->\n" + 
"<!ENTITY Acirc            \"&#x000C2;\" ><!--LATIN CAPITAL LETTER A WITH CIRCUMFLEX -->\n" + 
"<!ENTITY acirc            \"&#x000E2;\" ><!--LATIN SMALL LETTER A WITH CIRCUMFLEX -->\n" + 
"<!ENTITY AElig            \"&#x000C6;\" ><!--LATIN CAPITAL LETTER AE -->\n" + 
"<!ENTITY aelig            \"&#x000E6;\" ><!--LATIN SMALL LETTER AE -->\n" + 
"<!ENTITY Agrave           \"&#x000C0;\" ><!--LATIN CAPITAL LETTER A WITH GRAVE -->\n" + 
"<!ENTITY agrave           \"&#x000E0;\" ><!--LATIN SMALL LETTER A WITH GRAVE -->\n" + 
"<!ENTITY Aring            \"&#x000C5;\" ><!--LATIN CAPITAL LETTER A WITH RING ABOVE -->\n" + 
"<!ENTITY aring            \"&#x000E5;\" ><!--LATIN SMALL LETTER A WITH RING ABOVE -->\n" + 
"<!ENTITY Atilde           \"&#x000C3;\" ><!--LATIN CAPITAL LETTER A WITH TILDE -->\n" + 
"<!ENTITY atilde           \"&#x000E3;\" ><!--LATIN SMALL LETTER A WITH TILDE -->\n" + 
"<!ENTITY Auml             \"&#x000C4;\" ><!--LATIN CAPITAL LETTER A WITH DIAERESIS -->\n" + 
"<!ENTITY auml             \"&#x000E4;\" ><!--LATIN SMALL LETTER A WITH DIAERESIS -->\n" + 
"<!ENTITY Ccedil           \"&#x000C7;\" ><!--LATIN CAPITAL LETTER C WITH CEDILLA -->\n" + 
"<!ENTITY ccedil           \"&#x000E7;\" ><!--LATIN SMALL LETTER C WITH CEDILLA -->\n" + 
"<!ENTITY Eacute           \"&#x000C9;\" ><!--LATIN CAPITAL LETTER E WITH ACUTE -->\n" + 
"<!ENTITY eacute           \"&#x000E9;\" ><!--LATIN SMALL LETTER E WITH ACUTE -->\n" + 
"<!ENTITY Ecirc            \"&#x000CA;\" ><!--LATIN CAPITAL LETTER E WITH CIRCUMFLEX -->\n" + 
"<!ENTITY ecirc            \"&#x000EA;\" ><!--LATIN SMALL LETTER E WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Egrave           \"&#x000C8;\" ><!--LATIN CAPITAL LETTER E WITH GRAVE -->\n" + 
"<!ENTITY egrave           \"&#x000E8;\" ><!--LATIN SMALL LETTER E WITH GRAVE -->\n" + 
"<!ENTITY ETH              \"&#x000D0;\" ><!--LATIN CAPITAL LETTER ETH -->\n" + 
"<!ENTITY eth              \"&#x000F0;\" ><!--LATIN SMALL LETTER ETH -->\n" + 
"<!ENTITY Euml             \"&#x000CB;\" ><!--LATIN CAPITAL LETTER E WITH DIAERESIS -->\n" + 
"<!ENTITY euml             \"&#x000EB;\" ><!--LATIN SMALL LETTER E WITH DIAERESIS -->\n" + 
"<!ENTITY Iacute           \"&#x000CD;\" ><!--LATIN CAPITAL LETTER I WITH ACUTE -->\n" + 
"<!ENTITY iacute           \"&#x000ED;\" ><!--LATIN SMALL LETTER I WITH ACUTE -->\n" + 
"<!ENTITY Icirc            \"&#x000CE;\" ><!--LATIN CAPITAL LETTER I WITH CIRCUMFLEX -->\n" + 
"<!ENTITY icirc            \"&#x000EE;\" ><!--LATIN SMALL LETTER I WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Igrave           \"&#x000CC;\" ><!--LATIN CAPITAL LETTER I WITH GRAVE -->\n" + 
"<!ENTITY igrave           \"&#x000EC;\" ><!--LATIN SMALL LETTER I WITH GRAVE -->\n" + 
"<!ENTITY Iuml             \"&#x000CF;\" ><!--LATIN CAPITAL LETTER I WITH DIAERESIS -->\n" + 
"<!ENTITY iuml             \"&#x000EF;\" ><!--LATIN SMALL LETTER I WITH DIAERESIS -->\n" + 
"<!ENTITY Ntilde           \"&#x000D1;\" ><!--LATIN CAPITAL LETTER N WITH TILDE -->\n" + 
"<!ENTITY ntilde           \"&#x000F1;\" ><!--LATIN SMALL LETTER N WITH TILDE -->\n" + 
"<!ENTITY Oacute           \"&#x000D3;\" ><!--LATIN CAPITAL LETTER O WITH ACUTE -->\n" + 
"<!ENTITY oacute           \"&#x000F3;\" ><!--LATIN SMALL LETTER O WITH ACUTE -->\n" + 
"<!ENTITY Ocirc            \"&#x000D4;\" ><!--LATIN CAPITAL LETTER O WITH CIRCUMFLEX -->\n" + 
"<!ENTITY ocirc            \"&#x000F4;\" ><!--LATIN SMALL LETTER O WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Ograve           \"&#x000D2;\" ><!--LATIN CAPITAL LETTER O WITH GRAVE -->\n" + 
"<!ENTITY ograve           \"&#x000F2;\" ><!--LATIN SMALL LETTER O WITH GRAVE -->\n" + 
"<!ENTITY Oslash           \"&#x000D8;\" ><!--LATIN CAPITAL LETTER O WITH STROKE -->\n" + 
"<!ENTITY oslash           \"&#x000F8;\" ><!--LATIN SMALL LETTER O WITH STROKE -->\n" + 
"<!ENTITY Otilde           \"&#x000D5;\" ><!--LATIN CAPITAL LETTER O WITH TILDE -->\n" + 
"<!ENTITY otilde           \"&#x000F5;\" ><!--LATIN SMALL LETTER O WITH TILDE -->\n" + 
"<!ENTITY Ouml             \"&#x000D6;\" ><!--LATIN CAPITAL LETTER O WITH DIAERESIS -->\n" + 
"<!ENTITY ouml             \"&#x000F6;\" ><!--LATIN SMALL LETTER O WITH DIAERESIS -->\n" + 
"<!ENTITY szlig            \"&#x000DF;\" ><!--LATIN SMALL LETTER SHARP S -->\n" + 
"<!ENTITY THORN            \"&#x000DE;\" ><!--LATIN CAPITAL LETTER THORN -->\n" + 
"<!ENTITY thorn            \"&#x000FE;\" ><!--LATIN SMALL LETTER THORN -->\n" + 
"<!ENTITY Uacute           \"&#x000DA;\" ><!--LATIN CAPITAL LETTER U WITH ACUTE -->\n" + 
"<!ENTITY uacute           \"&#x000FA;\" ><!--LATIN SMALL LETTER U WITH ACUTE -->\n" + 
"<!ENTITY Ucirc            \"&#x000DB;\" ><!--LATIN CAPITAL LETTER U WITH CIRCUMFLEX -->\n" + 
"<!ENTITY ucirc            \"&#x000FB;\" ><!--LATIN SMALL LETTER U WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Ugrave           \"&#x000D9;\" ><!--LATIN CAPITAL LETTER U WITH GRAVE -->\n" + 
"<!ENTITY ugrave           \"&#x000F9;\" ><!--LATIN SMALL LETTER U WITH GRAVE -->\n" + 
"<!ENTITY Uuml             \"&#x000DC;\" ><!--LATIN CAPITAL LETTER U WITH DIAERESIS -->\n" + 
"<!ENTITY uuml             \"&#x000FC;\" ><!--LATIN SMALL LETTER U WITH DIAERESIS -->\n" + 
"<!ENTITY Yacute           \"&#x000DD;\" ><!--LATIN CAPITAL LETTER Y WITH ACUTE -->\n" + 
"<!ENTITY yacute           \"&#x000FD;\" ><!--LATIN SMALL LETTER Y WITH ACUTE -->\n" + 
"<!ENTITY yuml             \"&#x000FF;\" ><!--LATIN SMALL LETTER Y WITH DIAERESIS -->\n" + 
"\n" + 
"<!--\n" + 
"     File isolat2.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isolat2.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Added Latin 2//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isolat2.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isolat2 PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Added Latin 2//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isolat2.ent\"\n" + 
"       >\n" + 
"       %isolat2;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY Abreve           \"&#x00102;\" ><!--LATIN CAPITAL LETTER A WITH BREVE -->\n" + 
"<!ENTITY abreve           \"&#x00103;\" ><!--LATIN SMALL LETTER A WITH BREVE -->\n" + 
"<!ENTITY Amacr            \"&#x00100;\" ><!--LATIN CAPITAL LETTER A WITH MACRON -->\n" + 
"<!ENTITY amacr            \"&#x00101;\" ><!--LATIN SMALL LETTER A WITH MACRON -->\n" + 
"<!ENTITY Aogon            \"&#x00104;\" ><!--LATIN CAPITAL LETTER A WITH OGONEK -->\n" + 
"<!ENTITY aogon            \"&#x00105;\" ><!--LATIN SMALL LETTER A WITH OGONEK -->\n" + 
"<!ENTITY Cacute           \"&#x00106;\" ><!--LATIN CAPITAL LETTER C WITH ACUTE -->\n" + 
"<!ENTITY cacute           \"&#x00107;\" ><!--LATIN SMALL LETTER C WITH ACUTE -->\n" + 
"<!ENTITY Ccaron           \"&#x0010C;\" ><!--LATIN CAPITAL LETTER C WITH CARON -->\n" + 
"<!ENTITY ccaron           \"&#x0010D;\" ><!--LATIN SMALL LETTER C WITH CARON -->\n" + 
"<!ENTITY Ccirc            \"&#x00108;\" ><!--LATIN CAPITAL LETTER C WITH CIRCUMFLEX -->\n" + 
"<!ENTITY ccirc            \"&#x00109;\" ><!--LATIN SMALL LETTER C WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Cdot             \"&#x0010A;\" ><!--LATIN CAPITAL LETTER C WITH DOT ABOVE -->\n" + 
"<!ENTITY cdot             \"&#x0010B;\" ><!--LATIN SMALL LETTER C WITH DOT ABOVE -->\n" + 
"<!ENTITY Dcaron           \"&#x0010E;\" ><!--LATIN CAPITAL LETTER D WITH CARON -->\n" + 
"<!ENTITY dcaron           \"&#x0010F;\" ><!--LATIN SMALL LETTER D WITH CARON -->\n" + 
"<!ENTITY Dstrok           \"&#x00110;\" ><!--LATIN CAPITAL LETTER D WITH STROKE -->\n" + 
"<!ENTITY dstrok           \"&#x00111;\" ><!--LATIN SMALL LETTER D WITH STROKE -->\n" + 
"<!ENTITY Ecaron           \"&#x0011A;\" ><!--LATIN CAPITAL LETTER E WITH CARON -->\n" + 
"<!ENTITY ecaron           \"&#x0011B;\" ><!--LATIN SMALL LETTER E WITH CARON -->\n" + 
"<!ENTITY Edot             \"&#x00116;\" ><!--LATIN CAPITAL LETTER E WITH DOT ABOVE -->\n" + 
"<!ENTITY edot             \"&#x00117;\" ><!--LATIN SMALL LETTER E WITH DOT ABOVE -->\n" + 
"<!ENTITY Emacr            \"&#x00112;\" ><!--LATIN CAPITAL LETTER E WITH MACRON -->\n" + 
"<!ENTITY emacr            \"&#x00113;\" ><!--LATIN SMALL LETTER E WITH MACRON -->\n" + 
"<!ENTITY ENG              \"&#x0014A;\" ><!--LATIN CAPITAL LETTER ENG -->\n" + 
"<!ENTITY eng              \"&#x0014B;\" ><!--LATIN SMALL LETTER ENG -->\n" + 
"<!ENTITY Eogon            \"&#x00118;\" ><!--LATIN CAPITAL LETTER E WITH OGONEK -->\n" + 
"<!ENTITY eogon            \"&#x00119;\" ><!--LATIN SMALL LETTER E WITH OGONEK -->\n" + 
"<!ENTITY gacute           \"&#x001F5;\" ><!--LATIN SMALL LETTER G WITH ACUTE -->\n" + 
"<!ENTITY Gbreve           \"&#x0011E;\" ><!--LATIN CAPITAL LETTER G WITH BREVE -->\n" + 
"<!ENTITY gbreve           \"&#x0011F;\" ><!--LATIN SMALL LETTER G WITH BREVE -->\n" + 
"<!ENTITY Gcedil           \"&#x00122;\" ><!--LATIN CAPITAL LETTER G WITH CEDILLA -->\n" + 
"<!ENTITY Gcirc            \"&#x0011C;\" ><!--LATIN CAPITAL LETTER G WITH CIRCUMFLEX -->\n" + 
"<!ENTITY gcirc            \"&#x0011D;\" ><!--LATIN SMALL LETTER G WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Gdot             \"&#x00120;\" ><!--LATIN CAPITAL LETTER G WITH DOT ABOVE -->\n" + 
"<!ENTITY gdot             \"&#x00121;\" ><!--LATIN SMALL LETTER G WITH DOT ABOVE -->\n" + 
"<!ENTITY Hcirc            \"&#x00124;\" ><!--LATIN CAPITAL LETTER H WITH CIRCUMFLEX -->\n" + 
"<!ENTITY hcirc            \"&#x00125;\" ><!--LATIN SMALL LETTER H WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Hstrok           \"&#x00126;\" ><!--LATIN CAPITAL LETTER H WITH STROKE -->\n" + 
"<!ENTITY hstrok           \"&#x00127;\" ><!--LATIN SMALL LETTER H WITH STROKE -->\n" + 
"<!ENTITY Idot             \"&#x00130;\" ><!--LATIN CAPITAL LETTER I WITH DOT ABOVE -->\n" + 
"<!ENTITY IJlig            \"&#x00132;\" ><!--LATIN CAPITAL LIGATURE IJ -->\n" + 
"<!ENTITY ijlig            \"&#x00133;\" ><!--LATIN SMALL LIGATURE IJ -->\n" + 
"<!ENTITY Imacr            \"&#x0012A;\" ><!--LATIN CAPITAL LETTER I WITH MACRON -->\n" + 
"<!ENTITY imacr            \"&#x0012B;\" ><!--LATIN SMALL LETTER I WITH MACRON -->\n" + 
"<!ENTITY inodot           \"&#x00131;\" ><!--LATIN SMALL LETTER DOTLESS I -->\n" + 
"<!ENTITY Iogon            \"&#x0012E;\" ><!--LATIN CAPITAL LETTER I WITH OGONEK -->\n" + 
"<!ENTITY iogon            \"&#x0012F;\" ><!--LATIN SMALL LETTER I WITH OGONEK -->\n" + 
"<!ENTITY Itilde           \"&#x00128;\" ><!--LATIN CAPITAL LETTER I WITH TILDE -->\n" + 
"<!ENTITY itilde           \"&#x00129;\" ><!--LATIN SMALL LETTER I WITH TILDE -->\n" + 
"<!ENTITY Jcirc            \"&#x00134;\" ><!--LATIN CAPITAL LETTER J WITH CIRCUMFLEX -->\n" + 
"<!ENTITY jcirc            \"&#x00135;\" ><!--LATIN SMALL LETTER J WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Kcedil           \"&#x00136;\" ><!--LATIN CAPITAL LETTER K WITH CEDILLA -->\n" + 
"<!ENTITY kcedil           \"&#x00137;\" ><!--LATIN SMALL LETTER K WITH CEDILLA -->\n" + 
"<!ENTITY kgreen           \"&#x00138;\" ><!--LATIN SMALL LETTER KRA -->\n" + 
"<!ENTITY Lacute           \"&#x00139;\" ><!--LATIN CAPITAL LETTER L WITH ACUTE -->\n" + 
"<!ENTITY lacute           \"&#x0013A;\" ><!--LATIN SMALL LETTER L WITH ACUTE -->\n" + 
"<!ENTITY Lcaron           \"&#x0013D;\" ><!--LATIN CAPITAL LETTER L WITH CARON -->\n" + 
"<!ENTITY lcaron           \"&#x0013E;\" ><!--LATIN SMALL LETTER L WITH CARON -->\n" + 
"<!ENTITY Lcedil           \"&#x0013B;\" ><!--LATIN CAPITAL LETTER L WITH CEDILLA -->\n" + 
"<!ENTITY lcedil           \"&#x0013C;\" ><!--LATIN SMALL LETTER L WITH CEDILLA -->\n" + 
"<!ENTITY Lmidot           \"&#x0013F;\" ><!--LATIN CAPITAL LETTER L WITH MIDDLE DOT -->\n" + 
"<!ENTITY lmidot           \"&#x00140;\" ><!--LATIN SMALL LETTER L WITH MIDDLE DOT -->\n" + 
"<!ENTITY Lstrok           \"&#x00141;\" ><!--LATIN CAPITAL LETTER L WITH STROKE -->\n" + 
"<!ENTITY lstrok           \"&#x00142;\" ><!--LATIN SMALL LETTER L WITH STROKE -->\n" + 
"<!ENTITY Nacute           \"&#x00143;\" ><!--LATIN CAPITAL LETTER N WITH ACUTE -->\n" + 
"<!ENTITY nacute           \"&#x00144;\" ><!--LATIN SMALL LETTER N WITH ACUTE -->\n" + 
"<!ENTITY napos            \"&#x00149;\" ><!--LATIN SMALL LETTER N PRECEDED BY APOSTROPHE -->\n" + 
"<!ENTITY Ncaron           \"&#x00147;\" ><!--LATIN CAPITAL LETTER N WITH CARON -->\n" + 
"<!ENTITY ncaron           \"&#x00148;\" ><!--LATIN SMALL LETTER N WITH CARON -->\n" + 
"<!ENTITY Ncedil           \"&#x00145;\" ><!--LATIN CAPITAL LETTER N WITH CEDILLA -->\n" + 
"<!ENTITY ncedil           \"&#x00146;\" ><!--LATIN SMALL LETTER N WITH CEDILLA -->\n" + 
"<!ENTITY Odblac           \"&#x00150;\" ><!--LATIN CAPITAL LETTER O WITH DOUBLE ACUTE -->\n" + 
"<!ENTITY odblac           \"&#x00151;\" ><!--LATIN SMALL LETTER O WITH DOUBLE ACUTE -->\n" + 
"<!ENTITY OElig            \"&#x00152;\" ><!--LATIN CAPITAL LIGATURE OE -->\n" + 
"<!ENTITY oelig            \"&#x00153;\" ><!--LATIN SMALL LIGATURE OE -->\n" + 
"<!ENTITY Omacr            \"&#x0014C;\" ><!--LATIN CAPITAL LETTER O WITH MACRON -->\n" + 
"<!ENTITY omacr            \"&#x0014D;\" ><!--LATIN SMALL LETTER O WITH MACRON -->\n" + 
"<!ENTITY Racute           \"&#x00154;\" ><!--LATIN CAPITAL LETTER R WITH ACUTE -->\n" + 
"<!ENTITY racute           \"&#x00155;\" ><!--LATIN SMALL LETTER R WITH ACUTE -->\n" + 
"<!ENTITY Rcaron           \"&#x00158;\" ><!--LATIN CAPITAL LETTER R WITH CARON -->\n" + 
"<!ENTITY rcaron           \"&#x00159;\" ><!--LATIN SMALL LETTER R WITH CARON -->\n" + 
"<!ENTITY Rcedil           \"&#x00156;\" ><!--LATIN CAPITAL LETTER R WITH CEDILLA -->\n" + 
"<!ENTITY rcedil           \"&#x00157;\" ><!--LATIN SMALL LETTER R WITH CEDILLA -->\n" + 
"<!ENTITY Sacute           \"&#x0015A;\" ><!--LATIN CAPITAL LETTER S WITH ACUTE -->\n" + 
"<!ENTITY sacute           \"&#x0015B;\" ><!--LATIN SMALL LETTER S WITH ACUTE -->\n" + 
"<!ENTITY Scaron           \"&#x00160;\" ><!--LATIN CAPITAL LETTER S WITH CARON -->\n" + 
"<!ENTITY scaron           \"&#x00161;\" ><!--LATIN SMALL LETTER S WITH CARON -->\n" + 
"<!ENTITY Scedil           \"&#x0015E;\" ><!--LATIN CAPITAL LETTER S WITH CEDILLA -->\n" + 
"<!ENTITY scedil           \"&#x0015F;\" ><!--LATIN SMALL LETTER S WITH CEDILLA -->\n" + 
"<!ENTITY Scirc            \"&#x0015C;\" ><!--LATIN CAPITAL LETTER S WITH CIRCUMFLEX -->\n" + 
"<!ENTITY scirc            \"&#x0015D;\" ><!--LATIN SMALL LETTER S WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Tcaron           \"&#x00164;\" ><!--LATIN CAPITAL LETTER T WITH CARON -->\n" + 
"<!ENTITY tcaron           \"&#x00165;\" ><!--LATIN SMALL LETTER T WITH CARON -->\n" + 
"<!ENTITY Tcedil           \"&#x00162;\" ><!--LATIN CAPITAL LETTER T WITH CEDILLA -->\n" + 
"<!ENTITY tcedil           \"&#x00163;\" ><!--LATIN SMALL LETTER T WITH CEDILLA -->\n" + 
"<!ENTITY Tstrok           \"&#x00166;\" ><!--LATIN CAPITAL LETTER T WITH STROKE -->\n" + 
"<!ENTITY tstrok           \"&#x00167;\" ><!--LATIN SMALL LETTER T WITH STROKE -->\n" + 
"<!ENTITY Ubreve           \"&#x0016C;\" ><!--LATIN CAPITAL LETTER U WITH BREVE -->\n" + 
"<!ENTITY ubreve           \"&#x0016D;\" ><!--LATIN SMALL LETTER U WITH BREVE -->\n" + 
"<!ENTITY Udblac           \"&#x00170;\" ><!--LATIN CAPITAL LETTER U WITH DOUBLE ACUTE -->\n" + 
"<!ENTITY udblac           \"&#x00171;\" ><!--LATIN SMALL LETTER U WITH DOUBLE ACUTE -->\n" + 
"<!ENTITY Umacr            \"&#x0016A;\" ><!--LATIN CAPITAL LETTER U WITH MACRON -->\n" + 
"<!ENTITY umacr            \"&#x0016B;\" ><!--LATIN SMALL LETTER U WITH MACRON -->\n" + 
"<!ENTITY Uogon            \"&#x00172;\" ><!--LATIN CAPITAL LETTER U WITH OGONEK -->\n" + 
"<!ENTITY uogon            \"&#x00173;\" ><!--LATIN SMALL LETTER U WITH OGONEK -->\n" + 
"<!ENTITY Uring            \"&#x0016E;\" ><!--LATIN CAPITAL LETTER U WITH RING ABOVE -->\n" + 
"<!ENTITY uring            \"&#x0016F;\" ><!--LATIN SMALL LETTER U WITH RING ABOVE -->\n" + 
"<!ENTITY Utilde           \"&#x00168;\" ><!--LATIN CAPITAL LETTER U WITH TILDE -->\n" + 
"<!ENTITY utilde           \"&#x00169;\" ><!--LATIN SMALL LETTER U WITH TILDE -->\n" + 
"<!ENTITY Wcirc            \"&#x00174;\" ><!--LATIN CAPITAL LETTER W WITH CIRCUMFLEX -->\n" + 
"<!ENTITY wcirc            \"&#x00175;\" ><!--LATIN SMALL LETTER W WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Ycirc            \"&#x00176;\" ><!--LATIN CAPITAL LETTER Y WITH CIRCUMFLEX -->\n" + 
"<!ENTITY ycirc            \"&#x00177;\" ><!--LATIN SMALL LETTER Y WITH CIRCUMFLEX -->\n" + 
"<!ENTITY Yuml             \"&#x00178;\" ><!--LATIN CAPITAL LETTER Y WITH DIAERESIS -->\n" + 
"<!ENTITY Zacute           \"&#x00179;\" ><!--LATIN CAPITAL LETTER Z WITH ACUTE -->\n" + 
"<!ENTITY zacute           \"&#x0017A;\" ><!--LATIN SMALL LETTER Z WITH ACUTE -->\n" + 
"<!ENTITY Zcaron           \"&#x0017D;\" ><!--LATIN CAPITAL LETTER Z WITH CARON -->\n" + 
"<!ENTITY zcaron           \"&#x0017E;\" ><!--LATIN SMALL LETTER Z WITH CARON -->\n" + 
"<!ENTITY Zdot             \"&#x0017B;\" ><!--LATIN CAPITAL LETTER Z WITH DOT ABOVE -->\n" + 
"<!ENTITY zdot             \"&#x0017C;\" ><!--LATIN SMALL LETTER Z WITH DOT ABOVE -->\n" + 
"\n" + 
"<!--\n" + 
"     File isonum.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isonum.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Numeric and Special Graphic//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isonum.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isonum PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Numeric and Special Graphic//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isonum.ent\"\n" + 
"       >\n" + 
"       %isonum;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY amp              \"&#38;#38;\" ><!--AMPERSAND -->\n" + 
"<!ENTITY apos             \"&#x00027;\" ><!--APOSTROPHE -->\n" + 
"<!ENTITY ast              \"&#x0002A;\" ><!--ASTERISK -->\n" + 
"<!ENTITY brvbar           \"&#x000A6;\" ><!--BROKEN BAR -->\n" + 
"<!ENTITY bsol             \"&#x0005C;\" ><!--REVERSE SOLIDUS -->\n" + 
"<!ENTITY cent             \"&#x000A2;\" ><!--CENT SIGN -->\n" + 
"<!ENTITY colon            \"&#x0003A;\" ><!--COLON -->\n" + 
"<!ENTITY comma            \"&#x0002C;\" ><!--COMMA -->\n" + 
"<!ENTITY commat           \"&#x00040;\" ><!--COMMERCIAL AT -->\n" + 
"<!ENTITY copy             \"&#x000A9;\" ><!--COPYRIGHT SIGN -->\n" + 
"<!ENTITY curren           \"&#x000A4;\" ><!--CURRENCY SIGN -->\n" + 
"<!ENTITY darr             \"&#x02193;\" ><!--DOWNWARDS ARROW -->\n" + 
"<!ENTITY deg              \"&#x000B0;\" ><!--DEGREE SIGN -->\n" + 
"<!ENTITY divide           \"&#x000F7;\" ><!--DIVISION SIGN -->\n" + 
"<!ENTITY dollar           \"&#x00024;\" ><!--DOLLAR SIGN -->\n" + 
"<!ENTITY equals           \"&#x0003D;\" ><!--EQUALS SIGN -->\n" + 
"<!ENTITY excl             \"&#x00021;\" ><!--EXCLAMATION MARK -->\n" + 
"<!ENTITY frac12           \"&#x000BD;\" ><!--VULGAR FRACTION ONE HALF -->\n" + 
"<!ENTITY frac14           \"&#x000BC;\" ><!--VULGAR FRACTION ONE QUARTER -->\n" + 
"<!ENTITY frac18           \"&#x0215B;\" ><!--VULGAR FRACTION ONE EIGHTH -->\n" + 
"<!ENTITY frac34           \"&#x000BE;\" ><!--VULGAR FRACTION THREE QUARTERS -->\n" + 
"<!ENTITY frac38           \"&#x0215C;\" ><!--VULGAR FRACTION THREE EIGHTHS -->\n" + 
"<!ENTITY frac58           \"&#x0215D;\" ><!--VULGAR FRACTION FIVE EIGHTHS -->\n" + 
"<!ENTITY frac78           \"&#x0215E;\" ><!--VULGAR FRACTION SEVEN EIGHTHS -->\n" + 
"<!ENTITY gt               \"&#x0003E;\" ><!--GREATER-THAN SIGN -->\n" + 
"<!ENTITY half             \"&#x000BD;\" ><!--VULGAR FRACTION ONE HALF -->\n" + 
"<!ENTITY horbar           \"&#x02015;\" ><!--HORIZONTAL BAR -->\n" + 
"<!ENTITY hyphen           \"&#x02010;\" ><!--HYPHEN -->\n" + 
"<!ENTITY iexcl            \"&#x000A1;\" ><!--INVERTED EXCLAMATION MARK -->\n" + 
"<!ENTITY iquest           \"&#x000BF;\" ><!--INVERTED QUESTION MARK -->\n" + 
"<!ENTITY laquo            \"&#x000AB;\" ><!--LEFT-POINTING DOUBLE ANGLE QUOTATION MARK -->\n" + 
"<!ENTITY larr             \"&#x02190;\" ><!--LEFTWARDS ARROW -->\n" + 
"<!ENTITY lcub             \"&#x0007B;\" ><!--LEFT CURLY BRACKET -->\n" + 
"<!ENTITY ldquo            \"&#x0201C;\" ><!--LEFT DOUBLE QUOTATION MARK -->\n" + 
"<!ENTITY lowbar           \"&#x0005F;\" ><!--LOW LINE -->\n" + 
"<!ENTITY lpar             \"&#x00028;\" ><!--LEFT PARENTHESIS -->\n" + 
"<!ENTITY lsqb             \"&#x0005B;\" ><!--LEFT SQUARE BRACKET -->\n" + 
"<!ENTITY lsquo            \"&#x02018;\" ><!--LEFT SINGLE QUOTATION MARK -->\n" + 
"<!ENTITY lt               \"&#38;#60;\" ><!--LESS-THAN SIGN -->\n" + 
"<!ENTITY micro            \"&#x000B5;\" ><!--MICRO SIGN -->\n" + 
"<!ENTITY middot           \"&#x000B7;\" ><!--MIDDLE DOT -->\n" + 
"<!ENTITY nbsp             \"&#x000A0;\" ><!--NO-BREAK SPACE -->\n" + 
"<!ENTITY not              \"&#x000AC;\" ><!--NOT SIGN -->\n" + 
"<!ENTITY num              \"&#x00023;\" ><!--NUMBER SIGN -->\n" + 
"<!ENTITY ohm              \"&#x02126;\" ><!--OHM SIGN -->\n" + 
"<!ENTITY ordf             \"&#x000AA;\" ><!--FEMININE ORDINAL INDICATOR -->\n" + 
"<!ENTITY ordm             \"&#x000BA;\" ><!--MASCULINE ORDINAL INDICATOR -->\n" + 
"<!ENTITY para             \"&#x000B6;\" ><!--PILCROW SIGN -->\n" + 
"<!ENTITY percnt           \"&#x00025;\" ><!--PERCENT SIGN -->\n" + 
"<!ENTITY period           \"&#x0002E;\" ><!--FULL STOP -->\n" + 
"<!ENTITY plus             \"&#x0002B;\" ><!--PLUS SIGN -->\n" + 
"<!ENTITY plusmn           \"&#x000B1;\" ><!--PLUS-MINUS SIGN -->\n" + 
"<!ENTITY pound            \"&#x000A3;\" ><!--POUND SIGN -->\n" + 
"<!ENTITY quest            \"&#x0003F;\" ><!--QUESTION MARK -->\n" + 
"<!ENTITY quot             \"&#x00022;\" ><!--QUOTATION MARK -->\n" + 
"<!ENTITY raquo            \"&#x000BB;\" ><!--RIGHT-POINTING DOUBLE ANGLE QUOTATION MARK -->\n" + 
"<!ENTITY rarr             \"&#x02192;\" ><!--RIGHTWARDS ARROW -->\n" + 
"<!ENTITY rcub             \"&#x0007D;\" ><!--RIGHT CURLY BRACKET -->\n" + 
"<!ENTITY rdquo            \"&#x0201D;\" ><!--RIGHT DOUBLE QUOTATION MARK -->\n" + 
"<!ENTITY reg              \"&#x000AE;\" ><!--REGISTERED SIGN -->\n" + 
"<!ENTITY rpar             \"&#x00029;\" ><!--RIGHT PARENTHESIS -->\n" + 
"<!ENTITY rsqb             \"&#x0005D;\" ><!--RIGHT SQUARE BRACKET -->\n" + 
"<!ENTITY rsquo            \"&#x02019;\" ><!--RIGHT SINGLE QUOTATION MARK -->\n" + 
"<!ENTITY sect             \"&#x000A7;\" ><!--SECTION SIGN -->\n" + 
"<!ENTITY semi             \"&#x0003B;\" ><!--SEMICOLON -->\n" + 
"<!ENTITY shy              \"&#x000AD;\" ><!--SOFT HYPHEN -->\n" + 
"<!ENTITY sol              \"&#x0002F;\" ><!--SOLIDUS -->\n" + 
"<!ENTITY sung             \"&#x0266A;\" ><!--EIGHTH NOTE -->\n" + 
"<!ENTITY sup1             \"&#x000B9;\" ><!--SUPERSCRIPT ONE -->\n" + 
"<!ENTITY sup2             \"&#x000B2;\" ><!--SUPERSCRIPT TWO -->\n" + 
"<!ENTITY sup3             \"&#x000B3;\" ><!--SUPERSCRIPT THREE -->\n" + 
"<!ENTITY times            \"&#x000D7;\" ><!--MULTIPLICATION SIGN -->\n" + 
"<!ENTITY trade            \"&#x02122;\" ><!--TRADE MARK SIGN -->\n" + 
"<!ENTITY uarr             \"&#x02191;\" ><!--UPWARDS ARROW -->\n" + 
"<!ENTITY verbar           \"&#x0007C;\" ><!--VERTICAL LINE -->\n" + 
"<!ENTITY yen              \"&#x000A5;\" ><!--YEN SIGN -->\n" + 
"\n" + 
"<!--\n" + 
"     File isopub.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isopub.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES Publishing//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isopub.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isopub PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES Publishing//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isopub.ent\"\n" + 
"       >\n" + 
"       %isopub;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY blank            \"&#x02423;\" ><!--OPEN BOX -->\n" + 
"<!ENTITY blk12            \"&#x02592;\" ><!--MEDIUM SHADE -->\n" + 
"<!ENTITY blk14            \"&#x02591;\" ><!--LIGHT SHADE -->\n" + 
"<!ENTITY blk34            \"&#x02593;\" ><!--DARK SHADE -->\n" + 
"<!ENTITY block            \"&#x02588;\" ><!--FULL BLOCK -->\n" + 
"<!ENTITY bull             \"&#x02022;\" ><!--BULLET -->\n" + 
"<!ENTITY caret            \"&#x02041;\" ><!--CARET INSERTION POINT -->\n" + 
"<!ENTITY check            \"&#x02713;\" ><!--CHECK MARK -->\n" + 
"<!ENTITY cir              \"&#x025CB;\" ><!--WHITE CIRCLE -->\n" + 
"<!ENTITY clubs            \"&#x02663;\" ><!--BLACK CLUB SUIT -->\n" + 
"<!ENTITY copysr           \"&#x02117;\" ><!--SOUND RECORDING COPYRIGHT -->\n" + 
"<!ENTITY cross            \"&#x02717;\" ><!--BALLOT X -->\n" + 
"<!ENTITY Dagger           \"&#x02021;\" ><!--DOUBLE DAGGER -->\n" + 
"<!ENTITY dagger           \"&#x02020;\" ><!--DAGGER -->\n" + 
"<!ENTITY dash             \"&#x02010;\" ><!--HYPHEN -->\n" + 
"<!ENTITY diams            \"&#x02666;\" ><!--BLACK DIAMOND SUIT -->\n" + 
"<!ENTITY dlcrop           \"&#x0230D;\" ><!--BOTTOM LEFT CROP -->\n" + 
"<!ENTITY drcrop           \"&#x0230C;\" ><!--BOTTOM RIGHT CROP -->\n" + 
"<!ENTITY dtri             \"&#x025BF;\" ><!--WHITE DOWN-POINTING SMALL TRIANGLE -->\n" + 
"<!ENTITY dtrif            \"&#x025BE;\" ><!--BLACK DOWN-POINTING SMALL TRIANGLE -->\n" + 
"<!ENTITY emsp             \"&#x02003;\" ><!--EM SPACE -->\n" + 
"<!ENTITY emsp13           \"&#x02004;\" ><!--THREE-PER-EM SPACE -->\n" + 
"<!ENTITY emsp14           \"&#x02005;\" ><!--FOUR-PER-EM SPACE -->\n" + 
"<!ENTITY ensp             \"&#x02002;\" ><!--EN SPACE -->\n" + 
"<!ENTITY female           \"&#x02640;\" ><!--FEMALE SIGN -->\n" + 
"<!ENTITY ffilig           \"&#x0FB03;\" ><!--LATIN SMALL LIGATURE FFI -->\n" + 
"<!ENTITY fflig            \"&#x0FB00;\" ><!--LATIN SMALL LIGATURE FF -->\n" + 
"<!ENTITY ffllig           \"&#x0FB04;\" ><!--LATIN SMALL LIGATURE FFL -->\n" + 
"<!ENTITY filig            \"&#x0FB01;\" ><!--LATIN SMALL LIGATURE FI -->\n" + 
"<!ENTITY flat             \"&#x0266D;\" ><!--MUSIC FLAT SIGN -->\n" + 
"<!ENTITY fllig            \"&#x0FB02;\" ><!--LATIN SMALL LIGATURE FL -->\n" + 
"<!ENTITY frac13           \"&#x02153;\" ><!--VULGAR FRACTION ONE THIRD -->\n" + 
"<!ENTITY frac15           \"&#x02155;\" ><!--VULGAR FRACTION ONE FIFTH -->\n" + 
"<!ENTITY frac16           \"&#x02159;\" ><!--VULGAR FRACTION ONE SIXTH -->\n" + 
"<!ENTITY frac23           \"&#x02154;\" ><!--VULGAR FRACTION TWO THIRDS -->\n" + 
"<!ENTITY frac25           \"&#x02156;\" ><!--VULGAR FRACTION TWO FIFTHS -->\n" + 
"<!ENTITY frac35           \"&#x02157;\" ><!--VULGAR FRACTION THREE FIFTHS -->\n" + 
"<!ENTITY frac45           \"&#x02158;\" ><!--VULGAR FRACTION FOUR FIFTHS -->\n" + 
"<!ENTITY frac56           \"&#x0215A;\" ><!--VULGAR FRACTION FIVE SIXTHS -->\n" + 
"<!ENTITY hairsp           \"&#x0200A;\" ><!--HAIR SPACE -->\n" + 
"<!ENTITY hearts           \"&#x02665;\" ><!--BLACK HEART SUIT -->\n" + 
"<!ENTITY hellip           \"&#x02026;\" ><!--HORIZONTAL ELLIPSIS -->\n" + 
"<!ENTITY hybull           \"&#x02043;\" ><!--HYPHEN BULLET -->\n" + 
"<!ENTITY incare           \"&#x02105;\" ><!--CARE OF -->\n" + 
"<!ENTITY ldquor           \"&#x0201E;\" ><!--DOUBLE LOW-9 QUOTATION MARK -->\n" + 
"<!ENTITY lhblk            \"&#x02584;\" ><!--LOWER HALF BLOCK -->\n" + 
"<!ENTITY loz              \"&#x025CA;\" ><!--LOZENGE -->\n" + 
"<!ENTITY lozf             \"&#x029EB;\" ><!--BLACK LOZENGE -->\n" + 
"<!ENTITY lsquor           \"&#x0201A;\" ><!--SINGLE LOW-9 QUOTATION MARK -->\n" + 
"<!ENTITY ltri             \"&#x025C3;\" ><!--WHITE LEFT-POINTING SMALL TRIANGLE -->\n" + 
"<!ENTITY ltrif            \"&#x025C2;\" ><!--BLACK LEFT-POINTING SMALL TRIANGLE -->\n" + 
"<!ENTITY male             \"&#x02642;\" ><!--MALE SIGN -->\n" + 
"<!ENTITY malt             \"&#x02720;\" ><!--MALTESE CROSS -->\n" + 
"<!ENTITY marker           \"&#x025AE;\" ><!--BLACK VERTICAL RECTANGLE -->\n" + 
"<!ENTITY mdash            \"&#x02014;\" ><!--EM DASH -->\n" + 
"<!ENTITY mldr             \"&#x02026;\" ><!--HORIZONTAL ELLIPSIS -->\n" + 
"<!ENTITY natur            \"&#x0266E;\" ><!--MUSIC NATURAL SIGN -->\n" + 
"<!ENTITY ndash            \"&#x02013;\" ><!--EN DASH -->\n" + 
"<!ENTITY nldr             \"&#x02025;\" ><!--TWO DOT LEADER -->\n" + 
"<!ENTITY numsp            \"&#x02007;\" ><!--FIGURE SPACE -->\n" + 
"<!ENTITY phone            \"&#x0260E;\" ><!--BLACK TELEPHONE -->\n" + 
"<!ENTITY puncsp           \"&#x02008;\" ><!--PUNCTUATION SPACE -->\n" + 
"<!ENTITY rdquor           \"&#x0201D;\" ><!--RIGHT DOUBLE QUOTATION MARK -->\n" + 
"<!ENTITY rect             \"&#x025AD;\" ><!--WHITE RECTANGLE -->\n" + 
"<!ENTITY rsquor           \"&#x02019;\" ><!--RIGHT SINGLE QUOTATION MARK -->\n" + 
"<!ENTITY rtri             \"&#x025B9;\" ><!--WHITE RIGHT-POINTING SMALL TRIANGLE -->\n" + 
"<!ENTITY rtrif            \"&#x025B8;\" ><!--BLACK RIGHT-POINTING SMALL TRIANGLE -->\n" + 
"<!ENTITY rx               \"&#x0211E;\" ><!--PRESCRIPTION TAKE -->\n" + 
"<!ENTITY sext             \"&#x02736;\" ><!--SIX POINTED BLACK STAR -->\n" + 
"<!ENTITY sharp            \"&#x0266F;\" ><!--MUSIC SHARP SIGN -->\n" + 
"<!ENTITY spades           \"&#x02660;\" ><!--BLACK SPADE SUIT -->\n" + 
"<!ENTITY squ              \"&#x025A1;\" ><!--WHITE SQUARE -->\n" + 
"<!ENTITY squf             \"&#x025AA;\" ><!--BLACK SMALL SQUARE -->\n" + 
"<!ENTITY star             \"&#x02606;\" ><!--WHITE STAR -->\n" + 
"<!ENTITY starf            \"&#x02605;\" ><!--BLACK STAR -->\n" + 
"<!ENTITY target           \"&#x02316;\" ><!--POSITION INDICATOR -->\n" + 
"<!ENTITY telrec           \"&#x02315;\" ><!--TELEPHONE RECORDER -->\n" + 
"<!ENTITY thinsp           \"&#x02009;\" ><!--THIN SPACE -->\n" + 
"<!ENTITY uhblk            \"&#x02580;\" ><!--UPPER HALF BLOCK -->\n" + 
"<!ENTITY ulcrop           \"&#x0230F;\" ><!--TOP LEFT CROP -->\n" + 
"<!ENTITY urcrop           \"&#x0230E;\" ><!--TOP RIGHT CROP -->\n" + 
"<!ENTITY utri             \"&#x025B5;\" ><!--WHITE UP-POINTING SMALL TRIANGLE -->\n" + 
"<!ENTITY utrif            \"&#x025B4;\" ><!--BLACK UP-POINTING SMALL TRIANGLE -->\n" + 
"<!ENTITY vellip           \"&#x022EE;\" ><!--VERTICAL ELLIPSIS -->\n" + 
"\n" + 
"<!--\n" + 
"     File isotech.ent produced by the XSL script entities.xsl\n" + 
"     from input data in unicode.xml.\n" + 
"\n" + 
"     Please report any errors to David Carlisle\n" + 
"     via the public W3C list www-math@w3.org.\n" + 
"\n" + 
"     The numeric character values assigned to each entity\n" + 
"     (should) match the Unicode assignments in Unicode 4.0.\n" + 
"\n" + 
"     Entity names in this file are derived from files carrying the\n" + 
"     following notice:\n" + 
"\n" + 
"     (C) International Organization for Standardization 1986\n" + 
"     Permission to copy in any form is granted for use with\n" + 
"     conforming SGML systems and applications as defined in\n" + 
"     ISO 8879, provided this notice is included in all copies.\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!--\n" + 
"     Version: $Id: isotech.ent,v 1.2 2003/12/08 15:14:43 davidc Exp $\n" + 
"\n" + 
"       Public identifier: ISO 8879:1986//ENTITIES General Technical//EN//XML\n" + 
"       System identifier: http://www.w3.org/2003/entities/iso8879/isotech.ent\n" + 
"\n" + 
"     The public identifier should always be used verbatim.\n" + 
"     The system identifier may be changed to suit local requirements.\n" + 
"\n" + 
"     Typical invocation:\n" + 
"\n" + 
"       <!ENTITY % isotech PUBLIC\n" + 
"         \"ISO 8879:1986//ENTITIES General Technical//EN//XML\"\n" + 
"         \"http://www.w3.org/2003/entities/iso8879/isotech.ent\"\n" + 
"       >\n" + 
"       %isotech;\n" + 
"\n" + 
"-->\n" + 
"\n" + 
"<!ENTITY aleph            \"&#x02135;\" ><!--ALEF SYMBOL -->\n" + 
"<!ENTITY and              \"&#x02227;\" ><!--LOGICAL AND -->\n" + 
"<!ENTITY ang90            \"&#x0221F;\" ><!--RIGHT ANGLE -->\n" + 
"<!ENTITY angsph           \"&#x02222;\" ><!--SPHERICAL ANGLE -->\n" + 
"<!ENTITY angst            \"&#x0212B;\" ><!--ANGSTROM SIGN -->\n" + 
"<!ENTITY ap               \"&#x02248;\" ><!--ALMOST EQUAL TO -->\n" + 
"<!ENTITY becaus           \"&#x02235;\" ><!--BECAUSE -->\n" + 
"<!ENTITY bernou           \"&#x0212C;\" ><!--SCRIPT CAPITAL B -->\n" + 
"<!ENTITY bottom           \"&#x022A5;\" ><!--UP TACK -->\n" + 
"<!ENTITY cap              \"&#x02229;\" ><!--INTERSECTION -->\n" + 
"<!ENTITY compfn           \"&#x02218;\" ><!--RING OPERATOR -->\n" + 
"<!ENTITY cong             \"&#x02245;\" ><!--APPROXIMATELY EQUAL TO -->\n" + 
"<!ENTITY conint           \"&#x0222E;\" ><!--CONTOUR INTEGRAL -->\n" + 
"<!ENTITY cup              \"&#x0222A;\" ><!--UNION -->\n" + 
"<!ENTITY Dot              \"&#x000A8;\" ><!--DIAERESIS -->\n" + 
"<!ENTITY DotDot           \" &#x020DC;\" ><!--COMBINING FOUR DOTS ABOVE -->\n" + 
"<!ENTITY equiv            \"&#x02261;\" ><!--IDENTICAL TO -->\n" + 
"<!ENTITY exist            \"&#x02203;\" ><!--THERE EXISTS -->\n" + 
"<!ENTITY fnof             \"&#x00192;\" ><!--LATIN SMALL LETTER F WITH HOOK -->\n" + 
"<!ENTITY forall           \"&#x02200;\" ><!--FOR ALL -->\n" + 
"<!ENTITY ge               \"&#x02265;\" ><!--GREATER-THAN OR EQUAL TO -->\n" + 
"<!ENTITY hamilt           \"&#x0210B;\" ><!--SCRIPT CAPITAL H -->\n" + 
"<!ENTITY iff              \"&#x021D4;\" ><!--LEFT RIGHT DOUBLE ARROW -->\n" + 
"<!ENTITY infin            \"&#x0221E;\" ><!--INFINITY -->\n" + 
"<!ENTITY int              \"&#x0222B;\" ><!--INTEGRAL -->\n" + 
"<!ENTITY isin             \"&#x02208;\" ><!--ELEMENT OF -->\n" + 
"<!ENTITY lagran           \"&#x02112;\" ><!--SCRIPT CAPITAL L -->\n" + 
"<!ENTITY lang             \"&#x02329;\" ><!--LEFT-POINTING ANGLE BRACKET -->\n" + 
"<!ENTITY lArr             \"&#x021D0;\" ><!--LEFTWARDS DOUBLE ARROW -->\n" + 
"<!ENTITY le               \"&#x02264;\" ><!--LESS-THAN OR EQUAL TO -->\n" + 
"<!ENTITY lowast           \"&#x02217;\" ><!--ASTERISK OPERATOR -->\n" + 
"<!ENTITY minus            \"&#x02212;\" ><!--MINUS SIGN -->\n" + 
"<!ENTITY mnplus           \"&#x02213;\" ><!--MINUS-OR-PLUS SIGN -->\n" + 
"<!ENTITY nabla            \"&#x02207;\" ><!--NABLA -->\n" + 
"<!ENTITY ne               \"&#x02260;\" ><!--NOT EQUAL TO -->\n" + 
"<!ENTITY ni               \"&#x0220B;\" ><!--CONTAINS AS MEMBER -->\n" + 
"<!ENTITY notin            \"&#x02209;\" ><!--NOT AN ELEMENT OF -->\n" + 
"<!ENTITY or               \"&#x02228;\" ><!--LOGICAL OR -->\n" + 
"<!ENTITY order            \"&#x02134;\" ><!--SCRIPT SMALL O -->\n" + 
"<!ENTITY par              \"&#x02225;\" ><!--PARALLEL TO -->\n" + 
"<!ENTITY part             \"&#x02202;\" ><!--PARTIAL DIFFERENTIAL -->\n" + 
"<!ENTITY permil           \"&#x02030;\" ><!--PER MILLE SIGN -->\n" + 
"<!ENTITY perp             \"&#x022A5;\" ><!--UP TACK -->\n" + 
"<!ENTITY phmmat           \"&#x02133;\" ><!--SCRIPT CAPITAL M -->\n" + 
"<!ENTITY Prime            \"&#x02033;\" ><!--DOUBLE PRIME -->\n" + 
"<!ENTITY prime            \"&#x02032;\" ><!--PRIME -->\n" + 
"<!ENTITY prop             \"&#x0221D;\" ><!--PROPORTIONAL TO -->\n" + 
"<!ENTITY radic            \"&#x0221A;\" ><!--SQUARE ROOT -->\n" + 
"<!ENTITY rang             \"&#x0232A;\" ><!--RIGHT-POINTING ANGLE BRACKET -->\n" + 
"<!ENTITY rArr             \"&#x021D2;\" ><!--RIGHTWARDS DOUBLE ARROW -->\n" + 
"<!ENTITY sim              \"&#x0223C;\" ><!--TILDE OPERATOR -->\n" + 
"<!ENTITY sime             \"&#x02243;\" ><!--ASYMPTOTICALLY EQUAL TO -->\n" + 
"<!ENTITY square           \"&#x025A1;\" ><!--WHITE SQUARE -->\n" + 
"<!ENTITY sub              \"&#x02282;\" ><!--SUBSET OF -->\n" + 
"<!ENTITY sube             \"&#x02286;\" ><!--SUBSET OF OR EQUAL TO -->\n" + 
"<!ENTITY sup              \"&#x02283;\" ><!--SUPERSET OF -->\n" + 
"<!ENTITY supe             \"&#x02287;\" ><!--SUPERSET OF OR EQUAL TO -->\n" + 
"<!ENTITY tdot             \" &#x020DB;\" ><!--COMBINING THREE DOTS ABOVE -->\n" + 
"<!ENTITY there4           \"&#x02234;\" ><!--THEREFORE -->\n" + 
"<!ENTITY tprime           \"&#x02034;\" ><!--TRIPLE PRIME -->\n" + 
"<!ENTITY Verbar           \"&#x02016;\" ><!--DOUBLE VERTICAL LINE -->\n" + 
"<!ENTITY wedgeq           \"&#x02259;\" ><!--ESTIMATES -->\n" + 
"\n" + 
"<!-- End of DocBook character entity sets module V4.5 ..................... -->\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- DTD modules .......................................................... -->\n" + 
"\n" + 
"<!-- Information pool .............. -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- DocBook XML information pool module V4.5 ............................. -->\n" + 
"<!-- File dbpoolx.mod ..................................................... -->\n" + 
"\n" + 
"<!-- Copyright 1992-2004 HaL Computer Systems, Inc.,\n" + 
"     O'Reilly & Associates, Inc., ArborText, Inc., Fujitsu Software\n" + 
"     Corporation, Norman Walsh, Sun Microsystems, Inc., and the\n" + 
"     Organization for the Advancement of Structured Information\n" + 
"     Standards (OASIS).\n" + 
"\n" + 
"     $Id: dbpoolx.mod 6340 2006-10-03 13:23:24Z nwalsh $\n" + 
"\n" + 
"     Permission to use, copy, modify and distribute the DocBook XML DTD\n" + 
"     and its accompanying documentation for any purpose and without fee\n" + 
"     is hereby granted in perpetuity, provided that the above copyright\n" + 
"     notice and this paragraph appear in all copies.  The copyright\n" + 
"     holders make no representation about the suitability of the DTD for\n" + 
"     any purpose.  It is provided \"as is\" without expressed or implied\n" + 
"     warranty.\n" + 
"\n" + 
"     If you modify the DocBook XML DTD in any way, except for declaring and\n" + 
"     referencing additional sets of general entities and declaring\n" + 
"     additional notations, label your DTD as a variant of DocBook.  See\n" + 
"     the maintenance documentation for more information.\n" + 
"\n" + 
"     Please direct all questions, bug reports, or suggestions for\n" + 
"     changes to the docbook@lists.oasis-open.org mailing list. For more\n" + 
"     information, see http://www.oasis-open.org/docbook/.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- This module contains the definitions for the objects, inline\n" + 
"     elements, and so on that are available to be used as the main\n" + 
"     content of DocBook documents.  Some elements are useful for general\n" + 
"     publishing, and others are useful specifically for computer\n" + 
"     documentation.\n" + 
"\n" + 
"     This module has the following dependencies on other modules:\n" + 
"\n" + 
"     o It assumes that a %notation.class; entity is defined by the\n" + 
"       driver file or other high-level module.  This entity is\n" + 
"       referenced in the NOTATION attributes for the graphic-related and\n" + 
"       ModeSpec elements.\n" + 
"\n" + 
"     o It assumes that an appropriately parameterized table module is\n" + 
"       available for use with the table-related elements.\n" + 
"\n" + 
"     In DTD driver files referring to this module, please use an entity\n" + 
"     declaration that uses the public identifier shown below:\n" + 
"\n" + 
"     <!ENTITY % dbpool PUBLIC\n" + 
"     \"-//OASIS//ELEMENTS DocBook XML Information Pool V4.5//EN\"\n" + 
"     \"dbpoolx.mod\">\n" + 
"     %dbpool;\n" + 
"\n" + 
"     See the documentation for detailed information on the parameter\n" + 
"     entity and module scheme used in DocBook, customizing DocBook and\n" + 
"     planning for interchange, and changes made since the last release\n" + 
"     of DocBook.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Forms entities ....................................................... -->\n" + 
"<!-- These PEs provide the hook by which the forms module can be inserted   -->\n" + 
"<!-- into the DTD. -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- General-purpose semantics entities ................................... -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Entities for module inclusions ....................................... -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Entities for element classes and mixtures ............................ -->\n" + 
"\n" + 
"<!-- \"Ubiquitous\" classes: ndxterm.class and beginpage -->\n" + 
"\n" + 
"<!-- Object-level classes ................................................. -->\n" + 
"\n" + 
"<!-- The DocBook TC may produce an official EBNF module for DocBook. -->\n" + 
"<!-- This PE provides the hook by which it can be inserted into the DTD. -->\n" + 
"\n" + 
"<!-- Character-level classes .............................................. -->\n" + 
"\n" + 
"<!-- The DocBook TC may produce an official EBNF module for DocBook. -->\n" + 
"<!-- This PE provides the hook by which it can be inserted into the DTD. -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Entities for content models .......................................... -->\n" + 
"\n" + 
"<!-- Redeclaration placeholder ............................................ -->\n" + 
"\n" + 
"<!-- For redeclaring entities that are declared after this point while\n" + 
"     retaining their references to the entities that are declared before\n" + 
"     this point -->\n" + 
"\n" + 
"<!-- Object-level mixtures ................................................ -->\n" + 
"\n" + 
"<!--\n" + 
"                      list admn line synp para infm form cmpd gen  desc\n" + 
"Component mixture       X    X    X    X    X    X    X    X    X    X\n" + 
"Sidebar mixture         X    X    X    X    X    X    X    a    X\n" + 
"Footnote mixture        X         X    X    X    X\n" + 
"Example mixture         X         X    X    X    X\n" + 
"Highlights mixture      X    X              X\n" + 
"Paragraph mixture       X         X    X         X\n" + 
"Admonition mixture      X         X    X    X    X    X    b    c\n" + 
"Figure mixture                    X    X         X\n" + 
"Table entry mixture     X    X    X         X    d\n" + 
"Glossary def mixture    X         X    X    X    X         e\n" + 
"Legal notice mixture    X    X    X         X    f\n" + 
"\n" + 
"a. Just Procedure; not Sidebar itself or MsgSet.\n" + 
"b. No MsgSet.\n" + 
"c. No Highlights.\n" + 
"d. Just Graphic; no other informal objects.\n" + 
"e. No Anchor, BridgeHead, or Highlights.\n" + 
"f. Just BlockQuote; no other informal objects.\n" + 
"-->\n" + 
"\n" + 
"<!-- %formal.class; is explicitly excluded from many contexts in which\n" + 
"     paragraphs are used -->\n" + 
"\n" + 
"<!-- Character-level mixtures ............................................. -->\n" + 
"\n" + 
"<!-- sgml.features -->\n" + 
"\n" + 
"<!-- not [sgml.features[ -->\n" + 
"\n" + 
"<!-- ]] not sgml.features -->\n" + 
"\n" + 
"<!--\n" + 
"                    #PCD xref word link cptr base dnfo othr inob (synop)\n" + 
"para.char.mix         X    X    X    X    X    X    X    X    X\n" + 
"title.char.mix        X    X    X    X    X    X    X    X    X\n" + 
"ndxterm.char.mix      X    X    X    X    X    X    X    X    a\n" + 
"cptr.char.mix         X              X    X    X         X    a\n" + 
"smallcptr.char.mix    X                   b                   a\n" + 
"word.char.mix         X         c    X         X         X    a\n" + 
"docinfo.char.mix      X         d    X    b              X    a\n" + 
"\n" + 
"a. Just InlineGraphic; no InlineEquation.\n" + 
"b. Just Replaceable; no other computer terms.\n" + 
"c. Just Emphasis and Trademark; no other word elements.\n" + 
"d. Just Acronym, Emphasis, and Trademark; no other word elements.\n" + 
"-->\n" + 
"\n" + 
"<!--ENTITY % bibliocomponent.mix (see Bibliographic section, below)-->\n" + 
"<!--ENTITY % person.ident.mix (see Bibliographic section, below)-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Entities for attributes and attribute components ..................... -->\n" + 
"\n" + 
"<!-- Effectivity attributes ............................................... -->\n" + 
"\n" + 
"<!-- Arch: Computer or chip architecture to which element applies; no\n" + 
"	default -->\n" + 
"\n" + 
"<!-- Condition: General-purpose effectivity attribute -->\n" + 
"\n" + 
"<!-- Conformance: Standards conformance characteristics -->\n" + 
"\n" + 
"<!-- OS: Operating system to which element applies; no default -->\n" + 
"\n" + 
"<!-- Revision: Editorial revision to which element belongs; no default -->\n" + 
"\n" + 
"<!-- Security: Security classification; no default -->\n" + 
"\n" + 
"<!-- UserLevel: Level of user experience to which element applies; no\n" + 
"	default -->\n" + 
"\n" + 
"<!-- Vendor: Computer vendor to which element applies; no default -->\n" + 
"\n" + 
"<!-- Wordsize: Computer word size (32 bit, 64 bit, etc.); no default -->\n" + 
"\n" + 
"<!-- Common attributes .................................................... -->\n" + 
"\n" + 
"<!-- Id: Unique identifier of element; no default -->\n" + 
"\n" + 
"<!-- Id: Unique identifier of element; a value must be supplied; no\n" + 
"	default -->\n" + 
"\n" + 
"<!-- Lang: Indicator of language in which element is written, for\n" + 
"	translation, character set management, etc.; no default -->\n" + 
"\n" + 
"<!-- Remap: Previous role of element before conversion; no default -->\n" + 
"\n" + 
"<!-- Role: New role of element in local environment; no default -->\n" + 
"\n" + 
"<!-- XRefLabel: Alternate labeling string for XRef text generation;\n" + 
"	default is usually title or other appropriate label text already\n" + 
"	contained in element -->\n" + 
"\n" + 
"<!-- RevisionFlag: Revision status of element; default is that element\n" + 
"	wasn't revised -->\n" + 
"\n" + 
"<!-- dir: Bidirectional override -->\n" + 
"\n" + 
"<!-- xml:base: base URI -->\n" + 
"\n" + 
"<!-- Role is included explicitly on each element -->\n" + 
"\n" + 
"<!-- Role is included explicitly on each element -->\n" + 
"\n" + 
"<!-- Semi-common attributes and other attribute entities .................. -->\n" + 
"\n" + 
"<!-- EntityRef: Name of an external entity containing the content\n" + 
"	of the graphic -->\n" + 
"<!-- FileRef: Filename, qualified by a pathname if desired,\n" + 
"	designating the file containing the content of the graphic -->\n" + 
"<!-- Format: Notation of the element content, if any -->\n" + 
"<!-- SrcCredit: Information about the source of the Graphic -->\n" + 
"<!-- Width: Same as CALS reprowid (desired width) -->\n" + 
"<!-- Depth: Same as CALS reprodep (desired depth) -->\n" + 
"<!-- Align: Same as CALS hplace with 'none' removed; #IMPLIED means\n" + 
"	application-specific -->\n" + 
"<!-- Scale: Conflation of CALS hscale and vscale -->\n" + 
"<!-- Scalefit: Same as CALS scalefit -->\n" + 
"\n" + 
"<!-- Action: Key combination type; default is unspecified if one\n" + 
"	child element, Simul if there is more than one; if value is\n" + 
"	Other, the OtherAction attribute must have a nonempty value -->\n" + 
"<!-- OtherAction: User-defined key combination type -->\n" + 
"\n" + 
"<!-- Label: Identifying number or string; default is usually the\n" + 
"	appropriate number or string autogenerated by a formatter -->\n" + 
"\n" + 
"<!-- xml:space: whitespace treatment -->\n" + 
"\n" + 
"<!-- Format: whether element is assumed to contain significant white\n" + 
"	space -->\n" + 
"\n" + 
"<!-- Linkend: link to related information; no default -->\n" + 
"\n" + 
"<!-- Linkend: required link to related information -->\n" + 
"\n" + 
"<!-- Linkends: link to one or more sets of related information; no\n" + 
"	default -->\n" + 
"\n" + 
"<!-- MoreInfo: whether element's content has an associated RefEntry -->\n" + 
"\n" + 
"<!-- Pagenum: number of page on which element appears; no default -->\n" + 
"\n" + 
"<!-- Status: Editorial or publication status of the element\n" + 
"	it applies to, such as \"in review\" or \"approved for distribution\" -->\n" + 
"\n" + 
"<!-- Width: width of the longest line in the element to which it\n" + 
"	pertains, in number of characters -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Title elements ....................................................... -->\n" + 
"\n" + 
"<!--doc:The text of the title of a section of a document or of a formal block-level element.-->\n" + 
"<!ELEMENT title  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of title.element-->\n" + 
"\n" + 
"<!ATTLIST title\n" + 
"		pagenum	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of title.attlist-->\n" + 
"<!--end of title.module-->\n" + 
"\n" + 
"<!--doc:The abbreviation of a Title.-->\n" + 
"<!ELEMENT titleabbrev  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of titleabbrev.element-->\n" + 
"\n" + 
"<!ATTLIST titleabbrev\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of titleabbrev.attlist-->\n" + 
"<!--end of titleabbrev.module-->\n" + 
"\n" + 
"<!--doc:The subtitle of a document.-->\n" + 
"<!ELEMENT subtitle  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of subtitle.element-->\n" + 
"\n" + 
"<!ATTLIST subtitle\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of subtitle.attlist-->\n" + 
"<!--end of subtitle.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Bibliographic entities and elements .................................. -->\n" + 
"\n" + 
"<!-- The bibliographic elements are typically used in the document\n" + 
"     hierarchy. They do not appear in content models of information\n" + 
"     pool elements.  See also the document information elements,\n" + 
"     below. -->\n" + 
"\n" + 
"<!-- I don't think this is well placed, but it needs to be here because of -->\n" + 
"<!-- the reference to bibliocomponent.mix -->\n" + 
"\n" + 
"<!-- BiblioList ........................ -->\n" + 
"\n" + 
"<!--doc:A wrapper for a set of bibliography entries.-->\n" + 
"<!ELEMENT bibliolist  (blockinfo?, (title, titleabbrev?)?,\n" + 
"                           (biblioentry|bibliomixed)+)>\n" + 
"<!--end of bibliolist.element-->\n" + 
"\n" + 
"<!ATTLIST bibliolist\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bibliolist.attlist-->\n" + 
"<!--end of bibliolist.module-->\n" + 
"\n" + 
"<!--doc:An entry in a Bibliography.-->\n" + 
"<!ELEMENT biblioentry  ((articleinfo | (abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"		))+)\n" + 
"                      >\n" + 
"<!--end of biblioentry.element-->\n" + 
"\n" + 
"<!ATTLIST biblioentry\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of biblioentry.attlist-->\n" + 
"<!--end of biblioentry.module-->\n" + 
"\n" + 
"<!--doc:An entry in a Bibliography.-->\n" + 
"<!ELEMENT bibliomixed  (#PCDATA | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"		 | bibliomset)*\n" + 
"                      >\n" + 
"<!--end of bibliomixed.element-->\n" + 
"\n" + 
"<!ATTLIST bibliomixed\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bibliomixed.attlist-->\n" + 
"<!--end of bibliomixed.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for an Article.-->\n" + 
"<!ELEMENT articleinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"	>\n" + 
"<!--end of articleinfo.element-->\n" + 
"\n" + 
"<!ATTLIST articleinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of articleinfo.attlist-->\n" + 
"<!--end of articleinfo.module-->\n" + 
"\n" + 
"<!--doc:A \"raw\" container for related bibliographic information.-->\n" + 
"<!ELEMENT biblioset  ((abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"		)+)\n" + 
"                      >\n" + 
"<!--end of biblioset.element-->\n" + 
"\n" + 
"<!-- Relation: Relationship of elements contained within BiblioSet -->\n" + 
"\n" + 
"<!ATTLIST biblioset\n" + 
"		relation	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of biblioset.attlist-->\n" + 
"<!--end of biblioset.module-->\n" + 
"\n" + 
"<!--doc:A \"cooked\" container for related bibliographic information.-->\n" + 
"<!ELEMENT bibliomset  (#PCDATA | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"		 | bibliomset)*\n" + 
"                      >\n" + 
"<!--end of bibliomset.element-->\n" + 
"\n" + 
"<!-- Relation: Relationship of elements contained within BiblioMSet -->\n" + 
"\n" + 
"<!ATTLIST bibliomset\n" + 
"		relation	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bibliomset.attlist-->\n" + 
"<!--end of bibliomset.module-->\n" + 
"\n" + 
"<!--doc:Untyped bibliographic information.-->\n" + 
"<!ELEMENT bibliomisc  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of bibliomisc.element-->\n" + 
"\n" + 
"<!ATTLIST bibliomisc\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bibliomisc.attlist-->\n" + 
"<!--end of bibliomisc.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Subject, Keyword, and ITermSet elements .............................. -->\n" + 
"\n" + 
"<!--doc:A set of terms describing the subject matter of a document.-->\n" + 
"<!ELEMENT subjectset  (subject+)>\n" + 
"<!--end of subjectset.element-->\n" + 
"\n" + 
"<!-- Scheme: Controlled vocabulary employed in SubjectTerms -->\n" + 
"\n" + 
"<!ATTLIST subjectset\n" + 
"		scheme		NMTOKEN		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of subjectset.attlist-->\n" + 
"<!--end of subjectset.module-->\n" + 
"\n" + 
"<!--doc:One of a group of terms describing the subject matter of a document.-->\n" + 
"<!ELEMENT subject  (subjectterm+)>\n" + 
"<!--end of subject.element-->\n" + 
"\n" + 
"<!-- Weight: Ranking of this group of SubjectTerms relative\n" + 
"		to others, 0 is low, no highest value specified -->\n" + 
"\n" + 
"<!ATTLIST subject\n" + 
"		weight		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of subject.attlist-->\n" + 
"<!--end of subject.module-->\n" + 
"\n" + 
"<!--doc:A term in a group of terms describing the subject matter of a document.-->\n" + 
"<!ELEMENT subjectterm  (#PCDATA)>\n" + 
"<!--end of subjectterm.element-->\n" + 
"\n" + 
"<!ATTLIST subjectterm\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of subjectterm.attlist-->\n" + 
"<!--end of subjectterm.module-->\n" + 
"<!--end of subjectset.content.module-->\n" + 
"\n" + 
"<!--doc:A set of keywords describing the content of a document.-->\n" + 
"<!ELEMENT keywordset  (keyword+)>\n" + 
"<!--end of keywordset.element-->\n" + 
"\n" + 
"<!ATTLIST keywordset\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of keywordset.attlist-->\n" + 
"<!--end of keywordset.module-->\n" + 
"\n" + 
"<!--doc:One of a set of keywords describing the content of a document.-->\n" + 
"<!ELEMENT keyword  (#PCDATA)>\n" + 
"<!--end of keyword.element-->\n" + 
"\n" + 
"<!ATTLIST keyword\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of keyword.attlist-->\n" + 
"<!--end of keyword.module-->\n" + 
"<!--end of keywordset.content.module-->\n" + 
"\n" + 
"<!--doc:A set of index terms in the meta-information of a document.-->\n" + 
"<!ELEMENT itermset  (indexterm+)>\n" + 
"<!--end of itermset.element-->\n" + 
"\n" + 
"<!ATTLIST itermset\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of itermset.attlist-->\n" + 
"<!--end of itermset.module-->\n" + 
"\n" + 
"<!-- Bibliographic info for \"blocks\" -->\n" + 
"\n" + 
"<!--doc:Meta-information for a block element.-->\n" + 
"<!ELEMENT blockinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"	>\n" + 
"<!--end of blockinfo.element-->\n" + 
"\n" + 
"<!ATTLIST blockinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of blockinfo.attlist-->\n" + 
"<!--end of blockinfo.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Compound (section-ish) elements ...................................... -->\n" + 
"\n" + 
"<!-- Message set ...................... -->\n" + 
"\n" + 
"<!--doc:A detailed set of messages, usually error messages.-->\n" + 
"<!ELEMENT msgset  (blockinfo?, (title, titleabbrev?)?,\n" + 
"                       (msgentry+|simplemsgentry+))>\n" + 
"<!--end of msgset.element-->\n" + 
"\n" + 
"<!ATTLIST msgset\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msgset.attlist-->\n" + 
"<!--end of msgset.module-->\n" + 
"\n" + 
"<!--doc:A wrapper for an entry in a message set.-->\n" + 
"<!ELEMENT msgentry  (msg+, msginfo?, msgexplan*)>\n" + 
"<!--end of msgentry.element-->\n" + 
"\n" + 
"<!ATTLIST msgentry\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msgentry.attlist-->\n" + 
"<!--end of msgentry.module-->\n" + 
"\n" + 
"<!--doc:A wrapper for a simpler entry in a message set.-->\n" + 
"<!ELEMENT simplemsgentry  (msgtext, msgexplan+)>\n" + 
"<!--end of simplemsgentry.element-->\n" + 
"\n" + 
"<!ATTLIST simplemsgentry\n" + 
"		audience	CDATA	#IMPLIED\n" + 
"		level		CDATA	#IMPLIED\n" + 
"		origin		CDATA	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of simplemsgentry.attlist-->\n" + 
"<!--end of simplemsgentry.module-->\n" + 
"\n" + 
"<!--doc:A message in a message set.-->\n" + 
"<!ELEMENT msg  (title?, msgmain, (msgsub | msgrel)*)>\n" + 
"<!--end of msg.element-->\n" + 
"\n" + 
"<!ATTLIST msg\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msg.attlist-->\n" + 
"<!--end of msg.module-->\n" + 
"\n" + 
"<!--doc:The primary component of a message in a message set.-->\n" + 
"<!ELEMENT msgmain  (title?, msgtext)>\n" + 
"<!--end of msgmain.element-->\n" + 
"\n" + 
"<!ATTLIST msgmain\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msgmain.attlist-->\n" + 
"<!--end of msgmain.module-->\n" + 
"\n" + 
"<!--doc:A subcomponent of a message in a message set.-->\n" + 
"<!ELEMENT msgsub  (title?, msgtext)>\n" + 
"<!--end of msgsub.element-->\n" + 
"\n" + 
"<!ATTLIST msgsub\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msgsub.attlist-->\n" + 
"<!--end of msgsub.module-->\n" + 
"\n" + 
"<!--doc:A related component of a message in a message set.-->\n" + 
"<!ELEMENT msgrel  (title?, msgtext)>\n" + 
"<!--end of msgrel.element-->\n" + 
"\n" + 
"<!ATTLIST msgrel\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msgrel.attlist-->\n" + 
"<!--end of msgrel.module-->\n" + 
"\n" + 
"<!-- MsgText (defined in the Inlines section, below)-->\n" + 
"\n" + 
"<!--doc:Information about a message in a message set.-->\n" + 
"<!ELEMENT msginfo  ((msglevel | msgorig | msgaud)*)>\n" + 
"<!--end of msginfo.element-->\n" + 
"\n" + 
"<!ATTLIST msginfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msginfo.attlist-->\n" + 
"<!--end of msginfo.module-->\n" + 
"\n" + 
"<!--doc:The level of importance or severity of a message in a message set.-->\n" + 
"<!ELEMENT msglevel  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of msglevel.element-->\n" + 
"\n" + 
"<!ATTLIST msglevel\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msglevel.attlist-->\n" + 
"<!--end of msglevel.module-->\n" + 
"\n" + 
"<!--doc:The origin of a message in a message set.-->\n" + 
"<!ELEMENT msgorig  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of msgorig.element-->\n" + 
"\n" + 
"<!ATTLIST msgorig\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msgorig.attlist-->\n" + 
"<!--end of msgorig.module-->\n" + 
"\n" + 
"<!--doc:The audience to which a message in a message set is relevant.-->\n" + 
"<!ELEMENT msgaud  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of msgaud.element-->\n" + 
"\n" + 
"<!ATTLIST msgaud\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msgaud.attlist-->\n" + 
"<!--end of msgaud.module-->\n" + 
"\n" + 
"<!--doc:Explanatory material relating to a message in a message set.-->\n" + 
"<!ELEMENT msgexplan  (title?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of msgexplan.element-->\n" + 
"\n" + 
"<!ATTLIST msgexplan\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msgexplan.attlist-->\n" + 
"<!--end of msgexplan.module-->\n" + 
"<!--end of msgset.content.module-->\n" + 
"\n" + 
"<!--doc:A task to be completed.-->\n" + 
"<!ELEMENT task  (blockinfo?,(indexterm )*,\n" + 
"                     (title, titleabbrev?),\n" + 
"                     tasksummary?,\n" + 
"                     taskprerequisites?,\n" + 
"                     procedure,\n" + 
"                     example*,\n" + 
"                     taskrelated?)>\n" + 
"<!--end of task.element-->\n" + 
"\n" + 
"<!ATTLIST task\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of task.attlist-->\n" + 
"<!--end of task.module-->\n" + 
"\n" + 
"<!--doc:A summary of a task.-->\n" + 
"<!ELEMENT tasksummary  (blockinfo?,\n" + 
"                            (title, titleabbrev?)?,\n" + 
"                            (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of tasksummary.element-->\n" + 
"\n" + 
"<!ATTLIST tasksummary\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of tasksummary.attlist-->\n" + 
"<!--end of tasksummary.module-->\n" + 
"\n" + 
"<!--doc:The prerequisites for a task.-->\n" + 
"<!ELEMENT taskprerequisites  (blockinfo?,\n" + 
"                                  (title, titleabbrev?)?,\n" + 
"                                  (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of taskprerequisites.element-->\n" + 
"\n" + 
"<!ATTLIST taskprerequisites\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of taskprerequisites.attlist-->\n" + 
"<!--end of taskprerequisites.module-->\n" + 
"\n" + 
"<!--doc:Information related to a task.-->\n" + 
"<!ELEMENT taskrelated  (blockinfo?,\n" + 
"                            (title, titleabbrev?)?,\n" + 
"                            (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of taskrelated.element-->\n" + 
"\n" + 
"<!ATTLIST taskrelated\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of taskrelated.attlist-->\n" + 
"<!--end of taskrelated.module-->\n" + 
"<!--end of task.content.module-->\n" + 
"\n" + 
"<!-- QandASet ........................ -->\n" + 
"\n" + 
"<!--doc:A question-and-answer set.-->\n" + 
"<!ELEMENT qandaset  (blockinfo?, (title, titleabbrev?)?,\n" + 
"			(calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist            |caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"\n" + 
"		|indexterm\n" + 
"\n" + 
"                )*,\n" + 
"                        (qandadiv+|qandaentry+))>\n" + 
"<!--end of qandaset.element-->\n" + 
"\n" + 
"<!ATTLIST qandaset\n" + 
"		defaultlabel	(qanda|number|none)       #IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"		>\n" + 
"<!--end of qandaset.attlist-->\n" + 
"<!--end of qandaset.module-->\n" + 
"\n" + 
"<!--doc:A titled division in a QandASet.-->\n" + 
"<!ELEMENT qandadiv  (blockinfo?, (title, titleabbrev?)?,\n" + 
"			(calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist            |caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"\n" + 
"		|indexterm\n" + 
"\n" + 
"                )*,\n" + 
"			(qandadiv+|qandaentry+))>\n" + 
"<!--end of qandadiv.element-->\n" + 
"\n" + 
"<!ATTLIST qandadiv\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"		>\n" + 
"<!--end of qandadiv.attlist-->\n" + 
"<!--end of qandadiv.module-->\n" + 
"\n" + 
"<!--doc:A question/answer set within a QandASet.-->\n" + 
"<!ELEMENT qandaentry  (blockinfo?, revhistory?, question, answer*)>\n" + 
"<!--end of qandaentry.element-->\n" + 
"\n" + 
"<!ATTLIST qandaentry\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"		>\n" + 
"<!--end of qandaentry.attlist-->\n" + 
"<!--end of qandaentry.module-->\n" + 
"\n" + 
"<!--doc:A question in a QandASet.-->\n" + 
"<!ELEMENT question  (label?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist            |caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"\n" + 
"		|indexterm\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of question.element-->\n" + 
"\n" + 
"<!ATTLIST question\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of question.attlist-->\n" + 
"<!--end of question.module-->\n" + 
"\n" + 
"<!--doc:An answer to a question posed in a QandASet.-->\n" + 
"<!ELEMENT answer  (label?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist            |caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"\n" + 
"		|indexterm\n" + 
"\n" + 
"                )*, qandaentry*)>\n" + 
"<!--end of answer.element-->\n" + 
"\n" + 
"<!ATTLIST answer\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of answer.attlist-->\n" + 
"<!--end of answer.module-->\n" + 
"\n" + 
"<!--doc:A label on a Question or Answer.-->\n" + 
"<!ELEMENT label  (#PCDATA\n" + 
"					|acronym|emphasis|trademark\n" + 
"		|link|olink|ulink\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of label.element-->\n" + 
"\n" + 
"<!ATTLIST label\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of label.attlist-->\n" + 
"<!--end of label.module-->\n" + 
"<!--end of qandaset.content.module-->\n" + 
"\n" + 
"<!-- Procedure ........................ -->\n" + 
"\n" + 
"<!--doc:A list of operations to be performed in a well-defined sequence.-->\n" + 
"<!ELEMENT procedure  (blockinfo?, (title, titleabbrev?)?,\n" + 
"                          (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*, step+)>\n" + 
"<!--end of procedure.element-->\n" + 
"\n" + 
"<!ATTLIST procedure\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of procedure.attlist-->\n" + 
"<!--end of procedure.module-->\n" + 
"\n" + 
"<!--doc:A unit of action in a procedure.-->\n" + 
"<!ELEMENT step  (title?, (((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+, ((substeps|stepalternatives), (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*)?)\n" + 
"                    | ((substeps|stepalternatives), (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*)))>\n" + 
"<!--end of step.element-->\n" + 
"\n" + 
"<!-- Performance: Whether the Step must be performed -->\n" + 
"<!-- not #REQUIRED! -->\n" + 
"\n" + 
"<!ATTLIST step\n" + 
"		performance	(optional\n" + 
"				|required)	\"required\"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of step.attlist-->\n" + 
"<!--end of step.module-->\n" + 
"\n" + 
"<!--doc:A wrapper for steps that occur within steps in a procedure.-->\n" + 
"<!ELEMENT substeps  (step+)>\n" + 
"<!--end of substeps.element-->\n" + 
"\n" + 
"<!-- Performance: whether entire set of substeps must be performed -->\n" + 
"<!-- not #REQUIRED! -->\n" + 
"\n" + 
"<!ATTLIST substeps\n" + 
"		performance	(optional\n" + 
"				|required)	\"required\"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of substeps.attlist-->\n" + 
"<!--end of substeps.module-->\n" + 
"\n" + 
"<!--doc:Alternative steps in a procedure.-->\n" + 
"<!ELEMENT stepalternatives  (step+)>\n" + 
"<!--end of stepalternatives.element-->\n" + 
"\n" + 
"<!-- Performance: Whether (one of) the alternatives must be performed -->\n" + 
"<!-- not #REQUIRED! -->\n" + 
"\n" + 
"<!ATTLIST stepalternatives\n" + 
"		performance	(optional\n" + 
"				|required)	\"required\"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of stepalternatives.attlist-->\n" + 
"<!--end of stepalternatives.module-->\n" + 
"<!--end of procedure.content.module-->\n" + 
"\n" + 
"<!-- Sidebar .......................... -->\n" + 
"\n" + 
"<!--doc:Meta-information for a Sidebar.-->\n" + 
"<!ELEMENT sidebarinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"	>\n" + 
"<!--end of sidebarinfo.element-->\n" + 
"\n" + 
"<!ATTLIST sidebarinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sidebarinfo.attlist-->\n" + 
"<!--end of sidebarinfo.module-->\n" + 
"\n" + 
"<!--doc:A portion of a document that is isolated from the main narrative flow.-->\n" + 
"<!ELEMENT sidebar  (sidebarinfo?,\n" + 
"                   (title, titleabbrev?)?,\n" + 
"                   (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of sidebar.element-->\n" + 
"\n" + 
"<!ATTLIST sidebar\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sidebar.attlist-->\n" + 
"<!--end of sidebar.module-->\n" + 
"<!--end of sidebar.content.model-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Paragraph-related elements ........................................... -->\n" + 
"\n" + 
"<!--doc:A summary.-->\n" + 
"<!ELEMENT abstract  (title?, (formalpara|para|simpara )+)>\n" + 
"<!--end of abstract.element-->\n" + 
"\n" + 
"<!ATTLIST abstract\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of abstract.attlist-->\n" + 
"<!--end of abstract.module-->\n" + 
"\n" + 
"<!--doc:A short description or note about an author.-->\n" + 
"<!ELEMENT authorblurb  (title?, (formalpara|para|simpara )+)>\n" + 
"<!--end of authorblurb.element-->\n" + 
"\n" + 
"<!ATTLIST authorblurb\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of authorblurb.attlist-->\n" + 
"<!--end of authorblurb.module-->\n" + 
"\n" + 
"<!--doc:A short description or note about a person.-->\n" + 
"<!ELEMENT personblurb  (title?, (formalpara|para|simpara )+)>\n" + 
"<!--end of personblurb.element-->\n" + 
"\n" + 
"<!ATTLIST personblurb\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of personblurb.attlist-->\n" + 
"<!--end of personblurb.module-->\n" + 
"\n" + 
"<!--doc:A quotation set off from the main text.-->\n" + 
"<!ELEMENT blockquote  (blockinfo?, title?, attribution?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)\n" + 
"                      >\n" + 
"<!--end of blockquote.element-->\n" + 
"\n" + 
"<!ATTLIST blockquote\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of blockquote.attlist-->\n" + 
"<!--end of blockquote.module-->\n" + 
"\n" + 
"<!--doc:The source of a block quote or epigraph.-->\n" + 
"<!ELEMENT attribution  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of attribution.element-->\n" + 
"\n" + 
"<!ATTLIST attribution\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of attribution.attlist-->\n" + 
"<!--end of attribution.module-->\n" + 
"\n" + 
"<!--doc:A free-floating heading.-->\n" + 
"<!ELEMENT bridgehead  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of bridgehead.element-->\n" + 
"\n" + 
"<!-- Renderas: Indicates the format in which the BridgeHead\n" + 
"		should appear -->\n" + 
"\n" + 
"<!ATTLIST bridgehead\n" + 
"		renderas	(other\n" + 
"				|sect1\n" + 
"				|sect2\n" + 
"				|sect3\n" + 
"				|sect4\n" + 
"				|sect5)		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bridgehead.attlist-->\n" + 
"<!--end of bridgehead.module-->\n" + 
"\n" + 
"<!--doc:A remark (or comment) intended for presentation in a draft manuscript.-->\n" + 
"<!ELEMENT remark  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*\n" + 
"                      >\n" + 
"<!--end of remark.element-->\n" + 
"\n" + 
"<!ATTLIST remark\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of remark.attlist-->\n" + 
"<!--end of remark.module-->\n" + 
"\n" + 
"<!--doc:A short inscription at the beginning of a document or component.-->\n" + 
"<!ELEMENT epigraph  (attribution?, ((formalpara|para|simpara )|literallayout)+)>\n" + 
"<!--end of epigraph.element-->\n" + 
"\n" + 
"<!ATTLIST epigraph\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of epigraph.attlist-->\n" + 
"<!-- Attribution (defined above)-->\n" + 
"<!--end of epigraph.module-->\n" + 
"\n" + 
"<!--doc:A footnote.-->\n" + 
"<!ELEMENT footnote  ((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		)+)\n" + 
"                      >\n" + 
"<!--end of footnote.element-->\n" + 
"\n" + 
"<!ATTLIST footnote\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of footnote.attlist-->\n" + 
"<!--end of footnote.module-->\n" + 
"\n" + 
"<!--doc:A summary of the main points of the discussed component.-->\n" + 
"<!ELEMENT highlights  ((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|formalpara|para|simpara\n" + 
"		|indexterm\n" + 
"		)+)\n" + 
"                      >\n" + 
"<!--end of highlights.element-->\n" + 
"\n" + 
"<!ATTLIST highlights\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of highlights.attlist-->\n" + 
"<!--end of highlights.module-->\n" + 
"\n" + 
"<!--doc:A paragraph with a title.-->\n" + 
"<!ELEMENT formalpara  (title, (indexterm )*, para)>\n" + 
"<!--end of formalpara.element-->\n" + 
"\n" + 
"<!ATTLIST formalpara\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of formalpara.attlist-->\n" + 
"<!--end of formalpara.module-->\n" + 
"\n" + 
"<!--doc:A paragraph.-->\n" + 
"<!ELEMENT para  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		 | calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist            |caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot\n" + 
"					|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table\n" + 
"		)*>\n" + 
"<!--end of para.element-->\n" + 
"\n" + 
"<!ATTLIST para\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of para.attlist-->\n" + 
"<!--end of para.module-->\n" + 
"\n" + 
"<!--doc:A paragraph that contains only text and inline markup, no block elements.-->\n" + 
"<!ELEMENT simpara  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of simpara.element-->\n" + 
"\n" + 
"<!ATTLIST simpara\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of simpara.attlist-->\n" + 
"<!--end of simpara.module-->\n" + 
"\n" + 
"<!--doc:A note of caution.-->\n" + 
"<!ELEMENT caution  (title?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure|sidebar\n" + 
"		|anchor|bridgehead|remark\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)\n" + 
"                      >\n" + 
"<!--end of caution.element-->\n" + 
"\n" + 
"<!ATTLIST caution\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of caution.attlist-->\n" + 
"\n" + 
"<!--doc:An admonition set off from the text.-->\n" + 
"<!ELEMENT important  (title?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure|sidebar\n" + 
"		|anchor|bridgehead|remark\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)\n" + 
"                      >\n" + 
"<!--end of important.element-->\n" + 
"\n" + 
"<!ATTLIST important\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of important.attlist-->\n" + 
"\n" + 
"<!--doc:A message set off from the text.-->\n" + 
"<!ELEMENT note  (title?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure|sidebar\n" + 
"		|anchor|bridgehead|remark\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)\n" + 
"                      >\n" + 
"<!--end of note.element-->\n" + 
"\n" + 
"<!ATTLIST note\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of note.attlist-->\n" + 
"\n" + 
"<!--doc:A suggestion to the user, set off from the text.-->\n" + 
"<!ELEMENT tip  (title?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure|sidebar\n" + 
"		|anchor|bridgehead|remark\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)\n" + 
"                      >\n" + 
"<!--end of tip.element-->\n" + 
"\n" + 
"<!ATTLIST tip\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of tip.attlist-->\n" + 
"\n" + 
"<!--doc:An admonition set off from the text.-->\n" + 
"<!ELEMENT warning  (title?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure|sidebar\n" + 
"		|anchor|bridgehead|remark\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)\n" + 
"                      >\n" + 
"<!--end of warning.element-->\n" + 
"\n" + 
"<!ATTLIST warning\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of warning.attlist-->\n" + 
"\n" + 
"<!--end of admon.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Lists ................................................................ -->\n" + 
"\n" + 
"<!-- GlossList ........................ -->\n" + 
"\n" + 
"<!--doc:A wrapper for a set of GlossEntrys.-->\n" + 
"<!ELEMENT glosslist  (blockinfo?, (title, titleabbrev?)?, glossentry+)>\n" + 
"<!--end of glosslist.element-->\n" + 
"\n" + 
"<!ATTLIST glosslist\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of glosslist.attlist-->\n" + 
"<!--end of glosslist.module-->\n" + 
"\n" + 
"<!--doc:An entry in a Glossary or GlossList.-->\n" + 
"<!ELEMENT glossentry  (glossterm, acronym?, abbrev?,\n" + 
"                      (indexterm )*,\n" + 
"                      revhistory?, (glosssee|glossdef+))>\n" + 
"<!--end of glossentry.element-->\n" + 
"\n" + 
"<!-- SortAs: String by which the GlossEntry is to be sorted\n" + 
"		(alphabetized) in lieu of its proper content -->\n" + 
"\n" + 
"<!ATTLIST glossentry\n" + 
"		sortas		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of glossentry.attlist-->\n" + 
"<!--end of glossentry.module-->\n" + 
"\n" + 
"<!-- GlossTerm (defined in the Inlines section, below)-->\n" + 
"\n" + 
"<!--doc:A definition in a GlossEntry.-->\n" + 
"<!ELEMENT glossdef  ((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table\n" + 
"		|remark\n" + 
"		|indexterm         |beginpage\n" + 
"		)+, glossseealso*)>\n" + 
"<!--end of glossdef.element-->\n" + 
"\n" + 
"<!-- Subject: List of subjects; keywords for the definition -->\n" + 
"\n" + 
"<!ATTLIST glossdef\n" + 
"		subject		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of glossdef.attlist-->\n" + 
"<!--end of glossdef.module-->\n" + 
"\n" + 
"<!--doc:A cross-reference from one GlossEntry to another.-->\n" + 
"<!ELEMENT glosssee  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of glosssee.element-->\n" + 
"\n" + 
"<!-- OtherTerm: Reference to the GlossEntry whose GlossTerm\n" + 
"		should be displayed at the point of the GlossSee -->\n" + 
"\n" + 
"<!ATTLIST glosssee\n" + 
"		otherterm	IDREF		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of glosssee.attlist-->\n" + 
"<!--end of glosssee.module-->\n" + 
"\n" + 
"<!--doc:A cross-reference from one GlossEntry to another.-->\n" + 
"<!ELEMENT glossseealso  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of glossseealso.element-->\n" + 
"\n" + 
"<!-- OtherTerm: Reference to the GlossEntry whose GlossTerm\n" + 
"		should be displayed at the point of the GlossSeeAlso -->\n" + 
"\n" + 
"<!ATTLIST glossseealso\n" + 
"		otherterm	IDREF		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of glossseealso.attlist-->\n" + 
"<!--end of glossseealso.module-->\n" + 
"<!--end of glossentry.content.module-->\n" + 
"\n" + 
"<!-- ItemizedList and OrderedList ..... -->\n" + 
"\n" + 
"<!--doc:A list in which each entry is marked with a bullet or other dingbat.-->\n" + 
"<!ELEMENT itemizedlist  (blockinfo?, (title, titleabbrev?)?,\n" + 
" 			    (                  	 caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*, listitem+)>\n" + 
"\n" + 
"<!--end of itemizedlist.element-->\n" + 
"\n" + 
"<!-- Spacing: Whether the vertical space in the list should be\n" + 
"		compressed -->\n" + 
"<!-- Mark: Keyword, e.g., bullet, dash, checkbox, none;\n" + 
"		list of keywords and defaults are implementation specific -->\n" + 
"\n" + 
"<!ATTLIST itemizedlist\n" + 
"		spacing		(normal\n" + 
"				|compact)	#IMPLIED\n" + 
"		mark		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of itemizedlist.attlist-->\n" + 
"<!--end of itemizedlist.module-->\n" + 
"\n" + 
"<!--doc:A list in which each entry is marked with a sequentially incremented label.-->\n" + 
"<!ELEMENT orderedlist  (blockinfo?, (title, titleabbrev?)?,\n" + 
" 			    (                  	 caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*, listitem+)>\n" + 
"\n" + 
"<!--end of orderedlist.element-->\n" + 
"\n" + 
"<!-- Numeration: Style of ListItem numbered; default is expected\n" + 
"		to be Arabic -->\n" + 
"<!-- InheritNum: Specifies for a nested list that the numbering\n" + 
"		of ListItems should include the number of the item\n" + 
"		within which they are nested (e.g., 1a and 1b within 1,\n" + 
"		rather than a and b) -->\n" + 
"<!-- Continuation: Where list numbering begins afresh (Restarts,\n" + 
"		the default) or continues that of the immediately preceding\n" + 
"		list (Continues) -->\n" + 
"<!-- Spacing: Whether the vertical space in the list should be\n" + 
"		compressed -->\n" + 
"\n" + 
"<!ATTLIST orderedlist\n" + 
"		numeration	(arabic\n" + 
"				|upperalpha\n" + 
"				|loweralpha\n" + 
"				|upperroman\n" + 
"				|lowerroman)	#IMPLIED\n" + 
"		inheritnum	(inherit\n" + 
"				|ignore)	\"ignore\"\n" + 
"		continuation	(continues\n" + 
"				|restarts)	\"restarts\"\n" + 
"		spacing		(normal\n" + 
"				|compact)	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of orderedlist.attlist-->\n" + 
"<!--end of orderedlist.module-->\n" + 
"\n" + 
"<!--doc:A wrapper for the elements of a list item.-->\n" + 
"<!ELEMENT listitem  ((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of listitem.element-->\n" + 
"\n" + 
"<!-- Override: Indicates the mark to be used for this ListItem\n" + 
"		instead of the default mark or the mark specified by\n" + 
"		the Mark attribute on the enclosing ItemizedList -->\n" + 
"\n" + 
"<!ATTLIST listitem\n" + 
"		override	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of listitem.attlist-->\n" + 
"<!--end of listitem.module-->\n" + 
"\n" + 
"<!-- SegmentedList .................... -->\n" + 
"\n" + 
"<!--doc:A segmented list, a list of sets of elements.-->\n" + 
"<!ELEMENT segmentedlist  ((title, titleabbrev?)?,\n" + 
"                         segtitle+,\n" + 
"                         seglistitem+)>\n" + 
"<!--end of segmentedlist.element-->\n" + 
"\n" + 
"<!ATTLIST segmentedlist\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of segmentedlist.attlist-->\n" + 
"<!--end of segmentedlist.module-->\n" + 
"\n" + 
"<!--doc:The title of an element of a list item in a segmented list.-->\n" + 
"<!ELEMENT segtitle  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of segtitle.element-->\n" + 
"\n" + 
"<!ATTLIST segtitle\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of segtitle.attlist-->\n" + 
"<!--end of segtitle.module-->\n" + 
"\n" + 
"<!--doc:A list item in a segmented list.-->\n" + 
"<!ELEMENT seglistitem  (seg+)>\n" + 
"<!--end of seglistitem.element-->\n" + 
"\n" + 
"<!ATTLIST seglistitem\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of seglistitem.attlist-->\n" + 
"<!--end of seglistitem.module-->\n" + 
"\n" + 
"<!--doc:An element of a list item in a segmented list.-->\n" + 
"<!ELEMENT seg  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of seg.element-->\n" + 
"\n" + 
"<!ATTLIST seg\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of seg.attlist-->\n" + 
"<!--end of seg.module-->\n" + 
"<!--end of segmentedlist.content.module-->\n" + 
"\n" + 
"<!-- SimpleList ....................... -->\n" + 
"\n" + 
"<!--doc:An undecorated list of single words or short phrases.-->\n" + 
"<!ELEMENT simplelist  (member+)>\n" + 
"<!--end of simplelist.element-->\n" + 
"\n" + 
"<!-- Columns: The number of columns the array should contain -->\n" + 
"<!-- Type: How the Members of the SimpleList should be\n" + 
"		formatted: Inline (members separated with commas etc.\n" + 
"		inline), Vert (top to bottom in n Columns), or Horiz (in\n" + 
"		the direction of text flow) in n Columns.  If Column\n" + 
"		is 1 or implied, Type=Vert and Type=Horiz give the same\n" + 
"		results. -->\n" + 
"\n" + 
"<!ATTLIST simplelist\n" + 
"		columns		CDATA		#IMPLIED\n" + 
"		type		(inline\n" + 
"				|vert\n" + 
"				|horiz)		\"vert\"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of simplelist.attlist-->\n" + 
"<!--end of simplelist.module-->\n" + 
"\n" + 
"<!--doc:An element of a simple list.-->\n" + 
"<!ELEMENT member  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of member.element-->\n" + 
"\n" + 
"<!ATTLIST member\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of member.attlist-->\n" + 
"<!--end of member.module-->\n" + 
"<!--end of simplelist.content.module-->\n" + 
"\n" + 
"<!-- VariableList ..................... -->\n" + 
"\n" + 
"<!--doc:A list in which each entry is composed of a set of one or more terms and an associated description.-->\n" + 
"<!ELEMENT variablelist  (blockinfo?, (title, titleabbrev?)?,\n" + 
" 			    (                  	 caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*, varlistentry+)>\n" + 
"<!--end of variablelist.element-->\n" + 
"\n" + 
"<!-- TermLength: Length beyond which the presentation engine\n" + 
"		may consider the Term too long and select an alternate\n" + 
"		presentation of the Term and, or, its associated ListItem. -->\n" + 
"\n" + 
"<!ATTLIST variablelist\n" + 
"		termlength	CDATA		#IMPLIED\n" + 
"		spacing		(normal\n" + 
"				|compact)	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of variablelist.attlist-->\n" + 
"<!--end of variablelist.module-->\n" + 
"\n" + 
"<!--doc:A wrapper for a set of terms and the associated description in a variable list.-->\n" + 
"<!ELEMENT varlistentry  (term+, listitem)>\n" + 
"<!--end of varlistentry.element-->\n" + 
"\n" + 
"<!ATTLIST varlistentry\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of varlistentry.attlist-->\n" + 
"<!--end of varlistentry.module-->\n" + 
"\n" + 
"<!--doc:The word or phrase being defined or described in a variable list.-->\n" + 
"<!ELEMENT term  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of term.element-->\n" + 
"\n" + 
"<!ATTLIST term\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of term.attlist-->\n" + 
"<!--end of term.module-->\n" + 
"\n" + 
"<!-- ListItem (defined above)-->\n" + 
"<!--end of variablelist.content.module-->\n" + 
"\n" + 
"<!-- CalloutList ...................... -->\n" + 
"\n" + 
"<!--doc:A list of Callouts.-->\n" + 
"<!ELEMENT calloutlist  ((title, titleabbrev?)?, callout+)>\n" + 
"<!--end of calloutlist.element-->\n" + 
"\n" + 
"<!ATTLIST calloutlist\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of calloutlist.attlist-->\n" + 
"<!--end of calloutlist.module-->\n" + 
"\n" + 
"<!--doc:A &ldquo;called out&rdquo; description of a marked Area.-->\n" + 
"<!ELEMENT callout  ((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of callout.element-->\n" + 
"\n" + 
"<!-- AreaRefs: IDs of one or more Areas or AreaSets described\n" + 
"		by this Callout -->\n" + 
"\n" + 
"<!ATTLIST callout\n" + 
"		arearefs	IDREFS		#REQUIRED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of callout.attlist-->\n" + 
"<!--end of callout.module-->\n" + 
"<!--end of calloutlist.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Objects .............................................................. -->\n" + 
"\n" + 
"<!-- Examples etc. .................... -->\n" + 
"\n" + 
"<!--doc:A formal example, with a title.-->\n" + 
"<!ELEMENT example  (blockinfo?, (title, titleabbrev?), (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|indexterm         |beginpage\n" + 
"		|procedure\n" + 
"\n" + 
"                )+)\n" + 
"		>\n" + 
"<!--end of example.element-->\n" + 
"\n" + 
"<!ATTLIST example\n" + 
"		floatstyle	CDATA			#IMPLIED\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		width		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of example.attlist-->\n" + 
"<!--end of example.module-->\n" + 
"\n" + 
"<!--doc:A displayed example without a title.-->\n" + 
"<!ELEMENT informalexample  (blockinfo?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|indexterm         |beginpage\n" + 
"		|procedure\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of informalexample.element-->\n" + 
"\n" + 
"<!ATTLIST informalexample\n" + 
"		floatstyle	CDATA			#IMPLIED\n" + 
"		width		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of informalexample.attlist-->\n" + 
"<!--end of informalexample.module-->\n" + 
"\n" + 
"<!--doc:A program listing with associated areas used in callouts.-->\n" + 
"<!ELEMENT programlistingco  (areaspec, programlisting, calloutlist*)>\n" + 
"<!--end of programlistingco.element-->\n" + 
"\n" + 
"<!ATTLIST programlistingco\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of programlistingco.attlist-->\n" + 
"<!-- CalloutList (defined above in Lists)-->\n" + 
"<!--end of informalexample.module-->\n" + 
"\n" + 
"<!--doc:A collection of regions in a graphic or code example.-->\n" + 
"<!ELEMENT areaspec  ((area|areaset)+)>\n" + 
"<!--end of areaspec.element-->\n" + 
"\n" + 
"<!-- Units: global unit of measure in which coordinates in\n" + 
"		this spec are expressed:\n" + 
"\n" + 
"		- CALSPair \"x1,y1 x2,y2\": lower-left and upper-right\n" + 
"		coordinates in a rectangle describing repro area in which\n" + 
"		graphic is placed, where X and Y dimensions are each some\n" + 
"		number 0..10000 (taken from CALS graphic attributes)\n" + 
"\n" + 
"		- LineColumn \"line column\": line number and column number\n" + 
"		at which to start callout text in \"linespecific\" content\n" + 
"\n" + 
"		- LineRange \"startline endline\": whole lines from startline\n" + 
"		to endline in \"linespecific\" content\n" + 
"\n" + 
"		- LineColumnPair \"line1 col1 line2 col2\": starting and ending\n" + 
"		points of area in \"linespecific\" content that starts at\n" + 
"		first position and ends at second position (including the\n" + 
"		beginnings of any intervening lines)\n" + 
"\n" + 
"		- Other: directive to look at value of OtherUnits attribute\n" + 
"		to get implementation-specific keyword\n" + 
"\n" + 
"		The default is implementation-specific; usually dependent on\n" + 
"		the parent element (GraphicCO gets CALSPair, ProgramListingCO\n" + 
"		and ScreenCO get LineColumn) -->\n" + 
"<!-- OtherUnits: User-defined units -->\n" + 
"\n" + 
"<!ATTLIST areaspec\n" + 
"		units		(calspair\n" + 
"				|linecolumn\n" + 
"				|linerange\n" + 
"				|linecolumnpair\n" + 
"				|other)		#IMPLIED\n" + 
"		otherunits	NMTOKEN		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of areaspec.attlist-->\n" + 
"<!--end of areaspec.module-->\n" + 
"\n" + 
"<!--doc:A region defined for a Callout in a graphic or code example.-->\n" + 
"<!ELEMENT area  EMPTY>\n" + 
"<!--end of area.element-->\n" + 
"\n" + 
"<!-- bug number/symbol override or initialization -->\n" + 
"<!-- to any related information -->\n" + 
"<!-- Units: unit of measure in which coordinates in this\n" + 
"		area are expressed; inherits from AreaSet and AreaSpec -->\n" + 
"<!-- OtherUnits: User-defined units -->\n" + 
"\n" + 
"<!ATTLIST area\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		linkends	IDREFS		#IMPLIED\n" + 
"		units		(calspair\n" + 
"				|linecolumn\n" + 
"				|linerange\n" + 
"				|linecolumnpair\n" + 
"				|other)		#IMPLIED\n" + 
"		otherunits	NMTOKEN		#IMPLIED\n" + 
"		coords		CDATA		#REQUIRED\n" + 
"		id		ID		#REQUIRED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of area.attlist-->\n" + 
"<!--end of area.module-->\n" + 
"\n" + 
"<!--doc:A set of related areas in a graphic or code example.-->\n" + 
"<!ELEMENT areaset  (area+)>\n" + 
"<!--end of areaset.element-->\n" + 
"\n" + 
"<!-- bug number/symbol override or initialization -->\n" + 
"<!-- Units: unit of measure in which coordinates in this\n" + 
"		area are expressed; inherits from AreaSpec -->\n" + 
"\n" + 
"<!ATTLIST areaset\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		units		(calspair\n" + 
"				|linecolumn\n" + 
"				|linerange\n" + 
"				|linecolumnpair\n" + 
"				|other)		#IMPLIED\n" + 
"		otherunits	NMTOKEN		#IMPLIED\n" + 
"		coords		CDATA		#REQUIRED\n" + 
"		id		ID		#REQUIRED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of areaset.attlist-->\n" + 
"<!--end of areaset.module-->\n" + 
"<!--end of areaspec.content.module-->\n" + 
"\n" + 
"<!--doc:A literal listing of all or part of a program.-->\n" + 
"<!ELEMENT programlisting  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		|co|coref|lineannotation|textobject)*>\n" + 
"<!--end of programlisting.element-->\n" + 
"\n" + 
"<!ATTLIST programlisting\n" + 
"		width		CDATA		#IMPLIED\n" + 
"		format		NOTATION\n" + 
"			(linespecific)	'linespecific'\n" + 
"         xml:space	(preserve)		#IMPLIED\n" + 
"         linenumbering	(numbered|unnumbered) 	#IMPLIED\n" + 
"         continuation	(continues|restarts)	#IMPLIED\n" + 
"         startinglinenumber	CDATA		#IMPLIED\n" + 
"         language	CDATA			#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of programlisting.attlist-->\n" + 
"<!--end of programlisting.module-->\n" + 
"\n" + 
"<!--doc:A block of text in which line breaks and white space are to be reproduced faithfully.-->\n" + 
"<!ELEMENT literallayout  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		|co|coref|textobject|lineannotation)*>\n" + 
"<!--end of literallayout.element-->\n" + 
"\n" + 
"<!ATTLIST literallayout\n" + 
"		width		CDATA		#IMPLIED\n" + 
"		format		NOTATION\n" + 
"			(linespecific)	'linespecific'\n" + 
"         xml:space	(preserve)		#IMPLIED\n" + 
"         linenumbering	(numbered|unnumbered) 	#IMPLIED\n" + 
"         continuation	(continues|restarts)	#IMPLIED\n" + 
"         startinglinenumber	CDATA		#IMPLIED\n" + 
"         language	CDATA			#IMPLIED\n" + 
"		class	(monospaced|normal)	\"normal\"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of literallayout.attlist-->\n" + 
"<!-- LineAnnotation (defined in the Inlines section, below)-->\n" + 
"<!--end of literallayout.module-->\n" + 
"\n" + 
"<!--doc:A screen with associated areas used in callouts.-->\n" + 
"<!ELEMENT screenco  (areaspec, screen, calloutlist*)>\n" + 
"<!--end of screenco.element-->\n" + 
"\n" + 
"<!ATTLIST screenco\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of screenco.attlist-->\n" + 
"<!-- AreaSpec (defined above)-->\n" + 
"<!-- CalloutList (defined above in Lists)-->\n" + 
"<!--end of screenco.module-->\n" + 
"\n" + 
"<!--doc:Text that a user sees or might see on a computer screen.-->\n" + 
"<!ELEMENT screen  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		|co|coref|textobject|lineannotation)*>\n" + 
"<!--end of screen.element-->\n" + 
"\n" + 
"<!ATTLIST screen\n" + 
"		width		CDATA		#IMPLIED\n" + 
"		format		NOTATION\n" + 
"			(linespecific)	'linespecific'\n" + 
"         xml:space	(preserve)		#IMPLIED\n" + 
"         linenumbering	(numbered|unnumbered) 	#IMPLIED\n" + 
"         continuation	(continues|restarts)	#IMPLIED\n" + 
"         startinglinenumber	CDATA		#IMPLIED\n" + 
"         language	CDATA			#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of screen.attlist-->\n" + 
"<!--end of screen.module-->\n" + 
"\n" + 
"<!--doc:A representation of what the user sees or might see on a computer screen.-->\n" + 
"<!ELEMENT screenshot  (screeninfo?,\n" + 
"                      (graphic|graphicco\n" + 
"                      |mediaobject|mediaobjectco))>\n" + 
"<!--end of screenshot.element-->\n" + 
"\n" + 
"<!ATTLIST screenshot\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of screenshot.attlist-->\n" + 
"<!--end of screenshot.module-->\n" + 
"\n" + 
"<!--doc:Information about how a screen shot was produced.-->\n" + 
"<!ELEMENT screeninfo  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*\n" + 
"		>\n" + 
"<!--end of screeninfo.element-->\n" + 
"\n" + 
"<!ATTLIST screeninfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of screeninfo.attlist-->\n" + 
"<!--end of screeninfo.module-->\n" + 
"<!--end of screenshot.content.module-->\n" + 
"\n" + 
"<!-- Figures etc. ..................... -->\n" + 
"\n" + 
"<!--doc:A formal figure, generally an illustration, with a title.-->\n" + 
"<!ELEMENT figure  (blockinfo?, (title, titleabbrev?),\n" + 
"                       (literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"					|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                 | link|olink|ulink )+)>\n" + 
"<!--end of figure.element-->\n" + 
"\n" + 
"<!-- Float: Whether the Figure is supposed to be rendered\n" + 
"		where convenient (yes (1) value) or at the place it occurs\n" + 
"		in the text (no (0) value, the default) -->\n" + 
"\n" + 
"<!ATTLIST figure\n" + 
"		float		CDATA	'0'\n" + 
"		floatstyle	CDATA			#IMPLIED\n" + 
"		pgwide      	CDATA       #IMPLIED\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of figure.attlist-->\n" + 
"<!--end of figure.module-->\n" + 
"\n" + 
"<!--doc:A untitled figure.-->\n" + 
"<!ELEMENT informalfigure  (blockinfo?, (literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"					|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                 | link|olink|ulink )+)>\n" + 
"<!--end of informalfigure.element-->\n" + 
"\n" + 
"<!--\n" + 
"Float: Whether the Figure is supposed to be rendered\n" + 
"where convenient (yes (1) value) or at the place it occurs\n" + 
"in the text (no (0) value, the default)\n" + 
"-->\n" + 
"<!ATTLIST informalfigure\n" + 
"		float		CDATA	\"0\"\n" + 
"		floatstyle	CDATA			#IMPLIED\n" + 
"		pgwide      	CDATA       #IMPLIED\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of informalfigure.attlist-->\n" + 
"<!--end of informalfigure.module-->\n" + 
"\n" + 
"<!--doc:A graphic that contains callout areas.-->\n" + 
"<!ELEMENT graphicco  (areaspec, graphic, calloutlist*)>\n" + 
"<!--end of graphicco.element-->\n" + 
"\n" + 
"<!ATTLIST graphicco\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of graphicco.attlist-->\n" + 
"<!-- AreaSpec (defined above in Examples)-->\n" + 
"<!-- CalloutList (defined above in Lists)-->\n" + 
"<!--end of graphicco.module-->\n" + 
"\n" + 
"<!-- Graphical data can be the content of Graphic, or you can reference\n" + 
"     an external file either as an entity (Entitref) or a filename\n" + 
"     (Fileref). -->\n" + 
"\n" + 
"<!--doc:A displayed graphical object (not an inline).-->\n" + 
"<!ELEMENT graphic  EMPTY>\n" + 
"<!--end of graphic.element-->\n" + 
"\n" + 
"<!ATTLIST graphic\n" + 
"\n" + 
"	entityref	ENTITY		#IMPLIED\n" + 
"	fileref 	CDATA		#IMPLIED\n" + 
"	format		(BMP| CGM-CHAR | CGM-BINARY | CGM-CLEAR | DITROFF | DVI\n" + 
"		| EPS | EQN | FAX | GIF | GIF87a | GIF89a\n" + 
"		| JPG | JPEG | IGES | PCX\n" + 
"		| PIC | PNG | PS | SGML | TBL | TEX | TIFF | WMF | WPG\n" + 
"                | SVG | PDF | SWF\n" + 
"		| linespecific\n" + 
"		) #IMPLIED\n" + 
"	srccredit	CDATA		#IMPLIED\n" + 
"	width		CDATA		#IMPLIED\n" + 
"	contentwidth	CDATA		#IMPLIED\n" + 
"	depth		CDATA		#IMPLIED\n" + 
"	contentdepth	CDATA		#IMPLIED\n" + 
"	align		(left\n" + 
"			|right\n" + 
"			|center)	#IMPLIED\n" + 
"	valign		(top\n" + 
"			|middle\n" + 
"			|bottom)	#IMPLIED\n" + 
"	scale		CDATA		#IMPLIED\n" + 
"	scalefit	CDATA\n" + 
"					#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of graphic.attlist-->\n" + 
"<!--end of graphic.module-->\n" + 
"\n" + 
"<!--doc:An object containing or pointing to graphical data that will be rendered inline.-->\n" + 
"<!ELEMENT inlinegraphic  EMPTY>\n" + 
"<!--end of inlinegraphic.element-->\n" + 
"\n" + 
"<!ATTLIST inlinegraphic\n" + 
"\n" + 
"	entityref	ENTITY		#IMPLIED\n" + 
"	fileref 	CDATA		#IMPLIED\n" + 
"	format		(BMP| CGM-CHAR | CGM-BINARY | CGM-CLEAR | DITROFF | DVI\n" + 
"		| EPS | EQN | FAX | GIF | GIF87a | GIF89a\n" + 
"		| JPG | JPEG | IGES | PCX\n" + 
"		| PIC | PNG | PS | SGML | TBL | TEX | TIFF | WMF | WPG\n" + 
"                | SVG | PDF | SWF\n" + 
"		| linespecific\n" + 
"		) #IMPLIED\n" + 
"	srccredit	CDATA		#IMPLIED\n" + 
"	width		CDATA		#IMPLIED\n" + 
"	contentwidth	CDATA		#IMPLIED\n" + 
"	depth		CDATA		#IMPLIED\n" + 
"	contentdepth	CDATA		#IMPLIED\n" + 
"	align		(left\n" + 
"			|right\n" + 
"			|center)	#IMPLIED\n" + 
"	valign		(top\n" + 
"			|middle\n" + 
"			|bottom)	#IMPLIED\n" + 
"	scale		CDATA		#IMPLIED\n" + 
"	scalefit	CDATA\n" + 
"					#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of inlinegraphic.attlist-->\n" + 
"<!--end of inlinegraphic.module-->\n" + 
"\n" + 
"<!--doc:A displayed media object (video, audio, image, etc.).-->\n" + 
"<!ELEMENT mediaobject  (objectinfo?,\n" + 
"                           (videoobject|audioobject|imageobject|imageobjectco|textobject )+,\n" + 
"			   caption?)>\n" + 
"<!--end of mediaobject.element-->\n" + 
"\n" + 
"<!ATTLIST mediaobject\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of mediaobject.attlist-->\n" + 
"<!--end of mediaobject.module-->\n" + 
"\n" + 
"<!--doc:An inline media object (video, audio, image, and so on).-->\n" + 
"<!ELEMENT inlinemediaobject  (objectinfo?,\n" + 
"                	         (videoobject|audioobject|imageobject|imageobjectco|textobject )+)>\n" + 
"<!--end of inlinemediaobject.element-->\n" + 
"\n" + 
"<!ATTLIST inlinemediaobject\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of inlinemediaobject.attlist-->\n" + 
"<!--end of inlinemediaobject.module-->\n" + 
"\n" + 
"<!--doc:A wrapper for video data and its associated meta-information.-->\n" + 
"<!ELEMENT videoobject  (objectinfo?, videodata)>\n" + 
"<!--end of videoobject.element-->\n" + 
"\n" + 
"<!ATTLIST videoobject\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of videoobject.attlist-->\n" + 
"<!--end of videoobject.module-->\n" + 
"\n" + 
"<!--doc:A wrapper for audio data and its associated meta-information.-->\n" + 
"<!ELEMENT audioobject  (objectinfo?, audiodata)>\n" + 
"<!--end of audioobject.element-->\n" + 
"\n" + 
"<!ATTLIST audioobject\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of audioobject.attlist-->\n" + 
"<!--end of audioobject.module-->\n" + 
"\n" + 
"<!--doc:A wrapper for image data and its associated meta-information.-->\n" + 
"<!ELEMENT imageobject  (objectinfo?, imagedata)>\n" + 
"<!--end of imageobject.element-->\n" + 
"\n" + 
"<!ATTLIST imageobject\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of imageobject.attlist-->\n" + 
"<!--end of imageobject.module-->\n" + 
"\n" + 
"<!--doc:A wrapper for a text description of an object and its associated meta-information.-->\n" + 
"<!ELEMENT textobject  (objectinfo?, (phrase|textdata|(calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot\n" + 
"		|formalpara|para|simpara 		|blockquote\n" + 
"		)+))>\n" + 
"<!--end of textobject.element-->\n" + 
"\n" + 
"<!ATTLIST textobject\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of textobject.attlist-->\n" + 
"<!--end of textobject.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for an object.-->\n" + 
"<!ELEMENT objectinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"	>\n" + 
"<!--end of objectinfo.element-->\n" + 
"\n" + 
"<!ATTLIST objectinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of objectinfo.attlist-->\n" + 
"<!--end of objectinfo.module-->\n" + 
"\n" + 
"<!--EntityRef: Name of an external entity containing the content\n" + 
"	of the object data-->\n" + 
"<!--FileRef: Filename, qualified by a pathname if desired,\n" + 
"	designating the file containing the content of the object data-->\n" + 
"<!--Format: Notation of the element content, if any-->\n" + 
"<!--SrcCredit: Information about the source of the image-->\n" + 
"\n" + 
"<!--doc:Pointer to external video data.-->\n" + 
"<!ELEMENT videodata  EMPTY>\n" + 
"<!--end of videodata.element-->\n" + 
"\n" + 
"<!--Width: Same as CALS reprowid (desired width)-->\n" + 
"<!--Depth: Same as CALS reprodep (desired depth)-->\n" + 
"<!--Align: Same as CALS hplace with 'none' removed; #IMPLIED means\n" + 
"	application-specific-->\n" + 
"<!--Scale: Conflation of CALS hscale and vscale-->\n" + 
"<!--Scalefit: Same as CALS scalefit-->\n" + 
"<!ATTLIST videodata\n" + 
"	width		CDATA		#IMPLIED\n" + 
"	contentwidth	CDATA		#IMPLIED\n" + 
"	depth		CDATA		#IMPLIED\n" + 
"	contentdepth	CDATA		#IMPLIED\n" + 
"	align		(left\n" + 
"			|right\n" + 
"			|center)	#IMPLIED\n" + 
"	valign		(top\n" + 
"			|middle\n" + 
"			|bottom)	#IMPLIED\n" + 
"	scale		CDATA		#IMPLIED\n" + 
"	scalefit	CDATA\n" + 
"					#IMPLIED\n" + 
"\n" + 
"	entityref	ENTITY		#IMPLIED\n" + 
"	fileref 	CDATA		#IMPLIED\n" + 
"	format		(BMP| CGM-CHAR | CGM-BINARY | CGM-CLEAR | DITROFF | DVI\n" + 
"		| EPS | EQN | FAX | GIF | GIF87a | GIF89a\n" + 
"		| JPG | JPEG | IGES | PCX\n" + 
"		| PIC | PNG | PS | SGML | TBL | TEX | TIFF | WMF | WPG\n" + 
"                | SVG | PDF | SWF\n" + 
"		| linespecific\n" + 
"		)\n" + 
"					#IMPLIED\n" + 
"	srccredit	CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of videodata.attlist-->\n" + 
"<!--end of videodata.module-->\n" + 
"\n" + 
"<!--doc:Pointer to external audio data.-->\n" + 
"<!ELEMENT audiodata  EMPTY>\n" + 
"<!--end of audiodata.element-->\n" + 
"\n" + 
"<!ATTLIST audiodata\n" + 
"\n" + 
"	entityref	ENTITY		#IMPLIED\n" + 
"	fileref 	CDATA		#IMPLIED\n" + 
"	format		(BMP| CGM-CHAR | CGM-BINARY | CGM-CLEAR | DITROFF | DVI\n" + 
"		| EPS | EQN | FAX | GIF | GIF87a | GIF89a\n" + 
"		| JPG | JPEG | IGES | PCX\n" + 
"		| PIC | PNG | PS | SGML | TBL | TEX | TIFF | WMF | WPG\n" + 
"                | SVG | PDF | SWF\n" + 
"		| linespecific\n" + 
"		)\n" + 
"					#IMPLIED\n" + 
"	srccredit	CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of audiodata.attlist-->\n" + 
"<!--end of audiodata.module-->\n" + 
"\n" + 
"<!--doc:Pointer to external image data.-->\n" + 
"<!ELEMENT imagedata  EMPTY>\n" + 
"<!--end of imagedata.element-->\n" + 
"\n" + 
"<!--Width: Same as CALS reprowid (desired width)-->\n" + 
"<!--Depth: Same as CALS reprodep (desired depth)-->\n" + 
"<!--Align: Same as CALS hplace with 'none' removed; #IMPLIED means\n" + 
"	application-specific-->\n" + 
"<!--Scale: Conflation of CALS hscale and vscale-->\n" + 
"<!--Scalefit: Same as CALS scalefit-->\n" + 
"<!ATTLIST imagedata\n" + 
"	width		CDATA		#IMPLIED\n" + 
"	contentwidth	CDATA		#IMPLIED\n" + 
"	depth		CDATA		#IMPLIED\n" + 
"	contentdepth	CDATA		#IMPLIED\n" + 
"	align		(left\n" + 
"			|right\n" + 
"			|center)	#IMPLIED\n" + 
"	valign		(top\n" + 
"			|middle\n" + 
"			|bottom)	#IMPLIED\n" + 
"	scale		CDATA		#IMPLIED\n" + 
"	scalefit	CDATA\n" + 
"					#IMPLIED\n" + 
"\n" + 
"	entityref	ENTITY		#IMPLIED\n" + 
"	fileref 	CDATA		#IMPLIED\n" + 
"	format		(BMP| CGM-CHAR | CGM-BINARY | CGM-CLEAR | DITROFF | DVI\n" + 
"		| EPS | EQN | FAX | GIF | GIF87a | GIF89a\n" + 
"		| JPG | JPEG | IGES | PCX\n" + 
"		| PIC | PNG | PS | SGML | TBL | TEX | TIFF | WMF | WPG\n" + 
"                | SVG | PDF | SWF\n" + 
"		| linespecific\n" + 
"		)\n" + 
"					#IMPLIED\n" + 
"	srccredit	CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of imagedata.attlist-->\n" + 
"<!--end of imagedata.module-->\n" + 
"\n" + 
"<!--doc:Pointer to external text data.-->\n" + 
"<!ELEMENT textdata  EMPTY>\n" + 
"<!--end of textdata.element-->\n" + 
"\n" + 
"<!ATTLIST textdata\n" + 
"		encoding	CDATA	#IMPLIED\n" + 
"\n" + 
"	entityref	ENTITY		#IMPLIED\n" + 
"	fileref 	CDATA		#IMPLIED\n" + 
"	format		(BMP| CGM-CHAR | CGM-BINARY | CGM-CLEAR | DITROFF | DVI\n" + 
"		| EPS | EQN | FAX | GIF | GIF87a | GIF89a\n" + 
"		| JPG | JPEG | IGES | PCX\n" + 
"		| PIC | PNG | PS | SGML | TBL | TEX | TIFF | WMF | WPG\n" + 
"                | SVG | PDF | SWF\n" + 
"		| linespecific\n" + 
"		)\n" + 
"					#IMPLIED\n" + 
"	srccredit	CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of textdata.attlist-->\n" + 
"<!--end of textdata.module-->\n" + 
"\n" + 
"<!--doc:A media object that contains callouts.-->\n" + 
"<!ELEMENT mediaobjectco  (objectinfo?, imageobjectco,\n" + 
"			   (imageobjectco|textobject)*)>\n" + 
"<!--end of mediaobjectco.element-->\n" + 
"\n" + 
"<!ATTLIST mediaobjectco\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of mediaobjectco.attlist-->\n" + 
"<!--end of mediaobjectco.module-->\n" + 
"\n" + 
"<!--doc:A wrapper for an image object with callouts.-->\n" + 
"<!ELEMENT imageobjectco  (areaspec, imageobject, calloutlist*)>\n" + 
"<!--end of imageobjectco.element-->\n" + 
"\n" + 
"<!ATTLIST imageobjectco\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of imageobjectco.attlist-->\n" + 
"<!--end of imageobjectco.module-->\n" + 
"<!--end of mediaobject.content.module-->\n" + 
"\n" + 
"<!-- Equations ........................ -->\n" + 
"\n" + 
"<!-- This PE provides a mechanism for replacing equation content, -->\n" + 
"<!-- perhaps adding a new or different model (e.g., MathML) -->\n" + 
"\n" + 
"<!--doc:A displayed mathematical equation.-->\n" + 
"<!ELEMENT equation  (blockinfo?, (title, titleabbrev?)?,\n" + 
"                         (informalequation | (alt?, (graphic+|mediaobject+|mathphrase+))))>\n" + 
"<!--end of equation.element-->\n" + 
"\n" + 
"<!ATTLIST equation\n" + 
"		floatstyle	CDATA			#IMPLIED\n" + 
"		label		CDATA		#IMPLIED\n" + 
"	 	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of equation.attlist-->\n" + 
"<!--end of equation.module-->\n" + 
"\n" + 
"<!--doc:A displayed mathematical equation without a title.-->\n" + 
"<!ELEMENT informalequation  (blockinfo?, (alt?, (graphic+|mediaobject+|mathphrase+))) >\n" + 
"<!--end of informalequation.element-->\n" + 
"\n" + 
"<!ATTLIST informalequation\n" + 
"		floatstyle	CDATA			#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of informalequation.attlist-->\n" + 
"<!--end of informalequation.module-->\n" + 
"\n" + 
"<!--doc:A mathematical equation or expression occurring inline.-->\n" + 
"<!ELEMENT inlineequation  ((alt?, (graphic+|inlinemediaobject+|mathphrase+)))>\n" + 
"<!--end of inlineequation.element-->\n" + 
"\n" + 
"<!ATTLIST inlineequation\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of inlineequation.attlist-->\n" + 
"<!--end of inlineequation.module-->\n" + 
"\n" + 
"<!--doc:Text representation for a graphical element.-->\n" + 
"<!ELEMENT alt  (#PCDATA)>\n" + 
"<!--end of alt.element-->\n" + 
"\n" + 
"<!ATTLIST alt\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of alt.attlist-->\n" + 
"<!--end of alt.module-->\n" + 
"\n" + 
"<!--doc:A mathematical phrase, an expression that can be represented with ordinary text and a small amount of markup.-->\n" + 
"<!ELEMENT mathphrase  (#PCDATA|subscript|superscript|emphasis)*>\n" + 
"<!--end of mathphrase.element-->\n" + 
"\n" + 
"<!ATTLIST mathphrase\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of mathphrase.attlist-->\n" + 
"<!--end of mathphrase.module-->\n" + 
"\n" + 
"<!-- Tables ........................... -->\n" + 
"\n" + 
"<!-- Choose a table model. CALS or OASIS XML Exchange -->\n" + 
"\n" + 
"<!-- Do we allow the HTML table model as well? -->\n" + 
"\n" + 
"  <!-- ====================================================== -->\n" + 
"  <!--  xhtmltbl.mod defines HTML tables and sets parameter\n" + 
"        entities so that, when the CALS table module is read,\n" + 
"        we end up allowing any table to be CALS or HTML.\n" + 
"        i.e. This include must come first!                    -->\n" + 
"  <!-- ====================================================== -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- DocBook XML HTML Table Module V4.5 ................................... -->\n" + 
"<!-- File htmltblx.mod .................................................... -->\n" + 
"\n" + 
"<!-- Copyright 2003-2006 ArborText, Inc., Norman Walsh, Sun Microsystems,\n" + 
"     Inc., and the Organization for the Advancement of Structured Information\n" + 
"     Standards (OASIS).\n" + 
"\n" + 
"     $Id: htmltblx.mod 6340 2006-10-03 13:23:24Z nwalsh $\n" + 
"\n" + 
"     Permission to use, copy, modify and distribute the DocBook XML DTD\n" + 
"     and its accompanying documentation for any purpose and without fee\n" + 
"     is hereby granted in perpetuity, provided that the above copyright\n" + 
"     notice and this paragraph appear in all copies.  The copyright\n" + 
"     holders make no representation about the suitability of the DTD for\n" + 
"     any purpose.  It is provided \"as is\" without expressed or implied\n" + 
"     warranty.\n" + 
"\n" + 
"     If you modify the DocBook XML DTD in any way, except for declaring and\n" + 
"     referencing additional sets of general entities and declaring\n" + 
"     additional notations, label your DTD as a variant of DocBook.  See\n" + 
"     the maintenance documentation for more information.\n" + 
"\n" + 
"     Please direct all questions, bug reports, or suggestions for\n" + 
"     changes to the docbook@lists.oasis-open.org mailing list. For more\n" + 
"     information, see http://www.oasis-open.org/docbook/.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- This module contains the definitions for elements that are\n" + 
"     isomorphic to the HTML elements. One could argue we should\n" + 
"     instead have based ourselves on the XHTML Table Module, but the\n" + 
"     HTML one is more like what browsers are likely to accept today\n" + 
"     and users are likely to use.\n" + 
"\n" + 
"     This module has been developed for use with the DocBook V4.5\n" + 
"     \"union table model\" in which elements and attlists common to both\n" + 
"     models are defined (as the union) in the CALS table module by\n" + 
"     setting various parameter entities appropriately in this file.\n" + 
"\n" + 
"     In DTD driver files referring to this module, please use an entity\n" + 
"     declaration that uses the public identifier shown below:\n" + 
"\n" + 
"     <!ENTITY % htmltbl PUBLIC\n" + 
"     \"-//OASIS//ELEMENTS DocBook XML HTML Tables V4.5//EN\"\n" + 
"     \"htmltblx.mod\">\n" + 
"     %htmltbl;\n" + 
"\n" + 
"     See the documentation for detailed information on the parameter\n" + 
"     entity and module scheme used in DocBook, customizing DocBook and\n" + 
"     planning for interchange, and changes made since the last release\n" + 
"     of DocBook.\n" + 
"-->\n" + 
"\n" + 
"<!--======================= XHTML Tables =======================================-->\n" + 
"\n" + 
"<!-- Does not contain lang or dir because they are in %common.attribs -->\n" + 
"\n" + 
"<!--doc:A group of columns in an HTML table.-->\n" + 
"<!ELEMENT colgroup  (col)*>\n" + 
"<!--doc:Specifications for a column in an HTML table.-->\n" + 
"<!ELEMENT col  EMPTY>\n" + 
"<!--doc:A row in an HTML table.-->\n" + 
"<!ELEMENT tr   (th|td)+>\n" + 
"<!--doc:A table header entry in an HTML table.-->\n" + 
"<!ELEMENT th   (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		 | calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot\n" + 
"		|formalpara|para|simpara 		|graphic|mediaobject\n" + 
"\n" + 
"		 | table | informaltable)*>\n" + 
"<!--doc:A table ntry in an HTML table.-->\n" + 
"<!ELEMENT td   (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		 | calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot\n" + 
"		|formalpara|para|simpara 		|graphic|mediaobject\n" + 
"\n" + 
"		 | table | informaltable)*>\n" + 
"\n" + 
"<!ATTLIST colgroup\n" + 
"  id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"  class       CDATA          #IMPLIED\n" + 
"  style       CDATA          #IMPLIED\n" + 
"  title       CDATA         #IMPLIED xml:lang    NMTOKEN        #IMPLIED onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"  span        CDATA       \"1\"\n" + 
"  width       CDATA  #IMPLIED\n" + 
"  align      (left|center|right|justify|char) #IMPLIED\n" + 
"   char       CDATA    #IMPLIED\n" + 
"   charoff    CDATA       #IMPLIED\n" + 
"  valign     (top|middle|bottom|baseline) #IMPLIED\n" + 
"  >\n" + 
"\n" + 
"<!ATTLIST col\n" + 
"  id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"  class       CDATA          #IMPLIED\n" + 
"  style       CDATA          #IMPLIED\n" + 
"  title       CDATA         #IMPLIED xml:lang    NMTOKEN        #IMPLIED onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"  span        CDATA       \"1\"\n" + 
"  width       CDATA  #IMPLIED\n" + 
"  align      (left|center|right|justify|char) #IMPLIED\n" + 
"   char       CDATA    #IMPLIED\n" + 
"   charoff    CDATA       #IMPLIED\n" + 
"  valign     (top|middle|bottom|baseline) #IMPLIED\n" + 
"  >\n" + 
"\n" + 
"<!ATTLIST tr\n" + 
"  id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"  class       CDATA          #IMPLIED\n" + 
"  style       CDATA          #IMPLIED\n" + 
"  title       CDATA         #IMPLIED xml:lang    NMTOKEN        #IMPLIED onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"  align      (left|center|right|justify|char) #IMPLIED\n" + 
"   char       CDATA    #IMPLIED\n" + 
"   charoff    CDATA       #IMPLIED\n" + 
"  valign     (top|middle|bottom|baseline) #IMPLIED\n" + 
"  bgcolor     CDATA        #IMPLIED\n" + 
"  >\n" + 
"\n" + 
"<!ATTLIST th\n" + 
"  id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"  class       CDATA          #IMPLIED\n" + 
"  style       CDATA          #IMPLIED\n" + 
"  title       CDATA         #IMPLIED xml:lang    NMTOKEN        #IMPLIED onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"  abbr        CDATA         #IMPLIED\n" + 
"  axis        CDATA          #IMPLIED\n" + 
"  headers     IDREFS         #IMPLIED\n" + 
"  scope       (row|col|rowgroup|colgroup)   #IMPLIED\n" + 
"  rowspan     CDATA       \"1\"\n" + 
"  colspan     CDATA       \"1\"\n" + 
"  align      (left|center|right|justify|char) #IMPLIED\n" + 
"   char       CDATA    #IMPLIED\n" + 
"   charoff    CDATA       #IMPLIED\n" + 
"  valign     (top|middle|bottom|baseline) #IMPLIED\n" + 
"  nowrap      (nowrap)       #IMPLIED\n" + 
"  bgcolor     CDATA         #IMPLIED\n" + 
"  width       CDATA       #IMPLIED\n" + 
"  height      CDATA       #IMPLIED\n" + 
"  >\n" + 
"\n" + 
"<!ATTLIST td\n" + 
"  id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"  class       CDATA          #IMPLIED\n" + 
"  style       CDATA          #IMPLIED\n" + 
"  title       CDATA         #IMPLIED xml:lang    NMTOKEN        #IMPLIED onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"  abbr        CDATA         #IMPLIED\n" + 
"  axis        CDATA          #IMPLIED\n" + 
"  headers     IDREFS         #IMPLIED\n" + 
"  scope       (row|col|rowgroup|colgroup)   #IMPLIED\n" + 
"  rowspan     CDATA       \"1\"\n" + 
"  colspan     CDATA       \"1\"\n" + 
"  align      (left|center|right|justify|char) #IMPLIED\n" + 
"   char       CDATA    #IMPLIED\n" + 
"   charoff    CDATA       #IMPLIED\n" + 
"  valign     (top|middle|bottom|baseline) #IMPLIED\n" + 
"  nowrap      (nowrap)       #IMPLIED\n" + 
"  bgcolor     CDATA         #IMPLIED\n" + 
"  width       CDATA       #IMPLIED\n" + 
"  height      CDATA       #IMPLIED\n" + 
"  >\n" + 
"\n" + 
"<!-- ====================================================== -->\n" + 
"<!--        Set up to read in the CALS model configured to\n" + 
"            merge with the XHTML table model                -->\n" + 
"<!-- ====================================================== -->\n" + 
"\n" + 
"<!-- Add label and role attributes to table and informaltable -->\n" + 
"\n" + 
"<!-- Add common attributes to Table, TGroup, TBody, THead, TFoot, Row,\n" + 
"     EntryTbl, and Entry (and InformalTable element). -->\n" + 
"\n" + 
"<!-- Content model for Table (that also allows HTML tables) -->\n" + 
"\n" + 
"<!-- Attributes for Table (including HTML ones) -->\n" + 
"\n" + 
"<!-- N.B. rules = (none | groups | rows | cols | all) but it can't be spec'd -->\n" + 
"<!-- that way because 'all' already occurs in a different enumeration in -->\n" + 
"<!-- CALS tables (frame). -->\n" + 
"\n" + 
"<!-- Allow either objects or inlines; beware of REs between elements. -->\n" + 
"\n" + 
"<!-- thead, tfoot, and tbody are defined in both table models,\n" + 
"     so we set up parameter entities to define union models for them\n" + 
" -->\n" + 
"\n" + 
"<!-- End of DocBook XML HTML Table Module V4.5 ............................ -->\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!--end of allow.html.tables-->\n" + 
"\n" + 
"<!-- Add label and role attributes to table and informaltable -->\n" + 
"\n" + 
"<!-- Add common attributes to Table, TGroup, TBody, THead, TFoot, Row,\n" + 
"     EntryTbl, and Entry (and InformalTable element). -->\n" + 
"\n" + 
"<!-- Content model for Table. -->\n" + 
"\n" + 
"<!-- Allow either objects or inlines; beware of REs between elements. -->\n" + 
"\n" + 
"<!-- Reference CALS Table Model -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- DocBook CALS Table Model V4.5 ........................................ -->\n" + 
"<!-- File calstblx.mod .................................................... -->\n" + 
"\n" + 
"<!-- Copyright 1992-2002 HaL Computer Systems, Inc.,\n" + 
"     O'Reilly & Associates, Inc., ArborText, Inc., Fujitsu Software\n" + 
"     Corporation, Norman Walsh, Sun Microsystems, Inc., and the\n" + 
"     Organization for the Advancement of Structured Information\n" + 
"     Standards (OASIS).\n" + 
"\n" + 
"     This DTD is based on the CALS Table Model\n" + 
"     PUBLIC \"-//USA-DOD//DTD Table Model 951010//EN\"\n" + 
"\n" + 
"     $Id: calstblx.dtd 6340 2006-10-03 13:23:24Z nwalsh $\n" + 
"\n" + 
"     Permission to use, copy, modify and distribute the DocBook DTD\n" + 
"     and its accompanying documentation for any purpose and without fee\n" + 
"     is hereby granted in perpetuity, provided that the above copyright\n" + 
"     notice and this paragraph appear in all copies.  The copyright\n" + 
"     holders make no representation about the suitability of the DTD for\n" + 
"     any purpose.  It is provided \"as is\" without expressed or implied\n" + 
"     warranty.\n" + 
"\n" + 
"     If you modify the DocBook DTD in any way, except for declaring and\n" + 
"     referencing additional sets of general entities and declaring\n" + 
"     additional notations, label your DTD as a variant of DocBook.  See\n" + 
"     the maintenance documentation for more information.\n" + 
"\n" + 
"     Please direct all questions, bug reports, or suggestions for\n" + 
"     changes to the docbook@lists.oasis-open.org mailing list. For more\n" + 
"     information, see http://www.oasis-open.org/docbook/.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- This module contains the definitions for the CALS Table Model\n" + 
"     converted to XML. It has been modified slightly for use in the\n" + 
"     combined HTML/CALS models supported by DocBook V4.5.\n" + 
"-->\n" + 
"\n" + 
"<!-- These definitions are not directly related to the table model, but are\n" + 
"     used in the default CALS table model and are usually defined elsewhere\n" + 
"     (and prior to the inclusion of this table module) in a CALS DTD. -->\n" + 
"\n" + 
"<!-- no if zero(s),\n" + 
"                                yes if any other digits value -->\n" + 
"\n" + 
"<!-- default for use in entry content -->\n" + 
"\n" + 
"<!--\n" + 
"The parameter entities as defined below provide the CALS table model\n" + 
"as published (as part of the Example DTD) in MIL-HDBK-28001.\n" + 
"\n" + 
"These following declarations provide the CALS-compliant default definitions\n" + 
"for these entities.  However, these entities can and should be redefined\n" + 
"(by giving the appropriate parameter entity declaration(s) prior to the\n" + 
"reference to this Table Model declaration set entity) to fit the needs\n" + 
"of the current application.\n" + 
"-->\n" + 
"\n" + 
"<!-- =====  Element and attribute declarations follow. =====  -->\n" + 
"\n" + 
"<!--doc:A formal table in a document.-->\n" + 
"<!ELEMENT table  (((blockinfo?,\n" + 
"           (title, titleabbrev?),\n" + 
"           (indexterm )*,\n" + 
"           textobject*,\n" + 
"           (graphic+|mediaobject+|tgroup+))\n" + 
"         |(caption, (col*|colgroup*), thead?, tfoot?, (tbody+|tr+))))>\n" + 
"\n" + 
"<!ATTLIST table\n" + 
"        frame           (void|above|below|hsides|lhs|rhs|vsides|box|border|\n" + 
"top|bottom|topbot|all|sides|none)                    #IMPLIED\n" + 
"        colsep          CDATA                               #IMPLIED\n" + 
"        rowsep          CDATA                               #IMPLIED\n" + 
"\n" + 
"    tabstyle    CDATA           #IMPLIED\n" + 
"    tocentry    CDATA       #IMPLIED\n" + 
"    shortentry  CDATA       #IMPLIED\n" + 
"    orient      (port|land)     #IMPLIED\n" + 
"    pgwide      CDATA       #IMPLIED\n" + 
"    summary     CDATA          #IMPLIED\n" + 
"    width       CDATA        #IMPLIED\n" + 
"    border      CDATA        #IMPLIED\n" + 
"    rules       CDATA		#IMPLIED\n" + 
"    cellspacing CDATA        #IMPLIED\n" + 
"    cellpadding CDATA        #IMPLIED\n" + 
"    align       (left|center|right)   #IMPLIED\n" + 
"    bgcolor     CDATA         #IMPLIED\n" + 
"\n" + 
"		floatstyle	CDATA			#IMPLIED\n" + 
"		rowheader	(firstcol|norowheader)	#IMPLIED\n" + 
"                label		CDATA		#IMPLIED\n" + 
"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	class       CDATA          #IMPLIED\n" + 
"	style       CDATA          #IMPLIED\n" + 
"	title       CDATA         #IMPLIED\n" + 
"	xml:lang    NMTOKEN        #IMPLIED\n" + 
"	onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"	role		CDATA		#IMPLIED\n" + 
">\n" + 
"\n" + 
"<!--doc:A wrapper for the main content of a table, or part of a table.-->\n" + 
"<!ELEMENT tgroup  (colspec*,spanspec*,thead?,tfoot?,tbody) >\n" + 
"\n" + 
"<!ATTLIST tgroup\n" + 
"        cols            CDATA                                   #REQUIRED\n" + 
"\n" + 
"    tgroupstyle CDATA           #IMPLIED\n" + 
"        colsep          CDATA                               #IMPLIED\n" + 
"        rowsep          CDATA                               #IMPLIED\n" + 
"        align           (left|right|center|justify|char)        #IMPLIED\n" + 
"        char            CDATA                                   #IMPLIED\n" + 
"        charoff         CDATA                                   #IMPLIED\n" + 
"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	class       CDATA          #IMPLIED\n" + 
"	style       CDATA          #IMPLIED\n" + 
"	title       CDATA         #IMPLIED\n" + 
"	xml:lang    NMTOKEN        #IMPLIED\n" + 
"	onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"	role		CDATA		#IMPLIED\n" + 
">\n" + 
"\n" + 
"<!--doc:Specifications for a column in a table.-->\n" + 
"<!ELEMENT colspec  EMPTY >\n" + 
"\n" + 
"<!ATTLIST colspec\n" + 
"        colnum          CDATA                                   #IMPLIED\n" + 
"        colname         CDATA                                   #IMPLIED\n" + 
"        colwidth        CDATA                                   #IMPLIED\n" + 
"        colsep          CDATA                               #IMPLIED\n" + 
"        rowsep          CDATA                               #IMPLIED\n" + 
"        align           (left|right|center|justify|char)        #IMPLIED\n" + 
"        char            CDATA                                   #IMPLIED\n" + 
"        charoff         CDATA                                   #IMPLIED\n" + 
">\n" + 
"\n" + 
"<!--doc:Formatting information for a spanned column in a table.-->\n" + 
"<!ELEMENT spanspec  EMPTY >\n" + 
"\n" + 
"<!ATTLIST spanspec\n" + 
"        namest          CDATA                                   #REQUIRED\n" + 
"        nameend         CDATA                                   #REQUIRED\n" + 
"        spanname        CDATA                                   #REQUIRED\n" + 
"        colsep          CDATA                               #IMPLIED\n" + 
"        rowsep          CDATA                               #IMPLIED\n" + 
"        align           (left|right|center|justify|char)        #IMPLIED\n" + 
"        char            CDATA                                   #IMPLIED\n" + 
"        charoff         CDATA                                   #IMPLIED\n" + 
">\n" + 
"\n" + 
"<!--doc:A table header consisting of one or more rows.-->\n" + 
"<!ELEMENT thead  ((tr+|(colspec*,row+)))>\n" + 
"<!ATTLIST thead\n" + 
"        valign          (top|middle|bottom)                     #IMPLIED\n" + 
"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	class       CDATA          #IMPLIED\n" + 
"	style       CDATA          #IMPLIED\n" + 
"	title       CDATA         #IMPLIED\n" + 
"	xml:lang    NMTOKEN        #IMPLIED\n" + 
"	onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"	role		CDATA		#IMPLIED\n" + 
">\n" + 
"\n" + 
"<!--doc:A table footer consisting of one or more rows.-->\n" + 
"<!ELEMENT tfoot  ((tr+|(colspec*,row+)))>\n" + 
"<!ATTLIST tfoot\n" + 
"        valign          (top|middle|bottom)                     #IMPLIED\n" + 
"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	class       CDATA          #IMPLIED\n" + 
"	style       CDATA          #IMPLIED\n" + 
"	title       CDATA         #IMPLIED\n" + 
"	xml:lang    NMTOKEN        #IMPLIED\n" + 
"	onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"	role		CDATA		#IMPLIED\n" + 
">\n" + 
"\n" + 
"<!--doc:A wrapper for the rows of a table or informal table.-->\n" + 
"<!ELEMENT tbody  ((tr+|row+))>\n" + 
"\n" + 
"<!ATTLIST tbody\n" + 
"        valign          (top|middle|bottom)                     #IMPLIED\n" + 
"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	class       CDATA          #IMPLIED\n" + 
"	style       CDATA          #IMPLIED\n" + 
"	title       CDATA         #IMPLIED\n" + 
"	xml:lang    NMTOKEN        #IMPLIED\n" + 
"	onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"	role		CDATA		#IMPLIED\n" + 
">\n" + 
"\n" + 
"<!--doc:A row in a table.-->\n" + 
"<!ELEMENT row  ((entry|entrytbl)+)>\n" + 
"\n" + 
"<!ATTLIST row\n" + 
"        rowsep          CDATA                               #IMPLIED\n" + 
"        valign          (top|middle|bottom)                     #IMPLIED\n" + 
"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	class       CDATA          #IMPLIED\n" + 
"	style       CDATA          #IMPLIED\n" + 
"	title       CDATA         #IMPLIED\n" + 
"	xml:lang    NMTOKEN        #IMPLIED\n" + 
"	onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"	role		CDATA		#IMPLIED\n" + 
">\n" + 
"\n" + 
"<!--doc:A subtable appearing in place of an Entry in a table.-->\n" + 
"<!ELEMENT entrytbl  (colspec*,spanspec*,thead?,tbody)>\n" + 
"\n" + 
"<!ATTLIST entrytbl\n" + 
"        cols            CDATA                                   #REQUIRED\n" + 
"\n" + 
"    tgroupstyle CDATA           #IMPLIED\n" + 
"        colname         CDATA                                   #IMPLIED\n" + 
"        spanname        CDATA                                   #IMPLIED\n" + 
"        namest          CDATA                                   #IMPLIED\n" + 
"        nameend         CDATA                                   #IMPLIED\n" + 
"        colsep          CDATA                               #IMPLIED\n" + 
"        rowsep          CDATA                               #IMPLIED\n" + 
"        align           (left|right|center|justify|char)        #IMPLIED\n" + 
"        char            CDATA                                   #IMPLIED\n" + 
"        charoff         CDATA                                   #IMPLIED\n" + 
"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	class       CDATA          #IMPLIED\n" + 
"	style       CDATA          #IMPLIED\n" + 
"	title       CDATA         #IMPLIED\n" + 
"	xml:lang    NMTOKEN        #IMPLIED\n" + 
"	onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"	role		CDATA		#IMPLIED\n" + 
">\n" + 
"\n" + 
"<!--doc:A cell in a table.-->\n" + 
"<!ELEMENT entry  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		 | calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot\n" + 
"		|formalpara|para|simpara 		|graphic|mediaobject\n" + 
"\n" + 
"		)*>\n" + 
"\n" + 
"<!ATTLIST entry\n" + 
"        colname         CDATA                                   #IMPLIED\n" + 
"        namest          CDATA                                   #IMPLIED\n" + 
"        nameend         CDATA                                   #IMPLIED\n" + 
"        spanname        CDATA                                   #IMPLIED\n" + 
"        morerows        CDATA                                   #IMPLIED\n" + 
"        colsep          CDATA                               #IMPLIED\n" + 
"        rowsep          CDATA                               #IMPLIED\n" + 
"        align           (left|right|center|justify|char)        #IMPLIED\n" + 
"        char            CDATA                                   #IMPLIED\n" + 
"        charoff         CDATA                                   #IMPLIED\n" + 
"        rotate          CDATA                               #IMPLIED\n" + 
"        valign          (top|middle|bottom)                     #IMPLIED\n" + 
"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	class       CDATA          #IMPLIED\n" + 
"	style       CDATA          #IMPLIED\n" + 
"	title       CDATA         #IMPLIED\n" + 
"	xml:lang    NMTOKEN        #IMPLIED\n" + 
"	onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"	role		CDATA		#IMPLIED\n" + 
">\n" + 
"\n" + 
"<!-- End of DocBook CALS Table Model V4.5 ................................. -->\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!--end of table.module-->\n" + 
"\n" + 
"<!-- Note that InformalTable is dependent on some of the entity\n" + 
"     declarations that customize Table. -->\n" + 
"\n" + 
"<!-- the following entity may have been declared by the XHTML table module -->\n" + 
"\n" + 
"<!--doc:A table without a title.-->\n" + 
"<!ELEMENT informaltable  (blockinfo?, ((textobject*,\n" + 
"          (graphic+|mediaobject+|tgroup+))\n" + 
"         | ((col*|colgroup*), thead?, tfoot?, (tbody+|tr+))))>\n" + 
"<!--end of informaltable.element-->\n" + 
"\n" + 
"<!-- Frame, Colsep, and Rowsep must be repeated because\n" + 
"		they are not in entities in the table module. -->\n" + 
"<!-- includes TabStyle, ToCentry, ShortEntry,\n" + 
"				Orient, PgWide -->\n" + 
"<!-- includes Label -->\n" + 
"<!-- includes common attributes -->\n" + 
"\n" + 
"<!ATTLIST informaltable\n" + 
"		frame		(void|above|below|hsides|lhs|rhs|vsides|box|border|\n" + 
"top|bottom|topbot|all|sides|none)	#IMPLIED\n" + 
"		colsep		CDATA	#IMPLIED\n" + 
"		rowsep		CDATA	#IMPLIED\n" + 
"\n" + 
"		floatstyle	CDATA			#IMPLIED\n" + 
"		rowheader	(firstcol|norowheader)	#IMPLIED\n" + 
"                label		CDATA		#IMPLIED\n" + 
"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	class       CDATA          #IMPLIED\n" + 
"	style       CDATA          #IMPLIED\n" + 
"	title       CDATA         #IMPLIED\n" + 
"	xml:lang    NMTOKEN        #IMPLIED\n" + 
"	onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
"    tabstyle    CDATA           #IMPLIED\n" + 
"    tocentry    CDATA       #IMPLIED\n" + 
"    shortentry  CDATA       #IMPLIED\n" + 
"    orient      (port|land)     #IMPLIED\n" + 
"    pgwide      CDATA       #IMPLIED\n" + 
"    summary     CDATA          #IMPLIED\n" + 
"    width       CDATA        #IMPLIED\n" + 
"    border      CDATA        #IMPLIED\n" + 
"    rules       CDATA		#IMPLIED\n" + 
"    cellspacing CDATA        #IMPLIED\n" + 
"    cellpadding CDATA        #IMPLIED\n" + 
"    align       (left|center|right)   #IMPLIED\n" + 
"    bgcolor     CDATA         #IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of informaltable.attlist-->\n" + 
"<!--end of informaltable.module-->\n" + 
"\n" + 
"<!--doc:A caption.-->\n" + 
"<!ELEMENT caption  (#PCDATA | calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot\n" + 
"		|formalpara|para|simpara 		|blockquote\n" + 
"		)*>\n" + 
"<!--end of caption.element-->\n" + 
"\n" + 
"<!-- attrs comes from HTML tables ... -->\n" + 
"\n" + 
"<!-- common.attrib, but without ID because ID is in attrs -->\n" + 
"\n" + 
"<!ATTLIST caption\n" + 
"		role		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"  class       CDATA          #IMPLIED\n" + 
"  style       CDATA          #IMPLIED\n" + 
"  title       CDATA         #IMPLIED xml:lang    NMTOKEN        #IMPLIED onclick     CDATA       #IMPLIED\n" + 
"  ondblclick  CDATA       #IMPLIED\n" + 
"  onmousedown CDATA       #IMPLIED\n" + 
"  onmouseup   CDATA       #IMPLIED\n" + 
"  onmouseover CDATA       #IMPLIED\n" + 
"  onmousemove CDATA       #IMPLIED\n" + 
"  onmouseout  CDATA       #IMPLIED\n" + 
"  onkeypress  CDATA       #IMPLIED\n" + 
"  onkeydown   CDATA       #IMPLIED\n" + 
"  onkeyup     CDATA       #IMPLIED\n" + 
"		align	(top|bottom|left|right)	#IMPLIED\n" + 
"\n" + 
">\n" + 
"\n" + 
"<!--end of caption.attlist-->\n" + 
"<!--end of caption.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Synopses ............................................................. -->\n" + 
"\n" + 
"<!-- Synopsis ......................... -->\n" + 
"\n" + 
"<!--doc:A general-purpose element for representing the syntax of commands or functions.-->\n" + 
"<!ELEMENT synopsis  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		|graphic|mediaobject|co|coref|textobject|lineannotation)*>\n" + 
"<!--end of synopsis.element-->\n" + 
"\n" + 
"<!ATTLIST synopsis\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		format		NOTATION\n" + 
"			(linespecific)	'linespecific'\n" + 
"         xml:space	(preserve)		#IMPLIED\n" + 
"         linenumbering	(numbered|unnumbered) 	#IMPLIED\n" + 
"         continuation	(continues|restarts)	#IMPLIED\n" + 
"         startinglinenumber	CDATA		#IMPLIED\n" + 
"         language	CDATA			#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of synopsis.attlist-->\n" + 
"\n" + 
"<!-- LineAnnotation (defined in the Inlines section, below)-->\n" + 
"<!--end of synopsis.module-->\n" + 
"\n" + 
"<!-- CmdSynopsis ...................... -->\n" + 
"\n" + 
"<!--doc:A syntax summary for a software command.-->\n" + 
"<!ELEMENT cmdsynopsis  ((command | arg | group | sbr)+, synopfragment*)>\n" + 
"<!--end of cmdsynopsis.element-->\n" + 
"\n" + 
"<!-- Sepchar: Character that should separate command and all\n" + 
"		top-level arguments; alternate value might be e.g., &Delta; -->\n" + 
"\n" + 
"<!ATTLIST cmdsynopsis\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		sepchar		CDATA		\" \"\n" + 
"		cmdlength	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of cmdsynopsis.attlist-->\n" + 
"<!--end of cmdsynopsis.module-->\n" + 
"\n" + 
"<!--doc:An argument in a CmdSynopsis.-->\n" + 
"<!ELEMENT arg  (#PCDATA\n" + 
"		| arg\n" + 
"		| group\n" + 
"		| option\n" + 
"		| synopfragmentref\n" + 
"		| replaceable\n" + 
"		| sbr)*>\n" + 
"<!--end of arg.element-->\n" + 
"\n" + 
"<!-- Choice: Whether Arg must be supplied: Opt (optional to\n" + 
"		supply, e.g. [arg]; the default), Req (required to supply,\n" + 
"		e.g. {arg}), or Plain (required to supply, e.g. arg) -->\n" + 
"<!-- Rep: whether Arg is repeatable: Norepeat (e.g. arg without\n" + 
"		ellipsis; the default), or Repeat (e.g. arg...) -->\n" + 
"\n" + 
"<!ATTLIST arg\n" + 
"		choice		(opt\n" + 
"				|req\n" + 
"				|plain)		'opt'\n" + 
"		rep		(norepeat\n" + 
"				|repeat)	'norepeat'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of arg.attlist-->\n" + 
"<!--end of arg.module-->\n" + 
"\n" + 
"<!--doc:A group of elements in a CmdSynopsis.-->\n" + 
"<!ELEMENT group  ((arg | group | option | synopfragmentref\n" + 
"		| replaceable | sbr)+)>\n" + 
"<!--end of group.element-->\n" + 
"\n" + 
"<!-- Choice: Whether Group must be supplied: Opt (optional to\n" + 
"		supply, e.g.  [g1|g2|g3]; the default), Req (required to\n" + 
"		supply, e.g.  {g1|g2|g3}), Plain (required to supply,\n" + 
"		e.g.  g1|g2|g3), OptMult (can supply zero or more, e.g.\n" + 
"		[[g1|g2|g3]]), or ReqMult (must supply one or more, e.g.\n" + 
"		{{g1|g2|g3}}) -->\n" + 
"<!-- Rep: whether Group is repeatable: Norepeat (e.g. group\n" + 
"		without ellipsis; the default), or Repeat (e.g. group...) -->\n" + 
"\n" + 
"<!ATTLIST group\n" + 
"		choice		(opt\n" + 
"				|req\n" + 
"				|plain)         'opt'\n" + 
"		rep		(norepeat\n" + 
"				|repeat)	'norepeat'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of group.attlist-->\n" + 
"<!--end of group.module-->\n" + 
"\n" + 
"<!-- Synopsis break -->\n" + 
"\n" + 
"<!--doc:An explicit line break in a command synopsis.-->\n" + 
"<!ELEMENT sbr  EMPTY>\n" + 
"<!--end of sbr.element-->\n" + 
"\n" + 
"<!ATTLIST sbr\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sbr.attlist-->\n" + 
"<!--end of sbr.module-->\n" + 
"\n" + 
"<!--doc:A reference to a fragment of a command synopsis.-->\n" + 
"<!ELEMENT synopfragmentref  (#PCDATA)>\n" + 
"<!--end of synopfragmentref.element-->\n" + 
"\n" + 
"<!-- to SynopFragment of complex synopsis\n" + 
"			material for separate referencing -->\n" + 
"\n" + 
"<!ATTLIST synopfragmentref\n" + 
"		linkend	IDREF		#REQUIRED		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of synopfragmentref.attlist-->\n" + 
"<!--end of synopfragmentref.module-->\n" + 
"\n" + 
"<!--doc:A portion of a CmdSynopsis broken out from the main body of the synopsis.-->\n" + 
"<!ELEMENT synopfragment  ((arg | group)+)>\n" + 
"<!--end of synopfragment.element-->\n" + 
"\n" + 
"<!ATTLIST synopfragment\n" + 
"		id		ID		#REQUIRED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of synopfragment.attlist-->\n" + 
"<!--end of synopfragment.module-->\n" + 
"\n" + 
"<!-- Command (defined in the Inlines section, below)-->\n" + 
"<!-- Option (defined in the Inlines section, below)-->\n" + 
"<!-- Replaceable (defined in the Inlines section, below)-->\n" + 
"<!--end of cmdsynopsis.content.module-->\n" + 
"\n" + 
"<!-- FuncSynopsis ..................... -->\n" + 
"\n" + 
"<!--doc:The syntax summary for a function definition.-->\n" + 
"<!ELEMENT funcsynopsis  ((funcsynopsisinfo | funcprototype)+)>\n" + 
"<!--end of funcsynopsis.element-->\n" + 
"\n" + 
"<!ATTLIST funcsynopsis\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of funcsynopsis.attlist-->\n" + 
"<!--end of funcsynopsis.module-->\n" + 
"\n" + 
"<!--doc:Information supplementing the FuncDefs of a FuncSynopsis.-->\n" + 
"<!ELEMENT funcsynopsisinfo  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|textobject|lineannotation)*>\n" + 
"<!--end of funcsynopsisinfo.element-->\n" + 
"\n" + 
"<!ATTLIST funcsynopsisinfo\n" + 
"		format		NOTATION\n" + 
"			(linespecific)	'linespecific'\n" + 
"         xml:space	(preserve)		#IMPLIED\n" + 
"         linenumbering	(numbered|unnumbered) 	#IMPLIED\n" + 
"         continuation	(continues|restarts)	#IMPLIED\n" + 
"         startinglinenumber	CDATA		#IMPLIED\n" + 
"         language	CDATA			#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of funcsynopsisinfo.attlist-->\n" + 
"<!--end of funcsynopsisinfo.module-->\n" + 
"\n" + 
"<!--doc:The prototype of a function.-->\n" + 
"<!ELEMENT funcprototype  (modifier*,\n" + 
"                              funcdef,\n" + 
"                              (void|varargs|(paramdef+, varargs?)),\n" + 
"                              modifier*)>\n" + 
"\n" + 
"<!--end of funcprototype.element-->\n" + 
"\n" + 
"<!ATTLIST funcprototype\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of funcprototype.attlist-->\n" + 
"<!--end of funcprototype.module-->\n" + 
"\n" + 
"<!--doc:A function (subroutine) name and its return type.-->\n" + 
"<!ELEMENT funcdef  (#PCDATA\n" + 
"		| type\n" + 
"		| replaceable\n" + 
"		| function)*>\n" + 
"<!--end of funcdef.element-->\n" + 
"\n" + 
"<!ATTLIST funcdef\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of funcdef.attlist-->\n" + 
"<!--end of funcdef.module-->\n" + 
"\n" + 
"<!--doc:An empty element in a function synopsis indicating that the function in question takes no arguments.-->\n" + 
"<!ELEMENT void  EMPTY>\n" + 
"<!--end of void.element-->\n" + 
"\n" + 
"<!ATTLIST void\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of void.attlist-->\n" + 
"<!--end of void.module-->\n" + 
"\n" + 
"<!--doc:An empty element in a function synopsis indicating a variable number of arguments.-->\n" + 
"<!ELEMENT varargs  EMPTY>\n" + 
"<!--end of varargs.element-->\n" + 
"\n" + 
"<!ATTLIST varargs\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of varargs.attlist-->\n" + 
"<!--end of varargs.module-->\n" + 
"\n" + 
"<!-- Processing assumes that only one Parameter will appear in a\n" + 
"     ParamDef, and that FuncParams will be used at most once, for\n" + 
"     providing information on the \"inner parameters\" for parameters that\n" + 
"     are pointers to functions. -->\n" + 
"\n" + 
"<!--doc:Information about a function parameter in a programming language.-->\n" + 
"<!ELEMENT paramdef  (#PCDATA\n" + 
"                | initializer\n" + 
"		| type\n" + 
"		| replaceable\n" + 
"		| parameter\n" + 
"		| funcparams)*>\n" + 
"<!--end of paramdef.element-->\n" + 
"\n" + 
"<!ATTLIST paramdef\n" + 
"		choice		(opt\n" + 
"				|req)	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of paramdef.attlist-->\n" + 
"<!--end of paramdef.module-->\n" + 
"\n" + 
"<!--doc:Parameters for a function referenced through a function pointer in a synopsis.-->\n" + 
"<!ELEMENT funcparams  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of funcparams.element-->\n" + 
"\n" + 
"<!ATTLIST funcparams\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of funcparams.attlist-->\n" + 
"<!--end of funcparams.module-->\n" + 
"\n" + 
"<!-- LineAnnotation (defined in the Inlines section, below)-->\n" + 
"<!-- Replaceable (defined in the Inlines section, below)-->\n" + 
"<!-- Function (defined in the Inlines section, below)-->\n" + 
"<!-- Parameter (defined in the Inlines section, below)-->\n" + 
"<!--end of funcsynopsis.content.module-->\n" + 
"\n" + 
"<!-- ClassSynopsis ..................... -->\n" + 
"\n" + 
"<!--doc:The syntax summary for a class definition.-->\n" + 
"<!ELEMENT classsynopsis  ((ooclass|oointerface|ooexception)+,\n" + 
"                         (classsynopsisinfo\n" + 
"                          |fieldsynopsis|constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis )*)>\n" + 
"<!--end of classsynopsis.element-->\n" + 
"\n" + 
"<!ATTLIST classsynopsis\n" + 
"	language	CDATA	#IMPLIED\n" + 
"	class	(class|interface)	\"class\"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of classsynopsis.attlist-->\n" + 
"<!--end of classsynopsis.module-->\n" + 
"\n" + 
"<!--doc:Information supplementing the contents of a ClassSynopsis.-->\n" + 
"<!ELEMENT classsynopsisinfo  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|textobject|lineannotation)*>\n" + 
"<!--end of classsynopsisinfo.element-->\n" + 
"\n" + 
"<!ATTLIST classsynopsisinfo\n" + 
"		format		NOTATION\n" + 
"			(linespecific)	'linespecific'\n" + 
"         xml:space	(preserve)		#IMPLIED\n" + 
"         linenumbering	(numbered|unnumbered) 	#IMPLIED\n" + 
"         continuation	(continues|restarts)	#IMPLIED\n" + 
"         startinglinenumber	CDATA		#IMPLIED\n" + 
"         language	CDATA			#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of classsynopsisinfo.attlist-->\n" + 
"<!--end of classsynopsisinfo.module-->\n" + 
"\n" + 
"<!--doc:A class in an object-oriented programming language.-->\n" + 
"<!ELEMENT ooclass  ((modifier|package)*, classname)>\n" + 
"<!--end of ooclass.element-->\n" + 
"\n" + 
"<!ATTLIST ooclass\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of ooclass.attlist-->\n" + 
"<!--end of ooclass.module-->\n" + 
"\n" + 
"<!--doc:An interface in an object-oriented programming language.-->\n" + 
"<!ELEMENT oointerface  ((modifier|package)*, interfacename)>\n" + 
"<!--end of oointerface.element-->\n" + 
"\n" + 
"<!ATTLIST oointerface\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of oointerface.attlist-->\n" + 
"<!--end of oointerface.module-->\n" + 
"\n" + 
"<!--doc:An exception in an object-oriented programming language.-->\n" + 
"<!ELEMENT ooexception  ((modifier|package)*, exceptionname)>\n" + 
"<!--end of ooexception.element-->\n" + 
"\n" + 
"<!ATTLIST ooexception\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of ooexception.attlist-->\n" + 
"<!--end of ooexception.module-->\n" + 
"\n" + 
"<!--doc:Modifiers in a synopsis.-->\n" + 
"<!ELEMENT modifier  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of modifier.element-->\n" + 
"\n" + 
"<!ATTLIST modifier\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of modifier.attlist-->\n" + 
"<!--end of modifier.module-->\n" + 
"\n" + 
"<!--doc:The name of an interface.-->\n" + 
"<!ELEMENT interfacename  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of interfacename.element-->\n" + 
"\n" + 
"<!ATTLIST interfacename\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of interfacename.attlist-->\n" + 
"<!--end of interfacename.module-->\n" + 
"\n" + 
"<!--doc:The name of an exception.-->\n" + 
"<!ELEMENT exceptionname  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of exceptionname.element-->\n" + 
"\n" + 
"<!ATTLIST exceptionname\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of exceptionname.attlist-->\n" + 
"<!--end of exceptionname.module-->\n" + 
"\n" + 
"<!--doc:The name of a field in a class definition.-->\n" + 
"<!ELEMENT fieldsynopsis  (modifier*, type?, varname, initializer?)>\n" + 
"<!--end of fieldsynopsis.element-->\n" + 
"\n" + 
"<!ATTLIST fieldsynopsis\n" + 
"	language	CDATA	#IMPLIED\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of fieldsynopsis.attlist-->\n" + 
"<!--end of fieldsynopsis.module-->\n" + 
"\n" + 
"<!--doc:The initializer for a FieldSynopsis.-->\n" + 
"<!ELEMENT initializer  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of initializer.element-->\n" + 
"\n" + 
"<!ATTLIST initializer\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of initializer.attlist-->\n" + 
"<!--end of initializer.module-->\n" + 
"\n" + 
"<!--doc:A syntax summary for a constructor.-->\n" + 
"<!ELEMENT constructorsynopsis  (modifier*,\n" + 
"                               methodname?,\n" + 
"                               (methodparam+|void?),\n" + 
"                               exceptionname*)>\n" + 
"<!--end of constructorsynopsis.element-->\n" + 
"\n" + 
"<!ATTLIST constructorsynopsis\n" + 
"	language	CDATA	#IMPLIED\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of constructorsynopsis.attlist-->\n" + 
"<!--end of constructorsynopsis.module-->\n" + 
"\n" + 
"<!--doc:A syntax summary for a destructor.-->\n" + 
"<!ELEMENT destructorsynopsis  (modifier*,\n" + 
"                              methodname?,\n" + 
"                              (methodparam+|void?),\n" + 
"                              exceptionname*)>\n" + 
"<!--end of destructorsynopsis.element-->\n" + 
"\n" + 
"<!ATTLIST destructorsynopsis\n" + 
"	language	CDATA	#IMPLIED\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of destructorsynopsis.attlist-->\n" + 
"<!--end of destructorsynopsis.module-->\n" + 
"\n" + 
"<!--doc:A syntax summary for a method.-->\n" + 
"<!ELEMENT methodsynopsis  (modifier*,\n" + 
"                          (type|void)?,\n" + 
"                          methodname,\n" + 
"                          (methodparam+|void?),\n" + 
"                          exceptionname*,\n" + 
"                          modifier*)>\n" + 
"<!--end of methodsynopsis.element-->\n" + 
"\n" + 
"<!ATTLIST methodsynopsis\n" + 
"	language	CDATA	#IMPLIED\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of methodsynopsis.attlist-->\n" + 
"<!--end of methodsynopsis.module-->\n" + 
"\n" + 
"<!--doc:The name of a method.-->\n" + 
"<!ELEMENT methodname  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of methodname.element-->\n" + 
"\n" + 
"<!ATTLIST methodname\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of methodname.attlist-->\n" + 
"<!--end of methodname.module-->\n" + 
"\n" + 
"<!--doc:Parameters to a method.-->\n" + 
"<!ELEMENT methodparam  (modifier*,\n" + 
"                       type?,\n" + 
"                       ((parameter,initializer?)|funcparams),\n" + 
"                       modifier*)>\n" + 
"<!--end of methodparam.element-->\n" + 
"\n" + 
"<!ATTLIST methodparam\n" + 
"	choice		(opt\n" + 
"			|req\n" + 
"			|plain)		\"req\"\n" + 
"	rep		(norepeat\n" + 
"			|repeat)	\"norepeat\"\n" + 
"	id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"	role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of methodparam.attlist-->\n" + 
"<!--end of methodparam.module-->\n" + 
"<!--end of classsynopsis.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Document information entities and elements ........................... -->\n" + 
"\n" + 
"<!-- The document information elements include some elements that are\n" + 
"     currently used only in the document hierarchy module. They are\n" + 
"     defined here so that they will be available for use in customized\n" + 
"     document hierarchies. -->\n" + 
"\n" + 
"<!-- .................................. -->\n" + 
"\n" + 
"<!-- Ackno ............................ -->\n" + 
"\n" + 
"<!--doc:Acknowledgements in an Article.-->\n" + 
"<!ELEMENT ackno  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of ackno.element-->\n" + 
"\n" + 
"<!ATTLIST ackno\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of ackno.attlist-->\n" + 
"<!--end of ackno.module-->\n" + 
"\n" + 
"<!-- Address .......................... -->\n" + 
"\n" + 
"<!--doc:A real-world address, generally a postal address.-->\n" + 
"<!ELEMENT address  (#PCDATA|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|street|pob|postcode|city|state|country|phone\n" + 
"		|fax|email|otheraddr)*>\n" + 
"<!--end of address.element-->\n" + 
"\n" + 
"<!ATTLIST address\n" + 
"		format		NOTATION\n" + 
"			(linespecific)	'linespecific'\n" + 
"         xml:space	(preserve)		#IMPLIED\n" + 
"         linenumbering	(numbered|unnumbered) 	#IMPLIED\n" + 
"         continuation	(continues|restarts)	#IMPLIED\n" + 
"         startinglinenumber	CDATA		#IMPLIED\n" + 
"         language	CDATA			#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of address.attlist-->\n" + 
"<!--end of address.module-->\n" + 
"\n" + 
"<!--doc:A street address in an address.-->\n" + 
"<!ELEMENT street  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of street.element-->\n" + 
"\n" + 
"<!ATTLIST street\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of street.attlist-->\n" + 
"  <!--end of street.module-->\n" + 
"\n" + 
"<!--doc:A post office box in an address.-->\n" + 
"<!ELEMENT pob  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of pob.element-->\n" + 
"\n" + 
"<!ATTLIST pob\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of pob.attlist-->\n" + 
"  <!--end of pob.module-->\n" + 
"\n" + 
"<!--doc:A postal code in an address.-->\n" + 
"<!ELEMENT postcode  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of postcode.element-->\n" + 
"\n" + 
"<!ATTLIST postcode\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of postcode.attlist-->\n" + 
"  <!--end of postcode.module-->\n" + 
"\n" + 
"<!--doc:The name of a city in an address.-->\n" + 
"<!ELEMENT city  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of city.element-->\n" + 
"\n" + 
"<!ATTLIST city\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of city.attlist-->\n" + 
"  <!--end of city.module-->\n" + 
"\n" + 
"<!--doc:A state or province in an address.-->\n" + 
"<!ELEMENT state  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of state.element-->\n" + 
"\n" + 
"<!ATTLIST state\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of state.attlist-->\n" + 
"  <!--end of state.module-->\n" + 
"\n" + 
"<!--doc:The name of a country.-->\n" + 
"<!ELEMENT country  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of country.element-->\n" + 
"\n" + 
"<!ATTLIST country\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of country.attlist-->\n" + 
"  <!--end of country.module-->\n" + 
"\n" + 
"<!--doc:A telephone number.-->\n" + 
"<!ELEMENT phone  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of phone.element-->\n" + 
"\n" + 
"<!ATTLIST phone\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of phone.attlist-->\n" + 
"  <!--end of phone.module-->\n" + 
"\n" + 
"<!--doc:A fax number.-->\n" + 
"<!ELEMENT fax  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of fax.element-->\n" + 
"\n" + 
"<!ATTLIST fax\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of fax.attlist-->\n" + 
"  <!--end of fax.module-->\n" + 
"\n" + 
"  <!-- Email (defined in the Inlines section, below)-->\n" + 
"\n" + 
"<!--doc:Uncategorized information in address.-->\n" + 
"<!ELEMENT otheraddr  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of otheraddr.element-->\n" + 
"\n" + 
"<!ATTLIST otheraddr\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of otheraddr.attlist-->\n" + 
"  <!--end of otheraddr.module-->\n" + 
"<!--end of address.content.module-->\n" + 
"\n" + 
"<!-- Affiliation ...................... -->\n" + 
"\n" + 
"<!--doc:The institutional affiliation of an individual.-->\n" + 
"<!ELEMENT affiliation  (shortaffil?, jobtitle*, orgname?, orgdiv*,\n" + 
"		address*)>\n" + 
"<!--end of affiliation.element-->\n" + 
"\n" + 
"<!ATTLIST affiliation\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of affiliation.attlist-->\n" + 
"<!--end of affiliation.module-->\n" + 
"\n" + 
"<!--doc:A brief description of an affiliation.-->\n" + 
"<!ELEMENT shortaffil  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of shortaffil.element-->\n" + 
"\n" + 
"<!ATTLIST shortaffil\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of shortaffil.attlist-->\n" + 
"  <!--end of shortaffil.module-->\n" + 
"\n" + 
"<!--doc:The title of an individual in an organization.-->\n" + 
"<!ELEMENT jobtitle  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of jobtitle.element-->\n" + 
"\n" + 
"<!ATTLIST jobtitle\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of jobtitle.attlist-->\n" + 
"  <!--end of jobtitle.module-->\n" + 
"\n" + 
"  <!-- OrgName (defined elsewhere in this section)-->\n" + 
"\n" + 
"<!--doc:A division of an organization.-->\n" + 
"<!ELEMENT orgdiv  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of orgdiv.element-->\n" + 
"\n" + 
"<!ATTLIST orgdiv\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of orgdiv.attlist-->\n" + 
"  <!--end of orgdiv.module-->\n" + 
"\n" + 
"  <!-- Address (defined elsewhere in this section)-->\n" + 
"<!--end of affiliation.content.module-->\n" + 
"\n" + 
"<!-- ArtPageNums ...................... -->\n" + 
"\n" + 
"<!--doc:The page numbers of an article as published.-->\n" + 
"<!ELEMENT artpagenums  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of artpagenums.element-->\n" + 
"\n" + 
"<!ATTLIST artpagenums\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of artpagenums.attlist-->\n" + 
"<!--end of artpagenums.module-->\n" + 
"\n" + 
"<!-- PersonName -->\n" + 
"\n" + 
"<!--doc:The personal name of an individual.-->\n" + 
"<!ELEMENT personname  ((honorific|firstname|surname|lineage|othername)+)>\n" + 
"<!--end of personname.element-->\n" + 
"\n" + 
"<!ATTLIST personname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of personname.attlist-->\n" + 
"<!--end of personname.module-->\n" + 
"\n" + 
"<!-- Author ........................... -->\n" + 
"\n" + 
"<!--doc:The name of an individual author.-->\n" + 
"<!ELEMENT author  ((personname|(honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib )+),(personblurb|email|address)*)>\n" + 
"<!--end of author.element-->\n" + 
"\n" + 
"<!ATTLIST author\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of author.attlist-->\n" + 
"<!--(see \"Personal identity elements\" for %person.ident.mix;)-->\n" + 
"<!--end of author.module-->\n" + 
"\n" + 
"<!-- AuthorGroup ...................... -->\n" + 
"\n" + 
"<!--doc:Wrapper for author information when a document has multiple authors or collabarators.-->\n" + 
"<!ELEMENT authorgroup  ((author|editor|collab|corpauthor|corpcredit|othercredit)+)>\n" + 
"<!--end of authorgroup.element-->\n" + 
"\n" + 
"<!ATTLIST authorgroup\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of authorgroup.attlist-->\n" + 
"<!--end of authorgroup.module-->\n" + 
"\n" + 
"  <!-- Author (defined elsewhere in this section)-->\n" + 
"  <!-- Editor (defined elsewhere in this section)-->\n" + 
"\n" + 
"<!--doc:Identifies a collaborator.-->\n" + 
"<!ELEMENT collab  (collabname, affiliation*)>\n" + 
"<!--end of collab.element-->\n" + 
"\n" + 
"<!ATTLIST collab\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of collab.attlist-->\n" + 
"  <!--end of collab.module-->\n" + 
"\n" + 
"<!--doc:The name of a collaborator.-->\n" + 
"<!ELEMENT collabname  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of collabname.element-->\n" + 
"\n" + 
"<!ATTLIST collabname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of collabname.attlist-->\n" + 
"    <!--end of collabname.module-->\n" + 
"\n" + 
"    <!-- Affiliation (defined elsewhere in this section)-->\n" + 
"  <!--end of collab.content.module-->\n" + 
"\n" + 
"  <!-- CorpAuthor (defined elsewhere in this section)-->\n" + 
"  <!-- OtherCredit (defined elsewhere in this section)-->\n" + 
"\n" + 
"<!--end of authorgroup.content.module-->\n" + 
"\n" + 
"<!-- AuthorInitials ................... -->\n" + 
"\n" + 
"<!--doc:The initials or other short identifier for an author.-->\n" + 
"<!ELEMENT authorinitials  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of authorinitials.element-->\n" + 
"\n" + 
"<!ATTLIST authorinitials\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of authorinitials.attlist-->\n" + 
"<!--end of authorinitials.module-->\n" + 
"\n" + 
"<!-- ConfGroup ........................ -->\n" + 
"\n" + 
"<!--doc:A wrapper for document meta-information about a conference.-->\n" + 
"<!ELEMENT confgroup  ((confdates|conftitle|confnum|address|confsponsor)*)>\n" + 
"<!--end of confgroup.element-->\n" + 
"\n" + 
"<!ATTLIST confgroup\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of confgroup.attlist-->\n" + 
"<!--end of confgroup.module-->\n" + 
"\n" + 
"<!--doc:The dates of a conference for which a document was written.-->\n" + 
"<!ELEMENT confdates  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of confdates.element-->\n" + 
"\n" + 
"<!ATTLIST confdates\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of confdates.attlist-->\n" + 
"  <!--end of confdates.module-->\n" + 
"\n" + 
"<!--doc:The title of a conference for which a document was written.-->\n" + 
"<!ELEMENT conftitle  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of conftitle.element-->\n" + 
"\n" + 
"<!ATTLIST conftitle\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of conftitle.attlist-->\n" + 
"  <!--end of conftitle.module-->\n" + 
"\n" + 
"<!--doc:An identifier, frequently numerical, associated with a conference for which a document was written.-->\n" + 
"<!ELEMENT confnum  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of confnum.element-->\n" + 
"\n" + 
"<!ATTLIST confnum\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of confnum.attlist-->\n" + 
"  <!--end of confnum.module-->\n" + 
"\n" + 
"  <!-- Address (defined elsewhere in this section)-->\n" + 
"\n" + 
"<!--doc:The sponsor of a conference for which a document was written.-->\n" + 
"<!ELEMENT confsponsor  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of confsponsor.element-->\n" + 
"\n" + 
"<!ATTLIST confsponsor\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of confsponsor.attlist-->\n" + 
"  <!--end of confsponsor.module-->\n" + 
"<!--end of confgroup.content.module-->\n" + 
"\n" + 
"<!-- ContractNum ...................... -->\n" + 
"\n" + 
"<!--doc:The contract number of a document.-->\n" + 
"<!ELEMENT contractnum  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of contractnum.element-->\n" + 
"\n" + 
"<!ATTLIST contractnum\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of contractnum.attlist-->\n" + 
"<!--end of contractnum.module-->\n" + 
"\n" + 
"<!-- ContractSponsor .................. -->\n" + 
"\n" + 
"<!--doc:The sponsor of a contract.-->\n" + 
"<!ELEMENT contractsponsor  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of contractsponsor.element-->\n" + 
"\n" + 
"<!ATTLIST contractsponsor\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of contractsponsor.attlist-->\n" + 
"<!--end of contractsponsor.module-->\n" + 
"\n" + 
"<!-- Copyright ........................ -->\n" + 
"\n" + 
"<!--doc:Copyright information about a document.-->\n" + 
"<!ELEMENT copyright  (year+, holder*)>\n" + 
"<!--end of copyright.element-->\n" + 
"\n" + 
"<!ATTLIST copyright\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of copyright.attlist-->\n" + 
"<!--end of copyright.module-->\n" + 
"\n" + 
"<!--doc:The year of publication of a document.-->\n" + 
"<!ELEMENT year  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of year.element-->\n" + 
"\n" + 
"<!ATTLIST year\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of year.attlist-->\n" + 
"  <!--end of year.module-->\n" + 
"\n" + 
"<!--doc:The name of the individual or organization that holds a copyright.-->\n" + 
"<!ELEMENT holder  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of holder.element-->\n" + 
"\n" + 
"<!ATTLIST holder\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of holder.attlist-->\n" + 
"  <!--end of holder.module-->\n" + 
"<!--end of copyright.content.module-->\n" + 
"\n" + 
"<!-- CorpAuthor ....................... -->\n" + 
"\n" + 
"<!--doc:A corporate author, as opposed to an individual.-->\n" + 
"<!ELEMENT corpauthor  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of corpauthor.element-->\n" + 
"\n" + 
"<!ATTLIST corpauthor\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of corpauthor.attlist-->\n" + 
"<!--end of corpauthor.module-->\n" + 
"\n" + 
"<!-- CorpCredit ...................... -->\n" + 
"\n" + 
"<!--doc:A corporation or organization credited in a document.-->\n" + 
"<!ELEMENT corpcredit  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of corpcredit.element-->\n" + 
"\n" + 
"<!ATTLIST corpcredit\n" + 
"		class	(graphicdesigner\n" + 
"			|productioneditor\n" + 
"			|copyeditor\n" + 
"			|technicaleditor\n" + 
"			|translator\n" + 
"			|other)			#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of corpcredit.attlist-->\n" + 
"<!--end of corpcredit.module-->\n" + 
"\n" + 
"<!-- CorpName ......................... -->\n" + 
"\n" + 
"<!--doc:The name of a corporation.-->\n" + 
"<!ELEMENT corpname  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of corpname.element-->\n" + 
"\n" + 
"<!ATTLIST corpname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of corpname.attlist-->\n" + 
"<!--end of corpname.module-->\n" + 
"\n" + 
"<!-- Date ............................. -->\n" + 
"\n" + 
"<!--doc:The date of publication or revision of a document.-->\n" + 
"<!ELEMENT date  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of date.element-->\n" + 
"\n" + 
"<!ATTLIST date\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of date.attlist-->\n" + 
"<!--end of date.module-->\n" + 
"\n" + 
"<!-- Edition .......................... -->\n" + 
"\n" + 
"<!--doc:The name or number of an edition of a document.-->\n" + 
"<!ELEMENT edition  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of edition.element-->\n" + 
"\n" + 
"<!ATTLIST edition\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of edition.attlist-->\n" + 
"<!--end of edition.module-->\n" + 
"\n" + 
"<!-- Editor ........................... -->\n" + 
"\n" + 
"<!--doc:The name of the editor of a document.-->\n" + 
"<!ELEMENT editor  ((personname|(honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib )+),(personblurb|email|address)*)>\n" + 
"<!--end of editor.element-->\n" + 
"\n" + 
"<!ATTLIST editor\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of editor.attlist-->\n" + 
"  <!--(see \"Personal identity elements\" for %person.ident.mix;)-->\n" + 
"<!--end of editor.module-->\n" + 
"\n" + 
"<!-- ISBN ............................. -->\n" + 
"\n" + 
"<!--doc:The International Standard Book Number of a document.-->\n" + 
"<!ELEMENT isbn  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of isbn.element-->\n" + 
"\n" + 
"<!ATTLIST isbn\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of isbn.attlist-->\n" + 
"<!--end of isbn.module-->\n" + 
"\n" + 
"<!-- ISSN ............................. -->\n" + 
"\n" + 
"<!--doc:The International Standard Serial Number of a periodical.-->\n" + 
"<!ELEMENT issn  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of issn.element-->\n" + 
"\n" + 
"<!ATTLIST issn\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of issn.attlist-->\n" + 
"<!--end of issn.module-->\n" + 
"\n" + 
"<!-- BiblioId ................. -->\n" + 
"\n" + 
"<!--doc:An identifier for a document.-->\n" + 
"<!ELEMENT biblioid  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of biblioid.element-->\n" + 
"\n" + 
"<!ATTLIST biblioid\n" + 
"		class	(uri\n" + 
"                         |doi\n" + 
"                         |isbn\n" + 
"			 |isrn\n" + 
"                         |issn\n" + 
"                         |libraryofcongress\n" + 
"                         |pubnumber\n" + 
"                         |other)	#IMPLIED\n" + 
"		otherclass	CDATA	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of biblioid.attlist-->\n" + 
"<!--end of biblioid.module-->\n" + 
"\n" + 
"<!-- CiteBiblioId ................. -->\n" + 
"\n" + 
"<!--doc:A citation of a bibliographic identifier.-->\n" + 
"<!ELEMENT citebiblioid  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of citebiblioid.element-->\n" + 
"\n" + 
"<!ATTLIST citebiblioid\n" + 
"		class	(uri\n" + 
"                         |doi\n" + 
"                         |isbn\n" + 
"			 |isrn\n" + 
"                         |issn\n" + 
"                         |libraryofcongress\n" + 
"                         |pubnumber\n" + 
"                         |other)	#IMPLIED\n" + 
"		otherclass	CDATA	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of citebiblioid.attlist-->\n" + 
"<!--end of citebiblioid.module-->\n" + 
"\n" + 
"<!-- BiblioSource ................. -->\n" + 
"\n" + 
"<!--doc:The source of a document.-->\n" + 
"<!ELEMENT bibliosource  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of bibliosource.element-->\n" + 
"\n" + 
"<!ATTLIST bibliosource\n" + 
"		class	(uri\n" + 
"                         |doi\n" + 
"                         |isbn\n" + 
"			 |isrn\n" + 
"                         |issn\n" + 
"                         |libraryofcongress\n" + 
"                         |pubnumber\n" + 
"                         |other)	#IMPLIED\n" + 
"		otherclass	CDATA	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bibliosource.attlist-->\n" + 
"<!--end of bibliosource.module-->\n" + 
"\n" + 
"<!-- BiblioRelation ................. -->\n" + 
"\n" + 
"<!--doc:The relationship of a document to another.-->\n" + 
"<!ELEMENT bibliorelation  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of bibliorelation.element-->\n" + 
"\n" + 
"<!ATTLIST bibliorelation\n" + 
"		class	(uri\n" + 
"                         |doi\n" + 
"                         |isbn\n" + 
"			 |isrn\n" + 
"                         |issn\n" + 
"                         |libraryofcongress\n" + 
"                         |pubnumber\n" + 
"                         |other)	#IMPLIED\n" + 
"		otherclass	CDATA	#IMPLIED\n" + 
"		type    (isversionof\n" + 
"                         |hasversion\n" + 
"                         |isreplacedby\n" + 
"                         |replaces\n" + 
"                         |isrequiredby\n" + 
"                         |requires\n" + 
"                         |ispartof\n" + 
"                         |haspart\n" + 
"                         |isreferencedby\n" + 
"                         |references\n" + 
"                         |isformatof\n" + 
"                         |hasformat\n" + 
"                         |othertype\n" + 
"                         )       #IMPLIED\n" + 
"		othertype	CDATA	#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bibliorelation.attlist-->\n" + 
"<!--end of bibliorelation.module-->\n" + 
"\n" + 
"<!-- BiblioCoverage ................. -->\n" + 
"\n" + 
"<!--doc:The spatial or temporal coverage of a document.-->\n" + 
"<!ELEMENT bibliocoverage  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of bibliocoverage.element-->\n" + 
"\n" + 
"<!ATTLIST bibliocoverage\n" + 
"		spatial	(dcmipoint|iso3166|dcmibox|tgn|otherspatial)	#IMPLIED\n" + 
"		otherspatial	CDATA	#IMPLIED\n" + 
"		temporal (dcmiperiod|w3c-dtf|othertemporal) #IMPLIED\n" + 
"		othertemporal	CDATA	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bibliocoverage.attlist-->\n" + 
"<!--end of bibliocoverage.module-->\n" + 
"\n" + 
"<!-- InvPartNumber .................... -->\n" + 
"\n" + 
"<!--doc:An inventory part number.-->\n" + 
"<!ELEMENT invpartnumber  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of invpartnumber.element-->\n" + 
"\n" + 
"<!ATTLIST invpartnumber\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of invpartnumber.attlist-->\n" + 
"<!--end of invpartnumber.module-->\n" + 
"\n" + 
"<!-- IssueNum ......................... -->\n" + 
"\n" + 
"<!--doc:The number of an issue of a journal.-->\n" + 
"<!ELEMENT issuenum  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of issuenum.element-->\n" + 
"\n" + 
"<!ATTLIST issuenum\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of issuenum.attlist-->\n" + 
"<!--end of issuenum.module-->\n" + 
"\n" + 
"<!-- LegalNotice ...................... -->\n" + 
"\n" + 
"<!--doc:A statement of legal obligations or requirements.-->\n" + 
"<!ELEMENT legalnotice  (blockinfo?, title?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot\n" + 
"		|formalpara|para|simpara 		|blockquote\n" + 
"		|indexterm         |beginpage\n" + 
"		)+)\n" + 
"		>\n" + 
"<!--end of legalnotice.element-->\n" + 
"\n" + 
"<!ATTLIST legalnotice\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of legalnotice.attlist-->\n" + 
"<!--end of legalnotice.module-->\n" + 
"\n" + 
"<!-- ModeSpec ......................... -->\n" + 
"\n" + 
"<!--doc:Application-specific information necessary for the completion of an OLink.-->\n" + 
"<!ELEMENT modespec  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*\n" + 
"		>\n" + 
"<!--end of modespec.element-->\n" + 
"\n" + 
"<!-- Application: Type of action required for completion\n" + 
"		of the links to which the ModeSpec is relevant (e.g.,\n" + 
"		retrieval query) -->\n" + 
"\n" + 
"<!ATTLIST modespec\n" + 
"		application	NOTATION\n" + 
"				(BMP| CGM-CHAR | CGM-BINARY | CGM-CLEAR | DITROFF | DVI\n" + 
"		| EPS | EQN | FAX | GIF | GIF87a | GIF89a\n" + 
"		| JPG | JPEG | IGES | PCX\n" + 
"		| PIC | PNG | PS | SGML | TBL | TEX | TIFF | WMF | WPG\n" + 
"                | SVG | PDF | SWF\n" + 
"		| linespecific\n" + 
"		)	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of modespec.attlist-->\n" + 
"<!--end of modespec.module-->\n" + 
"\n" + 
"<!-- OrgName .......................... -->\n" + 
"\n" + 
"<!--doc:The name of an organization other than a corporation.-->\n" + 
"<!ELEMENT orgname  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of orgname.element-->\n" + 
"\n" + 
"<!ATTLIST orgname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		class	(corporation|nonprofit|consortium|informal|other)	#IMPLIED\n" + 
"		otherclass	CDATA			#IMPLIED\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of orgname.attlist-->\n" + 
"<!--end of orgname.module-->\n" + 
"\n" + 
"<!-- OtherCredit ...................... -->\n" + 
"\n" + 
"<!--doc:A person or entity, other than an author or editor, credited in a document.-->\n" + 
"<!ELEMENT othercredit  ((personname|(honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib )+),\n" + 
"                            (personblurb|email|address)*)>\n" + 
"<!--end of othercredit.element-->\n" + 
"\n" + 
"<!ATTLIST othercredit\n" + 
"		class	(graphicdesigner\n" + 
"			|productioneditor\n" + 
"			|copyeditor\n" + 
"			|technicaleditor\n" + 
"			|translator\n" + 
"			|other)			#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of othercredit.attlist-->\n" + 
"  <!--(see \"Personal identity elements\" for %person.ident.mix;)-->\n" + 
"<!--end of othercredit.module-->\n" + 
"\n" + 
"<!-- PageNums ......................... -->\n" + 
"\n" + 
"<!--doc:The numbers of the pages in a book, for use in a bibliographic entry.-->\n" + 
"<!ELEMENT pagenums  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of pagenums.element-->\n" + 
"\n" + 
"<!ATTLIST pagenums\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of pagenums.attlist-->\n" + 
"<!--end of pagenums.module-->\n" + 
"\n" + 
"<!-- Personal identity elements ....... -->\n" + 
"\n" + 
"<!-- These elements are used only within Author, Editor, and\n" + 
"OtherCredit. -->\n" + 
"\n" + 
"<!--doc:A summary of the contributions made to a document by a credited source.-->\n" + 
"<!ELEMENT contrib  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of contrib.element-->\n" + 
"\n" + 
"<!ATTLIST contrib\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of contrib.attlist-->\n" + 
"  <!--end of contrib.module-->\n" + 
"\n" + 
"<!--doc:The first name of a person.-->\n" + 
"<!ELEMENT firstname  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of firstname.element-->\n" + 
"\n" + 
"<!ATTLIST firstname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of firstname.attlist-->\n" + 
"  <!--end of firstname.module-->\n" + 
"\n" + 
"<!--doc:The title of a person.-->\n" + 
"<!ELEMENT honorific  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of honorific.element-->\n" + 
"\n" + 
"<!ATTLIST honorific\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of honorific.attlist-->\n" + 
"  <!--end of honorific.module-->\n" + 
"\n" + 
"<!--doc:The portion of a person's name indicating a relationship to ancestors.-->\n" + 
"<!ELEMENT lineage  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of lineage.element-->\n" + 
"\n" + 
"<!ATTLIST lineage\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of lineage.attlist-->\n" + 
"  <!--end of lineage.module-->\n" + 
"\n" + 
"<!--doc:A component of a persons name that is not a first name, surname, or lineage.-->\n" + 
"<!ELEMENT othername  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of othername.element-->\n" + 
"\n" + 
"<!ATTLIST othername\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of othername.attlist-->\n" + 
"  <!--end of othername.module-->\n" + 
"\n" + 
"<!--doc:A family name; in western cultures the last name.-->\n" + 
"<!ELEMENT surname  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of surname.element-->\n" + 
"\n" + 
"<!ATTLIST surname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of surname.attlist-->\n" + 
"  <!--end of surname.module-->\n" + 
"<!--end of person.ident.module-->\n" + 
"\n" + 
"<!-- PrintHistory ..................... -->\n" + 
"\n" + 
"<!--doc:The printing history of a document.-->\n" + 
"<!ELEMENT printhistory  ((formalpara|para|simpara )+)>\n" + 
"<!--end of printhistory.element-->\n" + 
"\n" + 
"<!ATTLIST printhistory\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of printhistory.attlist-->\n" + 
"<!--end of printhistory.module-->\n" + 
"\n" + 
"<!-- ProductName ...................... -->\n" + 
"\n" + 
"<!--doc:The formal name of a product.-->\n" + 
"<!ELEMENT productname  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of productname.element-->\n" + 
"\n" + 
"<!-- Class: More precisely identifies the item the element names -->\n" + 
"\n" + 
"<!ATTLIST productname\n" + 
"		class		(service\n" + 
"				|trade\n" + 
"				|registered\n" + 
"				|copyright)	'trade'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of productname.attlist-->\n" + 
"<!--end of productname.module-->\n" + 
"\n" + 
"<!-- ProductNumber .................... -->\n" + 
"\n" + 
"<!--doc:A number assigned to a product.-->\n" + 
"<!ELEMENT productnumber  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of productnumber.element-->\n" + 
"\n" + 
"<!ATTLIST productnumber\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of productnumber.attlist-->\n" + 
"<!--end of productnumber.module-->\n" + 
"\n" + 
"<!-- PubDate .......................... -->\n" + 
"\n" + 
"<!--doc:The date of publication of a document.-->\n" + 
"<!ELEMENT pubdate  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of pubdate.element-->\n" + 
"\n" + 
"<!ATTLIST pubdate\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of pubdate.attlist-->\n" + 
"<!--end of pubdate.module-->\n" + 
"\n" + 
"<!-- Publisher ........................ -->\n" + 
"\n" + 
"<!--doc:The publisher of a document.-->\n" + 
"<!ELEMENT publisher  (publishername, address*)>\n" + 
"<!--end of publisher.element-->\n" + 
"\n" + 
"<!ATTLIST publisher\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of publisher.attlist-->\n" + 
"<!--end of publisher.module-->\n" + 
"\n" + 
"<!--doc:The name of the publisher of a document.-->\n" + 
"<!ELEMENT publishername  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of publishername.element-->\n" + 
"\n" + 
"<!ATTLIST publishername\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of publishername.attlist-->\n" + 
"  <!--end of publishername.module-->\n" + 
"\n" + 
"  <!-- Address (defined elsewhere in this section)-->\n" + 
"<!--end of publisher.content.module-->\n" + 
"\n" + 
"<!-- PubsNumber ....................... -->\n" + 
"\n" + 
"<!--doc:A number assigned to a publication other than an ISBN or ISSN or inventory part number.-->\n" + 
"<!ELEMENT pubsnumber  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of pubsnumber.element-->\n" + 
"\n" + 
"<!ATTLIST pubsnumber\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of pubsnumber.attlist-->\n" + 
"<!--end of pubsnumber.module-->\n" + 
"\n" + 
"<!-- ReleaseInfo ...................... -->\n" + 
"\n" + 
"<!--doc:Information about a particular release of a document.-->\n" + 
"<!ELEMENT releaseinfo  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of releaseinfo.element-->\n" + 
"\n" + 
"<!ATTLIST releaseinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of releaseinfo.attlist-->\n" + 
"<!--end of releaseinfo.module-->\n" + 
"\n" + 
"<!-- RevHistory ....................... -->\n" + 
"\n" + 
"<!--doc:A history of the revisions to a document.-->\n" + 
"<!ELEMENT revhistory  (revision+)>\n" + 
"<!--end of revhistory.element-->\n" + 
"\n" + 
"<!ATTLIST revhistory\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of revhistory.attlist-->\n" + 
"<!--end of revhistory.module-->\n" + 
"\n" + 
"<!--doc:An entry describing a single revision in the history of the revisions to a document.-->\n" + 
"<!ELEMENT revision  (revnumber?, date, (author|authorinitials)*,\n" + 
"                    (revremark|revdescription)?)>\n" + 
"<!--end of revision.element-->\n" + 
"\n" + 
"<!ATTLIST revision\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of revision.attlist-->\n" + 
"<!--end of revision.module-->\n" + 
"\n" + 
"<!--doc:A document revision number.-->\n" + 
"<!ELEMENT revnumber  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of revnumber.element-->\n" + 
"\n" + 
"<!ATTLIST revnumber\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of revnumber.attlist-->\n" + 
"<!--end of revnumber.module-->\n" + 
"\n" + 
"<!-- Date (defined elsewhere in this section)-->\n" + 
"<!-- AuthorInitials (defined elsewhere in this section)-->\n" + 
"\n" + 
"<!--doc:A description of a revision to a document.-->\n" + 
"<!ELEMENT revremark  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of revremark.element-->\n" + 
"\n" + 
"<!ATTLIST revremark\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of revremark.attlist-->\n" + 
"<!--end of revremark.module-->\n" + 
"\n" + 
"<!--doc:A extended description of a revision to a document.-->\n" + 
"<!ELEMENT revdescription  ((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|procedure\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"\n" + 
"		|indexterm\n" + 
"		)+)>\n" + 
"<!--end of revdescription.element-->\n" + 
"\n" + 
"<!ATTLIST revdescription\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of revdescription.attlist-->\n" + 
"<!--end of revdescription.module-->\n" + 
"<!--end of revhistory.content.module-->\n" + 
"\n" + 
"<!-- SeriesVolNums .................... -->\n" + 
"\n" + 
"<!--doc:Numbers of the volumes in a series of books.-->\n" + 
"<!ELEMENT seriesvolnums  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of seriesvolnums.element-->\n" + 
"\n" + 
"<!ATTLIST seriesvolnums\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of seriesvolnums.attlist-->\n" + 
"<!--end of seriesvolnums.module-->\n" + 
"\n" + 
"<!-- VolumeNum ........................ -->\n" + 
"\n" + 
"<!--doc:The volume number of a document in a set (as of books in a set or articles in a journal).-->\n" + 
"<!ELEMENT volumenum  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of volumenum.element-->\n" + 
"\n" + 
"<!ATTLIST volumenum\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of volumenum.attlist-->\n" + 
"<!--end of volumenum.module-->\n" + 
"\n" + 
"<!-- .................................. -->\n" + 
"\n" + 
"<!--end of docinfo.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Inline, link, and ubiquitous elements ................................ -->\n" + 
"\n" + 
"<!-- Technical and computer terms ......................................... -->\n" + 
"\n" + 
"<!--doc:A graphical user interface (GUI) keyboard shortcut.-->\n" + 
"<!ELEMENT accel  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of accel.element-->\n" + 
"\n" + 
"<!ATTLIST accel\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of accel.attlist-->\n" + 
"<!--end of accel.module-->\n" + 
"\n" + 
"<!--doc:A response to a user event.-->\n" + 
"<!ELEMENT action  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of action.element-->\n" + 
"\n" + 
"<!ATTLIST action\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of action.attlist-->\n" + 
"<!--end of action.module-->\n" + 
"\n" + 
"<!--doc:The name of a software program.-->\n" + 
"<!ELEMENT application  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of application.element-->\n" + 
"\n" + 
"<!ATTLIST application\n" + 
"		class 		(hardware\n" + 
"				|software)	#IMPLIED\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of application.attlist-->\n" + 
"<!--end of application.module-->\n" + 
"\n" + 
"<!--doc:The name of a class, in the object-oriented programming sense.-->\n" + 
"<!ELEMENT classname  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of classname.element-->\n" + 
"\n" + 
"<!ATTLIST classname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of classname.attlist-->\n" + 
"<!--end of classname.module-->\n" + 
"\n" + 
"<!--doc:A package.-->\n" + 
"<!ELEMENT package  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of package.element-->\n" + 
"\n" + 
"<!ATTLIST package\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of package.attlist-->\n" + 
"<!--end of package.module-->\n" + 
"\n" + 
"<!-- CO is a callout area of the LineColumn unit type (a single character\n" + 
"     position); the position is directly indicated by the location of CO. -->\n" + 
"\n" + 
"<!--doc:The location of a callout embedded in text.-->\n" + 
"<!ELEMENT co  EMPTY>\n" + 
"<!--end of co.element-->\n" + 
"\n" + 
"<!-- bug number/symbol override or initialization -->\n" + 
"<!-- to any related information -->\n" + 
"\n" + 
"<!ATTLIST co\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		linkends	IDREFS		#IMPLIED\n" + 
"		id		ID		#REQUIRED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of co.attlist-->\n" + 
"<!--end of co.module-->\n" + 
"\n" + 
"<!-- COREF is a reference to a CO -->\n" + 
"\n" + 
"<!--doc:A cross reference to a co.-->\n" + 
"<!ELEMENT coref  EMPTY>\n" + 
"<!--end of coref.element-->\n" + 
"\n" + 
"<!-- bug number/symbol override or initialization -->\n" + 
"<!-- to any related information -->\n" + 
"\n" + 
"<!ATTLIST coref\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		linkend	IDREF		#REQUIRED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of coref.attlist-->\n" + 
"<!--end of coref.module-->\n" + 
"\n" + 
"<!--doc:The name of an executable program or other software command.-->\n" + 
"<!ELEMENT command  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of command.element-->\n" + 
"\n" + 
"<!ATTLIST command\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of command.attlist-->\n" + 
"<!--end of command.module-->\n" + 
"\n" + 
"<!--doc:Data, generally text, displayed or presented by a computer.-->\n" + 
"<!ELEMENT computeroutput  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|co)*>\n" + 
"<!--end of computeroutput.element-->\n" + 
"\n" + 
"<!ATTLIST computeroutput\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of computeroutput.attlist-->\n" + 
"<!--end of computeroutput.module-->\n" + 
"\n" + 
"<!--doc:The name of a database, or part of a database.-->\n" + 
"<!ELEMENT database  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of database.element-->\n" + 
"\n" + 
"<!-- Class: Type of database the element names; no default -->\n" + 
"\n" + 
"<!ATTLIST database\n" + 
"		class 		(name\n" + 
"				|table\n" + 
"				|field\n" + 
"				|key1\n" + 
"				|key2\n" + 
"				|record\n" + 
"                                |index\n" + 
"                                |view\n" + 
"                                |primarykey\n" + 
"                                |secondarykey\n" + 
"                                |foreignkey\n" + 
"                                |altkey\n" + 
"                                |procedure\n" + 
"                                |datatype\n" + 
"                                |constraint\n" + 
"                                |rule\n" + 
"                                |user\n" + 
"                                |group)	#IMPLIED\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of database.attlist-->\n" + 
"<!--end of database.module-->\n" + 
"\n" + 
"<!--doc:An email address.-->\n" + 
"<!ELEMENT email  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of email.element-->\n" + 
"\n" + 
"<!ATTLIST email\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of email.attlist-->\n" + 
"<!--end of email.module-->\n" + 
"\n" + 
"<!--doc:A software environment variable.-->\n" + 
"<!ELEMENT envar  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of envar.element-->\n" + 
"\n" + 
"<!ATTLIST envar\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of envar.attlist-->\n" + 
"<!--end of envar.module-->\n" + 
"\n" + 
"<!--doc:An error code.-->\n" + 
"<!ELEMENT errorcode  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of errorcode.element-->\n" + 
"\n" + 
"<!ATTLIST errorcode\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of errorcode.attlist-->\n" + 
"<!--end of errorcode.module-->\n" + 
"\n" + 
"<!--doc:An error name.-->\n" + 
"<!ELEMENT errorname  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of errorname.element-->\n" + 
"\n" + 
"<!ATTLIST errorname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of errorname.attlist-->\n" + 
"<!--end of errorname.module-->\n" + 
"\n" + 
"<!--doc:An error message..-->\n" + 
"<!ELEMENT errortext  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of errortext.element-->\n" + 
"\n" + 
"<!ATTLIST errortext\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of errortext.attlist-->\n" + 
"<!--end of errortext.module-->\n" + 
"\n" + 
"<!--doc:The classification of an error message.-->\n" + 
"<!ELEMENT errortype  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of errortype.element-->\n" + 
"\n" + 
"<!ATTLIST errortype\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of errortype.attlist-->\n" + 
"<!--end of errortype.module-->\n" + 
"\n" + 
"<!--doc:The name of a file.-->\n" + 
"<!ELEMENT filename  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of filename.element-->\n" + 
"\n" + 
"<!-- Class: Type of filename the element names; no default -->\n" + 
"<!-- Path: Search path (possibly system-specific) in which\n" + 
"		file can be found -->\n" + 
"\n" + 
"<!ATTLIST filename\n" + 
"		class		(headerfile\n" + 
"                                |partition\n" + 
"                                |devicefile\n" + 
"                                |libraryfile\n" + 
"                                |directory\n" + 
"                                |extension\n" + 
"				|symlink)       #IMPLIED\n" + 
"		path		CDATA		#IMPLIED\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of filename.attlist-->\n" + 
"<!--end of filename.module-->\n" + 
"\n" + 
"<!--doc:The name of a function or subroutine, as in a programming language.-->\n" + 
"<!ELEMENT function  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of function.element-->\n" + 
"\n" + 
"<!ATTLIST function\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of function.attlist-->\n" + 
"<!--end of function.module-->\n" + 
"\n" + 
"<!--doc:The text on a button in a GUI.-->\n" + 
"<!ELEMENT guibutton  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|accel|superscript|subscript)*>\n" + 
"<!--end of guibutton.element-->\n" + 
"\n" + 
"<!ATTLIST guibutton\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of guibutton.attlist-->\n" + 
"<!--end of guibutton.module-->\n" + 
"\n" + 
"<!--doc:Graphic and/or text appearing as a icon in a GUI.-->\n" + 
"<!ELEMENT guiicon  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|accel|superscript|subscript)*>\n" + 
"<!--end of guiicon.element-->\n" + 
"\n" + 
"<!ATTLIST guiicon\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of guiicon.attlist-->\n" + 
"<!--end of guiicon.module-->\n" + 
"\n" + 
"<!--doc:The text of a label in a GUI.-->\n" + 
"<!ELEMENT guilabel  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|accel|superscript|subscript)*>\n" + 
"<!--end of guilabel.element-->\n" + 
"\n" + 
"<!ATTLIST guilabel\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of guilabel.attlist-->\n" + 
"<!--end of guilabel.module-->\n" + 
"\n" + 
"<!--doc:The name of a menu in a GUI.-->\n" + 
"<!ELEMENT guimenu  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|accel|superscript|subscript)*>\n" + 
"<!--end of guimenu.element-->\n" + 
"\n" + 
"<!ATTLIST guimenu\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of guimenu.attlist-->\n" + 
"<!--end of guimenu.module-->\n" + 
"\n" + 
"<!--doc:The name of a terminal menu item in a GUI.-->\n" + 
"<!ELEMENT guimenuitem  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|accel|superscript|subscript)*>\n" + 
"<!--end of guimenuitem.element-->\n" + 
"\n" + 
"<!ATTLIST guimenuitem\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of guimenuitem.attlist-->\n" + 
"<!--end of guimenuitem.module-->\n" + 
"\n" + 
"<!--doc:The name of a submenu in a GUI.-->\n" + 
"<!ELEMENT guisubmenu  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|accel|superscript|subscript)*>\n" + 
"<!--end of guisubmenu.element-->\n" + 
"\n" + 
"<!ATTLIST guisubmenu\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of guisubmenu.attlist-->\n" + 
"<!--end of guisubmenu.module-->\n" + 
"\n" + 
"<!--doc:A physical part of a computer system.-->\n" + 
"<!ELEMENT hardware  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of hardware.element-->\n" + 
"\n" + 
"<!ATTLIST hardware\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of hardware.attlist-->\n" + 
"<!--end of hardware.module-->\n" + 
"\n" + 
"<!--doc:An element of a GUI.-->\n" + 
"<!ELEMENT interface  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|accel)*>\n" + 
"<!--end of interface.element-->\n" + 
"\n" + 
"<!-- Class: Type of the Interface item; no default -->\n" + 
"\n" + 
"<!ATTLIST interface\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of interface.attlist-->\n" + 
"<!--end of interface.module-->\n" + 
"\n" + 
"<!--doc:The text printed on a key on a keyboard.-->\n" + 
"<!ELEMENT keycap  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of keycap.element-->\n" + 
"\n" + 
"<!ATTLIST keycap\n" + 
"		function	(alt\n" + 
"				|control\n" + 
"				|shift\n" + 
"				|meta\n" + 
"				|escape\n" + 
"				|enter\n" + 
"				|tab\n" + 
"				|backspace\n" + 
"				|command\n" + 
"				|option\n" + 
"				|space\n" + 
"				|delete\n" + 
"				|insert\n" + 
"				|up\n" + 
"				|down\n" + 
"				|left\n" + 
"				|right\n" + 
"				|home\n" + 
"				|end\n" + 
"				|pageup\n" + 
"				|pagedown\n" + 
"				|other)		#IMPLIED\n" + 
"		otherfunction	CDATA		#IMPLIED\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of keycap.attlist-->\n" + 
"<!--end of keycap.module-->\n" + 
"\n" + 
"<!--doc:The internal, frequently numeric, identifier for a key on a keyboard.-->\n" + 
"<!ELEMENT keycode  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of keycode.element-->\n" + 
"\n" + 
"<!ATTLIST keycode\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of keycode.attlist-->\n" + 
"<!--end of keycode.module-->\n" + 
"\n" + 
"<!--doc:A combination of input actions.-->\n" + 
"<!ELEMENT keycombo  ((keycap|keycombo|keysym|mousebutton)+)>\n" + 
"<!--end of keycombo.element-->\n" + 
"\n" + 
"<!ATTLIST keycombo\n" + 
"\n" + 
"	action		(click\n" + 
"			|double-click\n" + 
"			|press\n" + 
"			|seq\n" + 
"			|simul\n" + 
"			|other)		#IMPLIED\n" + 
"	otheraction	CDATA		#IMPLIED\n" + 
"\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of keycombo.attlist-->\n" + 
"<!--end of keycombo.module-->\n" + 
"\n" + 
"<!--doc:The symbolic name of a key on a keyboard.-->\n" + 
"<!ELEMENT keysym  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of keysym.element-->\n" + 
"\n" + 
"<!ATTLIST keysym\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of keysym.attlist-->\n" + 
"<!--end of keysym.module-->\n" + 
"\n" + 
"<!--doc:A comment on a line in a verbatim listing.-->\n" + 
"<!ELEMENT lineannotation  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of lineannotation.element-->\n" + 
"\n" + 
"<!ATTLIST lineannotation\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of lineannotation.attlist-->\n" + 
"<!--end of lineannotation.module-->\n" + 
"\n" + 
"<!--doc:Inline text that is some literal value.-->\n" + 
"<!ELEMENT literal  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of literal.element-->\n" + 
"\n" + 
"<!ATTLIST literal\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of literal.attlist-->\n" + 
"<!--end of literal.module-->\n" + 
"\n" + 
"<!--doc:An inline code fragment.-->\n" + 
"<!ELEMENT code  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of code.element-->\n" + 
"\n" + 
"<!ATTLIST code\n" + 
"		language	CDATA	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of code.attlist-->\n" + 
"<!--end of code.module-->\n" + 
"\n" + 
"<!--doc:A programming or system constant.-->\n" + 
"<!ELEMENT constant  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of constant.element-->\n" + 
"\n" + 
"<!ATTLIST constant\n" + 
"		class	(limit)		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of constant.attlist-->\n" + 
"<!--end of constant.module-->\n" + 
"\n" + 
"<!--doc:The name of a variable.-->\n" + 
"<!ELEMENT varname  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of varname.element-->\n" + 
"\n" + 
"<!ATTLIST varname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of varname.attlist-->\n" + 
"<!--end of varname.module-->\n" + 
"\n" + 
"<!--doc:A string of formatting markup in text that is to be represented literally.-->\n" + 
"<!ELEMENT markup  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of markup.element-->\n" + 
"\n" + 
"<!ATTLIST markup\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of markup.attlist-->\n" + 
"<!--end of markup.module-->\n" + 
"\n" + 
"<!--doc:A name that identifies the physical medium on which some information resides.-->\n" + 
"<!ELEMENT medialabel  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of medialabel.element-->\n" + 
"\n" + 
"<!-- Class: Type of medium named by the element; no default -->\n" + 
"\n" + 
"<!ATTLIST medialabel\n" + 
"		class 		(cartridge\n" + 
"				|cdrom\n" + 
"				|disk\n" + 
"				|tape)		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of medialabel.attlist-->\n" + 
"<!--end of medialabel.module-->\n" + 
"\n" + 
"<!--doc:A selection or series of selections from a menu.-->\n" + 
"<!ELEMENT menuchoice  (shortcut?, (guibutton|guiicon|guilabel\n" + 
"		|guimenu|guimenuitem|guisubmenu|interface)+)>\n" + 
"<!--end of menuchoice.element-->\n" + 
"\n" + 
"<!ATTLIST menuchoice\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of menuchoice.attlist-->\n" + 
"<!--end of menuchoice.module-->\n" + 
"\n" + 
"<!-- See also KeyCombo -->\n" + 
"\n" + 
"<!--doc:A key combination for an action that is also accessible through a menu.-->\n" + 
"<!ELEMENT shortcut  ((keycap|keycombo|keysym|mousebutton)+)>\n" + 
"<!--end of shortcut.element-->\n" + 
"\n" + 
"<!ATTLIST shortcut\n" + 
"\n" + 
"	action		(click\n" + 
"			|double-click\n" + 
"			|press\n" + 
"			|seq\n" + 
"			|simul\n" + 
"			|other)		#IMPLIED\n" + 
"	otheraction	CDATA		#IMPLIED\n" + 
"\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of shortcut.attlist-->\n" + 
"<!--end of shortcut.module-->\n" + 
"<!--end of menuchoice.content.module-->\n" + 
"\n" + 
"<!--doc:The conventional name of a mouse button.-->\n" + 
"<!ELEMENT mousebutton  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of mousebutton.element-->\n" + 
"\n" + 
"<!ATTLIST mousebutton\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of mousebutton.attlist-->\n" + 
"<!--end of mousebutton.module-->\n" + 
"\n" + 
"<!--doc:The actual text of a message component in a message set.-->\n" + 
"<!ELEMENT msgtext  ((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of msgtext.element-->\n" + 
"\n" + 
"<!ATTLIST msgtext\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of msgtext.attlist-->\n" + 
"<!--end of msgtext.module-->\n" + 
"\n" + 
"<!--doc:An option for a software command.-->\n" + 
"<!ELEMENT option  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of option.element-->\n" + 
"\n" + 
"<!ATTLIST option\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of option.attlist-->\n" + 
"<!--end of option.module-->\n" + 
"\n" + 
"<!--doc:Optional information.-->\n" + 
"<!ELEMENT optional  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of optional.element-->\n" + 
"\n" + 
"<!ATTLIST optional\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of optional.attlist-->\n" + 
"<!--end of optional.module-->\n" + 
"\n" + 
"<!--doc:A value or a symbolic reference to a value.-->\n" + 
"<!ELEMENT parameter  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of parameter.element-->\n" + 
"\n" + 
"<!-- Class: Type of the Parameter; no default -->\n" + 
"\n" + 
"<!ATTLIST parameter\n" + 
"		class 		(command\n" + 
"				|function\n" + 
"				|option)	#IMPLIED\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of parameter.attlist-->\n" + 
"<!--end of parameter.module-->\n" + 
"\n" + 
"<!--doc:A character or string indicating the start of an input field in a  computer display.-->\n" + 
"<!ELEMENT prompt  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|co)*>\n" + 
"<!--end of prompt.element-->\n" + 
"\n" + 
"<!ATTLIST prompt\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of prompt.attlist-->\n" + 
"<!--end of prompt.module-->\n" + 
"\n" + 
"<!--doc:A unit of data associated with some part of a computer system.-->\n" + 
"<!ELEMENT property  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of property.element-->\n" + 
"\n" + 
"<!ATTLIST property\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of property.attlist-->\n" + 
"<!--end of property.module-->\n" + 
"\n" + 
"<!--doc:Content that may or must be replaced by the user.-->\n" + 
"<!ELEMENT replaceable  (#PCDATA\n" + 
"		| link|olink|ulink\n" + 
"		| optional\n" + 
"		| anchor\n" + 
"		| remark|subscript|superscript\n" + 
"		| inlinegraphic\n" + 
"                | inlinemediaobject\n" + 
"		| co)*>\n" + 
"<!--end of replaceable.element-->\n" + 
"\n" + 
"<!-- Class: Type of information the element represents; no\n" + 
"		default -->\n" + 
"\n" + 
"<!ATTLIST replaceable\n" + 
"		class		(command\n" + 
"				|function\n" + 
"				|option\n" + 
"				|parameter)	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of replaceable.attlist-->\n" + 
"<!--end of replaceable.module-->\n" + 
"\n" + 
"<!--doc:The value returned by a function.-->\n" + 
"<!ELEMENT returnvalue  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of returnvalue.element-->\n" + 
"\n" + 
"<!ATTLIST returnvalue\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of returnvalue.attlist-->\n" + 
"<!--end of returnvalue.module-->\n" + 
"\n" + 
"<!--doc:A component of SGML markup.-->\n" + 
"<!ELEMENT sgmltag  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of sgmltag.element-->\n" + 
"\n" + 
"<!-- Class: Type of SGML construct the element names; no default -->\n" + 
"\n" + 
"<!ATTLIST sgmltag\n" + 
"		class 		(attribute\n" + 
"				|attvalue\n" + 
"				|element\n" + 
"				|endtag\n" + 
"                                |emptytag\n" + 
"				|genentity\n" + 
"				|numcharref\n" + 
"				|paramentity\n" + 
"				|pi\n" + 
"                                |xmlpi\n" + 
"				|starttag\n" + 
"				|sgmlcomment\n" + 
"                                |prefix\n" + 
"                                |namespace\n" + 
"                                |localname)	#IMPLIED\n" + 
"		namespace	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sgmltag.attlist-->\n" + 
"<!--end of sgmltag.module-->\n" + 
"\n" + 
"<!--doc:A field in a structure (in the programming language sense).-->\n" + 
"<!ELEMENT structfield  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of structfield.element-->\n" + 
"\n" + 
"<!ATTLIST structfield\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of structfield.attlist-->\n" + 
"<!--end of structfield.module-->\n" + 
"\n" + 
"<!--doc:The name of a structure (in the programming language sense).-->\n" + 
"<!ELEMENT structname  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of structname.element-->\n" + 
"\n" + 
"<!ATTLIST structname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of structname.attlist-->\n" + 
"<!--end of structname.module-->\n" + 
"\n" + 
"<!--doc:A name that is replaced by a value before processing.-->\n" + 
"<!ELEMENT symbol  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of symbol.element-->\n" + 
"\n" + 
"<!-- Class: Type of symbol; no default -->\n" + 
"\n" + 
"<!ATTLIST symbol\n" + 
"		class		(limit)		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of symbol.attlist-->\n" + 
"<!--end of symbol.module-->\n" + 
"\n" + 
"<!--doc:A system-related item or term.-->\n" + 
"<!ELEMENT systemitem  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		 | acronym | co)*>\n" + 
"<!--end of systemitem.element-->\n" + 
"\n" + 
"<!-- Class: Type of system item the element names; no default -->\n" + 
"\n" + 
"<!ATTLIST systemitem\n" + 
"		class	(constant\n" + 
"                        |daemon\n" + 
"			|domainname\n" + 
"			|etheraddress\n" + 
"			|event\n" + 
"			|eventhandler\n" + 
"			|filesystem\n" + 
"			|fqdomainname\n" + 
"			|groupname\n" + 
"			|ipaddress\n" + 
"			|library\n" + 
"			|macro\n" + 
"			|netmask\n" + 
"			|newsgroup\n" + 
"			|osname\n" + 
"                        |protocol\n" + 
"			|resource\n" + 
"			|systemname\n" + 
"			|username\n" + 
"                        |process\n" + 
"                        |server\n" + 
"                        |service)	#IMPLIED\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of systemitem.attlist-->\n" + 
"<!--end of systemitem.module-->\n" + 
"\n" + 
"<!--doc:A Uniform Resource Identifier.-->\n" + 
"<!ELEMENT uri  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of uri.element-->\n" + 
"\n" + 
"<!-- Type: Type of URI; no default -->\n" + 
"\n" + 
"<!ATTLIST uri\n" + 
"		type	CDATA	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of uri.attlist-->\n" + 
"<!--end of uri.module-->\n" + 
"\n" + 
"<!--doc:A unit of information.-->\n" + 
"<!ELEMENT token  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of token.element-->\n" + 
"\n" + 
"<!ATTLIST token\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of token.attlist-->\n" + 
"<!--end of token.module-->\n" + 
"\n" + 
"<!--doc:The classification of a value.-->\n" + 
"<!ELEMENT type  (#PCDATA\n" + 
"					|replaceable\n" + 
"					|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of type.element-->\n" + 
"\n" + 
"<!ATTLIST type\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of type.attlist-->\n" + 
"<!--end of type.module-->\n" + 
"\n" + 
"<!--doc:Data entered by the user.-->\n" + 
"<!ELEMENT userinput  (#PCDATA\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		|co)*>\n" + 
"<!--end of userinput.element-->\n" + 
"\n" + 
"<!ATTLIST userinput\n" + 
"		moreinfo	(refentry|none)	'none'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of userinput.attlist-->\n" + 
"<!--end of userinput.module-->\n" + 
"\n" + 
"<!--doc:An inline definition of a term.-->\n" + 
"<!ELEMENT termdef  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of termdef.element-->\n" + 
"\n" + 
"<!ATTLIST termdef\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of termdef.attlist-->\n" + 
"<!--end of termdef.module-->\n" + 
"\n" + 
"<!-- General words and phrases ............................................ -->\n" + 
"\n" + 
"<!--doc:An abbreviation, especially one followed by a period.-->\n" + 
"<!ELEMENT abbrev  (#PCDATA\n" + 
"					|acronym|emphasis|trademark\n" + 
"		|link|olink|ulink\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of abbrev.element-->\n" + 
"\n" + 
"<!ATTLIST abbrev\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of abbrev.attlist-->\n" + 
"<!--end of abbrev.module-->\n" + 
"\n" + 
"<!--doc:An often pronounceable word made from the initial (or selected) letters of a name or phrase.-->\n" + 
"<!ELEMENT acronym  (#PCDATA\n" + 
"					|acronym|emphasis|trademark\n" + 
"		|link|olink|ulink\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*\n" + 
"		>\n" + 
"<!--end of acronym.element-->\n" + 
"\n" + 
"<!ATTLIST acronym\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of acronym.attlist-->\n" + 
"<!--end of acronym.module-->\n" + 
"\n" + 
"<!--doc:An inline bibliographic reference to another published work.-->\n" + 
"<!ELEMENT citation  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of citation.element-->\n" + 
"\n" + 
"<!ATTLIST citation\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of citation.attlist-->\n" + 
"<!--end of citation.module-->\n" + 
"\n" + 
"<!--doc:A citation to a reference page.-->\n" + 
"<!ELEMENT citerefentry  (refentrytitle, manvolnum?)>\n" + 
"<!--end of citerefentry.element-->\n" + 
"\n" + 
"<!ATTLIST citerefentry\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of citerefentry.attlist-->\n" + 
"<!--end of citerefentry.module-->\n" + 
"\n" + 
"<!--doc:The title of a reference page.-->\n" + 
"<!ELEMENT refentrytitle  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of refentrytitle.element-->\n" + 
"\n" + 
"<!ATTLIST refentrytitle\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refentrytitle.attlist-->\n" + 
"<!--end of refentrytitle.module-->\n" + 
"\n" + 
"<!--doc:A reference volume number.-->\n" + 
"<!ELEMENT manvolnum  (#PCDATA\n" + 
"					|acronym|emphasis|trademark\n" + 
"		|link|olink|ulink\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of manvolnum.element-->\n" + 
"\n" + 
"<!ATTLIST manvolnum\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of manvolnum.attlist-->\n" + 
"<!--end of manvolnum.module-->\n" + 
"\n" + 
"<!--doc:The title of a cited work.-->\n" + 
"<!ELEMENT citetitle  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of citetitle.element-->\n" + 
"\n" + 
"<!-- Pubwork: Genre of published work cited; no default -->\n" + 
"\n" + 
"<!ATTLIST citetitle\n" + 
"		pubwork		(article\n" + 
"				|book\n" + 
"				|chapter\n" + 
"				|part\n" + 
"				|refentry\n" + 
"				|section\n" + 
"				|journal\n" + 
"				|series\n" + 
"				|set\n" + 
"				|manuscript\n" + 
"				|cdrom\n" + 
"				|dvd\n" + 
"				|wiki\n" + 
"				|gopher\n" + 
"				|bbs\n" + 
"                                |emailmessage\n" + 
"                                |webpage\n" + 
"                                |newsposting)	#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of citetitle.attlist-->\n" + 
"<!--end of citetitle.module-->\n" + 
"\n" + 
"<!--doc:Emphasized text.-->\n" + 
"<!ELEMENT emphasis  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of emphasis.element-->\n" + 
"\n" + 
"<!ATTLIST emphasis\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of emphasis.attlist-->\n" + 
"<!--end of emphasis.module-->\n" + 
"\n" + 
"<!--doc:A word or phrase in a language other than the primary language of the document.-->\n" + 
"<!ELEMENT foreignphrase  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of foreignphrase.element-->\n" + 
"\n" + 
"<!ATTLIST foreignphrase\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of foreignphrase.attlist-->\n" + 
"<!--end of foreignphrase.module-->\n" + 
"\n" + 
"<!--doc:A glossary term.-->\n" + 
"<!ELEMENT glossterm  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*\n" + 
"		>\n" + 
"<!--end of glossterm.element-->\n" + 
"\n" + 
"<!-- to GlossEntry if Glossterm used in text -->\n" + 
"<!-- BaseForm: Provides the form of GlossTerm to be used\n" + 
"		for indexing -->\n" + 
"\n" + 
"<!ATTLIST glossterm\n" + 
"		baseform	CDATA		#IMPLIED\n" + 
"		linkend	IDREF		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of glossterm.attlist-->\n" + 
"<!--end of glossterm.module-->\n" + 
"\n" + 
"<!--doc:The first occurrence of a term.-->\n" + 
"<!ELEMENT firstterm  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*\n" + 
"		>\n" + 
"<!--end of firstterm.element-->\n" + 
"\n" + 
"<!-- to GlossEntry or other explanation -->\n" + 
"\n" + 
"<!ATTLIST firstterm\n" + 
"		baseform	CDATA		#IMPLIED\n" + 
"		linkend	IDREF		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of firstterm.attlist-->\n" + 
"<!--end of firstterm.module-->\n" + 
"\n" + 
"<!--doc:A span of text.-->\n" + 
"<!ELEMENT phrase  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of phrase.element-->\n" + 
"\n" + 
"<!ATTLIST phrase\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of phrase.attlist-->\n" + 
"<!--end of phrase.module-->\n" + 
"\n" + 
"<!--doc:An inline quotation.-->\n" + 
"<!ELEMENT quote  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of quote.element-->\n" + 
"\n" + 
"<!ATTLIST quote\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of quote.attlist-->\n" + 
"<!--end of quote.module-->\n" + 
"\n" + 
"<!--doc:A subscript (as in H{^2}O, the molecular formula for water).-->\n" + 
"<!ELEMENT subscript  (#PCDATA\n" + 
"		| link|olink|ulink\n" + 
"		| emphasis\n" + 
"		| replaceable\n" + 
"		| symbol\n" + 
"		| inlinegraphic\n" + 
"                | inlinemediaobject\n" + 
"		| anchor\n" + 
"		| remark|subscript|superscript )*\n" + 
"		>\n" + 
"<!--end of subscript.element-->\n" + 
"\n" + 
"<!ATTLIST subscript\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of subscript.attlist-->\n" + 
"\n" + 
"<!--doc:A superscript (as in x^2, the mathematical notation for x multiplied by itself).-->\n" + 
"<!ELEMENT superscript  (#PCDATA\n" + 
"		| link|olink|ulink\n" + 
"		| emphasis\n" + 
"		| replaceable\n" + 
"		| symbol\n" + 
"		| inlinegraphic\n" + 
"                | inlinemediaobject\n" + 
"		| anchor\n" + 
"		| remark|subscript|superscript )*\n" + 
"		>\n" + 
"<!--end of superscript.element-->\n" + 
"\n" + 
"<!ATTLIST superscript\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of superscript.attlist-->\n" + 
"<!--end of ssscript.module-->\n" + 
"\n" + 
"<!--doc:A trademark.-->\n" + 
"<!ELEMENT trademark  (#PCDATA\n" + 
"		| link|olink|ulink\n" + 
"		| action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		| anchor\n" + 
"		| remark|subscript|superscript\n" + 
"		| inlinegraphic\n" + 
"                | inlinemediaobject\n" + 
"		| emphasis)*>\n" + 
"<!--end of trademark.element-->\n" + 
"\n" + 
"<!-- Class: More precisely identifies the item the element names -->\n" + 
"\n" + 
"<!ATTLIST trademark\n" + 
"		class		(service\n" + 
"				|trade\n" + 
"				|registered\n" + 
"				|copyright)	'trade'\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of trademark.attlist-->\n" + 
"<!--end of trademark.module-->\n" + 
"\n" + 
"<!--doc:A word meant specifically as a word and not representing anything else.-->\n" + 
"<!ELEMENT wordasword  (#PCDATA\n" + 
"					|acronym|emphasis|trademark\n" + 
"		|link|olink|ulink\n" + 
"		|anchor\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of wordasword.element-->\n" + 
"\n" + 
"<!ATTLIST wordasword\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of wordasword.attlist-->\n" + 
"<!--end of wordasword.module-->\n" + 
"\n" + 
"<!-- Links and cross-references ........................................... -->\n" + 
"\n" + 
"<!--doc:A hypertext link.-->\n" + 
"<!ELEMENT link  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*\n" + 
"		>\n" + 
"<!--end of link.element-->\n" + 
"\n" + 
"<!-- Endterm: ID of element containing text that is to be\n" + 
"		fetched from elsewhere in the document to appear as\n" + 
"		the content of this element -->\n" + 
"<!-- to linked-to object -->\n" + 
"<!-- Type: Freely assignable parameter -->\n" + 
"\n" + 
"<!ATTLIST link\n" + 
"		endterm		IDREF		#IMPLIED\n" + 
"		xrefstyle	CDATA		#IMPLIED\n" + 
"		type		CDATA		#IMPLIED\n" + 
"		linkend	IDREF		#REQUIRED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of link.attlist-->\n" + 
"<!--end of link.module-->\n" + 
"\n" + 
"<!--doc:A link that addresses its target indirectly, through an entity.-->\n" + 
"<!ELEMENT olink  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*\n" + 
"		>\n" + 
"<!--end of olink.element-->\n" + 
"\n" + 
"<!-- TargetDocEnt: Name of an entity to be the target of the link -->\n" + 
"<!-- LinkMode: ID of a ModeSpec containing instructions for\n" + 
"		operating on the entity named by TargetDocEnt -->\n" + 
"<!-- LocalInfo: Information that may be passed to ModeSpec -->\n" + 
"<!-- Type: Freely assignable parameter -->\n" + 
"\n" + 
"<!ATTLIST olink\n" + 
"		targetdocent	ENTITY 		#IMPLIED\n" + 
"		linkmode	IDREF		#IMPLIED\n" + 
"		localinfo 	CDATA		#IMPLIED\n" + 
"		type		CDATA		#IMPLIED\n" + 
"		targetdoc	CDATA		#IMPLIED\n" + 
"		targetptr	CDATA		#IMPLIED\n" + 
"		xrefstyle	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of olink.attlist-->\n" + 
"<!--end of olink.module-->\n" + 
"\n" + 
"<!--doc:A link that addresses its target by means of a URL (Uniform Resource Locator).-->\n" + 
"<!ELEMENT ulink  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*\n" + 
"		>\n" + 
"<!--end of ulink.element-->\n" + 
"\n" + 
"<!-- URL: uniform resource locator; the target of the ULink -->\n" + 
"<!-- Type: Freely assignable parameter -->\n" + 
"\n" + 
"<!ATTLIST ulink\n" + 
"		url		CDATA		#REQUIRED\n" + 
"		type		CDATA		#IMPLIED\n" + 
"		xrefstyle	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of ulink.attlist-->\n" + 
"<!--end of ulink.module-->\n" + 
"\n" + 
"<!--doc:A cross reference to a footnote (a footnote mark).-->\n" + 
"<!ELEMENT footnoteref  EMPTY>\n" + 
"<!--end of footnoteref.element-->\n" + 
"\n" + 
"<!-- to footnote content supplied elsewhere -->\n" + 
"\n" + 
"<!ATTLIST footnoteref\n" + 
"		linkend	IDREF		#REQUIRED		label		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of footnoteref.attlist-->\n" + 
"<!--end of footnoteref.module-->\n" + 
"\n" + 
"<!--doc:A cross reference to another part of the document.-->\n" + 
"<!ELEMENT xref  EMPTY>\n" + 
"<!--end of xref.element-->\n" + 
"\n" + 
"<!-- Endterm: ID of element containing text that is to be\n" + 
"		fetched from elsewhere in the document to appear as\n" + 
"		the content of this element -->\n" + 
"<!-- to linked-to object -->\n" + 
"\n" + 
"<!ATTLIST xref\n" + 
"		endterm		IDREF		#IMPLIED\n" + 
"		xrefstyle	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		linkend	IDREF		#REQUIRED\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of xref.attlist-->\n" + 
"<!--end of xref.module-->\n" + 
"\n" + 
"<!--doc:A cross reference to a bibliographic entry.-->\n" + 
"<!ELEMENT biblioref  EMPTY>\n" + 
"<!--end of biblioref.element-->\n" + 
"\n" + 
"<!ATTLIST biblioref\n" + 
"		endterm		IDREF		#IMPLIED\n" + 
"		xrefstyle	CDATA		#IMPLIED\n" + 
"		units		CDATA		#IMPLIED\n" + 
"		begin		CDATA		#IMPLIED\n" + 
"		end		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		linkend	IDREF		#REQUIRED\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of biblioref.attlist-->\n" + 
"<!--end of biblioref.module-->\n" + 
"\n" + 
"<!-- Ubiquitous elements .................................................. -->\n" + 
"\n" + 
"<!--doc:A spot in the document.-->\n" + 
"<!ELEMENT anchor  EMPTY>\n" + 
"<!--end of anchor.element-->\n" + 
"\n" + 
"<!-- required -->\n" + 
"<!-- replaces Lang -->\n" + 
"\n" + 
"<!ATTLIST anchor\n" + 
"		id		ID		#REQUIRED		pagenum	CDATA		#IMPLIED		remap		CDATA		#IMPLIED\n" + 
"		xreflabel	CDATA		#IMPLIED\n" + 
"		revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"		arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of anchor.attlist-->\n" + 
"<!--end of anchor.module-->\n" + 
"\n" + 
"<!--doc:The location of a page break in a print version of the document.-->\n" + 
"<!ELEMENT beginpage  EMPTY>\n" + 
"<!--end of beginpage.element-->\n" + 
"\n" + 
"<!-- PageNum: Number of page that begins at this point -->\n" + 
"\n" + 
"<!ATTLIST beginpage\n" + 
"		pagenum	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of beginpage.attlist-->\n" + 
"<!--end of beginpage.module-->\n" + 
"\n" + 
"<!-- IndexTerms appear in the text flow for generating or linking an\n" + 
"     index. -->\n" + 
"\n" + 
"<!--doc:A wrapper for terms to be indexed.-->\n" + 
"<!ELEMENT indexterm  (primary?, ((secondary, ((tertiary, (see|seealso+)?)\n" + 
"		| see | seealso+)?) | see | seealso+)?)\n" + 
"			>\n" + 
"<!--end of indexterm.element-->\n" + 
"\n" + 
"<!-- Scope: Indicates which generated indices the IndexTerm\n" + 
"		should appear in: Global (whole document set), Local (this\n" + 
"		document only), or All (both) -->\n" + 
"<!-- Significance: Whether this IndexTerm is the most pertinent\n" + 
"		of its series (Preferred) or not (Normal, the default) -->\n" + 
"<!-- Class: Indicates type of IndexTerm; default is Singular,\n" + 
"		or EndOfRange if StartRef is supplied; StartOfRange value\n" + 
"		must be supplied explicitly on starts of ranges -->\n" + 
"<!-- StartRef: ID of the IndexTerm that starts the indexing\n" + 
"		range ended by this IndexTerm -->\n" + 
"<!-- Zone: IDs of the elements to which the IndexTerm applies,\n" + 
"		and indicates that the IndexTerm applies to those entire\n" + 
"		elements rather than the point at which the IndexTerm\n" + 
"		occurs -->\n" + 
"\n" + 
"<!ATTLIST indexterm\n" + 
"		pagenum	CDATA		#IMPLIED\n" + 
"		scope		(all\n" + 
"				|global\n" + 
"				|local)		#IMPLIED\n" + 
"		significance	(preferred\n" + 
"				|normal)	\"normal\"\n" + 
"		class		(singular\n" + 
"				|startofrange\n" + 
"				|endofrange)	#IMPLIED\n" + 
"		startref	IDREF		#IMPLIED\n" + 
"		zone		IDREFS		#IMPLIED\n" + 
"		type		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of indexterm.attlist-->\n" + 
"<!--end of indexterm.module-->\n" + 
"\n" + 
"<!--doc:The primary word or phrase under which an index term should be sorted.-->\n" + 
"<!ELEMENT primary    (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		)*>\n" + 
"<!--end of primary.element-->\n" + 
"<!-- SortAs: Alternate sort string for index sorting, e.g.,\n" + 
"		\"fourteen\" for an element containing \"14\" -->\n" + 
"\n" + 
"<!ATTLIST primary\n" + 
"		sortas		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of primary.attlist-->\n" + 
"\n" + 
"<!--doc:A secondary word or phrase in an index term.-->\n" + 
"<!ELEMENT secondary  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		)*>\n" + 
"<!--end of secondary.element-->\n" + 
"<!-- SortAs: Alternate sort string for index sorting, e.g.,\n" + 
"		\"fourteen\" for an element containing \"14\" -->\n" + 
"\n" + 
"<!ATTLIST secondary\n" + 
"		sortas		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of secondary.attlist-->\n" + 
"\n" + 
"<!--doc:A tertiary word or phrase in an index term.-->\n" + 
"<!ELEMENT tertiary   (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		)*>\n" + 
"<!--end of tertiary.element-->\n" + 
"<!-- SortAs: Alternate sort string for index sorting, e.g.,\n" + 
"		\"fourteen\" for an element containing \"14\" -->\n" + 
"\n" + 
"<!ATTLIST tertiary\n" + 
"		sortas		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of tertiary.attlist-->\n" + 
"\n" + 
"<!--end of primsecter.module-->\n" + 
"\n" + 
"<!--doc:Part of an index term directing the reader instead to another entry in the index.-->\n" + 
"<!ELEMENT see  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		)*>\n" + 
"<!--end of see.element-->\n" + 
"\n" + 
"<!ATTLIST see\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of see.attlist-->\n" + 
"\n" + 
"<!--doc:Part of an index term directing the reader also to another entry in the index.-->\n" + 
"<!ELEMENT seealso  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		)*>\n" + 
"<!--end of seealso.element-->\n" + 
"\n" + 
"<!ATTLIST seealso\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of seealso.attlist-->\n" + 
"<!--end of seeseealso.module-->\n" + 
"<!--end of indexterm.content.module-->\n" + 
"\n" + 
"<!-- End of DocBook XML information pool module V4.5 ...................... -->\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- Redeclaration placeholder ..... -->\n" + 
"\n" + 
"<!-- Document hierarchy ............ -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- DocBook document hierarchy module V4.5 ............................... -->\n" + 
"<!-- File dbhierx.mod ..................................................... -->\n" + 
"\n" + 
"<!-- Copyright 1992-2004 HaL Computer Systems, Inc.,\n" + 
"     O'Reilly & Associates, Inc., ArborText, Inc., Fujitsu Software\n" + 
"     Corporation, Norman Walsh, Sun Microsystems, Inc., and the\n" + 
"     Organization for the Advancement of Structured Information\n" + 
"     Standards (OASIS).\n" + 
"\n" + 
"     $Id: dbhierx.mod 6340 2006-10-03 13:23:24Z nwalsh $\n" + 
"\n" + 
"     Permission to use, copy, modify and distribute the DocBook DTD\n" + 
"     and its accompanying documentation for any purpose and without fee\n" + 
"     is hereby granted in perpetuity, provided that the above copyright\n" + 
"     notice and this paragraph appear in all copies.  The copyright\n" + 
"     holders make no representation about the suitability of the DTD for\n" + 
"     any purpose.  It is provided \"as is\" without expressed or implied\n" + 
"     warranty.\n" + 
"\n" + 
"     If you modify the DocBook DTD in any way, except for declaring and\n" + 
"     referencing additional sets of general entities and declaring\n" + 
"     additional notations, label your DTD as a variant of DocBook.  See\n" + 
"     the maintenance documentation for more information.\n" + 
"\n" + 
"     Please direct all questions, bug reports, or suggestions for\n" + 
"     changes to the docbook@lists.oasis-open.org mailing list. For more\n" + 
"     information, see http://www.oasis-open.org/docbook/.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- This module contains the definitions for the overall document\n" + 
"     hierarchies of DocBook documents.  It covers computer documentation\n" + 
"     manuals and manual fragments, as well as reference entries (such as\n" + 
"     man pages) and technical journals or anthologies containing\n" + 
"     articles.\n" + 
"\n" + 
"     This module depends on the DocBook information pool module.  All\n" + 
"     elements and entities referenced but not defined here are assumed\n" + 
"     to be defined in the information pool module.\n" + 
"\n" + 
"     In DTD driver files referring to this module, please use an entity\n" + 
"     declaration that uses the public identifier shown below:\n" + 
"\n" + 
"     <!ENTITY % dbhier PUBLIC\n" + 
"     \"-//OASIS//ELEMENTS DocBook Document Hierarchy V4.5//EN\"\n" + 
"     \"dbhierx.mod\">\n" + 
"     %dbhier;\n" + 
"\n" + 
"     See the documentation for detailed information on the parameter\n" + 
"     entity and module scheme used in DocBook, customizing DocBook and\n" + 
"     planning for interchange, and changes made since the last release\n" + 
"     of DocBook.\n" + 
"-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Entities for module inclusions ....................................... -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Entities for element classes ......................................... -->\n" + 
"\n" + 
"<!-- Redeclaration placeholder ............................................ -->\n" + 
"\n" + 
"<!-- For redeclaring entities that are declared after this point while\n" + 
"     retaining their references to the entities that are declared before\n" + 
"     this point -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Entities for element mixtures ........................................ -->\n" + 
"\n" + 
"<!-- Redeclaration placeholder 2 .......................................... -->\n" + 
"\n" + 
"<!-- For redeclaring entities that are declared after this point while\n" + 
"     retaining their references to the entities that are declared before\n" + 
"     this point -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Entities for content models .......................................... -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Set and SetInfo ...................................................... -->\n" + 
"\n" + 
"<!--doc:A collection of books.-->\n" + 
"<!ELEMENT set  ((title, subtitle?, titleabbrev?)?, setinfo?, toc?, (set|book )+,\n" + 
"		setindex?)\n" + 
"		>\n" + 
"<!--end of set.element-->\n" + 
"\n" + 
"<!-- FPI: SGML formal public identifier -->\n" + 
"\n" + 
"<!ATTLIST set\n" + 
"		fpi		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of set.attlist-->\n" + 
"<!--end of set.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Set.-->\n" + 
"<!ELEMENT setinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of setinfo.element-->\n" + 
"\n" + 
"<!-- Contents: IDs of the ToC, Books, and SetIndex that comprise\n" + 
"		the set, in the order of their appearance -->\n" + 
"\n" + 
"<!ATTLIST setinfo\n" + 
"		contents	IDREFS		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of setinfo.attlist-->\n" + 
"<!--end of setinfo.module-->\n" + 
"<!--end of set.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Book and BookInfo .................................................... -->\n" + 
"\n" + 
"<!--doc:A book.-->\n" + 
"<!ELEMENT book  ((title, subtitle?, titleabbrev?)?, bookinfo?,\n" + 
" 		(dedication | toc | lot\n" + 
" 		| glossary | bibliography | preface\n" + 
"		| chapter  | reference | part\n" + 
"		| article\n" + 
" 		| appendix\n" + 
"		| index|setindex\n" + 
"		| colophon)*)\n" + 
"		>\n" + 
"<!--end of book.element-->\n" + 
"\n" + 
"<!-- FPI: SGML formal public identifier -->\n" + 
"\n" + 
"<!ATTLIST book		fpi		CDATA		#IMPLIED\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of book.attlist-->\n" + 
"<!--end of book.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Book.-->\n" + 
"<!ELEMENT bookinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of bookinfo.element-->\n" + 
"\n" + 
"<!-- Contents: IDs of the ToC, LoTs, Prefaces, Parts, Chapters,\n" + 
"		Appendixes, References, GLossary, Bibliography, and indexes\n" + 
"		comprising the Book, in the order of their appearance -->\n" + 
"\n" + 
"<!ATTLIST bookinfo\n" + 
"		contents	IDREFS		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bookinfo.attlist-->\n" + 
"<!--end of bookinfo.module-->\n" + 
"<!--end of book.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Dedication, ToC, and LoT ............................................. -->\n" + 
"\n" + 
"<!--doc:A wrapper for the dedication section of a book.-->\n" + 
"<!ELEMENT dedication  ((title, subtitle?, titleabbrev?)?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot\n" + 
"		|formalpara|para|simpara 		|blockquote\n" + 
"		|indexterm         |beginpage\n" + 
"		)+)>\n" + 
"<!--end of dedication.element-->\n" + 
"\n" + 
"<!ATTLIST dedication\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of dedication.attlist-->\n" + 
"<!--end of dedication.module-->\n" + 
"\n" + 
"<!--doc:Text at the back of a book describing facts about its production.-->\n" + 
"<!ELEMENT colophon  ((title, subtitle?, titleabbrev?)?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot\n" + 
"		|formalpara|para|simpara 		|blockquote\n" + 
"		)+)>\n" + 
"<!--end of colophon.element-->\n" + 
"\n" + 
"<!ATTLIST colophon\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"		>\n" + 
"<!--end of colophon.attlist-->\n" + 
"<!--end of colophon.module-->\n" + 
"\n" + 
"<!--doc:A table of contents.-->\n" + 
"<!ELEMENT toc  (beginpage?,\n" + 
"		(title, subtitle?, titleabbrev?)?,\n" + 
"		tocfront*,\n" + 
"		(tocpart | tocchap)*, tocback*)>\n" + 
"<!--end of toc.element-->\n" + 
"\n" + 
"<!ATTLIST toc\n" + 
"		pagenum	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of toc.attlist-->\n" + 
"<!--end of toc.module-->\n" + 
"\n" + 
"<!--doc:An entry in a table of contents for a front matter component.-->\n" + 
"<!ELEMENT tocfront  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of tocfront.element-->\n" + 
"\n" + 
"<!-- to element that this entry represents -->\n" + 
"\n" + 
"<!ATTLIST tocfront\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		linkend	IDREF		#IMPLIED		pagenum	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of tocfront.attlist-->\n" + 
"<!--end of tocfront.module-->\n" + 
"\n" + 
"<!--doc:A component title in a table of contents.-->\n" + 
"<!ELEMENT tocentry  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of tocentry.element-->\n" + 
"\n" + 
"<!-- to element that this entry represents -->\n" + 
"\n" + 
"<!ATTLIST tocentry\n" + 
"		linkend	IDREF		#IMPLIED		pagenum	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of tocentry.attlist-->\n" + 
"<!--end of tocentry.module-->\n" + 
"\n" + 
"<!--doc:An entry in a table of contents for a part of a book.-->\n" + 
"<!ELEMENT tocpart  (tocentry+, tocchap*)>\n" + 
"<!--end of tocpart.element-->\n" + 
"\n" + 
"<!ATTLIST tocpart\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of tocpart.attlist-->\n" + 
"<!--end of tocpart.module-->\n" + 
"\n" + 
"<!--doc:An entry in a table of contents for a component in the body of a document.-->\n" + 
"<!ELEMENT tocchap  (tocentry+, toclevel1*)>\n" + 
"<!--end of tocchap.element-->\n" + 
"\n" + 
"<!ATTLIST tocchap\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of tocchap.attlist-->\n" + 
"<!--end of tocchap.module-->\n" + 
"\n" + 
"<!--doc:A top-level entry within a table of contents entry for a chapter-like component.-->\n" + 
"<!ELEMENT toclevel1  (tocentry+, toclevel2*)>\n" + 
"<!--end of toclevel1.element-->\n" + 
"\n" + 
"<!ATTLIST toclevel1\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of toclevel1.attlist-->\n" + 
"<!--end of toclevel1.module-->\n" + 
"\n" + 
"<!--doc:A second-level entry within a table of contents entry for a chapter-like component.-->\n" + 
"<!ELEMENT toclevel2  (tocentry+, toclevel3*)>\n" + 
"<!--end of toclevel2.element-->\n" + 
"\n" + 
"<!ATTLIST toclevel2\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of toclevel2.attlist-->\n" + 
"<!--end of toclevel2.module-->\n" + 
"\n" + 
"<!--doc:A third-level entry within a table of contents entry for a chapter-like component.-->\n" + 
"<!ELEMENT toclevel3  (tocentry+, toclevel4*)>\n" + 
"<!--end of toclevel3.element-->\n" + 
"\n" + 
"<!ATTLIST toclevel3\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of toclevel3.attlist-->\n" + 
"<!--end of toclevel3.module-->\n" + 
"\n" + 
"<!--doc:A fourth-level entry within a table of contents entry for a chapter-like component.-->\n" + 
"<!ELEMENT toclevel4  (tocentry+, toclevel5*)>\n" + 
"<!--end of toclevel4.element-->\n" + 
"\n" + 
"<!ATTLIST toclevel4\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of toclevel4.attlist-->\n" + 
"<!--end of toclevel4.module-->\n" + 
"\n" + 
"<!--doc:A fifth-level entry within a table of contents entry for a chapter-like component.-->\n" + 
"<!ELEMENT toclevel5  (tocentry+)>\n" + 
"<!--end of toclevel5.element-->\n" + 
"\n" + 
"<!ATTLIST toclevel5\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of toclevel5.attlist-->\n" + 
"<!--end of toclevel5.module-->\n" + 
"\n" + 
"<!--doc:An entry in a table of contents for a back matter component.-->\n" + 
"<!ELEMENT tocback  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of tocback.element-->\n" + 
"\n" + 
"<!-- to element that this entry represents -->\n" + 
"\n" + 
"<!ATTLIST tocback\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		linkend	IDREF		#IMPLIED		pagenum	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of tocback.attlist-->\n" + 
"<!--end of tocback.module-->\n" + 
"<!--end of toc.content.module-->\n" + 
"\n" + 
"<!--doc:A list of the titles of formal objects (as tables or figures) in a document.-->\n" + 
"<!ELEMENT lot  (beginpage?, (title, subtitle?, titleabbrev?)?, lotentry*)>\n" + 
"<!--end of lot.element-->\n" + 
"\n" + 
"<!ATTLIST lot\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of lot.attlist-->\n" + 
"<!--end of lot.module-->\n" + 
"\n" + 
"<!--doc:An entry in a list of titles.-->\n" + 
"<!ELEMENT lotentry  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject|inlineequation\n" + 
"		|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of lotentry.element-->\n" + 
"\n" + 
"<!-- SrcCredit: Information about the source of the entry,\n" + 
"		as for a list of illustrations -->\n" + 
"<!-- linkend: to element that this entry represents-->\n" + 
"\n" + 
"<!ATTLIST lotentry\n" + 
"		linkend	IDREF		#IMPLIED\n" + 
"		pagenum	CDATA		#IMPLIED\n" + 
"		srccredit	CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of lotentry.attlist-->\n" + 
"<!--end of lotentry.module-->\n" + 
"<!--end of lot.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Appendix, Chapter, Part, Preface, Reference, PartIntro ............... -->\n" + 
"\n" + 
"<!--doc:An appendix in a Book or Article.-->\n" + 
"<!ELEMENT appendix  (beginpage?,\n" + 
"                     appendixinfo?,\n" + 
"                     (title, subtitle?, titleabbrev?),\n" + 
"                     (toc|lot|index|glossary|bibliography\n" + 
"				)*,\n" + 
"                     tocchap?,\n" + 
"                     (((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+,\n" + 
"	(sect1*|(refentry )*|simplesect*|(section )*))\n" + 
"	| (sect1+|(refentry )+|simplesect+|(section )+)),\n" + 
"                     (toc|lot|index|glossary|bibliography\n" + 
"				)*)\n" + 
"		>\n" + 
"<!--end of appendix.element-->\n" + 
"\n" + 
"<!ATTLIST appendix\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of appendix.attlist-->\n" + 
"<!--end of appendix.module-->\n" + 
"\n" + 
"<!--doc:A chapter, as of a book.-->\n" + 
"<!ELEMENT chapter  (beginpage?,\n" + 
"                    chapterinfo?,\n" + 
"                    (title, subtitle?, titleabbrev?),\n" + 
"                    (toc|lot|index|glossary|bibliography\n" + 
"				)*,\n" + 
"                    tocchap?,\n" + 
"                    (((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+,\n" + 
"	(sect1*|(refentry )*|simplesect*|(section )*))\n" + 
"	| (sect1+|(refentry )+|simplesect+|(section )+)),\n" + 
"                    (toc|lot|index|glossary|bibliography\n" + 
"				)*)\n" + 
"		>\n" + 
"<!--end of chapter.element-->\n" + 
"\n" + 
"<!ATTLIST chapter\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of chapter.attlist-->\n" + 
"<!--end of chapter.module-->\n" + 
"\n" + 
"<!-- Note that Part was to have its content model reduced in V4.5.  This\n" + 
"change will not be made after all. -->\n" + 
"\n" + 
"<!--doc:A division in a book.-->\n" + 
"<!ELEMENT part  (beginpage?,\n" + 
"                partinfo?, (title, subtitle?, titleabbrev?), partintro?,\n" + 
"		(appendix |chapter |toc|lot|index|glossary|bibliography\n" + 
"				|article\n" + 
"		|preface|refentry |reference )+)\n" + 
"		>\n" + 
"<!--end of part.element-->\n" + 
"\n" + 
"<!ATTLIST part\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of part.attlist-->\n" + 
"<!--ELEMENT PartIntro (defined below)-->\n" + 
"<!--end of part.module-->\n" + 
"\n" + 
"<!--doc:Introductory matter preceding the first chapter of a book.-->\n" + 
"<!ELEMENT preface  (beginpage?,\n" + 
"                    prefaceinfo?,\n" + 
"                    (title, subtitle?, titleabbrev?),\n" + 
"                    (toc|lot|index|glossary|bibliography\n" + 
"				)*,\n" + 
"                    tocchap?,\n" + 
"                    (((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+,\n" + 
"	(sect1*|(refentry )*|simplesect*|(section )*))\n" + 
"	| (sect1+|(refentry )+|simplesect+|(section )+)),\n" + 
"                    (toc|lot|index|glossary|bibliography\n" + 
"				)*)\n" + 
"		>\n" + 
"<!--end of preface.element-->\n" + 
"\n" + 
"<!ATTLIST preface\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of preface.attlist-->\n" + 
"<!--end of preface.module-->\n" + 
"\n" + 
"<!--doc:A collection of reference entries.-->\n" + 
"<!ELEMENT reference  (beginpage?,\n" + 
"                     referenceinfo?,\n" + 
"                     (title, subtitle?, titleabbrev?), partintro?,\n" + 
"                     (refentry )+)\n" + 
"		>\n" + 
"<!--end of reference.element-->\n" + 
"\n" + 
"<!ATTLIST reference\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of reference.attlist-->\n" + 
"<!--ELEMENT PartIntro (defined below)-->\n" + 
"<!--end of reference.module-->\n" + 
"\n" + 
"<!--doc:An introduction to the contents of a part.-->\n" + 
"<!ELEMENT partintro  ((title, subtitle?, titleabbrev?)?, (((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+,\n" + 
"	(sect1*|(refentry )*|simplesect*|(section )*))\n" + 
"	| (sect1+|(refentry )+|simplesect+|(section )+)))\n" + 
"		>\n" + 
"<!--end of partintro.element-->\n" + 
"\n" + 
"<!ATTLIST partintro\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of partintro.attlist-->\n" + 
"<!--end of partintro.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Other Info elements .................................................. -->\n" + 
"\n" + 
"<!--doc:Meta-information for an Appendix.-->\n" + 
"<!ELEMENT appendixinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of appendixinfo.element-->\n" + 
"\n" + 
"<!ATTLIST appendixinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of appendixinfo.attlist-->\n" + 
"<!--end of appendixinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Bibliography.-->\n" + 
"<!ELEMENT bibliographyinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of bibliographyinfo.element-->\n" + 
"\n" + 
"<!ATTLIST bibliographyinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bibliographyinfo.attlist-->\n" + 
"<!--end of bibliographyinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Chapter.-->\n" + 
"<!ELEMENT chapterinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of chapterinfo.element-->\n" + 
"\n" + 
"<!ATTLIST chapterinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of chapterinfo.attlist-->\n" + 
"<!--end of chapterinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Glossary.-->\n" + 
"<!ELEMENT glossaryinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of glossaryinfo.element-->\n" + 
"\n" + 
"<!ATTLIST glossaryinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of glossaryinfo.attlist-->\n" + 
"<!--end of glossaryinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for an Index.-->\n" + 
"<!ELEMENT indexinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)>\n" + 
"<!--end of indexinfo.element-->\n" + 
"\n" + 
"<!ATTLIST indexinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of indexinfo.attlist-->\n" + 
"<!--end of indexinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a SetIndex.-->\n" + 
"<!ELEMENT setindexinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of setindexinfo.element-->\n" + 
"\n" + 
"<!ATTLIST setindexinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of setindexinfo.attlist-->\n" + 
"<!--end of setindexinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Part.-->\n" + 
"<!ELEMENT partinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of partinfo.element-->\n" + 
"\n" + 
"<!ATTLIST partinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of partinfo.attlist-->\n" + 
"<!--end of partinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Preface.-->\n" + 
"<!ELEMENT prefaceinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of prefaceinfo.element-->\n" + 
"\n" + 
"<!ATTLIST prefaceinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of prefaceinfo.attlist-->\n" + 
"<!--end of prefaceinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Refentry.-->\n" + 
"<!ELEMENT refentryinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of refentryinfo.element-->\n" + 
"\n" + 
"<!ATTLIST refentryinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refentryinfo.attlist-->\n" + 
"<!--end of refentryinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a refsection.-->\n" + 
"<!ELEMENT refsectioninfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of refsectioninfo.element-->\n" + 
"\n" + 
"<!ATTLIST refsectioninfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refsectioninfo.attlist-->\n" + 
"<!--end of refsectioninfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a RefSect1.-->\n" + 
"<!ELEMENT refsect1info  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of refsect1info.element-->\n" + 
"\n" + 
"<!ATTLIST refsect1info\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refsect1info.attlist-->\n" + 
"<!--end of refsect1info.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a RefSect2.-->\n" + 
"<!ELEMENT refsect2info  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of refsect2info.element-->\n" + 
"\n" + 
"<!ATTLIST refsect2info\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refsect2info.attlist-->\n" + 
"<!--end of refsect2info.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a RefSect3.-->\n" + 
"<!ELEMENT refsect3info  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of refsect3info.element-->\n" + 
"\n" + 
"<!ATTLIST refsect3info\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refsect3info.attlist-->\n" + 
"<!--end of refsect3info.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a RefSynopsisDiv.-->\n" + 
"<!ELEMENT refsynopsisdivinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of refsynopsisdivinfo.element-->\n" + 
"\n" + 
"<!ATTLIST refsynopsisdivinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refsynopsisdivinfo.attlist-->\n" + 
"<!--end of refsynopsisdivinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Reference.-->\n" + 
"<!ELEMENT referenceinfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of referenceinfo.element-->\n" + 
"\n" + 
"<!ATTLIST referenceinfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of referenceinfo.attlist-->\n" + 
"<!--end of referenceinfo.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Sect1.-->\n" + 
"<!ELEMENT sect1info  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of sect1info.element-->\n" + 
"\n" + 
"<!ATTLIST sect1info\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sect1info.attlist-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Sect2.-->\n" + 
"<!ELEMENT sect2info  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of sect2info.element-->\n" + 
"\n" + 
"<!ATTLIST sect2info\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sect2info.attlist-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Sect3.-->\n" + 
"<!ELEMENT sect3info  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of sect3info.element-->\n" + 
"\n" + 
"<!ATTLIST sect3info\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sect3info.attlist-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Sect4.-->\n" + 
"<!ELEMENT sect4info  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of sect4info.element-->\n" + 
"\n" + 
"<!ATTLIST sect4info\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sect4info.attlist-->\n" + 
"\n" + 
"<!--doc:Meta-information for a Sect5.-->\n" + 
"<!ELEMENT sect5info  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of sect5info.element-->\n" + 
"\n" + 
"<!ATTLIST sect5info\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sect5info.attlist-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Section (parallel to Sect*) ......................................... -->\n" + 
"\n" + 
"<!--doc:A recursive section.-->\n" + 
"<!ELEMENT section  (sectioninfo?,\n" + 
"			(title, subtitle?, titleabbrev?),\n" + 
"			(toc|lot|index|glossary|bibliography\n" + 
"				)*,\n" + 
"			(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+,\n" + 
" 			  ((refentry )*|(section )*|simplesect*))\n" + 
"			 | (refentry )+|(section )+|simplesect+),\n" + 
"			(toc|lot|index|glossary|bibliography\n" + 
"				)*)\n" + 
"		>\n" + 
"<!--end of section.element-->\n" + 
"\n" + 
"<!ATTLIST section\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of section.attlist-->\n" + 
"<!--end of section.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a recursive section.-->\n" + 
"<!ELEMENT sectioninfo  ((graphic | mediaobject | legalnotice | modespec\n" + 
"		 | subjectset | keywordset | itermset | abbrev|abstract|address|artpagenums|author\n" + 
"		|authorgroup|authorinitials|bibliomisc|biblioset\n" + 
"		|collab|confgroup|contractnum|contractsponsor\n" + 
"		|copyright|corpauthor|corpname|corpcredit|date|edition\n" + 
"		|editor|invpartnumber|isbn|issn|issuenum|orgname\n" + 
"		|biblioid|citebiblioid|bibliosource|bibliorelation|bibliocoverage\n" + 
"		|othercredit|pagenums|printhistory|productname\n" + 
"		|productnumber|pubdate|publisher|publishername\n" + 
"		|pubsnumber|releaseinfo|revhistory|seriesvolnums\n" + 
"		|subtitle|title|titleabbrev|volumenum|citetitle\n" + 
"		|personname|honorific|firstname|surname|lineage|othername|affiliation\n" + 
"		|authorblurb|contrib\n" + 
"		|indexterm\n" + 
"\n" + 
"                 )+)\n" + 
"		>\n" + 
"<!--end of sectioninfo.element-->\n" + 
"\n" + 
"<!ATTLIST sectioninfo\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sectioninfo.attlist-->\n" + 
"<!--end of sectioninfo.module-->\n" + 
"<!--end of section.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Sect1, Sect2, Sect3, Sect4, Sect5 .................................... -->\n" + 
"\n" + 
"<!--doc:A top-level section of document.-->\n" + 
"<!ELEMENT sect1  (sect1info?, (title, subtitle?, titleabbrev?), (toc|lot|index|glossary|bibliography\n" + 
"				)*,\n" + 
"		(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+,\n" + 
"		((refentry )* | sect2* | simplesect*))\n" + 
"		| (refentry )+ | sect2+ | simplesect+), (toc|lot|index|glossary|bibliography\n" + 
"				)*)\n" + 
"		>\n" + 
"<!--end of sect1.element-->\n" + 
"\n" + 
"<!-- Renderas: Indicates the format in which the heading should\n" + 
"		appear -->\n" + 
"\n" + 
"<!ATTLIST sect1\n" + 
"		renderas	(sect2\n" + 
"				|sect3\n" + 
"				|sect4\n" + 
"				|sect5)		#IMPLIED\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sect1.attlist-->\n" + 
"<!--end of sect1.module-->\n" + 
"\n" + 
"<!--doc:A subsection within a Sect1.-->\n" + 
"<!ELEMENT sect2  (sect2info?, (title, subtitle?, titleabbrev?), (toc|lot|index|glossary|bibliography\n" + 
"				)*,\n" + 
"		(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+,\n" + 
"		((refentry )* | sect3* | simplesect*))\n" + 
"		| (refentry )+ | sect3+ | simplesect+), (toc|lot|index|glossary|bibliography\n" + 
"				)*)>\n" + 
"<!--end of sect2.element-->\n" + 
"\n" + 
"<!-- Renderas: Indicates the format in which the heading should\n" + 
"		appear -->\n" + 
"\n" + 
"<!ATTLIST sect2\n" + 
"		renderas	(sect1\n" + 
"				|sect3\n" + 
"				|sect4\n" + 
"				|sect5)		#IMPLIED\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sect2.attlist-->\n" + 
"<!--end of sect2.module-->\n" + 
"\n" + 
"<!--doc:A subsection within a Sect2.-->\n" + 
"<!ELEMENT sect3  (sect3info?, (title, subtitle?, titleabbrev?), (toc|lot|index|glossary|bibliography\n" + 
"				)*,\n" + 
"		(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+,\n" + 
"		((refentry )* | sect4* | simplesect*))\n" + 
"		| (refentry )+ | sect4+ | simplesect+), (toc|lot|index|glossary|bibliography\n" + 
"				)*)>\n" + 
"<!--end of sect3.element-->\n" + 
"\n" + 
"<!-- Renderas: Indicates the format in which the heading should\n" + 
"		appear -->\n" + 
"\n" + 
"<!ATTLIST sect3\n" + 
"		renderas	(sect1\n" + 
"				|sect2\n" + 
"				|sect4\n" + 
"				|sect5)		#IMPLIED\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sect3.attlist-->\n" + 
"<!--end of sect3.module-->\n" + 
"\n" + 
"<!--doc:A subsection within a Sect3.-->\n" + 
"<!ELEMENT sect4  (sect4info?, (title, subtitle?, titleabbrev?), (toc|lot|index|glossary|bibliography\n" + 
"				)*,\n" + 
"		(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+,\n" + 
"		((refentry )* | sect5* | simplesect*))\n" + 
"		| (refentry )+ | sect5+ | simplesect+), (toc|lot|index|glossary|bibliography\n" + 
"				)*)>\n" + 
"<!--end of sect4.element-->\n" + 
"\n" + 
"<!-- Renderas: Indicates the format in which the heading should\n" + 
"		appear -->\n" + 
"\n" + 
"<!ATTLIST sect4\n" + 
"		renderas	(sect1\n" + 
"				|sect2\n" + 
"				|sect3\n" + 
"				|sect5)		#IMPLIED\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sect4.attlist-->\n" + 
"<!--end of sect4.module-->\n" + 
"\n" + 
"<!--doc:A subsection within a Sect4.-->\n" + 
"<!ELEMENT sect5  (sect5info?, (title, subtitle?, titleabbrev?), (toc|lot|index|glossary|bibliography\n" + 
"				)*,\n" + 
"		(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+, ((refentry )* | simplesect*))\n" + 
"		| (refentry )+ | simplesect+), (toc|lot|index|glossary|bibliography\n" + 
"				)*)>\n" + 
"<!--end of sect5.element-->\n" + 
"\n" + 
"<!-- Renderas: Indicates the format in which the heading should\n" + 
"		appear -->\n" + 
"\n" + 
"<!ATTLIST sect5\n" + 
"		renderas	(sect1\n" + 
"				|sect2\n" + 
"				|sect3\n" + 
"				|sect4)		#IMPLIED\n" + 
"		label		CDATA		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of sect5.attlist-->\n" + 
"<!--end of sect5.module-->\n" + 
"\n" + 
"<!--doc:A section of a document with no subdivisions.-->\n" + 
"<!ELEMENT simplesect  ((title, subtitle?, titleabbrev?), (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+)\n" + 
"		>\n" + 
"<!--end of simplesect.element-->\n" + 
"\n" + 
"<!ATTLIST simplesect\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of simplesect.attlist-->\n" + 
"<!--end of simplesect.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Bibliography ......................................................... -->\n" + 
"\n" + 
"<!--doc:A bibliography.-->\n" + 
"<!ELEMENT bibliography  (bibliographyinfo?,\n" + 
"                        (title, subtitle?, titleabbrev?)?,\n" + 
"                        (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*,\n" + 
"                        (bibliodiv+ | (biblioentry|bibliomixed)+))>\n" + 
"<!--end of bibliography.element-->\n" + 
"\n" + 
"<!ATTLIST bibliography\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bibliography.attlist-->\n" + 
"<!--end of bibliography.module-->\n" + 
"\n" + 
"<!--doc:A section of a Bibliography.-->\n" + 
"<!ELEMENT bibliodiv  ((title, subtitle?, titleabbrev?)?, (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*,\n" + 
"		(biblioentry|bibliomixed)+)>\n" + 
"<!--end of bibliodiv.element-->\n" + 
"\n" + 
"<!ATTLIST bibliodiv\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of bibliodiv.attlist-->\n" + 
"<!--end of bibliodiv.module-->\n" + 
"<!--end of bibliography.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Glossary ............................................................. -->\n" + 
"\n" + 
"<!--doc:A glossary.-->\n" + 
"<!ELEMENT glossary  (glossaryinfo?,\n" + 
"                    (title, subtitle?, titleabbrev?)?,\n" + 
"                    (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*,\n" + 
"                    (glossdiv+ | glossentry+), bibliography?)>\n" + 
"<!--end of glossary.element-->\n" + 
"\n" + 
"<!ATTLIST glossary\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of glossary.attlist-->\n" + 
"<!--end of glossary.module-->\n" + 
"\n" + 
"<!--doc:A division in a Glossary.-->\n" + 
"<!ELEMENT glossdiv  ((title, subtitle?, titleabbrev?), (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*,\n" + 
"		glossentry+)>\n" + 
"<!--end of glossdiv.element-->\n" + 
"\n" + 
"<!ATTLIST glossdiv\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of glossdiv.attlist-->\n" + 
"<!--end of glossdiv.module-->\n" + 
"<!--end of glossary.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Index and SetIndex ................................................... -->\n" + 
"\n" + 
"<!--doc:An index.-->\n" + 
"<!ELEMENT index  (indexinfo?,\n" + 
"                 (title, subtitle?, titleabbrev?)?,\n" + 
"                 (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*,\n" + 
"                 (indexdiv* | indexentry*))\n" + 
"		>\n" + 
"<!--end of index.element-->\n" + 
"\n" + 
"<!ATTLIST index\n" + 
"		type		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of index.attlist-->\n" + 
"\n" + 
"<!--doc:An index to a set of books.-->\n" + 
"<!ELEMENT setindex  (setindexinfo?,\n" + 
"                    (title, subtitle?, titleabbrev?)?,\n" + 
"                    (calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )*,\n" + 
"                    (indexdiv* | indexentry*))\n" + 
"		>\n" + 
"<!--end of setindex.element-->\n" + 
"\n" + 
"<!ATTLIST setindex\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of setindex.attlist-->\n" + 
"<!--end of indexes.module-->\n" + 
"\n" + 
"<!-- SegmentedList in this content is useful for marking up permuted\n" + 
"     indices. -->\n" + 
"\n" + 
"<!--doc:A division in an index.-->\n" + 
"<!ELEMENT indexdiv  ((title, subtitle?, titleabbrev?)?, ((itemizedlist|orderedlist|variablelist|simplelist\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|anchor|remark\n" + 
"		|link|olink|ulink\n" + 
" 		                        |beginpage\n" + 
"		)*,\n" + 
"		(indexentry+ | segmentedlist)))>\n" + 
"<!--end of indexdiv.element-->\n" + 
"\n" + 
"<!ATTLIST indexdiv\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of indexdiv.attlist-->\n" + 
"<!--end of indexdiv.module-->\n" + 
"\n" + 
"<!-- Index entries appear in the index, not the text. -->\n" + 
"\n" + 
"<!--doc:An entry in an index.-->\n" + 
"<!ELEMENT indexentry  (primaryie, (seeie|seealsoie)*,\n" + 
"		(secondaryie, (seeie|seealsoie|tertiaryie)*)*)>\n" + 
"<!--end of indexentry.element-->\n" + 
"\n" + 
"<!ATTLIST indexentry\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of indexentry.attlist-->\n" + 
"<!--end of indexentry.module-->\n" + 
"\n" + 
"<!--doc:A primary term in an index entry, not in the text.-->\n" + 
"<!ELEMENT primaryie  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		)*>\n" + 
"<!--end of primaryie.element-->\n" + 
"\n" + 
"<!-- to IndexTerms that these entries represent -->\n" + 
"\n" + 
"<!ATTLIST primaryie\n" + 
"		linkends	IDREFS		#IMPLIED		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of primaryie.attlist-->\n" + 
"\n" + 
"<!--doc:A secondary term in an index entry, rather than in the text.-->\n" + 
"<!ELEMENT secondaryie  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		)*>\n" + 
"<!--end of secondaryie.element-->\n" + 
"\n" + 
"<!-- to IndexTerms that these entries represent -->\n" + 
"\n" + 
"<!ATTLIST secondaryie\n" + 
"		linkends	IDREFS		#IMPLIED		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of secondaryie.attlist-->\n" + 
"\n" + 
"<!--doc:A tertiary term in an index entry, rather than in the text.-->\n" + 
"<!ELEMENT tertiaryie  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		)*>\n" + 
"<!--end of tertiaryie.element-->\n" + 
"\n" + 
"<!-- to IndexTerms that these entries represent -->\n" + 
"\n" + 
"<!ATTLIST tertiaryie\n" + 
"		linkends	IDREFS		#IMPLIED		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of tertiaryie.attlist-->\n" + 
"\n" + 
"<!--end of primsecterie.module-->\n" + 
"\n" + 
"<!--doc:A See entry in an index, rather than in the text.-->\n" + 
"<!ELEMENT seeie  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		)*>\n" + 
"<!--end of seeie.element-->\n" + 
"\n" + 
"<!-- to IndexEntry to look up -->\n" + 
"\n" + 
"<!ATTLIST seeie\n" + 
"		linkend	IDREF		#IMPLIED		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of seeie.attlist-->\n" + 
"<!--end of seeie.module-->\n" + 
"\n" + 
"<!--doc:A See also entry in an index, rather than in the text.-->\n" + 
"<!ELEMENT seealsoie  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		)*>\n" + 
"<!--end of seealsoie.element-->\n" + 
"\n" + 
"<!-- to related IndexEntries -->\n" + 
"\n" + 
"<!ATTLIST seealsoie\n" + 
"		linkends	IDREFS		#IMPLIED		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of seealsoie.attlist-->\n" + 
"<!--end of seealsoie.module-->\n" + 
"<!--end of index.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- RefEntry ............................................................. -->\n" + 
"\n" + 
"<!--doc:A reference page (originally a UNIX man-style reference page).-->\n" + 
"<!ELEMENT refentry  (beginpage?,\n" + 
"                    (indexterm )*,\n" + 
"                    refentryinfo?, refmeta?, (remark|link|olink|ulink )*,\n" + 
"                    refnamediv+, refsynopsisdiv?, (refsect1+|refsection+))\n" + 
"		>\n" + 
"<!--end of refentry.element-->\n" + 
"\n" + 
"<!ATTLIST refentry\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refentry.attlist-->\n" + 
"<!--end of refentry.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a reference entry.-->\n" + 
"<!ELEMENT refmeta  ((indexterm )*,\n" + 
"                   refentrytitle, manvolnum?, refmiscinfo*,\n" + 
"                   (indexterm )*)\n" + 
"		>\n" + 
"<!--end of refmeta.element-->\n" + 
"\n" + 
"<!ATTLIST refmeta\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refmeta.attlist-->\n" + 
"<!--end of refmeta.module-->\n" + 
"\n" + 
"<!--doc:Meta-information for a reference entry other than the title and volume number.-->\n" + 
"<!ELEMENT refmiscinfo  (#PCDATA\n" + 
"		|link|olink|ulink\n" + 
"					|emphasis|trademark\n" + 
"					|replaceable\n" + 
"		|remark|subscript|superscript 	|inlinegraphic|inlinemediaobject\n" + 
"		|indexterm\n" + 
"		)*>\n" + 
"<!--end of refmiscinfo.element-->\n" + 
"\n" + 
"<!-- Class: Freely assignable parameter; no default -->\n" + 
"\n" + 
"<!ATTLIST refmiscinfo\n" + 
"		class		CDATA		#IMPLIED\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refmiscinfo.attlist-->\n" + 
"<!--end of refmiscinfo.module-->\n" + 
"\n" + 
"<!--doc:The name, purpose, and classification of a reference page.-->\n" + 
"<!ELEMENT refnamediv  (refdescriptor?, refname+, refpurpose, refclass*,\n" + 
"		(remark|link|olink|ulink )*)>\n" + 
"<!--end of refnamediv.element-->\n" + 
"\n" + 
"<!ATTLIST refnamediv\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refnamediv.attlist-->\n" + 
"<!--end of refnamediv.module-->\n" + 
"\n" + 
"<!--doc:A description of the topic of a reference page.-->\n" + 
"<!ELEMENT refdescriptor  (#PCDATA\n" + 
"		|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of refdescriptor.element-->\n" + 
"\n" + 
"<!ATTLIST refdescriptor\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refdescriptor.attlist-->\n" + 
"<!--end of refdescriptor.module-->\n" + 
"\n" + 
"<!--doc:The name of (one of) the subject(s) of a reference page.-->\n" + 
"<!ELEMENT refname  (#PCDATA\n" + 
"		|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		)*>\n" + 
"<!--end of refname.element-->\n" + 
"\n" + 
"<!ATTLIST refname\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refname.attlist-->\n" + 
"<!--end of refname.module-->\n" + 
"\n" + 
"<!--doc:A short (one sentence) synopsis of the topic of a reference page.-->\n" + 
"<!ELEMENT refpurpose  (#PCDATA\n" + 
"		|footnoteref|xref|biblioref 	|abbrev|acronym|citation|citerefentry|citetitle|citebiblioid|emphasis\n" + 
"		|firstterm|foreignphrase|glossterm|termdef|footnote|phrase\n" + 
"		|orgname|quote|trademark|wordasword\n" + 
"		|personname\n" + 
"		|link|olink|ulink 	|action|application\n" + 
"                |classname|methodname|interfacename|exceptionname\n" + 
"                |ooclass|oointerface|ooexception\n" + 
"                |package\n" + 
"                |command|computeroutput\n" + 
"		|database|email|envar|errorcode|errorname|errortype|errortext|filename\n" + 
"		|function|guibutton|guiicon|guilabel|guimenu|guimenuitem\n" + 
"		|guisubmenu|hardware|interface|keycap\n" + 
"		|keycode|keycombo|keysym|literal|code|constant|markup|medialabel\n" + 
"		|menuchoice|mousebutton|option|optional|parameter\n" + 
"		|prompt|property|replaceable|returnvalue|sgmltag|structfield\n" + 
"		|structname|symbol|systemitem|uri|token|type|userinput|varname\n" + 
"\n" + 
"		|anchor 	|author|authorinitials|corpauthor|corpcredit|modespec|othercredit\n" + 
"		|productname|productnumber|revhistory\n" + 
"\n" + 
"		|remark|subscript|superscript\n" + 
"		|indexterm         |beginpage\n" + 
"		)*>\n" + 
"<!--end of refpurpose.element-->\n" + 
"\n" + 
"<!ATTLIST refpurpose\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refpurpose.attlist-->\n" + 
"<!--end of refpurpose.module-->\n" + 
"\n" + 
"<!--doc:The scope or other indication of applicability of a reference entry.-->\n" + 
"<!ELEMENT refclass  (#PCDATA\n" + 
"		|application\n" + 
"		)*>\n" + 
"<!--end of refclass.element-->\n" + 
"\n" + 
"<!ATTLIST refclass\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refclass.attlist-->\n" + 
"<!--end of refclass.module-->\n" + 
"\n" + 
"<!--doc:A syntactic synopsis of the subject of the reference page.-->\n" + 
"<!ELEMENT refsynopsisdiv  (refsynopsisdivinfo?, (title, subtitle?, titleabbrev?)?,\n" + 
"		(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+, refsect2*) | (refsect2+)))>\n" + 
"<!--end of refsynopsisdiv.element-->\n" + 
"\n" + 
"<!ATTLIST refsynopsisdiv\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refsynopsisdiv.attlist-->\n" + 
"<!--end of refsynopsisdiv.module-->\n" + 
"\n" + 
"<!--doc:A recursive section in a refentry.-->\n" + 
"<!ELEMENT refsection  (refsectioninfo?, (title, subtitle?, titleabbrev?),\n" + 
"		(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+, refsection*) | refsection+))>\n" + 
"<!--end of refsection.element-->\n" + 
"\n" + 
"<!ATTLIST refsection\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refsection.attlist-->\n" + 
"<!--end of refsection.module-->\n" + 
"\n" + 
"<!--doc:A major subsection of a reference entry.-->\n" + 
"<!ELEMENT refsect1  (refsect1info?, (title, subtitle?, titleabbrev?),\n" + 
"		(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+, refsect2*) | refsect2+))>\n" + 
"<!--end of refsect1.element-->\n" + 
"\n" + 
"<!ATTLIST refsect1\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refsect1.attlist-->\n" + 
"<!--end of refsect1.module-->\n" + 
"\n" + 
"<!--doc:A subsection of a RefSect1.-->\n" + 
"<!ELEMENT refsect2  (refsect2info?, (title, subtitle?, titleabbrev?),\n" + 
"	(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+, refsect3*) | refsect3+))>\n" + 
"<!--end of refsect2.element-->\n" + 
"\n" + 
"<!ATTLIST refsect2\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refsect2.attlist-->\n" + 
"<!--end of refsect2.module-->\n" + 
"\n" + 
"<!--doc:A subsection of a RefSect2.-->\n" + 
"<!ELEMENT refsect3  (refsect3info?, (title, subtitle?, titleabbrev?),\n" + 
"	(calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"                )+)>\n" + 
"<!--end of refsect3.element-->\n" + 
"\n" + 
"<!ATTLIST refsect3\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of refsect3.attlist-->\n" + 
"<!--end of refsect3.module-->\n" + 
"<!--end of refentry.content.module-->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Article .............................................................. -->\n" + 
"\n" + 
"<!-- An Article is a chapter-level, stand-alone document that is often,\n" + 
"     but need not be, collected into a Book. -->\n" + 
"\n" + 
"<!--doc:An article.-->\n" + 
"<!ELEMENT article  ((title, subtitle?, titleabbrev?)?, articleinfo?, tocchap?, lot*,\n" + 
"			(((calloutlist|glosslist|bibliolist|itemizedlist|orderedlist|segmentedlist\n" + 
"		|simplelist|variablelist 		|caution|important|note|tip|warning\n" + 
"		|literallayout|programlisting|programlistingco|screen\n" + 
"		|screenco|screenshot 	|synopsis|cmdsynopsis|funcsynopsis\n" + 
"                 |classsynopsis|fieldsynopsis\n" + 
"                 |constructorsynopsis\n" + 
"                 |destructorsynopsis\n" + 
"                 |methodsynopsis\n" + 
"		|formalpara|para|simpara 		|address|blockquote\n" + 
"                |graphic|graphicco|mediaobject|mediaobjectco\n" + 
"                |informalequation\n" + 
"		|informalexample\n" + 
"                |informalfigure\n" + 
"                |informaltable\n" + 
"		|equation|example|figure|table 		|msgset|procedure|sidebar|qandaset|task\n" + 
"\n" + 
"		|anchor|bridgehead|remark|highlights\n" + 
"				|abstract|authorblurb|epigraph\n" + 
"\n" + 
"		|indexterm         |beginpage\n" + 
"\n" + 
"		)+,\n" + 
"	(sect1*|(refentry )*|simplesect*|(section )*))\n" + 
"	| (sect1+|(refentry )+|simplesect+|(section )+)),\n" + 
"			(toc|lot|index|glossary|bibliography\n" + 
"				|appendix |colophon|ackno)*)\n" + 
"		>\n" + 
"<!--end of article.element-->\n" + 
"\n" + 
"<!-- Class: Indicates the type of a particular article;\n" + 
"		all articles have the same structure and general purpose.\n" + 
"		No default. -->\n" + 
"<!-- ParentBook: ID of the enclosing Book -->\n" + 
"\n" + 
"<!ATTLIST article\n" + 
"		class		(journalarticle\n" + 
"				|productsheet\n" + 
"				|whitepaper\n" + 
"				|techreport\n" + 
"                                |specification\n" + 
"				|faq)		#IMPLIED\n" + 
"		parentbook	IDREF		#IMPLIED\n" + 
"		status		CDATA		#IMPLIED\n" + 
"\n" + 
"		id		ID		#IMPLIED\n" + 
"	lang		CDATA		#IMPLIED\n" + 
"	remap		CDATA		#IMPLIED\n" + 
"	xreflabel	CDATA		#IMPLIED\n" + 
"	revisionflag	(changed\n" + 
"			|added\n" + 
"			|deleted\n" + 
"			|off)		#IMPLIED\n" + 
"	arch		CDATA		#IMPLIED\n" + 
"        condition	CDATA		#IMPLIED\n" + 
"	conformance	NMTOKENS	#IMPLIED\n" + 
"	os		CDATA		#IMPLIED\n" + 
"	revision	CDATA		#IMPLIED\n" + 
"        security	CDATA		#IMPLIED\n" + 
"	userlevel	CDATA		#IMPLIED\n" + 
"	vendor		CDATA		#IMPLIED\n" + 
"	wordsize	CDATA		#IMPLIED\n" + 
"\n" + 
"	dir		(ltr\n" + 
"			|rtl\n" + 
"			|lro\n" + 
"			|rlo)		#IMPLIED\n" + 
"	xml:base	CDATA		#IMPLIED\n" + 
"\n" + 
"		role		CDATA		#IMPLIED\n" + 
"\n" + 
">\n" + 
"<!--end of article.attlist-->\n" + 
"<!--end of article.module-->\n" + 
"\n" + 
"<!-- End of DocBook document hierarchy module V4.5 ........................ -->\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- Other general entities ............................................... -->\n" + 
"\n" + 
"<!-- ...................................................................... -->\n" + 
"<!-- DocBook additional general entities V4.5 ............................. -->\n" + 
"\n" + 
"<!-- Copyright 1992-2004 HaL Computer Systems, Inc.,\n" + 
"     O'Reilly & Associates, Inc., ArborText, Inc., Fujitsu Software\n" + 
"     Corporation, Norman Walsh, Sun Microsystems, Inc., and the\n" + 
"     Organization for the Advancement of Structured Information\n" + 
"     Standards (OASIS).\n" + 
"\n" + 
"     In DTD driver files referring to this module, please use an entity\n" + 
"     declaration that uses the public identifier shown below:\n" + 
"\n" + 
"     <!ENTITY % dbgenent PUBLIC\n" + 
"     \"-//OASIS//ENTITIES DocBook Additional General Entities V4.5//EN\"\n" + 
"     \"dbgenent.mod\">\n" + 
"     %dbgenent;\n" + 
"-->\n" + 
"\n" + 
"<!-- File dbgenent.mod .................................................... -->\n" + 
"\n" + 
"<!-- You can edit this file to add the following:\n" + 
"\n" + 
"     o General entity declarations of any kind.  For example:\n" + 
"\n" + 
"       <!ENTITY productname \"WinWidget\">          (small boilerplate)\n" + 
"       <!ENTITY legal-notice SYSTEM \"notice.sgm\"> (large boilerplate)\n" + 
"\n" + 
"     o Notation declarations.  For example:\n" + 
"\n" + 
"       <!NOTATION chicken-scratch SYSTEM>\n" + 
"\n" + 
"     o Declarations for and references to external parameter entities\n" + 
"       containing collections of any of the above.  For example:\n" + 
"\n" + 
"       <!ENTITY % all-titles PUBLIC \"-//DocTools//ELEMENTS Book Titles//EN\"\n" + 
"           \"booktitles.ent\">\n" + 
"       %all-titles;\n" + 
"-->\n" + 
"\n" + 
"<!-- End of DocBook additional general entities V4.5 ...................... -->\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + 
"<!-- End of DocBook XML DTD V4.5 .......................................... -->\n" + 
"<!-- ...................................................................... -->\n" + 
"\n" + ;
}
