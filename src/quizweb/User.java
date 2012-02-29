package quizweb;

import java.util.*;

public class User {
	public int userID;
	public String username;
	public String password; // TODO JACK
	public String homepageURL;
	public boolean isAdmin;
	
	public ArrayList<Integer> friendList;
	public ArrayList<Integer> quizHistory;
	public ArrayList<Integer> createdQuiz;
	
	// Achievement related stats
	public ArrayList<Achievement> achievements;
	public int quizTakenNumber;
	public int createdQuizNumber;
	public int practiceNumber;
	public int highScoreNumber;
	
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
