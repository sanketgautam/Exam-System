package Instructor;
import javax.imageio.ImageIO;
import javax.swing.*;

import Login.dbConnector;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.*;
import java.io.IOException;

public class Instructor extends JFrame{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	JPanel oldpanel=null;
	static int instructorid;
	static dbConnector dbc = new dbConnector();
	static public  JFrame frame;
	public Instructor(int id)
	{
		frame = this;
		instructorid = id;
	//	System.out.println("Id : " +id);
		setSize((int)width,(int)height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		getContentPane().setBackground(Color.white);
		setLayout(null);
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Instructor");
		LeftPanel p1 = new LeftPanel(this);
		add(p1);
		Dashboard ob =new Dashboard(this);
		
		
		
	}
	//Remove this main
	public static void main(String args[])
	{
		Instructor ob = new Instructor(1);
	}
	void addRightPanel(JPanel p)
	{
		
		if(oldpanel!=null){
			remove(oldpanel);
			//oldpanel.setVisible(false);
		}
		p.setBounds((int)(width/4),0,(int)((width*3)/4),(int)height);
		getContentPane().add(p);
		oldpanel=p;
		p.setVisible(false);
		p.setVisible(true);
		//repaint();
	}

}
	
	