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
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class DashboardExamShowPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int n;
	JPanel[] examListItem;
	//JPanel container;
	JLabel[] label1;
	JLabel[] label2;
	JLabel[] label3;
	JLabel[] label4;
	JLabel[] label5;
	JButton[] chooseExamButton;
	public static Exam examOb;
	DashboardExamShowPanel(){
		//empty constructor
	}
	DashboardExamShowPanel(String[][] exams) throws IOException{
		
		n = exams.length;
		
		examListItem = new JPanel[n];
		label1 = new JLabel[n];
		label2 = new JLabel[n];
		label3 = new JLabel[n];
		label4 = new JLabel[n];
		label5 = new JLabel[n];
		chooseExamButton = new JButton[n]; 
		//setting the layout of panel absolute
		super.setLayout(null);
		/*Getting Bounds of the panel*/
		Rectangle bounds = super.getBounds();
		//System.out.print("height : "+dashboard.bothPanelHeight);
		int x = bounds.x;
		int y = bounds.y;
		int width = Dashboard.rightPanelWidth;
		int height = Dashboard.bothPanelHeight;
		int yi = (int)(height*0.2);
		//setting bounds on the container panel
		//container.setBounds((int)(0.1*width), (int)(0.1*height), 800, Math.min(yi, 50*10));
		//adding the content to the panel
		int index=0;
		for(String[] exam : exams){
			examListItem[index] = new JPanel();
			examListItem[index].setLayout(null);
			if(index==0)
			{
				examListItem[index].setBackground(Color.WHITE);
			}else{
				if(index%2==0)
					examListItem[index].setBackground(Color.YELLOW);
				else
					examListItem[index].setBackground(Color.PINK);
			}
			examListItem[index].setBounds((int)(0.1*width), yi, 800, 50);
			if(n==1){
				System.out.println("No Exams Available");
				/*Inserting no exams to show label*/
				JLabel jl = new JLabel("No Exams Available !");
				jl.setBounds((int)(0.1*width), (int)(height*0.2)+60, 800, 50);
				jl.setHorizontalAlignment(SwingConstants.CENTER);
				super.add(jl);
				/*----------------------------------*/
			}
			//adding the details of the exam to the examListItem
				label1[index] = new JLabel("E"+exam[0]);
				label2[index] = new JLabel(exam[1]);
				label3[index] = new JLabel(exam[2]);
				label4[index] = new JLabel(exam[3]);
				label5[index] = new JLabel("G"+exam[4]);
				chooseExamButton[index] = new JButton();
				chooseExamButton[index].setBackground(Color.white);
				try
				{
					BufferedImage bi = ImageIO.read(new File("next.jpg"));
					chooseExamButton[index].setIcon(new ImageIcon(bi));
				}catch(Exception e){
					System.out.println("The Exam open button image can't be found !");
					//do something here
				}
				/*Getting Bounds of the panel*/
				Rectangle bounds2 = examListItem[index].getBounds();
				//int x2 = bounds2.x;
				//int y2 = bounds2.y;
				int width2 = (int)bounds2.getWidth();
				int height2 = (int)bounds2.getHeight();
				//int yi2 = (int)(height2*0.2);
				//setting bounds to the labels inside the list element
				label1[index].setBounds((int)(0.05*width2), (int)(0.05*height2), 50, 50);
				label2[index].setBounds((int)(0.05*width2)+100, (int)(0.05*height2), 250, 50);
				label3[index].setBounds((int)(0.05*width2)+400, (int)(0.05*height2), 200, 50);
				label4[index].setBounds((int)(0.05*width2)+580, (int)(0.05*height2), 100, 50);
				label5[index].setBounds((int)(0.05*width2)+650, (int)(0.05*height2), 50, 50);
				chooseExamButton[index].setBounds((int)(0.05*width2)+720, (int)(0.25*height2),30, 30);
				chooseExamButton[index].addActionListener(new CustomActionListener());
				//getting value of n from  database
				if(index>0){
					String ExamID = (label1[index].getText()).substring(1);
					String query="SELECT count(qid) FROM examquestiontable WHERE eid="+ExamID;
					int numberOfQuestions=0;
					try {
						ResultSet r = Database.execute(query);
						while (r.next()) {
							numberOfQuestions = r.getInt(1);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(numberOfQuestions<=1){    //also add logic of datetime of exam
						chooseExamButton[index].setEnabled(false);
					}
				}
				//-------------------------------//
				yi+=(50);
				//adding labels inside the examListItem
				examListItem[index].add(label1[index]);
				examListItem[index].add(label2[index]);
				examListItem[index].add(label3[index]);
				examListItem[index].add(label4[index]);
				examListItem[index].add(label5[index]);
				if(index!=0)
					examListItem[index].add(chooseExamButton[index]);
			/*----------------------------------------------*/
			//adding examListItem to the Main ExamShowPanel
			super.add(examListItem[index]);
			index++;
		}

		//Doing the reamainsssssssing stuff
		JLabel headingLabel = new JLabel("Exams");
		headingLabel.setBounds((int)(width*0.5)-50, (int)(height*0.05), 200, 50);
        
        super.add(headingLabel);
        /*super.add(edit);
        super.add(cPassword);*/
	}
	class CustomActionListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	          //do something
	    	  //System.out.println(e.getSource());
	    	  JButton b = (JButton)e.getSource();
	    	  String examID = null;
	    	  for(int index=0;index<n;index++)
	    	  {
	    		  if(chooseExamButton[index]==b){
	    			  System.out.println(label1[index].getText()+" Exam Button Clicked");
	    			  examID = label1[index].getText();
	    		  }
	    	  }
	    	  examID = examID.substring(1);
	    	  int EID = Integer.parseInt(examID);
	    	  /*Link to @Bharat Code here*/
	    	  	examOb = new Exam(EID);
	    	  Dashboard.frame.setVisible(false);
	      }
	   }
}
