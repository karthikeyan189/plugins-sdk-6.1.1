<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="includeInternationalisation.jsp" %>

<div class="metadatacontainer">
	
	<spring:hasBindErrors name="model">
		<font color="red">
			<p style="color:red; font-size:16px;">${pleasecorrectinput}</p>
		</font>
	</spring:hasBindErrors>
	
	<div class="row">
		${addnewhttpstreamer} 
		<form method="post" action='<portlet:actionURL><portlet:param name="action" value="add" /></portlet:actionURL>'>
			<form:input path="model.streamer" size="75" />
			<div style="color:#ff0000; display:inline;"><form:errors path="model.streamer"/></div>
			<input type="submit" name="speichern" value="${save}" alt="${save}"/>
			<br/>${zb} <em><font color="orange">www.mystreamingserver.com</font></em> ${orip} <em><font color="orange">134.35.13.253</font></em>
		</form>
	</div>

	<div class="row">
		<em class="title">${httpstreamerlist}</em>
	</div>	
		
	<c:forEach items="${model.hostList}" var="host">
		<div class="datarow">
			<div class="asl">
				<form method="post" action='<portlet:actionURL><portlet:param name="action" value="edit" /></portlet:actionURL>'>
					<spring:bind path="model.streamer">
						<input type="text" maxlength="300" size="75" name="<c:out value="${status.expression}" />" value="<c:out value="${host.streamer}" />" />
					</spring:bind>

					<input type="submit" value="${edit}">
					<spring:bind path="model.hostId"><input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${host.id}" />" /></spring:bind>
				</form>	

				<c:if test="${host.deletable==true}">
					<form method="post" action='<portlet:actionURL><portlet:param name="action" value="delete" /></portlet:actionURL>'>
						<input type="image" onclick="return window.confirm('${reallydeletestreamer}')" src="/lecture2go-theme/images/l2go/delete.gif" alt="${deletestreamer}" title="${deletestreamer}">
						<spring:bind path="model.hostId"><input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${host.id}" />" /></spring:bind>
					</form>
				</c:if>
			</div>

			<div class="aseb">
				<c:forEach items="${host.facilitiesList}" var="facility">
					<span style="font-size:9px;"><em><font color="orange">${facility.name}</font> &nbsp;&nbsp;&nbsp; ${rootdirectory} /${host.serverRoot}</em><br/></span>	
				</c:forEach>	
			</div>
		</div>		
	</c:forEach>
</div>