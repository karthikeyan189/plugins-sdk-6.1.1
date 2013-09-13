<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="includeInternationalisation.jsp" %>

<div class="facilities">
	<form method="POST" action="<portlet:renderURL/>">
		<select name="producerId" value="${model.lectureseriesId}" onchange="submit()">
			<OPTION value="<c:out value="0" />"><c:out value="-- ${chooseproducer} --" /></OPTION>
			<c:forEach items="${model.producerLRList}" var="producer">
				<OPTION value="${producer.id}" <c:if test="${producer.id==model.producerId}"> <c:out value="selected" /> </c:if> > <c:out value="${producer.lastName}, ${producer.firstName} - ${producer.idNum}" /></OPTION>
			</c:forEach>
		</select>
		<input type="hidden" name="currentSeite" value="1" />
	</form>		

	&nbsp;&nbsp;

	<c:if test="${model.producerId!=0}">
		<form method="POST" action="<portlet:renderURL/>">
			<select name="lectureseriesId" 
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
			</select>
			<input type="hidden" name="producerId" value="${model.producerId}">
			<input type="hidden" name="currentSeite" value="1" />
		</form>		
	</c:if>
</div>

<c:if test="${model.videoList==null && model.producerId==0}">
	<br/><br/>
	${pleaseselectproducer}
</c:if>

<c:if test="${model.videoList==null && model.producerId!=0}">
	<br/><br/>
	${novideosforthisproducer}
</c:if>

