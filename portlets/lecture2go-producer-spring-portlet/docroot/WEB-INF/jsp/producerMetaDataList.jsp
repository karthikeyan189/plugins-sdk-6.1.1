<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="includeInternationalisation.jsp" %>

<c:if test="${model.producer.hasLectureseries}">
	<div class="dropdownselect">
		<form method="POST" action="<portlet:renderURL portletMode="view"><portlet:param name="select" value="true" /><portlet:param name="action" value="lectureseriesSelect" /></portlet:renderURL>">
			<select name="lectureseriesId" value="${model.lectureseriesId}" onchange="submit()" style="width:250px" size="1">
				<OPTION value="<c:out value="null" />"><c:out value="-- ${selectlectureseries} --" /></OPTION>
				<c:forEach items="${model.lectureseriesList}" var="lectureseries">
					<OPTION value="${lectureseries.id}" <c:if test="${lectureseries.id==model.lectureseriesId}"> <c:out value="selected" /> </c:if> > <c:out value="${lectureseries.number} ${lectureseries.name} - ${lectureseries.semesterName}" /></OPTION>
				</c:forEach>
			</select>
		</form>	

		<form method="POST" action="/web/vod/input?p_p_id=producerMetaDataInput_WAR_lecture2goproducerspringportlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1">
			<input type="submit" value="${addnewvideodataset}"/>
			<input type="hidden" name="lectureseriesId" value="${model.lectureseriesId}"/>
			<input type="hidden" name="autocompare" value="1">
		</form>
	</div>
</c:if>

<c:if test="${!model.producer.hasLectureseries}">
	${pleaceapplyforanewlectureseries}
	<br/><br/>
	<a href="/web/vod/my-lectures?p_p_id=producerLectureseriesManagement_WAR_lecture2goproducerspringportlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=2&_producerLectureseriesManagement_WAR_lecture2goproducerspringportlet_action=edit">
		${newlectureserierequest}
	</a>
</c:if>

<c:if test="${model.videoList!=null}">
<div class="metadatacontainer">
	<c:forEach items="${model.videoList}" var="video">
		<div class="datarow">
			<div class="buttons">
					<form method="post" action='<portlet:actionURL><portlet:param name="action" value="loeschen" /></portlet:actionURL>'>
						<input type="image" onclick="return window.confirm('${reallydeletealldata}')" src="/lecture2go-theme/images/l2go/delete.gif" alt="${deletevideo}" title="${deletevideo}">
						<input type="hidden" name="metadataId" value="${video.metadataId}" />
						<input type="hidden" name="videoId" value="${video.id}" />
						<input type="hidden" name="currentSeite" value="${model.currentSeite}" />
						<input type="hidden" name="lectureseriesId" value="${model.lectureseriesId}" />
					</form>
					
					<form method="post" action='<portlet:renderURL portletMode="view"><portlet:param name="videoId" value="${video.id}" /><portlet:param name="action" value="editMetadata" /><portlet:param name="lectureseriesId" value="${model.lectureseriesId}" /><portlet:param name="currentSeite" value="${model.currentSeite}"/></portlet:renderURL>'>
						<input type="image" src="/lecture2go-theme/images/l2go/edit.png" alt="${editinfo}" title="${editinfo}">
					</form>
										
					<c:if test="${video.filename!=null}">
							<!-- segment button start -->
							<form method="post" action='producer-segment?p_p_id=producerSegmentInput_WAR_lecture2goproducerspringportlet&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&currentSeite=${model.currentSeite}&lectureseriesId=${model.lectureseriesId}&videoId=${video.id}'>
								<c:if test="${video.segmentList==null}">
									<input type="image" src="/lecture2go-theme/images/l2go/segmentOff.png" alt="${addchapterorcomment}" title="${addchapterorcomment}">
								</c:if>
								<c:if test="${video.segmentList!=null}">
									<input type="image" src="/lecture2go-theme/images/l2go/segmentOn.png" alt="${editcommentorchapter}" title="${editcommentorchapter}">
								</c:if>
							</form>
							<!-- segment button end -->
							<!-- download button begin -->
							<c:if test="${video.downloadAllawed==true}">
									<form method="post" action='<portlet:actionURL portletMode="view"><portlet:param name="videoId" value="${video.id}"/><portlet:param name="action" value="deaktivateDownload"/><portlet:param name="currentSeite" value="${model.currentSeite}"/><portlet:param name="lectureseriesId" value="${model.lectureseriesId}"/></portlet:actionURL>'>
										<input type="image"  title="${lockdownload}"src="/lecture2go-theme/images/l2go/dl_aktiv.png" />
									</form>
							</c:if>
							<c:if test="${video.downloadAllawed==false}">
									<form method="post" action='<portlet:actionURL portletMode="view"><portlet:param name="videoId" value="${video.id}" /><portlet:param name="action" value="aktivateDownload" /><portlet:param name="currentSeite" value="${model.currentSeite}"/><portlet:param name="lectureseriesId" value="${model.lectureseriesId}" /></portlet:actionURL>'>
										<input type="image" title="${permitdownload}"src="/lecture2go-theme/images/l2go/dl_inaktiv.png" />
									</form>
							</c:if>
							<!-- download button end -->
							<!-- openaccess button begin -->
							<c:if test="${video.openaccess==true}">
									<form method="post" action='<portlet:actionURL portletMode="view"><portlet:param name="videoId" value="${video.id}" /><portlet:param name="action" value="deaktivateOpenaccess" /><portlet:param name="currentSeite" value="${model.currentSeite}" /><portlet:param name="lectureseriesId" value="${model.lectureseriesId}" /></portlet:actionURL>'>
										<input type="image" title="${lockpublicaccess}" onclick="return window.confirm('${reallypickupopenaccess}')" src="/lecture2go-theme/images/l2go/unlock_button.png" />
									</form>
							</c:if>
							<c:if test="${video.openaccess==false}">
									<form method="post" action='<portlet:actionURL portletMode="view"><portlet:param name="videoId" value="${video.id}" /><portlet:param name="action" value="aktivateOpenaccess" /><portlet:param name="currentSeite" value="${model.currentSeite}" /><portlet:param name="lectureseriesId" value="${model.lectureseriesId}" /></portlet:actionURL>'>
										<input type="image" title="${publicityvisible}" onclick="return window.confirm('${reallyavailibletothepublic}')" src="/lecture2go-theme/images/l2go/lock_button.png" />
									</form>
							</c:if>
							<!-- openaccess button end -->
					</c:if>
	
					<form method="post" action='upload?p_p_id=producerVideoDataInput_WAR_lecture2goproducerspringportlet&p_p_lifecycle=0&currentSeite=${model.currentSeite}&videoId=${video.id}'>
						<input type="image" src="/lecture2go-theme/images/l2go/upload.png" title="${uploadandmanagefiles}" alt="${uploadandmanagefiles}">
					</form>
			</div>		
			
			<div>
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
								<a style="color:red;">${novideouploaded}</a>
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

<%@ include file="includeProducerMetadataListPaging.jsp" %>
</c:if>