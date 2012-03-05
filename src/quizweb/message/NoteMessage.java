package quizweb.message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import quizweb.database.DBConnection;

public class NoteMessage extends Message {
	
	private static final String DBTable = "note";
	private boolean isRead;
	
	public NoteMessage(int uid1, int uid2, String c) {
		super(uid1, uid2, c);
		isRead = false;
	}
	
	@Override
	public void addMessageToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable +" VALUES (?, ?, NOW(), ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, fromUser);
			stmt.setInt(2, toUser);
			stmt.setString(3, content);
			stmt.setBoolean(4, isRead);
			DBConnection.DBUpdate(stmt);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static ArrayList<NoteMessage> getMessagesByUserID(int userID) {
		ArrayList<NoteMessage> noteMessageQueue = new ArrayList<NoteMessage>();
		try {
			String statement = new String("SELECT * FROM " + DBTable +" WHERE uid1 = ? OR uid2 = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			stmt.setInt(2, userID);
			ResultSet rs = DBConnection.DBQuery(stmt);
			rs.beforeFirst();
			while(rs.next()) {
				NoteMessage nm = new NoteMessage(rs.getInt("uid1"), rs.getInt("uid2"), rs.getString("note"));
				nm.setMessageID(rs.getInt("mid"));
				nm.setTime(rs.getTimestamp("time"));
				noteMessageQueue.add(nm); 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return noteMessageQueue;
	}

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}
}
