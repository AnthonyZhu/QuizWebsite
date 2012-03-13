$(function() {
	$("#request_btn").click(function() {
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
	
$(function() {
	$("#confirm_btn").click(function() {
		var senderName = $("#senderName").val();
		var senderID = $("#senderID").val();
		var receiverID = $("#receiverID").val();
		var time = $("#time").val();
		var dataString = 'senderID=' + senderID + '&receiverID=' + receiverID;

		$.ajax({
			type: "POST",
			url: "newFriendRelation.jsp",
			data: dataString,
			success: function(){
				$('#confirm_btn').hide();
				$('#confirm_msg').html("<p>You are now friend with "+ senderName +"</p>");
				$('#reject_btn').hide();
			}
		});
		return false;
	});
});
	
$(function() {
	$("#reject_btn").click(function() {
		var senderID = $("#senderID").val();
		var receiverID = $("#receiverID").val();
		var time = $("#time").val();
		var dataString = 'senderID=' + senderID + '&receiverID=' + receiverID;

		$.ajax({
			type: "POST",
			url: "rejectFriendRequest.jsp",
			data: dataString,
			success: function(){
				$('#'+senderID).hide();			
			}
		});
		return false;
	});
});
