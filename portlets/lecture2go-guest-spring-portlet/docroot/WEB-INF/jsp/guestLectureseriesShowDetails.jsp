<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="includeInternationalisation.jsp"%>

<script type="text/javascript">
	$(function() {
		// Tabs
		$('#tabs').tabs();
	});
	$(function() {
		 $( "#dialog" ).dialog({
			 autoOpen: false
		 });
		 
		 $("#contlink").click(function() {
		    $("#dialog").dialog("open");
		    $("#dialog").dialog('option', 'draggable', false);
		    $("#dialog").dialog('option', 'resizable', false);
		    $("#dialog").dialog('option', 'hight', 217);
		    $("#dialog").dialog('option', 'width', 300);
		    $("#dialog").dialog('option', 'position','center');
		 });
	 });

	function contact(){
    	var sub=$("#subject").val().trim();
    	var txt=$("#text").val().trim();
    	if(sub=="" || txt==""){
    		alert ("${pleasefilloutfields}");
    		return false;
    	}else {
    		sub="${feedbackforvideo} "+sub+" ${model.URL}";
    		EmailManager.sendEmail('${model.liferayUser.emailAddress}','${model.emailTo}',sub,txt,handleGetData);
    		return true;
    	}		
	}
	
	function handleGetData(data) {
		if(data)alert("${thankyouforthemessage}");
    	else alert("${messagenotdelivered}");
		$("#subject").val("");
		$("#text").val("");
    	$("#dialog").dialog("close");
	}
</script>

<c:if test="${model.videoId==-1}">
	<meta http-equiv="refresh" content="0; URL=<portlet:renderURL portletMode='view'/>">
</c:if>

<c:if test="${model.videoId!=-1}">
	<c:if test="${model.citation2goView==1 || (model.citation2goView==0 && model.clipStartTime==null && model.clipEndTime==null)}">
		<%@ include file="includeFacilityDropdownFilter.jsp"%>
		<br>
		<div class="videodetailbody">
			<div class="lectureseriestitle">
				<c:choose>
					<c:when test="${model.clipStartTime!=null || model.clipEndTime!=null}">
						<em class="italic">${citation2go}</em>
						<span class="entire-video">
							<a href="${model.URL}">${gotofullvideo}</a>
						</span>
					</c:when>
					<c:otherwise>
						<em class="italic"> ${model.lectureseries.name} </em>
					</c:otherwise>
				</c:choose>
			</div>

			<c:if test="${model.userIsStudent && model.video.permittedToSegment}">
				<div class="comment">
					<a href="${model.domainURL}/web/vod/mycomments?p_p_id=studentMetaData_WAR_lecture2gostudentspringportlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_studentMetaData_WAR_lecture2gostudentspringportlet_videoId=${model.video.id}">${commentvideo}</a>
				</div>
			</c:if> 
			
			<div class="player">						
				<%@ include file="includeStrobeMediaPlayer.html"%>
			</div>
			
			<div class="segments">
				<c:choose>
				    <c:when test="${model.video.hasChapters && model.video.hasComments}">
				        <em class="italic">${chaptersandcomments}</em>
				    </c:when>
				    <c:otherwise>
						<c:if test="${model.video.hasChapters}"><em class="italic">${chapters} </em></c:if>
						<c:if test="${model.video.hasComments}"><em class="italic">${comments} </em></c:if>
				    </c:otherwise>
				</c:choose>
				
				<c:if test="${model.segmentList==null}">
					<em class="italic">${lectureseries}</em>&nbsp;&nbsp;&nbsp;
				</c:if>
			
				<c:if test="${model.segmentList!=null && model.clipStartTime==null && model.clipEndTime==null}">
					<%@ include file="includeChapter.html"%><br />
					<em class="italic">${lectureseries}</em>
				</c:if> 
				<%@ include file="includeRelatedVideos.jsp"%>
			</div>

			<%@ include file="includeTabs.jsp"%>
		</div>
	</c:if>
	
	<!-- citation2go -->
	<c:if test="${model.citation2goView==0 && (model.clipStartTime!=null || model.clipEndTime!=null)}">
		<br/><br/><br/>
		<b>${citation2gonotavailable}</b>
	</c:if>
</c:if>