package transactionProxy.connecManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	public static ThreadLocal<Connection> threadConn=new ThreadLocal<Connection>();
	
	public static Connection getConnection() {
		Connection conn=threadConn.get();
		if(conn==null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/demoProject", "root", "123456");
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			threadConn.set(conn);
		}
		return conn;
	}
	
	public static void startTransction() {
		
	}
}
