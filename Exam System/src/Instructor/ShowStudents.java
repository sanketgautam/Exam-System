package Instructor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.*;

import Chart.BarChart;
import Chart.LineChart;
import Chart.PieChart;

public class ShowStudents extends JPanel{
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	int panelwidth = ((int)width)*3/4;
	int panelheight = (int)height;
	Instructor instructor;
	private static final long serialVersionUID = 1L;
	
	
	JPanel[] examListItem;
	int index;
	JLabel[] label1;
	JLabel[] label2;
	JLabel[] label3;
	JLabel[] label4;
	double marks,totalMarks;
	double data[] = new double[10];
	//JButton[] chooseExamButton;
	JButton back,analyseResult;
	
	String students[][];// Fetch exams from database
	int n;
	
	ShowStudents ob;
	String examid,examname;
	ShowStudents(Instructor a,String examid,String examname,String examdate) //throws IOException{
	{
		instructor=a;
		this.examid =examid;
		this.examname = examname;
		ob =this;
		
		setLayout(null);
		setBounds((int)(width/4),0,(int)((width*3)/4),(int)height);
		setBackground(Color.YELLOW);
				
		getStudents(this.examid);
		
		examListItem = new JPanel[n];
		
		label1 = new JLabel[n];
		label2 = new JLabel[n];
		label3 = new JLabel[n];
		label4 = new JLabel[n];
		
		
		
		back = new JButton("Back");
		
		analyseResult = new JButton("Analyse Result");
		
		analyseResult.setBounds((int)(panelwidth*0.5)-50+10, (int)(2*panelheight/3)+50, 120, 40);
		
		back.setBounds((int)(panelwidth*0.5)-50+10, (int)(2*panelheight/3)+100, 120, 40);
		
		
		add(back);
		add(analyseResult);
		
		JLabel title = new JLabel("Results  |    \t "+examid+"   :  "+examname+"     |    "+examdate);
		title.setBounds((int)(width*0.1), (int)(panelheight*0.05), 500, 50);
        add(title);
        displayList();
        back.addActionListener(new eventAction());
        analyseResult.addActionListener(new eventAction());
        
        
	}
	void displayList()
	{
		index=0;
		/*Getting Bounds of the panel*/
		Rectangle bounds = getBounds();
		
		int x = bounds.x;
		int y = bounds.y;
		
		int yi = (int)(height*0.1);
		
		
		JPanel title = new JPanel();
		title.setLayout(null);
		title.setBackground(Color.WHITE);
		title.setBounds((int)(0.1*width), yi, 800, 50);
		JLabel[] heading = new JLabel[4];
		Font f=new Font("Century Gothic",Font.LAYOUT_LEFT_TO_RIGHT,15);
		heading[0] = new JLabel("Student Id");
		heading[1] = new JLabel("Name");
		heading[2] = new JLabel("Marks");
		heading[3] = new JLabel("Total Marks");
		
		heading[0].setFont(f);
		heading[1].setFont(f);
		heading[2].setFont(f);
		heading[3].setFont(f);
		
		Rectangle bounds2 = title.getBounds();
		//int x2 = bounds2.x;
		//int y2 = bounds2.y;
		int width2 = (int)bounds2.getWidth();
		int height2 = (int)bounds2.getHeight();
		yi+=50;
		heading[0].setBounds((int)(0.05*width2)-20, (int)(0.05*height2), 150, 50);
		heading[1].setBounds((int)(0.05*width2)+100, (int)(0.05*height2), 300, 50);
		heading[2].setBounds((int)(0.05*width2)+300, (int)(0.05*height2), 200, 50);
		heading[3].setBounds((int)(0.05*width2)+450, (int)(0.05*height2), 200, 50);
		
		title.add(heading[0]);
		title.add(heading[1]);
		title.add(heading[2]);
		title.add(heading[3]);
		
		add(title);
		JPanel container = new JPanel();
		container.setLayout(null);
		yi=0;
		
		
		
		
		
		for(String[] exam : students){
			examListItem[index] = new JPanel();
			
			examListItem[index].setLayout(null);
			if(index%2==0)
				examListItem[index].setBackground(Color.GRAY);
			else
				examListItem[index].setBackground(Color.LIGHT_GRAY);
			    examListItem[index].setBounds(0, yi, 800, 50);
			//adding the details of the exam to the examListItem
				label1[index] = new JLabel(exam[0]);
				label2[index] = new JLabel(exam[1]);
				label3[index] = new JLabel(exam[2]);
				label4[index] = new JLabel(exam[3]);
				
				marks = Double.parseDouble(exam[2]);
				totalMarks= Double.parseDouble(exam[3]);
				
				checkMarks((marks*100.0)/totalMarks);
				
			// Add buttons for Delete Exam , Add Exam , View Exam 
				
				
				try
				{
					BufferedImage bi = ImageIO.read(new File("next.jpg"));
					
					 bi = ImageIO.read(new File("delete.png"));
				
					
					
					
				}catch(Exception e){
					//do something here
				}
				/*Getting Bounds of the panel*/
				bounds2 = examListItem[index].getBounds();
				//int x2 = bounds2.x;
				//int y2 = bounds2.y;
				width2 = (int)bounds2.getWidth();
				height2 = (int)bounds2.getHeight();
				//int yi2 = (int)(height2*0.2);
				//setting bounds to the labels inside the list element
				label1[index].setBounds((int)(0.05*width2), (int)(0.05*height2), 50, 50);
				label2[index].setBounds((int)(0.05*width2)+100, (int)(0.05*height2), 300, 50);
				label3[index].setBounds((int)(0.05*width2)+300, (int)(0.05*height2), 200, 50);
				label4[index].setBounds((int)(0.05*width2)+450, (int)(0.05*height2), 200, 50);
				
				
				yi+=(50);
				//adding labels inside the examListItem
				examListItem[index].add(label1[index]);
				examListItem[index].add(label2[index]);
				examListItem[index].add(label3[index]);
				examListItem[index].add(label4[index]);
				
				
				
				
				
				
			/*----------------------------------------------*/
			//adding examListItem to the Main ExamShowPanel
			//add(examListItem[index]);
		    container.add(examListItem[index]);
			container.setBounds((int)(0.1*width), (int)(height*0.1)+50, 800, Math.min(50*5, yi));
			add(container);
			index++;
		}
		for(int i=0;i<10;i++)
		{
			data[i]= data[i]*100/students.length;
		}
		
	}
	void checkMarks(double mark)
	{
		if(mark>=0&&mark<10)
			data[0]++;
		else if(mark>=10&&mark<20)
			data[1]++;
		else if(mark>=20&&mark<30)
			data[2]++;
		else if(mark>=30&&mark<40)
			data[3]++;
		else if(mark>=40&&mark<50)
			data[4]++;
		else if(mark>=50&&mark<60)
			data[5]++;
		else if(mark>=60&&mark<70)
			data[6]++;
		else if(mark>=70&&mark<80)
			data[7]++;
		else if(mark>=80&&mark<90)
			data[8]++;
		else if(mark>=90&&mark<100)
			data[9]++;
		//numberOfStudents++;
		
	}
	void  analyseResult()
	{
		String type[] ={"Bar Chart ","Pie Chart","Line Chart"};
		
		int choice = JOptionPane.showOptionDialog(this, "Select Type Of Graph ", "", 1, 1, null,type, "");
		switch(choice)
		{
		case 0:
			BarChart ob = new BarChart(examid+" - "+examname,data);
			break;
		case 1:
			PieChart ob1 = new PieChart(examid+" - "+examname,data);
			break;
		case 2:
			LineChart ob2 = new LineChart(examid+" - "+examname,data);
			break;
		}
	}
	
