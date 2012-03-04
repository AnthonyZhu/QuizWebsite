package quizweb.record;

import java.util.*;

import quizweb.*;
import quizweb.achievement.*;

public class AchievementRecord extends Record {
	public Achievement achievement;
	
	public AchievementRecord(User user, Achievement achievement) {
		super(user);
		this.achievement = achievement;
		// Add the record to achievement record table
		// Retrieve record ID
		// TODO 
	}
	
	public static ArrayList<AchievementRecord> getAchievementsByUserID(int userID) {
		// TODO Get user achievement record list from database
		return null;
	}
		
	
}
