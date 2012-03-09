(function(global) {
	
	$('#createAncmt').hide();
	
	global.showAdminUser = function() {
		$('#searchArea').show();
		$('#createAncmt').hide();
		$('#adminFrame').attr('src', 'admin_user.jsp');
	};
	
	global.showAdminQuiz = function()  {
		$('#searchArea').hide();
		$('#createAncmt').hide();
		$('#adminFrame').attr('src', 'admin_quiz.jsp');
	};
	
	global.showAdminAnnouncement = function() {
		$('#searchArea').hide();
		$('#createAncmt').show();
		$('#adminFrame').attr('src', 'admin_announcement.jsp');
		$('#submitButton').hide();
		$('#createButton').show();
	};
	
	global.search = function() {
		$('#adminFrame').attr('src', 'admin_user.jsp?keyword=' + $('#inputBox').val());
	};
	
	global.createAnnouncement = function() {
		$('#adminFrame').attr('src', 'admin_createAnnouncement.jsp');
		$('#createButton').hide();
		$('#submitButton').show();
	};
	
	global.submitAnnouncement = function() {
		$('#adminFrame').attr('src', 'admin_announcement.jsp');
		$('#submitButton').hide();
		$('#createButton').show();
	};
	
	global.deleteAnnouncement = function(id) {
		$('#row' + id).remove();
	};
	
})(window);


