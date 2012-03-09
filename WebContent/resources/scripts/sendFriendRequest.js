$(function() {
	$("#submit_btn").click(function() {
		var sender = $("#sender").val();
		var receiver = $("#receiver").val();
		var dataString = 'sender=' + sender + '&receiver=' + receiver;

		$.ajax({
			type: "POST",
			url: "newFriendRequest.jsp",
			data: dataString,
			success: function(){
				$('#submit_btn').html("<input type='submit' id='submit_btn' value='request sent'/>");
			}
		});
		return false;
	});
});