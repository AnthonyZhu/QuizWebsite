package quizweb.database;

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
	//Value: 'Mary', '123', 'F'
	public String InsertQueryByEntry(String table, String Value) {
		String statement = new String();
		statement = "INSERT INTO " + table + " VALUES (" + Value + ")";
		return statement;
	}
	
	//INSERT INTO user (name, password, gender) VALUES ('Mary', '123', 'F')
	//Column: name, password, gender
	//Value: 'Mary', '123', 'F'
	public String InsertQueryByColumn(String table, String Column, String Value) {
		String statement = new String();
		statement = "INSERT INTO " + table + " (" + Column + ")" + "VALUES (" + Value + ")";
		return statement;
	}
}
