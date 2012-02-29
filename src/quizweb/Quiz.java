package quizweb;

import java.util.*;

public class Quiz {		
	public int quizID;
	public String quizURL;	
	public String description;
	public User creator; // TODO JACK
	public ArrayList<Integer> questions;
	public int userCount;
	
	static private int maxQuizID = 0;
	
	// TODO JACK
	HashMap<User, ArrayList<QuizRecord>> history;
	ArrayList<QuizRecord> topRecord;
	
	/**
	 * Assign quiz ID to new quiz instance
	 * @return
	 */
	static private synchronized int getNextQuizID() {
		// TODO
		return 0;
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
