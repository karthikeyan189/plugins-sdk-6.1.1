<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="includeInternationalisation.jsp" %>

<c:if test="${model.videoList!=null}">
		
	<p class="searchresults">	
		${yoursearchreturned} <em><b> ${model.numberOfSearchResults} </b></em> ${results}!
	</p>
		
	<c:forEach items="${model.videoList}" var="video">
		<div class="videotile wide">
			<a class="whitetop" title="${gotolectureseries}" href='${video.url}'>
				<img class="imgmedium" src="${video.image}">
			</a>

			<div class="searchdescription">${video.lecturerName}, <span class="descriptiondate">${video.simpleDate}</span>
				<a title="${gotolectureseries}" href='${video.url}'>
					<div class="white">${video.title}</div><br/>
					[&nbsp;${video.lectureserieName}&nbsp;]
				</a>
			</div>
		</div>
	</c:forEach>
	<div class="tileseparator"></div>
</c:if>

<c:if test="${model.moreVideoSearchResults!=null}">		
	<c:if test="${model.numberOfSearchResults2<=model.searchResultsLimit}">
		<p class="searchresults">	
			${eswurden} <em><b> ${model.numberOfSearchResults2} </b></em> ${otherresults}!
		</p>
	</c:if>
	<c:if test="${model.numberOfSearchResults2>model.searchResultsLimit}">
		<p class="searchresults">	
			${morethan} <em><b> ${model.searchResultsLimit} </b></em> ${resultsfound}!
		</p>
	</c:if>

	<c:forEach items="${model.moreVideoSearchResults}" var="video">
		<div class="videotile wide black">
			<a class="whitetop" title="${gotolectureseries}" href='${video.url}'>
				<img class="imgmedium" src="${video.image}">
			</a>

			<div class="searchdescription">${video.lecturerName}, <span class="descriptiondate">${video.simpleDate}</span>
				<a title="${gotolectureseries}" href='${video.url}'>
					<div class="white">${video.title}</div><br/>
					[&nbsp;${video.lectureserieName}&nbsp;]
				</a>
			</div>
		</div>
	</c:forEach>
	<div class="tileseparator"></div>
</c:if>	