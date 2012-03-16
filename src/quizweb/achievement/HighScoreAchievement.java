package quizweb.achievement;

import java.sql.*;
import java.util.*;

import quizweb.*;
import quizweb.database.*;
import quizweb.record.*;

public class HighScoreAchievement extends Achievement {
	
	private static ArrayList<HighScoreAchievement> allAchievements = new ArrayList<HighScoreAchievement>();
	
	public static ArrayList<HighScoreAchievement> getAllAchievements() {
		if (allAchievements.isEmpty())
			loadAllAchievements();
		return allAchievements;
	}
	
	public static void loadAllAchievements() {
		String statement = new String("SELECT * FROM " + DBTable + " WHERE type = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, HIGHSCORE_TYPE);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HighScoreAchievement achievement = new HighScoreAchievement(rs.getInt("aid"), rs.getString("name"), rs.getString("url"),
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
	
	public HighScoreAchievement(int id, String name, String url, String description, int threshold) {
		super(name, url, description, threshold);
		this.id = id;
		this.type = HIGHSCORE_TYPE;
	}	

	public static ArrayList<Achievement> updateAchievement(User user) {
		ArrayList<Achievement> records = AchievementRecord.getAchievementsByUserID(user.userID, HIGHSCORE_TYPE);
		ArrayList<Achievement> newAchievements = new ArrayList<Achievement>();
		for (int i = 0; i < getAllAchievements().size(); i++) {
			Achievement achievement = getAllAchievements().get(i);
			boolean found = false;
			for (int j = 0; j < records.size(); j++) 
				if (records.get(j).name.equals(achievement.name)) {
					found = true;
					break;
				}
			if (found) continue;
			if (user.highScoreNumber >= achievement.threshold) {
				AchievementRecord record = new AchievementRecord(user, achievement);
				record.addRecordToDB();
				newAchievements.add(achievement);
			}			
		}
		return newAchievements;
	}
}
