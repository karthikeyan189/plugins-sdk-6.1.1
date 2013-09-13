<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form style="display:inline" method="POST" action='<portlet:renderURL/>'>
	<spring:bind path="model.lectureseriesId">
		<select
			name="lectureseriesId"
			value="${model.lectureseriesId}" 
			onchange="submit()"
			style="width:250px" 
			width="250px" 
			size="1"
		>
			<OPTION value="<c:out value="0" />"><c:out value="-- ${selectlectureseries} --" /></OPTION>
				<c:forEach items="${model.lectureseriesList}" var="lectureseries">
						<OPTION value="${lectureseries.id}" <c:if test="${lectureseries.id==model.lectureseriesId}"> <c:out value="selected" /> </c:if> > <c:out value="${lectureseries.number} ${lectureseries.name} - ${lectureseries.semesterName}" /></OPTION>
				</c:forEach>
		</select>&nbsp;<em style="color:#F17B0D;">*</em>
		<font color="red">
			<c:out value="${status.errorMessage}" />
		</font>
	</spring:bind>
	<input type="hidden" name="autocompare" value="1">
</form>