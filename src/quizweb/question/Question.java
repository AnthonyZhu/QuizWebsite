package quizweb.question;

import java.util.*;

import quizweb.Quiz;
import quizweb.XMLElement;

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
	
	public void addQustionToDB() {
		System.out.println("THIS LINE SHOULD NEVER BE REACHED");		
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

	public static Question getQuestionByXMLElem(XMLElement root, Quiz quiz, int pos) {
		String type = root.attributeMap.get("type");		
		if (type.equals("question-response")) {
			return ResponseQuestion.getResponseQuestionByXMLElem(root, quiz, pos);
		} else if (type.equals("fill-in-blank")) {
			return FillInBlankQuestion.getFillInBlankQuestionByXMLElem(root, quiz, pos);
		} else if (type.equals("multiple-choice")) {
			return MultipleChoiceQuestion.getMultipleChoiceQuestionByXMLElem(root, quiz, pos);
		} else if (type.equals("picture-response")) {
			return PictureQuestion.getPictureQuestionByXMLElem(root, quiz, pos);
		} else if (type.equals("matching-question")) {
			return MatchingQuestion.getMatchingQuestionByXMLElem(root, quiz, pos);
		} else if (type.equals("multi-answer-question")) {
			return MultiAnswerQuestion.getMultiAnswerQuestionByXMLElem(root, quiz, pos);
		} else if (type.equals("multi-choice-multi-answer-question")) {
			return MultiChoiceMultiAnswerQuestion.getMultiChoiceMultiAnswerQuestionByXMLElem(root, quiz, pos);
		} else {
			System.out.println("Question Type " + type + " Not Recognized.");
			return null;
		}
	}

	public static ArrayList<String> getAnswerListByXMLElem(XMLElement root) {
		ArrayList<String> answerList = new ArrayList<String>();
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (!elem.name.equals("answer")) 
				System.out.println("Answer list unexpected name " + elem.name);
			answerList.add(elem.content);
		}
		return answerList;
	}
	
	public static ArrayList<String> getQuestionListByXMLElem(XMLElement root) {
		ArrayList<String> questionList = new ArrayList<String>();
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (!elem.name.equals("query")) 
				System.out.println("Question list unexpected name " + elem.name);
			questionList.add(elem.content);
		}
		return questionList;
	}	
}
