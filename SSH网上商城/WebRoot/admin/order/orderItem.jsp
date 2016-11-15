<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table border="0"  width="100%">
	<s:iterator value="list"  var="orderItem">
		<tr>
				<td><img width="40" height="45"  src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>"/></td>
				<td><s:property value="#orderItem.count"/></td>
				<td><s:property value="#orderItem.subtotal"/></td>
		</tr>
	</s:iterator>
</table>

