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
	
	static public int totalQuizTaken = 0;

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
