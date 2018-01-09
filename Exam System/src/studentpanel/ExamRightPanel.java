package studentpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

//import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
//import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.border.Border;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;



public class ExamRightPanel extends JPanel implements ActionListener{

	ButtonGroup group;
	List<JButton> listOfButtons;
	JButton previous,next,viewLater,deselect;
	Dimension screenSize;
	JPanel pan;
	int cnt=0;
	JLabel Qno;
	JTextArea Q;
	JRadioButton Opt1,Opt2,Opt3,Opt4;
	int count=0;
	int pcount=-1;
	int cflag=0;
	
 //static int n=3; //=Exam.n  i.e. size of array minus 1( max index)
/*String []Questions=new String[]{"1","2","3","4"};
String [] Option1=new String[]{"op11","op12","op13","op14"};
String [] Option2=new String[]{"op21","op22","op23","op24"};
String [] Option3=new String[]{"op31","op32","op33","op34"};
String [] Option4=new String[]{"op41","op42","op43","op44"};
static String [] correctOptions=new String[]{"1","1","1","1"};*/
static String[] optionChossen;
//static int questionsAttempted=0;
//static int correctAnswers=0;
//static int totalMarks=0;
	
	
//-----------------------------------------------------------------------------------------------------------------
public static void calculate()
{
	Exam.totalMarks=Exam.n*4;
	/*System.out.println("Options Choosen array : ");
	for(String s : optionChossen){
		System.out.println("------"+s);
	}*/
		for(int i=0;i<=Exam.n;i++){
		if(optionChossen[i]!=null){
			Exam.questionsAttempted++;
			System.out.println("optionChoosen["+i+"] = "+optionChossen[i]+"correctOptions["+i+"]"+Exam.correctOptions[i]);
			if(optionChossen[i].equals(Exam.correctOptions[i])){
				Exam.correctAnswers++;
				Exam.MarksObtained+=4;
			}
		}else{
			optionChossen[i]="0";
		}
	}
	//GradePanel.m=totalMarks;
	//GradePanel.c=correctAnswers;
	//GradePanel.q=questionsAttempted;
	ExamGradePanel.data(Exam.MarksObtained+"", Exam.correctAnswers+"", Exam.questionsAttempted+"");
	//ArrayList<GradeDetail> list=new ArrayList<GradeDetail>();
	//list.add(new GradeDetail(questionsAttempted,correctAnswers,totalMarks));
	//System.out.println("qAttempted:"+questionsAttempted+" "+"correct="+correctAnswers+" totalMarks: "+totalMarks);
 // return list;
}
//----------------------------------------------------------------------------------------------------------------
ExamRightPanel()
	{
     	optionChossen=new String[Exam.n+1];
		screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		
		
		addContentToPanel();
		next.addActionListener(this);
		previous.addActionListener(this);
		viewLater.addActionListener(this);
		
		Opt1.addActionListener(this);
		Opt2.addActionListener(this);
		Opt3.addActionListener(this);
		Opt4.addActionListener(this);
		deselect.addActionListener(this);
			

       

	} 

public void actionPerformed(ActionEvent e) {
		

		Object source = e.getSource();

if(source==next)
{
		
			if(count<Exam.n){
				
									   	
		pcount=count;
		count++;
		cflag=count;
			Q.setText(Exam.Questions[count]);
			Opt1.setText(Exam.Option1[count]);
			Opt2.setText(Exam.Option2[count]);
			Opt3.setText(Exam.Option3[count]);
			Opt4.setText(Exam.Option4[count]);			
			Qno.setText("Q."+(count+1));
			
						
			
			System.out.println("clicked next"+count);	
			group.clearSelection();
			}
			if(count>=Exam.n-1){
				next.setVisible(false);
			}
			if(pcount>=0) previous.setVisible(true);
			if(optionChossen[count]!=null){
				if(optionChossen[count].equals("1")) Opt1.setSelected(true);
				if(optionChossen[count].equals("2")) Opt2.setSelected(true);
				if(optionChossen[count].equals("3")) Opt3.setSelected(true);
				if(optionChossen[count].equals("4")) Opt4.setSelected(true);
			}
//-------------------------------------------------------------------------------------------------------------			

		}
	else if(source==previous){
			
		if(pcount>=0){
			
			System.out.println("clicked prev"+pcount);
			cflag=pcount;
			Q.setText(Exam.Questions[pcount]);
			Opt1.setText(Exam.Option1[pcount]);
			Opt2.setText(Exam.Option2[pcount]);
			Opt3.setText(Exam.Option3[pcount]);
			Opt4.setText(Exam.Option4[pcount]);			
			Qno.setText("Q."+(pcount+1));
			
			if(optionChossen[pcount]!=null){
				System.out.println(optionChossen[pcount].equals("2"));
				if(optionChossen[pcount].equals("1")) Opt1.setSelected(true);
				if(optionChossen[pcount].equals("2")) Opt2.setSelected(true);
				if(optionChossen[pcount].equals("3")) Opt3.setSelected(true);
				if(optionChossen[pcount].equals("4")) Opt4.setSelected(true);
			}
			
		count=pcount;
			pcount--;
	        															
	
			//group.clearSelection();
		}
		if(count<Exam.n) next.setVisible(true);
		if(pcount<0)previous.setVisible(false);
		
//-----------------------------------------------------------------------------------------------------------			
	}
	else if(source == viewLater)
	{
		    
	   optionChossen[cflag]=null;
		System.out.println("clicked view later");
		group.clearSelection();
		listOfButtons.get(cflag).setBackground(Color.YELLOW);

	}
	else if(source==Opt1)
	{
			
			if(Opt1.isSelected()==true){
				System.out.println("cflag:"+cflag);
				listOfButtons.get(cflag).setBackground(Color.GREEN);
				optionChossen[cflag]="1";
				
			}
			else if(Opt1.isSelected()==false){
				
			}
	}
	else if(source==Opt2){
			
			if(Opt2.isSelected()==true){
				optionChossen[cflag]="2";
				listOfButtons.get(cflag).setBackground(Color.GREEN);
			}
			else if(Opt2.isSelected()==false){
				
			}
		}
	else if(source==Opt3){
		
		if(Opt3.isSelected()==true){
			optionChossen[cflag]="3";
			listOfButtons.get(cflag).setBackground(Color.GREEN);
		}
		else if(Opt3.isSelected()==false){
			
		}
	}
	else if(source==Opt4){
		
		if(Opt4.isSelected()==true){
			optionChossen[cflag]="4";
			listOfButtons.get(cflag).setBackground(Color.GREEN);
		}
		else if(Opt4.isSelected()==false){
			
		}
	}
	else if(source==deselect){
		optionChossen[cflag]=null;
		group.clearSelection();
		listOfButtons.get(cflag).setBackground(Color.WHITE);
	}
	System.out.println("optionChoosen : ["+cflag+"] = "+optionChossen[cflag]);

	
	}
	
	
private void addContentToPanel()
{
		  
			 
			previous=new JButton();
			next=new JButton();
			viewLater=new JButton();
			deselect=new JButton();
			
			
			Qno=new JLabel();
			Q=new JTextArea();
			Opt1=new JRadioButton();
			Opt2=new JRadioButton();
			Opt3=new JRadioButton();
			Opt4=new JRadioButton();
			
			
			
			Q.setFont(new Font("Serif", Font.BOLD, 14));
			
			
			 group = new ButtonGroup();
			 group.add(Opt1);
			 group.add(Opt2);
			 group.add(Opt3);
			 group.add(Opt4);
			
			 
			 
			 Q.setWrapStyleWord(true); 
			 Q.setLineWrap(true); 
			 Q.setEditable(false);

			 previous.setText("Previous");
			next.setText("Next");
			viewLater.setText("View Later");
			Q.setText(Exam.Questions[0]);
			Qno.setText("Q.1");
			Opt1.setText(Exam.Option1[0]);
			Opt2.setText(Exam.Option2[0]);
			Opt3.setText(Exam.Option3[0]);
			Opt4.setText(Exam.Option4[0]);					
			deselect.setText("Deselect");
		
			
			
			previous.setBounds(screenSize.width/70+40,screenSize.height/2+250, 100,42);     //need to be crrct
			next.setBounds(screenSize.width/2+150, screenSize.height/2+250, 102, 42);
			viewLater.setBounds(screenSize.width/3, screenSize.height/2+250, 100, 41);
			
			Qno.setBounds(screenSize.width/70+5, screenSize.height/4-169, 50,50);
			Q.setBounds(screenSize.width/70+40, screenSize.height/4-150, 877,150);
			
			Opt1.setBounds(screenSize.width/70+70, screenSize.height/4, 300,50);
			Opt2.setBounds(screenSize.width/70+70, screenSize.height/4+50, 300,50);
			Opt3.setBounds(screenSize.width/70+70, screenSize.height/4+100, 300,50);
			Opt4.setBounds(screenSize.width/70+70, screenSize.height/4+150, 300,50);
			deselect.setBounds(screenSize.width/70+70, screenSize.height/4+200, 100,41);

                     previous.setVisible(false);
						deselect.setVisible(true);
						Opt1.setVisible(true);
						Opt2.setVisible(true);
						Opt3.setVisible(true);
						Opt4.setVisible(true);								
					
			setBounds(screenSize.width/4,0,screenSize.width*3/4, screenSize.height);
			   setLayout(null);
			   add(previous);
				add(next);
				add(viewLater);
				add(Qno);
				add(Q);
				add(Opt1);
				add(Opt2);
				add(Opt3);
				add(Opt4);								
				add(deselect);				
				
		}
		

public void actionPerformedFromLeftPanel(int i)
{
					group.clearSelection();
					//System.out.println("button licked"+i);
					count=i-1;
					pcount=i-2;
                    cflag=i-1;
					
					
					Q.setText(Exam.Questions[i-1]);
					Opt1.setText(Exam.Option1[i-1]);
					Opt2.setText(Exam.Option2[i-1]);
					Opt3.setText(Exam.Option3[i-1]);
					Opt4.setText(Exam.Option4[i-1]);			
					Qno.setText("Q."+(i));
	if(count<Exam.n) next.setVisible(true);
	if(count>=Exam.n-1) next.setVisible(false);
	if(pcount>=0) previous.setVisible(true);
	if(pcount<0) previous.setVisible(false);
	
	if(optionChossen[i-1]!=null){
		if(optionChossen[i-1].equals("1")) Opt1.setSelected(true);
		if(optionChossen[i-1].equals("2")) Opt2.setSelected(true);
		if(optionChossen[i-1].equals("3")) Opt3.setSelected(true);
		if(optionChossen[i-1].equals("4")) Opt4.setSelected(true);
	}
		}
		
		
		
public  void receivedButtonList( List<JButton> buttons)
		{
			listOfButtons=buttons;
		}

		
}

