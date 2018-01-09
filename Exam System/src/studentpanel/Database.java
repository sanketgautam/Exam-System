package studentpanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.ResultSetMetaData;
//import com.mysql.jdbc.Statement;

public class Database {
	static Connection connection;
	public static void demo(String[] args) {
		String query = "desc instructor";  
		ResultSet r=null;
		String[] sid = null;
	    String[] sname = null;
	    //connecting to the database
	    connectToDatabase();
	    //executing the query
		try {
			r = execute(query);
			
			if(r!=null){
				/*---------------------------*/
				   ResultSetMetaData rsmd = (ResultSetMetaData) r.getMetaData();
			       int columns = rsmd.getColumnCount();
			       
			       sid = new String[columns];
			       sname = new String[columns];
			       
			       int index=0;
			       while (r.next()) {
			           for (int i = 1; i <= columns; i++) {
			               if (i > 1 && i<columns) 
			               	System.out.print(",  ");
			               String columnValue = r.getString(i);
			               System.out.print(columnValue + " ");//rsmd.getColumnName(i)
			           }
		               index++;
			           System.out.println("");
				
			           /*---------------------------*/
			       }
			       System.out.println("The output is NOT NULL");
			}else{
				System.out.println("The output is NULL");
			}
			
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void connectToDatabase(){
		// TODO Auto-generated method stub   
		try {
		       Class.forName("com.mysql.jdbc.Driver");
		   } catch (ClassNotFoundException e) {
		       System.out.println("Where is your MySQL JDBC Driver?");
		       e.printStackTrace();
		       //return;
		   }

		   System.out.println("MySQL JDBC Driver Registered!");
		   connection = null;
		   
		   try {
		       connection = DriverManager.getConnection("jdbc:mysql://192.168.137.133:3306/examsystem","smarty", "exams");

		   } catch(SQLException e) {
		       System.out.println("Connection Failed! Check output console");
		       e.printStackTrace();
		       //return;
		   }
	}
	static ResultSet execute(String query) throws SQLException{
		   ResultSet r;
		   if (connection != null) {
		       System.out.println("You made it, take control your database now!");
		       Statement stmt = 	(Statement) connection.createStatement();
		       r = stmt.executeQuery(query);
		       	return r;
		   }else{
		       //System.out.println("Failed to make connection!");
		       System.out.println("Please connect to the database first!");
		   }
		   return null;
	}
	static int executeUpdate(String query) throws SQLException{
		   int r=0;
		   if (connection != null) {
		       //System.out.println("You made it, take control your database now!");
		       Statement stmt = 	(Statement) connection.createStatement();
		        r = stmt.executeUpdate(query);
		       	return r;
		   }else{
		       //System.out.println("Failed to make connection!");
		       System.out.println("Please connect to the database first!");
		   }
		   return r;
	}
	static void  closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
