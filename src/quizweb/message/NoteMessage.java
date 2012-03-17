package quizweb.message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import quizweb.User;
import quizweb.XMLElement;
import quizweb.database.DBConnection;

public class NoteMessage extends Message {
	
	private static final String DBTable = "note";
	private boolean isRead;
	
	public NoteMessage(int uid1, int uid2, String c) {
		super(uid1, uid2, c);
		isRead = false;
	}
	
	public NoteMessage(int id, int uid1, int uid2, String c, Timestamp time, boolean isread) {
		super(id, uid1, uid2, c, time);
		isRead = isread;
	}
	
	@Override
	public void addMessageToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable +" " +
					"(uid1, uid2, time, note, isRead) VALUES (?, ?, NOW(), ?, ?)");
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
			String statement = new String("SELECT * FROM " + DBTable +" WHERE uid2 = ?");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, userID);
			ResultSet rs = DBConnection.DBQuery(stmt);
			rs.beforeFirst();
			while(rs.next()) {
				NoteMessage nm = new NoteMessage(
						rs.getInt("mid"), rs.getInt("uid1"), rs.getInt("uid2"), 
						rs.getString("note"), rs.getTimestamp("time"), rs.getBoolean("isRead"));
				noteMessageQueue.add(nm); 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return noteMessageQueue;
	}
	
	public static int getUnreadCount(User user) {
		int unreadCount = 0;
		try {
			String statement = new String("SELECT COUNT(mid) FROM " + DBTable + " WHERE uid2 = ? and isRead = false");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, user.userID);			
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				unreadCount = rs.getInt("COUNT(mid)");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return unreadCount;		
	}
	

	public static Message getNoteMessageByXMLElem(XMLElement root) {
		User toUser = null;
		User fromUser = null;
		String content = new String("This message has no content.");
		boolean isRead = false;
		if (root.attributeMap.containsKey("isread") && root.attributeMap.get("isread").equals("true")) 
			isRead = true;
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("to")) {
				toUser = User.getUserByUsername(elem.content);
			} else if (elem.name.equals("from")) {
				fromUser = User.getUserByUsername(elem.content);
			} else if (elem.name.equals("content")) {
				content  = elem.content;
			} else {
				System.out.println("Unrecognized field in challenge message " + elem.name);
			}
		}
		if (fromUser == null) {
			System.out.println("Unrecognized from user");
			return null;
		} else if (toUser == null) {
			System.out.println("Unrecognized to user");
			return null;
		}
		NoteMessage message = new NoteMessage(fromUser.userID, toUser.userID, content);
		message.isRead = isRead;
		return message;
	}
}
