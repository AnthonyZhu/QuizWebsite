package quizweb.announcement;

import java.util.*;

public class announcementSortByTime implements Comparator<Announcement> {

	@Override
	public int compare(Announcement announcement1, Announcement announcement2) {
		if (announcement1.timestamp.getTime() < announcement2.timestamp.getTime())
			return -1;
		else if (announcement1.timestamp.getTime() > announcement2.timestamp.getTime())
			return 1;
		else 
			return 0;
	}
	
}
