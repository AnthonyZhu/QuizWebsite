package quizweb.message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import quizweb.database.DBConnection;

public class ChallengeMessage extends Message {
	
	private static final String DBTable = "challenge";
	private static DBConnection db; 
	private int quizID;
	private double bestScore;
	private boolean isRead;
	
	public ChallengeMessage(Timestamp time, int uid1, int uid2, int qID, double bestscore, boolean isread) {
		super(time, uid1, uid2, new String());
		quizID = qID;
		bestScore = bestscore;
		isRead = isread;
		db = new DBConnection();
	}

	@Override
	public void addMessageToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable +" VALUES (?, ?, NOW(), ?, ?, ?)");
			PreparedStatement stmt = db.con.prepareStatement(statement);
			stmt.setInt(1, fromUser);
			stmt.setInt(2, toUser);
			stmt.setInt(3, quizID);
			stmt.setDouble(4, bestScore);
			stmt.setBoolean(5, isRead);
			db.DBUpdate(stmt);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static ArrayList<ChallengeMessage> getMessagesByUserID(int userID) {
		ArrayList<ChallengeMessage> ChallengMessageQueue = new ArrayList<ChallengeMessage>();
		try {
			String statement = new String("SELECT * FROM " + DBTable +" WHERE uid1 = ? OR uid2 = ?");
			PreparedStatement stmt = db.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			stmt.setInt(2, userID);
			ResultSet rs = db.DBQuery(stmt);
			rs.beforeFirst();
			while(rs.next()) {
				ChallengeMessage cm = new ChallengeMessage(
						rs.getTimestamp("time"), rs.getInt("uid1"), rs.getInt("uid2"), rs.getInt("qid"), rs.getDouble("bestScore"), rs.getBoolean("isRead"));
				ChallengMessageQueue.add(cm); 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return ChallengMessageQueue;
	}
}
