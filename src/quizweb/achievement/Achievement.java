package quizweb.achievement;

import java.util.*;
import quizweb.*;

public class Achievement {
	public String description;
	public String name;
	public double threshold;
	
	public static ArrayList<Achievement> allAchievements;
	
	public static void initAllAchievements() {
		// TODO initialize achievements from database;
	}
	
	public boolean isAccomplished(User user) {
		System.out.println("THIS LINE SHOULD NEVER BE REACHED");
		return false;
	}
	
	public boolean equals(Achievement other) {
		return name.equals(other.name);
	}
}
