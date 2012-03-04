package quizweb.message;

import java.util.ArrayList;

import quizweb.*;

public class Message {
	private int messageID;
	
	public long timestamp;
	public User fromUser;
	public User toUser;
	public String content;
	
	public static ArrayList<Message> getMessagesByUserID(int userID) {
		// TODO Get user message list from database
		return null;
	}
	
	public boolean equals(Message other) {
		return messageID == other.messageID;
	}
}
