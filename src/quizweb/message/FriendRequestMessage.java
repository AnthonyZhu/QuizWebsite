package quizweb.message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import quizweb.database.DBConnection;

public class FriendRequestMessage extends Message {
	
	private static final String DBTable = "friend_request";
	private boolean isConfirmed;
	private boolean isRejected;

	public FriendRequestMessage(int uid1, int uid2) {
		super(uid1, uid2, new String());
		isConfirmed = false;
		isRejected = false;
	}
	
	public FriendRequestMessage(int id, int uid1, int uid2, Timestamp time, boolean isconfirmed, boolean isrejected) {
		super(uid1, uid2, new String());
		isConfirmed = isconfirmed;
		isRejected = isrejected;
	}
	
	//static public void removeFriendRequest(Message)

	@Override
	public void addMessageToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable +"" +
					"(uid1, uid2, time, isConfirmed, isRejected) VALUES (?, ?, NOW(), ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, fromUser);
			stmt.setInt(2, toUser);
			stmt.setBoolean(3, isConfirmed);
			stmt.setBoolean(4, isRejected);
			DBConnection.DBUpdate(stmt);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static ArrayList<FriendRequestMessage> getMessagesByUserID(int userID) {
		ArrayList<FriendRequestMessage> FriendRequestMessageQueue = new ArrayList<FriendRequestMessage>();
		try {
			String statement = new String("SELECT * FROM " + DBTable +" WHERE uid1 = ? OR uid2 = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			stmt.setInt(2, userID);
			ResultSet rs = DBConnection.DBQuery(stmt);
			rs.beforeFirst();
			while(rs.next()) {
				FriendRequestMessage fm = new FriendRequestMessage(
						rs.getInt("mid"), rs.getInt("uid1"), rs.getInt("uid2"), 
						rs.getTimestamp("time"), rs.getBoolean("isConfirmed"), rs.getBoolean("isRejected"));
				FriendRequestMessageQueue.add(fm); 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return FriendRequestMessageQueue;
	}
}
