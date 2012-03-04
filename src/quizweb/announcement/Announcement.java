package quizweb.announcement;

import java.util.*;

public class Announcement {
	public int announcementID;
	public String title;
	public String content; 
	public long timestamp; 
	
	public static ArrayList<Announcement> allAnnouncements;
	
	public Announcement(String title, String content) {
		this.title = title;
		this.content = content;
		this.timestamp = new Date().getTime();
		// TODO add the announcement to database
		// retrieve announcementID from database
	}
	
	public static void addAnnouncement(Announcement announcement) {
		allAnnouncements.add(announcement);
		// TODO
	}
	
	public boolean equals(Announcement other) {
		return announcementID == other.announcementID;
	}
}
