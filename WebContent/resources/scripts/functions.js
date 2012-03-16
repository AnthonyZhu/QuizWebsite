(function(global) {
	var contentShow = true;
	global.toggleContent = function() {
		if(contentShow == true) {
			$('#contentFrame').fadeOut();
			contentShow = false;
		}
		else {
			$('#contentFrame').fadeIn();
			contentShow = true;
		}
	};
	
	global.showNewsFeed = function() {
		$('#contentFrame').attr('src', 'http://localhost:8080/QuizWebsite/homepage/home_feed.jsp');
	};
	
	
	global.showNotes = function()  {
		$('#contentFrame').attr('src', 'http://localhost:8080/QuizWebsite/homepage/home_notes.jsp');
	};
	
	global.showQuizTaken = function() {
		$('#contentFrame').attr('src', 'http://localhost:8080/QuizWebsite/homepage/home_quizTaken.jsp');
	};
	
	global.showQuizCreated = function() {
		$('#contentFrame').attr('src', 'http://localhost:8080/QuizWebsite/homepage/home_quizCreated.jsp');
	};
	
	global.showFriendRequests = function() {
		$('#contentFrame').attr('src', 'http://localhost:8080/QuizWebsite/homepage/home_friendRequests.jsp');
	};
	
	global.showChallenges = function() {
		$('#contentFrame').attr('src', 'http://localhost:8080/QuizWebsite/homepage/home_challenges.jsp');
	};
	
	global.showMoreAnnouncements = function() {
		$('#contentFrame').attr('src', 'http://localhost:8080/QuizWebsite/homepage/home_moreAnnouncements.jsp');
	};
	
	global.showMorePopularQuizzes = function() {
		$('#contentFrame').attr('src', 'http://localhost:8080/QuizWebsite/homepage/home_morePopularQuizzes.jsp');
	};
	
	global.showMoreRecentlyQuizzes = function() {
		$('#contentFrame').attr('src', 'http://localhost:8080/QuizWebsite/homepage/home_moreRecentlyQuizzes.jsp');
	};
	
	/**
	global.sendNotePopup = function() {
		session = request.getSession();
		User user = (User)session.getAttribute("user");
		var popup = window.open('','newWindow','width=400,height=200');
		popup.focus();
		popup.document.open();
		popup.document.writeln("<div id='note_form'>");
		popup.document.writeln("<form name='note' action=''>");
		popup.document.writeln("<textarea id='note'></textarea>");
		popup.document.writeln("<input type='hidden' id='sender' value='"+ user.userID +"'/>");
		popup.document.writeln("<input type='hidden' id='receiver' value='"+ senderID +"'/>");
		popup.document.writeln("<input type='submit' id='note_btn' value='send note'/>");
		popup.document.writeln("</form>");
		popup.document.writeln("<span class='error' style='display:none'>empty note!</span>");
		popup.document.writeln("</div>");
	};
	**/

	
})(window);


$(".highlight").focusin(function() {
	if(!$(this).hasClass("highlight_background")) {
		$(".highlight").removeClass("highlight_background");
		$(this).addClass("highlight_background");
	}
});

$(".highlight").focusout(function() {
	$(this).removeClass("highlight_background");
});

