package quizweb.quiz;

import java.sql.*;
import java.util.*;

import quizweb.*;
import quizweb.record.*;

public class QuizSummary {
	Quiz quiz;
	User user;
	public double totalScore;
	public double averageScore;
	public double bestScore;
	public HashSet<User> topUserSet;
	
	public double bestTimespan;
	public double averageTimespan;
	public int totalUser;
	
	public double raterNumber;
	public double totalRating;
	public double averageRating;
	
	public QuizSummary(Quiz myQuiz) {
		quiz = myQuiz;
		user = null;
		totalScore = quiz.getTotalScore();
		averageScore = 0;
		bestScore = 0;
		topUserSet = new HashSet<User>();
		
		averageTimespan = 0;
		bestTimespan = 1e9;
		totalUser = 0;
		
		raterNumber = 0;
		totalRating = 0;
		averageRating = 0;
		
		ArrayList<QuizTakenRecord> records = QuizTakenRecord.getQuizHistoryByQuizID(quiz.quizID, new Timestamp(0));
		if (records.size() == 0)
			return;
		
		HashSet<Integer> userSet = new HashSet<Integer>(); 
		for (int i = 0; i < records.size(); i++) {
			QuizTakenRecord record = records.get(i);
			averageScore += record.score;
			if (record.score > bestScore) {
				bestScore = record.score;
				topUserSet.clear();
				topUserSet.add(record.user);
			} else if (record.score == bestScore){
				topUserSet.add(record.user);
			}
			averageTimespan += record.timeSpan;
			if (bestTimespan > record.timeSpan) {
				bestTimespan = record.timeSpan;
			}
			userSet.add(new Integer(record.user.userID));
		}
		totalUser = userSet.size();
		averageScore /= records.size();
		averageTimespan /= records.size();
		
		quiz = Quiz.getQuizByQuizID(quiz.quizID);
		raterNumber = quiz.raterNumber;
		totalRating = quiz.totalRating;
		if (raterNumber > 0)
			averageRating = quiz.totalRating / quiz.raterNumber;
	}
	
	public QuizSummary(Quiz myQuiz, User myUser) {
		quiz = myQuiz;
		user = myUser;
		totalScore = quiz.getTotalScore();
		averageScore = 0;
		bestScore = 0;
		topUserSet = new HashSet<User>();
		topUserSet.add(user);
		
		averageTimespan = 0;
		bestTimespan = 1e9;
		totalUser = 1;
		
		raterNumber = 0;
		totalRating = 0;
		averageRating = 0;
		
		ArrayList<QuizTakenRecord> records = QuizTakenRecord.getQuizHistoryByQuizIDUserID(quiz.quizID, user.userID);
		if (records.size() == 0)
			return;
		
		for (int i = 0; i < records.size(); i++) {
			QuizTakenRecord record = records.get(i);
			averageScore += record.score;
			if (record.score > bestScore) {
				bestScore = record.score;
			}
			averageTimespan += record.timeSpan;
			if (bestTimespan > record.timeSpan) {
				bestTimespan = record.timeSpan;
			}
		}
		averageScore /= records.size();
		averageTimespan /= records.size();
	}
}
