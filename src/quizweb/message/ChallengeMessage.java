package quizweb.message;

import java.sql.Timestamp;

import quizweb.database.DBConnection;

public class ChallengeMessage extends Message {
	
	private static final String DBTable = "challenge";
	private static DBConnection db; 
	public int quizID;
	public double bestScore; 
	
	public ChallengeMessage(Timestamp time, int uid1, int uid2, int qID, double bestscore) {
		super(time, uid1, uid2, new String());
		quizID = qID;
		bestScore = bestscore;
		db = new DBConnection();
	}

	@Override
	public void addMessageToDB() {
		// TODO Auto-generated method stub
		
	}
	

}
