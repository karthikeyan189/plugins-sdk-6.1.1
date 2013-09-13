<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="includeInternationalisation.jsp" %>

<br/>

<c:if test="${model.videoList==null}">
${novideocommentsfound}
</c:if>

<c:if test="${model.videoList!=null}">
<div class="metadatacontainer">
		<c:forEach items="${model.videoList}" var="video">
			<div class="datarow">
				<div class="buttons">
					<!-- segment button start -->
					<form method="post" action='<portlet:renderURL portletMode="view"><portlet:param name="videoId" value="${video.id}"></portlet:param></portlet:renderURL>'>
						<c:if test="${video.segmentList!=null}">
							<input type="image" width="20" src="/lecture2go-theme/images/l2go/segmentOn.png" alt="${addchapterorcomment}" title="${addchapterorcomment}">
						</c:if>
					</form>
					<!-- segment button end -->				
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
					<br/>
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
</c:if>