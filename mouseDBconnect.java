import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mouseDBconnect {
	
	private static final String USERNAME = "comp271project";
	private static final String PASSWORD = "b3stproj3ct3v3r";
	private static final String CONN = "jdbc:mysql://db4free.net/mouselabdb?autoReconnect=true&useSSL=false"; //gpa = name of database

	public static Connection getConnection() throws SQLException {
		
		/**
		 * **GETCONNECTION method**
		 * Output: Returns the information necessary to connect to MySQL database in try blocks in Courses and Users classes.
		 */
		
		return DriverManager.getConnection(CONN, USERNAME, PASSWORD);
	}
	
}