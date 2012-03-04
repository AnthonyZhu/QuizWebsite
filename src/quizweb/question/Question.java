package quizweb.question;

import java.util.ArrayList;

public class Question {
	public int questionID;
	public Object question;
	public Object answer;
	public double score;
	
	public double getScore(Object userAnswer) {
		System.out.println("THIS LINE SHOULD NEVER BE REACHED");
		return -1;
	}
	
	public static ArrayList<Question> getQuestionsByQuizID(int quizID) {
		// TODO get question from the database
		return null;
	}
	
	public boolean equals(Question other) {
		return questionID == other.questionID;
	}
}
