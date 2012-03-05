package quizweb.message;

import java.sql.Timestamp;

public abstract class Message {
	
	protected int messageID;
	protected Timestamp timestamp;
	protected int fromUser;
	protected int toUser;
	protected String content;
	
	public Message(Timestamp time, int uid1, int uid2, String c) {
		timestamp = time;
		fromUser = uid1;
		toUser = uid2;
		content = c;
	}
	
	public String getContent() {
		return content;
	}
	
	public int getSender() {
		return fromUser;
	}
	
	public int getReceiver() {
		return toUser;
	}
	
	public abstract void addMessageToDB();
	
//	public boolean equals(Message other) {
//		return messageID == other.messageID;
//	}
}
