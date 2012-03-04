package quizweb.question;

import java.util.ArrayList;


public class PictureQuestion extends Question {
//	Cast Type Summary:
//	String 				question;
//	ArrayList<String>	answer;
//	String				userAnswer;
	static final String DBTable = "";
	
	public String		questionURL;
	
	@SuppressWarnings("unchecked")
	public PictureQuestion(Object question, Object answer, double score, String questionURL) {
		super(question, answer, score);
		this.questionURL = questionURL;
		String questionStr = (String) question;
		String answerStr = getConcatedString((ArrayList<String>) answer);
		// TODO add to database
	}
	
	public static ArrayList<Question> getQuestionsByQuizID(int quizID) {
		// TODO get fill in blank questions from database
		return null;
	}
	
	public static PictureQuestion getQuestionByQuestionID(int questionID) {
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
