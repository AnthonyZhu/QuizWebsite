package quizweb.achievement;

import java.sql.*;
import java.util.*;

import quizweb.*;
import quizweb.database.*;
import quizweb.record.*;

public class PracticeAchievement extends Achievement {
	
	private static ArrayList<PracticeAchievement> allAchievements = new ArrayList<PracticeAchievement>();

	public static ArrayList<PracticeAchievement> getAllAchievements() {
		if (allAchievements.isEmpty())
			loadAllAchievements();
		return allAchievements;
	}	
	
	public static void loadAllAchievements() {
		String statement = new String("SELECT * FROM " + DBTable + " WHERE type = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, PRACTICE_TYPE);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PracticeAchievement achievement = new PracticeAchievement(rs.getInt("aid"), rs.getString("name"), rs.getString("url"),
						rs.getString("description"), rs.getInt("threshold"));
				allAchievements.add(achievement);
			}
			rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
	public PracticeAchievement(String name, String url, String description, int threshold) {
		super(name, url, description, threshold);
		this.type = PRACTICE_TYPE;
	}	
	
	public PracticeAchievement(int id, String name, String url, String description, int threshold) {
		super(name, url, description, threshold);
		this.id = id;
		this.type = PRACTICE_TYPE;
	}

	public static ArrayList<Achievement> updateAchievement(User user) {
		ArrayList<Achievement> records = AchievementRecord.getAchievementsByUserID(user.userID, PRACTICE_TYPE);
		ArrayList<Achievement> newAchievements = new ArrayList<Achievement>();
		for (int i = 0; i < getAllAchievements().size(); i++) {
			Achievement achievement = (Achievement) getAllAchievements().get(i);
			boolean found = false;
			for (int j = 0; j < records.size(); j++) 
				if (records.get(j).name.equals(achievement.name)) {
					found = true;
					break;
				}
			if (found) continue;
			if (user.practiceNumber >= achievement.threshold) {
				AchievementRecord record = new AchievementRecord(user, achievement);
				record.addRecordToDB();
				newAchievements.add(achievement);
			}			
		}
		return newAchievements;
	}
}
