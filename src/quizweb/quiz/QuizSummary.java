package quizweb.quiz;

import java.sql.Timestamp;
import java.util.*;

import quizweb.*;
import quizweb.record.*;

public class QuizSummary {
	Quiz quiz;
	public double totalScore;
	public double averageScore;
	public double averageTimespan;
	public int totalUser;
	
	public QuizSummary(Quiz quiz) {
		this.quiz = quiz;
		this.totalScore = quiz.getTotalScore();
		ArrayList<QuizTakenRecord> records = QuizTakenRecord.getQuizHistoryByQuizID(quiz.quizID, new Timestamp(0));
		
		this.averageScore = 0;
		this.averageTimespan = 0;
		this.totalUser = 0;
		HashSet<Integer> userSet = new HashSet<Integer>(); 
		for (int i = 0; i < records.size(); i++) {
			QuizTakenRecord record = records.get(i);
			this.averageScore += record.score;
			this.averageTimespan += record.timeSpan;
			userSet.add(new Integer(record.user.userID));
		}
		this.totalUser = userSet.size();
		this.averageScore /= records.size();
		this.averageTimespan /= records.size();
	}
}
