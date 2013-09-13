<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="includeInternationalisation.jsp" %>

<div class="row">
	<div class="box">
		${resultspersite}
		<form style="display:inline" method="POST" action='<portlet:actionURL><portlet:param name="action" value="viewallusers"/><portlet:param name="roleId" value="${model.roleId}"/></portlet:actionURL>'>
			<select name="pagesize" onchange="submit();">
				<c:forEach items="10,20,30,40,50,100,200,300,500,1000,2000,3000,5000,10000" var="size">
					<option <c:if test="${size == model.pageSize}">selected="selected"</c:if> value="${size}"> ${size} </option>
				</c:forEach>
			</select>
		</form>
	</div>
	
	<div class="box">	
		<form style="display:inline" method="POST" action='<portlet:actionURL><portlet:param name="action" value="viewallusers"/><portlet:param name="pagesize" value="${model.pageSize}"/></portlet:actionURL>'>
			<select name="roleId" style="width:150px" onchange="submit();" >
				<OPTION value="<c:out value="0" />"><c:out value="-- ${chooserole} --" /></OPTION>
				<c:forEach items="${model.l2goRoles}" var="role">
					<OPTION <c:if test="${role.key == model.roleId}">selected="selected"</c:if> value="${role.key}"> ${role.value} </OPTION>
				</c:forEach>
			</select>
		</form>
	</div>
</div>

<c:if test="${not empty model.userList}">
	<div class="row">
		<div id="art">
			<div id="student">${student}</div>
			<div id="coordinator">${coordinator}</div>
			<div id="producer">${producer}</div>
			<div id="l2gadmin">${l2gadmin}</div>
		</div>
	</div>
	<c:forEach items="${model.userList}" var="user">
			<div class="datarow">
				<div class="box">
					<em class="italic">
						 ${user.firstName} ${user.lastName} (${user.screenName})
					</em>
				    <br />
					<span style="font-size:8px;">
						<c:if test="${user.isCoordinator}">
							<em>${coordinatorfor}</em> <c:forEach items="${user.objectCoordinator.facilityList}" var="e"><b>${e.name}</b> &nbsp; </c:forEach>
						</c:if>
						<c:if test="${user.isProducer}">
							<c:if test="${user.isCoordinator}"><br/></c:if>
							<em>${producerfor}</em> <c:forEach items="${user.objectProducer.facilityList}" var="e"> <b>${e.name}</b> &nbsp; </c:forEach>
						</c:if>						
					</span>					
				</div>
				
				<div class="buttons">

					<div class="aub">	
						<form style="display:inline" method="POST" action='<portlet:actionURL><portlet:param name="action" value="viewallusers"/><portlet:param name="roleId" value="${model.roleId}"/><portlet:param name="admin" value="1"/><portlet:param name="userId" value="${user.userId}"/><portlet:param name="pagesize" value="${model.pageSize}"/><portlet:param name="pagenumber" value="${model.currentPageNumber}"/></portlet:actionURL>' >
							<c:choose>
								<c:when test="${user.isL2goadmin==true}"><input type="checkbox" name="isL2goadmin" value="true" checked="checked" onclick="submit();"/></c:when>
								<c:otherwise><input type="checkbox" name="isL2goadmin" value="false" onclick="submit();"/></c:otherwise>
							</c:choose>
						</form>	
					</div>
					
					<div class="aub">
						<form style="display:inline" method="POST" action='<portlet:actionURL><portlet:param name="action" value="viewallusers"/><portlet:param name="roleId" value="${model.roleId}"/><portlet:param name="producer" value="1"/><portlet:param name="userId" value="${user.userId}"/><portlet:param name="pagesize" value="${model.pageSize}"/><portlet:param name="pagenumber" value="${model.currentPageNumber}"/></portlet:actionURL>' >
							<c:choose>
								<c:when test="${user.isProducer==true}"><input type="checkbox" name="isProducer" value="true" checked="checked" onclick="submit();"/></c:when>
								<c:otherwise><input type="checkbox" name="isProducer" value="false" disabled="disabled" onclick="submit();"/></c:otherwise>
							</c:choose>
						</form>
			
						<a href='<portlet:renderURL><portlet:param name="action" value="approvalproducer"/><portlet:param name="pagesize" value="${model.pageSize}"/><portlet:param name="pagenumber" value="${model.currentPageNumber}"/><portlet:param name="roleId" value="${model.roleId}"/><portlet:param name="userId" value="${user.userId}"/></portlet:renderURL>'>
							<input type="image" height="15" width="15" src="/lecture2go-theme/images/l2go/edit.png" alt="${edit}" title="${edit}">
						</a>
					</div>
					
					<div class="aub">
						<form style="display:inline" method="POST" action='<portlet:actionURL><portlet:param name="action" value="viewallusers"/><portlet:param name="roleId" value="${model.roleId}"/><portlet:param name="coordinator" value="1"/><portlet:param name="userId" value="${user.userId}"/><portlet:param name="pagesize" value="${model.pageSize}"/><portlet:param name="pagenumber" value="${model.currentPageNumber}"/></portlet:actionURL>' >
							<c:choose>
								<c:when test="${user.isCoordinator==true}"><input type="checkbox" name="isCoordinator" value="true" checked="checked" onclick="submit();"/></c:when>
								<c:otherwise><input type="checkbox" name="isCoordinator" value="false" disabled="disabled" onclick="submit();"/></c:otherwise>
							</c:choose>
						</form>	

						<a href='<portlet:renderURL><portlet:param name="action" value="approvalcoordinator"/><portlet:param name="pagesize" value="${model.pageSize}"/><portlet:param name="pagenumber" value="${model.currentPageNumber}"/><portlet:param name="roleId" value="${model.roleId}"/><portlet:param name="userId" value="${user.userId}"/></portlet:renderURL>'>
							<input type="image" height="15" width="15" src="/lecture2go-theme/images/l2go/edit.png" alt="${edit}" title="${edit}">
						</a>
					</div>

					<div class="aub">
						<form style="display:inline" method="POST" action='<portlet:actionURL><portlet:param name="action" value="viewallusers"/><portlet:param name="roleId" value="${model.roleId}"/><portlet:param name="student" value="1"/><portlet:param name="userId" value="${user.userId}"/><portlet:param name="pagesize" value="${model.pageSize}"/><portlet:param name="pagenumber" value="${model.currentPageNumber}"/></portlet:actionURL>' >
							<c:choose>
									<c:when test="${user.isStudent==true}"><input type="checkbox" name="isStudent" value="true" checked="checked" onclick="submit();"/></c:when>
									<c:otherwise><input type="checkbox" name="isStudent" value="false" onclick="submit();"/></c:otherwise>
							</c:choose>
						</form>	
					</div>
											
				</div>
			</div>
	</c:forEach>
</c:if>

<br/>
<%@ include file="includeAdminUserPaging.jsp" %>