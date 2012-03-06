package quizweb.achievement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import quizweb.*;
import quizweb.database.DBConnection;
import quizweb.record.AchievementRecord;

public abstract class Achievement {
	//1->QuizCreated
	//2->QuizTaken
	//3->Practice
	//4->HighScore
	private String name;
	private String fileDirectory;
	private String description;
	private static final String DBTable = "achievement";
	
	public double threshold;
	public static ArrayList<Achievement> allAchievements;
	
	
	
	public Achievement(String name, String fileDirectory, String discription){
		this.name = name;
		this.fileDirectory = fileDirectory;
		this.description = discription;
	}
	
	public String getName(){
		return name;
	}
	
	public String getFileDirectory(){
		return fileDirectory;
	}
	
	public String getDescription(){
		return description;
	}

//		// TODO initialize achievements from database;
//		String statement = query.SelectQueryByColumn("achievement id", "Achievement record", "uid=\"" + UserID + "\"");
//		ResultSet rs = db.DBQuery(statement);
//		ArrayList<Achievement> allAchievements = new ArrayList<Achievement>();
//		
//		try{
//			while(rs.next()){
//				String stat = query.SelectAllQuery("Achievement", "achievement id=\"" + rs.getString("achievement id") + "\"");
//				ResultSet rsAchievement = db.DBQuery(stat);
//				while(rsAchievement.next()){
//					String name = rsAchievement.getString("name");
//					String file = rsAchievement.getString("file directory");
//					String discription = rsAchievement.getString("discription");
//					Achievement eachAchievement = new Achievement(name,file,discription);
//					allAchievements.add(eachAchievement);
//				}
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return allAchievements;
//	}
	
	public static void addAchievement(int UserID, String name, String file, String description){
		try {
			String statement = new String("INSERT INTO " + DBTable +" VALUES (?, ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setString(1, name);
			stmt.setString(2, file);
			stmt.setString(3, description);
			DBConnection.DBUpdate(stmt);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	public boolean isAccomplished(User user) {
		System.out.println("THIS LINE SHOULD NEVER BE REACHED");
		return false;
	}
	
	public boolean equals(Achievement other) {
		return name.equals(other.name);
	}
}
