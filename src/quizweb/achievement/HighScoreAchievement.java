package quizweb.achievement;

import quizweb.User;

public class HighScoreAchievement extends Achievement {
	
	public HighScoreAchievement(String name, String fileDirectory, String discription) {
		super(name, fileDirectory, discription);
	}

	@Override
	public boolean isAccomplished(User user) {
		return user.highScoreNumber >= threshold;
	}

}
