<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.dspace.org/dspace-tags.tld" prefix="dspace"%>
<%@page import="javax.servlet.jsp.jstl.fmt.LocaleSupport"%>

<c:set var="dspace.layout.head" scope="request">
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jdyna/jdyna.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/utils.js"></script>
	<link href="<%= request.getContextPath() %>/css/researcher.css" type="text/css" rel="stylesheet" />
</c:set>
<dspace:layout locbar="link" navbar="admin"
	titlekey="jsp.dspace-admin.hku.jdyna-configuration.listedittabs">

	<table width="95%">
		<tr>
			<td align="left">
			<h1><fmt:message key="jsp.dspace-admin.hku.jdyna-configuration.listedittabs" /></h1>
			</td>
			<td align="right" class="standard"><a target="_blank"
				href='<%=request.getContextPath()%><%=LocaleSupport.getLocalizedMessage(pageContext,
                                "help.site-admin.rp")%>'><fmt:message
				key="jsp.help" /></a></td>
		</tr>
	</table>
	
	<c:if test="${not empty messages}">
		<div class="message" id="successMessages"><c:forEach var="msg"
			items="${messages}">
			<div id="authority-message">${msg}</div>
		</c:forEach></div>
		<c:remove var="messages" scope="session" />
	</c:if>
	
	<fieldset>
	<ul>
		<c:forEach items="${listTab}" var="tab">
			<li>
			<div style="padding: 0; margin: 0 10px;">${tab.title} <c:if
				test="${tab.mandatory eq 'false'}">
				<a class="jdynaremovebutton"
					title="<fmt:message
				key="jsp.dspace-admin.hku.jdyna-configuration.deletedynamicfield" />"
					href="<%=request.getContextPath()%>/${specificPartPath}/administrator/deleteEditTabs.htm?id=${tab.id}">
				<img
					src="<%=request.getContextPath()%>/image/authority/jdynadeletebutton.jpg"
					border="0"
					alt="<fmt:message
					key="jsp.dspace-admin.hku.jdyna-configuration.deletedynamicfield" />"
					title="<fmt:message
					key="jsp.dspace-admin.hku.jdyna-configuration.deletedynamicfield" />"
					name="remove" id="remove_${tab.id}" /> </a>
			</c:if> <a class="jdynaeditbutton"
				title="<fmt:message
				key="jsp.dspace-admin.hku.jdyna-configuration.editdynamicfield" />"
				href="<%=request.getContextPath()%>/${specificPartPath}/administrator/editEditTabs.htm?id=${tab.id}">
			<img
				src="<%=request.getContextPath()%>/image/authority/jdynaeditbutton.jpg"
				border="0"
				alt="<fmt:message
					key="jsp.dspace-admin.hku.jdyna-configuration.editdynamicfield" />"
				title="<fmt:message
					key="jsp.dspace-admin.hku.jdyna-configuration.editdynamicfield" />"
				name="edit" id="edit_${tab.id}" /> </a></div>

			</li>
			<div>&nbsp;</div>

		</c:forEach>
	</ul>
	</fieldset>

	<div style="padding: 0; margin: 0 10px;"><a
		href="<%=request.getContextPath()%>/${specificPartPath}/administrator/createEditTabs.htm">
	<fmt:message key="jsp.dspace-admin.hku.jdyna-configuration.newtab" />
	</a></div>
</dspace:layout>