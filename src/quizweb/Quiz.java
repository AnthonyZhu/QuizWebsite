package quizweb;

import java.util.*;

import quizweb.question.*;
import quizweb.record.*;

public class Quiz {
	public int quizID;
	public String quizURL;	
	public String description;
	public User creator; 
	
	public boolean isRandom;
	public boolean opFeedback;
	public boolean opPractice;	
	
	// Statistics
	public int userNumber;	
	public int raterNumber;
	public double totalRating;
	
	// Lazy read
	public ArrayList<Question> questions;
	public ArrayList<QuizTakenRecord> history;
	public ArrayList<QuizTakenRecord> topRecord;
	
	public ArrayList<Question> getQuestions() {
		if (questions == null) {
			questions = Question.getQuestionsByQuizID(quizID);
			return questions;
		} else {
			return questions;
		}
	}
	
	public ArrayList<QuizTakenRecord> getHistory() {
		if (history == null) {
			history = QuizTakenRecord.getQuizHistoryByQuizID(quizID);
			return history;
		} else {
			return history;
		}
	}

	public ArrayList<QuizTakenRecord> getTopRecord() {
		if (topRecord == null) {
			topRecord = new ArrayList<QuizTakenRecord>(getHistory());
			Collections.sort(topRecord);
			return topRecord;
		} else {
			return topRecord;
		}
	}
	
	public double getTotalScore() {
		double sum = 0;
		for (int i = 0; i < getQuestions().size(); i++) {
			sum += getQuestions().get(i).score;
		}
		return sum;
	}
	
	/**
	 * Compute the score of the quiz
	 * @return score
	 */
	public double getScore(ArrayList<Object> userAnswers) {
		double sum = 0;
		for (int i = 0; i < questions.size(); i++) {
			sum += questions.get(i).getScore(userAnswers.get(i));
		}
		return sum;
	}	
	
	/**
	 * Get best score for a given user
	 * @param user
	 * @return
	 */
	public double getBestScore(User user) {
		// TODO
		return 0;
	}
	
	public boolean equals(Quiz other) {
		return quizID == other.quizID;
	}
}
