package quizweb.message;

import java.util.ArrayList;

public abstract class Message {
	
	protected int messageID;
	protected long timestamp;
	protected int fromUser;
	protected int toUser;
	protected String content;
	
	public Message(long time, int uid1, int uid2, String c) {
		timestamp = time;
		fromUser = uid1;
		toUser = uid2;
		content = c;
	}
	
	public static ArrayList<Message> getMessagesByUserID(int userID) {
		// TODO Get user message list from database
		return null;
	}
	
	public abstract void addMessageToDB();
	
//	public boolean equals(Message other) {
//		return messageID == other.messageID;
//	}
}
