
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="includeInternationalisation.jsp" %>

<script type="text/javascript">
	
	function show_upload_status() {
	  FileUploadProgressListener.getFileUploadStatus(${model.video.id}, handleGetData);
	}
	
	function handleGetData(data) {
		  if(data == null){
			  dwr.util.setValue("dataDivId", "100 %");
		  }else{
			  dwr.util.setValue("dataDivId", "" + data + " %");
			  show_upload_status();
		  }
	}
	
	function uploadProceed() {
		$('#up').show(100, show_upload_status);
		$('#swf').show();
	}
	
	function getExtension(filename) {
	    var parts = filename.split('.');
	    return parts[parts.length - 1];
	}
	
	function isAccepted(filename) {
	    var ext = getExtension(filename);
	    switch (ext.toLowerCase()) {
	    case 'm4v':
	    case 'm4a':
	    case 'mp3':
	    case 'mp4':
	    case 'pdf':
	        return true;
	    }
	    return false;
	}
	
	$(function() {
	    $('#formUp').submit(function() {
	        function failValidation(msg) {
	            alert(msg);
	            return false;
	        }
	
	        var file = $('#file');
	        
	        if (!isAccepted(file.val())) {
	            return failValidation('${impermissiblefilename}');
	        }else{
	            // success at this point
	        	setTimeout(uploadProceed, 500);
	        	//return true
	        }
	    });
	});

</script>

<spring:bind path="model.contactFile">
   <c:forEach items="${status.errorMessages}" var="error">
        <c:if test="${error=='1'}"> <p style="color:red; font-size:16px;"><c:out value="${pleasechoosevideofile}" /></p> </c:if>
        <c:if test="${error=='2'}"> <p style="color:red; font-size:16px;"><c:out value="${pleasenotefilesintax}" /></p> </c:if>
        <c:if test="${error=='3'}"> <p style="color:red; font-size:16px;"><c:out value="${impermissiblefilename}" /></p> </c:if>
        <c:if test="${error=='4'}"> <p style="color:red; font-size:16px;"><c:out value="${pleaseselectyourfileupload}" /></p> </c:if>
        <c:if test="${error=='5'}"> <p style="color:red; font-size:16px;"><c:out value="${pleasenotefilesintax}" /></p> </c:if>
        <c:if test="${error=='6'}"> <p style="color:red; font-size:16px;"><c:out value="${invalidvalueday}" /></p> </c:if>
        <c:if test="${error=='7'}"> <p style="color:red; font-size:16px;"><c:out value="${invalidvaluemonth}" /></p> </c:if>
        <c:if test="${error=='8'}"> <p style="color:red; font-size:16px;"><c:out value="${invalidvalueyear}" /></p> </c:if>
        <c:if test="${error=='9'}"> <p style="color:red; font-size:16px;"><c:out value="${invalidvaluehour}" /></p> </c:if>
        <c:if test="${error=='10'}"> <p style="color:red; font-size:16px;"><c:out value="${invalidvalueminute}" /></p> </c:if>        
   </c:forEach>
</spring:bind>

<c:if test="${model.videoId==-1}">
	<meta http-equiv="refresh" content="0; URL=${model.domainURL}/myvideos" />
</c:if>

