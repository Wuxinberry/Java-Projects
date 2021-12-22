package FineChat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlHandler {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/finechat?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	
	static final String USER = "root";
	static final String PASS = "010409";
	
	private static Connection conn = null;
	@SuppressWarnings({ "rawtypes", "unused" })
	private static Statement stmt = null;
	@SuppressWarnings("unused")
	private static ResultSet rs = null;
	
	/*
	 * Name: connect
	 * Function: build connection with database 
	 * return: connection 
	 * */
	public Connection connect() throws SQLException {
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("database connecting...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("database connected.");
		return conn;
	}
	
	
	public static void shutdown() {
		if ( rs != null ) {
			try {
				rs.close();
			} catch ( Exception ex ) {
			ex.printStackTrace();
			}
		}
		if ( stmt != null ) {
			try {
				stmt.close();
			} catch ( Exception ex) {
				ex.printStackTrace();
			}
		}
		MysqlPool.release(conn);
	}
	
	
	/*
	 * Name: execute
	 * Function: execute sql command
	 * Return: affected count 
	 * */
	public int execute (String sql){
		try {
			conn = MysqlPool.getConnection();
			stmt = conn.createStatement();
			int affectedCount = stmt.executeUpdate(sql); // execute statement
			return affectedCount;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	
	/*
	 * Name:query
	 * Function: query result
	 * Return: result queried
	 * */
	public ResultSet query(String sql) {
		try {
			conn = MysqlPool.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
