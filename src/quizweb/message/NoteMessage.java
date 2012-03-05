package quizweb.message;

import java.util.HashMap;
import quizweb.database.DBConnection;
import quizweb.database.DBQueries;

public class NoteMessage extends Message {
	
	private static final String DBTable = "note";
	
	public NoteMessage(long time, int uid1, int uid2, String c) {
		super(time, uid1, uid2, c);
	}

	@Override
	public void addMessageToDB() {
		DBConnection db = new DBConnection();
		DBQueries query = new DBQueries();
		HashMap<String, String> valueMap = new HashMap<String, String>();
		query.InsertQueryByEntry(DBTable, valueMap);
	}
}