package database;
import java.sql.*;

public class DBConnection {
	static String account = "ccs108yzzhu";
	static String password = "aebaujei";
	static String server = "mysql-user-master.stanford.edu";
	static String database = "c_cs108_yzzhu";
	Connection con;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + server, account ,password);
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
		} catch (SQLException e) { 
			e.printStackTrace();
			} 
		catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
	}
}
