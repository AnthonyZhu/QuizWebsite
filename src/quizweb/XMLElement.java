package quizweb;

import java.util.*;

public class XMLElement {
	public String name;
	public String content;
	public HashMap<String, String> attributeMap;
	public ArrayList<XMLElement> childList;
	
	XMLElement(String name) {
		this.name = name;
		attributeMap = new HashMap<String, String>();
		childList = new ArrayList<XMLElement>();
	}
}
