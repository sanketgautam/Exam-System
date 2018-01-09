package Instructor;

import java.awt.Rectangle;
import javax.swing.*;

import Login.ChangePassword;
import Login.ChangePassword2;
import Login.dbConnector;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.*;
//import java.net.URL;

public class RightPanel extends JPanel {
	/**
	 * 
	 */
	static dbConnector dbc = new dbConnector();
	private static final long serialVersionUID = 1L;
	int n,editLabelCount;
	JLabel[] label1;
	JLabel[] label2;
	JTextField[] tfs;
	JButton edit,cPassword;
	boolean editStatus = false;
	void createRightPanel(String[][] details,byte[] b)throws Exception{
		
		n = details.length;
		label1 = new JLabel[n];
		label2 = new JLabel[n];
		//setting the layout of panel absolute
		setLayout(null);
		/*Getting Bounds of the panel*/
		Rectangle bounds = super.getBounds();
		
		int x = bounds.x;
		int y = bounds.y;
		int width = (int)bounds.getWidth();
		int height = (int)bounds.getHeight();
		int yi = (int)(height*0.2);
		//adding the content to the panel
		int index=0;
		for(String[] info : details){
			System.out.println(info[0]+" : "+info[1]);
			if(info[0]=="image")
			{	
				JLabel picLabel = new JLabel(new ImageIcon(b));
		        picLabel.setBounds((int)(width*0.80), (int)(height*0.2), 130, 150);
		        super.add(picLabel);
		        n--;
			}else{
				System.out.println("start");
				label1[index] = new JLabel(info[0]+" : ");
				if(info[0]=="Id No")
					label2[index] = new JLabel("STD"+info[1]);
				else
					label2[index] = new JLabel(info[1]);
				label1[index].setBounds((int)(0.05*width), yi, 100, 50);
				label2[index].setBounds((int)(0.05*width)+100, yi, 400, 50);
				yi+=(10+50);
				super.add(label1[index]);
				super.add(label2[index]);
				index++;
			
		}
		}
		/*Setting up parameters for Picture class*/
		
		//scaling the image
		/*BufferedImage toolkitImage = bi.getScaledInstance(130, 150,BufferedImage.SCALE_SMOOTH);
		int width = toolkitImage.getWidth(null);
		int height = toolkitImage.getHeight(null);
		bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);*/
		//Picture pic = new Picture(bi.getWidth(),bi.getHeight(),bi.getType());
		// paint something to the new image!
       
        
        
		//printing new image on JOptionPane
		JLabel headingLabel = new JLabel("Dashboard");
		headingLabel.setBounds((int)(width*0.5)-50, (int)(height*0.05), 200, 50);
		//creating new font for heading
		//Font font = new Font();
		//headingLabel.setFont(font);
		/*JLabel picLabel = new JLabel(new ImageIcon(bi));
        picLabel.setBounds((int)(width*0.80), (int)(height*0.2), 130, 150);
        JLabel nameLabel = new JLabel("Name : ");
        nameLabel.setBounds((int)(0.05*width), y+10, 100, 50);*/
        edit = new JButton("Edit Details");
        cPassword = new JButton("Change Password");
        edit.setBounds((int)(0.3*width), yi+50, 200, 50);
        cPassword.setBounds((int)(0.3*width)+220, yi+50, 200, 50);
        /*Adding event listener to the buttons*/
        edit.addActionListener(new CustomActionListener());
        cPassword.addActionListener(new CustomActionListener());
        /*Adding Labels and Buttons onto the panel*/
        add(headingLabel);
        add(edit);
        add(cPassword);
        
        /*super.add(picLabel);
        super.add(nameLabel);*/
	}
	
	class CustomActionListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	         // System.out.println("editStatus : "+editStatus);
	    	if(e.getSource()==(Object)cPassword)
	    	{
	    		ChangePassword2 ob = new ChangePassword2(Instructor.instructorid);
	    	}
	    	else
	    	{
	    	  if(editStatus==false)
	          {
	    		  //allocating the JTextFields array
		    	  tfs = new JTextField[n];
		    	  //doing all the magic
		    	  for(int index = 0;index<n;index++)
		    	  {
		    		  if(!label1[index].getText().startsWith("Id No")&&!label1[index].getText().startsWith("Group(s)")){
			    		  JLabel label = label2[index];
			    		  /*Getting Bounds of the label*/
			    		  Rectangle bounds = label.getBounds();
			    		  /*Creating new JTextFields for respective labels*/
			    		  tfs[index]= new JTextField();
			    		  tfs[index].setText(label.getText());
			    		  tfs[index].setBounds(bounds);
			    		  RightPanel.super.remove(label);
			    		  RightPanel.super.add(tfs[index]);
		    		  }
		    	  }
		    	  edit.setText("Save Changes");
		    	  editStatus =true;
	          
	          }else{
	        	  int indexof = tfs[1].getText().indexOf(" ");
	        	  String first_name = tfs[1].getText().substring(0, indexof);
	        	  String last_name = tfs[1].getText().substring(indexof+1);
	        	  String dob = tfs[2].getText();
	        	  String phone_no = tfs[3].getText();
	        	  String email_id = tfs[4].getText();
	        	  String institute_name = tfs[6].getText();
	        	  String address = tfs[7].getText();
	        	  
	        	  String[] queries={"UPDATE instructor SET first_name = '"+first_name+"' WHERE iid="+Dashboard.iid,
	        			  			"UPDATE instructor SET last_name = '"+last_name+"' WHERE iid="+Dashboard.iid,
	        			  			"UPDATE instructor SET dob = '"+dob+"' WHERE iid="+Dashboard.iid,
	        			  			"UPDATE instructor SET phone_no = '"+phone_no+"' WHERE iid="+Dashboard.iid,
	        			  			"UPDATE instructor SET email_id = '"+email_id+"' WHERE iid="+Dashboard.iid,
	        			  			"UPDATE instructor SET institute_name = '"+institute_name+"' WHERE iid="+Dashboard.iid,
	        			  			"UPDATE instructor SET address = '"+address+"' WHERE iid="+Dashboard.iid
	        			  };
	        	  for(String query : queries){
	        		  	try {
	        		  		Statement stmt = dbc.conn.createStatement();
	        		  		
	        		  		stmt.executeUpdate(query);
	        		  		
		  				} catch (SQLException e1) {
		  					// TODO Auto-generated catch block
		  					e1.printStackTrace();
		  				}
	        	  }
	        	 System.out.println("Data Saved Successfully in the database !");
	        	  /*-------------------------*/
	        	  for(int index = 0;index<n;index++)
		    	  {
	        		  
	        		  if(!label1[index].getText().startsWith("Id No")&&!label1[index].getText().startsWith("Group(s)")){
	        			  ///System.out.println(label1[index].getText());
			    		  JTextField tf = tfs[index];
			    		  /*Getting Bounds of the tf*/
			    		  Rectangle bounds = tf.getBounds();
			    		  /*Creating new JLabel for repective tf
			    		  tfs[index]= new JTextField(6);
			    		  tfs[index].setText(label.getText());
			    		  tfs[index].setBounds(bounds);*/
			    		  label2[index].setBounds(bounds);
			    		  label2[index].setText(tf.getText());
			    		  RightPanel.super.remove(tf);
			    		  RightPanel.super.add(label2[index]);
	        		  }
		    	  }
	        	  RightPanel.super.setVisible(false);
	        	  RightPanel.super.setVisible(true);
		    	  edit.setText("Edit Details");
	        	  editStatus =false;
	          }	      }}
	   }	
}
