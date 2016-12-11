package exercise1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
	// for MSSQL
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=game";
	static final String USER = "player";
	static final String PASS = "123";
	static Connection connection = null;
	
	// for MySQL
	//static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	//static final String DB_URL = "jdbc:mariadb://localhost:3306/game";
	//static final String USER = "player";
	//static final String PASS = "12345";
	//static Connection connection = null;


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
