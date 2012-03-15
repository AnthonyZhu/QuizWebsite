package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizweb.question.Question;

/**
 * Servlet implementation class TakingQuizServlet
 */
@WebServlet("/TakingQuizServlet")
public class TakingQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakingQuizServlet() {
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
		HttpSession session = request.getSession();
		int quizID = (Integer) session.getAttribute("quizID");
		int position = (Integer) session.getAttribute("question_position");
		int size = Question.getQuestionsByQuizID(quizID).size();
		
		if(position == size){
			RequestDispatcher dispatch = request.getRequestDispatcher("quiz_over.jsp");
			dispatch.forward(request, response);
		}else if(position < size){
			position +=1;
			session.setAttribute("question_position",position);
			RequestDispatcher dispatch = request.getRequestDispatcher("take_quiz.jsp");
			dispatch.forward(request, response);
		}else{
			System.out.println("error");
		}
		
		
		
		
		
		// TODO Auto-generated method stub
	}

}
