package quizweb.achievement;

import quizweb.User;

public class QuizCreatedAchievement extends Achievement {
	
	public static final int aid = 1;
	
	public QuizCreatedAchievement(String name, String fileDirectory, String discription) {
		super(name, fileDirectory, discription);
	}

	@Override
	public boolean isAccomplished(User user) {
		return user.getCreatedQuiz().size() >= threshold;
	}

}
