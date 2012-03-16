package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizweb.message.FriendRequestMessage;

/**
 * Servlet implementation class addFriendServlet
 */
@WebServlet("/addFriendServlet")
public class addFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addFriendServlet() {
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
		int sendID = Integer.parseInt(request.getParameter("sender"));
		int receiverID = Integer.parseInt(request.getParameter("receiver"));
		FriendRequestMessage friendAdding = new FriendRequestMessage(sendID, receiverID);
        friendAdding.addMessageToDB();
        RequestDispatcher dispatch = request.getRequestDispatcher("userpage.jsp?id=" + receiverID);
		dispatch.forward(request, response);
		
		// TODO Auto-generated method stub
	}

}
