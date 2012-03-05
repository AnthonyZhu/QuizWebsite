package quizweb.question;

import java.util.*;

public class QuestionSortByPosition implements Comparator<Question> {

	@Override
	public int compare(Question question1, Question question2) {
		if (question1.position < question2.position)
			return -1;
		else if (question1.position > question2.position)
			return 1;
		else 
			return 0;
	}
}
