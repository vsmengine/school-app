package dbHandlers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class DatabaseManager {
	private static String jdbcClassName="org.hsqldb.jdbc.JDBCDriver";
	private static String jdbcConnectionString="jdbc:hsqldb:hsql://localhost/school;shutdown=true";
	private static String userName="SA";
	private static String password="";
//	private static String jdbcClassName="oracle.jdbc.driver.OracleDriver";
//	private static String jdbcConnectionString="jdbc:oracle:thin:school/s@GUNTARS-PC:1521:edu";
//	private static String userName="school";
//	private static String password="s";

	public static String getJdbcClassName() {
		return jdbcClassName;
	}

	public static void setJdbcClassName(String jdbcClassName) {
		DatabaseManager.jdbcClassName = jdbcClassName;
	}

	public static String getJdbcConnectionString() {
		return jdbcConnectionString;
	}

	public static void setJdbcConnectionString(String jdbcConnectionString) {
		DatabaseManager.jdbcConnectionString = jdbcConnectionString;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		DatabaseManager.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		DatabaseManager.password = password;
	}

	
	// private static Connection connection = null;
	private static HashMap<String, Connection> connectionPool = new HashMap<String, Connection>();  
	
	public static Connection getConnection()  {
		long startTime = System.currentTimeMillis();
		String connectionKey=jdbcConnectionString+userName;
		Connection existingCon = DatabaseManager.connectionPool.get(connectionKey); 
		if (existingCon == null ) {
			Connection con=null;
			try {
			     Class.forName(jdbcClassName);
			     con = DriverManager.getConnection(jdbcConnectionString, userName, password);
			     DatabaseManager.connectionPool.put(connectionKey, con);
			     existingCon = con;
			 } catch (Exception ex) {
			     System.err.println("ERROR: failed to connect to Database");
			     ex.printStackTrace();
			 }
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Get Connection to DB took " + (endTime-startTime) + " ms");
		return existingCon;
	}
	
	@SuppressWarnings("finally")
	public static ResultSet getResultSetFromSql(String sql) {
		// Connection con=null;
		ResultSet resultSet=null;
		try {
			Statement statement=DatabaseManager.getStatement();
			resultSet=statement.executeQuery(sql);
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			return resultSet;
		}
	}
	
	public static Statement getStatement() throws SQLException {
		Connection con = dbHandlers.DatabaseManager.getConnection();
		return con.createStatement();
	}
	
	public static void setupDb() {
		HashMap<String, Boolean> tableMap = new HashMap<String, Boolean>();
		tableMap.put("COURSE", false);
		tableMap.put("REGISTRATION", false);
		tableMap.put("TEACHER", false);
		tableMap.put("STUDENT", false);
		tableMap.put("PROGRAM", false);
		tableMap.put("TEACHER_LEVEL", false);

		try {
			Connection con = dbHandlers.DatabaseManager.getConnection();
			DatabaseMetaData metaData = con.getMetaData();
			ResultSet tables = metaData.getTables(con.getCatalog(), "PUBLIC", "%", null);
			while (tables.next()) {
				//System.out.println("table = " + tables.getString(3));
				String tableName = tables.getString(3);
				if (tableMap.containsKey(tableName)) {
					tableMap.put(tableName, true);
				}
			}
			boolean dbExists = true;
			for (String table : tableMap.keySet()) {
				if (!tableMap.get(table)) {
					System.out.println("Table " + table + "exists not!");
					dbExists = false;
				}
			}
			if (!dbExists) {
				//CREATE DB
				File file = new File("./sql/create_schema.sql");
				BufferedReader br = new BufferedReader(new FileReader(file));
				String sql = "";
				String line; 
				while ((line = br.readLine()) != null) {
					sql = sql + line + "\n";
				}
				//System.out.println(sql);
				Statement statement=con.createStatement();
				System.out.println("CREATE SCHEMA ...");
				
				String sqls[] = sql.split(";");
				for(String sqlCommand : sqls) {
					System.out.println("---------------------------");
					System.out.println(sqlCommand);
					statement.executeUpdate(sqlCommand);
				}

				//System.out.println("ALTER TABLE ...");
				//statement.executeUpdate("ALTER TABLE COURSE ADD CONSTRAINT COURSE_PROGRAM_FK FOREIGN KEY (PROGRAM_ID) REFERENCES PROGRAM(PROGRAM_ID) ON DELETE SET NULL;");
				
				
				br.close();
				
			}

		}
		catch (java.sql.SQLException ex) {
			ex.printStackTrace(System.out);
			System.out.println(ex.getMessage());
		}
		catch (java.io.IOException ex) {
			ex.printStackTrace(System.out);
			System.out.println(ex.getMessage());
		}			
	}
	
	public static void updateDbConnectionInfo(String appConfigFile) {
		try {
	        FileInputStream is = new FileInputStream(appConfigFile);
	        Properties props = new Properties();
	        props.load(is);
	        String jdbcClassName = props.getProperty("jdbcClassName");
	        String jdbcConnectionString = props.getProperty("jdbcConnectionString");
	        String dbUserName = props.getProperty("dbUserName");
	        String dbPassword = props.getProperty("dbPassword");
	        
	        System.out.println(">>>>>>>>>>>>>>>> appConfigFile:" + appConfigFile);
	        System.out.println(">>>>>>>>>>>>>>>> jdbcClassName:" + jdbcClassName);
	        System.out.println(">>>>>>>>>>>>>>>> jdbcConnectionString:" + jdbcConnectionString);
	        System.out.println(">>>>>>>>>>>>>>>> userName:" + dbUserName);
	        System.out.println(">>>>>>>>>>>>>>>> password:" + dbPassword+".");
	        DatabaseManager.setJdbcClassName(jdbcClassName);
	        DatabaseManager.setJdbcConnectionString(jdbcConnectionString);
	        DatabaseManager.setUserName(dbUserName);
	        DatabaseManager.setPassword(dbPassword);
		}
		catch(Exception ex) {
			ex.printStackTrace(System.err);
		}
		
	}
	
	public static void main(String[] args) {
		setupDb();
	}

}
