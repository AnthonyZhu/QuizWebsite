package quizweb.achievement;

import quizweb.User;

public class QuizTakenAchievement extends Achievement {
	
	public QuizTakenAchievement(String name, String fileDirectory, String discription) {
		super(name, fileDirectory, discription);
	}

	@Override
	public boolean isAccomplished(User user) {
		return user.getQuizHistory().size() >= threshold;
	}
}
