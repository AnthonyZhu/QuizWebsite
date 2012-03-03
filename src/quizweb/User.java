package quizweb;

import java.util.*;

import quizweb.achievement.*;
import quizweb.message.*;
import quizweb.record.*;

public class User {
	public int userID;
	public String username;
	public String password; // TODO JACK it should be encrypt instead of clear text
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
	private ArrayList<AchievementRecord> achievements;
	private ArrayList<Message> messages;
	
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
			// TODO Get user quiz taken history from database
			return null;
		} else {
			return quizHistory;
		}
	}
	
	public ArrayList<QuizCreatedRecord> getCreatedQuiz() {
		if (createdQuiz == null) {
			// TODO Get created quiz record list from database
			return null;
		} else {
			return createdQuiz;
		}
	}
	
	public ArrayList<AchievementRecord> getAchievements() {
		if (achievements == null) {
			// TODO Get user achievement record list from database
			return null;
		} else {
			return achievements;
		}
	}
	
	public ArrayList<Message> getMessages() {
		if (messages == null) {
			// TODO Get user message list from database
			return null;
		} else {
			return messages;
		}
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
