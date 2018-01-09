package Instructor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;

import Login.dbConnector;

import java.sql.*;
import java.util.ArrayList;

public class newExam extends JPanel{
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	
	int panelwidth = ((int)width )*3/4;
	int panelheight = (int)height ;
	
	JButton addbtn,editbtn,deletebtn,save,viewbtn,back,saveexam,backedit;
	JLabel lduration;
	static dbConnector dbc = new dbConnector();
	boolean backandsave,edit;
	List listOfQuestions;
	
	JTextArea question,questionMCQ;
	JTextField choice1,choice2,choice3,choice4;
	JLabel lchoice1,lchoice2,lchoice3,lchoice4,lcorrectchoice,lquestion,llistofquestions;
	JLabel viewquestion,viewchoice1,viewchoice2,viewchoice3,viewchoice4,viewcorrectchoice;
	JComboBox correctchoice;
//  JSpinner duration; 
	
	JButton otherdetails;
	
	Question q ;
	static Exam exam;
	
	Instructor instructor;
	int qid=0;
	newExam(Instructor a)
	{
		
		instructor=a;
		
		try
		{
			BufferedImage bi = ImageIO.read(new File("next.jpg"));		
		}catch(Exception e){
			//do something here
		}
		
		setLayout(null);
		setBounds((int)(width/4),0,(int)((width*3)/4),(int)height);
		
		Color c = new Color(205,205,245);
		
		setBackground(c);
		
		exam = new Exam();
		
		//exam.setNumberOfQuestions(100); //----------------------------------------------------------------------Check this part
		
		listOfQuestions = new List();
		
		JScrollPane p = new JScrollPane(listOfQuestions);
		p.setBounds(60, 40, panelwidth-100, 2*panelheight/3);
		p.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(p);
		
		///duration = new JSpinner();
		//duration.setModel(new SpinnerNumberModel(15, 15, 180, 15));
		
		
		addbtn = new JButton("Add Question");
		editbtn = new JButton("Edit Question");
		deletebtn = new JButton("Delete Question");
		save = new JButton("Save"); 
		viewbtn = new JButton("View Question");
		back = new JButton(" < Back ");
		saveexam = new JButton("Save Exam");
		otherdetails= new JButton("Other Details");
		
		choice1 = new JTextField("");
		choice2 = new JTextField("");
		choice3 = new JTextField("");
		choice4 = new JTextField("");
		lquestion = new JLabel("Question : ");
		lchoice1 = new JLabel("Choice 1 : ");
		lchoice2 = new JLabel("Choice 2 : ");
		lchoice3 = new JLabel("Choice 3 : ");
		lchoice4 = new JLabel("Choice 4 :");
		lcorrectchoice =  new JLabel("Correct Choice : ");
		llistofquestions =  new JLabel("List Of Questions  : ");
		lduration =  new JLabel("Duration  : ");
		
		correctchoice = new JComboBox();
		correctchoice.setModel(new DefaultComboBoxModel(new String[] {"1", "2","3","4"}));
		
		
		question = new JTextArea();
		
		questionMCQ = new JTextArea();
		
		Font f=new Font("Century Gothic",Font.BOLD,20);
		
		llistofquestions.setBounds(60, 10, panelwidth-100, 30);
		llistofquestions.setFont(f);
		
		
		listOfQuestions.setBounds(60, 40, panelwidth-100, 2*panelheight/3);
		JScrollPane pane = new JScrollPane();
		this.add(pane);
		
		f=new Font("Century Gothic",Font.HANGING_BASELINE,15);
		
		addbtn.setFont(f);
		editbtn.setFont(f);
		viewbtn.setFont(f);
		deletebtn.setFont(f);
		saveexam.setFont(f);
		otherdetails.setFont(f);
		
		addbtn.setBounds(150-20, 2*panelheight/3+60, 130+30, 30);
		editbtn.setBounds(150+200-20, 2*panelheight/3+60, 130+30, 30);
		viewbtn.setBounds(150+2*200-20, 2*panelheight/3+60, 130+30, 30);
		deletebtn.setBounds(150+3*200-20,2*panelheight/3+60, 130+30, 30);
		saveexam.setBounds(150+2*150-20, 2*panelheight/3+130, 130+30, 30);
//		duration.setBounds(150+2*150-20+200+100, 2*panelheight/3+130, 80, 30);
//		lduration.setBounds(150+2*150-20+200, 2*panelheight/3+130, 80, 30);
		otherdetails.setBounds(150-20, 2*panelheight/3+130, 130+30, 30);
		
		
		question.setBounds(50, 30, (int)(width*3/4)-100, 500);
		questionMCQ.setBounds(150, 30, panelwidth-200, panelheight/3-30);
		
		choice1.setBounds(150,panelheight/2-100, panelwidth-200, 40);
		choice2.setBounds(150,panelheight/2-100+50,panelwidth-200,40);
		choice3.setBounds(150,panelheight/2-100+2*50,panelwidth-200,40);
		choice4.setBounds(150,panelheight/2-100+3*50,panelwidth-200,40);
		correctchoice.setBounds(200,panelheight/2-100+4*50,50,30);
		
		lquestion.setBounds(50, 10, panelwidth-100, 250);
		lchoice1.setBounds(50,panelheight/2-100, 100, 40);
		lchoice2.setBounds(50,panelheight/2-100+50,100, 40);
		lchoice3.setBounds(50,panelheight/2-100+2*50,100, 40);
		lchoice4.setBounds(50, panelheight/2-100+3*50,100, 40);
		lcorrectchoice.setBounds(50, panelheight/2-100+4*50, 150, 40);
		
		
		
		save.setBounds(panelwidth/2-100, panelheight/2-100+5*50, 130, 30);
		back.setBounds(panelwidth/2-100, panelheight/2-100+6*50, 130, 30);
		
		
	
		addbtn.addActionListener(new eventAction());
		save.addActionListener(new eventAction());
		deletebtn.addActionListener(new eventAction());
		editbtn.addActionListener(new eventAction());
		viewbtn.addActionListener(new eventAction());
		back.addActionListener(new eventAction());
		saveexam.addActionListener(new eventAction());
		otherdetails.addActionListener(new eventAction());
		listOfQuestions.addItemListener(new eventItem());
		addButtons();
		this.repaint(); // : replaced  : replaced  : replaced();
		
		instructor.setVisible(false);
		instructor.setVisible(true);
		backandsave = false;
		
		assignId();
		getMinQuestionId();
		
		//fetchGroups();
	//  ask date and time
		
	}
	public void askExamName()
	{
		JOptionPane ob = new JOptionPane();
		
		String ename = JOptionPane.showInputDialog(this,"Enter Name Of Examination");
		exam.setName(ename);
		llistofquestions.setText(ename);
		
	}
	
