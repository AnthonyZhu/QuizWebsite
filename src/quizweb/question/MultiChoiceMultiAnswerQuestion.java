package quizweb.question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import quizweb.Quiz;
import quizweb.XMLElement;
import quizweb.database.DBConnection;


public class MultiChoiceMultiAnswerQuestion extends Question {
//	Cast Type Summary:
//	ArrayList<String>	question;
//	ArrayList<String>	answer;
//	ArrayList<String>	userAnswer;
	static final String DBTable = "multiple_choice_answer_question";
	
	public MultiChoiceMultiAnswerQuestion(int quizID, int position, Object question, Object answer, double score) {
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
	
	public MultiChoiceMultiAnswerQuestion(int questionID, int quizID, int position, Object question, Object answer, double score) {
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
				MultiChoiceMultiAnswerQuestion q = new MultiChoiceMultiAnswerQuestion(
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
	
	public static MultiChoiceMultiAnswerQuestion getQuestionByQuestionID(int questionID) {
		try {
			String statement = new String("SELECT * FROM " + DBTable + " WHERE questionid = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, questionID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			ArrayList<String> questionStringList = getParsedStrings(rs.getString("question"));
			ArrayList<String> answerStringList = getParsedStrings(rs.getString("answer"));
			MultiChoiceMultiAnswerQuestion q = new MultiChoiceMultiAnswerQuestion(
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
		for (int i = 1; i < questionList.size(); i++) 
			for (int j = 0; j < answerList.size(); j++) {
				if (answerList.get(j).equals(questionList.get(i))) {
					indexList.add(new Integer(i));
					break;
				}				
			}
		return indexList;
	}
		
	
	@SuppressWarnings("unchecked")
	@Override
	public double getScore(Object userAnswer) {
		if (userAnswer == null)
			return 0;
		ArrayList<String> ans = (ArrayList<String>) userAnswer;
		ArrayList<String> trueAns = (ArrayList<String>) answer;
		ArrayList<String> ques = (ArrayList<String>) question;		
		int matches = 0;
		for (int i = 0; i < ans.size(); i++) {
			for (int j = 0; j < trueAns.size(); j++) {
				if (ans.get(i).equals(trueAns.get(j))) {
					matches++;
					break;
				}
			}
		}
		return score * ((ques.size()-1) - ans.size() - trueAns.size() + 2*matches) / (ques.size()-1);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String displayQuestion(int position) {
		ArrayList<String> questionList = (ArrayList<String>) question;
		String questionStr = questionList.get(0);		
		StringBuilder sb = new StringBuilder();
		sb.append("<span class=\"dominant_text\">" + position + ".</span>\n");
		sb.append("<span class=\"quiz_title\">\n");
		sb.append("<span class=\"dominant_text\">Multiple Choice Question (" + score + " points):</span><br /><br />\n");
		sb.append(questionStr + "\n");
		sb.append("</span><br /><br />\n");		
		sb.append("<div>\n");
		sb.append("<input type=\"hidden\" name=\"user_answer" + position + "_0" + "\" value=\"\" />\n");
		for (int i = 1; i < questionList.size(); i++) {
			sb.append("<input type=\"checkbox\" name=\"user_answer" + position + "_" + i + "\" value=\"" + questionList.get(i) + "\" />" + questionList.get(i) + "<br /><br />\n");
		}
		sb.append("</div>\n");
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String displayQuestionWithAnswer(int position, Object userAnswer) {
		ArrayList<String> questionList = (ArrayList<String>) question;
		String questionStr = questionList.get(0);
		ArrayList<String> answerList = (ArrayList<String>) answer;
		ArrayList<String> userAnswerList = (ArrayList<String>) userAnswer;
		boolean correct = false;
		if (getScore(userAnswer) == score)
			correct = true;
		StringBuilder sb = new StringBuilder();
		sb.append("<span class=\"dominant_text\">Feedback for Question " + position + " (Score: " + Math.round(getScore(userAnswer)*100)/100.0 + "/" + Math.round(score*100)/100.0 + ")" + ":</span><br /><br />\n");
		sb.append("<span class=\"quiz_title\">\n");
		sb.append(questionStr + "\n");
		sb.append("</span><br /><br />\n");
		sb.append("<div>\n");
		for (int i = 1; i < questionList.size(); i++) {
			sb.append("<input type=\"checkbox\" />" + questionList.get(i) + "<br /><br />\n");
		}
		sb.append("</div>\n");
		sb.append("<p class=\"answer\">Your answer is :<br /><br />\n");
		if (correct) {
			sb.append("<span class=\"correct answer\">\n");
			for (int i = 0; i < userAnswerList.size(); i++) {
				sb.append(userAnswerList.get(i) + "<br /><br />\n");
			}
			sb.append("&#160;&#160;</span>\n");
			sb.append("<img class=\"small\" src=\"images/right.png\"></p><br /><br />\n");
		} else {
			sb.append("<span class=\"wrong answer\">\n");
			for (int i = 0; i < userAnswerList.size(); i++) {
				sb.append(userAnswerList.get(i) + "<br /><br />\n");
			}
			sb.append("&#160;&#160;</span>\n");
			sb.append("<img class=\"small\" src=\"images/wrong.png\"><span class=\"wrong\">incorrect</span></p><br />\n");
			sb.append("<p class=\"answer\">Correct answer :  <br /><br /><span class=\"correct answer\">");
			for (int i = 0; i < answerList.size(); i++) {
				sb.append(answerList.get(i) + "<br /><br />\n");
			}
			sb.append("</span></p>\n");
		}
		return sb.toString();
	}	

	public static MultiChoiceMultiAnswerQuestion getMultiChoiceMultiAnswerQuestionByXMLElem(
			XMLElement root, Quiz quiz, int pos) {
		int quizID = quiz.quizID;
		int position = pos;
		Object question = null;
		Object answer = null;
		double score = 10;
		ArrayList<String> answerList = new ArrayList<String>();
		ArrayList<String> questionList = new ArrayList<String>();
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("query")) {
				questionList.add(elem.content);
			} else if (elem.name.equals("query-list")) {
				for (int j = 0; j < elem.childList.size(); j++) {
					XMLElement subElem = elem.childList.get(j);
					if (!subElem.name.equals("query")) 
						System.out.println("Question list unexpected name " + subElem.name);
					questionList.add(subElem.content);
					if (subElem.attributeMap.containsKey("answer"))
						answerList.add(subElem.content);
				}
				
			} else if (elem.name.equals("score")) {
				score = Double.parseDouble(elem.content);
			} else {
				System.out.println("Unexpected field in response question : " + elem.name);
			}
		}
		question = questionList;
		answer = answerList;
		return new MultiChoiceMultiAnswerQuestion(quizID, position, question, answer, score);
	}

}
