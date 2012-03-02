(function(global) {
	var contentShow = true;
	global.toggleContent = function() {
		if(contentShow == true) {
			$('#content').fadeOut();
			contentShow = false;
		}
		else {
			$('#content').fadeIn();
			contentShow = true;
		}
	};
	
	global.showNewsFeed = function() {
		$('#contentFrame').attr('src', 'home_feed.jsp');
	};
	
	
	global.showNotes = function()  {
		$('#contentFrame').attr('src', 'home_notes.jsp');
	};
	
	global.showQuizTaken = function() {
		$('#contentFrame').attr('src', 'home_quizTaken.jsp');
	};
	
	global.showQuizCreated = function() {
		$('#contentFrame').attr('src', 'home_quizCreated.jsp');
	};
	
	global.showFriendRequests = function() {
		$('#contentFrame').attr('src', 'home_friendRequests.jsp');
	};
	
	global.showChallenges = function() {
		$('#contentFrame').attr('src', 'home_challenges.jsp');
	};

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
