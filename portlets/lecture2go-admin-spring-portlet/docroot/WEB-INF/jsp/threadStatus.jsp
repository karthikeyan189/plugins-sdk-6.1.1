<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="includeInternationalisation.jsp" %>

<portlet:actionURL var="actionURL"></portlet:actionURL>

<script>
   function setState(state)
   {
      document.startstop.state.value = state;
   }
</script>

<div class="row">
	${threadisupdatedevery}
		<c:choose>
			<c:when test="${model.interrupted}"><span style="color: red"><b>${stopped}</b></span></c:when>
			<c:otherwise><span style="color: green"><b>${started} ${model.time} ${hours}</b></span></c:otherwise>
		</c:choose>
	
	<br/><br/>
	<form name="startstop" method="POST" action="<portlet:actionURL/>">
		<c:choose>
			<c:when test="${model.interrupted}">
				<input type="submit" value="${start}" class="margin0" name="action" onclick="setState('1')"/>
				<input type="hidden" name="state" />
			</c:when>
			<c:otherwise>
				<input type="submit" value="${stop}" class="margin0" name="action" onclick="setState('0')"/> 
				<input type="hidden" name="state" />
			</c:otherwise>
		</c:choose>
	</form>
	<br/><br/>
</div>