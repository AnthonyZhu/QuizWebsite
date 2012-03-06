package quizweb.announcement;

import java.sql.*;
import java.util.*;
import java.util.Date;

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
		this.timestamp = new Timestamp(new Date().getTime());	
		// add to database
		try {
			String statement = new String("INSERT INTO " + DBTable 
					+ " (title, content)" 
					+ " VALUES (?, ?)");
			PreparedStatement stmt = DBConnection.con.prepareStatement(statement, new String[] {"aid"});
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			announcementID = rs.getInt("aid");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		allAnnouncements.add(this);
	}
	
	public Announcement(int announcementID, String title, String content, Timestamp timestamp) {
		this.announcementID = announcementID;
		this.title = title;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	private static ArrayList<Announcement> getAllAnnouncements() {
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
	
	public boolean equals(Announcement other) {
		return announcementID == other.announcementID;
	}
}
