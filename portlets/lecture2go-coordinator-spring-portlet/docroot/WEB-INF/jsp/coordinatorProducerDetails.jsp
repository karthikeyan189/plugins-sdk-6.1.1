<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ include file="includeInternationalisation.jsp" %>

<div align="right">
	<a href='<portlet:renderURL portletMode="view"><portlet:param name="pagesize" value="${model.pageSize}"/><portlet:param name="pagenumber" value="${model.currentPageNumber}"/><portlet:param name="roleId" value="${model.roleId}"/></portlet:renderURL>'>
		${gotooverview}
	</a>
</div>

<br/><br/>

<spring:hasBindErrors name="model">
	<font color="red">
		<p style="color:red; font-size:16px;">${pleasecorrectinput}</p>
		<c:forEach items="${status.errors}" var="error">
    		<c:out value="${error}"/>
  		</c:forEach>
	</font>
</spring:hasBindErrors>

<form method="POST" action='<portlet:actionURL><portlet:param name="pagesize" value="${model.pageSize}"/><portlet:param name="pagenumber" value="${model.currentPageNumber}"/></portlet:actionURL>'>
	<div class="row">
		<em class="italic">${model.user.firstName} ${model.user.lastName}</em>
	</div>
	<div class="datarow">
		<div class="box">
			${facility}:*
			<spring:bind path="model.facilityId">
				<select name="facilityId" value="<c:out value="${model.user.objectProducer.facilityId}" />" width="290px" style="width:290px">
					<OPTION value="<c:out value="0"/>"><c:out value="-- ${facility} --" /></OPTION>
					<c:forEach items="${model.facilitiesList}" var="facility">
						<OPTION value="${facility.id}" <c:if test="${facility.id==model.facilityId}"> <c:out value="selected" /> </c:if> > <c:out value="${facility.name}" /></OPTION>
					</c:forEach>
				</select>
				<font color="red"><c:out value="${status.errorMessage}" /></font>
			</spring:bind>
		</div>
	</div>
	<div class="row">
		<input type="submit" value="${save}"/>
	    <input type="hidden" name="userId" value="${model.user.id}">
	    <input type="hidden" name="roleId" value="${model.roleId}">
	    <input type="hidden" name="action" value="approvalproducer">
 	</div>	        
</form>