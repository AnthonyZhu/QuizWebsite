package quizweb.achievement;

import java.sql.*;
import java.util.*;

import quizweb.*;
import quizweb.database.*;
import quizweb.record.*;

public class QuizTakenAchievement extends Achievement {
	
	static ArrayList<QuizTakenAchievement> allAchievements = new ArrayList<QuizTakenAchievement>();
	
	public static ArrayList<QuizTakenAchievement> getAllAchievements() {
		if (allAchievements.isEmpty())
			loadAllAchievements();
		return allAchievements;
	}	
	
	public static void loadAllAchievements() {
		String statement = new String("SELECT * FROM " + DBTable + " WHERE type = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, QUIZ_TAKEN_TYPE);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				QuizTakenAchievement achievement = new QuizTakenAchievement(rs.getInt("aid"), rs.getString("name"), rs.getString("url"),
						rs.getString("description"), rs.getInt("threshold"));
				allAchievements.add(achievement);
			}
			rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public QuizTakenAchievement(String name, String url, String description, int threshold) {
		super(name, url, description, threshold);
		this.type = QUIZ_TAKEN_TYPE;
	}
	
	public QuizTakenAchievement(int id, String name, String url, String description, int threshold) {
		super(name, url, description, threshold);
		this.id = id;
		this.type = QUIZ_TAKEN_TYPE;
	}	

	public static ArrayList<Achievement> updateAchievement(User user) {
		ArrayList<Achievement> records = AchievementRecord.getAchievementsByUserID(user.userID, QUIZ_TAKEN_TYPE);
		ArrayList<Achievement> newAchievements = new ArrayList<Achievement>();
		int totalTakenQuiz = QuizTakenRecord.getQuizHistoryByUserID(user.userID).size(); // TODO can be simplified using COUNT in database
		for (int i = 0; i < getAllAchievements().size(); i++) {
			Achievement achievement = getAllAchievements().get(i);
			boolean found = false;
			for (int j = 0; j < records.size(); j++) 
				if (records.get(j).name.equals(achievement.name)) {
					found = true;
					break;
				}
			if (found) continue;
			if (totalTakenQuiz >= achievement.threshold) {
				AchievementRecord record = new AchievementRecord(user, achievement);
				record.addRecordToDB();
				newAchievements.add(achievement);
			}			
		}
		return newAchievements;
	}
}
