package quizweb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {
	
	private String time;
	
	public CurrentTime() {
		time = new String();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		time = dateFormat.format(date);	
	}
	
	public String GetFormatedTime() {
		return time;
	}
}

