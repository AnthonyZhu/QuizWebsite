package quizweb.achievement;

import quizweb.User;

public class HighScoreAchievement extends Achievement {
	
	@Override
	public boolean isAccomplished(User user) {
		return user.highScoreNumber >= threshold;
	}

}
