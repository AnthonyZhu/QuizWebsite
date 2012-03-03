package quizweb.question;

import java.util.*;


public class MatchingQuestion extends Question {
//	Cast Type Summary:
//	ArrayList<String>	question;
//	ArrayList<String>	answer;
//	ArrayList<String>	userAnswer;

	@SuppressWarnings("unchecked")
	@Override
	public double getScore(Object userAnswer) {
		ArrayList<String> ans = (ArrayList<String>) userAnswer;
		ArrayList<String> trueAns = (ArrayList<String>) answer;
		ArrayList<String> ques = (ArrayList<String>) question;			
		int matches = 0;
		for (int i = 0; i < ans.size(); i++)
			if (ans.get(i).equals(trueAns.get(i))) 
					matches++;
		return score * matches / ques.size();
	}

}
