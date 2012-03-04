package quizweb.accountmanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		String statement = query.InsertQueryByColumn("user", "name, password, type", 
				"'" + name + "', '" + hashedPassword + "', '" + type + "'");
		System.out.println(statement);
//		String statement = new String("insert into user (name, password, type) values (\""
//				+ name + "\",\"" + hashedPassword + "\",\"" + type + "\")");
		db.DBUpdate(statement);
	}
	
	public boolean accountMatch(String name, String password) {
		if(!isExisted(name))
			return false;
		Encryption e = new Encryption();
		String hashedPassword = e.generateHashedPassword(password);
		String statement = query.SelectQueryByColumn("password", "user", "name = \"" + name + "\""); 
		System.out.println(statement);
		//String statement = new String("select password from user where name = \"" + name + "\"");
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
		System.out.println(statement);
		//String statement = new String("select * from user where name = \"" + name + "\"");
		try {
			if(db.DBQuery(statement).first())
				isExisted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExisted;
	}
}
