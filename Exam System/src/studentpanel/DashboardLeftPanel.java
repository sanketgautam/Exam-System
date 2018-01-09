package studentpanel;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import Login.User_Login;

public class DashboardLeftPanel extends JPanel {
	public static JPanel currPanel=null;
	public static DashboardExamShowPanel esp=new DashboardExamShowPanel();
	public static DashboardResultsPanel rsp = new DashboardResultsPanel(); 
	void createLeftPanel(String options[]){
		int n = options.length,index=0;
		JButton[] buttons = new JButton[n];
		//setting the layout of panel absolute
		super.setLayout(null);
		/*Getting Bounds of the panel*/
		Rectangle bounds = super.getBounds();
		int x = bounds.x;
		int y = bounds.y;
		int width = (int)bounds.getWidth();
		int height = (int)bounds.getHeight();
		/*Creating and Adding Buttons in the panel*/
		for(JButton b : buttons){
			b = new JButton(options[index]);
			b.setBounds(x+10, y+10, width-20, 50);
			b.addActionListener(new CustomActionListener());
			//b.setBackground(Color.magenta);
			y+=(10+50);
			index++;
			super.add(b);
		}
	}
	public static void display(JPanel p){
		//logic of showing the specified panel
		if(currPanel!=null){
			Dashboard.frame.remove(currPanel);
			/*if(currPanel==esp){
				for(JPanel innerP : ExamShowPanel.examListItem)
	    			innerP.setVisible(false);
			}else if(currPanel==rsp){
				for(JPanel innerP :ResultsPanel.resultListItem )
	    			innerP.setVisible(false); 
			}else if(currPanel==ResultsPanel.rdp){
				for(JPanel innerP :ResultDisplayPanel.infoListItem )
	    			innerP.setVisible(false);
			}*/
		}
		Dashboard.frame.add(p);
		p.setVisible(true);
		currPanel = p;
	}
	class CustomActionListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	    	  //System.out.println("Component Count : "+Dashboard.frame.getComponentCount());
	          //Getting data from the database
	    	  int n=0;
	    	  String query="SELECT count(*) FROM exam WHERE group_id in (SELECT group_id FROM student_group where sid="+Dashboard.sid+")";
	    	  try {
		  			ResultSet r = Database.execute(query);
		  			if(r.next()){
		  				n = r.getInt(1);
		  			}else{
		  				n = 0;
		  			}
		  			n++;
		  			
		  		} catch (SQLException e1) {
		  			// TODO Auto-generated catch block
		  			e1.printStackTrace();
		  		}
	    	  System.out.println("n ---------- "+n);
	    	  /*String[][] exams = new String[][]{	{"Exam ID","Exam Title","Exam Date & Time","Duration","roup"},
					{"E1","Avul Pakir Jainulabdeen Abdul Kalam Exam","25-04-2016 04:45:00","3:00","3"},
					{"E2","Samsung Placement Exam","31-04-2016 03:00:00","3:00","2"},
					{"E3","Google Mock Exam","30-03-2016 02:15:00","3:00","1"},
					{"E4","No description","20-01-2016 01:00:00","3:00","3"},
					{"E5","M.AN.I.T. Exam","12-12-2015 04:30:00","3:00","6"},
					{"E6","Rameswaram Ramanathapuram Exam","02-09-2016 05:00:00","1:15","9"},
					{"E7","SKG Exam","24-02-2016 03:15:00","00:30","6"},
					{"E4","No description","20-01-2016 09:30:00","00:15","5"}
				};*/
	    	  String[][] exams = new String[n][5];
	    	  
	    	  exams[0][0] = "xam ID";
			  exams[0][1] = "Exam Title";
			  exams[0][2] = "Exam Date & Time";
			  exams[0][3] = "Duration";
			  exams[0][4] = "roup";
	    	
			  query="SELECT eid,ename,edate_time,eduration,group_id FROM exam WHERE group_id in (SELECT group_id FROM student_group where sid="+Dashboard.sid+")";
		  		try {
		  			ResultSet r = Database.execute(query);
		  			int i=1;
		  			while (r.next()) {
		  				exams[i][0] = r.getString(1);
		  				exams[i][1] = r.getString(2);
		  				exams[i][2] = r.getString(3);
		  				exams[i][3] = r.getString(4);
		  				exams[i][4] = r.getString(5);
		  				i++;
		  			}
		  			
		  		} catch (SQLException e1) {
		  			// TODO Auto-generated catch block
		  			e1.printStackTrace();
		  		}
	    	  
	    	  /*-------------------ExamShowPanel creation------------------*/
	    	  
