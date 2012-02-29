package quizweb;

import java.util.*;

public class ResponseQuestion extends Question {
	String			question;
	ArrayList<String>	answer;
	
	@Override
	public double getScore(ArrayList<String> userAnswer) {
		// TODO 
		assert(userAnswer.size() == 1);
		
		
		return 0;
	}

}
