package quizweb.question;

import java.util.*;


public class FillInBlankQuestion extends Question {
//	Cast Type Summary:
//	String 				question;
//	ArrayList<String>	answer;
//	String				userAnswer;
	static final String DBTable = "";

	public FillInBlankQuestion(Object question, Object answer, double score) {
		super(question, answer, score);
		String questionStr = (String) question;
		String answerStr = getConcatedString((ArrayList<String>) answer);
		// TODO add to database
	}	
	
	public static ArrayList<Question> getQuestionsByQuizID(int quizID) {
		// TODO get fill in blank questions from database
		return null;
	}
	
	public static FillInBlankQuestion getQuestionByQuestionID(int questionID) {
		// TODO get question by question ID
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public double getScore(Object userAnswer) {
		String ans = (String) userAnswer;
		ArrayList<String> trueAns = (ArrayList<String>) answer;
		for (int i = 0; i < trueAns.size(); i++) {
			if (trueAns.get(i).equals(ans))
				return score;
		}		
		return 0;
	}

}
