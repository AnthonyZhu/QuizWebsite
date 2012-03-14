package quizweb.question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import quizweb.Quiz;
import quizweb.XMLElement;
import quizweb.database.DBConnection;


public class MatchingQuestion extends Question {
//	Cast Type Summary:
//	ArrayList<String>	question;
//	ArrayList<String>	answer;
//	ArrayList<String>	userAnswer;
	static final String DBTable = "matching_question";
	
	public MatchingQuestion(int quizID, int position, Object question, Object answer, double score) {
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
					indexList.add(new Integer(i-N+1));
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
		return score * matches / (ques.size() / 2);
	}

	public static MatchingQuestion getMatchingQuestionByXMLElem(XMLElement root, Quiz quiz, int pos) {
		int quizID = quiz.quizID;
		int position = pos;
		Object question = null;
		Object answer = null;
		double score = 10;
		ArrayList<String> questionList = new ArrayList<String>();
		ArrayList<String> answerList = new ArrayList<String>();
		ArrayList<Integer> answerIndex = new ArrayList<Integer>();
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("query-list")) {
				for (int j = 0; j < elem.childList.size(); j++) {
					XMLElement subElem = elem.childList.get(j);
					if (!subElem.name.equals("query")) 
						System.out.println("Question list unexpected name " + subElem.name);
					if (!subElem.attributeMap.containsKey("answer")) 
						System.out.println("Matching question must have attribute 'answer' for each query");
					int ans = Integer.parseInt(subElem.attributeMap.get("answer"));
					answerIndex.add(new Integer(ans));
					questionList.add(subElem.content);
				}
			} else if (elem.name.equals("answer-list")) {
				questionList.addAll(Question.getAnswerListByXMLElem(elem));
			} else if (elem.name.equals("score")) {
				score = Double.parseDouble(elem.content);
			} else {
				System.out.println("Unexpected field in matching question : " + elem.name);
			}
		}
		int N = questionList.size() / 2;
		for (int i = 0; i < answerIndex.size(); i++)
			answerList.add(questionList.get(N-1 + answerIndex.get(i).intValue()));
		question = questionList;
		answer = answerList;
		return new MatchingQuestion(quizID, position, question, answer, score);		
	}

}
