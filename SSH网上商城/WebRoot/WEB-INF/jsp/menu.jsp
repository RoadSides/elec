<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
	
			<s:if test="#session.userexit==null">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="<c:url value='user_loginPage.action'></c:url>">登录</a>|
				</li > 
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="<c:url value='/user_registPage.action'></c:url>">注册</a>|
				</li>
			</s:if>
			<s:else>
				<li id="headerLogin" class="headerLogin" style="display: list-item;"><s:property value="#session.userexit.name"/></li>
				<li id="headerLogin" class="headerLogin" style="display: list-item;"><a href="${pageContext.request.contextPath}/order_findByUid.action?page=1">[我的订单]</a></li>
				<li id="headerLogout" class="headerLogout" style="display: list-item;">
					<a href="<c:url value='user_quit.action'></c:url>">[退出]</a>|
				</li>
			</s:else>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${pageContext.request.contextPath}/cart_myCart.action">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	<div>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="<c:url value='index'/>">首页</a>
						|
					</li>
					<s:iterator var="c" value="#session.cList">
							<li><a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value='#c.cid'/>&page=1"><s:property value="#c.cname"/></a>&nbsp&nbsp&nbsp|</li>
					</s:iterator>
					
		</ul>
			
	</div>
</div>	

