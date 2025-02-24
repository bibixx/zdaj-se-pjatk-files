<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
			<xsl:apply-templates select="procesory/procesor"/>
		</font>
	</body>
	</html>
	
 </xsl:template>

 <xsl:template match="procesor" name="procesor">
	Procesor: 
	<br/>
	Typ: <xsl:value-of select="./typ"/> 
	<br/>
	Cena: <xsl:value-of select="./cena"/> <br/>
	<xsl:if test="link">
		Link: <xsl:value-of select="./link"/> <br/>
	</xsl:if>
 </xsl:template>

</xsl:stylesheet>
