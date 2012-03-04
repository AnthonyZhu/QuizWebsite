package quizweb.achievement;

import quizweb.User;

public class PracticeAchievement extends Achievement {

	@Override
	public boolean isAccomplished(User user) {
		return user.practiceNumber >= threshold;
	}

}
