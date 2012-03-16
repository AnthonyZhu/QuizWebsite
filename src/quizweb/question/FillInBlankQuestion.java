package quizweb.question;

import java.sql.*;
import java.util.*;

import quizweb.Quiz;
import quizweb.XMLElement;
import quizweb.database.DBConnection;


public class FillInBlankQuestion extends Question {
//	Cast Type Summary:
//	ArrayList<String>	question;
//	ArrayList<String>	answer;
//	String				userAnswer;
	static final String DBTable = "fill_in_blank_question";

	// Create a new fill in blank question
	public FillInBlankQuestion(int quizID, int position, Object question, Object answer, double score) {
		super(quizID, position, question, answer, score);
	}
	
	@SuppressWarnings("unchecked")
	public void addQustionToDB() {
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
			questionID = rs.getInt("GENERATED_KEY");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	// Generate reference from database
	public FillInBlankQuestion(int questionID, int quizID, int position, Object question, Object answer, double score) {
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
				ArrayList<String> questionStringList = getParsedStrings(rs.getString("question"));
				ArrayList<String> answerStringList = getParsedStrings(rs.getString("answer"));				
				FillInBlankQuestion q = new FillInBlankQuestion(
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

	public static FillInBlankQuestion getQuestionByQuestionID(int questionID) {
		try {
			String statement = new String("SELECT * FROM " + DBTable + " WHERE questionid = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, questionID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			ArrayList<String> questionStringList = getParsedStrings(rs.getString("question"));
			ArrayList<String> answerStringList = getParsedStrings(rs.getString("answer"));
			FillInBlankQuestion q = new FillInBlankQuestion(
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
	
	@SuppressWarnings("unchecked")
	@Override
	public String displayQuestion(int position) {
		ArrayList<String> questionList = (ArrayList<String>) question;
		String questionStr = questionList.get(0) + "_____________" + questionList.get(1);
		StringBuilder sb = new StringBuilder();
		sb.append("<span class=\"dominant_text\">" + position + ".</span>\n");
		sb.append("<span class=\"quiz_title\">\n");
		sb.append("<span class=\"dominant_text\">Fill-In-Blank Question (" + score + " points):</span><br /><br />\n");
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
		ArrayList<String> questionList = (ArrayList<String>) question;
		String questionStr = questionList.get(0) + "_____________" + questionList.get(1);
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
			sb.append("<img class=\"small\" src=\"images/wrong.png\"><span class=\"wrong\">incorrect</span></p><br />\n");
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
	

	public static FillInBlankQuestion getFillInBlankQuestionByXMLElem(XMLElement root,	Quiz quiz, int pos) {
		int quizID = quiz.quizID;
		int position = pos;
		Object question = null;
		Object answer = null;
		double score = 10;
		ArrayList<String> questionList = new ArrayList<String>();
		ArrayList<String> answerList = new ArrayList<String>();
		
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("blank-query")) {
				for (int j = 0; j < elem.childList.size(); j++) {
					XMLElement subElem = elem.childList.get(j);
					if (subElem.name.equals("pre")) {
						questionList.add(subElem.content);
					} else if (subElem.name.equals("post")) {
						questionList.add(subElem.content);
					} else if (subElem.name.equals("blank")) {
						// do nothing
					} else {
						System.out.println("blank-query unknown field : " + subElem.name);
					}
				}
				question = questionList;
			} else if (elem.name.equals("answer")) {
				answerList.add(elem.content);
				answer = answerList;
			} else if (elem.name.equals("answer-list")) {
				answer = Question.getAnswerListByXMLElem(elem);
			} else if (elem.name.equals("score")) {
				score = Double.parseDouble(elem.content);
			} else {
				System.out.println("Unexpected field in fill in blank question : " + elem.name);
			}
		}
		return new FillInBlankQuestion(quizID, position, question, answer, score);

	}

}
