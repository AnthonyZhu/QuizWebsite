package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizweb.Quiz;
import quizweb.User;

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
		String quizCorrection = request.getParameter("Field6");
		boolean opFeedback;
		String quizPractice = request.getParameter("Field7");
		boolean opPractice;
		String quizTag = request.getParameter("Field8");
		String quizDescription = request.getParameter("Field9");
		String userName = request.getParameter("userName");
		int userID = User.getUserByUsername(userName).userID;

		String quizURL = "";
		
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
		
		Quiz newQuiz = new Quiz(quizName,quizURL,quizDescription,quizCategory,userID,isRandom,opFeedback,opPractice);
		HttpSession session = request.getSession();
		session.setAttribute("newQuiz", newQuiz);
		session.setAttribute("questionPosistion", 1);
		RequestDispatcher dispatch = request.getRequestDispatcher("new_quiz_question.jsp");
		dispatch.forward(request, response);
		
		
		// TODO Auto-generated method stub
	}

}
