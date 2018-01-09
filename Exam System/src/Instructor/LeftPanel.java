package Instructor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;

import Login.User_Login;

public class LeftPanel extends JPanel{
	int flag,flagop1,flagop2;
	int shift =0;
	//JFrame f = new JFrame();
	JButton l0,l1,l2,l3,l4,lo1,lo2;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	int label_width,label_height,label_shift;
	Instructor i;
	LeftPanel(Instructor a)
	{
		i=a;
		//-----------------------------------------
		
		label_width =(int)( width/4);
		label_height = (int)(height/15);
		label_shift=label_height;
		flag=0;
		flagop1=0;
		flagop2=0;
		
		//-----------------------------------------

		
		
		//-------------------------------------
		setSize((int)(width/4),(int)height);
	//	Color color  = new Color(150,225,44);
		
	//	Color color  = new Color(225,225,225);
		//Color color  = new Color(156,169,141);
		//Color color  = new Color(29,46,62);
		//Color color  = new Color(224,221,212);
		//color = color.getColor("#73C5E1");
		
		Color color  = new Color(160,255,70);
		//setBackground(color);
		setBackground(Color.PINK);
		setLayout(null);
		l0 = new JButton("DashBoard");
		l1 = new JButton("Exam   >");
		l2 = new JButton("Exam Evaluation");
		l3 = new JButton("Result Analysis");
		l4 = new JButton("Log Out");
		lo1 = new JButton("   New Exam");
		lo2 = new JButton("   Delete Exam");
		
		Font f=new Font("Century Gothic",Font.LAYOUT_LEFT_TO_RIGHT,20);
		
		l0.setFont(f);
		l1.setFont(f);
		l2.setFont(f);
		l3.setFont(f);
		l4.setFont(f);
		
		f = new Font("Century Gothic",Font.LAYOUT_LEFT_TO_RIGHT,17);
		lo1.setFont(f);
		lo2.setFont(f);
		
		l0.setHorizontalAlignment(SwingConstants.LEFT);
		l1.setHorizontalAlignment(SwingConstants.LEFT);
		l2.setHorizontalAlignment(SwingConstants.LEFT);
		l3.setHorizontalAlignment(SwingConstants.LEFT);
		l4.setHorizontalAlignment(SwingConstants.LEFT);
		lo1.setHorizontalAlignment(SwingConstants.LEFT);
		lo2.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		
		Color c = new Color(124,207,228);
		
		Color c1 = new Color(215,215,218);
		//Color c = new Color(154,197,208);
		//Color c = new Color(116,197,226);
		//Color c = new Color(67,48,34);
		l0.setBackground(c1);
		l1.setBackground(c1);
		l2.setBackground(c1);
		l3.setBackground(c1);
		l4.setBackground(c1);

		lo1.setBackground(c);
		lo2.setBackground(c);
		
	/*	l1.setForeground(Color.WHITE);
		l2.setForeground(Color.WHITE);
		l3.setForeground(Color.WHITE);
		l4.setForeground(Color.WHITE);
		lo1.setForeground(Color.WHITE);
		lo2.setForeground(Color.WHITE);*/
		
		l0.addMouseListener(new eventMouse());
		l1.addMouseListener(new eventMouse());
		l2.addMouseListener(new eventMouse());
		l3.addMouseListener(new eventMouse());
		l4.addMouseListener(new eventMouse());
		lo1.addMouseListener(new eventMouse());
		lo2.addMouseListener(new eventMouse());
		
		createGUI();
		setVisible(true);
			
	}
	
	
	void createGUI()
	{
		removeAll();
		l0.setBounds(0,50,label_width-40+20,label_height);
		l1.setBounds(0,50+label_height+5,label_width-40+20,label_height);
		lo1.setBounds(0,50+label_height+label_shift*flag+2+5,label_width-60+40,label_height);
		lo2.setBounds(0,50+label_height+label_shift*flag+5*flag+(label_height+2),label_width-60+40,label_height);
		l3.setBounds(0,50+label_height+2*label_shift*flag+15*flag+5+(label_height+5),label_width-40+20,label_height);
		l4.setBounds(0,50+label_height+2*label_shift*flag+15*flag+5+2*(label_height+5),label_width-40+20,label_height);
		//l4.setBounds(0,50+2*label_shift*flag+15*flag+3*(label_height+5),label_width-40+20,label_height);

		/*l1.setBounds(20,50,label_width-40,label_height);
		lo1.setBounds(30,50+label_shift*flag+2,label_width-60,label_height);
		lo2.setBounds(30,50+label_shift*flag+1*flag+(label_height+2),label_width-60,label_height);
		l2.setBounds(20,50+2*label_shift*flag+15*flag+(label_height+5),label_width-40,label_height);
		l3.setBounds(20,50+2*label_shift*flag+15*flag+2*(label_height+5),label_width-40,label_height);
		l4.setBounds(20,50+2*label_shift*flag+15*flag+3*(label_height+5),label_width-40,label_height);*/
		
	//	l3.setVisible(false);
		//l4.setVisible(false);
		add(l0);
		add(l1);
		if(flag==1)
		{
			add(lo1);
			add(lo2);
		}
		//add(l2);
		add(l3);
		add(l4);
		
	}
	class eventMouse implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==(Object)l0)//Display DashBoard
			{
				flagop1=0;
				flagop2=0;
				Dashboard ob = new Dashboard(i);
				
			}
			if(e.getSource()==(Object)l1) // Display New Options 
			{
				if(flag==0)
			    {
					flag=1;
					l1.setText("Exam  v  ");
			    }
				else
				{
					flag=0;
					l1.setText("Exam  >  ");
				}
		  	  	createGUI();
		  	  	repaint();
			}
			if(e.getSource()==(Object)l2)//&&flag1==0) //change Right Panel 
			{				
				ExamShowPanel  p = new ExamShowPanel(i);
				i.addRightPanel(p);
				
				//p.setVisible(false);
				//p.setVisible(true);
				//flagop1=1;
				flagop1=0;
				flagop2=0;
				//p.setVisible(true);
			}
			else if(e.getSource()==(Object)lo1&&flagop1==0)//&&flag1==0) //change Right Panel 
			{				
				
				newExam p1 = new newExam(i);
				i.addRightPanel(p1);
				p1.askExamName();
				flagop1=1;
				flagop2=0;
				
				
				//p1.setVisible(false);
				//p1.setVisible(true);
				//p.setVisible(true);
			}
			else if(e.getSource()==(Object)lo2&&flagop2==0)
			{
				flagop2=1;
				flagop1=0;
				ExamShowPanel  p = new ExamShowPanel(i);
				
				i.addRightPanel(p);
				
			//	p.setVisible(false);
			//	p.setVisible(true);
				
			}
			else if(e.getSource()==(Object)l3)
			{
				flagop2=0;
				flagop1=0;
				ResultAnalysis  p = new ResultAnalysis(i);
				i.addRightPanel(p);
			//	p.setVisible(false);
			//	p.setVisible(true);
				
			}
			else if(e.getSource()==(Object)l4)
			{
				
				i.dispose();
				User_Login ob = new User_Login();
				ob.frame.setVisible(true);
				ob.createGUI();
				
				//ob.frame.repaint();
				
				//ob.panel1.repaint();
				
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
		}
	}
}









