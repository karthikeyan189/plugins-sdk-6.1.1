<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="includeInternationalisation.jsp" %>

<%@ include file="includeFacilityDropdownFilter.jsp" %>	

<br/>


<div id="gvlconainer">
	<c:forEach items="${model.videoList}" var="video">
		<%@ include file="includeKachelBig.html" %>
	</c:forEach>
</div>

<c:if test="${empty model.videoList}">
	${noopenaccessvideosyet}
</c:if>

<br/>
<%@ include file="includeGuestLectureseriesPaging.jsp" %>
