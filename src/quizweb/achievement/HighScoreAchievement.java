package quizweb.achievement;

import java.sql.*;
import java.util.*;

import quizweb.*;
import quizweb.database.*;
import quizweb.record.*;

public class HighScoreAchievement extends Achievement {
	
	static ArrayList<HighScoreAchievement> allAchievements = new ArrayList<HighScoreAchievement>();
	static {
		String statement = new String("SELECT * FROM " + DBTable + " WHERE type = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, HIGHSCORE_TYPE);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HighScoreAchievement achievement = new HighScoreAchievement(rs.getString("name"), rs.getString("url"),
						rs.getString("description"), rs.getInt("threshold"));
				allAchievements.add(achievement);
			}
			rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public HighScoreAchievement(String name, String url, String description, int threshold) {
		super(name, url, description, threshold);
		this.type = HIGHSCORE_TYPE;
	}

	public static void updateAchievement(User user) {
		ArrayList<Achievement> records = AchievementRecord.getAchievementsByUserID(user.userID, HIGHSCORE_TYPE);
		for (int i = 0; i < allAchievements.size(); i++) {
			Achievement achievement = allAchievements.get(i);
			if (records.contains(achievement)) continue;
			if (user.highScoreNumber >= achievement.threshold) {
				new AchievementRecord(user, achievement);
			}			
		}
	}
}
