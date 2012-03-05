package quizweb.achievement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import quizweb.*;
import quizweb.database.DBConnection;
import quizweb.database.DBQueries;
import quizweb.record.AchievementRecord;

public class Achievement {
	private String description;
	private String name;
	private String fileDirectory;
	
	
	
	//public double threshold;
	//public static ArrayList<Achievement> allAchievements;
	
	
	
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
	
	public static ArrayList<Achievement> getAchievementsByUserID(int UserID) {
		// TODO initialize achievements from database;
		DBConnection db = new DBConnection();
		DBQueries query = new DBQueries();
		String statement = query.SelectQueryByColumn("achievement id", "Achievement record", "uid=\"" + UserID + "\"");
		ResultSet rs = db.DBQuery(statement);
		ArrayList<Achievement> allAchievements = new ArrayList<Achievement>();
		
		try{
			while(rs.next()){
				String stat = query.SelectAllQuery("Achievement", "achievement id=\"" + rs.getString("achievement id") + "\"");
				ResultSet rsAchievement = db.DBQuery(stat);
				while(rsAchievement.next()){
					String name = rsAchievement.getString("name");
					String file = rsAchievement.getString("file directory");
					String discription = rsAchievement.getString("discription");
					Achievement eachAchievement = new Achievement(name,file,discription);
					allAchievements.add(eachAchievement);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return allAchievements;
	}
	
	public static void addAchievement(int UserID, Achievement newAchievement){
		DBConnection db = new DBConnection();
		DBQueries query = new DBQueries();
		HashMap<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("name", newAchievement.getName());
		valueMap.put("file directory", newAchievement.getFileDirectory());
		valueMap.put("description", newAchievement.getDescription());
		String statement = query.InsertQueryByColumn("Achievement", valueMap);
		db.DBUpdate(statement);
	}
	
	
	
	public boolean isAccomplished(User user) {
		System.out.println("THIS LINE SHOULD NEVER BE REACHED");
		return false;
	}
	
	public boolean equals(Achievement other) {
		return name.equals(other.name);
	}
}
