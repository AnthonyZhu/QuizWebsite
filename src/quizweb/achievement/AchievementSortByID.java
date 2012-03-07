package quizweb.achievement;

import java.util.Comparator;

public class AchievementSortByID implements Comparator<Achievement> {

	@Override
	public int compare(Achievement achievement1, Achievement achievement2) {
		if (achievement1.id < achievement2.id)
			return -1;
		else if (achievement1.id < achievement2.id)
			return 1;
		else 
			return 0;
	}

}
