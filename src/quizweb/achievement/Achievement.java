package quizweb.achievement;

import java.sql.*;

import quizweb.XMLElement;
import quizweb.database.*;

public class Achievement {
	public int id;
	public int type;
	public String name;
	public String url;
	public String description;
	public int threshold;
	
	static final String DBTable = "achievement";

	public static final int ALL_TYPE = 0;
	public static final int QUIZ_TAKEN_TYPE = 1;
	public static final int QUIZ_CREATED_TYPE = 2;
	public static final int PRACTICE_TYPE = 3;
	public static final int HIGHSCORE_TYPE = 4;
	
	public Achievement(String name, String url, String description, int threshold){
		this.name = name;
		this.url = url;
		this.description = description;
		this.threshold = threshold;
	}
	
	public Achievement(int id, int type, String name, String url, String description, int threshold){
		this.id = id;
		this.type = type;
		this.name = name;
		this.url = url;
		this.description = description;
		this.threshold = threshold;
	}
		
	public void addAchievementToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (type, name, url, description, threshold)" 
					+ " VALUES (?, ?, ?, ?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"aid"});
			stmt.setInt(1, type);
			stmt.setString(2, name);
			stmt.setString(3, url);
			stmt.setString(4, description);
			stmt.setInt(5, threshold);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt("GENERATED_KEY");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Achievement getAchievementByID(int aid) {
		String statement = new String("SELECT * FROM " + DBTable + " WHERE aid = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setInt(1, aid);
			ResultSet rs = stmt.executeQuery();
			Achievement achievement = null;
			if (rs.next()) {
				achievement = new Achievement(rs.getInt("aid"), rs.getInt("type"), rs.getString("name"), 
						rs.getString("url"), rs.getString("description"), rs.getInt("threshold"));				
			} else {
				System.out.println("Achievement " + aid + " NOT FOUND");
			}			
			rs.close();
			return achievement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Achievement getAchievementByName(String name) {
		name = name.trim();
		String statement = new String("SELECT * FROM " + DBTable + " WHERE name = ?");
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			Achievement achievement = null;
			if (rs.next()) {
				achievement = new Achievement(rs.getInt("aid"), rs.getInt("type"), rs.getString("name"), 
						rs.getString("url"), rs.getString("description"), rs.getInt("threshold"));				
			} else {
				System.out.println("Achievement " + name + " NOT FOUND");
			}
			rs.close();
			return achievement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	

	public static Achievement getAchievementByXMLElem(XMLElement root) {
		String name = new String("Name Missing");
		String url = new String("URL not Specified");
		String description = new String("");
		int threshold = -1;
		if (!root.attributeMap.containsKey("type")) {
			System.out.println("Achievement type not specified");
			return null;
		}
		String type = root.attributeMap.get("type");
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("name")) {
				name = elem.content;
			} else if (elem.name.equals("url")) {
				url = elem.content;
			} else if (elem.name.equals("description")) {
				description = elem.content;
			} else if (elem.name.equals("threshold")) {
				threshold = Integer.parseInt(elem.content);
			} else {
				System.out.println("Unrecognized achievement field " + elem.name);
			}
		}
		if (type.equals("quiz-created")) {
			return new QuizCreatedAchievement(name, url, description, threshold);
		} else if (type.equals("quiz-taken")) {
			return new QuizTakenAchievement(name, url, description, threshold);
		} else if (type.equals("highscore")) {
			return new HighScoreAchievement(name, url, description, threshold);
		} else if (type.equals("practice")) {
			return new PracticeAchievement(name, url, description, threshold);
		} else {
			System.out.println("Unrecognized achievement type " + type);
			return null;
		}
	}
	
	public boolean equals(Achievement other) {
		return name.equals(other.name);
	}
}
