package quizweb.record;

import java.util.*;

public class QuizTakenRecordSortByTime implements Comparator<QuizTakenRecord> {

	@Override
	public int compare(QuizTakenRecord record1, QuizTakenRecord record2) {
		if (record1.timestamp.getTime() > record2.timestamp.getTime())
			return -1;
		else if (record1.timestamp.getTime() < record2.timestamp.getTime())
			return 1;
		else 
			return 0;
	}

}
