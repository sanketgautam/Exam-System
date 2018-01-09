package Instructor;

import javax.swing.*;

import Login.dbConnector;
import studentpanel.Database;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
/*
 	Important Note :- The setBounds must be called before createLeftPanel(), or createRightPanel() as it is required
 */
public class Dashboard{
	
	public static int iid=0;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	static dbConnector dbc = new dbConnector();
	int panelwidth = ((int)width )*3/4;
	int panelheight = (int)height ;
	Instructor instructor;
	Dashboard(Instructor a)
	{
		iid = Instructor.instructorid;
		
		instructor=a;
		RightPanel rp = new RightPanel();
		rp.setBounds((int)(width/4),0,(int)((width*3)/4),(int)height);
		rp.setLayout(null);
		
		
		String query = "SELECT iid,first_name,last_name,dob,phone_no,email_id,institute_name,address,image from instructor WHERE iid="+iid;
		String[][] details = new String[][]{{"Id No","ABDUL2022"},
			{"Name","Avul Pakir Jainulabdeen Abdul Kalam"},
			//{"Groups","A+"},
			{"Date Of Birth","2016-04-07"},
			{"Mob No","0987654321"},
			{"Email","apj@abdulkalam.com"},
			{"Group(s)","1"},
			{"Institute","M.A.N.I.T."},
			{"Address","Rameswaram, Ramanathapuram , Tamil Nadu"},
			{"image","2222.png"}
		};
		
		byte[] b=null;
		//getting all the values from database
		try {
			Statement stmt = dbc.conn.createStatement();
			ResultSet r = stmt.executeQuery(query);
			
			ResultSetMetaData rsmd = (ResultSetMetaData) r.getMetaData();
			
			while (r.next()) {
			    details[0][1] = r.getString(1);
			    details[1][1] = r.getString(2)+" "+r.getString(3);
			    details[2][1] = r.getString(4);
			    details[3][1] = r.getString(5);
			    details[4][1] = r.getString(6);
			    //details[5][1] = r.getString(7);
			    details[6][1] = r.getString(7);
			    details[7][1] = r.getString(8);
			    b = r.getBytes(9);
			    //details[6][1] = b.toString();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//adding the group
		query="SELECT group_id FROM instructor_group WHERE iid="+iid;
		try {
			Statement stmt = dbc.conn.createStatement();
			ResultSet r = stmt.executeQuery(query);
			
			String groups = "";
			int index=0;
			while (r.next()) {
				if(index>0)
					groups+=", ";
				groups+="G"+r.getString(1);
				index++;
			}
			details[5][1] = groups;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//adding the right panel
		try{
			rp.createRightPanel(details,b);
			instructor.addRightPanel(rp);
			System.out.println("No Exception Occured !");
		}catch(Exception e){
			System.out.println("Exception Occured : "+e);
		}
		
		
		
	}
}
