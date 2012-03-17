package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizweb.Quiz;
import quizweb.User;
import quizweb.record.QuizTakenRecord;

/**
 * Servlet implementation class ModifyQuizServlet
 */
@WebServlet("/ModifyQuizServlet")
public class ModifyQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyQuizServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String submitButton = (String) request.getParameter("submit_btn");
		int targetQuizID = Integer.parseInt(request.getParameter("target_quizid"));
		Quiz quiz = Quiz.getQuizByQuizID(targetQuizID);
		int sourceUserID = Integer.parseInt(request.getParameter("source_userid"));
		User user = User.getUserByUserID(sourceUserID);
		String keyword = request.getParameter("keyword");
		if (submitButton.equals("Delete Quiz")) {
			if (user.permission == User.IS_ADMIN || quiz.creator.userID == user.userID) {
				Quiz.deleteQuiz(quiz);
			}
		} else if (submitButton.equals("Edit Quiz")) {
			if (quiz.creator.userID == user.userID) {
				// Edit quiz
			}
		} else if (submitButton.equals("Clear Quiz History")) { 
			if (user.permission == User.IS_ADMIN) {
				QuizTakenRecord.deleteQuizTakenHistory(quiz);
			}
		}
        RequestDispatcher dispatch = request.getRequestDispatcher("admin/admin_quiz.jsp?keyword=" + keyword);
		dispatch.forward(request, response);
	}

}
