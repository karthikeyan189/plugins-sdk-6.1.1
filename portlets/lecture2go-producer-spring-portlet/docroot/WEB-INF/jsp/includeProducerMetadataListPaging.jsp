<div class="paging" align="center">
	<c:if test="${model.numberResultPages!=0}">
			<c:if test="${model.hasPrev}">
				<a title="${gotofirstpage}" href="<portlet:renderURL portletMode="view">
						<portlet:param name="action" value="browseMetadata"/>
						<portlet:param name="pagesize" value="${model.pageSize}"/>
						<portlet:param name="pagenumber" value="1"/>
						<portlet:param name="currentSeite" value="1"/>
						<portlet:param name="lectureseriesId" value="${model.lectureseriesId}"/>
	    				</portlet:renderURL>">&#124;&lt;</a>
			    &nbsp;
			    <a title="${gotopage} ${model.currentPageNumber - 1}" href="<portlet:renderURL portletMode="view">
						<portlet:param name="action" value="browseMetadata"/>
						<portlet:param name="pagesize" value="${model.pageSize}"/>
						<portlet:param name="pagenumber" value="${model.currentPageNumber - 1}"/>
						<portlet:param name="currentSeite" value="${model.currentPageNumber - 1}"/>
						<portlet:param name="lectureseriesId" value="${model.lectureseriesId}"/>
					    </portlet:renderURL>">&lt;</a>
			</c:if>
			<c:forEach begin="${model.pageRangeFirst}" end="${model.pageRangeLast}" var="page">
		        <a
					<c:if test="${ page == model.currentPageNumber}">
						style="color: #ffffff;"
					</c:if>
					title="${gotopage} ${page}" href="<portlet:renderURL portletMode="view">
					  <portlet:param name="action" value="browseMetadata"/>
					  <portlet:param name="pagesize" value="${model.pageSize}"/>
					  <portlet:param name="pagenumber" value="${page}"/>
				      <portlet:param name="currentSeite" value="${page}"/>
					  <portlet:param name="lectureseriesId" value="${model.lectureseriesId}"/>
					  </portlet:renderURL>"> ${page}
				</a>
			</c:forEach>
			<c:if test="${model.hasNext}">
			    <a title="${gotopage} ${model.currentPageNumber + 1}" href="<portlet:renderURL portletMode="view">
						<portlet:param name="action" value="browseMetadata"></portlet:param>
						<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
						<portlet:param name="pagenumber" value="${model.currentPageNumber + 1}"></portlet:param>
						<portlet:param name="currentSeite" value="${model.currentPageNumber + 1}"></portlet:param>
						<portlet:param name="lectureseriesId" value="${model.lectureseriesId}"/>
						</portlet:renderURL>">&gt;</a>
		        &nbsp; <a title="${gotolastpage}" href="<portlet:renderURL portletMode="view">
						<portlet:param name="action" value="browseMetadata"></portlet:param>
						<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
						<portlet:param name="pagenumber" value="${model.numberResultPages}"></portlet:param>
						<portlet:param name="currentSeite" value="${model.numberResultPages}"></portlet:param>
						<portlet:param name="lectureseriesId" value="${model.lectureseriesId}"/>
			   	       </portlet:renderURL>">&gt;&#124;</a>
			</c:if>
	</c:if>
</div>