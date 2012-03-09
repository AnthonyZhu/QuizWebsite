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
		session.setAttribute("newQuiz", newQuiz);
		int quizID = newQuiz.quizID;
		double score = Double.parseDouble(request.getParameter("score"));
		String content = request.getParameter("Field1");
		String answer = request.getParameter("Field2");
		
		String questionType = request.getParameter("newQuestionType");
		if(questionType.equals("Question-Response")){
			ArrayList<String> answerList = new ArrayList();
			String[] answerSplit = answer.split(",");
		    for(int i=0;i<answerSplit.length;i++){
		    	answerList.add(answerSplit[i]);
		    	System.out.println(answerList.get(i));
		    }
			ResponseQuestion newQuestion = new ResponseQuestion(quizID,posistion,content,answerList,score);
		}else if(questionType.equals("Fill in the Blank")){
			FillInBlankQuestion newQuestion = new FillInBlankQuestion(quizID,posistion,content,answer,score);
		}else if(questionType.equals("Multiple Choice")){
			MultipleChoiceQuestion newQuestion = new MultipleChoiceQuestion(quizID,posistion,content,answer,score);
		}
		
		if(submit != null){
			if(submit.equals("Save and Finish")){
				session.setAttribute("quizID", quizID);
				RequestDispatcher dispatch = request.getRequestDispatcher("quiz_summary.jsp");
				dispatch.forward(request, response);
		    }
		}
		if(addAnotherQuestion != null){
			if(addAnotherQuestion.equals("Add Another Question")){
				posistion += 1;
				session.setAttribute("questionPosistion", posistion);
				RequestDispatcher dispatch = request.getRequestDispatcher("new_quiz_question.jsp");
				dispatch.forward(request, response);
	    	}
		}
	
		// TODO Auto-generated method stub
	}

}
