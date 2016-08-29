package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import resources.SQLiteOperationOutcomes;

public class SQLiteOperations {
	public static SQLiteOperationOutcomes InsertSignUpUserSQLite(String email, String password, String fname, String lname) {
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:/Users/sidarth/Documents/webworkspace/BlogApp/BlogAppDB");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      PreparedStatement statement = c.prepareStatement(
	              "INSERT INTO USER (EMAIL,PASSWORD,FNAME,LNAME) VALUES(?,?,?,?)");
	      statement.setString(1,email);
	      statement.setString(2,password);
	      statement.setString(3,fname);
	      statement.setString(4,lname);
	      System.out.println("Prepared Insert Query : " + statement);
	      statement.executeUpdate();
	      statement.close();
	      c.commit();
	      c.close();
	      System.out.println("Records created successfully");
	      return SQLiteOperationOutcomes.INSERT_SUCCESS;
	    } 
	    catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return SQLiteOperationOutcomes.INSERT_FAIL;
	    }
	  }
}
