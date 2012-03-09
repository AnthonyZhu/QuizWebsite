$(function() {
	$("#submit_btn").click(function() {
		var sender = $("#sender").val();
		var receiver = $("#receiver").val();
		var dataString = 'sender=' + sender + '&receiver=' + receiver;

		if(note=='')
		{
			$('.error').fadeOut(200).show();
		}
		else
		{
			$.ajax({
				type: "POST",
				url: "newChallenge.jsp",
				data: dataString,
				success: function(){
					$('#submit_btn').html("<input type='submit' id='submit_btn' value='challenge sent'/>");
				}
			});
		}
		return false;
	});
});