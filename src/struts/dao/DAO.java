package struts.dao;

import java.sql.*;

import struts.util.Utilities;

public abstract class DAO {
	public static final String JDBC_DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	public static final String DATABASE_URL = "jdbc:jtds:sqlserver://localhost:1433/CINEMA;";
	public static final Utilities util = new Utilities();
	protected static Connection connection;
	protected static Statement statement;
	protected static PreparedStatement pstatement;
	
	ReadSettings read = new ReadSettings();

	public DAO() {
		
		String[] settings = read.getDatabaseSettings();
		System.out.println(settings[0] + settings[1] + settings[2]);
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(settings[0], settings[1], settings[2]);
			//connection = DriverManager.getConnection(DATABASE_URL, "sa", "123456");
			statement = connection.createStatement();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

}
