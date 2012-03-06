package quizweb.record;

import java.util.Comparator;

public class RecordSortByTime implements Comparator<Record> {

	@Override
	public int compare(Record record1, Record record2) {
		if (record1.timestamp.getTime() > record2.timestamp.getTime())
			return -1;
		else if (record1.timestamp.getTime() < record2.timestamp.getTime())
			return 1;
		else
			return 0;
	}

}
