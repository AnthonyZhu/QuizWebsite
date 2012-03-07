package quizweb.achievement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import quizweb.*;
import quizweb.database.DBConnection;

public class Achievement {
	private String description;
	private String name;
	private String fileDirectory;	
	public double threshold;
	public static ArrayList<Achievement> allAchievements;
	private static final String DBTable1 = "achievement";
	private static final String DBTable2 = "achievement_history";
		
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
	
	//Create a new type of achievement into database
	public static void addNewAchievement(String name, String file, String description){
		try {
			String statement = new String("INSERT INTO " + DBTable1 +" VALUES (?, ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setString(1, name);
			stmt.setString(2, file);
			stmt.setString(3, description);
			DBConnection.DBUpdate(stmt);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//Insert achievement history when a user accomplish an achievement.
	public static void addAchievementHistory(int uid, int aid){
		try {
			String statement = new String("INSERT INTO " + DBTable2 +" VALUES (?, ?, NOW())");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, uid);
			stmt.setInt(2, aid);
			DBConnection.DBUpdate(stmt);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static ArrayList<Achievement> getAchievementsByUserID(int userID) {
		ArrayList<Achievement> AllAchievements = new ArrayList<Achievement>();
		try {
			String statement1 = new String("SELECT * FROM " + DBTable2 +" WHERE uid = ?");
			PreparedStatement stmt1 = DBConnection.con.prepareStatement(statement1);
			stmt1.setInt(1, userID);
			ResultSet rs1 = DBConnection.DBQuery(stmt1);
			rs1.beforeFirst();
			while(rs1.next()) {
				int aid = rs1.getInt("aid");
				String statement2 = new String("SELECT * FROM " + DBTable1 +" WHERE aid = ?");
				PreparedStatement stmt2 = DBConnection.con.prepareStatement(statement2);
				stmt2.setInt(1, aid);
				ResultSet rs2 = DBConnection.DBQuery(stmt2);
				Achievement a = new Achievement(rs2.getString("name"), rs2.getString("file"), rs2.getString("description"));
				AllAchievements.add(a); 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return AllAchievements;
	}
	
	public boolean isAccomplished(User user) {
		System.out.println("THIS LINE SHOULD NEVER BE REACHED");
		return false;
	}
	
	public boolean equals(Achievement other) {
		return name.equals(other.name);
	}
}
