(function(global) {
	
	$('#createAncmt').hide();
	
	global.showAdminUser = function() {
		$('#searchArea').show();
		$('#createAncmt').hide();
		$('#adminFrame').attr('src', 'admin_user.jsp');
	};
	
	global.showAdminQuiz = function()  {
		$('#searchArea').show();
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
		if ($('#adminFrame').attr('src').indexOf('admin_user.jsp') >= 0)
			$('#adminFrame').attr('src', 'admin_user.jsp?keyword=' + $('#inputBox').val());
		else if ($('#adminFrame').attr('src').indexOf('admin_quiz.jsp') >= 0)
			$('#adminFrame').attr('src', 'admin_quiz.jsp?keyword=' + $('#inputBox').val());
		else 
			$('#adminFrame').attr('src', 'admin_user.jsp?keyword=' + $('#inputBox').val());
	};
})(window);

$(function() {
	$("#createButton").click(function() {
		var title = $("#title").val();
		var announcement = $("#new_announcement").val();
		var dataString = 'title='+ title + '&announcement=' + announcement;

		if(title=='') {
			$('#error1').fadeOut(200).show();
			$('#error2').fadeOut(200).hide();
		}
		else if(announcement=='') {
			$('#error1').fadeOut(200).hide();
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
		}
		return false;
	});
});
