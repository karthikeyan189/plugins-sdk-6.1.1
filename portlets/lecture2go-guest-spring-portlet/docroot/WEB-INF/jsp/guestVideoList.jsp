<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="includeInternationalisation.jsp" %>

<span class="category-heading fat">${neww}</span><br>
<c:if test="${model.videoList.isEmpty()}">
	${noopenaccessvideosyet}
</c:if>
<c:if test="${!model.videoList.isEmpty()}">
	<c:set var="counter" value="0"/>
	<c:forEach items="${model.videoList}" var="video">
		<c:set var="counter" value="${counter+1}"/>
		<%@ include file="includeKachel.html" %>
	</c:forEach>
</c:if>

<div class="tileseparator"></div>

<span class="category-heading fat">${popular}</span><br>
<c:if test="${model.videoHitList.isEmpty()}">
	${videolistevaluationnotpossible}
</c:if>
<c:if test="${!model.videoHitList.isEmpty()}">
	<c:set var="counter" value="0"/>
	<c:forEach items="${model.videoHitList}" var="video">
		<c:set var="counter" value="${counter+1}"/>
		<%@ include file="includeKachel.html" %>
	</c:forEach>
</c:if>

<div class="tileseparator"></div>

<span class="category-heading fat">${random}</span><br>
<c:if test="${model.videoFavList.isEmpty()}">
	${randomlistnotavailibleyet}
</c:if>
<c:if test="${!model.videoFavList.isEmpty()}">
	<c:set var="counter" value="0"/>
	<c:forEach items="${model.videoFavList}" var="video">
		<c:set var="counter" value="${counter+1}"/>
		<%@ include file="includeKachel.html" %>
	</c:forEach>
</c:if>
