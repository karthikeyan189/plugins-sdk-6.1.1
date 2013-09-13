<div class="iframe">
	<c:forEach items="${model.relatedVideosList}" var="video">
		<c:choose>
		   <c:when test="${model.video.id==video.id}"><div class="chaptertile selected" ></c:when>
		   <c:otherwise><div class="chaptertile" ></c:otherwise>
		</c:choose>	
			<a href="<portlet:renderURL><portlet:param name="videoId" value="${video.id}"/><portlet:param name="facultyId" value="${model.facultyId}"/><portlet:param name="subFacility1Id" value="${model.subFacility1Id}"/><portlet:param name="subFacility2Id" value="${model.subFacility2Id}"/><portlet:param name="lectureseriesId" value="${model.lectureseriesId}"/><portlet:param name="method" value="get"/></portlet:renderURL>">
				<div class="img">
					<c:choose>
						<c:when test="${model.video.id==video.id}"><img class="imgsmallhit" src="${video.image}"/></c:when>
						<c:otherwise><img class="imgsmall" src="${video.image}"/></c:otherwise>
					</c:choose>
				</div>					
			</a>
			
			<a href="<portlet:renderURL><portlet:param name="videoId" value="${video.id}"/><portlet:param name="facultyId" value="${model.facultyId}"/><portlet:param name="subFacility1Id" value="${model.subFacility1Id}"/><portlet:param name="subFacility2Id" value="${model.subFacility2Id}"/><portlet:param name="lectureseriesId" value="${model.lectureseriesId}"/><portlet:param name="method" value="get"/></portlet:renderURL>">
				<div>
					<em class="gray">${video.shortName}:</em>
					<em class="white"> ${video.title}</em> <br />
					<em class="gray"><span style="font-size: 9px;">[${video.date}<c:if test="${time}">${time}</c:if>]</span></em>
				</div>
			</a>
		</div>	 
	</c:forEach>							  	
</div>