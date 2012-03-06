package quizweb.question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import quizweb.database.DBConnection;


public class MultipleChoiceQuestion extends Question {
//	Cast Type Summary:
//	ArrayList<String>	question;
//	String				answer;
//	String				userAnswer;
	static final String DBTable = "multiple_choice_question";
	
	@SuppressWarnings("unchecked")
	public MultipleChoiceQuestion(int quizID, int position, Object question, Object answer, double score) {
		super(quizID, position, question, answer, score);
		String questionStr = getConcatedString((ArrayList<String>) question);
		String answerStr = (String) answer;
		// add to database
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (quizid, position, question, answer, score) VALUES (?, ?, ?, ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"questionid"});
			stmt.setInt(1, quizID);
			stmt.setInt(2, position);
			stmt.setString(3, questionStr);
			stmt.setString(4, answerStr);
			stmt.setDouble(5, score);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			questionID = rs.getInt("questionid");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public MultipleChoiceQuestion(int questionID, int quizID, int position, Object question, Object answer, double score) {
		super(quizID, position, question, answer, score);
		this.questionID = questionID;
	}
	
	public static ArrayList<Question> getQuestionsByQuizID(int quizID) {
		ArrayList<Question> questionList = new ArrayList<Question>();
		try {
			String statement = new String("SELECT * FROM " + DBTable + " WHERE quizid = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, quizID);
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> questionStringList = getParsedStrings(rs.getString("question"));
			String answerString = rs.getString("answer");
			while (rs.next()) {
				MultipleChoiceQuestion q = new MultipleChoiceQuestion(
						rs.getInt("questionid"), rs.getInt("quizid"), rs.getInt("position"), 
						questionStringList, answerString, rs.getDouble("score"));
				questionList.add(q);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return questionList;
	}
	
	public static MultipleChoiceQuestion getQuestionByQuestionID(int questionID) {
		try {
			String statement = new String("SELECT * FROM " + DBTable + " WHERE questionid = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, questionID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			ArrayList<String> questionStringList = getParsedStrings(rs.getString("question"));
			String answerString = rs.getString("answer");
			MultipleChoiceQuestion q = new MultipleChoiceQuestion(
					rs.getInt("questionid"), rs.getInt("quizid"), rs.getInt("position"), 
					questionStringList, answerString, rs.getDouble("score"));
			rs.close();
			return q;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;	
	}	
	
	@SuppressWarnings("unchecked")
	public int getCorrectChoiceIndex() {
		ArrayList<String> questionList = (ArrayList<String>) question;
		for (int i = 0; i < questionList.size(); i++) {
			if (questionList.get(i).equals((String)answer))
				return i;
		}
		System.out.println("Cannot find correct choice index");
		return -1;
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
