package quizweb;

import java.util.*;

public abstract class Question {
	public int		questionID;
	public double	totalScore;
	static private int	maxQuestionID;
	
	
	
	/**
	 * Get the next available question ID
	 * @return
	 */
	static private synchronized int getNextQuestionId() {
		//TODO
		return 0;
	}
	
	/**
	 * This is the method to be override by subclasses
	 * Compute the score of a question given answer from the user
	 * @param answer by the user
	 * @return score
	 */
	abstract public double getScore(ArrayList<String> userAnswer);
}