	void assignId() 
	{
		String qry;
		int id = 0;
		
		qry = "SELECT max(eid) from exam";
	
		ResultSet rs;
		try {
			Statement stmt =dbc.conn.createStatement();
			rs = stmt.executeQuery(qry);

			
			if(rs.next())
			{
				id = rs.getInt(1);
			}
			System.out.println("MAX ID "+id);
			exam.setExamId(id+1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void getMinQuestionId() 
	{
		String qry;
		int id = 0;
		qry = "SELECT max(qid) from question";
		ResultSet rs;
		try {
			Statement stmt =dbc.conn.createStatement();
			rs = stmt.executeQuery(qry);
			if(rs.next())
			{
				id = rs.getInt(1);
				qid = id;
			}
			System.out.println("Minimum Question ID "+id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qid++;
	}
	void addButtons()
	{
	//	add(duration);
		add(llistofquestions);
		listOfQuestions.repaint();
		add(listOfQuestions);
		add(addbtn);
		add(editbtn);
		add(viewbtn);
		add(deletebtn);
		add(saveexam);
//		add(lduration);
		
		add(otherdetails);
		
		//updateGUI();
	//	spinner.setEnabled(true);
	//	spinner.setVisible(true);
		
		viewbtn.setEnabled(false);
		editbtn.setEnabled(false);
		deletebtn.setEnabled(false);
		
		this.repaint();//  : replaced  : replaced  : replaced();
		clearText();
		
		
	}
	void clearText()
	{
		questionMCQ.setText("");
		choice1.setText("");
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
		correctchoice.setSelectedIndex(0);
		
		
	}
	class eventItem implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			
			if(listOfQuestions.getSelectedIndex()==-1)
			{
				editbtn.setEnabled(false);
				deletebtn.setEnabled(false);
				viewbtn.setEnabled(false);
			}
			else
			{
				editbtn.setEnabled(true);
				deletebtn.setEnabled(true);
				viewbtn.setEnabled(true);
			}
			
		}
		
	}
	newExam(Instructor a,Exam e)
	{

		//System.out.println("here");
		otherdetails= new JButton("Other Details");
		instructor=a;
		
		setLayout(null);
		setBounds((int)(width/4),0,(int)((width*3)/4),(int)height);
		setBackground(Color.YELLOW);
		
		exam = e;
		
		//exam.setNumberOfQuestions(100);
		
		listOfQuestions = new List();
		JScrollPane p = new JScrollPane(listOfQuestions);
		p.setBounds(60, 40, panelwidth-100, 2*panelheight/3);
		p.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(p);
        otherdetails.setBounds(150-20, 2*panelheight/3+130, 130+30, 30);
        otherdetails.addActionListener(new eventAction());
        add(otherdetails);
        
		addbtn = new JButton("Add Question");
		editbtn = new JButton("Edit Question");
		deletebtn = new JButton("Delete Question");
		save = new JButton("Save"); 
		viewbtn = new JButton("View Question");
		back = new JButton(" < Back ");
		saveexam = new JButton("Save Exam");
		//duration = new JSpinner();
	//	duration.setModel(new SpinnerNumberModel(15, 15, 180, 15));
	//	duration.setBounds(150+2*150-20+200+100, 2*panelheight/3+130, 80, 30);
		
		choice1 = new JTextField("");
		choice2 = new JTextField("");
		choice3 = new JTextField("");
		choice4 = new JTextField("");
		lquestion = new JLabel("Question : ");
		lchoice1 = new JLabel("Choice 1 : ");
		lchoice2 = new JLabel("Choice 2 : ");
		lchoice3 = new JLabel("Choice 3 : ");
		lchoice4 = new JLabel("Choice 4 :");
		lcorrectchoice =  new JLabel("Correct Choice : ");
		llistofquestions =  new JLabel("List Of Questions  : ");
		lduration = new JLabel("Duration : ");
		
		correctchoice = new JComboBox();
		correctchoice.setModel(new DefaultComboBoxModel(new String[] {"1", "2","3","4"}));
		
		question = new JTextArea();
		questionMCQ = new JTextArea();
		
		llistofquestions.setBounds(60, 10, panelwidth-100, 30);
		listOfQuestions.setBounds(60, 40, panelwidth-100, 2*panelheight/3);
		
		addbtn.setBounds(150, 2*panelheight/3+60, 130, 30);
		editbtn.setBounds(150+200, 2*panelheight/3+60, 130, 30);
		viewbtn.setBounds(150+2*200, 2*panelheight/3+60, 130, 30);
		deletebtn.setBounds(150+3*200,2*panelheight/3+60, 130, 30);
		saveexam.setBounds(150+2*150, 2*panelheight/3+130, 130, 30);
		lduration.setBounds(150+2*150-20+200, 2*panelheight/3+130, 80, 30);
		
		
		question.setBounds(50, 30, (int)(width*3/4)-100, 500);
		questionMCQ.setBounds(150, 30, panelwidth-200, panelheight/3-30);
		
		choice1.setBounds(150,panelheight/2-100, panelwidth-200, 40);
		choice2.setBounds(150,panelheight/2-100+50,panelwidth-200, 40);
		choice3.setBounds(150,panelheight/2-100+2*50,panelwidth-200, 40);
		choice4.setBounds(150, panelheight/2-100+3*50,panelwidth-200, 40);
		correctchoice.setBounds(200, panelheight/2-100+4*50, 50, 30);
		
		lquestion.setBounds(50, 10, panelwidth-100, 250);
		lchoice1.setBounds(50,panelheight/2-100, 100, 40);
		lchoice2.setBounds(50,panelheight/2-100+50,100, 40);
		lchoice3.setBounds(50,panelheight/2-100+2*50,100, 40);
		lchoice4.setBounds(50, panelheight/2-100+3*50,100, 40);
		lcorrectchoice.setBounds(50, panelheight/2-100+4*50, 150, 40);
		
		
		
		save.setBounds(panelwidth/2-100, panelheight/2-100+5*50, 130, 30);
		back.setBounds(panelwidth/2-100, panelheight/2-100+6*50, 130, 30);
		
		addButtons();
		
	
		addbtn.addActionListener(new eventAction());
		save.addActionListener(new eventAction());
		deletebtn.addActionListener(new eventAction());
		editbtn.addActionListener(new eventAction());
		viewbtn.addActionListener(new eventAction());
		back.addActionListener(new eventAction());
		saveexam.addActionListener(new eventAction());
		

		editbtn.setEnabled(false);
		deletebtn.setEnabled(false);
		viewbtn.setEnabled(false);
		
		listOfQuestions.addItemListener(new eventItem());
		backandsave = false;
		llistofquestions.setText(exam.getName());
		displayList();
		repaint();
		
		
	}
	
	void displayQuestion()
	{
	  //viewQuestion();
		//JOptionPane.showInputDialog(this);
		int type = askType();
		
		q.setType(type); // --------------------------------------------------   Set Type Of Question
		
		if(type == 0)
		{
			newMCQ();
		}
		
	}
	
	void newMCQ()
	{
		removeAll();
		/*remove(listOfQuestions);
		remove(editbtn);
		remove(deletebtn);
		remove(addbtn);
		remove(viewbtn);
		remove(saveexam);
		*/
		add(questionMCQ);
		add(choice1);
		add(choice2);
		add(choice3);
		add(choice4);
		add(correctchoice);
		
		add(lquestion);
		add(lchoice1);
		add(lchoice2);
		add(lchoice3);
		add(lchoice4);
		add(lcorrectchoice);
		
		add(save);
		add(back);
		this.repaint();//  : replaced  : replaced  : replaced();
		
		this.setVisible(false);
		this.setVisible(true);
		
	}
//	void newTheory()
//	{
//		remove(listOfQuestions);
//		remove(editbtn);
//		remove(deletebtn);
//		remove(addbtn);
//		remove(viewbtn);
//		remove(saveexam);
//		
//		add(question);
//		add(save);
//		add(back);
//		
//		this.repaint();//  : replaced  : replaced  : replaced();
//	}
	int askType()
	{
		String type[] ={"Multiple Choice Question","Theory Question"};
		
		//int choice = JOptionPane.showOptionDialog(this, "Select Type Of Question ", "", 1, 1, null,type, "");
		int choice = 0;
		//System.out.println("option    "+choice);
		return(choice);
		
	}
	void editQuestion()
	{
		
		int item = listOfQuestions.getSelectedIndex();
		
		System.out.println(" item    :  "+item);
		
		//Some logic to determine if question is mcq or theory
		Question ques[] = exam.getQuestions();
		
		if(ques[item].getType()==0)
		{
			newMCQ();
		}
//		else
//		{
	//		newTheory();
		
	//	}
		int id = ques[item].getQuestionId();
		
		//exam.deleteQuestion(id);
		
		
		q=new Question();
		q.setQuestionId(id);
		
	//	exam.deleteQuestion(item);
		
		listOfQuestions.remove(item);
		
		question.setText(ques[item].getQuestion());
		questionMCQ.setText(ques[item].getQuestion());
		
//		q.setAnswer1(answer1);
		choice1.setText(ques[item].getAnswer1());
		choice2.setText(ques[item].getAnswer2());
		choice3.setText(ques[item].getAnswer3());
		choice4.setText(ques[item].getAnswer4());
		correctchoice.setSelectedIndex(ques[item].getCorrectAns()-1);
		
		exam.deleteQuestion(id);
		
		backandsave  = true; // This will save the data as well
	    //displayList();
		/*
	    Question ques= new Question();
		
		numberOfQuestions = exam.getNumberOfQuestions();
		
		ques.setQuestion(question.getText());
		ques.setQuestionId(numberOfQuestions+1);
		
		listOfQuestions.add(""+ques.getQuestionId()+ "  "+ques.getQuestion());
		
		exam.addQuestion(ques);
		*/	
		
	}
	void viewQuestion()
	{
	
		int item = listOfQuestions.getSelectedIndex();
		
		Question q[] =exam.getQuestions();
      
	
		
		//p.showMessageDialog(this,q[item].getQuestion(),"Question "+q[item].getQuestionId(),1);  //set icon for this
	  
		
		removeAll();
		
		
		
		
		
//		viewquestion = new JLabel(q[item].getQuestion());
//		viewchoice1 = new JLabel(q[item].getAnswer1());
//		viewchoice2 = new JLabel(q[item].getAnswer2());
//		viewchoice3 = new JLabel(q[item].getAnswer3());
//		viewchoice4 = new JLabel(q[item].getAnswer4());

		viewcorrectchoice = new JLabel(""+q[item].getCorrectAns());
		questionMCQ.setText(q[item].getQuestion());
		choice1.setText(q[item].getAnswer1());
		choice2.setText(q[item].getAnswer2());
		choice3.setText(q[item].getAnswer3());
		choice4.setText(q[item].getAnswer4());
		
		questionMCQ.setEditable(false);
		choice1.setEditable(false);
		choice2.setEditable(false);
		choice3.setEditable(false);
		choice4.setEditable(false);
		
		
//		viewcorrectchoice = new JLabel(""+q[item].getCorrectAns());
//		viewquestion.setBounds(150, 30, panelwidth-200, panelheight/3-30);
//		viewchoice1.setBounds(150,panelheight/2-100, panelwidth-200, 40);
//		viewchoice2.setBounds(150,panelheight/2-100+50,panelwidth-200, 40);
//		viewchoice3.setBounds(150,panelheight/2-100+2*50,panelwidth-200, 40);
//		viewchoice4.setBounds(150, panelheight/2-100+3*50,panelwidth-200, 40);
//		viewcorrectchoice.setBounds(200, panelheight/2-100+4*50, 50, 30);
//		add(viewquestion);		
//		add(viewchoice1);
//		add(viewchoice2);
//		add(viewchoice3);
//		add(viewchoice4);
//		add(viewcorrectchoice);
//		
		viewcorrectchoice.setBounds(200, panelheight/2-100+4*50, 50, 30);
		add(questionMCQ);
		add(choice1);
		add(choice2);
		add(choice3);
		add(choice4);
		add(viewcorrectchoice);
		add(lquestion);
		add(lchoice1);
		add(lchoice2);
		add(lchoice3);
		add(lchoice4);
		add(lcorrectchoice);
		
		//add(save);
		add(back);
		backandsave = false;
		this.repaint();//  : replaced  : replaced  : replaced();
		this.setVisible(false);
		this.setVisible(true);
		
	}
	void deleteQuestion()
	{
		//Display Dialog Box for asking are you sure you want to delete.
		int item = listOfQuestions.getSelectedIndex();
    	//System.out.println(" selected "+item);
		
		listOfQuestions.remove(item);
		Question q[] = exam.getQuestions();
		
		exam.deleteQuestion(item);
		
		displayList();
		
		repaint();
		//this.repaint();//  : replaced  : replaced();
		
	}
	void displayList(){
		listOfQuestions.removeAll();
		int numberOfQuestions = exam.getNumberOfQuestions();
		System.out.println("Njumber of Questionds : "+numberOfQuestions);
		
		Question ques[] = exam.getQuestions();
		
		exam.displayQues();
		System.out.println("Exit Display List");
		
		for(int i=0;i<numberOfQuestions;i++)
		{
			listOfQuestions.add(ques[i].getQuestionId()+"   "+ques[i].getQuestion());
		}
		this.repaint();
		//listOfQuestions.
		instructor.setVisible(false);
		instructor.setVisible(true);
	}
	void viewWarning()
	{
		JOptionPane p = new JOptionPane();
		if(p.showConfirmDialog(this, "Are You Sure ?","Delete Question",1)==0)
		{
			deleteQuestion();
		}
		else
		{
			
		}		
	}
	void saveQuestion()
	{
	//	System.out.println("saving ");
		
	//	exam.displayQues();
		
		//int numberOfQuestions = exam.getNumberOfQuestions();
		
		if(q.getType()==0)
			q.setQuestion(questionMCQ.getText());
		else 
			q.setQuestion(question.getText());
		
		q.setAnswer1(choice1.getText());
		q.setAnswer2(choice2.getText());
		q.setAnswer3(choice3.getText());
		q.setAnswer4(choice4.getText());
		q.setCorrectAns(correctchoice.getSelectedIndex()+1);
	//  q.setQuestionId(numberOfQuestions+1);
		
		//if(backandsave==false)
		if(edit!=true)
		{
			q.setQuestionId(qid);
			qid++;
			
		}
		listOfQuestions.add("Q"+q.getQuestionId()+ "  "+q.getQuestion());
		exam.addQuestion(q);
	//	
	//	exam.displayQues();
	}
	
	class eventAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == (Object)addbtn)
			{
				
				q= new Question();
				displayQuestion();
				
			}
			else if(e.getSource() == (Object)save)
			{
				//Transfer Question to database
				
//				System.out.println("Text "+ question.getText());
				
				
		//-------------------------------------------------------------------------			
				
				saveQuestion();
				
		//-------------------------------------------------------------------------
				removeAll();
				
				addButtons();
				displayList();
				question.setText("");
				//this.repaint();//  : replaced  : replaced();
			}
			else  if(e.getSource()==(Object)deletebtn)
			{				
				viewWarning();
			}
			else  if(e.getSource()==(Object)editbtn)
			{
				edit=true;
				editQuestion();
			}
			else  if(e.getSource()==(Object)viewbtn)
			{
				viewQuestion();
			}
			else  if(e.getSource()==(Object)back)
			{
				if(backandsave == true)
					saveQuestion();
				removeAll();
				addButtons();
				displayList();
				//question.setText("");
				questionMCQ.setEditable(true);
				choice1.setEditable(true);
				choice2.setEditable(true);
				choice3.setEditable(true);
				choice4.setEditable(true);
				//this.repaint();//  : replaced  : replaced();
			}
			else if(e.getSource()==(Object)saveexam)
			{
				String qry;
				
				//exam.setDuration(Integer.parseInt(duration.getValue().toString()));
				
				qry = "INSERT INTO EXAM VALUES(?,?,?,?,?,?)"; // Insert Group as well
				try {
					PreparedStatement pst = dbc.conn.prepareStatement(qry);
					//pst.setInt(1,Integer.parseInt(Instructor.instructorid)); //iid
				 System.out.println("Exam id 0 : "+ exam.getExamId());
					pst.setInt(1, Instructor.instructorid);
					pst.setInt(2,exam.getExamId()); //eid
					pst.setInt(6,exam.getGroup());// Gid exam.getGroupId
					
			 //    pst.setString(3,exam.getDateTime()); //datetime
					
					pst.setString(3, exam.getName());
					pst.setString(4,exam.getDateTime());
					pst.setInt(5,exam.getDuration()); //duration
					//exam.setDateTime(3,
					pst.executeUpdate();
					
					System.out.println("Exam id 1 : "+ exam.getExamId());
					Question q[]=exam.getQuestions();
					
					exam.displayQues();
					System.out.println("Exam id 2 : "+ exam.getExamId());
					int n =q.length;
					qry = "INSERT INTO Question VALUES(?,?,?,?,?,?,?)"; //Change Question
					String qry2 = "INSERT INTO ExamQuestionTable VALUES(?,?)";
					 
					for(int i=0;i<n;i++)
					{
						PreparedStatement pst1 = dbc.conn.prepareStatement(qry);
						PreparedStatement pst2= dbc.conn.prepareStatement(qry2);
						pst1.setInt(1, q[i].getQuestionId());
						pst1.setString(2, q[i].getQuestion());
						pst1.setString(3,q[i].getAnswer1());
						pst1.setString(4,q[i].getAnswer2());
						pst1.setString(5,q[i].getAnswer3());
						pst1.setString(6,q[i].getAnswer4());
						pst1.setInt(7,q[i].getCorrectAns());
						System.out.println("Exam id 3: "+ exam.getExamId());
						
						pst2.setInt(1, exam.getExamId());
						pst2.setInt(2, q[i].getQuestionId());
						
						pst1.executeUpdate();
						pst2.executeUpdate();
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
				// Add Exam to Database
				
				ExamShowPanel esp = new ExamShowPanel(instructor);
				instructor.addRightPanel(esp);
			}
			else if(e.getSource()==(Object)otherdetails)
			{
				ExamDetails ob = new ExamDetails();
				instructor.setEnabled(false);
				ob.setVisible(true);
			}
		}
	}
	class eventMouse implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			
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
	
//	 @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         File file = new File("background.png");
//         try 
// 		{
// 			BufferedImage img = ImageIO.read(file);
// 			g.drawImage(img, 0, 0, null);
// 		}
// 		catch (IOException e) 
// 		{
// 			e.printStackTrace();
// 		}
//         
//     }
	
	
}