<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>

<portlet:defineObjects />
<%
	Locale locale = renderRequest.getLocale();
	ResourceBundle resource = portletConfig.getResourceBundle(locale);
%>
<!-- detail and list view for guests - start-->
<c:set var="userstatus" value='<%= resource.getString("user-status") %>'/>
<c:set var="loggedinas" value='<%= resource.getString("logged-in-as") %>'/>
<c:set var="roleslinkedtoaccount" value='<%= resource.getString("roles-linked-to-account") %>'/>

<c:set var="clicktorequestproduceraccount" value='<%= resource.getString("click-to-request-producer-account") %>'/>
<c:set var="producer" value='<%= resource.getString("producer") %>'/>
<c:set var="requestproduceraccount" value='<%= resource.getString("request-producer-account") %>'/>
<c:set var="requestforrole" value='<%= resource.getString("request-for-role") %>'/>
<c:set var="forfaculty" value='<%= resource.getString("for-faculty") %>'/>
<c:set var="processed" value='<%= resource.getString("processed") %>'/>

