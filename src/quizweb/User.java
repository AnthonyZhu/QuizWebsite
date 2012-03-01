package quizweb;

import java.util.*;

public class User {
	private int userID;
	private String username;
	private String password; // TODO JACK
	private String homepageURL;
	
	private boolean isAdmin;
	private boolean isBlocked; // whether blocked by admin
	private boolean isDead; // whether removed by admin
	
	// Achievement related stats	
	private int practiceNumber;
	private int highScoreNumber;

	// Lazy instantiation
	private ArrayList<User> friendList;
	private ArrayList<QuizRecord> quizHistory;
	private ArrayList<Quiz> createdQuiz;
	private ArrayList<Achievement> achievements;
	private ArrayList<Message> messages;
	
	static private int maxUserID = 0;
	static public int totalUsers = 0;
	
	public User(String username){
		this.username = username;
	}
	
	public String getHomePageURL(){
		//TODO
		return homepageURL;
	}
	
	public boolean isUserAdmin(){
		// TODO
		return isAdmin;
	}
	
	public boolean isUserBlocked(){
		//TODO
		return isBlocked;
	}
	
	public boolean isUserRemoved(){
		//TODO
		return isDead;
	}
	
	public int getPracticeNumber(){
		//TODO
	    return practiceNumber;
	}
	
	public int getHighScore(){
		//TODO
		return highScoreNumber;
	}
	
	public ArrayList<User> getUserFriendList(){
		//TODO
		return friendList;
	}
	
	public ArrayList<QuizRecord> getUserQuizHistory(){
		//TODO
		return quizHistory;
	}
	
	public ArrayList<Quiz> getUserCreatedQuiz(){
		//TODO
		return createdQuiz;
	}
	
	public ArrayList<Achievement> getUserAchievement(){
		//TODO
		return achievements;
	}
	
	public ArrayList<Message> getUserMessage(){
		//TODO
		return messages;
	}
	
	/**
	 * Assign next user ID for the user instance
	 * @return
	 */
	private synchronized int getNextUserID() {
		// TODO
		userID = 0;
		return userID;
	}
	
	/** 
	 * Find a particular user given the username
	 * @param username
	 * @return
	 */
	public User findUser(String username) {
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
