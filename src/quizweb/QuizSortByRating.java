package quizweb;

import java.util.Comparator;

public class QuizSortByRating implements Comparator<Quiz> {

	@Override
	public int compare(Quiz quiz1, Quiz quiz2) {
		if (quiz1.raterNumber == 0)
			return 1;
		if (quiz2.raterNumber == 0)
			return -1;
		if (quiz1.totalRating / quiz1.raterNumber > quiz2.totalRating / quiz2.raterNumber)
			return -1;
		else 
			return 1;
	}

}
