package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizweb.User;
import quizweb.message.FriendRequestMessage;

/**
 * Servlet implementation class ConfirmFriendServlet
 */
@WebServlet("/ConfirmFriendServlet")
public class ConfirmFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmFriendServlet() {
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
		int sendID = Integer.parseInt(request.getParameter("senderID"));
		int receiverID = Integer.parseInt(request.getParameter("receiverID"));
		String confirm = request.getParameter("confirm_btn");
		String reject = request.getParameter("reject_btn");
		
		if (confirm != null){
			if (confirm.equals("confirm")){
				User.getUserByUserID(sendID).addFriend(User.getUserByUserID(receiverID));
				RequestDispatcher dispatch = request.getRequestDispatcher("homepage/homepage.jsp");
				dispatch.forward(request, response);
			}
		}
		if (reject != null){
			if(reject.equals("reject")){
				FriendRequestMessage.rejectFriendRequest(sendID, receiverID);
				RequestDispatcher dispatch = request.getRequestDispatcher("homepage/homepage.jsp");
				dispatch.forward(request, response);
			}
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("homepage/homepage.jsp");
		dispatch.forward(request, response);
		
		
		// TODO Auto-generated method stub
	}

}