<c:if test="${model.videoId!=-1}">
	<div align="right">
			<a href='myvideos?p_p_id=producerMetaData_WAR_lecture2goproducerspringportlet&p_p_lifecycle=0&currentSeite=${model.currentSeite}&lectureseriesId=${model.lectureseriesId}'>
				${gotooverview}
			</a>
	</div>

	<br />
	<br />
	
	<em class="italic">${model.video.title}</em>
	<br /><br />
	<div>	
		<!-- the upload form -->
		
		<form id="formUp" action="<portlet:actionURL><portlet:param name="action" value="upload"></portlet:param><portlet:param name="videoId" value="${model.video.id}"></portlet:param></portlet:actionURL>" enctype="multipart/form-data" method="post">
			<spring:bind path="model.contactFile">
			 <input id="file" type="file" name="contactFile" />
			</spring:bind>
		    <input id="submit" type="submit" value="${upload}">
			<c:if test="${model.video.filename!=null}">
				<input type="hidden" name="firstVideoUpload" value="false" />
			</c:if>
			<c:if test="${model.video.filename==null}">
				<input type="hidden" name="firstVideoUpload" value="true" />
			</c:if>
		    <input type="hidden" name="deleteVideoRequest" value="false" />
			<input type="hidden" name="videoSeite" value="${model.currentSeite}" />
		</form>

		<c:choose>
			<c:when test="${ model.video.filename==null && model.mp3File==null }">
				<span class="hint"><em>${pleaseuploadmp4ormp3}</em></span>
			</c:when>
			<c:otherwise>
				<span class="hint"><em>${otherallowedformats}</em></span>
			</c:otherwise>
		</c:choose>

		<br /><br />
		<div id="up" style="display:none;">
			<%-- file upload progress bar --%>
			<div>
				<span id="dataDivId"></span> 
			</div>
			
			<em>
				${uploadcantakeawhile}
			</em>
		</div>
		
		<br/>
		
	</div>
	
	<c:if test="${model.video.filename!=null}">
		<ul id="uploadbox">
			<li>
				<!-- video beginn -->
				<c:if test="${model.video.filename!=null}">
					<center>
						<%@ include file="includeStrobeMediaPlayerSmall.html" %>
						<form method="post" action='<portlet:actionURL><portlet:param name="action" value="deleteVideo"></portlet:param></portlet:actionURL>' >
							<input type="submit" name="_finish" value="${removedata}" onclick="return window.confirm('${reallydeletealldata}')" />
							<input type="hidden" name="videoId" value="${model.video.id}" />
							<input type="hidden" name="deleteVideoRequest" value="true" />
							<input type="hidden" name="videoSeite" value="${model.currentSeite}" />
						</form>
					</center>
				</c:if>				
				<!-- video end -->
			</li>
			<!-- mp3 audio beginn -->
			<c:if test="${model.video.uploadType!='audio'}">
				<li>
					<c:if test="${model.mp3File!=null}">
						<center>
							<img height="120px" src="/lecture2go-theme/images/l2go/mp3On.png" />
							<form method="post" action='<portlet:actionURL><portlet:param name="action" value="deleteMp3"></portlet:param></portlet:actionURL>' >
								<input type="submit" name="_finish" value="${removemp3}" />
								<input type="hidden" name="videoId" value="${model.video.id}" />
								<input type="hidden" name="deleteVideoRequest" value="true" />
								<input type="hidden" name="videoSeite" value="${model.currentSeite}" />
							</form>	
						</center>					
					</c:if>
					<c:if test="${model.mp3File==null}">
						<center><img height="120px" src="/lecture2go-theme/images/l2go/mp3Off.png" /></center>
					</c:if>
				</li>
			</c:if>
			<!-- mp3 audio end -->
			
			<li>
				<!-- m4a audio beginn -->
				<c:if test="${model.m4aFile!=null}">
					<center>
						<img height="120px" src="/lecture2go-theme/images/l2go/m4aOn.png" />
						<form method="post" action='<portlet:actionURL><portlet:param name="action" value="deleteM4a"></portlet:param></portlet:actionURL>' >
							<input type="submit" name="_finish" value="${removemp4}" />
							<input type="hidden" name="videoId" value="${model.video.id}" />
							<input type="hidden" name="deleteVideoRequest" value="true" />
							<input type="hidden" name="videoSeite" value="${model.currentSeite}" />
						</form>	
					</center>				
				</c:if>
				<c:if test="${model.m4aFile==null}">
					<center><img height="120px" src="/lecture2go-theme/images/l2go/m4aOff.png" /></center>
				</c:if>
				<!-- m4a audio end -->
			</li>
			<li>
				<!-- iPod beginn -->
				<c:if test="${model.m4vFile!=null}">
					<center>
						<img height="120px" src="/lecture2go-theme/images/l2go/iPodOn.png" />
						<form method="post" action='<portlet:actionURL><portlet:param name="action" value="deleteM4v"></portlet:param></portlet:actionURL>' >
							<input type="submit" name="_finish" value="${removeipod}" />
							<input type="hidden" name="videoId" value="${model.video.id}" />
							<input type="hidden" name="deleteVideoRequest" value="true" />
							<input type="hidden" name="videoSeite" value="${model.currentSeite}" />
						</form>
					</center>
				</c:if>
				<c:if test="${model.m4vFile==null}">
					<center><img height="120px" src="/lecture2go-theme/images/l2go/iPodOff.png" /></center>
				</c:if>
				<!-- iPod end -->
			</li>
			<li>
				<!-- pdf beginn -->
				<c:if test="${model.pdfFile!=null}">
					<center>
						<img height="120px" src="/lecture2go-theme/images/l2go/pdfOn.png" />
						<form method="post" action='<portlet:actionURL><portlet:param name="action" value="deletePdf"></portlet:param></portlet:actionURL>' >
							<input type="submit" name="_finish" value="${removepdf}" />
							<input type="hidden" name="videoId" value="${model.video.id}" />
							<input type="hidden" name="deleteVideoRequest" value="true" />
							<input type="hidden" name="videoSeite" value="${model.currentSeite}" />
						</form>	
					</center>				
				</c:if>
				<c:if test="${model.pdfFile==null}">
					<center><img height="120px" src="/lecture2go-theme/images/l2go/pdfOff.png" /></center>
				</c:if>
				<!-- pdf end -->
			</li>
		</ul>
	</c:if>

	<!-- embed code -->
	<br />
	<br />
	<c:if test="${model.video.filename!=null}">
		<c:if test="${model.video.openaccess==true}">
			<%@ include file="includeProducerEmbed.html" %>
		</c:if>

		<c:if test="${model.video.openaccess==false}">
			<%@ include file="includeSProducerEmbed.html" %>
		</c:if>
	</c:if>
</c:if>

