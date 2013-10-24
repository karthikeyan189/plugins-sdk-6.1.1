<form style="display:inline" method="POST" action='<portlet:renderURL/>'>
	<spring:bind path="model.lectureseriesId">
		<select name="lectureseriesId" value="${model.lectureseriesId}" onchange="submit()" style="width:250px" size="1">
			<OPTION value="<c:out value="0" />"><c:out value="-- ${selectlectureseries} --" /></OPTION>
				<c:forEach items="${model.lectureseriesList}" var="lectureseries">
					<OPTION value="${lectureseries.id}" <c:if test="${lectureseries.id==model.lectureseriesId}"> <c:out value="selected" /> </c:if> > <c:out value=" ${lectureseries.name} ( ${lectureseries.number} - ${lectureseries.semesterName} )" /></OPTION>
				</c:forEach>
		</select>&nbsp;<em style="color:#F17B0D;">*</em>
		<font color="red">
			<c:out value="${status.errorMessage}" />
		</font>
	</spring:bind>
	<input type="hidden" name="autocompare" value="1">
</form>
