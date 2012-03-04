package quizweb.question;

import java.util.*;

import quizweb.database.DBConnection;
import quizweb.database.DBQueries;

public class ResponseQuestion extends Question {
//	Cast Type Summary:
//	String 				question;
//	ArrayList<String>	answer;
//	String				userAnswer;
	static final String DBTable = "question_response_question";
	
	@SuppressWarnings("unchecked")
	public ResponseQuestion(Object question, Object answer, double score) {
		super(question, answer, score);
		String questionStr = (String) question;
		String answerStr = getConcatedString((ArrayList<String>) answer);		
		addToResponseQuestionDB();
	}

	public static void addToResponseQuestionDB() {
		DBConnection db = new DBConnection();
		DBQueries query = new DBQueries();
		HashMap<String, String> conditionMap = new HashMap<String, String>();
		// TODO JACK
//		conditionMap.put(key, value)
//		String statement = query.InsertQueryByColumn(DBTable, "", String.valueOf(type));
//		db.DBUpdate(statement);
	}
	
	public static ArrayList<Question> getQuestionsByQuizID(int quizID) {
		// TODO get fill in blank questions from database
		return null;
	}

	public static ResponseQuestion getQuestionByQuestionID(int questionID) {
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
