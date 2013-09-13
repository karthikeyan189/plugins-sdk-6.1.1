<div class="facilities">
		<form method="POST" action="<portlet:actionURL  portletMode="view" />">
		<spring:bind path="model.facultyId">
			<select
					name="facultyId"
					value="<c:out value="${model.facultyId}" />"
					onchange="submit()"
					width="300px" 
					style="width:300px" 
			>
					<OPTION value="<c:out value="0" />"><c:out value="-- ${selectfaculty} --" /></OPTION>
					<c:forEach items="${model.facultyList}" var="faculty">
							<OPTION value="${faculty.id}" <c:if test="${faculty.id==model.facultyId}"> <c:out value="selected" /> </c:if> > <c:out value="${faculty.name}" /></OPTION>
					</c:forEach>
			</select>
		</spring:bind>

		<spring:bind path="model.currentSeite">
		<input type="hidden" name="currentSeite" value="${model.currentSeite}" />
		</spring:bind>
		</form>
		&nbsp;&nbsp;&nbsp;
		<c:if test="${model.facultyId!=0 && model.subFacility1ListVisible==true}">
			<form method="POST" action="<portlet:actionURL  portletMode="view" />">
			<spring:bind path="model.subFacility1Id">
				<select
						name="subFacility1Id"
						value="<c:out value="${model.subFacility1Id}" />"
						onchange="submit()"
						width="300px" 
						style="width:300px" 
				>
						<OPTION value="<c:out value='0'  />"><c:out value="-- ${selectarea} --" /></OPTION>
						<c:forEach items="${model.subFacility1List}" var="subFacility1">
								<OPTION value="${subFacility1.id}" <c:if test="${subFacility1.id==model.subFacility1Id}"> <c:out value="selected" /> </c:if> > <c:out value="${subFacility1.name}" /></OPTION>
						</c:forEach>
				</select>
			</spring:bind>

			<spring:bind path="model.currentSeite">
				<input type="hidden" name="currentSeite" value="${model.currentSeite}" />
			</spring:bind>
			<spring:bind path="model.facultyId">
				<input type="hidden" name="facultyId" value="${model.facultyId}" />
			</spring:bind>
			</form>
			&nbsp;&nbsp;&nbsp;
		</c:if>
		
		<c:if test="${model.subFacility1Id!=0 && model.subFacility2ListVisible==true}">
			<form method="POST" action="<portlet:actionURL portletMode="view" />">
				<spring:bind path="model.lectureseriesId">
					<select
							name="lectureseriesId"
							value="<c:out value="" />"
							onchange="submit()"
							width="300px" 
							style="width:300px" 
					>
							<OPTION value="<c:out value="" />"><c:out value="-- ${selectlectureseries} --" /></OPTION>
							<c:forEach items="${model.subFacility2List}" var="subFacility2">
									<OPTION value="${subFacility2.id}" <c:if test="${subFacility2.id==model.lectureseries.id}"> <c:out value="selected" /> </c:if> > <c:out value="${subFacility2.number} - ${subFacility2.name}" /></OPTION>
							</c:forEach>
					</select>
				</spring:bind>

				<spring:bind path="model.currentSeite">
					<input type="hidden" name="currentSeite" value="${model.currentSeite}" />
				</spring:bind>
				<spring:bind path="model.facultyId">
					<input type="hidden" name="facultyId" value="${model.facultyId}" />
				</spring:bind>
				<spring:bind path="model.subFacility1Id">
					<input type="hidden" name="subFacility1Id" value="${model.subFacility1Id}" />
				</spring:bind>
				<spring:bind path="model.path">
					<input type="hidden" name="method" value="post" />
				</spring:bind>
			</form>
			&nbsp;&nbsp;&nbsp;
		</c:if>
</div>