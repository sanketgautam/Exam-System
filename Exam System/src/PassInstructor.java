
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Login.User_Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class PassInstructor extends JPanel implements ActionListener
{
     Connection conn=null;
    public JTextField jt[]=new JTextField[2];
    public JPasswordField jpf[]= new JPasswordField[2];
    public JLabel jl1,jl3,jl4;
    public JComboBox jcb;
    public String str1[]={"What is your Favorite Food?","What is your Favorite Color??","What is your Favorite Movie Name?"};
    public String pass="",passc="",answer="",recovery_ques="";
    public JLabel jl2[]=new JLabel[5];
    public JButton jb;
    public int i;
     public void connect()
     {
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           conn=DriverManager.getConnection("jdbc:mysql://192.168.137.133:3306/examsystem","smarty", "exams");
        }
        catch(Exception e)
        {
          System.out.println(e);
        }
     }
     public PassInstructor()
    {    
        this.setLayout(null);
        jl1=new JLabel("INFORMATION SUBMITTED SUCCESSFULLY !!!");
        Font f =new Font("Arial",Font.BOLD,15);
        jl1.setFont(f);
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension scr = tk.getScreenSize();
        jl1.setBounds((int)(scr.width/3.8),(int)(scr.height/27),(int)(scr.width/1),(int)(scr.height/18));
        jl2[0]=new JLabel("Your LOGIN ID is");
        jl2[1]=new JLabel("Create Password");
        jl2[2]=new JLabel("Confirm Password");
        jl2[3]=new JLabel("Recovery Question");
        jl2[4]=new JLabel("Give Answer");
        jl3=new JLabel("Password doesn't matching");
        jl4=new JLabel("Password should not be more than 10 letters and is case sensitive");
        jb=new JButton("SUBMIT");
        Font f1=new Font("Arial",Font.BOLD,15);
        for(int i=0;i<5;i++)
        {
          jl2[i].setForeground(Color.BLUE);
          jl2[i].setFont(f1);
        }
        jpf[0]=new JPasswordField(10);
        jpf[1]=new JPasswordField(10);  
        jcb=new JComboBox(str1);
        jl2[0].setBounds((int)(scr.width/3.62),(int)(scr.height/8),(int)(scr.width/6.4),(int)(scr.height/36));
        jl2[1].setBounds((int)(scr.width/3.62),(int)(scr.height/5.84),(int)(scr.width/6.4),(int)(scr.height/36));
        jpf[0].setBounds((int)(scr.width/2.29),(int)(scr.height/5.84),(int)(scr.width/9.6),(int)(scr.height/36));
        jl2[2].setBounds((int)(scr.width/3.62),(int)(scr.height/4.6),(int)(scr.width/6.4),(int)(scr.height/36));
        jl3.setBounds((int)(scr.width/1.83),(int)(scr.height/4.6),(int)(scr.width/4.8),(int)(scr.height/54));
         jl4.setBounds((int)(scr.width/1.83),(int)(scr.height/5.84),(int)(scr.width/4.8),(int)(scr.height/54));
        jpf[1].setBounds((int)(scr.width/2.29),(int)(scr.height/4.6),(int)(scr.width/9.6),(int)(scr.height/36));
        jl2[3].setBounds((int)(scr.width/3.62),(int)(scr.height/3.78),(int)(scr.width/6.4),(int)(scr.height/36));
        jcb.setBounds((int)(scr.width/2.29),(int)(scr.height/3.78),(int)(scr.width/7.68),(int)(scr.height/36));
        jl2[4].setBounds((int)(scr.width/3.62),(int)(scr.height/3.22),(int)(scr.width/6.4),(int)(scr.height/36));
        jb.setBounds((int)(scr.width/2.2),(int)(scr.height/2.48),(int)(scr.width/12.8),(int)(scr.height/27));
        jt[0]=new JTextField(12);
        jt[1]=new JTextField(12);
        this.connect();
        jt[0].setForeground(Color.BLACK);
        jt[1].setForeground(Color.BLACK);
        jt[0].setFont(f1);
        jt[1].setFont(f1);
        jt[0].setText(this.getLoginId());
        jt[0].setBounds((int)(scr.width/2.29),(int)(scr.height/8),(int)(scr.width/9.6),(int)(scr.height/36));
        jt[1].setBounds((int)(scr.width/2.29),(int)(scr.height/3.22),(int)(scr.width/9.6),(int)(scr.height/36));
        jt[0].setEditable(false);
        jb.addActionListener(this);
        jcb.addActionListener(this);
        add(jl4);
        add(jl3);
        jl4.setVisible(true);
        jl3.setVisible(false);
        add(jt[0]);
        add(jpf[0]);
        add(jpf[1]);
        add(jl2[0]);
        add(jl2[1]);
        add(jl2[2]);
        add(jl2[3]);
        add(jl2[4]);
        add(jcb);
        add(jt[1]);
        add(jb);
        this.add(jl1);
        //jl2[0].setBounds();
    }
      public String getLoginId()
    {
       String s="";
      
       try
       {
         String Query="select max(iid) from instructor;";
         Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery(Query);
           rs.next();
           i=rs.getInt(1);
           s="INST"+i;
       }
       catch(Exception e)
       { 
           //System.out.println("hello");
         System.out.println(e);
       }      
       return s;
    }
       public void actionPerformed(ActionEvent e)
    {
      if(e.getActionCommand().equals("SUBMIT"))
      {
          pass=jpf[0].getText();
          passc=jpf[1].getText();
          answer=jt[1].getText();
          recovery_ques=(String)jcb.getSelectedItem();
          if((pass!=null)&&(pass.equals(passc))&&(answer!=null))
          {
             setPasswdRecovery();
             jl3.setVisible(false);
             
             JOptionPane.showMessageDialog(this, "Your Id Has Been Successfully created\n   Please Login to Continue Further");
             
             User_Login.frame.setVisible(true);
        	 User_Login.createGUI();
        	 MyFrame.frame.setVisible(false);
          }
           else
          {
             jl3.setVisible(true);
          }
      }
    }
        public void setPasswdRecovery()
    {
       String Query="update instructor set recovery_question=?,answer=?,password=? where iid="+i+";";
       try
       {
          PreparedStatement pst=conn.prepareStatement(Query);
          pst.setString(1, recovery_ques);
          pst.setString(2, answer );
          pst.setString(3, pass);
        //   pst.setInt(4, 1);
          pst.executeUpdate();
        }
         catch(Exception e)
         {
            System.out.println(e);
         }
    }
}
