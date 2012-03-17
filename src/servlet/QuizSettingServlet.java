package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizweb.*;
import quizweb.record.*;

/**
 * Servlet implementation class QuizSettingServlet
 */
@WebServlet("/QuizSettingServlet")
public class QuizSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizSettingServlet() {
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
		String quizName = request.getParameter("Field1");
		String quizCategory = request.getParameter("Field2");
		String quizAuto = request.getParameter("Field3");
		String quizOrder = request.getParameter("Field4");
		boolean isRandom;
		String quizPaging = request.getParameter("Field5");
		boolean isOnePage;
		String quizCorrection = request.getParameter("Field6");
		boolean opFeedback;
		String quizPractice = request.getParameter("Field7");
		boolean opPractice;
		String quizTag = request.getParameter("Field8");
		String quizDescription = request.getParameter("Field9");
		
		HttpSession session = request.getSession();
		User homeUser = (User) session.getAttribute("user");
		int userID = homeUser.userID;
		String quizURL = "none URL";
		session.setAttribute("quizTag", quizTag);
		
		if(quizName.equals("") || quizCategory.equals("") || quizDescription.equals("")){
			RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz/new_quiz_settings.jsp");
			dispatch.forward(request, response);
			
		}else{
			if(quizOrder.equals("Yes")){
				isRandom = true;
			}else{
				isRandom = false;
			}
			
			if(quizCorrection.equals("Yes")){
				opFeedback = true;
			}else{
				opFeedback = false;
			}
			
			if(quizPractice.equals("Yes")){
				opPractice = true;
			}else{
				opPractice = false;
			}
			
			if(quizPaging.equals("Yes")){
				isOnePage = true;
			}else{
				isOnePage = false;
			}
			
			Quiz newQuiz = new Quiz(quizName,quizURL,quizDescription,quizCategory,userID,isRandom,isOnePage,opFeedback,opPractice);
			newQuiz.addQuizToDB();
			QuizCreatedRecord record = new QuizCreatedRecord(newQuiz, homeUser);
			record.addRecordToDB();
			session.setAttribute("newQuiz", newQuiz);
			session.setAttribute("questionPosition", 1);
			RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz/chooseQuestionType.jsp");
			dispatch.forward(request, response);
		}
	}
}
