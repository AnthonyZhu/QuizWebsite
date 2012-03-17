package quizweb.record;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import quizweb.*;
import quizweb.database.DBConnection;

public class QuizTakenRecord extends Record {
	public Quiz quiz;
	public long timeSpan;
	public double score;
	
	public boolean isFeedback;
	public boolean isPractice;
	
	public static String DBTable = "quiz_taken_record";

	public QuizTakenRecord(Quiz quiz, User user, long timeSpan, double score,  
			boolean isFeedback, boolean isPractice) {
		this.quiz = quiz;
		this.user = user;
		this.timeSpan = timeSpan;
		this.score = score;
		this.isFeedback = isFeedback;
		this.isPractice = isPractice;
	}	
	public QuizTakenRecord(int recordID, Quiz quiz, User user, long timeSpan, double score, 
			Timestamp timeStamp,  boolean isFeedback, boolean isPractice) {
		this.recordID = recordID;
		this.quiz = quiz;
		this.user = user;
		this.timeSpan = timeSpan;
		this.score = score;
		this.timestamp = timeStamp;
		this.isFeedback = isFeedback;
		this.isPractice = isPractice;
	}
	
	@Override
	public void addRecordToDB() {
		// add to database
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (qid, userid, timespan, score, isfeedback, ispractice)" 
					+ " VALUES (?, ?, ?, ?, ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"id"});
			stmt.setInt(1, quiz.quizID);
			stmt.setInt(2, user.userID);
			stmt.setLong(3, timeSpan);
			stmt.setDouble(4, score);
			stmt.setBoolean(5, isFeedback);
			stmt.setBoolean(6, isPractice);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			recordID = rs.getInt("GENERATED_KEY");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static int getTotalQuizTakenRecord() {
		int totalRecords = 0;
		try {
			String statement = new String("SELECT COUNT(id) FROM " + DBTable);
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				totalRecords = rs.getInt("COUNT(id)");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return totalRecords;
	}
	
	
	public static ArrayList<QuizTakenRecord> getQuizHistoryByUserID(int userID) {
		ArrayList<QuizTakenRecord> records = new ArrayList<QuizTakenRecord>();
		String statement = new String("SELECT * FROM " + DBTable + " WHERE userid = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Quiz quiz = Quiz.getQuizByQuizID(rs.getInt("qid"));				
				User user = User.getUserByUserID(rs.getInt("userid"));
				QuizTakenRecord record = new QuizTakenRecord(rs.getInt("id"), quiz,
						user, rs.getLong("timespan"), rs.getDouble("score"), rs.getTimestamp("time"),
						rs.getBoolean("isfeedback"), rs.getBoolean("ispractice"));
				records.add(record);
			}
			rs.close();
			Collections.sort(records, new RecordSortByTime());
			return records;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<QuizTakenRecord> getQuizHistoryByQuizID(int quizID, Timestamp time) {
		ArrayList<QuizTakenRecord> records = new ArrayList<QuizTakenRecord>();
		String statement = new String("SELECT * FROM " + DBTable + " WHERE qid = ? AND time > ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, quizID);
			stmt.setTimestamp(2, time);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = User.getUserByUserID(rs.getInt("userid"));
				Quiz quiz = Quiz.getQuizByQuizID(rs.getInt("qid"));
				QuizTakenRecord record = new QuizTakenRecord(rs.getInt("id"), quiz,
						user, rs.getLong("timespan"), rs.getDouble("score"), rs.getTimestamp("time"),
						rs.getBoolean("isfeedback"), rs.getBoolean("ispractice"));
				records.add(record);
			}
			rs.close();
			Collections.sort(records, new RecordSortByTime());
			return records;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<QuizTakenRecord> getQuizHistoryByQuizIDUserID(int quizID, int userID) {
		ArrayList<QuizTakenRecord> records = new ArrayList<QuizTakenRecord>();
		String statement = new String("SELECT * FROM " + DBTable + " WHERE qid = ? AND userid = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, quizID);
			stmt.setInt(2, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = User.getUserByUserID(rs.getInt("userid"));
				Quiz quiz = Quiz.getQuizByQuizID(rs.getInt("qid"));
				QuizTakenRecord record = new QuizTakenRecord(rs.getInt("id"), quiz,
						user, rs.getLong("timespan"), rs.getDouble("score"), rs.getTimestamp("time"),
						rs.getBoolean("isfeedback"), rs.getBoolean("ispractice"));
				records.add(record);
			}
			rs.close();
			Collections.sort(records, new RecordSortByTime());
			return records;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Record getQuizTakenRecordByXMLElem(XMLElement root) {
		User user = null;
		Quiz quiz = null;
		long timeSpan = -1;
		double score = -1;
		boolean isFeedback = false;
		boolean isPractice = false;
		if (root.attributeMap.containsKey("feedback") && root.attributeMap.get("feedback").equals("true"))
			isFeedback = true;
		if (root.attributeMap.containsKey("practice") && root.attributeMap.get("practice").equals("true"))
			isPractice = true;
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("user")) {
				user = User.getUserByUsername(elem.content);
			} else if (elem.name.equals("quiz")) {
				quiz = Quiz.getQuizByQuizName(elem.content);
			} else if (elem.name.equals("score")) {
				score = Double.parseDouble(elem.content);
			} else if (elem.name.equals("time")) {
				timeSpan = Long.parseLong(elem.content);
			} else {
				System.out.println("Unrecognized quiz taken record field " + elem.name);
			}			
		}
		QuizTakenRecord record = new QuizTakenRecord(quiz, user, timeSpan, score, isFeedback, isPractice);
		return record;
	}
	
	public static void deleteQuizTakenHistory(Quiz quiz) {
		try {
			String statement = new String("DELETE FROM " + DBTable + " WHERE qid=?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, quiz.quizID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}


