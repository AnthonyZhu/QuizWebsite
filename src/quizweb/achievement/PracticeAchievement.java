package quizweb.achievement;

import java.util.ArrayList;

import quizweb.User;

public class PracticeAchievement extends Achievement {
	
	private static final String DBTable = "achievement_history";

	public PracticeAchievement(String name, String fileDirectory, String discription) {
		super(name, fileDirectory, discription);
	}

	@Override
	public boolean isAccomplished(User user) {
		return user.practiceNumber >= threshold;
	}

	public static ArrayList<PracticeAchievement> getAchievementsByUserID(int UserID) {
		String statement = new String("INSERT INTO " + DBTable +" VALUES (?, ?, ?)");
		return null;
	}

}