	void getStudents(String examid)
	{
		
/*		students = new String[][]{{"S1","Avul Pakir ","80"},
			{"S2","Abhishek","70"},
			{"S3","Vansh ","75"},
			{"S4","Ashu","80"},
			{"S5","Abhijeet","78"},
			{"S6","Someone","50"},
			{"S7","Aaabb","60"} };*/
			
			String qry,qry2 ;
//				qry1 = "SELECT COUNT(sid) from student";
			String qry3= "SELECT count(S.sid) "
					+ "FROM Student S,ExamStudentTable E,Result R "
					+ "WHERE E.eid = "+examid+" AND S.sid = E.sid  AND R.estid = E.estid "
					+ "ORDER BY(S.sid)";
			qry = "SELECT S.sid,S.first_name,S.last_name,R.Marks_Obtained,R.Total_Marks "
					+ "FROM Student S,ExamStudentTable E,Result R "
					+ "WHERE E.eid = "+examid+" AND S.sid = E.sid  AND R.estid = E.estid "
					+ "ORDER BY(S.sid)";
			try {
				//ResultSet rs = dbc.executeQuery(qry1);
				
				Statement stmt =Instructor.dbc.conn.createStatement();
				ResultSet rs = stmt.executeQuery(qry3);
				if(rs.next())
				{
					n = rs.getInt(1);
					students = new String[n][4];
				}
				else
				{
					n=0;
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

			
				try {
					//ResultSet rs = dbc.executeQuery(qry1);
					
					Statement stmt =Instructor.dbc.conn.createStatement();
					ResultSet rs = stmt.executeQuery(qry);
					int i=0;
						
						String sname;
						int sid,smarks,stotal;
						
						while(rs.next())
						{
							sid = rs.getInt(1);
							sname = rs.getString(2)+" "+rs.getString(3);
							smarks = rs.getInt(4);
							stotal   = rs.getInt(5);
							students[i][0]="S"+sid;
							students[i][1]=sname;
							//System.out.println("student answered : "+students[i][1]);
							students[i][2]=""+smarks;
							students[i][3]=""+stotal;
							i++;
						}
					
					//System.out.println("number Of students "+n);
					
					//stmt.close();
					
								
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//  n = number of exams  		
				
			}

	

	public class eventAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == (Object)back)
			{
				ResultAnalysis ob = new ResultAnalysis(instructor);
				instructor.addRightPanel(ob);
			}
			if(e.getSource() == (Object)analyseResult)
			{
				analyseResult();
				
				
			}
		}
	}
}