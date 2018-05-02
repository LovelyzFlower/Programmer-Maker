package PM_game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	static public Connection conn;
	static public Statement stmt;	
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test";

	static final String USERNAME = "root";
	static final String PASSWORD = "1234";
	
	
	static public boolean connectionDB(){
		boolean result =true;
		try {
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
	
			System.out.println("DB connection is ok");
			stmt = conn.createStatement();
			System.out.println("Statement is generated");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver error");
			result =false;
		} catch (SQLException e) {
			System.out.println("DB connection is failed");
			result =false;
		}
		return result;
	}
	
	static public void makeUserTable(){
		String table_name = "user";
		String query = "create table if not exists "+ table_name +" (" + 
		               "db_id varchar(15) not null, " + 
		               "db_pw varchar(15) not null, " + 
		               "db_charname varchar(10) not null, " + 
		               "db_email varchar(20) not null, "+
		               "db_money int(10) default '200000'," +
		               "db_goodwill int(10) default '0'," +
		               "db_stress int(10) default '0'," +
		               "db_study int(10) default '0'," +
		               "db_hp int(10) default '100'," +
		               "db_hobby int(10) default '0'," +
		               "db_game int(10) default '0'," +
		               "db_week int(10) default '0'," +
		               "db_mid_exam int(10) default '0'," +
		               "db_final_exam int(10) default '0'," +
		               "db_grade_1_1 int(10) default '0'," +
		               "db_grade_1_2 int(10) default '0'," +
		               "db_grade_2_1 int(10) default '0'," +
		               "db_grade_2_2 int(10) default '0'," +
		               "db_grade_3_1 int(10) default '0'," +
		               "db_grade_3_2 int(10) default '0'," +
		               "db_grade_4_1 int(10) default '0'," +
		               "db_grade_4_2 int(10) default '0'" +
		               ")";	               	               
		try {
			stmt.executeUpdate(query); //쿼리 실행
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

	
}