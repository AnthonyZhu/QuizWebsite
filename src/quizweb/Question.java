package quizweb;

import java.util.*;

public abstract class Question {
	private int quizID;
	private int questionID;
	private String questionContent;
	private String answer;
	private double questionScore;
	
	private double totalScore;
	static private int maxQuestionID;
	
	public Question(int quizID, int questionID){
		this.quizID = quizID;
		this.questionScore = questionID;
	}
	
	public int getQuizID(){
		return quizID;
	}
	
	public int getQuestionID(){
		return questionID;
	}
	
	public abstract String getQuestionContent();
	public abstract String getAnswer();
	public abstract double getQuestionScore();
	public abstract double getUserScore(String userAnswer);
	
	
	/**
	 * Get the next available question ID
	 * @return
	 */
	static private synchronized int getNextQuestionId() {
		//TODO
		return 0;
	}
	
}
