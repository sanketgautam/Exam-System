package studentpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;

public class ExamGradePanel extends JPanel implements ActionListener{ 
	
	Dimension screenSize;	
	static JLabel l1,l2,l3;
	public static  int m,c,q;
	private JButton BackToDashboard;

ExamGradePanel() throws SQLException
{

	screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	setBounds(0, 0,screenSize.width, screenSize.height);
    setLayout(null);
    setBackground(Color.WHITE);
    BackToDashboard = new JButton("Back To Dashboard");
    BackToDashboard.setBounds(screenSize.width/2-100,screenSize.height/2+200, 200, 50);
    add(BackToDashboard);
    BackToDashboard.addActionListener(this);

    initialize();
        
}

public static void data(String m,String c,String q)
{
		System.out.println(m+c+q);
		l1.setText("Marks Obtained"+"                  "+":"+"           "+m);
		l2.setText("Questions Attempted"+"           "+":"+"           "+q);
		l3.setText("Correctly Answered"+"             "+":"+"           "+c);
		/*----------------------Saving result in the database---------------------------*/
		String query;
		//saving the details of exam conduction in the database
		query="INSERT INTO examstudenttable VALUES('"+Exam.estid+"','"+Exam.EID+"','"+Dashboard.sid+"','"+Exam.dateTimeStamp+"')";
		System.out.println(query);
		try {
	  		Database.executeUpdate(query);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//saving student's answer of each question in the database
		for(int index=0;index<Exam.n;index++){
			query="INSERT INTO studentquestionanswertable values('";
			query+=Exam.estid+"','"+Exam.QID[index]+"','"+ExamRightPanel.optionChossen[index]+"')";
			System.out.println(query);
			try {
		  		Database.executeUpdate(query);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		}
		//saving final result in the database
		query="INSERT into result VALUES('"+Exam.estid+"','"+Exam.n+"','"+Exam.questionsAttempted+"','"+Exam.correctAnswers+"','"+Exam.MarksObtained+"','"+Exam.totalMarks+"')";
		try {
	  		Database.executeUpdate(query);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		/*------------------------------------------------------------------------------*/
}
	
private void initialize() throws SQLException {
    
    JLabel l=new JLabel();
    l.setBounds(screenSize.width/3-30, screenSize.height/3-80,600, 50);
    l.setText("Congratulations!! Paper submitted Successfully");
    l.setFont(new Font("Serif", Font.BOLD, 25));
    add(l);
    
    
   l1=new JLabel();
    l1.setBounds(screenSize.width/3+70, screenSize.height/3,400, 50);

    l1.setFont(new Font("Serif", Font.BOLD, 18));
    add(l1);
    
   l2=new JLabel();
    l2.setBounds(screenSize.width/3+70, screenSize.height/3+80,400, 50);
    
    l2.setFont(new Font("Serif", Font.BOLD, 18));
    add(l2);
    
   l3=new JLabel();
    l3.setBounds(screenSize.width/3+70, screenSize.height/3+160,400, 50);
    
    l3.setFont(new Font("Serif", Font.BOLD, 18));
    add(l3);
    
	}

@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton source = (JButton) arg0.getSource();
		if(source==BackToDashboard){
			DashboardExamShowPanel.examOb.setVisible(false);
			Dashboard.frame.setVisible(true);
			//Exam.questionsAttempted = 0;
		}
	}
	
}
