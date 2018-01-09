package studentpanel;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DashboardResultsPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	int n;
	JPanel[] resultListItem;
	String[] estid;
	JLabel[] label1;
	JLabel[] label2;
	JLabel[] label3;
	JButton[] chooseResultButton;
	public static DashboardResultDisplayPanel rdp = new DashboardResultDisplayPanel();
	DashboardResultsPanel(){
		//empty constructor
	}
	DashboardResultsPanel(String[] estids,String[][] results) throws IOException{
		estid = estids;
		n = results.length;
		resultListItem = new JPanel[n];
		label1 = new JLabel[n];
		label2 = new JLabel[n];
		label3 = new JLabel[n];
		chooseResultButton = new JButton[n]; 
		//setting the layout of panel absolute
		super.setLayout(null);
		/*Getting Bounds of the panel*/
		//Rectangle bounds = super.getBounds();
		//System.out.print("height : "+dashboard.bothPanelHeight);
		//int x = bounds.x;
		//int y = bounds.y;
		int width = Dashboard.rightPanelWidth;
		int height = Dashboard.bothPanelHeight;
		int yi = (int)(height*0.2);
		
		//adding the content to the panel
		if(n==1){
			System.out.println("No Results Available");
			/*Inserting no exams to show label*/
			JLabel jl = new JLabel("No Results Available !");
			jl.setBounds((int)(0.1*width), (int)(height*0.2)+60, 800, 50);
			jl.setHorizontalAlignment(SwingConstants.CENTER);
			super.add(jl);
			/*----------------------------------*/
		}
		int index=0;
		for(String[] result : results){
			resultListItem[index] = new JPanel();
			resultListItem[index].setLayout(null);
			if(index==0)
			{
				resultListItem[index].setBackground(Color.WHITE);
			}else{
				if(index%2==0)
					resultListItem[index].setBackground(Color.lightGray);
				else
					resultListItem[index].setBackground(Color.ORANGE);
			}
			resultListItem[index].setBounds((int)(0.1*width), yi, 800, 50);
			//adding the details of the exam to the examListItem
				label1[index] = new JLabel("E"+result[0]);
				label2[index] = new JLabel(result[1]);
				label3[index] = new JLabel(result[2]);
				chooseResultButton[index] = new JButton();
				chooseResultButton[index].setBackground(Color.white);
				try
				{
					BufferedImage bi = ImageIO.read(new File("next.jpg"));
					chooseResultButton[index].setIcon(new ImageIcon(bi));
				}catch(Exception e){
					//do something here
				}
				/*Getting Bounds of the panel*/
				Rectangle bounds2 = resultListItem[index].getBounds();
				//int x2 = bounds2.x;
				//int y2 = bounds2.y;
				int width2 = (int)bounds2.getWidth();
				int height2 = (int)bounds2.getHeight();
				//int yi2 = (int)(height2*0.2);
				//setting bounds to the labels inside the list element
				label1[index].setBounds((int)(0.05*width2), (int)(0.05*height2), 50, 50);
				label2[index].setBounds((int)(0.05*width2)+100, (int)(0.05*height2), 300, 50);
				label3[index].setBounds((int)(0.05*width2)+400, (int)(0.05*height2), 200, 50);
				chooseResultButton[index].setBounds((int)(0.05*width2)+650, (int)(0.25*height2),30, 30);
				chooseResultButton[index].addActionListener(new CustomActionListener());
				yi+=(50);
				//adding labels inside the examListItem
				resultListItem[index].add(label1[index]);
				resultListItem[index].add(label2[index]);
				resultListItem[index].add(label3[index]);
				if(index!=0)
					resultListItem[index].add(chooseResultButton[index]);
			/*----------------------------------------------*/
			//adding examListItem to the Main ExamShowPanel
			super.add(resultListItem[index]);
			index++;
		}

		//Doing the reamainsssssssing stuff
		JLabel headingLabel = new JLabel("Exam Results");
		headingLabel.setBounds((int)(width*0.5)-50, (int)(height*0.05), 200, 50);
        /*edit = new JButton("Edit Details");
        cPassword = new JButton("Change Password");
        edit.setBounds((int)(0.3*width), yi+100, 200, 50);
        cPassword.setBounds((int)(0.3*width)+220, yi+100, 200, 50);
        /*Adding event listener to the buttons*/
        //edit.addActionListener(new CustomActionListener());
        /*Adding Labels and Buttons onto the panel*/
        super.add(headingLabel);
        /*super.add(edit);
        super.add(cPassword);*/
	}
	class CustomActionListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	          //do something
	    	  //System.out.println(e.getSource());
	    	  JButton b = (JButton)e.getSource();
	    	  System.out.println("button : "+b);
	    	  System.out.println("n is : "+n);
	    	  for(int index=0;index<n;index++)
	    	  {	  
	    		  //System.out.println("button"+index+" : "+chooseResultButton[index]);
	    		  if(chooseResultButton[index]==b){
	    			  System.out.println(label1[index].getText()+" ExamResult Button Clicked");
	    			  String id = estid[index].substring(1);
	    			  /*String[][] result = {{"Student ID : ","APJ234"},
	    					  			   {"Student Name : ","APJ Kalam"},
	    					  			   {"Total Questions : ","200"},
	    					  			   {"Questions Attempted : ","124"},
	    					  			   {"Correct Answers : ","95"},
	    					  			   {"Marks Obtained : ","252"},
	    					  			   {"Total Marks : ","400"}
	    					  			   };*/
	    			  String[][] result = new String[6][2];
	    			  /*-----------------Getting data from database----------------------*/
	   
			    	  result[0][0] = "Student ID :";
			    	  //result[0][1] = "Student Name :";
			    	  result[1][0] = "Total Questions : ";
			    	  result[2][0] = "Questions Attempted : ";
			    	  result[3][0] = "Correct Answers : ";
			    	  result[4][0] = "Marks Obtained : ";
			    	  result[5][0] = "Total Marks : ";
			    	  
			    	  String query="SELECT total_questions,questions_attempted,correct_answer,marks_obtained,total_marks  FROM result WHERE estid='"+estid[index]+"'";
				  	  System.out.println("------------"+query);
			    	  try {
				  			ResultSet r = Database.execute(query);
				  			int i=1;
				  			while (r.next()) {
				  				
				  				result[0][1] = "STD"+Dashboard.sid;
				  				result[1][1] = r.getString(1);
				  				result[2][1] = r.getString(2);
				  				result[3][1] = r.getString(3);
				  				result[4][1] = r.getString(4);
				  				result[5][1] = r.getString(5);
				  				i++;
				  			}
				  			System.out.println("Student ID : "+result[0][1]);
				  			System.out.println("Total Questions : "+result[1][1]);
				  			System.out.println("Questions Attempted : "+result[2][1]);
				  			System.out.println("Correct Answers : "+result[3][1]);
				  			System.out.println("Marks Obtained : "+result[4][1]);
				  			System.out.println("Total Marks : "+result[5][1]);
				  		} catch (SQLException e1) {
				  			// TODO Auto-generated catch block
				  			e1.printStackTrace();
				  		}
	    			  /*-----------------------------------------------------------------*/
	    			  
				  	  rdp = new DashboardResultDisplayPanel(estid[index],result);
	    			  //LeftPanel.this.rsp.setVisible(false);
	    			  DashboardLeftPanel.rsp.setVisible(false);
	    			  DashboardLeftPanel.esp.setVisible(false);
	    	    	  Dashboard.rp.setVisible(false);
	    			  rdp.setBounds(Dashboard.leftPanelWidth, Dashboard.topPanelHeight, Dashboard.rightPanelWidth, Dashboard.bothPanelHeight);
	    			  //dashboard.frame.setComponentZOrder(rdp,1);
	    			  //--rdp.setVisible(true);
	    			//--dashboard.frame.add(rdp);
	    			  	DashboardLeftPanel.display(rdp);
	    			  //dashboard.frame.setVisible(false);
	    			  //dashboard.frame.setVisible(true);
	  	    		  break;
	    		  }
	    	  }
	      }
	   }
}
