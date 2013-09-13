<c:if test="${model.liferayUser!=null}">
	<div id="contform">
		<ul>
			<li>
				<label>${subject}</label><em style="color:#F17B0D;">*</em><br/>
				<input id="subject" name="subject" type="text"/>
			</li>
			<li>
				<label>${text}</label><em style="color:#F17B0D;">*</em><br/>
				<textarea id="text" name="text" rows="3" cols="32"></textarea>
			</li>
		</ul>
	</div>
	
	<div id="contformbutt">
		<button type="submit" id="contsub" onclick="contact();" name="" value="">
			<span class="">${send}</span>
		</button>
	</div>
</c:if>

<c:if test="${model.liferayUser==null}">
	<div id="contpllogin">
		${pleaseloginforcontact}
	</div>
</c:if>