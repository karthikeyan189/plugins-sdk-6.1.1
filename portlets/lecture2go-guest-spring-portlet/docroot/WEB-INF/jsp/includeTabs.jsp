<!-- Tabs -->
<div id="tabs">
	<ul>
		<c:if test="${not empty model.metadata.description}">
			<li><a href="#tabs-1">${information}</a></li>		
		</c:if>

		<li><a href="#tabs-2">${forward}</a></li>

		<c:if test="${model.downloadAllawed==true}">	
			<li><a href="#tabs-3">${download}</a></li>
		</c:if>

		<li><a href="#tabs-4">${abo}</a></li>
		
	</ul>
	
	<c:if test="${not empty model.metadata.description}">
		<div id="tabs-1"><%@ include file="includeVideoDetail.html" %></div>
	</c:if>
	
	<div id="tabs-2"><%@ include file="includeEmbed.html" %></div>
	
	<c:if test="${model.downloadAllawed==true}">	
		<div id="tabs-3"><%@ include file="includeDownload.html" %></div>
	</c:if>
	
	<div id="tabs-4"><%@ include file="includeFeeds.html" %></div>
	
</div>