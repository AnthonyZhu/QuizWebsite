package quizweb.question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import quizweb.Quiz;
import quizweb.XMLElement;
import quizweb.database.*;

public class ResponseQuestion extends Question {
//	Cast Type Summary:
//	String 				question;
//	ArrayList<String>	answer;
//	String				userAnswer;
	static final String DBTable = "question_response_question";
	
	public ResponseQuestion(int quizID, int position, Object question, Object answer, double score) {
		super(quizID, position, question, answer, score);
	}
	
	@SuppressWarnings("unchecked")
	public void addQustionToDB() {	
		String questionStr = (String) question;
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
			questionID = rs.getInt("GENERATED_KEY");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public ResponseQuestion(int questionID, int quizID, int position, Object question, Object answer, double score) {
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
			while (rs.next()) {
				String questionString = rs.getString("question");
				ArrayList<String> answerStringList = getParsedStrings(rs.getString("answer"));				
				ResponseQuestion q = new ResponseQuestion(
						rs.getInt("questionid"), rs.getInt("quizid"), rs.getInt("position"), 
						questionString, answerStringList, rs.getDouble("score"));
				questionList.add(q);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return questionList;
	}

	public static ResponseQuestion getQuestionByQuestionID(int questionID) {
		try {
			String statement = new String("SELECT * FROM " + DBTable + " WHERE questionid = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, questionID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			String questionString = rs.getString("question");
			ArrayList<String> answerStringList = getParsedStrings(rs.getString("answer"));
			ResponseQuestion q = new ResponseQuestion(
					rs.getInt("questionid"), rs.getInt("quizid"), rs.getInt("position"), 
					questionString, answerStringList, rs.getDouble("score"));
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
		if (userAnswer == null)
			return 0;
		String ans = (String) userAnswer;
		ArrayList<String> trueAns = (ArrayList<String>) answer;
		for (int i = 0; i < trueAns.size(); i++) {
			if (trueAns.get(i).equals(ans))
				return score;
		}		
		return 0;
	}
	
	@Override
	public String displayQuestion(int position) {
		String questionStr = (String) question;
		StringBuilder sb = new StringBuilder();
		sb.append("<span class=\"dominant_text\">" + position + ".</span><br /><br />\n");
		sb.append("<span class=\"quiz_title\">\n");
		sb.append("<span class=\"dominant_text\">Response Question (" + score + " points):</span><br /><br />\n");
		sb.append(questionStr + "\n");
		sb.append("</span><br /><br />\n");
		sb.append("<p>Please answer below: </p>\n");
		sb.append("<div>");
		sb.append("<input id=\"Field1\" name=\"user_answer" + position + "\" type=\"text\" class=\"field text large\" value=\"\" maxlength=\"50\" tabindex=\"1\" onkeyup=\"validateRange(2, 'character');\" />");
		sb.append("</div>");
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String displayQuestionWithAnswer(int position, Object userAnswer) {
		String questionStr = (String) question;
		ArrayList<String> answerList = (ArrayList<String>) answer;
		String userAnswerStr = (String) userAnswer;
		boolean correct = false;
		if (getScore(userAnswer) == score)
			correct = true;
		StringBuilder sb = new StringBuilder();
		sb.append("<span class=\"dominant_text\">Feedback for Question " + position + ":</span><br /><br />\n");
		sb.append("<span class=\"quiz_title\">\n");
		sb.append(questionStr + "\n");
		sb.append("</span><br /><br />\n");
		sb.append("<p class=\"answer\">Your answer is :\n");
		if (correct) {
			sb.append("<span class=\"correct answer\">" + userAnswerStr + "&#160;&#160;</span>");
			sb.append("<img class=\"small\" src=\"images/right.png\"></p><br /><br />");
		} else {
			sb.append("<span class=\"wrong answer\">" + userAnswerStr + "&#160;&#160;</span>");
			sb.append("<img class=\"small\" src=\"images/wrong.png\"><span class=\"wrong\">incorrect</span>");
			sb.append("<p class=\"answer\">Correct answer :  <span class=\"correct answer\">");
			for (int i = 0; i < answerList.size(); i++) {
				sb.append(answerList.get(i));
				if (i < answerList.size() - 1) 
					sb.append(" OR ");
			}
			sb.append("</span></p>\n");
		}
		return sb.toString();
	}	

	public static ResponseQuestion getResponseQuestionByXMLElem(XMLElement root, Quiz quiz, int pos) {
		int quizID = quiz.quizID;
		int position = pos;
		Object question = null;
		Object answer = null;
		double score = 10;
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("query")) {
				question = (String) elem.content;
			} else if (elem.name.equals("answer")) {
				ArrayList<String> answerList = new ArrayList<String>();
				answerList.add(elem.content);
				answer = answerList;
			} else if (elem.name.equals("answer-list")) {
				answer = Question.getAnswerListByXMLElem(elem);
			} else if (elem.name.equals("score")) {
				score = Double.parseDouble(elem.content);
			} else {
				System.out.println("Unexpected field in response question : " + elem.name);
			}
		}
		return new ResponseQuestion(quizID, position, question, answer, score);
	}
}
