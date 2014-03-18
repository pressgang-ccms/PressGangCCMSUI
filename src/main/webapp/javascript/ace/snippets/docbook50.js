define("ace/snippets/docbook50",["require","exports","module"],function(e,t,n){t.snippetText="snippet CDATA\n\
	<![CDATA[\n\
	${1:content}\n\
	]]>\n\
snippet figure\n\
	<figure>\n\
		<title>${1:title}</title>\n\
		<mediaobject>\n\
			<imageobject>\n\
				<imagedata align='center' fileref='images/${2:imageid}.png'/>\n\
			</imageobject>\n\
			<textobject>\n\
				<phrase>${3:description}</phrase>\n\
			</textobject>\n\
		</mediaobject>\n\
	</figure>\n\
snippet formalpara\n\
	<formalpara>\n\
		<title>${1:title}</title>\n\
		<para>\n\
			${2:para}\n\
		</para>\n\
	</formalpara>\n\
snippet itemizedlist\n\
	<itemizedlist> \n\
		<listitem>\n\
			<para>\n\
				${1:para}\n\
			</para>\n\
		</listitem>\n\
	</itemizedlist>\n\
snippet keycombo\n\
	<keycombo>\n\
		<keycap>${1:CTRL}</keycap>\n\
		<mousebutton>${2:Button1}</mousebutton>\n\
	</keycombo>\n\
snippet listitem\n\
	<listitem>\n\
		<para>\n\
			${1:para}\n\
		</para>\n\
	</listitem>\n\
snippet menuchoice\n\
	<menuchoice><guimenu>${1:File}</guimenu><guimenuitem>${2:Open}</guimenuitem></menuchoice>\n\
snippet note\n\
	<note>\n\
		<para>\n\
			${1:para}\n\
		</para>\n\
	</note>\n\
snippet orderedlist\n\
	<orderedlist>\n\
		<listitem>\n\
			<para>\n\
				${1:para}\n\
			</para>\n\
		</listitem>\n\
	</orderedlist>\n\
snippet procedure\n\
	<procedure>\n\
		<title>${1:title}</title>\n\
		<step>\n\
			<para>\n\
				${2:para}\n\
			</para>\n\
		</step>\n\
	</procedure>\n\
snippet programlisting\n\
	<programlisting><![CDATA[\n\
	${1:code}\n\
	]]></programlisting>\n\
snippet programlistingjava\n\
	<programlisting language='Java' role='JAVA'><![CDATA[\n\
	${1:code}\n\
	]]></programlisting>\n\
snippet programlistingxml\n\
	<programlisting language='XML' role='XML'><![CDATA[\n\
	${1:code}\n\
	]]></programlisting>\n\
snippet section\n\
	<section>\n\
		<title>${1:title}</title>\n\
		<para>\n\
			${2:para}\n\
		</para>\n\
	</section>\n\
snippet step\n\
	<step>\n\
		<para>\n\
			${1:para}\n\
		</para>\n\
	</step>\n\
snippet table\n\
	<table frame='all'>\n\
		<title>${1:title}</title>\n\
		<tgroup cols='2' align='left' colsep='1' rowsep='1'>\n\
			<colspec colname='c1'/>\n\
			<colspec colname='c2'/>\n\
			<thead>\n\
				<row>\n\
					<entry>Header Row 0 Col 0</entry>\n\
					<entry>Header Row 0 Col 1</entry>\n\
				</row>\n\
			</thead>\n\
			<tbody>\n\
				<row>\n\
					<entry>Row 1 Col 0</entry>\n\
					<entry>Row 1 Col 1</entry>\n\
				</row>\n\
				<row>\n\
					<entry>Row 2 Col 0</entry>\n\
					<entry>Row 2 Col 1</entry> \n\
				</row>\n\
			</tbody>\n\
		</tgroup>\n\
	</table>\n\
snippet table3\n\
	<table frame='all'>\n\
		<title>${1:title}</title>\n\
		<tgroup cols='3' align='left' colsep='1' rowsep='1'>\n\
			<colspec colname='c1'/>\n\
			<colspec colname='c2'/>\n\
			<colspec colname='c3'/>\n\
			<thead>\n\
				<row>\n\
					<entry>Header Row 0 Col 0</entry>\n\
					<entry>Header Row 0 Col 1</entry>\n\
					<entry>Header Row 0 Col 2</entry>\n\
				</row>\n\
			</thead>\n\
			<tbody>\n\
				<row>\n\
					<entry>Row 1 Col 0</entry>\n\
					<entry>Row 1 Col 1</entry>\n\
					<entry>Row 1 Col 2</entry>\n\
				</row>\n\
				<row>\n\
					<entry>Row 2 Col 0</entry>\n\
					<entry>Row 2 Col 1</entry> \n\
					<entry>Row 2 Col 2</entry> \n\
				</row>\n\
			</tbody>\n\
		</tgroup>\n\
	</table>\n\
snippet table4\n\
	<table frame='all'>\n\
		<title>${1:title}</title>\n\
		<tgroup cols='4' align='left' colsep='1' rowsep='1'>\n\
			<colspec colname='c1'/>\n\
			<colspec colname='c2'/>\n\
			<colspec colname='c3'/>\n\
			<colspec colname='c4'/>\n\
			<thead>\n\
				<row>\n\
					<entry>Header Row 0 Col 0</entry>\n\
					<entry>Header Row 0 Col 1</entry>\n\
					<entry>Header Row 0 Col 2</entry>\n\
					<entry>Header Row 0 Col 3</entry>\n\
				</row>\n\
			</thead>\n\
			<tbody>\n\
				<row>\n\
					<entry>Row 1 Col 0</entry>\n\
					<entry>Row 1 Col 1</entry>\n\
					<entry>Row 1 Col 2</entry>\n\
					<entry>Row 1 Col 3</entry>\n\
				</row>\n\
				<row>\n\
					<entry>Row 2 Col 0</entry>\n\
					<entry>Row 2 Col 1</entry> \n\
					<entry>Row 2 Col 2</entry> \n\
					<entry>Row 2 Col 3</entry> \n\
				</row>\n\
			</tbody>\n\
		</tgroup>\n\
	</table>\n\
snippet table5\n\
	<table frame='all'>\n\
		<title>${1:title}</title>\n\
		<tgroup cols='5' align='left' colsep='1' rowsep='1'>\n\
			<colspec colname='c1'/>\n\
			<colspec colname='c2'/>\n\
			<colspec colname='c3'/>\n\
			<colspec colname='c4'/>\n\
			<colspec colname='c5'/>\n\
			<thead>\n\
				<row>\n\
					<entry>Header Row 0 Col 0</entry>\n\
					<entry>Header Row 0 Col 1</entry>\n\
					<entry>Header Row 0 Col 2</entry>\n\
					<entry>Header Row 0 Col 3</entry>\n\
					<entry>Header Row 0 Col 4</entry>\n\
				</row>\n\
			</thead>\n\
			<tbody>\n\
				<row>\n\
					<entry>Row 1 Col 0</entry>\n\
					<entry>Row 1 Col 1</entry>\n\
					<entry>Row 1 Col 2</entry>\n\
					<entry>Row 1 Col 3</entry>\n\
					<entry>Row 1 Col 4</entry>\n\
				</row>\n\
				<row>\n\
					<entry>Row 2 Col 0</entry>\n\
					<entry>Row 2 Col 1</entry> \n\
					<entry>Row 2 Col 2</entry> \n\
					<entry>Row 2 Col 3</entry> \n\
					<entry>Row 2 Col 4</entry> \n\
				</row>\n\
			</tbody>\n\
		</tgroup>\n\
	</table>\n\
snippet link\n\
	<link xlink:href=\"${1:url}\">${2:title}</link>\n\
snippet variablelist\n\
	<variablelist>\n\
		<title>${1:title}</title>\n\
		<varlistentry>\n\
			<term>\n\
				${2:term}\n\
			</term>\n\
			<listitem>\n\
				<para>\n\
					${3:para}\n\
				</para>\n\
			</listitem>\n\
		</varlistentry>\n\
	</variablelist>\n\
snippet variablelistentry\n\
	<varlistentry>\n\
		<term>\n\
			${1:term}\n\
		</term>\n\
		<listitem>\n\
			<para>\n\
				${2:para}\n\
			</para>\n\
		</listitem>\n\
	</varlistentry>\n\
snippet xref\n\
	<xref linkend='${1:id}'/>\n\
snippet important\n\
	<important>\n\
		<para>\n\
			${1:para}\n\
		</para>\n\
	</important>\n\
snippet warning\n\
	<warning>\n\
		<para>\n\
			${1:para}\n\
		</para>\n\
	</warning>\n\
snippet footnote\n\
	<footnote>\n\
		<para>\n\
			${1:para}\n\
		</para>\n\
	</footnote>\n\
snippet indexterm1\n\
	<indexterm xml:id='${1:ID}'><primary>${2:primary}</primary></indexterm>\n\
snippet indexterm2\n\
	<indexterm xml:id='${1:ID}'><primary>${2:primary}</primary><secondary>${3:secondary}</secondary></indexterm>\n\
snippet indexterm3\n\
	<indexterm xml:id='${1:ID}'><primary>${2:primary}</primary><secondary>${3:secondary}</secondary><tertiary>${4:tertiary}</tertiary>\n\
snippet inject\n\
	<!-- Inject: ${1:TOPIC_ID} -->\n\
snippet injectlist\n\
	<!-- InjectList: ${1:TOPIC_ID_COMMA_SEPARATED} -->\n\
snippet injectalphasort\n\
	<!-- InjectListAlphaSort: ${1:TOPIC_ID_COMMA_SEPARATED} -->\n\
snippet injectlistitems\n\
	<!-- InjectListItems: ${1:TOPIC_ID_COMMA_SEPARATED} -->\n\
snippet injectsequence\n\
	<!-- InjectSequence: ${1:TOPIC_ID_COMMA_SEPARATED} -->\n\
snippet promptlinux\n\
	<prompt>[user@host ${1:path}]\$</prompt> <userinput>${2:user_input}</userinput>\n\
snippet promptwindows\n\
	<prompt>C:\${1:path}&gt;</prompt> <userinput>${2:user_input}</userinput>\n\
snippet screen\n\
	<screen><![CDATA[${1:screen_output}]]${2:>}</screen>\n\
snippet stepalternatives\n\
	<stepalternatives>\n\
		<step>\n\
			<title>${1:text}</title>\n\
			<para>\n\
				${2:text}\n\
			</para>\n\
		</step>\n\
	</stepalternatives>\n\
snippet substeps\n\
	<substeps>\n\
		<step>\n\
			<title>${1:title}</title>\n\
			<para>\n\
				${2:text}\n\
			</para>\n\
		</step>\n\
	</substeps>\n\
snippet xiinclude\n\
	<xi:include xmlns:xi='http://www.w3.org/2001/XInclude'' href='${1:path_to_file}'/>\n\
snippet /para\n\
	</para><para>\n\
snippet mediaobject\n\
	<mediaobject>\n\
		<imageobject>\n\
			<imagedata fileref='images/${1:imageid}.png'/>\n\
		</imageobject>\n\
	</mediaobject>\n\
snippet inlinemediaobject\n\
	<inlinemediaobject>\n\
		<imageobject>\n\
			<imagedata fileref='images/${1:imageid}.png'/>\n\
		</imageobject>\n\
	</inlinemediaobject>\n\
",t.scope="docbook50"})