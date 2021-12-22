package FineChat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class MysqlPool {
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	private static int maxCount = 20;
	
	static {
		for ( int i = 0; i < maxCount; i++ ) {
			MysqlHandler handler = new MysqlHandler();
			Connection connection;
			try {
				connection = handler.connect();
				pool.add(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
	
	public static Connection getConnection() {
		if ( pool.size() == 0 ) {
			return null;
		} else {
			return pool.remove(0);
		}
	}
	
	public static void release(Connection connection) {
		pool.add(connection);
	}
}
