package quizweb;

import java.util.*;

public class MultipleChoiceQuestion extends Question {
	public ArrayList<String>	question;
	public String				answer;
	
	
	
	@Override
	public double getScore(ArrayList<String> userAnswer) {
		// TODO Auto-generated method stub
		assert(userAnswer.size() == 1);
		
		return 0;
	}

}
