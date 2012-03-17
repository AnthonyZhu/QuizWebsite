package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String submit = request.getParameter("saveForm");
		String addAnotherQuestion = request.getParameter("addNewQuestion");
		
		HttpSession session = request.getSession();
		Integer position = (Integer) session.getAttribute("questionPosition");
		Quiz newQuiz = (Quiz) session.getAttribute("newQuiz");
		int quizID = newQuiz.quizID;
		String questionType = (String) session.getAttribute("QuestionType");
		boolean flag = true;
		
		if(questionType.equals("Question-Response")){
			String scoreStr = request.getParameter("score");
			String content = request.getParameter("Field1");
			String answer = request.getParameter("Field2");
			
			if(scoreStr.equals("") || content.equals("") || answer.equals("")){
				flag = false;
				RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz/new_quiz_question.jsp");
				dispatch.forward(request, response);
				
			}else{
				double score = Double.parseDouble(scoreStr);
				ArrayList<String> answerList = new ArrayList<String>();
				String[] answerSplit = answer.split(",");
			    for(int i=0;i<answerSplit.length;i++){
			    	answerList.add(answerSplit[i]);
			    }
				ResponseQuestion newQuestion = new ResponseQuestion(quizID,position,content,answerList,score);
				newQuestion.addQustionToDB();
			}
			
		}else if(questionType.equals("Fill in the Blank")){
			String scoreStr = request.getParameter("score");
			String content = request.getParameter("Field1");
			String answer = request.getParameter("Field2");
			
			if(scoreStr.equals("") || content.equals("") || answer.equals("")){
				flag = false;
				RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz/new_quiz_question_fillInBlank.jsp");
				dispatch.forward(request, response);
				
			}else{
				double score = Double.parseDouble(scoreStr);
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
				
				FillInBlankQuestion newQuestion = new FillInBlankQuestion(quizID,position,contentList,answerList,score);
				newQuestion.addQustionToDB();
			}
			
		}else if(questionType.equals("Multiple Choice")){
			String scoreStr = request.getParameter("score");
			String content = request.getParameter("Field1");
			String choice = request.getParameter("Field2");
			String answer = request.getParameter("Field3");
			
			if(scoreStr.equals("") || content.equals("") || choice.equals("") || answer.equals("")){
				flag = false;
				RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz/new_quiz_question_multiChoice.jsp");
				dispatch.forward(request, response);
				
			}else{
				double score = Double.parseDouble(scoreStr);
				ArrayList<String> contentList = new ArrayList<String>();
				contentList.add(content);
				String[] contentSplit = choice.split(";");
			    for(int i=0;i<contentSplit.length;i++){
			    	contentList.add(contentSplit[i]);
			    }
				
				MultipleChoiceQuestion newQuestion = new MultipleChoiceQuestion(quizID,position,contentList,answer,score);
				newQuestion.addQustionToDB();
			}
			
		}else if(questionType.equals("Picture-Response Questions")){
			String scoreStr = request.getParameter("score");
			String content = request.getParameter("Field1");
			String answer = request.getParameter("Field2");
			String URL = request.getParameter("Field3");
			
			if(scoreStr.equals("") || content.equals("") || URL.equals("") || answer.equals("")){
				flag = false;
				RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz/new_quiz_question_picture.jsp");
				dispatch.forward(request, response);
				
			}else{
				double score = Double.parseDouble(scoreStr);
				ArrayList<String> answerList = new ArrayList<String>();
				String[] answerSplit = answer.split(",");
			    for(int i=0;i<answerSplit.length;i++){
			    	answerList.add(answerSplit[i]);
			    }
			    
			    PictureQuestion newQuestion = new PictureQuestion(quizID,position,content,answerList,score,URL);
			    newQuestion.addQustionToDB();
			}
		    
		}else if(questionType.equals("Multiple-Answer Questions")){
			String scoreStr = request.getParameter("score");
			String content = request.getParameter("Field1");
			String answer = request.getParameter("Field2");
			String answerNumStr = request.getParameter("Field3");
			String order = request.getParameter("Field4");
			
			if(scoreStr.equals("") || content.equals("") || order.equals("") || answer.equals("") || answerNumStr.equals("")){
				flag = false;
				RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz/new_quiz_question_multiAnswer.jsp");
				dispatch.forward(request, response);
				
			}else{
				double score = Double.parseDouble(scoreStr);
				int answerNum = Integer.parseInt(answerNumStr);
				boolean isOrder;
				if(order.equals("yes")){
					isOrder = true;
				}else{
					isOrder = false;
				}
				ArrayList<String> answerList = new ArrayList<String>();
				String[] answerSplit = answer.split(",");
			    for(int i=0;i<answerSplit.length;i++){
			    	answerList.add(answerSplit[i]);
			    }
			    
				MultiAnswerQuestion newQuestion = new MultiAnswerQuestion(quizID,position,content,answerList,score,answerNum,isOrder);
				newQuestion.addQustionToDB();
			}
			
		}else if(questionType.equals("Multiple Choice with Multiple Answers")){
			String scoreStr = request.getParameter("score");
			String content = request.getParameter("Field1");
			String choice = request.getParameter("Field2");
			String answer = request.getParameter("Field3");
			
			if(scoreStr.equals("") || content.equals("") || choice.equals("") || answer.equals("")){
				flag = false;
				RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz/new_quiz_question_multiChoiceAndAnswer.jsp");
				dispatch.forward(request, response);
				
			}else{
				double score = Double.parseDouble(scoreStr);
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
			    
			    MultiChoiceMultiAnswerQuestion newQuestion = new MultiChoiceMultiAnswerQuestion(quizID,position,contentList,answerList,score);
			    newQuestion.addQustionToDB();
			}
		    
		}else if(questionType.equals("Matching")){
			String scoreStr = request.getParameter("score");
			String content = request.getParameter("Field1");
			String choice = request.getParameter("Field2");
			String answer = request.getParameter("Field3");
			
			if(scoreStr.equals("") || content.equals("") || choice.equals("") || answer.equals("")){
				flag = false;
				RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz/new_quiz_question_matching.jsp");
				dispatch.forward(request, response);
				
			}else{
				double score = Double.parseDouble(scoreStr);
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
			    
			    MatchingQuestion newQuestion = new MatchingQuestion(quizID,position,contentList,answerList,score);
			    newQuestion.addQustionToDB();
			}
		}
		
		if(flag == true){
			if(submit != null){
				if(submit.equals("Save and Finish")){
					RequestDispatcher dispatch = request.getRequestDispatcher("quiz_summary.jsp?id=" + quizID);
					dispatch.forward(request, response);
			    }
			}
			if(addAnotherQuestion != null){
				if(addAnotherQuestion.equals("Add Another Question")){
					position += 1;
					session.setAttribute("questionPosition", position);
					RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz/chooseQuestionType.jsp");
					dispatch.forward(request, response);
		    	}
			}
		}
	}
}