//void createGUI2()
//{
//	removeAll();
//	GridLayout l = new GridLayout();
//	l.setColumns(1);
//	l.setRows(15);
//	setLayout(l);
//	l1 = new JLabel("Option 1");
//	l2 = new JLabel("Option 2");
//	l3 = new JLabel("      Option 3");
//	l4 = new JLabel("Option 4");
//	l1.addMouseListener(new eventMouse());
//	
//	l1.setSize((int)(width/4),(int)(height/15));
//	l2.setSize((int)(width/4),(int)(height/15));
//	l3.setSize((int)(width/4),(int)(height/15));
//	l4.setSize((int)(width/4),(int)(height/15));
//	
//	add(l1);
//	if(flag==1)
//		add(l3);
//	add(l2);
//	add(l4);
//	
//}
//
//void createGUI()
//{
//	GridBagLayout l = new GridBagLayout();
//	setLayout(l);
//	
//	GridBagConstraints gbc = new GridBagConstraints();
//	 gbc.gridx=0;
//     gbc.gridy=0;
//     gbc.fill=GridBagConstraints.BOTH;
//     gbc.gridwidth=60;
//     gbc.gridheight=60;
//     
//	l1 = new JLabel("Option 1");
//	l2 = new JLabel("Option 2");
//	l3 = new JLabel("Option 3");
//	l4 = new JLabel("Option 4");
//	
//	l1.setSize((int)(width/4),(int)(height/15));
//	l2.setSize((int)(width/4),(int)(height/15));
//	l3.setSize((int)(width/4),(int)(height/15));
//	l4.setSize((int)(width/4),(int)(height/15));
//	
//	add(l1);
//	add(l2);
//	add(l3);
//	add(l4);
//	
//	repaint();
//	
//}

