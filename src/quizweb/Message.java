package quizweb;

public class Message {
	private int messageID;
	private double timestamp;
	private int fromUserID;
	private int toUserID;
	private String content;
	
	static int maxMessageID = 0;
	
	public Message(int messageID){
		this.messageID = messageID;
	}
	
	public double getTimeStamp(){
		//TODO
		return timestamp;
	}
	
	public int getFromUserID(){
		//TODO
		return fromUserID;
	}
	
	public int getToUserID(){
		//TODO
		return toUserID;
	}
	
	public String getContent(){
		//TODO
		return content;
	}
	
	/**
	 * Assign a message ID for a message instance
	 * @return
	 */
	static private synchronized int getNextMessgeID() {
		// TODO
		return 0;
	}
}
