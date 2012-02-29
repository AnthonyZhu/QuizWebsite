package quizweb;

public class Message {
	public int messageID;
	public double timestamp;
	public User fromUser;
	public User toUser;
	public String content;
	
	static int maxMessageID = 0;
	
	
	/**
	 * Assign a message ID for a message instance
	 * @return
	 */
	static private synchronized int getNextMessgeID() {
		// TODO
		return 0;
	}
}
