package quizweb.message;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendChallengeServlet
 */
@WebServlet("/SendChallengeServlet")
public class SendChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendChallengeServlet() {
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
		String uid1 = request.getParameter("uid1");
		String uid2 = request.getParameter("uid2");
		String qid = request.getParameter("qid");
		String bestScore = request.getParameter("bestScore");
		ChallengeMessage cm = new ChallengeMessage(Integer.parseInt(uid1), Integer.parseInt(uid2), Integer.parseInt(qid), Double.parseDouble(bestScore));
		cm.addMessageToDB();
		RequestDispatcher dispatch = request.getRequestDispatcher("message.jsp");
		dispatch.forward(request, response);
	}

}
