<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>

<%@ include file="includeInternationalisation.jsp" %>

<script type="text/javascript">
	$(function(){
		// Tabs
		$('#tabs').tabs();
		// Accordion
		$("#accordion").accordion({ header: "h3" });		
	});
</script>


<c:if test="${model.videoId==-1}">
<meta http-equiv="refresh" content="0; URL=<portlet:renderURL portletMode='view'/>">
</c:if>

<c:if test="${model.lectureseries.password!=null && model.videoId!=-1 && !model.studentAuthenticated}">
	<center>
		<br/>
		<br/>
		<br/>
		<em>${enterpassword}</em>
		<br/>
		<br/>
		<br/>
		<form method="POST" action="<portlet:actionURL></portlet:actionURL>">
			<input type="password" name="vlpwd"/>
			<input type="hidden" name="videoId" value="${model.video.secureFilename}"/>
		</form>
	</center>
</c:if>

<c:if test="${(model.videoId!=-1 && model.lectureseries.password==null) || (model.videoId!=-1 && model.studentAuthenticated)}">
	<div class="videodetailbody">
		<div class="lectureseriestitle">
			<em class="italic">${model.lectureseries.name}</em>
		</div>
		
		<c:if test="${model.userIsStudent && model.video.permittedToSegment}">
			<div class="comment">				
				<a href="${model.domainURL}/web/vod/mycomments?p_p_id=studentMetaData_WAR_lecture2gostudentspringportlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_studentMetaData_WAR_lecture2gostudentspringportlet_videoId=${model.video.id}">${commentvideo}</a>
			</div>
		</c:if>
	
		<div class="player">
			<%@ include file="includeStrobeMediaPlayer.html" %>
		</div>
		
		<div class="segments">
			<c:if test="${model.segmentList!=null}">
				<em class="italic">${chapters} </em>
			</c:if> 
				
			<c:if test="${model.segmentList==null}">
				<em class="italic">${lectureseries}</em>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${model.segmentList!=null}">
				<%@ include file="includeChapter.html"%><br />
				<em class="italic">${lectureseries}</em>
			</c:if> 
				
			<%@ include file="includeSRelatedVideos.jsp"%>
		</div>
		
		<%@ include file="includeTabs.jsp" %>
	</div>
</c:if>

<c:if test="${model.videoId!=-1 && model.lectureseries.password!=null && !model.studentAuthenticated && model.pwdCheck}">
	<br/>
	<center>
		${wrongpassword}
	</center>
</c:if>