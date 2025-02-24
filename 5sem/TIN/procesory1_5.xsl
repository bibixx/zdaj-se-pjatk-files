
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">
 <xsl:decimal-format grouping-separator=" " name="polskie"/>
 <xsl:template match="/">
	<html>
	<head>
		<title>Procesory!!!</title>
	</head>
	<body bgcolor="white" vlink="blue" alinku="blue" link="blue">
		<font color="black">
			<h1> <center>Cennik procesorow z dnia:
				<xsl:value-of select="procesory/@data"/>
			</center>
			</h1>
			<xsl:apply-templates select="procesory/procesor">
				<xsl:sort select="typ"/>
			</xsl:apply-templates>
Suma: <xsl:value-of select="format-number(sum(procesory/procesor/cena),'### ###.00','polskie')"/>
		</font>
	</body>
	</html>
	
 </xsl:template>

 <xsl:template match="procesor" name="procesor">
	<xsl:number value="position()" format="1. "/>
	Procesor: <xsl:value-of select="./typ"/> 
	<br/>
	<xsl:for-each select="./cena">
		<xsl:sort select="@typ"/>
		Cena (<xsl:value-of select="@typ"/>): 
	<xsl:value-of select="format-number (current(),'### ### ###.00','polskie')"/> <br/>
	</xsl:for-each>
	<xsl:choose>
		<xsl:when test="link">
			Link: <xsl:value-of select="./link"/> <br/>
		</xsl:when>
		<xsl:otherwise>
			Brak odnosnika od strony <br/>
		</xsl:otherwise>
	</xsl:choose>
 </xsl:template>

</xsl:stylesheet>
