package quizweb;


import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import quizweb.question.*;
import quizweb.quiz.QuizSummary;
import quizweb.record.QuizTakenRecord;

public class QuizTest {

	@Before
	public void setUp() throws Exception {
		XMLReader.loadAllXML();
	}
	
	@Test
	public void testQuiz1() {
		Quiz quiz = Quiz.getQuizByQuizName("Bunny Quiz");
		int quizID = quiz.quizID;
		// General info
		assertEquals(quiz.category, "Animals");
		assertEquals(quiz.creator.username, "stanford");		
		// Update 
		quiz.raterNumber = 100;
		quiz.totalRating = 356;
		quiz.updateCurrentQuiz();		
		quiz = Quiz.getQuizByQuizID(quizID);
		// Score
		assertEquals(quiz.getTotalScore(), 72, 1e-6);
		assertEquals(quiz.getBestScore(), 60, 1e-6);
		User stanford = User.getUserByUsername("stanford");
		User google = User.getUserByUsername("google");
		assertEquals(quiz.getUserBestScore(stanford), 60, 1e-6);
		assertEquals(quiz.getUserBestScore(google), 10, 1e-6);
		// Rating
		assertEquals(quiz.getQuizRating(), 3.56, 1e-6);
		for (int i = 0; i < 100; i++)
			quiz.addQuizRating(5);
		assertEquals(quiz.getQuizRating(), 4.28, 1e-6);		
		
		// Summary
		QuizSummary summary = quiz.getQuizSummary();
		assertEquals(summary.totalScore, 72, 1e-6);
		assertEquals(summary.averageScore, 33.333333, 1e-6);
		assertEquals(summary.bestScore, 60, 1e-6);
		assertEquals(summary.topUserSet.size(), 1);
		assertEquals(summary.averageTimespan, 108777.777777, 1e-6);
		assertEquals(summary.bestTimespan, 10000, 1e-6);
		assertEquals(summary.totalUser, 6);	
		assertEquals(summary.raterNumber, 200, 1e-6);
		assertEquals(summary.totalRating, 856, 1e-6);
		assertEquals(summary.averageRating, 4.28, 1e-6);
		
		// Questions
		ArrayList<Question> questions = quiz.getQuestions();
		assertEquals(questions.size(), 7);
	}
	
	@Test
	public void testQuiz2() {
		Quiz quiz = Quiz.getQuizByQuizName("Bunny Quiz");
		User stanford = User.getUserByUsername("stanford");

		// QuizTakenRecord
		ArrayList<QuizTakenRecord> allHistory = quiz.getAllHistory();
		assertEquals(allHistory.size(), 9);
		for (int i = 0; i < allHistory.size() - 1; i++) {
			assertTrue(allHistory.get(i).timestamp.compareTo(allHistory.get(i+1).timestamp) <= 0);
		}
		
		ArrayList<QuizTakenRecord> stanfordHistory = quiz.getUserHistory(stanford);
		assertEquals(stanfordHistory.size(), 4);
		QuizSummary stanfordSummary = quiz.getQuizSummary(stanford);
		assertEquals(stanfordSummary.averageScore, 155.0/4, 1e-6);
		assertEquals(stanfordSummary.bestScore, 60, 1e-6);
		assertEquals(stanfordSummary.averageTimespan, 789.0/4*1000, 1e-6);
		assertEquals(stanfordSummary.bestTimespan, 54000, 1e-6);
		
		ArrayList<QuizTakenRecord> topRecords = quiz.getAllTopRecord();
		assertEquals(topRecords.size(), 9);
		assertEquals(topRecords.get(0).score, 60, 1e-6);
		assertEquals(topRecords.get(1).score, 50, 1e-6);
		assertEquals(topRecords.get(2).score, 40, 1e-6);
		assertEquals(topRecords.get(3).timeSpan, 40000, 1e-6);
		assertEquals(topRecords.get(4).timeSpan, 80000, 1e-6);
		assertEquals(topRecords.get(5).timeSpan, 54000, 1e-6);
		assertEquals(topRecords.get(6).timeSpan, 55000, 1e-6);
		assertEquals(topRecords.get(8).timeSpan, 20000, 1e-6);
		
		ArrayList<QuizTakenRecord> stanfordTopRecords = quiz.getUserTopRecord(stanford);
		assertEquals(stanfordTopRecords.size(), 4);
		assertEquals(stanfordTopRecords.get(0).score, 60, 1e-6);
		assertEquals(stanfordTopRecords.get(1).score, 35, 1e-6);
		assertEquals(stanfordTopRecords.get(2).score, 30, 1e-6);
		assertEquals(stanfordTopRecords.get(3).timeSpan, 55000, 1e-6);		
	}

}
