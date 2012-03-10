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
	
//	global.createAnnouncement = function() {
//		$('#adminFrame').attr('src', 'admin_createAnnouncement.jsp');
//		$('#createButton').hide();
//		$('#submitButton').show();
//	};
	
	global.createAnnouncement = function() {
		var title = $("#title").val();
		var announcement = $("#new_announcement").val();
		var dataString = 'title='+ title + '&announcement=' + announcement;

		if(title=='') {
			$('#error1').fadeOut(200).show();
		}
		else if(announcement=='') {
			$('#error2').fadeOut(200).show();
		}
		else {
			$.ajax({
				type: "POST",
				url: "newAnnouncement.jsp",
				data: dataString,
				success: function(){
					$('#searchArea').hide();
					$('#createAncmt').show();
					$('#adminFrame').attr('src', 'admin_announcement.jsp');
				}
			});
//			$('#adminFrame').attr('src', 'admin_announcement.jsp');
//			$('#submitButton').hide();
//			$('#createButton').show();
		}
		return false;
	};
	
//	global.deleteAnnouncement = function(id) {
//		$('#row' + id).remove();
//	};
	
})(window);

