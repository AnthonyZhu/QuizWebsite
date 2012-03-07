package quizweb.record;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import quizweb.Quiz;
import quizweb.User;
import quizweb.database.DBConnection;

public class QuizCreatedRecord extends Record {
	public Quiz quiz;
	
	public static String DBTable = "quiz_created_record";
	
	public QuizCreatedRecord(Quiz quiz, User user) {
		this.quiz = quiz;
		this.user = user;
		addRecordToDB();
	}	
	public QuizCreatedRecord(int recordID, Quiz quiz, User user, Timestamp timeStamp) {
		this.recordID = recordID;
		this.quiz = quiz;
		this.user = user;
		this.timestamp = timeStamp;
	}
	
	public void addRecordToDB() {
		// add to database
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (qid, userid)" 
					+ " VALUES (?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"id"});
			stmt.setInt(1, quiz.quizID);
			stmt.setInt(2, user.userID);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			recordID = rs.getInt("id");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}	
	
	
	public static ArrayList<QuizCreatedRecord> getCreatedQuizByUserID(int userID) {
		ArrayList<QuizCreatedRecord> records = new ArrayList<QuizCreatedRecord>();
		String statement = new String("SELECT * FROM " + DBTable + " WHERE userid = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Quiz quiz = Quiz.getQuizByQuizID(rs.getInt("qid"));
				User user = User.getUserByUserID(rs.getInt("userid"));
				QuizCreatedRecord record = new QuizCreatedRecord(rs.getInt("id"), quiz, user, rs.getTimestamp("time"));
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
}
