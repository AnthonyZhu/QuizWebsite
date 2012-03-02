package quizweb.message;

public class Message {
	private int messageID;
	static int maxMessageID = 0;
	
	private double timestamp;
	private int fromUserID;
	private int toUserID;
	private String content;
	
	/**
	 * Assign a message ID for a message instance
	 * @return assigned message ID
	 */
	static private synchronized int getNextMessgeID() {
		return maxMessageID++;
	}
}
