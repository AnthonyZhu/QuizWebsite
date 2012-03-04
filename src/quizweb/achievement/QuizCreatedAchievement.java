package quizweb.achievement;

import quizweb.User;

public class QuizCreatedAchievement extends Achievement {
	
	@Override
	public boolean isAccomplished(User user) {
		return user.getCreatedQuiz().size() >= threshold;
	}

}
