package quizweb.achievement;

import quizweb.User;

public abstract class Achievement {
	public String description;
	public String name;
	public double threshold;
	
	public abstract boolean isAccomplished(User user);
}
