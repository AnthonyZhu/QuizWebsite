package quizweb.record;

import java.util.Comparator;

public class QuizTakenRecordSortByTimespan implements Comparator<QuizTakenRecord> {

	@Override
	public int compare(QuizTakenRecord record1, QuizTakenRecord record2) {
		if (record1.timeSpan > record2.timeSpan)
			return -1;
		else if (record1.timeSpan < record2.timeSpan)
			return 1;
		else {
			if (record1.score > record2.score)
				return -1;
			else if (record1.score < record2.score)
				return 1;
			else 
				return 0;
		}
	}

}
