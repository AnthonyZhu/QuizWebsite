package quizweb;


import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import quizweb.record.*;

public class UserTest {

	@Before
	public void setUp() throws Exception {
		XMLReader.loadAllXML();
	}
	
	@Test
	public void testUser1() {
		User stanford = User.getUserByUsername("stanford");
		ArrayList<User> stanfordFriends = stanford.getFriendList();
		assertEquals(stanfordFriends.size(), 3);
		for (int i = 0; i < stanfordFriends.size(); i++) {
			assertTrue(stanfordFriends.get(i).username.equals("berkeley") ||
					stanfordFriends.get(i).username.equals("mit") ||
					stanfordFriends.get(i).username.equals("google"));
		}
		
		ArrayList<QuizTakenRecord> takenRecords = stanford.getQuizHistory();
		assertEquals(takenRecords.size(), 5);
		ArrayList<QuizCreatedRecord> createdRecords = stanford.getCreatedQuiz();
		assertEquals(createdRecords.size(), 1);
		
		User google = User.getUserByUsername("google");
		User.removeUser(google);
		google = User.getUserByUserID(google.userID);
		assertTrue(google.isDead);
		
		User facebook = User.getUserByUsername("facebook");
		facebook.addFriend(stanford);
		google.addFriend(stanford);
		stanfordFriends = stanford.getFriendList();
		assertEquals(stanfordFriends.size(), 4);
		assertEquals(stanford.isFriend(facebook), User.IS_FRIEND);
		
		stanford.removeFriend(google);
		ArrayList<User> googleFriends = google.getFriendList();
		assertEquals(googleFriends.size(), 2);
		assertEquals(stanford.isFriend(google), User.NOT_FRIEND);
		
		User.promoteUser(facebook);
		assertEquals(User.getUserByUsername("facebook").permission, User.IS_ADMIN);
		
	}

}
