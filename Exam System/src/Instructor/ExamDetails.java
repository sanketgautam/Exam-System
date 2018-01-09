package Instructor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Login.dbConnector;

public class ExamDetails extends JFrame{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	
	int panelwidth = ((int)width)*3/4;
	int panelheight = (int)height ;
	JButton date,group,save;
	static dbConnector dbc = new dbConnector();
	JLabel ltime,lduration,lgroup;
	static JLabel ldate;
	JSpinner duration;
	JSpinner hh,mm;
	public static JFrame f;
	public ExamDetails()
	{
		f=this;
		
		setLayout(null);
		//setUndecorated(true);
		setSize(500,500);
		
		//setBackground(Color.GREEN);
		
		setLocationRelativeTo(null);
		duration = new JSpinner();
		lduration = new JLabel("Duration : ");
		ldate = new JLabel("Date  "); 
		date = new JButton("Select Date ");
		lgroup  = new JLabel("Group  ");
		group = new JButton("Select Group ");
		save = new JButton("Save");
		ltime = new JLabel("Time  ");
		
		hh= new JSpinner();
		mm= new JSpinner();
		
		duration.setModel(new SpinnerNumberModel(15, 15, 180, 15));
		hh.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		mm.setModel(new SpinnerNumberModel(0, 0, 59, 15));
		
		
		duration.setBounds(100,10,150,30);
		lduration.setBounds(10,10,150,30);
		date.setBounds(100,50,150,30);
		ldate.setBounds(10,50,150,30);
		group.setBounds(100,100,150,30);
		lgroup.setBounds(10,100,150,30);
		hh.setBounds(60,150,50,30);
		mm.setBounds(150,150,50,30);
		ltime.setBounds(10,150,100,30);
		save.setBounds(50,250,80,30);
		
		
		
	//	setBounds((int)(width/4),0,(int)((width*3)/4),(int)height);
		add(lduration);
		add(ldate);
		add(date);
		add(lgroup);
		add(group);
		add(duration);
		add(save);
		add(hh);
		add(mm);
		add(ltime);
		
		date.addActionListener(new eventAction());
		save.addActionListener(new eventAction());
		group.addActionListener(new eventAction());
		
		//ShowCalendar ob = new ShowCalendar();
	}
	

	int fetchGroups()
	{
		String qry,qry2;
		qry2 = "SELECT count(group_id) FROM instructor_group WHERE iid = "+Instructor.instructorid ;
		qry = "SELECT group_id FROM instructor_group WHERE iid = "+Instructor.instructorid;//Instructor.instructorid;
		ResultSet rs;
		Object gid[] = null;
		int num,i;
		try {
			
			Statement stmt =dbc.conn.createStatement();
			rs = stmt.executeQuery(qry2); 
			System.out.println("Instructor : " +Instructor.instructorid+"");
			if(rs.next())
			{
				num = rs.getInt(1);
				gid = new Object[num];
			//	System.out.println("num : "+num);
			}
			stmt =dbc.conn.createStatement();
			rs = stmt.executeQuery(qry);
				i=0;
				while(rs.next())
				{
					gid[i] =(Object)("G"+ rs.getInt(1));
				//	System.out.println("gid[i] : "+gid[i]);
					i++;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e)
		{}

		String s = (String)JOptionPane.showInputDialog(this,"Select Group : ","",JOptionPane.PLAIN_MESSAGE,null,gid,"");
		return(Integer.parseInt(s.substring(1)));
		
		//If a string was returned, say so.
		//if ((s != null) && (s.length() > 0)) {
		    
		 //   return;
		//}
		
		
		
	}

	
	
	class eventAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == (Object)date)
			{
				f.setEnabled(false);
				
				ShowCalendar ob = new ShowCalendar();
				
				f.setEnabled(true);
				
				repaint();
			}
			else if(e.getSource() == (Object)group)
			{
				int group = fetchGroups();
				lgroup.setText("G"+group);
			}
			else if(e.getSource() == (Object)save)
			{
				f.dispose();
				newExam.exam.setDuration(Integer.parseInt(duration.getValue().toString()));
				newExam.exam.setGroupId(Integer.parseInt(lgroup.getText().substring(1)));
				newExam.exam.setDateTime(ldate.getText()+" "+hh.getValue().toString()+"-"+mm.getValue().toString()+"-00");
				
				Instructor.frame.setEnabled(true);
				
			}
		}
	}
}
