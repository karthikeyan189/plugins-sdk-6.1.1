<div class="paging" align="center">
	<c:if test="${model.numberResultPages!=0}">
		${page}:&nbsp;
		<c:if test="${model.hasPrev}">
			<a title="Zur ersten Seite" href="<portlet:renderURL portletMode="view"><portlet:param name="pagesize" value="${model.pageSize}"></portlet:param><portlet:param name="pagenumber" value="1"></portlet:param><portlet:param name="semester" value="${model.filters['semester']}"></portlet:param><portlet:param name="approved" value="${model.filters['approved']}"></portlet:param><portlet:param name="facultyid" value="${model.filters['facultyid']}"></portlet:param><portlet:param name="producerid" value="${model.filters['producerid']}"></portlet:param></portlet:renderURL>">&lt;&lt;</a>
		    &nbsp;
		    <a title="Zur n&auml;chsten Seite" href="<portlet:renderURL portletMode="view"><portlet:param name="pagesize" value="${model.pageSize}"></portlet:param><portlet:param name="pagenumber" value="${model.currentPageNumber - 1}"></portlet:param><portlet:param name="semester" value="${model.filters['semester']}"></portlet:param><portlet:param name="approved" value="${model.filters['approved']}"></portlet:param><portlet:param name="facultyid" value="${model.filters['facultyid']}"></portlet:param><portlet:param name="producerid" value="${model.filters['producerid']}"></portlet:param></portlet:renderURL>">&lt;</a>
		</c:if>
		<c:forEach begin="${model.pageRangeFirst}" end="${model.pageRangeLast}" var="page">
	        <a
				<c:if test="${ page == model.currentPageNumber}">
					style="color: #ffffff;"
				</c:if>
				title="${gotonextpage}" href="<portlet:renderURL portletMode="view"><portlet:param name="pagesize" value="${model.pageSize}"></portlet:param><portlet:param name="pagenumber" value="${page}"></portlet:param><portlet:param name="semester" value="${model.filters['semester']}"></portlet:param><portlet:param name="approved" value="${model.filters['approved']}"></portlet:param><portlet:param name="facultyid" value="${model.filters['facultyid']}"></portlet:param><portlet:param name="producerid" value="${model.filters['producerid']}"></portlet:param></portlet:renderURL>"> ${page}
			</a>
		</c:forEach>
		<c:if test="${model.hasNext}">
		    <a title="${gotonextpage}" href="<portlet:renderURL portletMode="view"><portlet:param name="pagesize" value="${model.pageSize}"></portlet:param><portlet:param name="pagenumber" value="${model.currentPageNumber + 1}"></portlet:param><portlet:param name="semester" value="${model.filters['semester']}"></portlet:param><portlet:param name="approved" value="${model.filters['approved']}"></portlet:param><portlet:param name="facultyid" value="${model.filters['facultyid']}"></portlet:param><portlet:param name="producerid" value="${model.filters['producerid']}"></portlet:param></portlet:renderURL>">&gt;</a>
		    	&nbsp;
				<a title="${gotolastpage}" href="<portlet:renderURL portletMode="view"><portlet:param name="pagesize" value="${model.pageSize}"></portlet:param><portlet:param name="pagenumber" value="${model.numberResultPages}"></portlet:param><portlet:param name="semester" value="${model.filters['semester']}"></portlet:param><portlet:param name="approved" value="${model.filters['approved']}"></portlet:param><portlet:param name="facultyid" value="${model.filters['facultyid']}"></portlet:param><portlet:param name="producerid" value="${model.filters['producerid']}"></portlet:param></portlet:renderURL>">&gt;&gt;</a>
		</c:if>
		&nbsp;&nbsp;${model.currentPageNumber} ${from} ${model.numberResultPages}
	</c:if>
</div>