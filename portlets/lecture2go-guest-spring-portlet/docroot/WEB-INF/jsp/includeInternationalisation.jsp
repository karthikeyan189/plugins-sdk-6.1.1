<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>

<portlet:defineObjects />
<%
	Locale locale = renderRequest.getLocale();
	ResourceBundle resource = portletConfig.getResourceBundle(locale);
%>
<!-- detail and list view for guests - start-->
<c:set var="selectfaculty" value='<%= resource.getString("select-faculty") %>'/>
<c:set var="selectarea" value='<%= resource.getString("select-area") %>'/>
<c:set var="lecture" value='<%= resource.getString("lecture") %>'/>
<c:set var="lectureseries" value='<%= resource.getString("lecture-series") %>'/>
<c:set var="selectlectureseries" value='<%= resource.getString("select-lecture-series") %>'/>
<c:set var="information" value='<%= resource.getString("information") %>'/>
<c:set var="download" value='<%= resource.getString("download") %>'/>
<c:set var="abo" value='<%= resource.getString("abo") %>'/>
<c:set var="forward" value='<%= resource.getString("forward") %>'/>
<c:set var="citation2go" value='<%= resource.getString("citation2go") %>'/>
<c:set var="chapter" value='<%= resource.getString("chapter") %>'/>
<c:set var="chapters" value='<%= resource.getString("chapters") %>'/>
<c:set var="duration" value='<%= resource.getString("duration") %>'/>
<c:set var="min" value='<%= resource.getString("min") %>'/>
<c:set var="mp4" value='<%= resource.getString("mp4") %>'/>
<c:set var="mb" value='<%= resource.getString("mb") %>'/>
<c:set var="lecture2golicense" value='<%= resource.getString("lecture2go-license") %>'/>
<c:set var="uhhl2g" value='<%= resource.getString("uhhl2g") %>'/>
<c:set var="byncsalicense" value='<%= resource.getString("byncsa-license") %>'/>
<c:set var="byncndlicense" value='<%= resource.getString("byncnd-license") %>'/>
<c:set var="byncsa" value='<%= resource.getString("byncsa") %>'/>
<c:set var="byncnd" value='<%= resource.getString("byncnd") %>'/>
<c:set var="views" value='<%= resource.getString("views") %>'/>
<c:set var="videodownload" value='<%= resource.getString("video-download") %>'/>
<c:set var="audiodownload" value='<%= resource.getString("audio-download") %>'/>
<c:set var="pdfdownload" value='<%= resource.getString("pdf-download") %>'/>
<c:set var="m4adownload" value='<%= resource.getString("m4a-download") %>'/>
<c:set var="m4vdownload" value='<%= resource.getString("m4v-download") %>'/>
<c:set var="mp4download" value='<%= resource.getString("mp4-download") %>'/>
<c:set var="downloadtext" value='<%= resource.getString("download-text") %>'/>
<c:set var="mp4abo" value='<%= resource.getString("mp4-abo") %>'/>
<c:set var="videofeed" value='<%= resource.getString("video-feed") %>'/>
<c:set var="audiofeed" value='<%= resource.getString("audio-feed") %>'/>
<c:set var="mobilefeed" value='<%= resource.getString("mobile-feed") %>'/>
<c:set var="mobilefeedinfo" value='<%= resource.getString("mobile-feed-info") %>'/>
<c:set var="m4aabo" value='<%= resource.getString("m4a-abo") %>'/>
<c:set var="m4afeed" value='<%= resource.getString("m4a-feed") %>'/>
<c:set var="m4afeedinfo" value='<%= resource.getString("m4a-feed-info") %>'/>
<c:set var="feedinfo" value='<%= resource.getString("feed-info") %>'/>
<c:set var="moreinfotext" value='<%= resource.getString("more-info-text") %>'/>
<c:set var="moreinfotexttitle" value='<%= resource.getString("more-info-text-title") %>'/>
<c:set var="videofeedinfo" value='<%= resource.getString("video-feed-info") %>'/>
<c:set var="audiofeedtitle" value='<%= resource.getString("audio-feed-title") %>'/>
<c:set var="audiofeedinfo" value='<%= resource.getString("audio-feed-info") %>'/>
<c:set var="mobilefeedtitle" value='<%= resource.getString("mobile-feed-title") %>'/>
<c:set var="tweet" value='<%= resource.getString("tweet") %>'/>
<c:set var="citationstart" value='<%= resource.getString("citation-start") %>'/>
<c:set var="citationend" value='<%= resource.getString("citation-end") %>'/>
<c:set var="citation2gotitle" value='<%= resource.getString("citation2go-title") %>'/>
<c:set var="urladdress" value='<%= resource.getString("url-address") %>'/>
<c:set var="urladdresstitle" value='<%= resource.getString("url-address-title") %>'/>
<c:set var="embed" value='<%= resource.getString("embed") %>'/>
<c:set var="embedtitle" value='<%= resource.getString("embed-title") %>'/>
<c:set var="embedcommsy" value='<%= resource.getString("embed-commsy") %>'/>
<c:set var="embedcommsytitle" value='<%= resource.getString("embed-commsy-title") %>'/>
<c:set var="embedconditions" value='<%= resource.getString("embed-conditions") %>'/>
<c:set var="commenton" value='<%= resource.getString("comment-on") %>'/>
<c:set var="commentoff" value='<%= resource.getString("comment-off") %>'/>
<c:set var="commentvideo" value='<%= resource.getString("comment-video") %>'/>
<c:set var="playchapter" value='<%= resource.getString("play-chapter") %>'/>

