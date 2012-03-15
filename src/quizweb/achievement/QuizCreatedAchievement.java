package quizweb.achievement;

import java.sql.*;
import java.util.*;

import quizweb.*;
import quizweb.database.*;
import quizweb.record.*;

public class QuizCreatedAchievement extends Achievement {
	
	private static ArrayList<QuizCreatedAchievement> allAchievements = new ArrayList<QuizCreatedAchievement>();

	public static ArrayList<QuizCreatedAchievement> getAllAchievements() {
		if (allAchievements.isEmpty())
			loadAllAchievements();
		return allAchievements;
	}
	
	public static void loadAllAchievements() {
		String statement = new String("SELECT * FROM " + DBTable + " WHERE type = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, QUIZ_CREATED_TYPE);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				QuizCreatedAchievement achievement = new QuizCreatedAchievement(rs.getInt("aid"), rs.getString("name"), rs.getString("url"), 
						rs.getString("description"), rs.getInt("threshold"));
				allAchievements.add(achievement);
			}
			rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
	public QuizCreatedAchievement(String name, String url, String description, int threshold) {
		super(name, url, description, threshold);
		this.type = QUIZ_CREATED_TYPE;
	}
	
	public QuizCreatedAchievement(int id, String name, String url, String description, int threshold) {
		super(name, url, description, threshold);
		this.id = id;
		this.type = QUIZ_CREATED_TYPE;
	}	

	public static void updateAchievement(User user) {
		ArrayList<Achievement> records = AchievementRecord.getAchievementsByUserID(user.userID, QUIZ_CREATED_TYPE);
		int totalCreatedQuiz = QuizCreatedRecord.getCreatedQuizByUserID(user.userID).size(); // TODO can be simplified using COUNT in database
		for (int i = 0; i < getAllAchievements().size(); i++) {
			Achievement achievement = getAllAchievements().get(i);
			boolean found = false;
			for (int j = 0; j < records.size(); j++) 
				if (records.get(j).name.equals(achievement.name)) {
					found = true;
					break;
				}
			if (found) continue;
			if (totalCreatedQuiz >= achievement.threshold) {
				AchievementRecord record = new AchievementRecord(user, achievement);
				record.addRecordToDB();
			}			
		}
	}

}
