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
	public ArrayList<QuizTakenRecord> history;
	public ArrayList<QuizTakenRecord> topRecord;
	
	public ArrayList<Question> getQuestions() {
		if (questions == null) {
			// TODO get question from the database
			return null;
		} else {
			return questions;
		}
	}
	
	public ArrayList<QuizTakenRecord> getHistory() {
		if (history == null) {
			// TODO Get quiz record history from database
			return null;
		} else {
			return history;
		}
	}

	public ArrayList<QuizTakenRecord> getTopRecord() {
		if (topRecord == null) {
			topRecord = new ArrayList<QuizTakenRecord>(getHistory());
			Collections.sort(topRecord);
			return null;
		} else {
			return topRecord;
		}
	}
	
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
