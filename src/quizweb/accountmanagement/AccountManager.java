package quizweb.accountmanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import quizweb.database.*;


public class AccountManager {
	DBConnection db;
	DBQueries query;

	public AccountManager(){
		db = new DBConnection();
		query = new DBQueries();
	};
	
	public void createNewAccount(String name, String password, String type){
		Encryption e = new Encryption();
		String hashedPassword = e.generateHashedPassword(password);
		HashMap<String, String>valueMap = new HashMap<String, String>();
		valueMap.put("name", name);
		valueMap.put("password", hashedPassword);
		valueMap.put("type", type);
		String statement = query.InsertQueryByColumn("user", valueMap);
		db.DBUpdate(statement);
	}
	
	public boolean accountMatch(String name, String password) {
		if(!isExisted(name))
			return false;
		Encryption e = new Encryption();
		String hashedPassword = e.generateHashedPassword(password);
		String statement = query.SelectQueryByColumn("password", "user", "name = \"" + name + "\""); 
		ResultSet rs = db.DBQuery(statement);
		try {
			rs.beforeFirst();
			rs.next();
			String storedPassword = rs.getString("password");
			if(hashedPassword.equals(storedPassword))
				return true; 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	public boolean isExisted(String name) {
		boolean isExisted = false;
		String statement = query.SelectAllQuery("user", "name = \"" + name + "\"");
		try {
			if(db.DBQuery(statement).first())
				isExisted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExisted;
	}
}
