package quizweb.record;

import quizweb.Quiz;

public class QuizTakenRecord extends Record {
	public Quiz quiz;
	public double timeSpan;
	public double score;
	
	public boolean isRandom;
	public boolean isOnepage;
	public boolean isFeedback;
	public boolean isPractice;
	
	public int rating;
	public String review;
	
	static public int totalQuizTaken = 0;

	/**
	 * Start the quiz, begin timing etc
	 */
	public void quizStart() {
		// TODO assign beginning time to timestamp 
	}
	
	/** 
	 * End the quiz, compute time stats
	 */
	public void quizEnd() {
		// TODO compute timeTaken
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
