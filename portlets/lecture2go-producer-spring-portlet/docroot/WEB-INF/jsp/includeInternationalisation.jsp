<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>

<portlet:defineObjects />
<%
	Locale locale = renderRequest.getLocale();
	ResourceBundle resource = portletConfig.getResourceBundle(locale);
%>

<c:set var="gotooverview" value='<%= resource.getString("go-to-overview") %>'/>
<c:set var="pleasecorrectinput" value='<%= resource.getString("please-correct-input") %>'/>
<c:set var="lectureid" value='<%= resource.getString("lecture-id") %>'/>
<c:set var="enterlectureid" value='<%= resource.getString("enter-lecture-id") %>'/>
<c:set var="lecturename" value='<%= resource.getString("lecture-name") %>'/>
<c:set var="enterlecturename" value='<%= resource.getString("enter-lecture-name") %>'/>
<c:set var="lecturetype" value='<%= resource.getString("lecture-type") %>'/>
<c:set var="selectlecturetype" value='<%= resource.getString("select-lecture-type") %>'/>
<c:set var="facilities" value='<%= resource.getString("facilities") %>'/>
<c:set var="add" value='<%= resource.getString("add") %>'/>
<c:set var="pleaceaddfacility" value='<%= resource.getString("pleace-add-facility") %>'/>
<c:set var="producer" value='<%= resource.getString("producer") %>'/>
<c:set var="addoneproducer" value='<%= resource.getString("add-one-producer") %>'/>
<c:set var="shortdescription" value='<%= resource.getString("short-description") %>'/>
<c:set var="semester" value='<%= resource.getString("semester") %>'/>
<c:set var="addnewsemester" value='<%= resource.getString("add-new-semester") %>'/>
<c:set var="languages" value='<%= resource.getString("languages") %>'/>
<c:set var="addonelanguage" value='<%= resource.getString("add-one-language") %>'/>
<c:set var="lecturers" value='<%= resource.getString("lecturers") %>'/>
<c:set var="exampleprofname" value='<%= resource.getString("example-prof-name") %>'/>
<c:set var="password" value='<%= resource.getString("password") %>'/>
<c:set var="description" value='<%= resource.getString("description") %>'/>
<c:set var="aftersaving" value='<%= resource.getString("after-saving") %>'/>
<c:set var="save" value='<%= resource.getString("save") %>'/>
<c:set var="editthisevent" value='<%= resource.getString("edit-this-event") %>'/>
<c:set var="chapter" value='<%= resource.getString("chapter") %>'/>
<c:set var="comment" value='<%= resource.getString("comment") %>'/>
<c:set var="shorttitle" value='<%= resource.getString("short-title") %>'/>
<c:set var="chapterbeginning" value='<%= resource.getString("chapter-beginning") %>'/>
<c:set var="chapterend" value='<%= resource.getString("chapter-end") %>'/>
<c:set var="text" value='<%= resource.getString("text") %>'/>
<c:set var="delete" value='<%= resource.getString("delete") %>'/>
<c:set var="reallydeletethischapter" value='<%= resource.getString("really-delete-this-chapter") %>'/>
<c:set var="commenton" value='<%= resource.getString("comment-on") %>'/>
<c:set var="commentoff" value='<%= resource.getString("comment-off") %>'/>
<c:set var="playthischapter" value='<%= resource.getString("play-this-chapter") %>'/>
<c:set var="gobacktovideo" value='<%= resource.getString("go-back-to-video") %>'/>
<c:set var="allowsegmentation" value='<%= resource.getString("allow-segmentation") %>'/>

<c:set var="title" value='<%= resource.getString("title") %>'/>
<c:set var="tags" value='<%= resource.getString("tags") %>'/>
<c:set var="author" value='<%= resource.getString("author") %>'/>
<c:set var="videoproducer" value='<%= resource.getString("videoproducer") %>'/>
<c:set var="publisher" value='<%= resource.getString("publisher") %>'/>
<c:set var="description" value='<%= resource.getString("description") %>'/>
<c:set var="l2golicence" value='<%= resource.getString("l2go-licence") %>'/>
<c:set var="byncsa" value='<%= resource.getString("by-nc-sa") %>'/>

<c:set var="selectlectureseries" value='<%= resource.getString("select-lecture-series") %>'/>
<c:set var="addnewvideodataset" value='<%= resource.getString("add-new-videodata-set") %>'/>
<c:set var="novideouploaded" value='<%= resource.getString("no-video-uploaded") %>'/>
<c:set var="videomp4" value='<%= resource.getString("video-mp4") %>'/>
<c:set var="audiomp3" value='<%= resource.getString("audio-mp3") %>'/>
<c:set var="audiom4a" value='<%= resource.getString("audio-m4a") %>'/>
<c:set var="iPodm4v" value='<%= resource.getString("iPod-m4v") %>'/>
<c:set var="textpdf" value='<%= resource.getString("text-pdf") %>'/>
<c:set var="recordingon" value='<%= resource.getString("recording-on") %>'/>
<c:set var="uploaded-on" value='<%= resource.getString("uploaded-on") %>'/>
<c:set var="uploadandmanagefiles" value='<%= resource.getString("upload-and-manage-files") %>'/>
<c:set var="lockpublicaccess" value='<%= resource.getString("lock-public-access") %>'/>
<c:set var="reallypickupopenaccess" value='<%= resource.getString("really-pick-up-open-access") %>'/>
<c:set var="publicityvisible" value='<%= resource.getString("publicity-visible") %>'/>
<c:set var="reallyavailibletothepublic" value='<%= resource.getString("really-availible-to-the-public") %>'/>
<c:set var="lockdownload" value='<%= resource.getString("lock-download") %>'/>
<c:set var="permitdownload" value='<%= resource.getString("permit-download") %>'/>
<c:set var="addchapterorcomment" value='<%= resource.getString("add-chapter-or-comment") %>'/>
<c:set var="editcommentorchapter" value='<%= resource.getString("edit-comment-or-chapter") %>'/>
<c:set var="editinfo" value='<%= resource.getString("edit-info") %>'/>
<c:set var="reallydeletealldata" value='<%= resource.getString("really-delete-all-data") %>'/>
<c:set var="deletevideo" value='<%= resource.getString("delete-video") %>'/>
<c:set var="language" value='<%= resource.getString("language") %>'/>
<c:set var="videodatasetsaved" value='<%= resource.getString("video-data-set-saved") %>'/>
<c:set var="views" value='<%= resource.getString("views") %>'/>

