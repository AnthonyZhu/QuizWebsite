package quizweb.question;

import java.util.*;


public class MultipleChoiceQuestion extends Question {
//	Cast Type Summary:
//	ArrayList<String>	question;
//	String				answer;
//	String				userAnswer;
	
	@Override
	public double getScore(Object userAnswer) {
		String ans = (String) userAnswer;
		String trueAns = (String) answer;
		if (ans.equals(trueAns)) 
			return score;
		return 0;
	}

}
