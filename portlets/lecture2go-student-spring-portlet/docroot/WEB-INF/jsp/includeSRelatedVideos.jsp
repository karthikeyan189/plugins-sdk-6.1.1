<div class="iframe">
	<c:forEach items="${model.relatedVideosList}" var="video">
		<c:choose>
		   <c:when test="${model.video.id==video.id}"><div class="chaptertile selected" ></c:when>
		   <c:otherwise><div class="chaptertile" ></c:otherwise>
		</c:choose>		
		<a href="<portlet:renderURL><portlet:param name="videoId" value="${video.secureFilename}"></portlet:param><portlet:param name="facultyId" value="${model.facultyId}"></portlet:param><portlet:param name="subFacility1Id" value="${model.subFacility1Id}"></portlet:param><portlet:param name="subFacility2Id" value="${model.subFacility2Id}"></portlet:param><portlet:param name="lectureseriesId" value="${model.lectureseriesId}"></portlet:param><portlet:param name="method" value="get"></portlet:param></portlet:renderURL>">
		   	<c:if test="${model.videoId==video.id}">
		   		<img class="imgsmallhit" src="${video.imageSmall}"/>
		   	</c:if>
		   	<c:if test="${model.videoId!=video.id}">
		   		<img class="imgsmall" src="${video.imageSmall}"/>
		   	</c:if>				        	
		</a>
		<a href="<portlet:renderURL><portlet:param name="videoId" value="${video.secureFilename}"></portlet:param><portlet:param name="facultyId" value="${model.facultyId}"></portlet:param><portlet:param name="subFacility1Id" value="${model.subFacility1Id}"></portlet:param><portlet:param name="subFacility2Id" value="${model.subFacility2Id}"></portlet:param><portlet:param name="lectureseriesId" value="${model.lectureseriesId}"></portlet:param><portlet:param name="method" value="get"></portlet:param></portlet:renderURL>">
			<em class="gray">${video.shortName}:</em>  <em class="white">${video.title}</em>
			<br/>
			<em class="gray"><span style="font-size: 9px;">[${video.date}<c:if test="${time}">${time}</c:if>]</span></em>
		</a>
		</div>	 
	</c:forEach>							  	
</div>