				try {
				esp = new DashboardExamShowPanel(exams);
				esp.setBounds(Dashboard.leftPanelWidth, Dashboard.topPanelHeight, Dashboard.rightPanelWidth, Dashboard.bothPanelHeight);
				Dashboard.frame.add(esp);
				esp.setVisible(false);
				} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
	    	  /*---------------ResultsPanel-------------------------*/
				//Getting data from the database
		    	  int m=0;
		    	  String resultQuery="SELECT count(*) FROM ExamStudentTable WHERE sid = "+Dashboard.sid;
		    	  try {
			  			ResultSet r = Database.execute(resultQuery);
			  			if(r.next()){
			  				m = r.getInt(1);
			  			}else{
			  				m = 0;
			  			}
			  			m++;
			  			
			  		} catch (SQLException e1) {
			  			// TODO Auto-generated catch block
			  			e1.printStackTrace();
			  		}
		    	  System.out.println("m ---------- "+m);
		    	  
		    	  String[][] results = new String[m][3];
		    	  results[0][0] = "xam ID";
		    	  results[0][1] = "Exam Title";
		    	  results[0][2] = "Conducted On";
		    	  String[] estid = new String[m];
		    	  estid[0] = "";
		    	  query="SELECT est.estid,e.eid,e.ename,est.datetimestamp FROM exam e,examstudenttable est WHERE e.eid=est.eid and est.sid="+Dashboard.sid;
			  		try {
			  			ResultSet r = Database.execute(query);
			  			int i=1;
			  			while (r.next()) {
			  				estid[i] = r.getString(1);
			  				results[i][0] = r.getString(2);
			  				results[i][1] = r.getString(3);
			  				results[i][2] = r.getString(4);
			  				i++;
			  			}
			  			
			  		} catch (SQLException e1) {
			  			// TODO Auto-generated catch block
			  			e1.printStackTrace();
			  		}
				/*-------------------ResultsPanel creation------------------*/
		    	  /*String[][] results = new String[][]{	{"Exam ID","Exam Title","Conducted On"},
						{"JEE124","JEE 2016","20-01-2016"},
						{"MANIT53","M.AN.I.T. Exam","12-12-2015"}
					};*/
					try {
					rsp = new DashboardResultsPanel(estid,results);
					rsp.setBounds(Dashboard.leftPanelWidth, Dashboard.topPanelHeight, Dashboard.rightPanelWidth, Dashboard.bothPanelHeight);
					Dashboard.frame.add(rsp);
					rsp.setVisible(false);
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
		    	  /*----------------------------------------*/
					if(e.getActionCommand().equals("Dashboard")){
			    		  //System.out.println("\"Dashboard\" Button Clicked !");
			    		  //--ResultsPanel.rdp.setVisible(false);
						//--esp.setVisible(false);
						//--rsp.setVisible(false);
						//--dashboard.rp.setVisible(true);
			    		  //dashboard.frame.setVisible(false);
			    		  //dashboard.frame.setVisible(true);
						display(Dashboard.rp);
			    	}else if(e.getActionCommand().equals("Exams")){
			    		display(esp); 
			    		/* Displaying ExamShowPanel */
			    		//--dashboard.frame.add(esp);
			    		//--dashboard.frame.setComponentZOrder(esp, 0);
			    		//--dashboard.frame.setComponentZOrder(rsp, 1);
			    		//--ResultsPanel.rdp.setVisible(false);
			    		//--dashboard.rp.setVisible(false);
			    		//--rsp.setVisible(false);
			    		//--esp.setVisible(true);
			    		//--dashboard.frame.remove(rsp);
			    		 //System.out.println(esp+"\n"+rsp);
			    		 //dashboard.frame.setVisible(false);
			    		 //dashboard.frame.setVisible(true);
			    		//--System.out.println("esp Order : "+dashboard.frame.getComponentZOrder(esp));
			    		//--System.out.println("rsp Order : "+dashboard.frame.getComponentZOrder(rsp));
			    		//--System.out.println("rp Order : "+dashboard.frame.getComponentZOrder(dashboard.rp));
			    		
			     }else if(e.getActionCommand().equals("Check Results")){
			    	 display(rsp);
		    	  }else if(e.getActionCommand().equals("Logout"))
		    	  {
		    		  User_Login ob = new User_Login();
		    		  ob.createGUI();
		    		  ob.frame.setVisible(true);
		    		  
		    		  Dashboard.frame.dispose();
		    		  
	    		//  System.out.println("\"Logout\" Button Clicked !");
	    	  }
	      }
	   }
}

