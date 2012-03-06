package quizweb.question;

import java.util.*;

public class Question {
	public int questionID;
	public int quizID;
	public int position;
	public Object question;
	public Object answer;
	public double score;
		
	static final Class<?>[] questionClass = {ResponseQuestion.class, FillInBlankQuestion.class,
		MultiAnswerQuestion.class, PictureQuestion.class, MultiAnswerQuestion.class, 
		MultiChoiceMultiAnswerQuestion.class, MatchingQuestion.class
	};
	
	public Question(int quizID, int position, Object question, Object answer, double score) {
		this.quizID = quizID;
		this.position = position;
		this.question = question;
		this.answer = answer;
		this.score = score;
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
		Collections.sort(questions, new QuestionSortByPosition());
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
}
