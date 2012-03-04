package quizweb;

import java.util.*;

import quizweb.achievement.*;
import quizweb.announcement.Announcement;
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
	
	public static int totalUsers = 0;
	
	// relationship status
	public final int IS_FRIEND = 1;
	public final int NOT_FRIEND = 0;
	public final int PENDING_FRIEND = 2;
	public final int REVERSE_PENDING = 3;
	
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
			quizHistory = QuizTakenRecord.getQuizHistoryByUserID(userID);
			return quizHistory;
		} else {
			return quizHistory;
		}
	}
	
	public ArrayList<QuizCreatedRecord> getCreatedQuiz() {
		if (createdQuiz == null) {
			createdQuiz = QuizCreatedRecord.getCreatedQuizByUserID(userID);
			return createdQuiz;
		} else {
			return createdQuiz;
		}
	}
	
	public ArrayList<AchievementRecord> getAchievements() {
		if (achievements == null) {
			achievements = AchievementRecord.getAchievementsByUserID(userID);
			return achievements;
		} else {
			return achievements;
		}
	}
	
	public ArrayList<Message> getMessages() {
		if (messages == null) {
			messages = Message.getMessagesByUserID(userID);
			return messages;
		} else {
			return messages;
		}
	}

	public User(String username, String password, String homepageURL, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.homepageURL = homepageURL;
		this.isAdmin = isAdmin;
		this.isBlocked = false;
		this.isDead = false;
		this.practiceNumber = 0;
		this.highScoreNumber = 0;
		this.friendList = new ArrayList<User>();
		this.quizHistory = new ArrayList<QuizTakenRecord>();
		this.createdQuiz = new ArrayList<QuizCreatedRecord>();
		this.achievements = new ArrayList<AchievementRecord>();
		this.messages = new ArrayList<Message>();
		// store the info into database
		// retrieve userID from database
		// update global user list
		// TODO
	}
	
	public User(String username, String password, String homepageURL, boolean isAdmin, boolean isBlocked,
			boolean isDead, int practiceNumber, int highScoreNumber) {
		this.username = username;
		this.password = password;
		this.homepageURL = homepageURL;
		this.isAdmin = isAdmin;
		this.isBlocked = isBlocked;
		this.isDead = isDead;
		this.practiceNumber = practiceNumber;
		this.highScoreNumber = highScoreNumber;
		this.friendList = null;
		this.quizHistory = null;
		this.createdQuiz = null;
		this.achievements = null;
		this.messages = null;	
	}
	
	/** 
	 * Find a particular user given the username
	 * @param username
	 * @return
	 */
	public static User getUserByUsername(String username) {
		// check if global user list has it
		// otherwise load from the database and update global user list
		// TODO
		return null;
	}
	
	public static void removeUser(User user) {
		// remove user record from the database
		// remove user record from the global user list
		// for the friend list, remove the user from each of his friend's friend list
		// TODO
	}
	
	public void addFriend(User user) {
		getFriendList().add(user);
		// change global user list
		// update database
		// TODO
	}
	
	public void removeFriend(User user) {
		getFriendList().remove(user);
		user.getFriendList().remove(this);
		// update global user list
		// update database
		// TODO
	}
	
	public int isFriend(User user) {
		// if the user is in the friend list, return is_friend
		ArrayList<User> friends = getFriendList();
		for (int i = 0; i < friends.size(); i++)
			if (friends.get(i).userID == user.userID) 
				return IS_FRIEND;
		// if the user has sent a friend request previously, return pending_friend
		// TODO
		// otherwise return not_friend
		return NOT_FRIEND;
	}
	
	public void addMessage(Message message) {
		// add message to database
		// update global user list instance
		// TODO
	}	
	
	// Achievement
	
	public void updateAchievements() {
		for (int i = 0; i < Achievement.allAchievements.size(); i++) {
			Achievement achi = Achievement.allAchievements.get(i);
			boolean isDone = false;
			for (int j = 0; j < getAchievements().size(); j++) {
				if (getAchievements().get(j).achievement.equals(achi)) {
					isDone = true;
					break;
				}				
			}
			if (!isDone && achi.isAccomplished(this))
				addAchievement(achi);
		}
	}
	
	public void addAchievement(Achievement achievement) {
		getAchievements().add(new AchievementRecord(this, achievement));		
	}

	// Admin 
	
	public void addAnnouncement(String title, String content) {
		if (!isAdmin) return;
		Announcement.addAnnouncement(new Announcement(title, content));
	}
	
	/**
	 * Promote a particular user to administration privilege 
	 * @param user
	 */
	public void promoteUser(User user) {
		if (isAdmin) {
			if (!user.isAdmin) {
				// update database with new informations
				// TODO
			}
			user.isAdmin = true;
		}
	}
	
	/**
	 * Check if two users are the same user
	 * @param other
	 * @return
	 */
	public boolean equals(User other) {
		return userID == other.userID;
	}
}
