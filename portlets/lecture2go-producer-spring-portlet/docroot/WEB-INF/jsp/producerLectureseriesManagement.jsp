<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="path" scope="request" value="test"/> 
	
<%@ include file="includeInternationalisation.jsp" %>

<c:if test="${not empty msg}">
	<div id="errorDivId" style="width:100%;font-weight:bold;color:green;">
		${msg}
	</div>
</c:if>

${pleaceapplyforanewlectureseries}
<br/><br/>
		
<a href="<portlet:renderURL portletMode="view"><portlet:param name="action" value="edit"></portlet:param></portlet:renderURL>" >
	${newlectureserierequest}
</a>
