package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizweb.Quiz;
import quizweb.question.Question;

/**
 * Servlet implementation class StartQuiz
 */
@WebServlet("/StartQuiz")
public class StartQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartQuiz() {
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
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		HttpSession session = request.getSession();
		
		int position = 1;
		Quiz quiz = Quiz.getQuizByQuizID(quizID);
		ArrayList<Question> questions = quiz.getQuestions();
		ArrayList<Integer> indices = new ArrayList<Integer>();	
		ArrayList<Object> userAnswers = new ArrayList<Object>(questions.size());

		for (int i = 0; i < questions.size(); i++)
			indices.add(new Integer(i));
		if (quiz.isRandom) {
			Random random = new Random();
			for (int i = 0; i < questions.size(); i++) {
				int j = (int) Math.floor(random.nextDouble() * (questions.size() - i));
				Integer temp = indices.get(i);
				indices.set(i, indices.get(j));
				indices.set(j, temp);
			}
		}
		
		session.setAttribute("quiz", quiz);
		session.setAttribute("position", position);
		session.setAttribute("questions", questions);
		session.setAttribute("indices", indices);
		session.setAttribute("userAnswers", userAnswers);
		session.setAttribute("start_time", new Date().getTime());
		
		RequestDispatcher dispatch = request.getRequestDispatcher("take_quiz.jsp");
		dispatch.forward(request, response);
		
		// TODO Auto-generated method stub
	}

}
