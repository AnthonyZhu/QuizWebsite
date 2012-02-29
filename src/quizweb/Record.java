package quizweb;

public class Record {
	public int recordID;
	public double timestamp;
	
	static int maxRecordID = 0;
	
	/**
	 * Assign a record ID for next record instance
	 * @return
	 */
	static private synchronized int getNextRecordID() {
		// TODO
		return 0;
	}
}
