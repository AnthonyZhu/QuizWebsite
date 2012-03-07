package quizweb.achievement;

import quizweb.User;

public class QuizCreatedAchievement extends Achievement {
	
	public QuizCreatedAchievement(String name, String fileDirectory, String discription) {
		super(name, fileDirectory, discription);
	}

	@Override
	public boolean isAccomplished(User user) {
		return user.getCreatedQuiz().size() >= threshold;
	}

}
