package quizweb.record;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import quizweb.*;
import quizweb.achievement.*;
import quizweb.database.DBConnection;

public class AchievementRecord extends Record {
	public Achievement achievement;
	
	public static String DBTable = "achievement_record";
	
	public AchievementRecord(User user, Achievement achievement) {
		this.user = user;
		this.achievement = achievement;
	}
	
	public AchievementRecord(int recordID, User user, Achievement achievement, Timestamp timestamp) {
		this.recordID = recordID;
		this.user = user;
		this.achievement = achievement;
		this.timestamp = timestamp;
	}
	
	@Override
	public void addRecordToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (userid, aid)" 
					+ " VALUES (?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"id"});
			stmt.setInt(1, user.userID);
			stmt.setInt(2, achievement.id);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			recordID = rs.getInt("GENERATED_KEY");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static int getTotalAchievementRecord() {
		int totalRecords = 0;
		try {
			String statement = new String("SELECT COUNT(id) FROM " + DBTable);
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				totalRecords = rs.getInt("COUNT(id)");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return totalRecords;
	}	

	public static ArrayList<Achievement> getAchievementsByUserID(int userID, int type) {
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		String statement = new String("SELECT * FROM " + DBTable + " WHERE userid = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Achievement achievement = Achievement.getAchievementByID(rs.getInt("aid"));
				if (achievement.type != type && type != Achievement.ALL_TYPE) 
					continue;
				achievements.add(achievement);
			}
			rs.close();
			Collections.sort(achievements, new AchievementSortByID());
			return achievements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<AchievementRecord> getAchievementRecordByUserID(int userID) {
		ArrayList<AchievementRecord> records = new ArrayList<AchievementRecord>();
		String statement = new String("SELECT * FROM " + DBTable + " WHERE userid = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = User.getUserByUserID(rs.getInt("userid"));
				Achievement achievement = Achievement.getAchievementByID(rs.getInt("aid"));
				AchievementRecord record = new AchievementRecord(rs.getInt("id"), user, achievement, rs.getTimestamp("time")); 
				records.add(record);
			}
			rs.close();
			Collections.sort(records, new RecordSortByTime());
			return records;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	

	public static Record getAchievementRecordByXMLElem(XMLElement root) {
		User user = null;
		Achievement achievement = null;
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("user")) {
				user = User.getUserByUsername(elem.content);
			} else if (elem.name.equals("achievement")) {
				achievement = Achievement.getAchievementByName(elem.content);
			} else {
				System.out.println("Unrecognized achievement record field " + elem.name);
			}
		}
		if (user == null) {
			System.out.println("User in achievement record not found");
			return null;
		}
		if (achievement == null) {
			System.out.println("Quiz in achievement record not found");
			return null;
		}
		return new AchievementRecord(user, achievement);
	}
}
