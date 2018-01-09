package studentpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.Timer;



public class ExamClockListener implements ActionListener {


private static final int N = 60;




JTextField tf;
    private int hours=Integer.parseInt(Exam.hours);  // fetch time from Exam class ,using Exam.H and Exam.M
    private int minutes=Integer.parseInt(Exam.minutes);
    private int seconds=0;
    private String hour;
    private String minute;
    private String second;
    JPanel gradePanel;
    JPanel CompositePanel;
    ExamRightPanel rightPanel;
    public ExamClockListener(JTextField tf,JPanel gradePanel,JPanel CompositePanel,ExamRightPanel ob) {
    this.gradePanel=gradePanel;
    this.CompositePanel=CompositePanel;    	
    this.tf=tf;
    rightPanel=ob;
	}

	@Override
    public void actionPerformed(ActionEvent e) {
	
        NumberFormat formatter = new DecimalFormat("00");
        
    	
    		if(hours==0&&minutes==0&&seconds==0){
    			CompositePanel.setVisible(false);
    			gradePanel.setVisible(true);
    			rightPanel.calculate();
    		}
    	
         
    		
    		
    		if(seconds==0)
    		{System.out.println(hours+":"+minutes+":"+seconds);
    			if(minutes!=0){
    				seconds=59;
    				minutes=minutes-1;
    			}else
    			{
    				minutes=59;
    				hours=hours-1;
    				seconds=59;
    			}
    		}
    
    	    hour = formatter.format(hours);
            minute = formatter.format(minutes);
            second = formatter.format(seconds);
            tf.setText(String.valueOf(hour + ":" + minute + ":" + second));
    		
            seconds--;


    	    
        
        
    }
}

