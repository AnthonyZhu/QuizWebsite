package quizweb;

import java.util.*;



import quizweb.achievement.Achievement;
import quizweb.message.Message;
import quizweb.record.QuizCreatedRecord;
import quizweb.record.QuizTakenRecord;

public class User {
	public int userID;
	public String username;
	public String password; // TODO JACK
	public String homepageURL;
	
	public boolean isAdmin;	// whether is admin
	public boolean isBlocked; // whether blocked by admin
	public boolean isDead; // whether removed by admin
	
	// Achievement related stats	
	public int practiceNumber;
	public int highScoreNumber;

	// Lazy instantiation
	private ArrayList<User> friendList;
	private ArrayList<QuizTakenRecord> quizHistory;
	private ArrayList<QuizCreatedRecord> createdQuiz;
	private ArrayList<Achievement> achievements;
	private ArrayList<Message> messages;
	
	static private int maxUserID = 0;
	static public int totalUsers = 0;
	
	public ArrayList<User> getFriendList(){
		if (friendList == null) {
			//TODO Get user friend list from database
			return null;
		} else {
			return friendList;
		}
	}
	
	public ArrayList<QuizTakenRecord> getQuizHistory() {
		if (quizHistory == null) {
			// TODO Get user quiz history from database
			return null;
		} else {
			return quizHistory;
		}
	}
	
	public ArrayList<Quiz> getCreatedQuiz() {
		if (createdQuiz == null) {
			// TODO Get created quiz list from database
			return null;
		} else {
			return createdQuiz;
		}
	}
	
	public ArrayList<Achievement> getAchievements() {
		if (achievements == null) {
			// TODO Get user achievement list from database
			return null;
		} else {
			return achievements;
		}
	}
	
	public ArrayList<Message> getUserMessages() {
		if (messages == null) {
			// TODO Get user message list from database
			return null;
		} else {
			return messages;
		}
	}
	
	/**
	 * Assign next user ID for the user instance
	 * @return user ID assigned
	 */
	private synchronized int getNextUserID() {
		return maxUserID++;
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
