<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ include file="includeInternationalisation.jsp" %>

<div align="right">
	<a href='<portlet:renderURL portletMode="view">
			<portlet:param name="action" value="view"></portlet:param>
			<portlet:param name="lectureseriesId" value="${lectureseries.id}"></portlet:param>
			<portlet:param name="pagesize" value="${model.pageSize}"></portlet:param>
			<portlet:param name="pagenumber" value="${model.currentPageNumber}"></portlet:param>
			<portlet:param name="semester" value="${model.filters['semester']}"></portlet:param>
			<portlet:param name="approved" value="${model.filters['approved']}"></portlet:param>
			<portlet:param name="facultyid" value="${model.filters['facultyid']}"></portlet:param>
			<portlet:param name="producerid" value="${model.filters['producerid']}"></portlet:param>	
		</portlet:renderURL>'
	>
		${gotooverview}
	</a>
</div>

<br><br>

<spring:hasBindErrors name="model">
	<font color="red">
		<p style="color:red; font-size:16px;">${pleasecorrectinput}</p>
	</font>
</spring:hasBindErrors>

<portlet:actionURL var="actionURL"><portlet:param name="action" value="edit"></portlet:param></portlet:actionURL>

<form:form action="${actionURL}" commandName="model">
		
	<form:hidden path="pageSize" htmlEscape="true" />
	<form:hidden path="currentPageNumber" htmlEscape="true" />
	
	<c:forEach items="${model.filters}" var="filter" varStatus="rowCounter" >
		<c:set var="myKey" value="'${filter.key}'"/>
    	<form:hidden path="filters[${myKey}]" htmlEscape="true" />
	</c:forEach>
	
    <form:hidden path="id" />
    
		<div class="datarow">
			<div class="formtitle">${lectureid}:<em style="color:#F17B0D;">*</em></div>
			<form:input path="lectureseriesNumber"/>	
			<div style="color:#ff0000; display:inline;"><form:errors path="lectureseriesNumber"/></div><span class="hint">${enterlectureid}</span>
		</div>

		<div class="datarow">
			<div class="formtitle">${lecturename}:<em style="color:#F17B0D;">*</em></div>
			<form:input path="lectureseriesName"/>
			<div style="color:#ff0000; display:inline;"><form:errors path="lectureseriesName"/></div><span class="hint">${enterlecturename}</span>
		</div>
		
		<div class="datarow">
			<div class="formtitle">${lecturetype}:<em style="color:#F17B0D;">*</em></div>
			<spring:bind path="eventType">
				<select size="1" name='<c:out value="${status.expression}"/>'>
					<option value=""></option>
					<c:forEach items="${eventtypesforselect}" var="type">
						<option value="${type}" <c:if test="${model.eventType==type}">selected</c:if>>${type}</option>
					</c:forEach>
				</select>
			</spring:bind>
			<div style="color:#ff0000; display:inline;"><form:errors path="eventType"/></div><span class="hint">${selectlecturetype}</span>
		</div>
		
		<div class="datarow">
			<div id="facultySelect" style="float:left;">
				<div class="formtitle">${facilities}:<em style="color:#F17B0D;">*</em></div>
				<select size="1" id="facilityName">
					<option value=""></option>
					<c:forEach items="${model.allFaculties}" var="faculty">
						<option value="${faculty.key}">${faculty.value}</option>
					</c:forEach>
				</select>
			</div>
				
			<button type="button" class="addFacultyButton">${add}</button>
			
			<div style="color:#ff0000; display:inline;"><form:errors path="faculties"/></div><span class="hint">${pleaceaddfacility}</span>
				
			<div style="display:none; margin-top:5px;">
				<form:input path="facilityName"  id="inputFacility" disabled="true"/>
			</div>
			
			<div id="facultiesContainer" style="margin-top:5px;">
				<c:forEach items="${model.faculties}" var="faculty" varStatus="rowCounter" >
					<div class="dynamicRow">
						<div class="formtitle">${faculty.value}</div>
						<button type="button" class="deleteRowButton" onclick="var p = jQuery(this).parent();p.remove()">x</button>
						<c:set var="myKey" value="'${faculty.key}'"/>
		        		<form:hidden path="faculties[${myKey}]" htmlEscape="true" />
					</div>
				</c:forEach>
			</div>
		</div>
		
		<c:if test="${not empty model.allProducers}">
		<div class="datarow">
			<div class="dynamicRow">
				<div id="producerSelect" style="float:left;">
					<div class="formtitle">${producer}:<em style="color:#F17B0D;">*</em></div>
					<select size="1" >
						<option value=""></option>
						<c:forEach items="${model.allProducers}" var="producer">
							<option value="${producer.key}">${producer.value}</option>
						</c:forEach>
					</select>
				</div>
				<button type="button" class="addProducerButton">${add}</button>
				<div style="color:#ff0000; display:inline;"><form:errors path="producers"/></div><span class="hint">${addoneproducer}</span>
			</div>
			<div id="producersContainer" style="margin-top:5px;">
				<c:forEach items="${model.producers}" var="producer" varStatus="rowCounter" >
					<div class="dynamicRow">
					<div class="formtitle">${producer.value}</div>
					<button type="button" class="deleteRowButton" onclick="var p = jQuery(this).parent();p.remove()">x</button>
					<c:set var="myKey" value="'${producer.key}'"/>
	        		<form:hidden path="producers[${myKey}]" htmlEscape="true" />
					</div>
				</c:forEach>
			</div>
		</div>
		</c:if>

		<div class="datarow">
			<div class="formtitle">${shortdescription}:</div>
			<form:input path="shortDescription"/>
		</div>
		
		<div class="datarow">
			<div>
				<div class="formtitle">${semester}:</div>
				<form:select path="semesterName" items="${model.allSemesters}"></form:select>
				<a style="font-size:8px;" href="javascript:void(0);" id="otherSemesterLink">${addnewsemester}</a>
			</div>
			<div style="display:none;margin-top:5px;">
				<form:input path="semesterName" id="inputSemester" disabled="true"/>
			</div>
		</div>

		<div class="datarow">
			<div id="languageSelect" style="float:left;">
				<div class="formtitle">${languages}:<em style="color:#F17B0D;">*</em></div>
				<select size="1">
					<option value=""></option>
					<c:forEach items="${languagesforselect}" var="lang">
						<option value="${lang}">${lang}</option>
					</c:forEach>
				</select>
			</div>
			
			<button type="button" class="addLanguageButton">${add}</button>
			
			<div style="color:#ff0000; display:inline;"><form:errors path="languages"/></div><span class="hint">${addonelanguage}</span>
				
			<div id="languagesContainer" style="margin-top:5px;">
				<c:forEach items="${model.languages}" var="language" varStatus="rowCounter" >
					<div class="dynamicRow">
						<div class="formtitle">${language.value}</div>
						<button type="button" class="deleteRowButton" onclick="var p = jQuery(this).parent();p.remove()">x</button>
						<c:set var="myKey" value="'${language.key}'"/>
			       		<form:hidden path="languages[${myKey}]" htmlEscape="true" />
					</div>
				</c:forEach>
			</div>
		</div>
				
		<div class="datarow">		
			<div class="formtitle">${lecturers}:</div>
			<form:input path="instructorNames"/>
			<span class="hint">${exampleprofname}</span>
		</div>
		
		<div class="datarow">		
			<div class="formtitle">${password}:</div>
			<form:input path="password"/>
		</div>

		<div class="datarow">		
			${description}:
			<br/>
			<spring:bind path="model.longDescription">
				<liferay-ui:input-editor/>
				<input id ="longDescription" name="<c:out value="${status.expression}" />" type="hidden" value="" />
			</spring:bind>
       </div>
   
       <input type="submit" value="${save}" onclick="<portlet:namespace />extractCodeFromEditor()"/>
