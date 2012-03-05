(function(global) {
	global.showQuestionFrame=function(){
		var menu=document.getElementById("newQuestionType");
		var chosenOption=menu.selectedIndex;
		switch(chosenOption){
			case 1: {$('#QuestionFrame').attr('src', "newQuestion_QuestionResponse.jsp");break;}
			case 2: {$('#QuestionFrame').attr('src', "newQuestion_FillInTheBlank.jsp");break;}
			case 3: {$('#QuestionFrame').attr('src', "newQuestion_MultipleChoice.jsp");break;}
			case 4: {$('#QuestionFrame').attr('src', "newQuestion_PictureResponse.jsp");break;}
			default:{break;};
		}

	};
})(window);