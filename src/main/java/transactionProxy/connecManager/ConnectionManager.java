package transactionProxy.connecManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	public static ThreadLocal<Connection> threadConn=new ThreadLocal<Connection>();
	
	/**
	 * 获取连接
	 * @return
	 */
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
	
	/**
	 * 设置手动提交事务
	 * @param conn
	 */
	public static void beginTransction(Connection conn) {
		try {
			if(conn!=null) {
				if(conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 提交事务
	 * @param conn
	 */
	public static void endTransction(Connection conn) {
		try {
			if(conn!=null) {
				if(!conn.getAutoCommit()) {
					conn.commit();
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 初始化事务状态
	 * @param conn
	 */
	public static void initTransction(Connection conn) {
		try {
			if(conn!=null) {
				if(conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}else {
					conn.setAutoCommit(true);
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 回滚事务
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn!=null) {
				conn.rollback();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 关闭连接
	 */
	public static void close() {
		Connection conn=threadConn.get();
		if(conn!=null) {
			try {
				conn.close();
				conn=null;
				threadConn.remove();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
	}
}
