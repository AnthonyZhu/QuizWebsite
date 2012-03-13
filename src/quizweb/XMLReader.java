package quizweb;

import java.io.*;
import java.util.*;

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
			if (elem.name == "quiz") {
				Quiz quiz = Quiz.getQuizByXMLElem(elem);
				quiz.addQuizToDB();
			} else if (elem.name == "user") {
				User user = User.getUserByXMLElem(elem);
				user.addUserToDB();
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
					System.out.println(name);
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
		new XMLReader("./xml/users.xml");
		new XMLReader("./xml/bunny.xml");
		new XMLReader("./xml/cities.xml");
		
	}
	
	public static void main(String[] args) {
		XMLReader.loadAllXML();
	}
	
}
