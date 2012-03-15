package servlet;

import java.io.IOException;
import java.util.*;

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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int position = (Integer) session.getAttribute("position");
		ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
		ArrayList<Object> userAnswers = (ArrayList<Object>) session.getAttribute("userAnswers");
		ArrayList<Integer> indices = (ArrayList<Integer>) session.getAttribute("indices");		
		// The quiz is over
		if (position > questions.size()) {
			RequestDispatcher dispatch = request.getRequestDispatcher("quiz_over.jsp");
			dispatch.forward(request, response);
			return;
		} 
		
		// Record last question
		if (position > 1) {
			int lastIndex = indices.get(position-2);
			String answerEntry = request.getParameter("user_answer");
			int type = (Integer) session.getAttribute("question_type");
			if (type == Question.TYPE_RESPONSE || type == Question.TYPE_BLANK || type == Question.TYPE_PICTURE) {
				userAnswers.set(lastIndex, answerEntry);
			} else if (type == Question.TYPE_MULTIANSWER) {
				userAnswers.set(lastIndex, Question.getParsedDisplayStrings(answerEntry));
			} else if (type == Question.TYPE_CHOICE) {
				ArrayList<String> queries = (ArrayList<String>) questions.get(lastIndex).question;
				int choice = Integer.parseInt(answerEntry);
				userAnswers.set(lastIndex, queries.get(choice));
			} else if (type == Question.TYPE_MATCHING) {
				ArrayList<String> queries = (ArrayList<String>) questions.get(lastIndex).question;
				ArrayList<String> choices = Question.getParsedDisplayStrings(answerEntry);
				ArrayList<String> answerList = new ArrayList<String>(); 
				for (int i = 0; i < choices.size(); i++) {
					answerList.add(queries.get(queries.size()/2 + Integer.parseInt(choices.get(i))));
				}
				userAnswers.set(lastIndex, answerList);
			} else if (type == Question.TYPE_MULTICHOICEMULTIANSWER) {
				ArrayList<String> queries = (ArrayList<String>) questions.get(lastIndex).question;
				ArrayList<String> choices = Question.getParsedDisplayStrings(answerEntry);
				ArrayList<String> answerList = new ArrayList<String>(); 
				for (int i = 0; i < choices.size(); i++) {
					answerList.add(queries.get(Integer.parseInt(choices.get(i))));
				}	
				userAnswers.set(lastIndex, answerList);
			}
		}
		
		int index = indices.get(position-1).intValue();
		Question question = questions.get(index);
		Object userAnswer = userAnswers.get(index);
		
		session.setAttribute("index", index);
		session.setAttribute("question", question);
		session.setAttribute("userAnswer", userAnswer);
		session.setAttribute("userAnswers", userAnswers);
		
		position += 1;
		session.setAttribute("position", position);
		RequestDispatcher dispatch = request.getRequestDispatcher("take_quiz.jsp");
		dispatch.forward(request, response);
	}

}
