(function(global) {
	
	global.showAdminUser = function() {
		$('#adminFrame').attr('src', 'admin_user.jsp');
	};
	
	global.showAdminQuiz = function()  {
		$('#adminFrame').attr('src', 'admin_quiz.jsp');
	};
	
	global.showQuizTaken = function() {
		$('#adminFrame').attr('src', 'admin_announcement.jsp');
	};
	
})(window);