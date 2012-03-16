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

import quizweb.question.*;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		boolean isPractice = (Boolean) session.getAttribute("ispractice");
		ArrayList<Integer> correctCount = null;
		int totalCorrectCount = 0;
		if (isPractice) {
			correctCount = (ArrayList<Integer>) session.getAttribute("correct_count");
			totalCorrectCount = (Integer) session.getAttribute("total_correct_count");
		}		
		
		// Record user answers
		for (int idx = 1; idx <= questions.size(); idx++) {
			int index = indices.get(idx-1);
			Question question = questions.get(index);
			if (question instanceof ResponseQuestion) {
				if (!request.getParameterMap().containsKey("user_answer" + idx)) continue;
				userAnswers.set(index, request.getParameter("user_answer" + idx));
			} else if (question instanceof FillInBlankQuestion) {
				if (!request.getParameterMap().containsKey("user_answer" + idx)) continue;
				userAnswers.set(index, request.getParameter("user_answer" + idx));				
			} else if (question instanceof MultipleChoiceQuestion) {
				if (!request.getParameterMap().containsKey("user_answer" + idx)) continue;
				userAnswers.set(index, request.getParameter("user_answer" + idx));
			} else if (question instanceof PictureQuestion) {
				if (!request.getParameterMap().containsKey("user_answer" + idx)) continue;
				userAnswers.set(index, request.getParameter("user_answer" + idx));			
			} else if (question instanceof MatchingQuestion) {
				if (!request.getParameterMap().containsKey("user_answer" + idx + "_1")) continue;
				ArrayList<String> questionList = (ArrayList<String>) question.question;
				ArrayList<String> userAnswer = new ArrayList<String>();
				for (int i = 1; i <= questionList.size()/2; i++) {
					String userAns = request.getParameter("user_answer" + idx + "_" + i);
					userAnswer.add(userAns);					
				}
				userAnswers.set(index, userAnswer);
			} else if (question instanceof MultiAnswerQuestion) {
				if (!request.getParameterMap().containsKey("user_answer" + idx + "_1")) continue;
				ArrayList<String> userAnswer = new ArrayList<String>();
				for (int i = 1; i <= ((MultiAnswerQuestion)question).answerNumber; i++) {
					String userAns = request.getParameter("user_answer" + idx + "_" + i);
					userAnswer.add(userAns);					
				}
				userAnswers.set(index, userAnswer);
			} else if (question instanceof MultiChoiceMultiAnswerQuestion) {
				if (!request.getParameterMap().containsKey("user_answer" + idx + "_0")) continue;
				ArrayList<String> questionList = (ArrayList<String>) question.question;
				ArrayList<String> userAnswer = new ArrayList<String>();
				for (int i = 1; i < questionList.size(); i++) {
					String userAns = request.getParameter("user_answer" + idx + "_" + i);
					if (userAns != null)
						userAnswer.add(userAns);					
				}
				userAnswers.set(index, userAnswer);
			}
			
			if (isPractice) {
				if (question.getScore(userAnswers.get(index)) == questions.get(index).score)  {
					totalCorrectCount++;
					correctCount.set(idx-1, new Integer(correctCount.get(idx-1).intValue()+1));
				}
			}
		}
		
		if (isPractice) {
			session.setAttribute("correct_count", correctCount);
			session.setAttribute("total_correct_count", totalCorrectCount);
		}
				
		// The quiz is over
		if (position >= questions.size() && !isPractice || totalCorrectCount >= 3 * questions.size()) {
			session.setAttribute("is_quiz_result_stored", false);
			RequestDispatcher dispatch = request.getRequestDispatcher("quiz_over.jsp");
			dispatch.forward(request, response);			
			return;
		}
		if (isPractice && position >= questions.size()) 
			position = 0;
		
//		int index = indices.get(position).intValue();
//		Question question = questions.get(index);
//		Object userAnswer = userAnswers.get(index);
//		
//		session.setAttribute("index", index);
//		session.setAttribute("question", question);
//		session.setAttribute("userAnswer", userAnswer);
		session.setAttribute("userAnswers", userAnswers);
		
		position += 1;
		session.setAttribute("position", position);
		RequestDispatcher dispatch = request.getRequestDispatcher("take_quiz.jsp");
		dispatch.forward(request, response);
	}

}
