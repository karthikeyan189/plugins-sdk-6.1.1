<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="includeInternationalisation.jsp" %>

<c:if test="${!model.videoListEmpty}">
	<c:forEach items="${model.lectureseriesList}" var="lectureseries">
		<form method="POST" action="<portlet:actionURL></portlet:actionURL>">
			<div class="datarow">		
				<div id="lectureseries">
					<em class="white"> ${lectureseries.name} - ${lectureseries.semesterName}</em>

					<div>
						<input type="text" maxlength="20" size="20" name="password" value="${lectureseries.password}" />
						<input type="submit" onclick="return window.confirm('${reallychangepassword}')" name="speichern" value="${setpassword}" alt="${setpassword}"/>
						<input type="hidden" name="lectureseriesId" value="${lectureseries.id}">
					</div>
					
					<br/>
					<span>
						<a style="color:red;">${lectureseries.numberOfVideos}</a> ${videodatasetinfo} <b>${lectureseries.id}</b> 
					</span>
				</div>
			</div>	
		</form>
	</c:forEach>
</c:if>
