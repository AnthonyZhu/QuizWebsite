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
		boolean isPractice = (Boolean) session.getAttribute("ispractice");
		ArrayList<Integer> correctCount = null;
		int totalCorrectCount = 0;
		if (isPractice) {
			correctCount = (ArrayList<Integer>) session.getAttribute("correct_count");
			totalCorrectCount = (Integer) session.getAttribute("total_correct_count");
		}		
		
		// Record last question
		for (int idx = 1; idx <= questions.size(); idx++) {
			if (!request.getParameterMap().containsKey("user_answer" + idx))
				continue;
			int lastIndex = indices.get(idx-1);
			String answerEntry = request.getParameter("user_answer" + idx);
			int type = (Integer) session.getAttribute("question_type" + idx);
			if (type == Question.TYPE_RESPONSE || type == Question.TYPE_BLANK || type == Question.TYPE_PICTURE) {
				userAnswers.set(lastIndex, answerEntry);
			} else if (type == Question.TYPE_MULTIANSWER) {
				userAnswers.set(lastIndex, Question.getParsedDisplayStrings(answerEntry));
			} else if (type == Question.TYPE_CHOICE) {
				try {
					ArrayList<String> queries = (ArrayList<String>) questions.get(lastIndex).question;
					int choice = Integer.parseInt(answerEntry);
					if (choice >= 1 && choice <= queries.size()-1)
						userAnswers.set(lastIndex, queries.get(choice));
				} catch (NumberFormatException e) {
					// pass
				}
			} else if (type == Question.TYPE_MATCHING) {
				ArrayList<String> queries = (ArrayList<String>) questions.get(lastIndex).question;
				ArrayList<String> choices = Question.getParsedDisplayStrings(answerEntry.trim());
				ArrayList<String> answerList = new ArrayList<String>(); 
				for (int i = 0; i < choices.size(); i++) {
					try {
						int choice = Integer.parseInt(choices.get(i));
						if (choice >= 1 && choice <= queries.size()/2)
							answerList.add(queries.get(queries.size()/2 + choice));
					} catch (NumberFormatException e) {
						break;
					}
					if (answerList.size() >= queries.size() / 2)
						break;
				}
				userAnswers.set(lastIndex, answerList);
			} else if (type == Question.TYPE_MULTICHOICEMULTIANSWER) {
				ArrayList<String> queries = (ArrayList<String>) questions.get(lastIndex).question;
				ArrayList<String> choices = Question.getParsedDisplayStrings(answerEntry);
				ArrayList<String> answerList = new ArrayList<String>(); 
				for (int i = 0; i < choices.size(); i++) {
					try {
						int choice = Integer.parseInt(choices.get(i));
						if (choice >= 1 && choice <= queries.size()-1)
							answerList.add(queries.get(choice));
					} catch (NumberFormatException e) {
						break;
					}
				}	
				userAnswers.set(lastIndex, answerList);
			}
			if (isPractice) {
				if (questions.get(lastIndex).getScore(userAnswers.get(lastIndex)) == questions.get(lastIndex).score)  {
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
