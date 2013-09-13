<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ include file="includeInternationalisation.jsp" %>

<div align="right">
	<a href='<portlet:renderURL portletMode="view"></portlet:renderURL>'>
		${gotooverview}
	</a>
</div>

<br><br>

<em class="italic">${model.lectureseriesNumber} : ${model.lectureseriesName}</em>

<div class="datarow">	
	<div class="formtitle">
		${lecturetype}:
	</div>
	${model.eventType}
</div>

<div class="datarow">
	<div class="formtitle">
		${facilities}:
	</div>
	<c:forEach items="${model.faculties}" var="faculty" varStatus="rowCounter" >
		${faculty.value};&nbsp;
	</c:forEach>
</div>

<div class="datarow">
	<div class="formtitle">
		${shortdescription}:
	</div>
	${model.shortDescription}
</div>

<div class="datarow">
	<div class="formtitle">
		${semester}:
	</div>
	${model.semesterName}
</div>

<div class="datarow">
	<div class="formtitle">
		${languages}:
	</div>
	<c:forEach items="${model.languages}" var="language" varStatus="rowCounter" >
		${language.value};&nbsp;
	</c:forEach>
</div>

<div class="datarow">
	<div class="formtitle">
		${lecturers}:
	</div>
	${model.instructorNames}
</div>

<div class="datarow">
	<div class="formtitle">
		${password}:
	</div>
	${model.password}
</div>

<br/><br/>
<div id="butlink">
	<a href="<portlet:renderURL portletMode="view"><portlet:param name="action" value="edit"></portlet:param><portlet:param name="lectureseriesId" value="${model.id}"></portlet:param><portlet:param name="pagesize" value="${model.pageSize}"></portlet:param><portlet:param name="pagenumber" value="${model.currentPageNumber}"></portlet:param></portlet:renderURL>" >
		<input type="button" name="${editthisevent}" value="${editthisevent}"/>
	</a>
</div>