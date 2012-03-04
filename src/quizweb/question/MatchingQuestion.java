package quizweb.question;

import java.util.*;


public class MatchingQuestion extends Question {
//	Cast Type Summary:
//	ArrayList<String>	question;
//	ArrayList<String>	answer;
//	ArrayList<String>	userAnswer;
	static final String DBTable = "";
	
	@SuppressWarnings("unchecked")
	public MatchingQuestion(Object question, Object answer, double score) {
		super(question, answer, score);
		String questionStr = getConcatedString((ArrayList<String>) question);
		String answerStr = getConcatedString((ArrayList<String>) answer);
		// TODO add to database
	}

	public static ArrayList<Question> getQuestionsByQuizID(int quizID) {
		// TODO get fill in blank questions from database
		return null;
	}	
	
	public static MatchingQuestion getQuestionByQuestionID(int questionID) {
		// TODO get question by question ID
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public double getScore(Object userAnswer) {
		ArrayList<String> ans = (ArrayList<String>) userAnswer;
		ArrayList<String> trueAns = (ArrayList<String>) answer;
		ArrayList<String> ques = (ArrayList<String>) question;			
		int matches = 0;
		for (int i = 0; i < ans.size(); i++)
			if (ans.get(i).equals(trueAns.get(i))) 
					matches++;
		return score * matches / ques.size();
	}

}
