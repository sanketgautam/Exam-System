package studentpanel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ExamLeftPanel extends JPanel implements ActionListener{

	
	
	Dimension 	screenSize;
    static  Timer timer;
	JButton b4,finishTest;
	
	JPanel CompositePanel;
    JLabel questions;  	 
	ExamRightPanel ob;
    int k;
    JPanel gradePanel;
   public final JTextField tf = new JTextField();
   
   
   ExamLeftPanel(ExamRightPanel p,JPanel CompositePanel,JPanel gradePanel)
   {
	 this.gradePanel=gradePanel;
	  this.CompositePanel=CompositePanel;
	  tf.setHorizontalAlignment(JTextField.RIGHT);
	  tf.setEditable(false);
	 
	   
	  ob=p;
	
	  screenSize=Toolkit.getDefaultToolkit().getScreenSize();
					


//---------------------------------------------------------------------------------------adding panel-------------------
	 
	
   
     

   addingContentToPanel();

  // addTimer();
   finishTest.addActionListener(this);

	
		
   }//end-of-constructor 
              

	public void addTimer( ){
		
		  
		//setTimer(timer);
    		
		
    }
    
 //-----------------------------------------------------------------------------addingContent to panel---------------------   
	private void addingContentToPanel() {
		
	//   questionAttempted=new JLabel();
	   finishTest=new JButton();
	   questions=new JLabel();
//----------------------------------------
	   
	   questions.setText("Questions:");  //No. of Q
	   questions.setFont(new Font("Serif", Font.BOLD, 14));

	  // questionAttempted.setText("Question Attempted:"+"2/29");
	   //questionAttempted.setFont(new Font("Serif", Font.BOLD, 14));
	    finishTest.setText("Finish Test");
//----------------------------------------
	 tf.setBounds(screenSize.width/9-30, 30, 65, 40);
	  tf.setFont(new Font("Serif", Font.BOLD, 14));
	 questions.setBounds(screenSize.width/9-30, screenSize.height/6, 150, 100);                 ////////
	   //questionAttempted.setBounds(screenSize.width/9-50, screenSize.height/2+100, 160, 100);
	   finishTest.setBounds(screenSize.width/9-50, screenSize.height/2+250, 100, 42);
	   
	   
	   
//----------------------------------------
	   setLayout(null);
	   setBackground(Color.GRAY);
	   setSize(screenSize.width/4, screenSize.height);
	   
	   
	   add(questions);
   	   //add(questionAttempted);
   	   add(finishTest);
   	   add(tf);
   	   
	
   	   
   	  

} //end of fun
	
//-----------------------------------------------------------------------------------------set timer-------------------
	public  void setTimer(JPanel gradePanel,JPanel CompositePanel) {
		   ExamClockListener cl = new ExamClockListener(tf,gradePanel,CompositePanel,ob);
			timer = new Timer(1000, cl); 	

	    timer.start();
	    /*try {
	    	
	      Thread.sleep(60*60*60000);
	    } catch (InterruptedException e) {
	    }*/
	    //timer.stop();
	}

//------------------------------------------------------------------------------------------------------------------------
	
	

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
        Object source = e.getSource();
		
		if(source==finishTest){ 
		//	ArrayList<GradeDetail> list=new ArrayList<GradeDetail>();
			ob.calculate();
			CompositePanel.setVisible(false);
			gradePanel.setVisible(true);
			
			
			System.out.println("finish clicked");
			
		}
		
	}


	
	

	
}
