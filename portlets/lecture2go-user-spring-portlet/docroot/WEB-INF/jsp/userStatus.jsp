<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ include file="includeInternationalisation.jsp" %>

<strong class="italic">${userstatus}</strong>

<br/>

${loggedinas} <em style="color: white;">${model.user.firstName} ${model.user.middleName} ${model.user.familyName}</em>.
${roleslinkedtoaccount} <br/><br/>
<c:forEach items="${model.user.userRoles}" var="role" varStatus="counter" >
	-<em>&nbsp;${role}</em><br/>
</c:forEach>
