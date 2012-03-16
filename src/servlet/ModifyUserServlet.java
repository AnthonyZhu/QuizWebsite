package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizweb.User;

/**
 * Servlet implementation class RemoveUserServlet
 */
@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserServlet() {
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
		int adminUserID = Integer.parseInt(request.getParameter("admin_userid"));
		int targetUserID = Integer.parseInt(request.getParameter("removed_userid"));
		User adminUser = User.getUserByUserID(adminUserID);
		User targetUser = User.getUserByUserID(targetUserID);
		if (adminUser.permission == User.IS_ADMIN && adminUser.userID != targetUserID) {
			if (submitButton.equals("Remove User")) {
				User.removeUser(targetUser);
			} else if (submitButton.equals("Unremove User")) {
				User.unremoveUser(targetUser);
			} else if (submitButton.equals("Block User")) {
				User.blockUser(targetUser);
			} else if (submitButton.equals("Unblock User")) {
				User.unblockUser(targetUser);
			} else if (submitButton.equals("Promote User")) {
				User.promoteUser(targetUser);
			} else if (submitButton.equals("Unpromote User")) {
				User.unpromoteUser(targetUser);
			}
		}
        RequestDispatcher dispatch = request.getRequestDispatcher("admin/admin_user.jsp?keyword=" + targetUser.username);
		dispatch.forward(request, response);		
	}

}
