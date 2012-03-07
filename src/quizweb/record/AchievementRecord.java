package quizweb.record;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		addRecordToDB(); 
	}
	
	public void addRecordToDB() {
		// add to database
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (uid, aid)" 
					+ " VALUES (?, ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"id"});
			stmt.setInt(1, user.userID);
			stmt.setInt(2, achievement.id);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			recordID = rs.getInt("id");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public static ArrayList<Achievement> getAchievementsByUserID(int userID, int type) {
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		String statement = new String("SELECT * FROM " + DBTable + " WHERE uid = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Achievement achievement = Achievement.getAchievementByID(rs.getInt("aid"));
				if (achievement.type != type && type != Achievement.ALL_TYPE) continue;
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
}
