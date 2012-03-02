package quizweb.record;

import quizweb.User;

public class Record {
	public int recordID;
	static int maxRecordID = 0;
	
	public User user;
	public double timestamp;
	
	/**
	 * Assign a record ID for next record instance
	 * @return record ID assigned
	 */
	static private synchronized int getNextRecordID() {
		return maxRecordID++;
	}
	
	/**
	 * Comparator function that enable sorting of record by timestamp
	 * @param other
	 * @return relationship
	 */
	public int compareTo(Record other) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;
		if (this.timestamp > other.timestamp)
			return BEFORE;
		else if (this.timestamp == other.timestamp)
			return EQUAL;
		else 
			return AFTER;
	}
}
