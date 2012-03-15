package quizweb.record;

import java.sql.*;

import quizweb.*;

public class Record {
	public int recordID;
	public User user;
	public Timestamp timestamp;
	
	public void addRecordToDB() {
		System.out.println("This line should never be reached");
	}

	public static Record getRecordByXMLElem(XMLElement root) {
		if (!root.attributeMap.containsKey("type")) {
			System.out.println("Record should contain attribute 'type'");
			return null;
		}
		String type = root.attributeMap.get("type");
		if (type.equals("quiz-taken")) {
			return QuizTakenRecord.getQuizTakenRecordByXMLElem(root);
		} else if (type.equals("quiz-created")) {
			return QuizCreatedRecord.getQuizCreatedRecordByXMLElem(root);
		} else if (type.equals("achievement")) {
			return AchievementRecord.getAchievementRecordByXMLElem(root);
		} else {
			System.out.println("Unrecognized record type " + type);
			return null;
		}		
	}
}
