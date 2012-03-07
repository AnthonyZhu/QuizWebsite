package quizweb.message;

import java.sql.Timestamp;

public abstract class Message {
	
	public int messageID;
	public Timestamp timestamp;
	public int fromUser;
	public int toUser;
	public String content;
	
	public Message(int uid1, int uid2, String c) {
		fromUser = uid1;
		toUser = uid2;
		content = c;
	}
	
	public Message(int id, int uid1, int uid2, String c, Timestamp time) {
		messageID = id;
		fromUser = uid1;
		toUser = uid2;
		content = c;
		timestamp = time;
	}
	
	public abstract void addMessageToDB();
	
//	public boolean equals(Message other) {
//		return messageID == other.messageID;
//	}
}
