package Login;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

class NewJFrame2 extends JFrame
{
     NewJFrame2(int id)
     {
        this.setLocationRelativeTo(null);
        //setLayout(null);
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension d=new Dimension();
        d=tk.getScreenSize();
        this.setBounds(d.width/4,d.height/3,d.width/2,d.height/3);
        
        this.setTitle("Change Password Instructor");
        Changepassstu2 cp=new Changepassstu2(this,id);
        Container ct =this.getContentPane();
        ct.add(cp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
     }
}
class Changepassstu2 extends JPanel implements ActionListener
{
    JPasswordField jtf1,jtf2,jtf3;
    JButton jb;
    JLabel jl1,jl2,jl3,jl,jl4;
    public static Connection conn;
    NewJFrame2 n;
    int id;
    Changepassstu2(NewJFrame2 m,int id)
    {
    	this.id =id;
        this.n=m;
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension d=new Dimension();
        d=tk.getScreenSize();
        setLayout(null);
        Font f = new Font("Arial",Font.PLAIN,20);
      jl1=new JLabel("OLD PASSWORD");
      jl2=new JLabel("NEW PASSWORD");
      jl3=new JLabel("CONFIRM PASSWORD");
      jl=new JLabel("Passwords are not matching");
      jl4=new JLabel("Password is Incorrect");
      jb= new JButton("SUBMIT");
      jl1.setForeground(Color.BLUE);
       jl2.setForeground(Color.BLUE);
        jl3.setForeground(Color.BLUE);
      jtf1=new JPasswordField(10);
      jtf2=new JPasswordField(10);
      jtf3=new JPasswordField(10);
      jb.addActionListener(this);
      jl1.setBounds((int)(d.width/8.35),(int)(d.height/21.6),(int)(d.width/7.68),(int)(d.height/36));
      jl2.setBounds((int)(d.width/8.35),(int)(d.height/12),(int)(d.width/7.68),(int)(d.height/36));
      jl3.setBounds((int)(d.width/8.35),(int)(d.height/8.5),(int)(d.width/7.68),(int)(d.height/36));
      jl.setBounds((int)(d.width/2.9),(int)(d.height/12),(int)(d.width/4.8),(int)(d.height/36));
      jl4.setBounds((int)(d.width/2.9),(int)(d.height/21.6),(int)(d.width/4.8),(int)(d.height/36));
      jtf1.setBounds((int)(d.width/4.27),(int)(d.height/21.6),(int)(d.width/9.6),(int)(d.height/36));
      jtf2.setBounds((int)(d.width/4.27),(int)(d.height/12),(int)(d.width/9.6),(int)(d.height/36));
      jtf3.setBounds((int)(d.width/4.27),(int)(d.height/8.3),(int)(d.width/9.6),(int)(d.height/36));
      jtf1.setFont(f);
      jtf2.setFont(f);
      jtf3.setFont(f);
      jtf1.setForeground(Color.BLACK);
      jtf2.setForeground(Color.BLACK);
      jtf3.setForeground(Color.BLACK);
      jl1.setFont(f);
      jl2.setFont(f);
      jl3.setFont(f);
      jb.setBounds((int)(d.width/4.8),(int)(d.height/6),(int)(d.width/19.2),(int)(d.height/36));
      jl.setVisible(false);
      jl4.setVisible(false);
      jl.setForeground(Color.red);
      jl4.setForeground(Color.red);
      add(jl1);
      add(jl2);
      add(jl3);
      add(jb);
      add(jtf1);
      add(jtf2);
      add(jtf3);
      add(jl);
      add(jl4);
    }
    /*public Changepassstu2(NewJFrame2 newJFrame2, int id2) {
		// TODO Auto-generated constructor stub
	}*/
	public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("SUBMIT"))
        {
            String str=jtf1.getText();
            connect();
            try{
            String Q="select password from instructor where iid="+id;
             Statement st=conn.createStatement();
             ResultSet rs=st.executeQuery(Q);
             rs.next();
             String s=rs.getString(1);
             if(!(str.equals(s)))
             {
                jl4.setVisible(true);
             }
             else
             {
                 jl4.setVisible(false);
                  if(!(jtf2.getText().equals(jtf3.getText())))
                   {
                      jl.setVisible(true);  
                   }
                 else
                   {
                      jl.setVisible(false);
                      String Query="update instructor set password=? where iid="+id;
                       PreparedStatement pst=conn.prepareStatement(Query);
                       pst.setString(1,jtf2.getText());
                       pst.executeUpdate();
                       JOptionPane.showMessageDialog(null,"Password Changed Successfully","OK",JOptionPane.DEFAULT_OPTION);
                       n.setVisible(false);
                   }
             }
            }
            catch(Exception ex)
            {
              System.out.println(ex);
            }
          
        }
    }
     public void connect()
     {
       
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           
           conn=DriverManager.getConnection("jdbc:mysql://192.168.137.133:3306/examsystem","smarty","exams");
        }
        catch(Exception e)
        {
          System.out.println(e);
        }
     }
}


public class ChangePassword2
{
     public static NewJFrame2 nj;
    public ChangePassword2(int id)
    {
       nj=new NewJFrame2(id);
    }
    
}
