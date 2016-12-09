package exercise1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";
	static final String USER = "username";
	static final String PASS = "password";
	static Connection connection = null;

	private static class DataSourceHolder {  
        private static final DataSource INSTANCE = new DataSource();  
    }
	
	private DataSource (){}  
    public static final DataSource getInstance() {  
        return DataSourceHolder.INSTANCE; 
    }  
	
	public Connection getConnection() {
		if (null == connection) {
			Connection conn = null;
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				conn.close();
			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
			}
			return conn;
		} else {
			return connection;
		}
	}

	public void closeConnection(ResultSet rs, Statement statement) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
