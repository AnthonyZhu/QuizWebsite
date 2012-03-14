package quizweb.question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import quizweb.Quiz;
import quizweb.XMLElement;
import quizweb.database.DBConnection;


public class PictureQuestion extends Question {
//	Cast Type Summary:
//	String 				question;
//	ArrayList<String>	answer;
//	String				userAnswer;
	static final String DBTable = "picture_response_question";
	
	public String		questionURL;
	
	public PictureQuestion(int quizID, int position, Object question, Object answer, double score, String questionURL) {
		super(quizID, position, question, answer, score);
		this.questionURL = questionURL;
	}
	
	@SuppressWarnings("unchecked")
	public void addQustionToDB() {
		String questionStr = (String) question;
		String answerStr = getConcatedString((ArrayList<String>) answer);
		// add to database
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (quizid, position, question, answer, score, url) VALUES (?, ?, ?, ?, ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"questionid"});
			stmt.setInt(1, quizID);
			stmt.setInt(2, position);
			stmt.setString(3, questionStr);
			stmt.setString(4, answerStr);
			stmt.setDouble(5, score);
			stmt.setString(6, questionURL);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			questionID = rs.getInt("GENERATED_KEY");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public PictureQuestion(int questionID, int quizID, int position, Object question, Object answer, double score, String questionURL) {
		super(quizID, position, question, answer, score);
		this.questionURL = questionURL;
		this.questionID = questionID;
	}	
	
	public static ArrayList<Question> getQuestionsByQuizID(int quizID) {
		ArrayList<Question> questionList = new ArrayList<Question>();
		try {
			String statement = new String("SELECT * FROM " + DBTable + " WHERE quizid = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, quizID);
			ResultSet rs = stmt.executeQuery();
			String questionString = rs.getString("question");
			ArrayList<String> answerStringList = getParsedStrings(rs.getString("answer"));
			while (rs.next()) {
				PictureQuestion q = new PictureQuestion(
						rs.getInt("questionid"), rs.getInt("quizid"), rs.getInt("position"), 
						questionString, answerStringList, rs.getDouble("score"), rs.getString("url"));
				questionList.add(q);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return questionList;
	}
	
	public static PictureQuestion getQuestionByQuestionID(int questionID) {
		try {
			String statement = new String("SELECT * FROM " + DBTable + " WHERE questionid = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, questionID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			String questionString = rs.getString("question");
			ArrayList<String> answerStringList = getParsedStrings(rs.getString("answer"));
			PictureQuestion q = new PictureQuestion(
					rs.getInt("questionid"), rs.getInt("quizid"), rs.getInt("position"), 
					questionString, answerStringList, rs.getDouble("score"), rs.getString("url"));
			rs.close();
			return q;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
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

	public static PictureQuestion getPictureQuestionByXMLElem(XMLElement root, Quiz quiz, int pos) {
		int quizID = quiz.quizID;
		int position = pos;
		Object question = new String();
		Object answer = null;
		double score = 10;
		String url = null;
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("image-location")) {
				url = elem.content;
			} else if (elem.name.equals("query")) {
				question = elem.content;
			} else if (elem.name.equals("answer")) {
				ArrayList<String> answerList = new ArrayList<String>();
				answerList.add(elem.content);
				answer = answerList;
			} else if (elem.name.equals("answer-list")) {
				answer = Question.getAnswerListByXMLElem(elem);
			} else if (elem.name.equals("score")) {
				score = Double.parseDouble(elem.content);
			} else {
				System.out.println("Unexpected field in picture question : " + elem.name);
			}
		}
		return new PictureQuestion(quizID, position, question, answer, score, url);
	}

}