<c:if test="${model.videoList!=null}">
	<div class="metadatacontainer">
	<c:forEach items="${model.videoList}" var="video">
		<div class="datarow">
			<div class="buttons">
				<form method="post" action='<portlet:renderURL portletMode="view"><portlet:param name="videoId" value="${video.id}" /><portlet:param name="lectureseriesId" value="${video.objectLectureseries.id}" /><portlet:param name="currentSeite" value="${model.currentSeite}" /></portlet:renderURL>'>
					<input type="image" src="/lecture2go-theme/images/l2go/edit.png" alt="${editinfo}" title="${editinfo}">
					<input type="hidden" name="currentSeite" value="${model.currentSeite}" />
					<input type="hidden" name="lectureseriesId" value="${model.lectureseriesId}">
					<input type="hidden" name="producerId" value="${video.objectProducer.id}">
				</form>
										
				<c:if test="${video.filename!=null}">
					<!-- segment button start -->
					<form method="post" action='coordinator-segment?p_p_id=coordinatorSegmentInput_WAR_lecture2gocoordinatorspringportlet&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&currentSeite=${model.currentSeite}&producerId=${video.objectProducer.id}&lectureseriesId=${model.lectureseriesId}&forward=true'>
						<c:if test="${video.segmentList==null}">
							<input type="image" width="20" src="/lecture2go-theme/images/l2go/segmentOff.png" alt="${addchapterorcomment}" title="${addchapterorcomment}">
						</c:if>
						<c:if test="${video.segmentList!=null}">
							<input type="image" width="20" src="/lecture2go-theme/images/l2go/segmentOn.png" alt="${editcommentorchapter}" title="${editcommentorchapter}">
						</c:if>
						<input type="hidden" name="currentSeite" value="${model.currentSeite}" />
						<input type="hidden" name="videoId" value="${video.id}" />
					</form>
					<!-- segment button end -->	
						
					<!-- download button begin -->
					<c:if test="${video.downloadAllawed==true}">
						<form method="post" action='<portlet:actionURL portletMode="view"><portlet:param name="videoId" value="${video.id}" /><portlet:param name="action" value="deaktivateDownload" /><portlet:param name="currentSeite" value="${model.currentSeite}" /></portlet:actionURL>'>
							<input type="image"  title="${lockdownload}"src="/lecture2go-theme/images/l2go/dl_aktiv.png" />
							<input type="hidden" name="lectureseriesId" value="${model.lectureseriesId}">
							<input type="hidden" name="producerId" value="${video.objectProducer.id}">
						</form>
					</c:if>
					<c:if test="${video.downloadAllawed==false}">
						<form method="post" action='<portlet:actionURL portletMode="view"><portlet:param name="videoId" value="${video.id}" /><portlet:param name="action" value="aktivateDownload" /><portlet:param name="currentSeite" value="${model.currentSeite}" /></portlet:actionURL>'>
							<input type="image" title="${permitdownload}"src="/lecture2go-theme/images/l2go/dl_inaktiv.png" />
							<input type="hidden" name="lectureseriesId" value="${model.lectureseriesId}">
							<input type="hidden" name="producerId" value="${video.objectProducer.id}">
						</form>
					</c:if>
					<!-- download button end -->
						
					<!-- openaccess button begin -->
					<c:if test="${video.openaccess==true}">
						<form method="post" action='<portlet:actionURL portletMode="view"><portlet:param name="videoId" value="${video.id}" /><portlet:param  name="producerId" value="${video.objectProducer.id}" /><portlet:param name="action" value="deaktivateOpenaccess" /><portlet:param name="currentSeite" value="${model.currentSeite}" /></portlet:actionURL>'>
							<input type="image" title="${lockpublicaccess}" onclick="return window.confirm('${reallypickupopenaccess}')" src="/lecture2go-theme/images/l2go/unlock_button.png" />
							<input type="hidden" name="lectureseriesId" value="${model.lectureseriesId}">
							<input type="hidden" name="producerId" value="${video.objectProducer.id}">
						</form>
					</c:if>
					<c:if test="${video.openaccess==false}">
						<form method="post" action='<portlet:actionURL portletMode="view"><portlet:param name="videoId" value="${video.id}" /><portlet:param name="action" value="aktivateOpenaccess" /><portlet:param name="currentSeite" value="${model.currentSeite}" /></portlet:actionURL>'>
							<input type="image" title="${publicityvisible}" onclick="return window.confirm('${reallyavailibletothepublic}')" src="/lecture2go-theme/images/l2go/lock_button.png" />
							<input type="hidden" name="lectureseriesId" value="${model.lectureseriesId}">
							<input type="hidden" name="producerId" value="${video.objectProducer.id}">
						</form>
					</c:if>
					<!-- openaccess button end -->
				</c:if>
			</div>	
			
			<div id="videotitle">
				<c:if test="${video.openaccess==false}">
					<a href="${video.secureUrl}" target="_blank">
						<em>${video.objectMetadata.creator}</em>: ${video.title}
					</a>
				</c:if>
				<c:if test="${video.openaccess==true}">
					<a href="${video.url}" target="_blank">
						<em>${video.objectMetadata.creator}</em>: ${video.title}
					</a>
				</c:if>
				<br />
				<span class="white">
					<em>${video.objectLectureseries.name}</em>
				</span>
				<br />
				<span style="font-size:8px;">
					<c:if test="${video.filename==null}">
						<a style="color:red;">${novideouploaded}
					</c:if>
					<c:if test="${video.mp4File!=null || video.mp3File!=null}">
						<em>${recordingon} ${video.date} ${time}</em>
						<c:if test="${video.mp4File!=null}">
							|&nbsp;${videomp4}
						</c:if>
						<c:if test="${video.mp3File!=null}">
							|&nbsp;${audiomp3}
						</c:if>
						<c:if test="${video.m4aFile!=null}">
							|&nbsp;${audiom4a}
						</c:if>
						<c:if test="${video.m4vFile!=null}">
							|&nbsp;${ipodm4v}
						</c:if>
						<c:if test="${video.pdfFile!=null}">
							|&nbsp;${textpdf}
						</c:if>
						<br/>
						<em>${uploadedon} ${video.uploadDate}</em>&nbsp;|&nbsp;<em>${video.hits} ${views}</em>
					</c:if>
				</span>
			</div>
		</div>
	</c:forEach>
</div>

<%@ include file="includeCoordinatorSiteBrowser.jsp" %>
</c:if>