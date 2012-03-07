package quizweb.achievement;

import quizweb.User;

public class PracticeAchievement extends Achievement {
	
	public static final int aid = 3;

	public PracticeAchievement(String name, String fileDirectory, String discription) {
		super(name, fileDirectory, discription);
	}

	@Override
	public boolean isAccomplished(User user) {
		return user.practiceNumber >= threshold;
	}

}
