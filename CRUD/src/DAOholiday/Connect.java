package DAOholiday;
import java.sql.*;
public class Connect {	
	private Connection connect;
	private static final  String url =  "jdbc:postgresql://localhost:5432/JavaTp";
	private static final String user = "postgres";
	private static final String password = "0000";
	public Connect() {
		try {
		Class.forName("org.postgresql.Driver");
		connect = DriverManager.getConnection(url,user,password);
		System.out.println("Connection was established");
		
		}
		catch (Exception e) {
			System.err.println("couldn't establish the connection");
		}
	}
	public Connection getConnect() {
		return connect;
	}
}
