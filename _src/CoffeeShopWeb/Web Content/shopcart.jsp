<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<%@page import="java.util.*" %>
<%@page import="junit.cookbook.coffee.display.*" %>

<jsp:useBean id="shopcartDisplay"
	class="junit.cookbook.coffee.display.ShopcartBean" scope="request"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM WebSphere Studio" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="theme/Master.css" rel="stylesheet" type="text/css" />
<title>shopcart.jsp</title>
</head>
<body>
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
<%
Iterator i = shopcartDisplay.shopcartItems.iterator();
while (i.hasNext()) {
	ShopcartItemBean item = (ShopcartItemBean) i.next();
%>
			<tr>
				<td><%= item.coffeeName %></td>
				<td id="product-<%= item.productId %>"><%= item.quantityInKilograms %>
				kg</td>
				<td><%= item.unitPrice %></td>
				<td><%= item.getTotalPrice() %></td>
			</tr>
<%
}
%>
		<tr>
			<td colspan="3"><b>Subtotal</b></td>
			<td><b><jsp:getProperty name="shopcartDisplay" property="subtotal" /></b></td>
		</tr>
	</tbody>
</table>

<form action="coffee" method="POST"><input type="submit"
	name="browseCatalog" value="Buy More Coffee!" /></form>
</body>
</html>
