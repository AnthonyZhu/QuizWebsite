package quizweb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import quizweb.achievement.*;
import quizweb.announcement.Announcement;
import quizweb.database.DBConnection;
import quizweb.message.*;
import quizweb.record.*;

public class User {
	public int userID;
	public String username;
	public String password; 
	public String homepageURL;
	
	public int permission;	// whether is admin
	public boolean isBlocked; // whether blocked by admin
	public boolean isDead; // whether removed by admin
	
	// Achievement related stats	
	public int practiceNumber;
	public int highScoreNumber;

	// Lazy instantiation
//	private ArrayList<User> friendList;
//	private ArrayList<QuizTakenRecord> quizHistory;
//	private ArrayList<QuizCreatedRecord> createdQuiz;
//	private ArrayList<AchievementRecord> achievements;
	
//	public static int totalUsers = 0;
	
	// permission type for permission
	public final int IS_NORMAL = 0;
	public final int IS_ADMIN = 1;	
	public final int IS_TEMP = 2;
	
	// relationship status
	public final int IS_FRIEND = 1;
	public final int NOT_FRIEND = 0;
	public final int PENDING_FRIEND = 2;
	public final int REVERSE_PENDING = 3;
	
	// database table names
	public static final String DBTable = "users";
	public static final String FriendDBTable = "friendship";
	
	public ArrayList<User> getFriendList(){
		ArrayList<User> friendList = new ArrayList<User>();
		try {
			String statement = new String("SELECT * FROM " + FriendDBTable + " WHERE uid1 = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int friendID = rs.getInt("uid2");
				friendList.add(getUserByUserID(friendID));
			}
			rs.close();
			return friendList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<QuizTakenRecord> getQuizHistory() {
		return QuizTakenRecord.getQuizHistoryByUserID(userID);
	}
	
	public ArrayList<QuizCreatedRecord> getCreatedQuiz() {
		return QuizCreatedRecord.getCreatedQuizByUserID(userID);
	}
	
	public ArrayList<Achievement> getAchievements() {
		return AchievementRecord.getAchievementsByUserID(userID, Achievement.ALL_TYPE);
	}
	
	public ArrayList<NoteMessage> getNoteMessages() {
		return NoteMessage.getMessagesByUserID(userID);
	}
	
	public ArrayList<FriendRequestMessage> getFriendRequestMessages() {
		return FriendRequestMessage.getMessagesByUserID(userID);
	}
	
	public ArrayList<ChallengeMessage> getChallengeMessages() {
		return ChallengeMessage.getMessagesByUserID(userID);
	}

	public User(String username, String password, String homepageURL, int permission) {
		this.username = username;
		this.password = password;
		this.homepageURL = homepageURL;
		this.permission = permission;
		this.isBlocked = false;
		this.isDead = false;
		this.practiceNumber = 0;
		this.highScoreNumber = 0;
		addUserToDB();
	}
	
	public User(int userID, String username, String password, String homepageURL, int permission, boolean isBlocked,
			boolean isDead, int practiceNumber, int highScoreNumber) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.homepageURL = homepageURL;
		this.permission= permission;
		this.isBlocked = isBlocked;
		this.isDead = isDead;
		this.practiceNumber = practiceNumber;
		this.highScoreNumber = highScoreNumber;
	}
	
	public void addUserToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (username, password, url, permission, isblocked, isdead, practicenumber, highscorenumber)" 
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"userid"});
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, homepageURL);
			stmt.setInt(4, permission);
			stmt.setBoolean(5, isBlocked);
			stmt.setBoolean(6, isDead);
			stmt.setInt(7, practiceNumber);
			stmt.setInt(8, highScoreNumber);			
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			userID = rs.getInt("GENERATED_KEY");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/** 
	 * Find a particular user given the username
	 * @param username
	 * @return
	 */
	public static User getUserByUsername(String username) {
		try {
			String statement = new String("SELECT * FROM " + DBTable + " WHERE username = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			User user = null;
			if (rs.next()) {
				user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"), rs.getString("url"), 
						rs.getInt("permission"), rs.getBoolean("isblocked"), rs.getBoolean("isdead"), 
						rs.getInt("practicenumber"), rs.getInt("highscorenumber"));
			}
			rs.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static User getUserByUserID(int userID) {
		try {
			String statement = new String("SELECT * FROM " + DBTable + " WHERE userid = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			User user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"), rs.getString("url"), 
					rs.getInt("permission"), rs.getBoolean("isblocked"), rs.getBoolean("isdead"), 
					rs.getInt("practicenumber"), rs.getInt("highscorenumber"));
			rs.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateCurrentUser() {
		try {
			String statement = new String("UPDATE " + DBTable + " SET "
					+ "username=?, password=?, url=?, permission=?, isblocked=?, isdead=?, practicenumber=?, highscorenumber=?"
					+ " WHERE userid=?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, homepageURL);
			stmt.setInt(4, permission);
			stmt.setBoolean(5, isBlocked);
			stmt.setBoolean(6, isDead);
			stmt.setInt(7, practiceNumber);
			stmt.setInt(8, highScoreNumber);
			stmt.setInt(9, userID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
	public static void removeUser(User user) {
		user.isDead = true;
		user.updateCurrentUser();
	}
	
	public void addFriend(User user) {
		try {
			String statement = new String("INSERT INTO " + FriendDBTable 
					+ " (uid1, uid2)" 
					+ " VALUES (?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			stmt.setInt(2, user.userID);
			stmt.executeUpdate();

			String statement2 = new String("INSERT INTO " + FriendDBTable 
					+ " (uid1, uid2)" 
					+ " VALUES (?, ?)");
			PreparedStatement stmt2 = DBConnection.con.prepareStatement(statement2);
			stmt2.setInt(1, user.userID);
			stmt2.setInt(2, userID);
			stmt2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeFriend(User user) {
		try {
			String statement = new String("DELETE FROM " + FriendDBTable 
					+ " WHERE uid1 = ? AND uid2 = ?"); 
			
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			stmt.setInt(2, user.userID);
			stmt.executeUpdate();

			String statement2 = new String("DELETE FROM " + FriendDBTable 
					+ " WHERE uid1 = ? AND uid2 = ?"); 
			PreparedStatement stmt2 = DBConnection.con.prepareStatement(statement2);
			stmt2.setInt(1, user.userID);
			stmt2.setInt(2, userID);
			stmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int isFriend(User user) {
		try {
			String statement = new String("SELECT * FROM " + FriendDBTable + " WHERE uid1 = ? AND uid2 = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			stmt.setInt(2, user.userID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return IS_FRIEND;
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	

	// Admin 
	public void addAnnouncement(String title, String content) {
		if (permission != IS_ADMIN) return;
		new Announcement(title, content); // TODO JACK
	}
	
	/**
	 * Promote a particular user to administration privilege 
	 * @param user
	 */
	public void promoteUser(User user) {
		if (permission == IS_ADMIN) {
			user.permission = IS_ADMIN;
			user.updateCurrentUser();
		}
	}
	
	// Quiz
	// Editing is done in servelet in quiz
	public void editQuiz (Quiz quiz) {
		if (quiz.creator.userID == userID) 
			quiz.updateCurrentQuiz();
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
