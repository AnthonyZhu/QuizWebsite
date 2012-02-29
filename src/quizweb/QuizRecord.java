package quizweb;

public class QuizRecord extends Record {
	public User user;
	public Quiz quiz;
	public double timeTaken;
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
	void quizStart() {
		// TODO assign beginning time to timestamp 
	}
	
	/** 
	 * End the quiz, compute time stats
	 */
	void quizEnd() {
		// TODO compute timeTaken
	}
	
}
