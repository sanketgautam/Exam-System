package studentpanel;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DashboardResultDisplayPanel extends JPanel{
	int n;
	public static JPanel[] infoListItem;
	JLabel[] label1;
	JLabel[] label2;
	String estid;
	
	JButton back;
	DashboardResultDisplayPanel(){
		//empty constructor	
	}
	DashboardResultDisplayPanel(String estid1,String[][] result){
		estid = estid1;
		n = result.length;
		infoListItem = new JPanel[n];
		label1 = new JLabel[n];
		label2 = new JLabel[n];
		//setting the layout of panel absolute
		super.setLayout(null);
		int width = Dashboard.rightPanelWidth;
		int height = Dashboard.bothPanelHeight;
		int yi = (int)(height*0.2);
		//adding the content to the panel
		int index=0;
		for(String[] info : result){
			infoListItem[index] = new JPanel();
			infoListItem[index].setLayout(null);
			if(index%2==0)
				infoListItem[index].setBackground(Color.lightGray);
			else
				infoListItem[index].setBackground(Color.white);
			infoListItem[index].setBounds((int)(0.1*width), yi, 800, 50);
			//adding the details of the exam to the examListItem
				label1[index] = new JLabel(info[0]);
				label2[index] = new JLabel(info[1]);
				/*Getting Bounds of the panel*/
				Rectangle bounds2 = infoListItem[index].getBounds();
				//int x2 = bounds2.x;
				//int y2 = bounds2.y;
				int width2 = (int)bounds2.getWidth();
				int height2 = (int)bounds2.getHeight();
				//int yi2 = (int)(height2*0.2);
				//setting bounds to the labels inside the list element
				label1[index].setBounds((int)(0.05*width2), (int)(0.05*height2), 300, 50);
				label2[index].setBounds((int)(0.05*width2)+400, (int)(0.05*height2), 100, 50);
				yi+=(50);
				//adding labels inside the examListItem
				infoListItem[index].add(label1[index]);
				infoListItem[index].add(label2[index]);
			/*----------------------------------------------*/
			//adding examListItem to the Main ExamShowPanel
			super.add(infoListItem[index]);
			index++;
		}
		//Doing the reamainsssssssing stuff
		String query="SELECT e.ename FROM exam e, examstudenttable est WHERE e.eid=est.eid and est.estid='"+estid+"'";
		String ExamName = "";
		
  		try {
  			ResultSet r = Database.execute(query);
  			while (r.next()) {
  				ExamName = r.getString(1);
  			}
  		} catch (SQLException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}
  		
		JLabel headingLabel = new JLabel(ExamName+" Result");
		headingLabel.setBounds((int)(width*0.5)-50, (int)(height*0.05), 200, 50);
        back = new JButton("Go Back");
        /*cPassword = new JButton("Change Password");*/
        back.setBounds((int)(0.42*width), yi+100, 150, 40);
        /*cPassword.setBounds((int)(0.3*width)+220, yi+100, 200, 50);
        /*Adding event listener to the buttons*/
        back.addActionListener(new CustomActionListener());
        /*Adding Labels and Buttons onto the panel*/
        super.add(headingLabel);
        super.add(back);
        /*super.add(cPassword);*/
	}
	class CustomActionListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	    	  //do something here
	    	  DashboardLeftPanel.display(DashboardLeftPanel.rsp);
	    	  //--LeftPanel.esp.setVisible(false);
	    	//--dashboard.rp.setVisible(false);
	    	//--ResultsPanel.rdp.setVisible(false);
	    	//--LeftPanel.rsp.setVisible(true);
	      }
	   }	
}

