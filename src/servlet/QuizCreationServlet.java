package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizweb.Quiz;
import quizweb.question.FillInBlankQuestion;
import quizweb.question.MatchingQuestion;
import quizweb.question.MultiAnswerQuestion;
import quizweb.question.MultiChoiceMultiAnswerQuestion;
import quizweb.question.MultipleChoiceQuestion;
import quizweb.question.PictureQuestion;
import quizweb.question.ResponseQuestion;

/**
 * Servlet implementation class QuizCreationServlet
 */
@WebServlet("/QuizCreationServlet")
public class QuizCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizCreationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String submit = request.getParameter("saveForm");
		String addAnotherQuestion = request.getParameter("addNewQuestion");
		
		HttpSession session = request.getSession();
		Integer posistion = (Integer) session.getAttribute("questionPosistion");
		Quiz newQuiz = (Quiz) session.getAttribute("newQuiz");
		int quizID = newQuiz.quizID;
		String questionType = (String) session.getAttribute("QuestionType");
		
		if(questionType.equals("Question-Response")){
			double score = Double.parseDouble(request.getParameter("score"));
			String content = request.getParameter("Field1");
			String answer = request.getParameter("Field2");
			
			ArrayList<String> answerList = new ArrayList<String>();
			String[] answerSplit = answer.split(",");
		    for(int i=0;i<answerSplit.length;i++){
		    	answerList.add(answerSplit[i]);
		    }
		    
			ResponseQuestion newQuestion = new ResponseQuestion(quizID,posistion,content,answerList,score);
			
		}else if(questionType.equals("Fill in the Blank")){
			double score = Double.parseDouble(request.getParameter("score"));
			String content = request.getParameter("Field1");
			String answer = request.getParameter("Field2");
			
			ArrayList<String> contentList = new ArrayList<String>();
			String[] contentSplit = content.split("_");
		    for(int i=0;i<contentSplit.length;i++){
		    	contentList.add(contentSplit[i]);
		    }
			ArrayList<String> answerList = new ArrayList<String>();
			String[] answerSplit = answer.split(",");
		    for(int i=0;i<answerSplit.length;i++){
		    	answerList.add(answerSplit[i]);
		    }
			
			FillInBlankQuestion newQuestion = new FillInBlankQuestion(quizID,posistion,contentList,answerList,score);
			
		}else if(questionType.equals("Multiple Choice")){
			double score = Double.parseDouble(request.getParameter("score"));
			String content = request.getParameter("Field1");
			String choice = request.getParameter("Field2");
			String answer = request.getParameter("Field3");
			
			ArrayList<String> contentList = new ArrayList<String>();
			contentList.add(content);
			String[] contentSplit = choice.split(";");
		    for(int i=0;i<contentSplit.length;i++){
		    	contentList.add(contentSplit[i]);
		    }
			
			MultipleChoiceQuestion newQuestion = new MultipleChoiceQuestion(quizID,posistion,contentList,answer,score);
			
		}else if(questionType.equals("Picture-Response Questions")){
			double score = Double.parseDouble(request.getParameter("score"));
			String content = request.getParameter("Field1");
			String answer = request.getParameter("Field2");
			String URL = request.getParameter("Field3");
			
			ArrayList<String> answerList = new ArrayList<String>();
			String[] answerSplit = answer.split(",");
		    for(int i=0;i<answerSplit.length;i++){
		    	answerList.add(answerSplit[i]);
		    }
		    
		    PictureQuestion newQuestion = new PictureQuestion(quizID,posistion,content,answerList,score,URL);
			
		}else if(questionType.equals("Multiple-Answer Questions")){
			double score = Double.parseDouble(request.getParameter("score"));
			String content = request.getParameter("Field1");
			String answer = request.getParameter("Field2");
			
			ArrayList<String> answerList = new ArrayList<String>();
			String[] answerSplit = answer.split(",");
		    for(int i=0;i<answerSplit.length;i++){
		    	answerList.add(answerSplit[i]);
		    }
		    
			MultiAnswerQuestion newQuestion = new MultiAnswerQuestion(quizID,posistion,content,answerList,score);
			
		}else if(questionType.equals("Multiple Choice with Multiple Answers")){
			double score = Double.parseDouble(request.getParameter("score"));
			String content = request.getParameter("Field1");
			String choice = request.getParameter("Field2");
			String answer = request.getParameter("Field3");
			
			ArrayList<String> contentList = new ArrayList<String>();
			contentList.add(content);
			String[] contentSplit = choice.split(";");
		    for(int i=0;i<contentSplit.length;i++){
		    	contentList.add(contentSplit[i]);
		    }
			
		    ArrayList<String> answerList = new ArrayList<String>();
			String[] answerSplit = answer.split(",");
		    for(int i=0;i<answerSplit.length;i++){
		    	answerList.add(answerSplit[i]);
		    }
		    
		    MultiChoiceMultiAnswerQuestion newQuestion = new MultiChoiceMultiAnswerQuestion(quizID,posistion,contentList,answerList,score);
			
		}else if(questionType.equals("Matching")){
			double score = Double.parseDouble(request.getParameter("score"));
			String content = request.getParameter("Field1");
			String choice = request.getParameter("Field2");
			String answer = request.getParameter("Field3");
			
			ArrayList<String> contentList = new ArrayList<String>();
			contentList.add(content);
			String[] contentSplit = choice.split(";");
		    for(int i=0;i<contentSplit.length;i++){
		    	contentList.add(contentSplit[i]);
		    }
			
		    ArrayList<String> answerList = new ArrayList<String>();
			String[] answerSplit = answer.split(",");
		    for(int i=0;i<answerSplit.length;i++){
		    	answerList.add(answerSplit[i]);
		    }
		    
		    MatchingQuestion newQuestion = new MatchingQuestion(quizID,posistion,contentList,answerList,score);
			
		}
		
		if(submit != null){
			if(submit.equals("Save and Finish")){
				RequestDispatcher dispatch = request.getRequestDispatcher("quiz_summary.jsp");
				dispatch.forward(request, response);
		    }
		}
		if(addAnotherQuestion != null){
			if(addAnotherQuestion.equals("Add Another Question")){
				posistion += 1;
				session.setAttribute("questionPosistion", posistion);
				RequestDispatcher dispatch = request.getRequestDispatcher("chooseQuestionType.jsp");
				dispatch.forward(request, response);
	    	}
		}
	
	}

}
