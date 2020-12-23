<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0"
    xmlns:xalan="http://xml.apache.org/xslt">
    
<xsl:template match="/">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<meta name="GENERATOR" content="IBM WebSphere Studio" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<link href="theme/Master.css" rel="stylesheet" type="text/css" />
	<title>Your Shopcart</title>
	</head>
	<body>

	<xsl:apply-templates />

	</body>
	</html>
</xsl:template>
	   
	   
<xsl:template match="shopcart">
	<h1>your shopcart contains</h1>
	<table name="shopcart" border="1">
		<thead>
			<tr>
				<th>Name</th>
				<th>Quantity</th>
				<th>Unit Price</th>
				<th>Total Price</th>
			</tr>
		</thead>
		<tbody>

	<xsl:apply-templates />

			<tr>
				<td colspan="3">Subtotal</td>
				<td class="subtotal" id="subtotal"><xsl:value-of select="subtotal" /></td>
			</tr>
		</tbody>
	</table>
	
	<form action="coffee" method="POST"><input type="submit"
		name="browseCatalog" value="Buy More Coffee!" /></form>
</xsl:template>	   

<xsl:template match="item">
			<tr class="shopcartItem" id="{@id}">
				<td><xsl:value-of select="name" /></td>
				<td><xsl:value-of select="quantity" /></td>
				<td><xsl:value-of select="unit-price" /></td>
				<td><xsl:value-of select="total-price" /></td>
			</tr>
</xsl:template>

</xsl:stylesheet>
