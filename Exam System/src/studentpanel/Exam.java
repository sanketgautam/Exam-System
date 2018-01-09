package studentpanel;


import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;


public class Exam  extends JFrame implements ActionListener{
	ExamRightPanel obj;
	//Connection con;
	static Timer t;
	List<JButton>  buttons;
	int numberOfButtons;
	JRadioButton Opt1,Opt2,Opt3,Opt4;
	JPanel LeftPanel;
	JPanel QuickQuestionPanel;		
	private JPanel InstructionPanel;	
    private Dimension screenSize;
    private JTextField timerTextField;
    private JLabel questionAttempted;
    private JPanel CompositePanel;
    private JButton bProceed;
    private JButton bBack;
   static  ExamLeftPanel obj2;
   ExamGradePanel examGradePanel;
//------------------------------------------------------------------------------------------------
   static int n=0;//no. of Q -> in this class it is used for creating buttons in QuickQpanel
   static String time="3";//will be used in 1st line of instructions ,get value from db,in hour
   static String estid;
   static String dateTimeStamp;
   static String hours="0";
   static String minutes="2";
	/*static String[] questions;
	static	String [] Option1;
	static String [] Option2;
	static	String [] Option3;
	static String [] Option4;
	*/
	/*static String[] correctAnswer;
	static String[] optionChossen;
	*/
  static  String []QID;
  static  String []Questions;//=new String[]{"1","2","3","4","2","3","4","2","3","4","4"};
  static String [] Option1;//=new String[]{"op11","op12","op13","op14","op12","op13","op14","op12","op13","op14","4"};
  static String [] Option2;//=new String[]{"op21","op22","op23","op24","op22","op23","op24","op22","op23","op24","4"};
  static String [] Option3;//=new String[]{"op31","op32","op33","op34","op32","op33","op34","op32","op33","op34","4"};
  static String [] Option4;//=new String[]{"op41","op42","op43","op44","op42","op43","op44","op42","op43","op44","4"};
  static String [] correctOptions;//=new String[]{"1","1","1","1","1","1","1","1","1","1","1"};
	
	
	public static int questionsAttempted=0;
	static int correctAnswers=0;
	static int MarksObtained=0;
	static int totalMarks=0;
	static int EID=0; //get EID from sanket's panel
//--------------------------------------------------------------------------------------------------   
	
   
   
   
   
/*   public static void main(String[] args) {

		
		new Exam(EID);
	}
*/
	/**
	 * Create the application.
	 */
	public Exam(int eid) {
		EID =eid;
		questionsAttempted = 0;
		correctAnswers=0;
		MarksObtained = 0;
		try {
			initialize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Constructor : "+correctAnswers+" : "+questionsAttempted);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException 
	{
		
		Calendar cal=Calendar.getInstance();
		Date d=cal.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(d));
		
		/*-------------------------------Getting data from database inittally----------------------*/
		String query;
		//getting value of n from  database
		query="SELECT count(qid) FROM examquestiontable WHERE eid="+EID;
		try {
			ResultSet r = Database.execute(query);
			while (r.next()) {
					n = r.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//getting value of duration from  database
				query="SELECT eduration FROM exam WHERE eid="+EID;
				try {
					ResultSet r = Database.execute(query);
					while (r.next()) {
							minutes = r.getString(1);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		//generating estid as Dashboard.sid+EID+sdf.format(d)
		estid = Dashboard.sid+EID+sdf.format(d);
		dateTimeStamp = sdf.format(d);
		/*populate Questions,Option1,Option2,Option3,Option4,correctOptions arrays*/
		QID = new String[n];
		Questions = new String[n];
		Option1 = new String[n];
		Option2 = new String[n];
		Option3 = new String[n];
		Option4 = new String[n];
		correctOptions = new String[n];
		
		query="SELECT qid,qquestion,qop1,qop2,qop3,qop4,qcorrect_answer FROM question WHERE qid in (SELECT qid FROM examquestiontable WHERE eid="+EID+")";
		try {
			ResultSet r = Database.execute(query);
			int index=0; 
			while (r.next()) {
				QID[index] = r.getString(1);
				Questions[index] = r.getString(2);
				Option1[index] = r.getString(3);
				Option2[index] = r.getString(4);
				Option3[index] = r.getString(5);
				Option4[index] = r.getString(6);
				correctOptions[index] = r.getString(7);
				index++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*-----------------------------------------------------------------------------------------*/
		Border border = BorderFactory.createLineBorder(Color.BLACK);  //border to textarea
		
		screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);				
		setBounds(0, 0,screenSize.width,screenSize.height);       
	    getContentPane().setBackground(Color.white);
	
		
		 
		
		//	obj.receivedButtonList(buttons);
		InstructionPanel = new JPanel();
		InstructionPanel.setBackground(new Color(173, 255, 47));
		InstructionPanel.setBounds(0,0, screenSize.width, screenSize.height);
		
		JLabel g=new JLabel();
		g.setFont(new Font("Serif", Font.BOLD, 25));
		g.setText("E"+EID+" General Instructions:");
		g.setBounds(screenSize.width/4,screenSize.height/5-80, screenSize.width/2, 50);
		InstructionPanel.add(g);
	
		JTextArea l1=new JTextArea();
		l1.setBounds(screenSize.width/4,screenSize.height/5, screenSize.width/2, 50);
		l1.setEditable(false);
		l1.setWrapStyleWord(true);  //typing start to nxt line whn reached end of textarea
		l1.setLineWrap(true);			  
		l1.setBorder(BorderFactory.createCompoundBorder(border, 
				             BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	
		      l1.setText("1.Total duration of examination is "+ minutes +" minutes");		      		 
		      InstructionPanel.add(l1);
		      l1.setFont(new Font("Serif", Font.PLAIN, 14));
		      
		      		      JTextArea l2=new JTextArea();
		      		      l2.setBounds(screenSize.width/4,screenSize.height/5+60, screenSize.width/2, 100);
		      		      l2.setEditable(false);
		      		      l2.setWrapStyleWord(true);  //typing start to nxt line whn reached end of textarea
		      		      l2.setLineWrap(true);
		      		      l2.setFont(new Font("Serif", Font.PLAIN, 14));
		      		      l2.setBorder(BorderFactory.createCompoundBorder(border, 
				             BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		      		      
		      
		      l2.setText("2. The clock will be set at the server."
		      		+ " The countdown timer in top right corner of screen will display the time remaining. "

	+"When the timer reaches 0 hours ,the examination will end by itself.You will not be required to "
+"end or submit your examination. However if you want to submit before time you can do by clicking on finish test button given "
+"on bottom left corner. ");
		      InstructionPanel.add(l2);
		      
		      
		      JTextArea l3=new JTextArea();
		      l3.setBounds(screenSize.width/4,screenSize.height/5+170, screenSize.width/2, 150);
		      l3.setEditable(false);
		      l3.setWrapStyleWord(true);  //typing start to nxt line whn reached end of textarea
		      l3.setLineWrap(true);			  
		      l3.setBorder(BorderFactory.createCompoundBorder(border, 
				             BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		      l3.setFont(new Font("Serif", Font.PLAIN, 14));
		      l3.setText("3. The question palette displayed on the left side of screen"+""
		      		+ " will show the status of each question using one of the "
+"following colour scheme:"+"\n"
+"\n"
+"White-You have not answered the question"
+"\n"
+"Green-You have answered the question"
+"\n"
+"Yellow-You have not answered the question but marked it for review");		      		 
		      
		      InstructionPanel.add(l3);
		      
		      getContentPane().add(InstructionPanel);
		      InstructionPanel.setLayout(null);
		      
		            
		            /*Proceed Button*/
		            bProceed = new JButton("Proceed");
		            bProceed.setBounds(screenSize.width/2-80,screenSize.height/2+250, 90, 50);
		            InstructionPanel.add(bProceed);
		            bProceed.addActionListener(this);
		            /*Back button*/
		            bBack = new JButton("Back");
		            bBack.setBounds(screenSize.width/2-80+100,screenSize.height/2+250, 90, 50);
		            InstructionPanel.add(bBack);
		            bBack.addActionListener(this);		    	    
	    
		            CompositePanel = new JPanel();
	    CompositePanel.setLayout(null);
	    CompositePanel.setBounds(0, 0,screenSize.width, screenSize.height);
	    CompositePanel.setVisible(false);

	      
	      getContentPane().add(CompositePanel);
	      
					   
		      
		      QuickQuestionPanel = new JPanel();
		      QuickQuestionPanel.setBounds(0, screenSize.height/4, screenSize.width/4, screenSize.height/4);
		      QuickQuestionPanel.setVisible(true);
		      QuickQuestionPanel.setBackground(Color.ORANGE);
		      QuickQuestionPanel.setLayout(new GridLayout(5, 3,1,1));
		      
		      		      		      		      		      		      		        		      
		
		          		 CompositePanel.add(QuickQuestionPanel);
		          		 
		          		  obj=new ExamRightPanel();
		          		  obj.setBackground(new Color(250, 250, 210));
		          		  CompositePanel.add(obj);
		
		          		examGradePanel=new ExamGradePanel();
		        		getContentPane().add(examGradePanel);		
		        		examGradePanel.setVisible(false);	
		        		examGradePanel.setLayout(null);
		        		
		          		  obj2 =new ExamLeftPanel(obj,CompositePanel,examGradePanel);       		
			       	      obj2.setBackground(new Color(240, 230, 140));  
		          		  CompositePanel.add(obj2);
	    
		          	
 		          	 
		        		numberOfButtons=n;  
		      buttons = new ArrayList<JButton>( numberOfButtons );
//-----------------------------------------------------------------------------------------numberOfButtons
			/*	try {
				   	con=(Connection) SqlConn.getcCon();
					String sql="SELECT COUNT(*) FROM   questions";
					PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
					
					ResultSet rs = st.executeQuery();
				   	
				      rs.next();
				//      numberOfButtons = rs.getInt(1);
					
					System.out.println("numberofbuttons"+rs.getInt(1));
					
				} catch (Exception e) {
						
					
					
					
				}finally{
							try {
								SqlConn.endCon(con);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

		      
	*/	      
//-------------------------------------------------------------------------------------------------------		      
		   
 
				
				
		      
			for ( int i = 1; i <= numberOfButtons; i++ )
		        {
				
				int s;
				s=i+1;
		            JButton button = new JButton( new SomeAction( " " + i ,i)  );
				//button.setPreferredSize(new Dimension(30,30));
				button.setSize(new Dimension(30,30));
				
				button.setBackground(Color.WHITE);
				QuickQuestionPanel.add( button );
		            // Remember buttons in collection, we might need to access them sometime
		            buttons.add( button );
		        }
			
		  /*try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);*/
		obj.receivedButtonList(buttons);
		setUndecorated(true);
		setVisible(true);
	
	//obj2.setTimer();
	}
	
	class SomeAction extends AbstractAction 
	{
		int i;
	    public SomeAction( String text,int i )
	    {	    	
	        super( text );
	        this.i=i;
	    }
	    
	    
	    public void actionPerformed( ActionEvent e )
	    {	        	   	        
	       
	        obj.actionPerformedFromLeftPanel(i);
	                
	    }

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
       Object source = e.getSource();
		
		if(source==bProceed){
			InstructionPanel.setVisible(false);	
			
			CompositePanel.setVisible(true);
			
			obj2.setTimer(examGradePanel,CompositePanel);	
			System.out.println("proceed clicked");
			
			
		}else if(source==bBack){
			//Dashboard.frame.setVisible(true);
			Exam.this.setVisible(false);
			Dashboard.frame.setVisible(true);
			//CompositePanel.setVisible(true);
			
			//obj2.setTimer(examGradePanel,CompositePanel);	
			//System.out.println("proceed clicked");
			
			
		}
		
	}
}
