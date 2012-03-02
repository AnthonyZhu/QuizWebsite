package quizweb.question;

import java.util.*;


public class FillInBlankQuestion extends Question {
	public String			question;
	public ArrayList<String>	answer;
	
	@Override
	public double getScore(ArrayList<String> userAnswer) {
		// TODO 
		assert(userAnswer.size() == 1);
		
		return 0;
	}

}
