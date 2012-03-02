package quizweb;

import java.util.*;

import quizweb.question.*;
import quizweb.record.*;

public class Quiz {
	public int quizID;
	static private int maxQuizID = 0;
	public String quizURL;	
	public String description;
	public User creator; 
	
	// Statistics
	public int userNumber;	
	public int raterNumber;
	public double totalRating;
	
	// Lazy read
	public ArrayList<Question> questions;
	public HashMap<User, ArrayList<QuizTakenRecord>> history;
	public ArrayList<QuizTakenRecord> topRecord;
	
	/**
	 * Assign quiz ID to new quiz instance
	 * @return assigned quiz ID
	 */
	static private synchronized int getNextQuizID() {
		return maxQuizID++;
	}
	
	/**
	 * Get best score for a given user
	 * @param user
	 * @return
	 */
	public double getBestScore(User user) {
		// TODO
		return 0;
	}
	
}
