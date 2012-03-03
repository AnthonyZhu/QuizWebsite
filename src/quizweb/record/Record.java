package quizweb.record;

import quizweb.*;

public class Record implements Comparable<Record>{
	public int recordID;
	
	public User user;
	public long timestamp;
	
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