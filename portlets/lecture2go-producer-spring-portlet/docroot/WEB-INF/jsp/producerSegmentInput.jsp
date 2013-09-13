<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
		<a href='myvideos?p_p_id=producerMetaData_WAR_lecture2goproducerspringportlet&p_p_lifecycle=0&currentSeite=${model.currentSeite}&lectureseriesId=${model.lectureseriesId}'>
			${gotooverview}
		</a>
</div>

<br/>
<em class="title"><b>${model.video.title}</b></em>
<br/>
<div class="videodetailbody">

	<form method="POST" action="<portlet:actionURL/>">
		${allowsegmentation} 
		<spring:bind path="model.permittedToSegment">
			<input type="checkbox" name="permittedToSegment" onclick="submit();" value="1" <c:if test='${model.video.permittedToSegment}'> checked="checked"</c:if> />
		</spring:bind>
		<spring:bind path="model.action">
			<input type="hidden" name="<c:out value="${status.expression}" />" value="permittToSegment"/>
		</spring:bind>
		<input type="hidden" name="videoId" value="${model.video.id}">
		<input type="hidden" name="currentSeite" value="${model.currentSeite}">
		<input type="hidden" name="forward" value="false">
	</form>

	<br/>

	<div class="player">						
		<%@ include file="includeStrobeMediaPlayer.html"%>
		<%@ include file="includeInputMetadataForSegments.html" %>
	</div>

	<div class="segments">
		<c:if test="${model.segmentList!=null}">
			<%@ include file="includeChapter.html" %><br/><br/>
		</c:if>
	</div>
		
</div>

<script>
	$("#hidr").click(function () {
      $("p#iav").hide();
    });
    $("#showr").click(function () {
      $("p#iav").show();
    });
</script>