package quizweb;

import java.util.*;

public class User {
	public int userID;
	public String username;
	public String password; // TODO JACK
	public String homepageURL;
	
	public boolean isAdmin;
	public boolean isBlocked; // whether blocked by admin
	public boolean isDead; // whether removed by admin
	
	// Achievement related stats	
	public int practiceNumber;
	public int highScoreNumber;

	// Lazy instantiation
	public ArrayList<User> friendList;
	public ArrayList<QuizRecord> quizHistory;
	public ArrayList<Quiz> createdQuiz;
	public ArrayList<Achievement> achievements;
	public ArrayList<Message> messages;
	
	static private int maxUserID = 0;
	static public int totalUsers = 0;
	
	/**
	 * Assign next user ID for the user instance
	 * @return
	 */
	static private synchronized int getNextUserID() {
		// TODO
		return 0;
	}
	
	
	/** 
	 * Find a particular user given the username
	 * @param username
	 * @return
	 */
	static public User findUser(String username) {
		// TODO
		return null;
	}
	
	/**
	 * Promote a particular user to administration privilege 
	 * @param user
	 */
	public void promoteUser(User user) {
		// TODO
	}
}