<c:set var="gotofirstpage" value='<%= resource.getString("go-to-first-page") %>'/>
<c:set var="gotopage" value='<%= resource.getString("go-to-page") %>'/>
<c:set var="gotolastpage" value='<%= resource.getString("go-to-last-page") %>'/>

<c:set var="chooseconference" value='<%= resource.getString("choose-conference") %>'/>
<c:set var="choosesession" value='<%= resource.getString("choose-session") %>'/>

<!-- detail and list view for guests - end-->

<!-- search list view for guest - start -->
<c:set var="yoursearchreturned" value='<%= resource.getString("your-search-returned") %>'/>
<c:set var="results" value='<%= resource.getString("results") %>'/>
<c:set var="gotolectureseries" value='<%= resource.getString("go-to-lecture-series") %>'/>
<c:set var="eswurden" value='<%= resource.getString("es-wurden") %>'/>
<c:set var="otherresults" value='<%= resource.getString("other-results") %>'/>
<c:set var="morethan" value='<%= resource.getString("more-than") %>'/>
<c:set var="resultsfound" value='<%= resource.getString("results-found") %>'/>
<c:set var="moreinfo" value='<%= resource.getString("more-info") %>'/>
<!-- search list view for guest -end -->

<!-- guest video list - start -->
<c:set var="neww" value='<%= resource.getString("new") %>'/>
<c:set var="popular" value='<%= resource.getString("popular") %>'/>
<c:set var="random" value='<%= resource.getString("random") %>'/>
<!-- guest video list - end -->

<c:set var="random" value='<%= resource.getString("random") %>'/>
<c:set var="lectureonlyasastreamavailable" value='<%= resource.getString("lecture-only-as-a-stream-available") %>'/>
<c:set var="time" value='<%= resource.getString("time") %>'/>
<c:set var="playthischapter" value='<%= resource.getString("play-this-chapter") %>'/>

<c:set var="citation2gonotavailable" value='<%= resource.getString("citation2go-not-available") %>'/>
<c:set var="citation2gosetstartpoint" value='<%= resource.getString("citation2go-set-start-point") %>'/>
<c:set var="citation2gosetendpoint" value='<%= resource.getString("citation2go-set-end-point") %>'/>
<c:set var="click" value='<%= resource.getString("click") %>'/>

<c:set var="gotofullvideo" value='<%= resource.getString("go-to-full-video") %>'/>
<c:set var="links" value='<%= resource.getString("links") %>'/>
<c:set var="url" value='<%= resource.getString("url") %>'/>
<c:set var="socialmedia" value='<%= resource.getString("social-media") %>'/>
<c:set var="licence" value='<%= resource.getString("licence") %>'/>

<c:set var="noopenaccessvideosyet" value='<%= resource.getString("no-open-access-videos-yet") %>'/>
<c:set var="randomlistnotavailibleyet" value='<%= resource.getString("random-list-not-availible-yet") %>'/>
<c:set var="videolistevaluationnotpossible" value='<%= resource.getString("video-list-evaluation-not-possible") %>'/>

<c:set var="subject" value='<%= resource.getString("subject") %>'/>
<c:set var="contact" value='<%= resource.getString("contact") %>'/>
<c:set var="text" value='<%= resource.getString("text") %>'/>
<c:set var="send" value='<%= resource.getString("send") %>'/>

<c:set var="pleaseloginforcontact" value='<%= resource.getString("please-login-for-contact") %>'/>
<c:set var="pleasefilloutfields" value='<%= resource.getString("please-fill-out-fields") %>'/>
<c:set var="thankyouforthemessage" value='<%= resource.getString("thank-you-for-the-message") %>'/>
<c:set var="feedbackforvideo" value='<%= resource.getString("feedback-for-video") %>'/>


<c:set var="mp4video" value='<%= resource.getString("mp4-video") %>'/>
<c:set var="mp3audio" value='<%= resource.getString("mp3-audio") %>'/>
<c:set var="m4vvideo" value='<%= resource.getString("m4v-video") %>'/>
<c:set var="m4aaudio" value='<%= resource.getString("m4a-audio") %>'/>
<c:set var="pdftext" value='<%= resource.getString("pdf-text") %>'/>



