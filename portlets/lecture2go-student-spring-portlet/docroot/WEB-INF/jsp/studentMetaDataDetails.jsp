<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="includeInternationalisation.jsp" %>

<div align="right">
	<c:if test="${model.video.openaccess}">
		<a href='${model.video.url}'>
			${gobacktovideo}
		</a>	
	</c:if>
	<c:if test="${!model.video.openaccess}">
		<a href='${model.video.secureUrl}'>
			${gobacktovideo}
		</a>	
	</c:if>
	&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href='mycomments'>
		${gobacktomycomments}
	</a>
</div>

<br/>
<em class="title"><b>${model.video.title}</b></em>
<br/>

<div class="videodetailbody">
	<div class="player">
		<%@ include file="includeStrobeMediaPlayer.html" %>
		<%@ include file="includeInputMetadataForSegments.html" %>
	</div>
	
	<div class="segments">
		<c:if test="${model.segmentList!=null}">
			<%@ include file="includeChapter.html" %><br/><br/>
		</c:if>
	</div>
</div>