<c:set var="pleasechoosevideofile" value='<%= resource.getString("please-choose-video-file") %>'/>
<c:set var="pleasenotefilesintax" value='<%= resource.getString("please-note-file-sintax") %>'/>
<c:set var="impermissiblefilename" value='<%= resource.getString("impermissible-file-name") %>'/>
<c:set var="upload" value='<%= resource.getString("upload") %>'/>

<c:set var="uploadcantakeawhile" value='<%= resource.getString("upload-can-take-a-while") %>'/>
<c:set var="pleaseuploadmp4ormp3" value='<%= resource.getString("please-upload-mp4-or-mp3") %>'/>
<c:set var="otherallowedformats" value='<%= resource.getString("other-allowed-formats") %>'/>
<c:set var="reallydeletealldata" value='<%= resource.getString("really-delete-all-data") %>'/>
<c:set var="removemp3" value='<%= resource.getString("remove-mp3") %>'/>
<c:set var="removemp4" value='<%= resource.getString("remove-mp4") %>'/>
<c:set var="removeipod" value='<%= resource.getString("remove-ipod") %>'/>
<c:set var="removepdf" value='<%= resource.getString("remove-pdf") %>'/>
<c:set var="embedconditions" value='<%= resource.getString("embed-conditions") %>'/>
<c:set var="urladdress" value='<%= resource.getString("url-address") %>'/>
<c:set var="urladdresstitle" value='<%= resource.getString("url-address-title") %>'/>
<c:set var="embed" value='<%= resource.getString("embed") %>'/>
<c:set var="embedtitle" value='<%= resource.getString("embed-title") %>'/>
<c:set var="embedcommsy" value='<%= resource.getString("embed-commsy") %>'/>
<c:set var="embedcommsytitle" value='<%= resource.getString("embed-commsy-title") %>'/>
<c:set var="removedata" value='<%= resource.getString("remove-data") %>'/>

<c:set var="reallyavailibletothepublick" value='<%= resource.getString("really-availible-to-the-public") %>'/>
<c:set var="reallydeletealldata" value='<%= resource.getString("really-delete-all-data") %>'/>

<c:set var="mp4" value='<%= resource.getString("mp4") %>'/>
<c:set var="duration" value='<%= resource.getString("duration") %>'/>
<c:set var="min" value='<%= resource.getString("min") %>'/>
<c:set var="views" value='<%= resource.getString("views") %>'/>

<c:set var="gotofirstpage" value='<%= resource.getString("go-to-first-page") %>'/>
<c:set var="gotopage" value='<%= resource.getString("go-to-page") %>'/>
<c:set var="page" value='<%= resource.getString("page") %>'/>
<c:set var="gotolastpage" value='<%= resource.getString("go-to-last-page") %>'/>

<c:set var="videodatasetinfo" value='<%= resource.getString("video-data-set-info") %>'/>
<c:set var="reallychangepassword" value='<%= resource.getString("really-change-password") %>'/>
<c:set var="setpassword" value='<%= resource.getString("set-password") %>'/>

<c:set var="youcanapplylectures" value='<%= resource.getString("you-can-apply-lectures") %>'/>
<c:set var="newlectureserierequest" value='<%= resource.getString("new-lecture-serie-request") %>'/>

<c:set var="coordinatorfor" value='<%= resource.getString("coordinator-for") %>'/>
<c:set var="producerfor" value='<%= resource.getString("producer-for") %>'/>

<c:set var="invalidvalueday" value='<%= resource.getString("invalid-value-day") %>'/>
<c:set var="invalidvaluemonth" value='<%= resource.getString("invalid-value-month") %>'/>
<c:set var="invalidvalueyear" value='<%= resource.getString("invalid-value-year") %>'/>
<c:set var="invalidvaluehour" value='<%= resource.getString("invalid-value-hour") %>'/>
<c:set var="invalidvalueminute" value='<%= resource.getString("invalid-value-minute") %>'/>	

<c:set var="citation2go" value='<%= resource.getString("citation2go") %>'/>	
<c:set var="activated" value='<%= resource.getString("activated") %>'/>	
<c:set var="disabled" value='<%= resource.getString("disabled") %>'/>	

<c:set var="pleaceapplyforanewlectureseries" value='<%= resource.getString("pleace-apply-for-a-new-lectureseries") %>'/>	

<c:set var="languagesforselect" value='<%= resource.getString("languages-for-select") %>'/>
<c:set var="eventtypesforselect" value='<%= resource.getString("event-types-for-select") %>'/>