</form:form>

<script type="text/javascript">

function <portlet:namespace />initEditor() {
    return '${model.longDescription}';
}

function <portlet:namespace />extractCodeFromEditor() {
    var x = document.getElementById("longDescription").value = window.<portlet:namespace />editor.getHTML();
}

	jQuery(document).ready(function(){
		
		jQuery('.addFacultyButton').click(function(){
			jQuery('#facultySelect select option:selected').each(function(){
				var length = jQuery('#facultiesContainer input').length
				if(jQuery(this).text()!="" && length==0){
					var str = '<div class="dynamicRow">';
					str += '<div class="formtitle" >'+jQuery(this).text().replace(/^(\-)+/, '')+'</div><button type="button" class="deleteRowButton" onclick="var p = jQuery(this).parent();p.remove()">x</button>'
					str += '<input type="hidden" name="faculties[\''+jQuery(this).val()+'\']" id="faculties\''+jQuery(this).val()+'\'" value="'+jQuery(this).text()+'" />';
					str += '</div>';
					
					jQuery('#facultiesContainer').html(jQuery('#facultiesContainer').html() + str);
					jQuery(this).removeAttr('selected');
				}
			});
		});


		jQuery('.addProducerButton').click(function(){
			jQuery('#producerSelect select option:selected').each(function(){
				if(jQuery(this).text()!=""){
					var length = jQuery('#producersContainer input').length
					var str = '<div class="dynamicRow">';
					str += '<div class="formtitle" >'+jQuery(this).text()+'</div><button type="button" class="deleteRowButton" onclick="var p = jQuery(this).parent();p.remove()">x</button>'
					str += '<input type="hidden" name="producers[\''+jQuery(this).val()+'\']" id="producers\''+jQuery(this).val()+'\'" value="'+jQuery(this).text()+'" />';
					str += '</div>';
					
					jQuery('#producersContainer').html(jQuery('#producersContainer').html() + str);
					jQuery(this).removeAttr('selected');
				}
			});
		});


		jQuery('.addLanguageButton').click(function(){
			jQuery('#languageSelect select option:selected').each(function(){
				if(jQuery(this).text()!=""){
					var length = jQuery('#languagesContainer input').length
					var str = '<div class="dynamicRow">';
					str += '<div class="formtitle" >'+jQuery(this).text()+'</div><button type="button" class="deleteRowButton" onclick="var p = jQuery(this).parent();p.remove()">x</button>'
					str += '<input type="hidden" name="languages[\''+jQuery(this).val()+'\']" id="languages\''+jQuery(this).val()+'\'" value="'+jQuery(this).text()+'" />';
					str += '</div>';
					
					jQuery('#languagesContainer').html(jQuery('#languagesContainer').html() + str);
					jQuery(this).removeAttr('selected');
				}
			});
		});
		

		
		jQuery('#otherEventCategoryLink').click(function(){
			jQuery('#inputEventCategory').removeAttr('disabled');
			jQuery('#inputEventCategory').parent().show();
			jQuery('#eventCategory').attr('disabled', 'disabled');
		});

		jQuery('#otherSemesterLink').click(function(){
			jQuery('#inputSemester').removeAttr('disabled');
			jQuery('#inputSemester').parent().show();
			jQuery('#semesterName').attr('disabled', 'disabled');
		});
		
		jQuery('#otherFacilityLink').click(function(){
			jQuery('#inputFacility').removeAttr('disabled');
			jQuery('#inputFacility').parent().show();
			jQuery('#facilityName').attr('disabled', 'disabled');
		});
		
	}
	);

</script>