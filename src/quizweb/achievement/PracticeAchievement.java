package quizweb.achievement;

import java.sql.*;
import java.util.*;

import quizweb.*;
import quizweb.database.*;
import quizweb.record.*;

public class PracticeAchievement extends Achievement {
	
	static ArrayList<PracticeAchievement> allAchievements = new ArrayList<PracticeAchievement>();
	static {
		String statement = new String("SELECT * FROM " + DBTable + " WHERE type = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, PRACTICE_TYPE);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PracticeAchievement achievement = new PracticeAchievement(rs.getString("name"), rs.getString("url"),
						rs.getString("description"), rs.getInt("threshold"));
				allAchievements.add(achievement);
			}
			rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public PracticeAchievement(String name, String url, String discription, int threshold) {
		super(name, url, discription, threshold);
		this.type = PRACTICE_TYPE;
		super.addAchievementToDB();
	}

	public static void updateAchievement(User user) {
		ArrayList<Achievement> records = AchievementRecord.getAchievementsByUserID(user.userID, PRACTICE_TYPE);
		for (int i = 0; i < allAchievements.size(); i++) {
			Achievement achievement = allAchievements.get(i);
			if (records.contains(achievement)) continue;
			if (user.practiceNumber >= achievement.threshold) {
				new AchievementRecord(user, achievement);
			}			
		}
	}
}
