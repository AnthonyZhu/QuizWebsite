package quizweb.message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import quizweb.database.DBConnection;

public class NoteMessage extends Message {
	
	private static final String DBTable = "note";
	
	private DBConnection db; 
	
	public NoteMessage(Timestamp time, int uid1, int uid2, String c) {
		super(time, uid1, uid2, c);
		db = new DBConnection();
	}
	
	@Override
	public void addMessageToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable +" VALUES (?, ?, NOW(), ?)");
			PreparedStatement stmt = db.con.prepareStatement(statement);
			stmt.setInt(1, fromUser);
			stmt.setInt(2, toUser);
			stmt.setString(3, content);
			db.DBUpdate(stmt);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public ArrayList<Message> getMessagesByUserID(int userID) {
		ArrayList<Message> noteMessageQueue = new ArrayList<Message>();
		try {
			String statement = new String("SELECT * FROM " + DBTable +" WHERE uid1 = ? OR uid2 = ?");
			PreparedStatement stmt = db.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			stmt.setInt(2, userID);
			ResultSet rs = db.DBQuery(stmt);
			rs.beforeFirst();
			rs.next();
			while(rs.next()) {
				NoteMessage nm = new NoteMessage(
						rs.getTimestamp("time"), rs.getInt("uid1"), rs.getInt("uid2"), rs.getString("note"));
				noteMessageQueue.add(nm); 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return noteMessageQueue;
	}
}