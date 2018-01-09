import java.sql.*;

class dbConnector{
	static final String jdbc_driver = "com.mysql.jdbc.Driver";
	static final String db_url = "jdbc:mysql://192.168.43.135:3306/examsystem";
	//static final String db_url = "jdbc:mysql://localhost/examsystem";
	static String user="smarty",pass="exams";
	//static String user="root",pass="mysql";
	static Connection conn = null;
	static Statement stmt = null;
	public static void main(String[] args) 
	{
		
		try
		{
			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//Connect to Databse 
			conn = DriverManager.getConnection(db_url,user,pass);
			System.out.println("Database Selected ");
			
			//Create a statement 
			stmt  = conn.createStatement();
			
			//Execute a query
			String qry ;
			//qry = "SHOW TABLES";
			//qry = "SELECT * FROM EXAM";
			
			//qry = "delete from Question";
		//	stmt.executeUpdate(qry);
			//qry = "desc question";
			//qry = "SELECT eid,ename FROM exam";
			
			qry = "SELECT S.sid,S.first_name,S.last_name,R.Total_Marks "
					+ "FROM Student S,ExamStudentTable E,Result R "
					+ "WHERE E.eid = "+11+" AND S.sid = E.sid  AND R.estid = E.estid "
					+ "ORDER BY(S.sid)";
			
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next())
			{
				String name = rs.getString(2);
			//  String name = rs.getString(2);
				System.out.println(" "+name+", ");
			}
			rs.close(); 
		
		}catch(SQLException e)
		{//Exceptions in JDBC
			System.err.println("Exception  "+e.getMessage());
		}
		catch(Exception e)
		{//Exceptions in Class.forName()
			System.err.println("Exception  "+e.getMessage());
		}
		finally
		{
			//Close all connections
			try // Close stmt
			{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException e){}
			try // Close conn
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e){}
		}

			//Close all connections
			
		
	}
	
	public ResultSet executeQuery(String qry)
	{
		ResultSet rs =null;
		Statement stmt = null;
		try{
			stmt = this.conn.createStatement();
			rs = stmt.executeQuery(qry);
			}
			catch(Exception exp)
			{
				System.err.println("In qry : " +qry);
			}
			finally
			{
				try {
					stmt.close();
				} catch (SQLException exp) {
					// TODO Auto-generated catch block
					exp.printStackTrace();
				}
			}
		return rs;
		
	}

}
