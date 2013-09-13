<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="includeInternationalisation.jsp" %>

<div style="width:100%;">
	<div style="float:left;width:100%;">
		<form method="POST" action="<portlet:renderURL portletMode="view"><portlet:param name="action" value="view"></portlet:param></portlet:renderURL>">
			<div style="float:left;width:100%;">
				${filter}
				<select name="facultyid" onchange="submit()" style="width:250px">
					<option value="">--${selectfaculty}--</option>
					<c:forEach items="${model.allFaculties}" var="faculty">
						<option <c:if test="${faculty.key eq model.filters['facultyid']}">selected="selected"</c:if> value="${faculty.key}">${faculty.value}</option>
					</c:forEach>
				</select>
				
				<select name="producerid" onchange="submit();">
					<option value="">--${selectproducer}--</option>
					<c:forEach items="${model.allProducers}" var="producer">
						<option <c:if test="${producer.key eq model.filters['producerid']}">selected="selected"</c:if> value="${producer.key}">${producer.value}</option>
					</c:forEach>
				</select>
				
				<select name="semester" onchange="submit();">
					<option value="">--${selectsemester}--</option>
					<c:forEach items="${model.allSemesters}" var="semester">
						<option <c:if test="${semester == model.filters['semester']}">selected="selected"</c:if> value="${semester}">${semester}</option>
					</c:forEach>
				</select>
				
				<select name="approved" onchange="submit();">
					<option value="all">--${selectstatus}--</option>
					<option <c:if test="${'approvedtrue' eq model.filters['approved']}">selected="selected"</c:if> value="approvedtrue">${approved}</option>
					<option <c:if test="${'approvedfalse' eq model.filters['approved']}">selected="selected"</c:if> value="approvedfalse">${pending}</option>
				</select>
			</div>
			
			<div style="float:left;width:100%;margin-top:15px;">
				${resultspersite}
				<select name="pagesize" onchange="submit();">
					<c:forEach items="10,20,30,40,50" var="size">
						<option <c:if test="${size == model.pageSize}">selected="selected"</c:if> value="${size}"> ${size} </option>
					</c:forEach>
				</select>
				<br/>
				<br/>
				<br/>
				<a href="<portlet:renderURL portletMode="view"><portlet:param name="action" value="edit"></portlet:param></portlet:renderURL>" >${addnewlectureserie}</a>
				<br/>
				<br/>
			</div>
		</form>
	</div>
</div>

<br/>
<br/>
<br/>
<br/>

<c:if test="${not empty model.lectureseriesList}">
	<div class="metadatacontainer">
		<c:forEach items="${model.lectureseriesList}" var="lectureseries">
			<div class="datarow">
				<div class="buttons">
					<c:if test="${lectureseries.deleteable}">
					    <form name="delete[${lectureseries.id}]" method="post" action="<portlet:actionURL portletMode="view">
					    		<portlet:param name="action" value="delete"></portlet:param>
					    		<portlet:param name="lectureseriesId" value="${lectureseries.id}"></portlet:param>
					    		<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
					    		<portlet:param name="pagenumber" value="${model.currentPageNumber}"></portlet:param>
					    		<portlet:param name="semester" value="${model.filters['semester']}"></portlet:param>
					    		<portlet:param name="approved" value="${model.filters['approved']}"></portlet:param>
					    		<portlet:param name="facultyid" value="${model.filters['facultyid']}"></portlet:param>
					    		<portlet:param name="producerid" value="${model.filters['producerid']}"></portlet:param>
					    	</portlet:actionURL>"
					    >
						    <input type="image" onclick="return window.confirm('${yourareabouttodeletealllectureseriedata}')" src="/lecture2go-theme/images/l2go/delete.gif" alt="${deletelectureserie}" title="${deletelectureserie}" />
					    </form>
				    </c:if>
				    <c:if test="${lectureseries.approved}">
						<form method="post" action='<portlet:renderURL portletMode="view">
								<portlet:param name="action" value="edit"></portlet:param>
								<portlet:param name="lectureseriesId" value="${lectureseries.id}"></portlet:param>
								<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
								<portlet:param name="pagenumber" value="${model.currentPageNumber}"></portlet:param>
								<portlet:param name="semester" value="${model.filters['semester']}"></portlet:param>
								<portlet:param name="approved" value="${model.filters['approved']}"></portlet:param>
								<portlet:param name="facultyid" value="${model.filters['facultyid']}"></portlet:param>
								<portlet:param name="producerid" value="${model.filters['producerid']}"></portlet:param>
							</portlet:renderURL>'
						>
							<input type="image" src="/lecture2go-theme/images/l2go/edit.png" alt="${editinfo}" title="${editinfo}">
						</form>
					</c:if>
					<c:if test="${!lectureseries.approved}">
						<form method="post" action='<portlet:renderURL portletMode="view">
								<portlet:param name="action" value="edit"></portlet:param>
								<portlet:param name="lectureseriesId" value="${lectureseries.id}"></portlet:param>
								<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
								<portlet:param name="pagenumber" value="${model.currentPageNumber}"></portlet:param>
								<portlet:param name="semester" value="${model.filters['semester']}"></portlet:param>
								<portlet:param name="approved" value="${model.filters['approved']}"></portlet:param>
								<portlet:param name="facultyid" value="${model.filters['facultyid']}"></portlet:param>
								<portlet:param name="producerid" value="${model.filters['producerid']}"></portlet:param>
							</portlet:renderURL>'
						>
							<input type="image" src="/lecture2go-theme/images/l2go/approve.png" alt="${editandapprove}" title="${editandapprove}" width="20" height="20">
						</form>
					</c:if>
			    </div>
				<div>
					<em class="italic">
					  	${lectureseries.number} : ${lectureseries.name}
					</em>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>

<c:if test="${empty model.lectureseriesList}">
	<br/>
	<br/>
	${nolectureseriestoapprove}<br/><br/>
	${pleasechoosefilterunlockedtomanagelectureseries}
</c:if>

<%@ include file="includeCoordinatorLectureseriesPaging.jsp" %>
