package quizweb.achievement;

import java.sql.*;
import java.util.*;

import quizweb.*;
import quizweb.database.*;
import quizweb.record.*;

public class QuizCreatedAchievement extends Achievement {
	
	static ArrayList<QuizCreatedAchievement> allAchievements = new ArrayList<QuizCreatedAchievement>();
	static {
		String statement = new String("SELECT * FROM " + DBTable + " WHERE type = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, QUIZ_CREATED_TYPE);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				QuizCreatedAchievement achievement = new QuizCreatedAchievement(rs.getString("name"), 
						rs.getString("description"), rs.getInt("threshold"));
				allAchievements.add(achievement);
			}
			rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public QuizCreatedAchievement(String name, String discription, int threshold) {
		super(name, discription, threshold);
		this.type = QUIZ_CREATED_TYPE;
		super.addAchievementToDB();
	}

	public static void updateAchievement(User user) {
		ArrayList<Achievement> records = AchievementRecord.getAchievementsByUserID(user.userID, QUIZ_CREATED_TYPE);
		int totalCreatedQuiz = QuizCreatedRecord.getCreatedQuizByUserID(user.userID).size(); // TODO can be simplified using COUNT in database
		for (int i = 0; i < allAchievements.size(); i++) {
			Achievement achievement = allAchievements.get(i);
			if (records.contains(achievement)) continue;
			if (totalCreatedQuiz >= achievement.threshold) {
				new AchievementRecord(user, achievement);
			}			
		}
	}

}
