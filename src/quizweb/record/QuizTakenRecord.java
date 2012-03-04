package quizweb.record;

import java.util.*;
import quizweb.*;

public class QuizTakenRecord extends Record {
	public Quiz quiz;
	public long timeSpan;
	public double score;
	
	public boolean isOnepage;
	public boolean isFeedback;
	public boolean isPractice;
	
	public int rating;
	public String review;
	
	public ArrayList<Object> userAnswers;
	
	public static int totalQuizTaken = 0;

	public QuizTakenRecord(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}	
	
	public static ArrayList<QuizTakenRecord> getQuizHistoryByUserID(int userID) {
		// TODO Get user quiz taken history from database
		return null;
	}
	
	public static ArrayList<QuizTakenRecord> getQuizHistoryByQuizID(int quizID) {
		// TODO Get quiz record history from database
		return null;
	}	
	
	/**
	 * Start the quiz, begin timing etc
	 */
	public void quizStart() {
		timestamp = new Date().getTime();
		// TODO
	}
	
	/** 
	 * End the quiz, compute time stats
	 */
	public void quizEnd() {
		long curTime = new Date().getTime();
		timeSpan = curTime - timestamp;
		score = quiz.getScore(userAnswers);
		// TODO
	}
	
	
	public int compareTo(QuizTakenRecord other) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;
		if (this.score > other.score || (this.score == other.score && this.timeSpan < other.timeSpan))
			return BEFORE;
		else if (this.timestamp == other.timestamp && this.timeSpan == other.timeSpan)
			return EQUAL;
		else 
			return AFTER;		
	}
}
