package quizweb.question;

import java.sql.*;
import java.util.*;

import quizweb.database.DBConnection;


public class FillInBlankQuestion extends Question {
//	Cast Type Summary:
//	ArrayList<String>	question;
//	ArrayList<String>	answer;
//	String				userAnswer;
	static final String DBTable = "fill_in_blank_question";
	private DBConnection db; 

	@SuppressWarnings("unchecked")
	public FillInBlankQuestion(int quizID, int position, Object question, Object answer, double score) {
		super(quizID, position, question, answer, score);
		String questionStr = getConcatedString((ArrayList<String>) question);
		String answerStr = getConcatedString((ArrayList<String>) answer);
		// add to database
		db = new DBConnection();
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (quizid, position, question, answer, score) VALUES (?, ?, ?, ?, ?)");
			PreparedStatement stmt = db.con.prepareStatement(statement, new String[] {"id"});
			stmt.setInt(1, quizID);
			stmt.setInt(2, position);
			stmt.setString(3, questionStr);
			stmt.setString(4, answerStr);
			stmt.setDouble(5, score);
			db.DBUpdate(stmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
