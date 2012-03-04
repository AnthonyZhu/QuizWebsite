package quizweb.question;

import java.util.*;


public class MultipleChoiceQuestion extends Question {
//	Cast Type Summary:
//	ArrayList<String>	question;
//	String				answer;
//	String				userAnswer;
	static final String DBTable = "";
	
	@SuppressWarnings("unchecked")
	public MultipleChoiceQuestion(Object question, Object answer, double score) {
		super(question, answer, score);
		String questionStr = getConcatedString((ArrayList<String>) question);
		String answerStr = (String) answer;
		// TODO add to database
	}	
	
	public static ArrayList<Question> getQuestionsByQuizID(int quizID) {
		// TODO get fill in blank questions from database
		return null;
	}
	
	public static MultipleChoiceQuestion getQuestionByQuestionID(int questionID) {
		// TODO get question by question ID
		return null;
	}	
	
	@Override
	public double getScore(Object userAnswer) {
		String ans = (String) userAnswer;
		String trueAns = (String) answer;
		if (ans.equals(trueAns)) 
			return score;
		return 0;
	}

}
