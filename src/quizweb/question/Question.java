package quizweb.question;

import java.util.*;

public class Question implements Comparable<Question>{
	public int questionID;
	public Object question;
	public Object answer;
	public double score;
	public long timestamp;
	
	static final Class<?>[] questionClass = {ResponseQuestion.class, FillInBlankQuestion.class,
		MultiAnswerQuestion.class, PictureQuestion.class, MultiAnswerQuestion.class, 
		MultiChoiceMultiAnswerQuestion.class, MatchingQuestion.class
	};
	
	public Question(Object question, Object answer, double score) {
		this.question = question;
		this.answer = answer;
		this.score = score;
		this.timestamp = new Date().getTime();
	}
	
	public double getScore(Object userAnswer) {
		System.out.println("THIS LINE SHOULD NEVER BE REACHED");
		return -1;
	}
	
	public static ArrayList<Question> getQuestionsByQuizID(int quizID) {
		ArrayList<Question> questions = new ArrayList<Question>();
		questions.addAll(ResponseQuestion.getQuestionsByQuizID(quizID));
		questions.addAll(FillInBlankQuestion.getQuestionsByQuizID(quizID));
		questions.addAll(MultiAnswerQuestion.getQuestionsByQuizID(quizID));
		questions.addAll(PictureQuestion.getQuestionsByQuizID(quizID));
		questions.addAll(MultiAnswerQuestion.getQuestionsByQuizID(quizID));
		questions.addAll(MultiChoiceMultiAnswerQuestion.getQuestionsByQuizID(quizID));
		questions.addAll(MatchingQuestion.getQuestionsByQuizID(quizID));
		Collections.sort(questions);
		return questions;
	}
	
	public static ArrayList<String> getParsedStrings(String str) {
		return new ArrayList<String>(Arrays.asList(str.split("&&&")));
	}
	
	public static String getConcatedString(ArrayList<String> strings) {
		String retString = new String();
		for (int i = 0; i < strings.size(); i++) {
			if (i > 0)
				retString = retString + "&&&";
			retString = retString + strings.get(i);
		}
		return retString;
	}

	@Override
	public int compareTo(Question other) {
		if (this.timestamp < other.timestamp)
			return -1;
		else if (this.timestamp == other.timestamp)
			return 0;
		else
			return 1;
	}
}
