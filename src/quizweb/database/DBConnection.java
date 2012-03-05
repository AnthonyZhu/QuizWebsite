package quizweb.database;
import java.sql.*;

public class DBConnection {
	static String account = "ccs108yzzhu";
	static String password = "aebaujei";
	static String server = "mysql-user-master.stanford.edu";
	static String database = "c_cs108_yzzhu";
	public Connection con;
	
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
	
	public ResultSet DBQuery(PreparedStatement stmt) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int DBUpdate(PreparedStatement stmt) {
		int rs = 0;
		try {
			rs = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void DBClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
