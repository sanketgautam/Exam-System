package Login;
import javax.imageio.ImageIO;
import javax.swing.*;

import AdminPanel.MultiplePanels;
import Instructor.Instructor;
import SignUp.SignStudent;
import studentpanel.Dashboard;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class User_Login {
	
	
	static dbConnector dbc =new dbConnector(); // ----------------------------------------------connector to database examsystem
	Statement stmt = null;
	
	public static JFrame frame = new JFrame();
	static circImage photo = new circImage();
	JLabel title = new JLabel("");
	//--------------------------
	
	public static JPanel panel1 = new JPanel();
	public static JPanel panel2 = new JPanel();
	
	//--------------------------
	
	static JTextField enterId = new JTextField("Login ID ");
	
	JPasswordField pass = new JPasswordField();
	
	
	static JLabel login = new JLabel("Login");
	static JLabel wrongId = new JLabel("Incorrect \n ID!!");
	JLabel wrongPass = new JLabel("Incorrect \nPassword!!");
	
	static JButton next = new JButton("Next > ");
	static JButton signup = new JButton("Sign Up");
	JButton back = new JButton(" < Back ");
	JButton log = new JButton(" Log In ");
	 static Font f =new Font("Arial",Font.PLAIN,15);
	
	//------------------------------
	
	static BufferedImage bimg;
	static File bfile;
	ImageIcon img;
	
	
	//--------------------------------
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	//-------------------------------
	int type,logid; // Type of Login
	Rectangle frameBounds;

	public User_Login()
	{
		
	       try {
	        	//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    	   UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	        }
	        catch (ClassNotFoundException e) {}
	        catch (InstantiationException e) {}
	        catch (IllegalAccessException e) {}
	        catch (UnsupportedLookAndFeelException e) {}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setIconImage(new Image);
	//	frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.white);
		frame.setLayout(null);
		
		//--------------------------------------------------------- Setting tooltip texts for textfields
		
		pass.setToolTipText("Password");
		enterId.setToolTipText("Login Id");
		
		//----------------------------------------------------------
		
		panel1.setLayout(null);
		panel2.setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frameBounds = frame.getMaximizedBounds();
		panel1.setBounds((int)width/2-200,(int)height/2-200,400,500);
		panel2.setBounds((int)width/2-200,(int)height/2-200,400,500);
		title.setBounds((int)width/2-300,30,600,100);
		
		try 
		{
			bfile= new File("images\\title.png");
			bimg = ImageIO.read(bfile);
			title.setIcon(new ImageIcon(bimg));
			
		}catch(Exception e){}
		
		frame.add(title);
		frame.setVisible(true);
		frame.repaint();
	}
	public static void main(String args[])
	{
		/*try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}*/
		User_Login ob = new User_Login();
		ob.addListeners();
		ob.createGUI();
	}
	void addListeners()
	{
		enterId.addMouseListener(new eventMouse());
		pass.addMouseListener(new eventMouse());
		enterId.addActionListener(new eventAction());
		next.addActionListener(new eventAction());
		back.addActionListener(new eventAction());
		signup.addActionListener(new eventAction());
		log.addActionListener(new eventAction());
	}
	public static void createGUI() // Display 1st Panel i.e. Login 
	{
		panel2.setVisible(false);
		login.setText("Login");
		int height,width;
		Rectangle panelBounds  = new Rectangle();
		panelBounds = panel1.getBounds();	
		//next.setForeground(Color.BLUE);
		next.setBackground(Color.blue);
		
		//------------------------------
		
		try 
		{
			bfile= new File("images\\next.png");
			bimg = ImageIO.read(bfile);
			next.setIcon(new ImageIcon(bimg));
			bfile = new File("images\\signup.png");
			bimg = ImageIO.read(bfile);
			signup.setIcon(new ImageIcon(bimg));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//--------------------------------
		
		height=panelBounds.height;
		width = panelBounds.width;
		
		int buttonWidth = 200;
		int buttonHeight = 30;
		
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setBounds(0,10,width,100);
		
		enterId.setBounds(width/2-buttonWidth/2,height/2,buttonWidth,buttonHeight+10);
		enterId.setText("Login ID");
		enterId.setForeground(Color.GRAY);
		next.setBounds(width/2-buttonWidth/2,height/2+50,buttonWidth,buttonHeight);
		next.setActionCommand("next");
		signup.setBounds(width/2-buttonWidth/2,height/2+buttonHeight+100,buttonWidth,buttonHeight);
		
		photo.setDefaultImage("images\\default.png");//Replace with the query to insert default image
		photo.setBounds(width/2-70,100,150,130);

		wrongId.setBounds(width/2+buttonWidth/2+10,height/2-50+50,buttonWidth,buttonHeight+10);
		wrongId.setVisible(false);

	//--------Adding Components to First Panel ---------
		login.setFont(f);
		panel1.add(login);
		panel1.add(photo);
		panel1.add(wrongId);
		panel1.add(enterId);
		panel1.add(next);
		panel1.add(signup);
		frame.add(panel1);
	// ------------------------------------------------
		
		panel1.setVisible(true);
		
	}
	
	void createGUI2(String nm,byte b[]) // Display 2nd Panel i.e. Login Page 
	{
		panel1.setVisible(false);
		Rectangle panelBounds  = new Rectangle();
		panelBounds = panel2.getBounds();
		int height,width;	
		height=panelBounds.height;
		width = panelBounds.width;
		int buttonWidth = 200;
		int buttonHeight = 30;
		//enterId.setBounds(width/2-100,10,150,100);
		//pass.setBounds(width/2-100,10,150,100);
		login.setText(nm); //Get name from database
		login.setBounds(width/2-(login.getText().length())*10,10,login.getText().length()*20,100);
		//enterId.setBounds(width/2-buttonWidth/2,height/2,buttonWidth,buttonHeight+10);
		pass.setBounds(width/2-buttonWidth/2,height/2,buttonWidth,buttonHeight+10);
		
		//pass.setForeground(Color.GRAY);
		log.setBounds(width/2-buttonWidth/2,height/2+50,buttonWidth,buttonHeight);
		back.setBounds(width/2-buttonWidth/2,height/2+buttonHeight+100,buttonWidth,buttonHeight);
		
		photo.setImage(b);
		photo.setBounds(width/2-70,100,150,130);
		wrongPass.setBounds(width/2+buttonWidth/2+10,height/2-50+50,buttonWidth,buttonHeight+10);
		wrongPass.setVisible(false);
		
		log.setText("Login");
		log.setActionCommand("Login");

		try 
		{
			bfile= new File("images\\login.png");
			bimg = ImageIO.read(bfile);
			log.setIcon(new ImageIcon(bimg));
			bfile = new File("images\\back.png");
			bimg = ImageIO.read(bfile);
			back.setIcon(new ImageIcon(bimg));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	// ----------- Adding Components ----------------
		
		panel2.add(login);
		panel2.add(photo);
		panel2.add(wrongPass);
		panel2.add(pass);
		panel2.add(log);
		panel2.add(back);
		frame.add(panel2);
		frame.repaint();
		
	// ----------------------------------------------
	}

	
	
	
	void createGUI2(String nm) // Display 2nd Panel i.e. Login Page 
	{
		panel1.setVisible(false);
		Rectangle panelBounds  = new Rectangle();
		panelBounds = panel2.getBounds();
		int height,width;	
		height=panelBounds.height;
		width = panelBounds.width;
		int buttonWidth = 200;
		int buttonHeight = 30;
		//enterId.setBounds(width/2-100,10,150,100);
		//pass.setBounds(width/2-100,10,150,100);
		
		login.setText(nm); //Get name from database
		login.setBounds(width/2-(login.getText().length())*10,10,login.getText().length()*20,100);
		
		//enterId.setBounds(width/2-buttonWidth/2,height/2,buttonWidth,buttonHeight+10);
		pass.setBounds(width/2-buttonWidth/2,height/2,buttonWidth,buttonHeight+10);
		
		//pass.setForeground(Color.GRAY);
		log.setBounds(width/2-buttonWidth/2,height/2+50,buttonWidth,buttonHeight);
		back.setBounds(width/2-buttonWidth/2,height/2+buttonHeight+100,buttonWidth,buttonHeight);
		
		
		photo.setBounds(width/2-70,100,150,130);
		wrongPass.setBounds(width/2+buttonWidth/2+10,height/2-50+50,buttonWidth,buttonHeight+10);
		wrongPass.setVisible(false);
		
		log.setText("Login");
		log.setActionCommand("Login");

		try 
		{
			bfile= new File("images\\login.png");
			bimg = ImageIO.read(bfile);
			log.setIcon(new ImageIcon(bimg));
			bfile = new File("images\\back.png");
			bimg = ImageIO.read(bfile);
			back.setIcon(new ImageIcon(bimg));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	// ----------- Adding Components ----------------
		
		panel2.add(login);
		panel2.add(photo);
		panel2.add(wrongPass);
		panel2.add(pass);
		panel2.add(log);
		panel2.add(back);
		frame.add(panel2);
		frame.repaint();
		
	// ----------------------------------------------
	}

	
	
	
	void vibrateTextField()
	{
		Rectangle b = enterId.getBounds();
		Color c = new Color (255, 180, 180);
		enterId.setBackground(c);
		
		//.red);
	//	int x =b.x;
	//	System.out.println("Vibrating");
	///	for(int i=0;i<=360;i++)
//		{
	//		double angle;
	//		angle = i*Math.PI/180.0;
	//		System.out.println(angle);
			//enterId.setBounds(x+i,b.y,b.width,b.height);
	//		enterId.move(x+i, b.y);
			//enterId.setBounds(x+(int)(Math.sin(angle)*1000),b.y,b.width,b.height);
	//		int del=500000;
		//	while(del--!=0);
//		}
		//enterId.setBounds(b);
	}
	boolean CheckId(String id)
	{
		boolean flag=false;
		
		
		if(id.startsWith("INST"))
		{
			type = 1;
			try{
				stmt = dbc.conn.createStatement();
				logid = Integer.parseInt(id.substring(4));
			
				System.out.println("Inst Log id : "+logid);
			String qry ;
			qry = "SELECT iid FROM INSTRUCTOR WHERE iid = "+logid;
			ResultSet rs = stmt.executeQuery(qry);
			//System.out.println("Login : " + rs.getInt(1));
			if(rs!=null)
			{
				
				flag=true;
			}
			
			//flag=true;
			}
			catch(Exception e)
			{
				System.err.println("In CheckId : " +e.getMessage());
			}
			finally
			{
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		else if(id.startsWith("STD"))
		{
			type = 0;
			
			try{
				stmt = dbc.conn.createStatement();
				
				logid = Integer.parseInt(id.substring(3));
				String st;
				
				String qry ;
				qry = "SELECT sid FROM STUDENT "+
					  "WHERE sid = \'"+logid+"\'";
				ResultSet rs = stmt.executeQuery(qry);
				if(rs.next())
				{
					flag=true;
				}
				//flag=true;
				}
				catch(Exception e)
				{
					System.err.println("In CheckId : " +e.getMessage());
				}
				finally
				{
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		else if(id.equals("admin"))
		{
			type =2;
			flag=true;
		}
		
		
		//Code to check for id's validity
		//and if it matches a certain type
		
		
		
		return (flag);
	}
	boolean checkPassword(String pass)
	{
		boolean flag=false;
		if(type==0)//Student
		{
			try{
				stmt = dbc.conn.createStatement();
				String qry ;
				qry = "SELECT password FROM STUDENT "+   // check name of column
					  "WHERE sid = \'"+logid+"\'";
				ResultSet rs = stmt.executeQuery(qry);
				if(rs.next())
				{
					if(rs.getString(1).equals(pass))  //Should it be zero
					{
						flag=true;
					}
					
				}
				}
				catch(Exception e)
				{
					System.err.println("In checkPassword : " +e.getMessage());
				}
				finally
				{
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		else if(type==1)//Instructor
		{
			try{
				stmt = dbc.conn.createStatement();
				String qry ;
				qry = "SELECT password FROM INSTRUCTOR "+   // check name of column
					  "WHERE iid = \'"+logid+"\'";
				ResultSet rs = stmt.executeQuery(qry);
				if(rs.next())
				{
					if(rs.getString(1).equals(pass))  //Should it be zero
					{
						flag=true;
					}
					
				}
				}
				catch(Exception e)
				{
					System.err.println("In checkPassword : " +e.getMessage());
				}
				finally
				{
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		else if(type == 2)//Admin
		{
			if(pass.equals("admin"))
				flag=true;
		}
		
		return(flag);
	}
	class eventMouse implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==(Object)enterId) // Reset (Clear) Text Field
			{
				enterId.setText("");
				enterId.setForeground(Color.BLACK);
				Color c = new Color (255, 255, 255);
				enterId.setBackground(c);
				wrongId.setVisible(false);
			}
			if(e.getSource()==(Object)pass) // Reset (Clear) Text Field
			{
				pass.setText("");
				pass.setForeground(Color.BLACK);
				Color c = new Color (255, 255, 255);
				pass.setBackground(c);
				wrongPass.setVisible(false);
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	class eventKey implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == (Object)enterId)
			{
				 
			}
		}
		
		
	}
	int askType()
	{
		String type[] ={"Student SignUp","Instructor SignUp"};
		
		int choice = JOptionPane.showOptionDialog(frame, "Select Type ", "", 1, 1, null,type, "");
		//System.out.println("option    "+choice);
		return(choice+1);
		
	}
	class eventAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == (Object)next)
			{
				String id;
				
				id = enterId.getText();
				String name = null,img=null;
				byte[] arr = null;
				
				if(CheckId(id)) // Check the type of ID and match it (Create a separate Function for it)
				{
					
				if(type == 0)
				{
					try{
						stmt = dbc.conn.createStatement();
						String qry ;
						qry = "SELECT first_name,last_name,image FROM STUDENT "+   // check name of column
							  "WHERE sid = \'"+logid+"\'";
						ResultSet rs = stmt.executeQuery(qry);
						if(rs.next())
						{
							
							name = rs.getString(1)+" "+rs.getString(2);
							arr = rs.getBytes(3);
							
							//frame.setTitle(img);
						}
						}
						catch(Exception exp)
						{
							System.err.println("In getting img : " +exp.getMessage());
						}
						finally
						{
							try {
								stmt.close();
							} catch (SQLException exp) {
								// TODO Auto-generated catch block
								exp.printStackTrace();
							}
						}
					createGUI2(name,arr);
				}	
				else if(type == 1)
				{
					try{
						stmt = dbc.conn.createStatement();
						String qry ;
						qry = "SELECT first_name,last_name,image FROM INSTRUCTOR "+   // check name of column
							  "WHERE iid = \'"+logid+"\'";
						ResultSet rs = stmt.executeQuery(qry);
						if(rs.next())
						{
							name = rs.getString(1)+" "+rs.getString(2);
							arr = rs.getBytes(3);
						}
						}
						catch(Exception exp)
						{
							System.err.println("In getting img : " +exp.getMessage());
						}
						finally
						{
							try {
								stmt.close();
							} catch (SQLException exp) {
								
								exp.printStackTrace();
							}
						}
					createGUI2(name,arr);
				}
				else if(type == 2)
				{
					name ="admin";
					photo.setDefaultImage("images\\admin.png");
					createGUI2(name);
					
				}
				
				//ask if he has saved image type as well or not
					pass.setText("");
					panel1.setVisible(false);
					
					
					panel2.setVisible(true);
				}
				else
				{
					wrongId.setVisible(true);
					Color c = new Color (255, 180, 180);
				    enterId.setBackground(c);
				}
				
			}
			else if(e.getSource() == (Object)back)
			{				
				panel2.setVisible(false);
				createGUI();
				panel1.setVisible(true);
			}
			else if(e.getSource() == (Object)signup)
			{	
				int choice = askType();
			
				SignStudent ob = new SignStudent(choice);
				frame.setVisible(false);
				//frame.dispose();
				// Open signup page
				
			}
			else if(e.getSource() == (Object)log)
			{	
				String st = pass.getText();
				//Write Query for getting password corresponding to id
				boolean flag = checkPassword(st);
				if(flag)
				{
					if(type ==0)
					{
						Dashboard ob = new Dashboard(logid);
						frame.dispose();
						//frame.setVisible(false);
						
						
					}
					else if(type ==1)
					{
						
						Instructor ob = new Instructor(logid);
						ob.setVisible(true);
						frame.setVisible(false);
					}
					else 
					{
						MultiplePanels ob = new MultiplePanels();
						
						frame.setVisible(false);
						
						//frame.dispose();
						
						//frame.setVisible(false);
					}
					
				}
				else
				{
					Color c = new Color (255, 180, 180);
				    pass.setBackground(c);
					wrongPass.setVisible(true);
				}

			}
		}
		
	}
}
class circImage extends JPanel
{
	BufferedImage img;
	String fileName;
	ImageIcon i;
	File file;
	circImage()
	{		
		
	}
	public void paint(Graphics g) 
	{
		super.paint(g);
		
	
		g.setClip(new Ellipse2D.Float(25,10,100,100)); // Clipping of Image
		
	    g.drawImage(img, 0, 0, 150 , 150, null);
	    
	    
	    repaint();
	}
	public void setImage(byte b[])
	{
		/*for(byte bt : b)
			System.out.println(bt);*/
		i= new ImageIcon(b);
		
		img = new BufferedImage(i.getIconWidth(),i.getIconHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g2 = img.createGraphics();
	    i.paintIcon(null,g2, 0,0);
	    g2.dispose();
	    
	//	img = i.getImage(b);
		//img = new BufferedImage(b);
	}
	public void setDefaultImage(String st)
	{
		file = new File(st);
		 	try 
			{
				img = ImageIO.read(file);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			} 
	}
	public String getImage()
	{
		return(fileName);
	}
}
