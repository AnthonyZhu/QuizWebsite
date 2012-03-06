package quizweb.message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import quizweb.database.DBConnection;

public class ChallengeMessage extends Message {
	
	private static final String DBTable = "challenge";
	private int quizID;
	private double bestScore;
	private boolean isRead;
	
	public ChallengeMessage(int uid1, int uid2, int qID, double bestscore) {
		super(uid1, uid2, new String());
		quizID = qID;
		bestScore = bestscore;
		isRead = false;
	}

	@Override
	public void addMessageToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable +" VALUES (?, ?, NOW(), ?, ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, fromUser);
			stmt.setInt(2, toUser);
			stmt.setInt(3, quizID);
			stmt.setDouble(4, bestScore);
			stmt.setBoolean(5, isRead);
			DBConnection.DBUpdate(stmt);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static ArrayList<ChallengeMessage> getMessagesByUserID(int userID) {
		ArrayList<ChallengeMessage> ChallengMessageQueue = new ArrayList<ChallengeMessage>();
		try {
			String statement = new String("SELECT * FROM " + DBTable +" WHERE uid1 = ? OR uid2 = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			stmt.setInt(2, userID);
			ResultSet rs = DBConnection.DBQuery(stmt);
			rs.beforeFirst();
			while(rs.next()) {
				ChallengeMessage cm = new ChallengeMessage(rs.getInt("uid1"), rs.getInt("uid2"), rs.getInt("qid"), rs.getDouble("bestScore"));
				cm.setMessageID(rs.getInt("mid"));
				cm.setTime(rs.getTimestamp("time"));
				ChallengMessageQueue.add(cm); 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return ChallengMessageQueue;
	}
}
