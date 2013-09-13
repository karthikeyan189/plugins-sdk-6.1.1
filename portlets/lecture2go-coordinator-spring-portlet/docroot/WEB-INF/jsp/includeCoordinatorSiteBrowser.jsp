<div align="center">
	<c:if test="${model.videoSeiten!=0}">
		${page}:&nbsp;
		<c:forEach items="${model.numberOfPages}" var="videoSeite">
			<a
				<c:if test="${model.currentSeite==videoSeite}">
					style="color: #ffffff;"
				</c:if>
				title="${gotonextpage}" href="<portlet:renderURL portletMode="view"><portlet:param name="lectureseriesId" value="${model.lectureseriesId}"></portlet:param><portlet:param name="currentSeite" value="${videoSeite}"></portlet:param><portlet:param name="producerId" value="${model.producerId}"></portlet:param><portlet:param name="browse" value="1"></portlet:param></portlet:renderURL>"> ${videoSeite}
			</a>
		</c:forEach>
	</c:if>
</div>