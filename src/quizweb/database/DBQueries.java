package quizweb.database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class DBQueries {
	
	//SELECT uid FROM user WHERE age = 0
	public String SelectQueryByColumn(String column, String table, String condition) {
		String statement = new String();
		statement = "SELECT " + column + " FROM " + table + " WHERE " + condition;
		return statement;
	}
	
	//SELECT * FROM user WHERE age = 0
	public String SelectAllQuery(String table, String condition) {
		String statement = new String();
		statement = "SELECT *" + " FROM " + table + " WHERE " + condition;
		return statement;
	}
	
	//INSERT INTO user VALUES ('Mary', '123', 'F')
	public String InsertQueryByEntry(String table, HashMap<String, String> valueMap) {
		String statement = new String();
		String value = new String();
		Collection<String> c = valueMap.keySet();
		Iterator<String> iter = c.iterator();
		value += "'" + valueMap.get(iter.next()) + "'";
		while(iter.hasNext()) {
			value = ", '" + valueMap.get(iter.next()) + "'";
		}
		statement = "INSERT INTO " + table + " VALUES (" + value + ")";
		return statement;
	}
	
	//INSERT INTO user (name, password, gender) VALUES ('Mary', '123', 'F')
	public String InsertQueryByColumn(String table, HashMap<String, String> valueMap) {
		String statement = new String();
		String value = new String();
		String column = new String();
		Collection<String> c = valueMap.keySet();
		Iterator<String> iter = c.iterator();
		String s = iter.next();
		column += s;
		value += "'" + valueMap.get(s) + "'";
		while(iter.hasNext()) {
			s = iter.next();
			column += "," + s;
			value += ",'" + valueMap.get(s) + "'";
		}
		statement = "INSERT INTO " + table + " (" + column + ")" + "VALUES (" + value + ")";
		return statement;
	}
}
