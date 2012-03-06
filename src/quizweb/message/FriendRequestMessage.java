package quizweb.message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import quizweb.database.DBConnection;

public class FriendRequestMessage extends Message {
	
	private static final String DBTable = "friend_request";
	
	public FriendRequestMessage(int uid1, int uid2) {
		super(uid1, uid2, new String());
	}
	//static public void removeFriendRequest(Message)

	@Override
	public void addMessageToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable +" VALUES (?, ?, NOW())");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, fromUser);
			stmt.setInt(2, toUser);
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
				FriendRequestMessage fm = new FriendRequestMessage(rs.getInt("uid1"), rs.getInt("uid2"));
				fm.setMessageID(rs.getInt("mid"));
				fm.setTime(rs.getTimestamp("time"));
				FriendRequestMessageQueue.add(fm); 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return FriendRequestMessageQueue;
	}
}
