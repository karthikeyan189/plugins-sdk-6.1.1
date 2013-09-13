<div class="paging" align="center">
	<c:if test="${model.numberResultPages!=0}">
					<c:if test="${model.hasPrev}">
						<a title="${gotofirstpage}" href="<portlet:renderURL portletMode="view">
																	<portlet:param name="action" value="viewproducers"></portlet:param>
																	<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
																	<portlet:param name="pagenumber" value="1"></portlet:param>
	
																	<portlet:param name="videoSeite" value="1"></portlet:param>
																	<portlet:param name="facultyId" value="${model.facultyId}"></portlet:param>
																	<portlet:param name="subFacility1Id" value="${model.subFacility1Id}"></portlet:param>
																	<portlet:param name="subFacility2Id" value="${model.subFacility2Id}"></portlet:param>
																	<portlet:param name="browse" value="1"></portlet:param>
														  </portlet:renderURL>">&#124;&lt;</a>
					    &nbsp;
					    <a title="${gotopage} ${model.currentPageNumber - 1}" href="<portlet:renderURL portletMode="view">
																		<portlet:param name="action" value="viewproducers"></portlet:param>
																		<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
																		<portlet:param name="pagenumber" value="${model.currentPageNumber - 1}"></portlet:param>
	
																		<portlet:param name="videoSeite" value="${model.currentPageNumber - 1}"></portlet:param>
																		<portlet:param name="facultyId" value="${model.facultyId}"></portlet:param>
																		<portlet:param name="subFacility1Id" value="${model.subFacility1Id}"></portlet:param>
																		<portlet:param name="subFacility2Id" value="${model.subFacility2Id}"></portlet:param>
																		<portlet:param name="browse" value="1"></portlet:param>
																</portlet:renderURL>">&lt;</a>
					</c:if>
					<c:forEach begin="${model.pageRangeFirst}" end="${model.pageRangeLast}" var="page">
					        <a
								<c:if test="${ page == model.currentPageNumber}">
									style="color: #ffffff;"
								</c:if>
								title="${gotopage} ${page}" href="<portlet:renderURL portletMode="view">
																	  		<portlet:param name="action" value="viewproducers"></portlet:param>
																	  		<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
																	  		<portlet:param name="pagenumber" value="${page}"></portlet:param>
	
																			<portlet:param name="videoSeite" value="${page}"></portlet:param>
																			<portlet:param name="facultyId" value="${model.facultyId}"></portlet:param>
																			<portlet:param name="subFacility1Id" value="${model.subFacility1Id}"></portlet:param>
																			<portlet:param name="subFacility2Id" value="${model.subFacility2Id}"></portlet:param>
																			<portlet:param name="browse" value="1"></portlet:param>
																	  </portlet:renderURL>"> ${page}
							</a>
					</c:forEach>
					<c:if test="${model.hasNext}">
					    <a title="${gotopage} ${model.currentPageNumber + 1}" href="<portlet:renderURL portletMode="view">
																		<portlet:param name="action" value="viewproducers"></portlet:param>
																		<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
																		<portlet:param name="pagenumber" value="${model.currentPageNumber + 1}"></portlet:param>
	
																		<portlet:param name="videoSeite" value="${model.currentPageNumber + 1}"></portlet:param>
																		<portlet:param name="facultyId" value="${model.facultyId}"></portlet:param>
																		<portlet:param name="subFacility1Id" value="${model.subFacility1Id}"></portlet:param>
																		<portlet:param name="subFacility2Id" value="${model.subFacility2Id}"></portlet:param>
																		<portlet:param name="browse" value="1"></portlet:param>
																 </portlet:renderURL>">&gt;</a>
			            &nbsp; <a title="${gotolastpage}" href="<portlet:renderURL portletMode="view">
																<portlet:param name="action" value="viewproducers"></portlet:param>
																<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
																<portlet:param name="pagenumber" value="${model.numberResultPages}"></portlet:param>
	
																<portlet:param name="videoSeite" value="${model.numberResultPages}"></portlet:param>
																<portlet:param name="facultyId" value="${model.facultyId}"></portlet:param>
																<portlet:param name="subFacility1Id" value="${model.subFacility1Id}"></portlet:param>
																<portlet:param name="subFacility2Id" value="${model.subFacility2Id}"></portlet:param>
																<portlet:param name="browse" value="1"></portlet:param>
														   </portlet:renderURL>">&gt;&#124;</a>
			        </c:if>
	</c:if>
</div>