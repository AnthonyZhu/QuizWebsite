package quizweb.database;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DBConnection {
	static String account = "ccs108yzzhu";
	static String password = "aebaujei";
	static String server = "mysql-user-master.stanford.edu";
	static String database = "c_cs108_yzzhu";
//	static String account = "ccs108gxj";
//	static String password = "aehifinu";
//	static String server = "mysql-user-master.stanford.edu";
//	static String database = "c_cs108_gxj";	
	
	public static Connection con = initConnection();
	
	private static Connection initConnection() {
		Connection thisCon = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			thisCon = DriverManager.getConnection("jdbc:mysql://" + server, account ,password);
			Statement stmt = thisCon.createStatement();
			stmt.executeQuery("USE " + database);
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return thisCon;
	}
	
	public static void resetDatabase() {
		try {
			Statement stmt = con.createStatement();
			BufferedReader bufRead = new BufferedReader(new FileReader("setup.sql"));
			String line;
			String command = new String();
			while ((line = bufRead.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("--"))
					continue;
				command = command + line;
				if (line.endsWith(";")) {
					stmt.executeUpdate(command);
					command = new String();
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet DBQuery(PreparedStatement stmt) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static int DBUpdate(PreparedStatement stmt) {
		int rs = 0;
		try {
			rs = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void DBClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
