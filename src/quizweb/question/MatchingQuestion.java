package quizweb.question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import quizweb.database.DBConnection;


public class MatchingQuestion extends Question {
//	Cast Type Summary:
//	ArrayList<String>	question;
//	ArrayList<String>	answer;
//	ArrayList<String>	userAnswer;
	static final String DBTable = "matching_question";
	
	@SuppressWarnings("unchecked")
	public MatchingQuestion(int quizID, int position, Object question, Object answer, double score) {
		super(quizID, position, question, answer, score);
		String questionStr = getConcatedString((ArrayList<String>) question);
		String answerStr = getConcatedString((ArrayList<String>) answer);
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
	
	public MatchingQuestion(int questionID, int quizID, int position, Object question, Object answer, double score) {
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
			ArrayList<String> answerStringList = getParsedStrings(rs.getString("answer"));
			while (rs.next()) {
				MatchingQuestion q = new MatchingQuestion(
						rs.getInt("questionid"), rs.getInt("quizid"), rs.getInt("position"), 
						questionStringList, answerStringList, rs.getDouble("score"));
				questionList.add(q);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return questionList;

	}	
	
	public static MatchingQuestion getQuestionByQuestionID(int questionID) {
		try {
			String statement = new String("SELECT * FROM " + DBTable + " WHERE questionid = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, questionID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			ArrayList<String> questionStringList = getParsedStrings(rs.getString("question"));
			ArrayList<String> answerStringList = getParsedStrings(rs.getString("answer"));
			MatchingQuestion q = new MatchingQuestion(
					rs.getInt("questionid"), rs.getInt("quizid"), rs.getInt("position"), 
					questionStringList, answerStringList, rs.getDouble("score"));
			rs.close();
			return q;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;	
	}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getCorrectChoiceIndexes() {
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		ArrayList<String> questionList = (ArrayList<String>) question;
		ArrayList<String> answerList = (ArrayList<String>) answer;
		int N = questionList.size() / 2;
		for (int j = 0; j < answerList.size(); j++) 
			for (int i = N; i < questionList.size(); i++) {
				if (answerList.get(j).equals(questionList.get(i))) {
					indexList.add(new Integer(i-N));
					break;
				}				
			}
		return indexList;
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
