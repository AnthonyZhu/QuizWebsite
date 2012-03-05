package quizweb.accountmanagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import quizweb.database.*;


public class AccountManager {
	DBConnection db;
	private static final String DBTable = "user";
	public AccountManager(){
		db = new DBConnection();
	}
	
	public void createNewAccount(String name, String password, String type){
		Encryption e = new Encryption();
		String hashedPassword = e.generateHashedPassword(password);
		String statement = new String("INSERT INTO " + DBTable +" (name, password, type) VALUES (?, ?, ?)");
		try {
			PreparedStatement stmt = db.con.prepareStatement(statement);
			stmt.setString(1, name);
			stmt.setString(2, hashedPassword);
			stmt.setString(3, type);
			db.DBUpdate(stmt);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean accountMatch(String name, String password) {
		if(!isExisted(name))
			return false;
		Encryption e = new Encryption();
		String hashedPassword = e.generateHashedPassword(password);
		try {
			String statement = new String("SELECT password FROM " + DBTable +" WHERE name = ?");
			PreparedStatement stmt = db.con.prepareStatement(statement);
			stmt.setString(1, name);
			ResultSet rs = db.DBQuery(stmt);
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
		try {
			String statement = new String("SELECT * FROM " + DBTable +" WHERE name = ?");
			PreparedStatement stmt = db.con.prepareStatement(statement);
			stmt.setString(1, name);
			if(db.DBQuery(stmt).first())
				isExisted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExisted;
	}
}
