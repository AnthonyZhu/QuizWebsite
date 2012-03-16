package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizweb.message.NoteMessage;

/**
 * Servlet implementation class ReplyNoteServlet
 */
@WebServlet("/ReplyNoteServlet")
public class ReplyNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyNoteServlet() {
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
		String note = request.getParameter("replyNote");
		NoteMessage replyNote = new NoteMessage(sendID,receiverID,note);
		replyNote.addMessageToDB();
		RequestDispatcher dispatch = request.getRequestDispatcher("homepage/home_notes.jsp");
		dispatch.forward(request, response);
		
		// TODO Auto-generated method stub
	}

}
