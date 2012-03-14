package quizweb;

import java.io.*;
import java.util.*;

import quizweb.achievement.*;
import quizweb.announcement.*;
import quizweb.database.*;
import quizweb.question.*;
import quizweb.record.Record;

public class XMLReader {
	public static boolean isLoaded = false;
	
	String content;
	int pos;
	XMLElement root;
	
	
	public XMLReader(String filename) {
		try {
			BufferedReader bufRead = new BufferedReader(new FileReader(filename));
			String line;
			content = new String();
			while ((line = bufRead.readLine()) != null) {
				content = content + " " + line.trim();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parseXML();
		addToDB();
	}
	
	private void addToDB() {
		for (int i = 0; i < root.childList.size(); i++) {
			XMLElement elem = root.childList.get(i);
			if (elem.name.equals("quiz")) {
				ArrayList<XMLElement> questionElems = new ArrayList<XMLElement>();
				Quiz quiz = Quiz.getQuizByXMLElem(elem, questionElems);
				quiz.addQuizToDB();		
				for (int j = 0; j < questionElems.size(); j++) {
					XMLElement questionElem = questionElems.get(j);
					Question question = Question.getQuestionByXMLElem(questionElem, quiz, j);
					question.addQustionToDB();
				}
			} else if (elem.name.equals("user")) {
				User user = User.getUserByXMLElem(elem);
				user.addUserToDB();
			} else if (elem.name.equals("achievement")) {
				Achievement achievement = Achievement.getAchievementByXMLElem(elem);
				achievement.addAchievementToDB();
			} else if (elem.name.equals("announcement")) {
				Announcement announcement = Announcement.getAnnouncementByXMLElem(elem);
				announcement.addAnnouncementToDB();
			} else if (elem.name.equals("record")) {
				Record record = Record.getRecordByXMLElem(elem);
				record.addRecordToDB();
			} else if (elem.name.equals("message")) {
				
			} else if (elem.name.equals("friendship")) {
				if (!elem.attributeMap.containsKey("user"))
					System.out.println("Must contain user attribute");
				String username = elem.attributeMap.get("user");
				for (int j = 0; j < elem.childList.size(); j++) {
					XMLElement subElem = elem.childList.get(j);
					if (subElem.name.equals("friend")) {
						User.addFriendship(username, subElem.content);
					} else {
						System.out.println("Unrecognized friendship field " + subElem.name);
					}
				}				
			} else {
				System.out.println("Unrecognized field " + elem.name);
			}
		}
		
	}

	private String getNextString() {
		while ( pos < content.length() && content.charAt(pos) == ' ') 
			pos++;
		if (pos >= content.length())
			return new String("");
		if (content.charAt(pos) == '<') {
			int endPos = content.indexOf('>', pos);
			String retStr = content.substring(pos, endPos+1);
			pos = endPos + 1;
			return retStr;
		} else {
			int endPos = content.indexOf('<', pos);
			String retStr = content.substring(pos, endPos);
			pos = endPos;
			return retStr;
		}	
	}
	
	private void parseXML() {
		Stack<XMLElement> elemStack = new Stack<XMLElement>();
		pos = 0;
		root = new XMLElement("xml");
		elemStack.push(root);
		
		String next = getNextString();
		while (!next.isEmpty()) {
			if (next.contains("?xml")) {
				next = getNextString();
				continue;
			}
			if (next.charAt(0) == '<') {
				if (next.charAt(1) != '/') {
					int namePos = next.indexOf(' ');
					if (namePos == -1)
						namePos = next.length() - 1;
					String name = next.substring(1, namePos);
					XMLElement newElem = new XMLElement(name);
					if (namePos < next.length() - 3) {						
						int newPos = namePos + 1;
						while (newPos < next.length() - 1) {
							int nextPos = next.indexOf(' ', newPos);
							if (nextPos == -1)
								nextPos = next.length() - 1;
							String attrStr = next.substring(newPos, nextPos);
							int midPos = attrStr.indexOf('=');
							String key = attrStr.substring(0, midPos);
							String value = attrStr.substring(midPos+2, attrStr.length()-1);
							newElem.attributeMap.put(key, value);
							newPos = nextPos + 1;
						}
					}
					elemStack.peek().childList.add(newElem);
					elemStack.push(newElem);
					if (next.charAt(next.length()-2) == '/')
						elemStack.pop();
				} else {
					XMLElement parentElem = elemStack.pop();
					assert(parentElem.name.equals(next.substring(2, next.length()-1)));
				}
			} else {
				elemStack.peek().content = next;
			}
			next = getNextString();
		}
	}

	public static void loadAllXML() {
		if (isLoaded) return;
		isLoaded = true;
		// Load users
		new XMLReader("./xml/users.xml");
		// Load quizzes
		new XMLReader("./xml/bunny.xml");
		new XMLReader("./xml/cities.xml");
		// Load achievement
		new XMLReader("./xml/achievements.xml");
		// Load records
		new XMLReader("./xml/records.xml");
		
		//new XMLReader("./xml/announcements.xml");
		
	}
	
	public static void main(String[] args) {
		DBConnection.resetDatabase();
		XMLReader.loadAllXML();
		System.out.println("ALL DONE");
	}
	
}
