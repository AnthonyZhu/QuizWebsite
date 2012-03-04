package quizweb.achievement;

import quizweb.User;

public class QuizTakenAchievement extends Achievement {
	
	@Override
	public boolean isAccomplished(User user) {
		return user.getQuizHistory().size() >= threshold;
	}
}
