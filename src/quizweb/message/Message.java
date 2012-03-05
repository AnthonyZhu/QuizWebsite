package quizweb.message;

import java.sql.Timestamp;

public abstract class Message {
	
	protected int messageID;
	protected Timestamp timestamp;
	protected int fromUser;
	protected int toUser;
	protected String content;
	
	public Message(int uid1, int uid2, String c) {
		fromUser = uid1;
		toUser = uid2;
		content = c;
	}
	
	public abstract void addMessageToDB();
	
	public int getMessageID() {
		return messageID;
	}
	
	public void setMessageID(int mid) {
		messageID = mid;
	}
	
	public Timestamp getTime() {
		return timestamp;
	}
	
	public void setTime(Timestamp time) {
		timestamp = time;
	}
	
	public int getSender() {
		return fromUser;
	}
	
	public int getReceiver() {
		return toUser;
	}
	
	public abstract String getContent();
	
//	public boolean equals(Message other) {
//		return messageID == other.messageID;
//	}
}
