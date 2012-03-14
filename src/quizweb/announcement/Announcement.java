package quizweb.announcement;

import java.sql.*;
import java.util.*;
import java.util.Date;

import quizweb.XMLElement;
import quizweb.database.DBConnection;

public class Announcement {
	public int announcementID;
	public String title;
	public String content; 
	public Timestamp timestamp; 
	
	public static final String DBTable = "announcement";
	
	public static ArrayList<Announcement> allAnnouncements = getAllAnnouncements();
	
	public Announcement(String title, String content) {
		this.title = title;
		this.content = content;
		allAnnouncements.add(this);
	}
	
	public Announcement(int announcementID, String title, String content, Timestamp timestamp) {
		this.announcementID = announcementID;
		this.title = title;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	public void addAnnouncementToDB() {
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (title, content, time)" 
					+ " VALUES (?, ?, NOW())");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"aid"});
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			announcementID = rs.getInt("GENERATED_KEY");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static ArrayList<Announcement> getAllAnnouncements() {
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		String statement = new String("SELECT * FROM " + DBTable);
		PreparedStatement stmt;
		try {
			stmt = DBConnection.con.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Announcement announcement = new Announcement(rs.getInt("aid"), 
						rs.getString("title"), rs.getString("content"), rs.getTimestamp("time"));
				announcements.add(announcement);
			}
			rs.close();
			Collections.sort(announcements, new announcementSortByTime());
			return announcements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Announcement getAnnouncementByXMLElem(XMLElement root) {
		String title = new String("Untitled");
		String content = new String("This announcement has no content.");
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("title")) {
				title = elem.content;
			} else if (elem.name.equals("content")) {
				content = elem.content;
			} else {
				System.out.println("Unrecognized announcement field " + elem.name);
			}
		}
		return new Announcement(title, content);
	}
	
	public boolean equals(Announcement other) {
		return announcementID == other.announcementID;
	}	
}
