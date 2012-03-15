package quizweb.announcement;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import quizweb.*;


public class AnnouncementTest {
	@Before
	public void setUp() throws Exception {
		XMLReader.loadAllXML();
	}	
	
	@Test
	public void testAnnouncement() {
		ArrayList<Announcement> announcements = Announcement.getAllAnnouncements();
		assertEquals(announcements.size(), 6);
		for (int i = 0; i < announcements.size() - 1; i++) {
			assertTrue(announcements.get(i).title.compareTo(announcements.get(i+1).title) < 0);
			assertTrue(announcements.get(i).timestamp.compareTo(announcements.get(i+1).timestamp) <= 0);
		}
			
	}
}
