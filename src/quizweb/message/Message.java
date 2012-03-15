package quizweb.message;

import java.sql.Timestamp;

import quizweb.XMLElement;

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

	public static Message getMessageByXMLElem(XMLElement root) {
		if (!root.attributeMap.containsKey("type")) {
			System.out.println("Message type must have a 'type' field");
			return null;
		}
		
		String type = root.attributeMap.get("type");
		if (type.equals("challenge")) {
			return ChallengeMessage.getChallengeMessageByXMLElem(root);
		} else if (type.equals("friend-request")) {
			return FriendRequestMessage.getFriendRequestByXMLElem(root);
		} else if (type.equals("note")) {
			return NoteMessage.getNoteMessageByXMLElem(root);
		} else {
			System.out.println("Unrecognized message type " + type);
			return null;
		}
	}
	
//	public boolean equals(Message other) {
//		return messageID == other.messageID;
//	}
}
