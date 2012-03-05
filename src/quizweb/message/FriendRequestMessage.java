package quizweb.message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import quizweb.database.DBConnection;

public class FriendRequestMessage extends Message {
	
	private static final String DBTable = "friend_request";
	private static DBConnection db; 
	
	public FriendRequestMessage(Timestamp time, int uid1, int uid2) {
		super(time, uid1, uid2, new String());
		db = new DBConnection();
	}
	//static public void removeFriendRequest(Message)

	@Override
	public void addMessageToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable +" VALUES (?, ?, NOW())");
			PreparedStatement stmt = db.con.prepareStatement(statement);
			stmt.setInt(1, fromUser);
			stmt.setInt(2, toUser);
			db.DBUpdate(stmt);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static ArrayList<FriendRequestMessage> getMessagesByUserID(int userID) {
		ArrayList<FriendRequestMessage> FriendRequestMessageQueue = new ArrayList<FriendRequestMessage>();
		try {
			String statement = new String("SELECT * FROM " + DBTable +" WHERE uid1 = ? OR uid2 = ?");
			PreparedStatement stmt = db.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			stmt.setInt(2, userID);
			ResultSet rs = db.DBQuery(stmt);
			rs.beforeFirst();
			while(rs.next()) {
				FriendRequestMessage fm = new FriendRequestMessage(
						rs.getTimestamp("time"), rs.getInt("uid1"), rs.getInt("uid2"));
				FriendRequestMessageQueue.add(fm); 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return FriendRequestMessageQueue;
	}
}
