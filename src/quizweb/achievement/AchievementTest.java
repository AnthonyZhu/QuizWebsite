package quizweb.achievement;


import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import quizweb.*;

public class AchievementTest {

	@Before
	public void setUp() throws Exception {
		XMLReader.loadAllXML();
	}
	
	@Test
	public void testAchievement() {
		assertEquals(QuizCreatedAchievement.getAllAchievements().size(), 3);
		assertEquals(QuizTakenAchievement.getAllAchievements().size(), 3);
		assertEquals(HighScoreAchievement.getAllAchievements().size(), 3);
		assertEquals(PracticeAchievement.getAllAchievements().size(), 2);
		
		Achievement achievement = Achievement.getAchievementByName("Unbeatable");
		Achievement achievement2 = Achievement.getAchievementByID(achievement.id);
		assertEquals(achievement.description, achievement2.description);
		
		User stanford = User.getUserByUsername("stanford");
		User microsoft = User.getUserByUsername("microsoft");
		QuizCreatedAchievement.updateAchievement(stanford);
		QuizTakenAchievement.updateAchievement(stanford);
		QuizCreatedAchievement.updateAchievement(microsoft);
		QuizTakenAchievement.updateAchievement(microsoft);
		
		ArrayList<Achievement> stanfordAchievements = stanford.getAchievements();
		assertEquals(stanfordAchievements.size(), 5);
		assertEquals(stanfordAchievements.get(0).name, "Amateur Author");
		assertEquals(stanfordAchievements.get(1).name, "Amateur Quiz Taker");
		assertEquals(stanfordAchievements.get(2).name, "Prolific Quiz Taker");
		assertEquals(stanfordAchievements.get(3).name, "Unbeatable");
		assertEquals(stanfordAchievements.get(4).name, "Practice Makes Perfect");
		
		ArrayList<Achievement> microsoftAchievements = microsoft.getAchievements();
		assertEquals(microsoftAchievements.size(), 3);
		assertEquals(microsoftAchievements.get(0).name, "Amateur Author");
		assertEquals(microsoftAchievements.get(1).name, "Amateur Quiz Taker");
		assertEquals(microsoftAchievements.get(2).name, "Unbeatable");
		
		stanford.highScoreNumber = 1;
		stanford.practiceNumber = 100;
		stanford.updateCurrentUser();
		stanford = User.getUserByUserID(stanford.userID);
		HighScoreAchievement.updateAchievement(stanford);
		PracticeAchievement.updateAchievement(stanford);		
		stanfordAchievements = stanford.getAchievements();
		assertEquals(stanfordAchievements.size(), 7);
		assertEquals(stanfordAchievements.get(3).name, "I am the Greatest");
		assertEquals(stanfordAchievements.get(6).name, "Practice Machine");
		
	}

}
