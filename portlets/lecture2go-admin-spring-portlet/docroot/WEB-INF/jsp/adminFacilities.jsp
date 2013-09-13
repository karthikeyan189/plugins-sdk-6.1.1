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
	
	<form method="post" action='<portlet:actionURL/>'>
		<div class="box">
			${newinstitution}
			<form:input path="model.name" />
			<div style="color:#ff0000; display:inline;"><form:errors path="model.name"/></div>
		</div>
		<div class="box">
			<select name="hostId" width="150px" style="width:150px">
				<OPTION value="<c:out value="" />"><c:out value="-- ${choosestreamer} --" /></OPTION>
				<c:forEach items="${model.hostList}" var="host">
						<OPTION value="${host.id}" > <c:out value="${host.streamer}: ${rootdirectory} ${host.serverRoot}" /> </OPTION>
				</c:forEach>
			</select>
			<div style="color:#ff0000; display:inline;"><form:errors path="model.hostId"/></div>
		</div>
		<div class="box">
			<input type="submit" name="speichern" value="${save}" alt="${save}"/>
			<input type="hidden" name="id" value="${model.facility.id}">
		</div>
		<input type="hidden" name="action" value="add"/>
	</form>

	<div class="row">
		<em class="title">${model.facility.name}</em>
	</div>
		

	<c:forEach items="${model.facilitiesList}" var="facility">
		<div class="datarow">
			<div class="cfl">
				<form method="post" action="<portlet:actionURL><portlet:param name="action" value="rename"/></portlet:actionURL>">
					<input type="text" name="name" value="${facility.name}" size="50"/>
					<input type="submit" name="rename" value="${edit}" alt="${edit}"/>
					<input type="hidden" name="id" value="${facility.id}"/>
				</form>
				<br/>
				<c:if test="${facility.level==1}">
					<span style="font-size:9px;">
						<em>${httpstreamer} <font color="orange">${facility.host.streamer}</font></em>
					</span>
				</c:if>
			</div>

			<div class="box">
				<input type="text" name="sort" value="${facility.sort}" maxlength="2" size="1" readonly>	
			</div>
			
			<div class="buttons">
				<c:if test="${facility.sort>1}">
					<div class="aeb">
						<form method="post" action='<portlet:actionURL><portlet:param name="action" value="hoch"/></portlet:actionURL>'>
							<input type="submit" name="hoch" value="${up}"/>
							<input type="hidden" name="id" value="${facility.id}"/>
						</form>
					</div>
				</c:if>
	
				<c:if test="${facility.sort<model.listFacilitiesSize}">
					<div class="aeb">
						<form method="post" action='<portlet:actionURL><portlet:param name="action" value="runter"/></portlet:actionURL>'>
							<input type="submit" name="runter" value="${down}"/>
							<input type="hidden" name="id" value="${facility.id}"/>
						</form>
					</div>
				</c:if>
	
				<c:if test="${facility.deletable==true}">
					<div class="aeb">
						<form method="post" action='<portlet:actionURL><portlet:param name="action" value="delete" /></portlet:actionURL>'>
							<input type="image" onclick="return window.confirm('${reallydeleteinstitution}')" src="/lecture2go-theme/images/l2go/delete.gif" alt="${deleteinstitution}" title="${deleteinstitution}">
							<input type="hidden" name="id" value="${facility.id}">
						</form>
					</div>	
				</c:if>
			</div>
		</div>
	</c:forEach>
</div>