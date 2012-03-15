package quizweb.accountmanagement;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizweb.User;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAccountServlet() {
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
		AccountManager am = (AccountManager)getServletContext().getAttribute("account manager");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(User.getUserByUsername(username) != null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("login/nameInUse.jsp");
			dispatch.forward(request, response);
		}
		else {
			am.createNewAccount(username, password, 1);
			HttpSession session = request.getSession();
			User user = User.getUserByUsername(username);
			session.setAttribute("user", user);
			RequestDispatcher dispatch = request.getRequestDispatcher("homepage/homepage.jsp");
			dispatch.forward(request, response);
		}
	}
}
