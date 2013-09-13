<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ include file="includeInternationalisation.jsp" %>

<script type="text/javascript">
    function <portlet:namespace />initEditor() {
         return '${model.longDescription}';
    }

    function <portlet:namespace />extractCodeFromEditor() {
         //var x = document.<portlet:namespace />form.<portlet:namespace />htmlCodeFromEditorPlacedHere.value = window.<portlet:namespace />editor.getHTML();
         var x = document.getElementById("description").value = window.<portlet:namespace />editor.getHTML();
    }
</script>

<c:if test="${model.success}">
	<font color="green">
		<br/><br/><br/>
		<p style="color:green; font-size:16px;">${videodatasetsaved}</p>
	</font>
	<meta http-equiv="refresh" content="0; URL=/web/vod/myvideos?lectureseriesId=0"> 
</c:if>

<spring:hasBindErrors name="model">
	<font color="red">
		<p style="color:red; font-size:16px;">${pleasecorrectinput}</p>
	</font>
</spring:hasBindErrors>

<c:if test="${!model.success}">
	<div align="right">
		<a href='/web/vod/myvideos'>${gotooverview}</a>
	</div>
	
	<br><br>
	
	<div class="datarow">
		<div id="verysmallbox">
			<%@ include file="includeProducerLectureseriesDropdownFilter.jsp" %>
			<form style="display:inline" method="POST" action="<portlet:actionURL/>">
		</div>	
	
		&nbsp;
		
		<div id="verysmallbox">
			<!-- language Beginn -->
			<spring:bind path="model.language">
				<select size="1" name='<c:out value="${status.expression}"/>'>
					<option value="">${language}</option>
					<c:forEach items="${languagesforselect}" var="lang">
						<option value="${lang}" <c:if test="${model.language==lang}">selected</c:if>>${lang}</option>
					</c:forEach>
				</select>
							
				<font color="red">
					<c:out value="${status.errorMessage}" />
				</font>
			</spring:bind>
			<em style="color:#F17B0D;">*</em>
			<!-- language Ende -->
		</div>
	</div>	
	
	<div class="datarow">
		<!-- Title Beginn -->
		<em>${title}:</em><em style="color:#F17B0D;">*</em>
		<spring:bind path="model.title">
			<font color="red">
				<c:out value="${status.errorMessage}" />
			</font>
			<br />
			<input type="text" maxlength="200" size="125"
			name="<c:out value="${status.expression}" />"
			value="<c:out value="${model.title}" />" />
		</spring:bind>			
		<!-- Title Ende -->
	</div>
		        
	<div class="datarow">
		<!-- Tags Beginn -->
		<em>${tags}:</em><br />
		<spring:bind path="model.tags">
			<input type="text" maxlength="200" size="125"
			name="<c:out value="${status.expression}" />"
			value="<c:out value="${model.tags}" />" />
		</spring:bind>
		<!-- Tags Ende -->
	</div>
	
	<div class="datarow">		
       	<!-- creator Beginn -->
       	<div id="smallbox">
			<em>${author}:</em><em style="color:#F17B0D;">*</em>
			<spring:bind path="model.creator">
				<font color="red">
					<c:out value="${status.errorMessage}" />
				</font>
				<br />
				<input type="text" maxlength="200" size="25"
				name="<c:out value="${status.expression}" />"
				value="<c:out value="${model.creator}" />" />
			</spring:bind>
		</div>
	    <!-- creator Ende -->
		
		<!-- rightsHolder Beginn -->
		<div id="smallbox">
			<em>${videoproducer}:</em><em style="color:#F17B0D;">*</em>
			<spring:bind path="model.rightsHolder">
				<font color="red">
					<c:out value="${status.errorMessage}" />
				</font>
				<br />
				<input type="text" maxlength="200" size="25"
				name="<c:out value="${status.expression}" />"
				value="<c:out value="${model.rightsHolder}" />" />
			</spring:bind>
		</div>
		<!-- rightsHolder Ende -->

		<!-- publisher Beginn -->
		<div id="smallbox">
			<em>${publisher}:</em><em style="color:#F17B0D;">*</em>
			<spring:bind path="model.publisher">
				<font color="red">
					<c:out value="${status.errorMessage}" />
				</font>
				<br />
				<input type="text" maxlength="200" size="25"
				name="<c:out value="${status.expression}" />"
				value="<c:out value="${model.publisher}" />" />
			</spring:bind>
		</div>
		<!-- publisher Ende -->
	</div>
	
 	<div class="datarow">
		<%@ include file="includeLicense.html" %>
	</div>   

    <div class="datarow">
		<!-- citation2go begin -->
		<em>${citation2go}:</em><em style="color:#F17B0D;">*</em>
		<spring:bind path="model.citation2go">
			<font color="red">
			     <c:out value="${status.errorMessage}" />
			</font>
		</spring:bind>
		<br/><br/>
		<div>
			<spring:bind path="model.citation2go">
			   ${activated} &nbsp;<input type="radio" name="citation2go" value="1" <c:if test="${model.citation2go==1}">checked="checked"</c:if> /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			   ${disabled} &nbsp;<input type="radio" name="citation2go" value="0" <c:if test="${model.citation2go==0}">checked="checked"</c:if>/>				
			</spring:bind>
		</div>
		<!-- citation2go end --> 
	 </div>
		
     <div class="datarow">
		<!-- description Beginn -->
		<em>${description}:</em><br />
		<spring:bind path="model.description">
			<liferay-ui:input-editor />
			<input id ="description" name="<c:out value="${status.expression}" />" type="hidden" value="<c:out value="de" />" />
		</spring:bind>
		<!-- description Ende -->
	 </div>
                
     <input type="submit" value="${save}" onclick="<portlet:namespace />extractCodeFromEditor()"/>
     <input type="hidden" name="lectureseriesId" value="${model.lectureseriesId}"/>
	</form>
</c:if>