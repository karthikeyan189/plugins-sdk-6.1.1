<!-- Tabs -->
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">${information}</a></li>
		<c:if test="${model.downloadAllawed==true}">	
			<li><a href="#tabs-2">${download}</a></li>
		</c:if>
	</ul>
	<div id="tabs-1"><%@ include file="includeVideoDetail.html" %></div>
	<c:if test="${model.downloadAllawed==true}">	
		<div id="tabs-2"><%@ include file="includeDownload.html" %></div>
	</c:if>
</div>
				