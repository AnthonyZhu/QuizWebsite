package quizweb.record;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import quizweb.*;
import quizweb.achievement.HighScoreAchievement;
import quizweb.achievement.PracticeAchievement;
import quizweb.achievement.QuizTakenAchievement;
import quizweb.database.DBConnection;

public class QuizTakenRecord extends Record {
	public Quiz quiz;
	public long timeSpan;
	public double score;
	
	public boolean isOnepage;
	public boolean isFeedback;
	public boolean isPractice;
	
	public long quizStartTime;
	
//	public ArrayList<Object> userAnswers;
//	public static int totalQuizTaken = 0;
	
	public static String DBTable = "quiz_taken_record";

	public QuizTakenRecord(Quiz quiz, User user,  
			boolean isOnepage, boolean isFeedback, boolean isPractice) {
		this.quiz = quiz;
		this.user = user;
		this.timeSpan = 0;
		this.score = 0;
		this.isOnepage = isOnepage;
		this.isFeedback = isFeedback;
		this.isPractice = isPractice;
		this.quizStartTime = 0;
	}	
	public QuizTakenRecord(int recordID, Quiz quiz, User user, long timeSpan, double score, 
			Timestamp timeStamp, boolean isOnepage, boolean isFeedback, boolean isPractice) {
		this.recordID = recordID;
		this.quiz = quiz;
		this.user = user;
		this.timeSpan = timeSpan;
		this.score = score;
		this.timestamp = timeStamp;
		this.isOnepage = isOnepage;
		this.isFeedback = isFeedback;
		this.isPractice = isPractice;
	}
	
	public void addRecordToDB() {
		// add to database
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (qid, userid, timespan, score, isonepage, isfeedback, ispractice)" 
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"id"});
			stmt.setInt(1, quiz.quizID);
			stmt.setInt(2, user.userID);
			stmt.setLong(3, timeSpan);
			stmt.setDouble(4, score);
			stmt.setBoolean(5, isOnepage);
			stmt.setBoolean(6, isFeedback);
			stmt.setBoolean(7, isPractice);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			recordID = rs.getInt("GENERATED_KEY");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
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
						rs.getBoolean("isonepage"), rs.getBoolean("isfeedback"), rs.getBoolean("ispractice"));
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
						rs.getBoolean("isonepage"), rs.getBoolean("isfeedback"), rs.getBoolean("ispractice"));
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
						rs.getBoolean("isonepage"), rs.getBoolean("isfeedback"), rs.getBoolean("ispractice"));
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
	
	/**
	 * Start the quiz, begin timing etc
	 */
	public void quizStart() {
		quizStartTime = new Date().getTime();
	}
	
	/** 
	 * End the quiz, compute time stats
	 */
	public void quizEnd(ArrayList<Object> userAnswers) {
		long curTime = new Date().getTime();
		timeSpan = curTime - quizStartTime;
		score = quiz.getScore(userAnswers);
		addRecordToDB();
		// TODO
		// Check if it is a practice
		if (isPractice) {
			user.practiceNumber++;
			user.updateCurrentUser();
			PracticeAchievement.updateAchievement(user);
		}
		// Check if it is a highscore
		if (score >= quiz.getBestScore()) {
			user.highScoreNumber++;
			user.updateCurrentUser();
			HighScoreAchievement.updateAchievement(user);
		}
		QuizTakenAchievement.updateAchievement(user);
	}	
}


