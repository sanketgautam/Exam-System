package studentpanel;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/*
 	Important Note :- The setBounds must be called before createLeftPanel(), or createRightPanel() as it is required
 */
public class Dashboard {
	public static int sid=0;
	public static JFrame frame;
	public static int topPanelHeight;
	public static int leftPanelWidth;
	public static int rightPanelWidth;
	public static int bothPanelHeight;
	public static DashboardRightPanel rp;
	public Dashboard(int sid)
	{
		Dashboard.sid=sid;
		Database.connectToDatabase();
		frame = new JFrame();
		frame.setTitle("SKG");
		frame.setLayout(null);
		/*getting the size of the screen*/
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (new Double(screenSize.getWidth())).intValue();
		int screenHeight = (new Double(screenSize.getHeight())).intValue();
		frame.setSize(screenWidth, screenHeight);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/*		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*SwingUtilities.updateComponentTreeUI(frame);*/
		/*Setting up panels on the Frame*/
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.GRAY);
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.PINK);
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.YELLOW);
		/*Getting position of Frame*/
		int x = frame.getX();
		int y = frame.getY();
		/*Locating panels on the frame*/
		topPanelHeight = (int)(screenHeight*0.05);
		leftPanelWidth = (int)(screenWidth*0.25);
		rightPanelWidth = screenWidth-leftPanelWidth;
		bothPanelHeight = screenHeight-topPanelHeight;
		topPanel.setBounds(x, y, screenWidth, topPanelHeight);
		leftPanel.setBounds(x, topPanelHeight, leftPanelWidth, bothPanelHeight);
		rightPanel.setBounds(leftPanelWidth, topPanelHeight, rightPanelWidth, bothPanelHeight);
		DashboardLeftPanel lp = new DashboardLeftPanel();
		lp.setBackground(Color.PINK);
		lp.setBounds(x, topPanelHeight, leftPanelWidth, bothPanelHeight);
		lp.createLeftPanel(new String[]{"Dashboard","Exams","Check Results","Logout"});
	
		rp = new DashboardRightPanel();
		rp.setVisible(false);
		DashboardLeftPanel.display(rp);
		rp.setBounds(leftPanelWidth, topPanelHeight, rightPanelWidth, bothPanelHeight);
		//System.out.println(rp.getBackground());
		String query = "SELECT sid,first_name,last_name,dob,phone_no,email_id,institute_name,address,image from student WHERE sid="+sid;
		String[][] details = new String[][]{{"Id No","ABDUL2022"},
			{"Name","Avul Pakir Jainulabdeen Abdul Kalam"},
			//{"Groups","A+"},
			{"Date Of Birth","2016-04-07"},
			{"Mob No","0987654321"},
			{"Email","apj@abdulkalam.com"},
			{"Group(s)","1"},
			{"Institute","M.A.N.I.T."},
			{"Address","Rameswaram, Ramanathapuram , Tamil Nadu"},
			{"image","C:/Users/Sanket/Desktop/kalam.jpg"}
		};
		
		byte[] b=null;
		//getting all the values from database
		try {
			ResultSet r = Database.execute(query);
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
		query="SELECT group_id FROM student_group WHERE sid="+sid;
		try {
			ResultSet r = Database.execute(query);
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
			System.out.println("No Exception Occured !");
		}catch(Exception e){
			System.out.println("Exception Occured : "+e);
		}
		//rp.setBackground(Color.YELLOW);
		/*Adding panels to frame*/
		frame.add(topPanel);
		frame.add(lp);//frame.add(leftPanel);
		frame.add(rp);//frame.add(rightPanel);
		frame.setVisible(true);
	}
}

