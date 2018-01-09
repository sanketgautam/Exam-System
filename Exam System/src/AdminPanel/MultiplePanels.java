package AdminPanel;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import Login.User_Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.*;
class MyConnection
{
    public static Connection conn=null;
   MyConnection()
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
}
class MyJframe extends JFrame
{
	static JFrame frame;
    MyJframe(String title)
    {
    	super(title);
    	frame=this;
      
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
      this.setBounds(0,0,d.width,d.height);
      setLayout(null);
      Container ct;
       ct=this.getContentPane();
       this.setUndecorated(true);
      MyJpanel1 mjp1=new MyJpanel1();
      DummyPanel11 dp11=new DummyPanel11();
      DummyPanel12 dp12=new DummyPanel12();
      DummyPanel13 dp13=new DummyPanel13();
      DummyPanel14 dp14=new DummyPanel14();
      DummyPanel15 dp15=new DummyPanel15();
      MyJpanel mjp=new MyJpanel(ct,dp11,dp12,dp13,dp14,dp15,mjp1);      
      ct.add(mjp1);
      ct.add(mjp);           
      ct.add(dp11);
      ct.add(dp12);
      ct.add(dp13);
      ct.add(dp14);
      ct.add(dp15);
      this.setVisible(true);      
    }
}
class MyJpanel extends JPanel implements MouseListener  //left side
{
    public JButton jl[]=new JButton[4];
    String arr[]={"asd","qwerty","",""};
   // public static int groupnumber=1;
    Container c;
    DummyPanel11 dp111;
    DummyPanel12 dp112;
    DummyPanel13 dp113;
    DummyPanel14 dp114;
    DummyPanel15 dp115;
    MyJpanel1 mjp1;
       SubPanel13 sp13=new SubPanel13(arr);
       SubPanel11 sp11=new SubPanel11(sp13);
       SubPanel14 sp14=new SubPanel14();
       SubPanel12 sp12=new SubPanel12(sp14);
       SubPanel15 sp15=new SubPanel15(c,sp11,sp12,sp13,sp14,this); 
       SubPanel2 groupdetails =new SubPanel2();
       SubPanel3 memberdetails =new SubPanel3();
       
   MyJpanel(Container c,DummyPanel11 dp11,DummyPanel12 dp12,DummyPanel13 dp13,DummyPanel14 dp14,DummyPanel15 dp15,MyJpanel1 mjp1)
   {
      this.c=c; 
      dp111=dp11;
      dp112=dp12;
      
      dp113=dp13;
      dp114=dp14;
      dp115=dp15;
      this.mjp1=mjp1;
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
      this.setBounds(0,0,d.width/4,d.height);
      Color col=new Color(120,0,200);
      this.setBackground(Color.pink);
      Font f=new Font("Arial",Font.PLAIN,15);
      jl[0]=new JButton("Create Group");
      jl[1]=new JButton("Group Details");
      jl[2]=new JButton("View Group Member Details");
      jl[3]=new JButton("LOGOUT");
      for(int i=0;i<=3;i++)
      {
          jl[i].setFont(f);
          jl[i].addMouseListener(this);
      }
      setLayout(null);
      jl[0].setBounds(d.width/47,d.height/8,(int)(d.width/4.7),d.height/18);
      
      // e : 2.4 setbounds 1.05
      // d  : 2.5 mlac 0.38
      
      jl[1].setBounds(d.width/47,13*d.height/70,(int)(d.width/4.7),d.height/18);
      jl[2].setBounds(d.width/47,(17*d.height)/70,(int)(d.width/4.7),d.height/18);
      jl[3].setBounds(d.width/47,(21*d.height)/70,(int)(d.width/4.7),d.height/18);
        add(jl[0]);
        add(jl[1]);
        add(jl[2]);
        add(jl[3]);
        sp15.jb.addMouseListener(this);
   }
  public void mouseClicked(MouseEvent m)
   {
        
       if(m.getSource()==jl[0])
       {
     
          dp111.setVisible(false);
          dp112.setVisible(false);
          dp113.setVisible(false);
          dp114.setVisible(false);
          dp115.setVisible(false);
          groupdetails.setVisible(false);
          memberdetails.setVisible(false);
           sp11.setVisible(true);
           sp12.setVisible(true);
           sp13.setVisible(true);
           sp14.setVisible(true);
           sp15.setVisible(true);
          c.add(sp11);
          c.add(sp12);
          c.add(sp13);
          c.add(sp14);
          c.add(sp15);
         // c.repaint();
         // System.out.println("hello");
                
       }
       if(m.getSource()==jl[1])
       {
          dp111.setVisible(false);
          dp112.setVisible(false);
          dp113.setVisible(false);
          dp114.setVisible(false);
          dp115.setVisible(false);
           sp11.setVisible(false);
           sp12.setVisible(false);
           sp13.setVisible(false);
           sp14.setVisible(false);
           sp15.setVisible(false);
           memberdetails.setVisible(false);
           groupdetails.setVisible(true);
           c.add(groupdetails);
           // c.repaint();
       }
         if(m.getSource() ==jl[2])
       {
            dp111.setVisible(false);
          dp112.setVisible(false);
          dp113.setVisible(false);
          dp114.setVisible(false);
          dp115.setVisible(false);
           sp11.setVisible(false);
           sp12.setVisible(false);
           sp13.setVisible(false);
           sp14.setVisible(false);
           sp15.setVisible(false);
           groupdetails.setVisible(false);
           memberdetails.setVisible(true);
           c.add(memberdetails);
       }
       if(m.getSource()==jl[3])
       {    
            dp111.setVisible(false);
          dp112.setVisible(false);
          dp113.setVisible(false);
          dp114.setVisible(false);
          dp115.setVisible(false);
           sp11.setVisible(false);
           sp12.setVisible(false);
           sp13.setVisible(false);
           sp14.setVisible(false);
           sp15.setVisible(false);
           groupdetails.setVisible(false);
           memberdetails.setVisible(false);
           mjp1.setVisible(false);
           this.setVisible(false);
           
           this.setVisible(false);
           
           MyJframe.frame.dispose();
           
           User_Login.frame.setVisible(true);
           User_Login.createGUI();
       }
       if(m.getSource()==sp15.jb)   //submit ki coding 
       {
           sp11.setVisible(false);
           sp12.setVisible(false);
           sp13.setVisible(false);
           sp14.setVisible(false);
           sp15.setVisible(false);
          int arraystu[];
          int ins;
          int j=0;
        
          if(sp11.arrqw!=null)
          {
              arraystu=new int[sp11.arrqw.length];
            for(int i=0;i<sp11.arrqw.length;i++)
          {
            if(!(sp11.arrqw[i].equals("")))
            {
                String str=sp11.arrqw[i].substring(3);
                arraystu[j]=Integer.parseInt(str);
                String Query="insert into student_group values (?,?);";
                try
                {
                    PreparedStatement pst=MyConnection.conn.prepareStatement(Query);
                    pst.setInt(1, Integer.parseInt(sp15.gt.getText()));
                    pst.setInt(2,arraystu[j]);
                    pst.executeUpdate();
                }
                catch(Exception ex)
                {
                   System.out.println(ex);
                }
                j++;
               
            }
            
          }  
          }
       
          j=0;
          if(sp12.arrqw!=null)
          {
              arraystu=new int[sp12.arrqw.length];
            for(int i=0;i<sp12.arrqw.length;i++)
          {
            if(!(sp12.arrqw[i].equals("")))
            {
                String str=sp12.arrqw[i].substring(4);
                arraystu[j]=Integer.parseInt(str);
                String Query="insert into instructor_group values (?,?);";
                try
                {
                    PreparedStatement pst=MyConnection.conn.prepareStatement(Query);
                    pst.setInt(1, Integer.parseInt(sp15.gt.getText()));
                    pst.setInt(2,arraystu[j]);
                    pst.executeUpdate();
                }
                catch(Exception ex)
                {
                   System.out.println(ex);
                }
                j++;
               
            }
            
          }  
          }
      
       SubPanel13 sp3=new SubPanel13(arr);
       SubPanel11 sp1=new SubPanel11(sp3);
       SubPanel14 sp4=new SubPanel14();
       SubPanel12 sp2=new SubPanel12(sp4);
       SubPanel15 sp5=new SubPanel15(c,sp1,sp2,sp3,sp4,this);
        sp11=sp1;       
        sp12=sp2;
        sp13=sp3;
        sp14=sp4;        
        sp15=sp5;
        sp11.setVisible(true);
       sp12.setVisible(true);
       sp13.setVisible(true);
       sp14.setVisible(true);
       sp15.setVisible(true); 
       c.add(sp13);
       c.add(sp11);
       c.add(sp12);
       c.add(sp14);
       c.add(sp15); 
    // System.out.println("hwwwww");
       }
     
   }
  public void mouseEntered(MouseEvent m)
   {
       if(m.getSource()==jl[0])
       {
         jl[0].setForeground(Color.black);  
       }
       if(m.getSource()==jl[1])
       {
         jl[1].setForeground(Color.black);  
       }
       if(m.getSource()==jl[2])
       {
         jl[2].setForeground(Color.black);  
       }
       if(m.getSource()==jl[3])
       {
         jl[3].setForeground(Color.black);  
       }
      
   }
   public void mousePressed(MouseEvent m)
   {
       
   }
   public void mouseExited(MouseEvent m)
   {
       if(m.getSource()==jl[0])
       {
         jl[0].setForeground(Color.BLACK);  
       }
       if(m.getSource()==jl[1])
       {
         jl[1].setForeground(Color.black);  
       }
       if(m.getSource()==jl[2])
       {
         jl[2].setForeground(Color.black);  
       }
       if(m.getSource()==jl[3])
       {
         jl[3].setForeground(Color.black);  
       }
      
   }
  public void mouseReleased(MouseEvent m)
   {
       
   }
   
}
class MyJpanel1 extends JPanel  //orange color
{
    public JLabel jl;
   MyJpanel1()
   {
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
      setLayout(null);
      Font f=new Font("Arial",Font.BOLD,15);
      jl=new JLabel("ADMIN DASHBOARD");
      jl.setFont(f);
      jl.setForeground(Color.blue);
      jl.setBounds(d.width/2,0,d.width/3,d.height/14);      
     // add(jl);
      this.setBounds(0,0,d.width,d.height/20);
      this.setBackground(Color.lightGray);
      //this.setVisible(false);
      
   }
}

class SubPanel11 extends JPanel implements ActionListener //create group ka pehla panel
{
    public JLabel jl,jl1;
    public JScrollPane jsp;
    public JList <String> jlst;
    public String arr[];
    public Integer ar[];
    public JButton jb;
    SubPanel13 sp13;
    JList mylist=null;
    JScrollPane jsp2=null;
    int current;
    String arrqw[]=null,s1[]=null;
   SubPanel11(SubPanel13 sp13)
   {
       this.sp13=sp13;
     
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d,d1;
      d=new Dimension();
      d1=new Dimension();
      d=tk.getScreenSize();
      setLayout(null);
      Font f=new Font("Arial",Font.BOLD,15);
      jb=new JButton("OK");
      jl=new JLabel("ADD STUDENT");
      jl1=new JLabel("Only Max 5 Students in a group are permitted");
      jl.setFont(f);
      jl.setForeground(Color.blue);
      d1=jl.getPreferredSize();
       jl.setBounds(d.width/24,d.height/14,d1.width,d1.height);
      this.add(jl);
      this.getStudentId();
      
      jlst=new JList <String>(arr);
      jlst.setFont(f);
      jlst.setForeground(Color.BLUE);
      jlst.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);    
      jsp=new JScrollPane(jlst);  
      jsp.setBounds(d.width/24,d.height/8,(int)(d.width/9.6),(int)(d.height/2.7));
      jb.setBounds(d.width/24,(int)(d.height/1.96) ,d.width/24,(int)(d.height/36));
      jl1.setBounds(d.width/64,(int)(d.height/1.85) ,(int)(d.width/4.8),(int)(d.height/43.2));
      jl1.setForeground(Color.red);
      jb.addActionListener(this);
     sp13.jb.addActionListener(this);
       add(jsp);
       add(jb);
      // add(jl1);
      this.setBounds(d.width/4,d.height/14,3*d.width/16,d.height);
    //  this.setBackground(Color.black);
   }
  
     
     public void getStudentId()
     {
       
        int i;
         try
         { 
          String Q="select count(sid) from student";
          Statement st=MyConnection.conn.createStatement();
          ResultSet rs=st.executeQuery(Q);
          rs.next();
          i=rs.getInt(1);
          ar=new Integer[i];
          arr=new String[i];
          String Query="select sid from student"; 
          Statement st1=MyConnection.conn.createStatement();
         ResultSet rs1=st1.executeQuery(Query);
         rs1.next();
         Font f=new Font("Arial",Font.PLAIN,15);
         for(int j=0;j<i;j++)
          {
              
             ar[j]=rs1.getInt(1);
             arr[j]="STD"+ar[j];
             rs1.next();
          }
        
         }
         catch(Exception e)
         {
           e.printStackTrace();
         }
     }
   public void actionPerformed(ActionEvent e)
   {
       int selected[] = jlst.getSelectedIndices();
         arrqw=new String[selected.length];
         
       if(e.getActionCommand().equals("OK"))
       {
           current=selected.length;
          for (int i=0; i<selected.length; i++)
          {   
              String element =(String)jlst.getModel().getElementAt(selected[i]);
             arrqw[i]=element;   
           }
          sp13.jsp1.setVisible(false);
          if(jsp2!=null){
            if(mylist!=null){
                jsp2.remove(mylist);
            }
            sp13.remove(jsp2);
          }
           mylist=new JList(arrqw);
           Font f=new Font("Arial",Font.BOLD,15);
           mylist.setFont(f);
           mylist.setForeground(Color.blue);
           jsp2=new JScrollPane(mylist);
          Dimension d=new Dimension();
          Toolkit tk=Toolkit.getDefaultToolkit();
          d=tk.getScreenSize();
          jsp2.setBounds(d.width/24,d.height/8,(int)(d.width/9.6),(int)(d.height/5));   
          sp13.add(jsp2);
          MultiplePanels.m.setVisible(false);
          MultiplePanels.m.setVisible(true);
       }
         if(e.getActionCommand().equals("Remove"))
       {
           
         int selected1[] = mylist.getSelectedIndices();
           int s[]=new int[current-selected1.length];
            arrqw=new String[current-selected1.length];          //s1
        
          int count=0,ko=0,flag=0;
          for(int i=0;i<current;i++)
          {
             for(int j=0;j<selected1.length;j++)
             {
                 if(selected1[j]==i)
                 {
                    flag=1;
                 }
             }
             if(flag==0)
             {
                s[count]=i;
                count++;
             } 
             flag=0;
          }
          for(int i=0;i<current-selected1.length;i++)
          {
              String element =(String)mylist.getModel().getElementAt(s[i]);
              arrqw[i]=element;      //s1
          }
          current=current-selected1.length;
          sp13.jsp1.setVisible(false);
          if(jsp2!=null){
            if(mylist!=null){
                jsp2.remove(mylist);
            }
            sp13.remove(jsp2);
          }
           mylist=new JList(arrqw);                  //s1
           Font f=new Font("Arial",Font.BOLD,15);
           mylist.setFont(f);
           mylist.setForeground(Color.blue);
           jsp2=new JScrollPane(mylist);
          Dimension d=new Dimension();
          Toolkit tk=Toolkit.getDefaultToolkit();
          d=tk.getScreenSize();
          jsp2.setBounds(d.width/24,d.height/8,(int)(d.width/9.6),(int)(d.height/5));   
          sp13.add(jsp2);
          MultiplePanels.m.setVisible(false);
          MultiplePanels.m.setVisible(true);
       }
      
 
   }  
}
class SubPanel12 extends JPanel implements ActionListener //create group ka doosra panel
{
    public JLabel jl,jl1;
    public JScrollPane jsp;
    public JList <String> jlst;
    public JButton jb;
    public String arr[];
    public Integer ar[];
    public  int myarr[];
    SubPanel14 sp14;
    JList mylist=null;
    JScrollPane jsp2=null;
     int current;
    String arrqw[]=null,s1[]=null;
   SubPanel12(SubPanel14 sp14)
   {
      this.sp14=sp14;
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
       Dimension d1=new Dimension();
      d=tk.getScreenSize();
      setLayout(null);
      Font f=new Font("Arial",Font.BOLD,15); 
      this.getInstructorId();      
      jlst=new JList <String>(arr); 
      jlst.setFont(f);
      jlst.setForeground(Color.blue);
      jlst.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      jsp=new JScrollPane(jlst);
      jl=new JLabel("ADD INSTRUCTOR");
      jl1=new JLabel("Only one Instructor per group is permitted");
      jb=new JButton("OK");
      jl.setFont(f);
      jl.setForeground(Color.blue);
      d1=jl.getPreferredSize();
      jl.setBounds(d.width/24,d.height/14,d1.width,d1.height);
       jb.setBounds(d.width/24,(int)(d.height/1.96),d.width/24,d.height/36);
      jl1.setBounds(d.width/64,(int)(d.height/1.85),(int)(d.width/4.8),(int)(d.height/43.2));
      jl1.setForeground(Color.red);
      jb.addActionListener(this);
      sp14.jb.addActionListener(this);
      add(jb);
      add(jl);
     // add(jl1);
      jsp.setBounds(d.width/24,(int)(d.height/8),(int)(d.width/9.6),(int)(d.height/2.7));
      add(jsp);
      this.setBounds(d.width*7/16,d.height/14,3*d.width/16,d.height);
     // this.setBackground(Color.black);      
   }
   
     public void getInstructorId()
     {
      
        int i;
         try
         { 
          String Q="select count(iid) from instructor;";
          Statement st=MyConnection.conn.createStatement();
          ResultSet rs=st.executeQuery(Q);
          rs.next();
          i=rs.getInt(1);
          ar=new Integer[i];
          arr=new String[i];
          String Query="select iid from instructor;";
          Statement st1=MyConnection.conn.createStatement();
         ResultSet rs1=st1.executeQuery(Query);
         rs1.next();
         
         for(int j=0;j<i;j++)
          {
             ar[j]=rs1.getInt(1); 
             arr[j]="INST"+ar[j];
             rs1.next();
          }
         }
         catch(Exception e)
         {
            
           e.printStackTrace();
         }
     }
   public void actionPerformed(ActionEvent e)
   {
       int selected[] = jlst.getSelectedIndices();
         arrqw=new String[selected.length];
         
       if(e.getActionCommand().equals("OK"))
       {
           current=selected.length;
          for (int i=0; i<selected.length; i++)
          {   
              String element =(String)jlst.getModel().getElementAt(selected[i]);
             arrqw[i]=element;   
           }
          sp14.jsp1.setVisible(false);
          if(jsp2!=null){
            if(mylist!=null){
                jsp2.remove(mylist);
            }
            sp14.remove(jsp2);
          }
           mylist=new JList(arrqw);
           Font f=new Font("Arial",Font.BOLD,15);
           mylist.setFont(f);
           mylist.setForeground(Color.blue);
           jsp2=new JScrollPane(mylist);
          Dimension d=new Dimension();
          Toolkit tk=Toolkit.getDefaultToolkit();
          d=tk.getScreenSize();
          jsp2.setBounds(d.width/24,d.height/8,(int)(d.width/9.6),(int)(d.height/5));   
          sp14.add(jsp2);
          MultiplePanels.m.setVisible(false);
          MultiplePanels.m.setVisible(true);
       }
         if(e.getActionCommand().equals("Remove"))
       {
           
         int selected1[] = mylist.getSelectedIndices();
           int s[]=new int[current-selected1.length];
            arrqw=new String[current-selected1.length];     //s1
        
          int count=0,ko=0,flag=0;
          for(int i=0;i<current;i++)
          {
             for(int j=0;j<selected1.length;j++)
             {
                 if(selected1[j]==i)
                 {
                    flag=1;
                 }
             }
             if(flag==0)
             {
                s[count]=i;
                count++;
             } 
             flag=0;
          }
          for(int i=0;i<current-selected1.length;i++)
          {
              String element =(String)mylist.getModel().getElementAt(s[i]);
              arrqw[i]=element;                          //s1
          }
          current=current-selected1.length;
          sp14.jsp1.setVisible(false);
          if(jsp2!=null){
            if(mylist!=null){
                jsp2.remove(mylist);
            }
            sp14.remove(jsp2);
          }
           mylist=new JList(arrqw);                            //s1
           Font f=new Font("Arial",Font.BOLD,15);
           mylist.setFont(f);
           mylist.setForeground(Color.blue);
           jsp2=new JScrollPane(mylist);
          Dimension d=new Dimension();
          Toolkit tk=Toolkit.getDefaultToolkit();
          d=tk.getScreenSize();
          jsp2.setBounds(d.width/24,d.height/8,(int)(d.width/9.6),(int)(d.height/5));   
          sp14.add(jsp2);
          MultiplePanels.m.setVisible(false);
          MultiplePanels.m.setVisible(true);
       }
 
   }     
   }

class SubPanel13 extends JPanel implements ActionListener //create group ka teesra panel
{
  //  public JTextField jtf[]=new JTextField[5];
   public JLabel jl;
   public JButton jb=new JButton("Remove");
   public JTable jta;
   public String []arr={"Group no.","No. of students"};
   public Object [][]arr1;
   public JScrollPane jsp,jsp1;
   public JList jlst;
   public String vansh[]={"",""};
   
   int m;
   SubPanel13(String arr3[])
   {
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      Dimension d1=new Dimension();
      Dimension d2=new Dimension();
      d=tk.getScreenSize();
      Font f=new Font("Arial",Font.BOLD,15);
      
      setLayout(null);
      jl=new JLabel("ADDED STUDENTS");
      jl.setFont(f);
      jl.setForeground(Color.blue);
      d2=jl.getPreferredSize();
     // d1=jtf[0].getPreferredSize();
     // connect();
      getallotedgroup();
      jta=new JTable(arr1,arr);
     jta.setFont(f);
      jta.setRowHeight(30);
      jta.disable();
      jsp=new JScrollPane(jta);
      jlst=new JList(vansh);
      jlst.setForeground(Color.BLUE);
      jlst.setFont(f);
      jsp1=new JScrollPane(jlst);
      jl.setBounds(d.width/24,d.height/14,d2.width,d2.height);
       jsp.setBounds(d.width/192,(int)(d.height/2.16),(int)(d.width/6.4),(int)(d.height/3.6));
       jsp1.setBounds(d.width/24,d.height/8,(int)(d.width/9.6),(int)(d.height/5));
       jb.setBounds(d.width/15,(int)(d.height*0.35),(int)(d.width/20),(int)(d.height/36));
      this.setBounds(d.width*5/8,d.height/14,3*d.width/16,d.height);
      
      add(jl);
      add(jsp);
      add(jsp1);
      add(jb);              
   }
   public void actionPerformed(ActionEvent e)
   {
      
   }
   
    public void getallotedgroup()
    {
        int va,ab;
       try
       {
         String Query="select count(distinct(group_id)) from student_group"; 
         Statement st=MyConnection.conn.createStatement();
         ResultSet rs=st.executeQuery(Query);
         rs.next();
         m=rs.getInt(1);
         arr1=new Object[m][2];
         String Query1="select group_id ,count(distinct(sid)) from student_group where group_id is not null group by(group_id)";
         Statement st1=MyConnection.conn.createStatement();
         ResultSet rs1=st1.executeQuery(Query1);
         rs1.next();
         for(int i=0;i<m;i++)
         {
            va= rs1.getInt(1);
            ab=rs1.getInt(2);
            arr1[i][0]=va;
            arr1[i][1]=ab;
            rs1.next();
            
         }
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
    }
}
class SubPanel14 extends JPanel implements ActionListener //create group ka fourth panel
{
   // JTextField jtf;
    JList jlst;
    String str[]={""};
    JButton jb;
    JLabel jl;
     public JTable jta;
    JScrollPane jsp,jsp1;
   // Connection conn;
     public String []arr={"Group no.","No. of instructor"};
   public Object [][]arr1;
   int m;
   SubPanel14()
   {
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      Dimension d1=new Dimension();
      Dimension d2=new Dimension();
      d=tk.getScreenSize();
     // jtf=new JTextField(10);
      jb=new JButton("Remove");
      jl=new JLabel("ADDED INSTRUCTOR");
    //  jtf.setEditable(false);
      Font f=new Font("Arial",Font.BOLD,15);
      jl.setFont(f);
    //  jtf.setFont(f);
      jl.setForeground(Color.blue);
      setLayout(null);
      d2=jl.getPreferredSize();
     // connect();
      getallotedgroup();
      jlst=new JList(str);
      jsp1=new JScrollPane(jlst);
      jta=new JTable(arr1,arr);
      jta.setFont(f);
      jta.setRowHeight(30);
      jta.disable();
      jsp=new JScrollPane(jta);
      jsp.setBounds(d.width/192,(int)(d.height/2.16),(int)(d.width/6.4),(int)(d.height/3.6));
       jsp1.setBounds(d.width/24,d.height/8,(int)(d.width/9.6),(int)(d.height/5));
       jb.setBounds(d.width/15,(int)(d.height*0.35),(int)(d.width/20),(int)(d.height/36));
       
     // d1=jtf.getPreferredSize();      
      jl.setBounds(d.width/24,d.height/14,d2.width,d2.height);
     // jtf.setBounds(d.width/d.width,d.height/14+2*d2.height,d1.width,d1.height);
     // jb.setBounds(d.width/d.width+d1.width+d1.width/7,d.height/14+2*d2.height,d1.width/2,d1.height);
      this.setBounds(d.width*13/16,d.height/14,3*d.width/16,d.height*3/4);
      add(jl);
      add(jsp);
      add(jsp1);
      add(jb);
     // add(jb);
     // add(jtf);
     // this.setBackground(Color.black);
      
   }
   public void actionPerformed(ActionEvent e)
   {
       
   }  
   
    public void getallotedgroup()
    {
        int va,ab;
       try
       {
         String Query="select count(distinct(group_id)) from instructor_group"; 
         Statement st=MyConnection.conn.createStatement();
         ResultSet rs=st.executeQuery(Query);
         rs.next();
         m=rs.getInt(1);
         arr1=new Object[m][2];
         String Query1="select group_id ,count(distinct(iid)) from instructor_group where group_id is not null group by(group_id)";
         Statement st1=MyConnection.conn.createStatement();
         ResultSet rs1=st1.executeQuery(Query1);
         rs1.next();
         for(int i=0;i<m;i++)
         {
            va= rs1.getInt(1);
            ab=rs1.getInt(2);
            arr1[i][0]=va;
            arr1[i][1]=ab;
            rs1.next();
            
         }
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
    }
}
class SubPanel15 extends JPanel implements ActionListener 
{
    JButton jb;
    JLabel grouptext;
    JTextField gt;
    SubPanel11 sp11;
    SubPanel12 sp12;
    SubPanel13 sp13;
    SubPanel14 sp14;
    MyJpanel mj;
    Container c;
  //  public Connection conn1=null,conn2=null;
    public static int groupnumber=1;
   SubPanel15(Container c,SubPanel11 sp11,SubPanel12 sp12,SubPanel13 sp13,SubPanel14 sp14,MyJpanel mj)
   {
       this.mj=mj;
       this.c=c;
       this.sp11=sp11;
       this.sp12=sp12;
       this.sp13=sp13;
       this.sp14=sp14;
       setLayout(null);
       Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
    //  this.setBackground(Color.black);
      this.setBounds(d.width*13/16,d.height/14+d.height*3/4,3*d.width/16,d.height);
      jb=new JButton("SUBMIT");
      grouptext=new JLabel("GROUP NUMBER");
      grouptext.setForeground(Color.red);
      grouptext.setBounds(0,d.height/108,(int)(d.width/19.2),(int)(d.height/30.8));
      Font f=new Font("Arial",Font.PLAIN,15);
      gt=new JTextField(5);
      gt.setFont(f);
      gt.setBounds((int)(d.width/17.45),d.height/108,(int)(d.width/38.4),(int)(d.height/36));
      jb.setBounds(0,(int)(d.height/18),(int)(d.width/19.6),d.height/36);
     jb.addMouseListener(mj);
      add(jb);
      add(grouptext);
      add(gt);
   }
  public void actionPerformed(ActionEvent e)
  {
      
  }
}
class SubPanel2 extends JPanel implements ActionListener
{
   
       JTextField jtf[]=new JTextField[7];
       JLabel jl[]=new JLabel[3];
       JButton jb,jb1,jb2;
       //conn=null;
       JList jlst1,jlst2;
       JScrollPane jsp1,jsp2;
       String str1[]={""},str2[]={""};
    SubPanel2()
    {
        
        setLayout(null);
        jl[0]=new JLabel("GROUP NUMBER");
        jl[1]=new JLabel("STUDENTS IDS");
        jl[2]=new JLabel("INSTRUCTOR ID");
         jb=new JButton("SUBMIT");
         jb1=new JButton("REMOVE STUDENTS");
         jb2=new JButton("REMOVE INSTRUCTORS");
         jlst1=new JList(str1);
         jlst2=new JList(str2);
         jsp1=new JScrollPane(jlst1);
         jsp2=new JScrollPane(jlst2);
        Font f=new Font("Arial",Font.BOLD,15);
        for(int i=0;i<=2;i++)
        {
            jl[i].setFont(f);
            jl[i].setForeground(Color.blue);
            
        }
        for(int i=0;i<=6;i++)
        {
         jtf[i]=new JTextField(5);
         jtf[i].setEditable(false);
         jtf[i].setFont(f);
         jtf[i].setForeground(Color.blue);
        }
        jtf[0].setEditable(true);
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
      jl[0].setBounds((int)(d.width/19.2),(int)(d.height/21.6),(int)(d.width/6.4),(int)(d.height/36));
      jtf[0].setBounds((int)(d.width/4.8),(int)(d.height/21.6),(int)(d.width/12.8),(int)(d.height/36));
      jb.setBounds((int)(d.width/3.43),(int)(d.height/21.6),(int)(d.width/19.2),(int)(d.height/36));
      jl[1].setBounds((int)(d.width/19.2),(int)(d.height/7.2),(int)(d.width/6.4),(int)(d.height/36));
      jsp1.setBounds((int)(d.width/4.8),(int)(d.height/7.2),(int)(d.width/7.5),(int)(d.height/4));
      jsp2.setBounds((int)(d.width/4.8),(int)(d.height/2),(int)(d.width/7.5),(int)(d.height/4));
      jb1.setBounds((int)(d.width/4.3),(int)(d.height/2.5),(int)(d.width/12),(int)(d.height/36));
      jb2.setBounds((int)(d.width/4.4),(int)(d.height/1.3),(int)(d.width/11),(int)(d.height/36));
      //jb2.setBounds((int)(d.width/4),(int)(d.height/2),(int)(d.width/19.2),(int)(d.height/36));
    //  jtf[1].setBounds((int)(d.width/4.8),(int)(d.height/7.2),(int)(d.width/12.8),(int)(d.height/36));
    //  jtf[2].setBounds((int)(d.width/4.8),(int)(d.height/6),(int)(d.width/12.8),(int)(d.height/36));
    //  jtf[3].setBounds((int)(d.width/4.8),(int)(d.height/5.14),(int)(d.width/12.8),(int)(d.height/36));
    //  jtf[4].setBounds((int)(d.width/4.8),(int)(d.height/4.5),(int)(d.width/12.8),(int)(d.height/36));
    //  jtf[5].setBounds((int)(d.width/4.8),(int)(d.height/4),(int)(d.width/12.8),(int)(d.height/36));
      jl[2].setBounds((int)(d.width/19.2),(int)(d.height/2),(int)(d.width/7.68),(int)(d.height/36));
      //jtf[6].setBounds((int)(d.width/4.8),(int)(d.height/2.92),(int)(d.width/12.8),(int)(d.height/36));
     // jl[1].setBounds(100,60,300,30);
      
      jb.addActionListener(this);
      jb1.addActionListener(this);
      jb2.addActionListener(this);
      add(jl[0]);
      add(jl[1]);
      add(jb);
      add(jtf[0]);
      add(jsp1);
      add(jb1);
      add(jb2);
      add(jsp2);     
// add(jtf[1]);
      //add(jtf[2]);
      //add(jtf[3]);
    //  add(jtf[4]);
    //  add(jtf[5]);
      add(jl[2]);
   //   add(jtf[6]);
      //this.setBounds(d.width/4,d.height/14,3*d.width/16,d.height);
      this.setBounds(d.width/4,d.height/14,3*d.width/4,d.height);
     // this.setBackground(Color.black);    
    }
    public void actionPerformed(ActionEvent e)
    {
       if(e.getActionCommand().equals("SUBMIT"))
       {
           if(!jtf[0].getText().equals(""))
           {
       //   connect1();
          getIds1();
         // connect2();
          getIds2();
           }
       }
       if(e.getActionCommand().equals("REMOVE STUDENTS"))
       {
          int selected[]=jlst1.getSelectedIndices();
       //   connect1();
          for(int i=0;i<selected.length;i++)
          {
            String element =(String)jlst1.getModel().getElementAt(selected[i]);
            String query="delete from student_group where group_id=? and sid=?;";
            try
            {
               PreparedStatement pst=MyConnection.conn.prepareStatement(query);
               pst.setInt(1,Integer.parseInt(jtf[0].getText()));
               pst.setInt(2,Integer.parseInt(element.substring(3)));
               pst.executeUpdate();
              
            }
            catch(Exception ex)
            {
              System.out.println(ex);
            }
          }
           getIds1();
          
       }
       if(e.getActionCommand().equals("REMOVE INSTRUCTORS"))
       {
         
          int selected[]=jlst2.getSelectedIndices();
         // connect2();
          for(int i=0;i<selected.length;i++)
          {
            String element =(String)jlst2.getModel().getElementAt(selected[i]);
            String query="delete from instructor_group where group_id=? and iid=?;";
            try
            {
               PreparedStatement pst=MyConnection.conn.prepareStatement(query);
               pst.setInt(1,Integer.parseInt(jtf[0].getText()));
               pst.setInt(2,Integer.parseInt(element.substring(4)));
               pst.executeUpdate();
               
            }
            catch(Exception ex)
            {
              System.out.println(ex);
            }
          }
          getIds2();
       }
       
    }
   
     public void getIds1()
     { 
        int i;
        Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
       Font f=new Font("Arial",Font.BOLD,15);
         try
         { 
          String Q="select count(distinct(sid)) from student_group where group_id="+Integer.parseInt(jtf[0].getText())+";";
          Statement st=MyConnection.conn.createStatement();
          ResultSet rs=st.executeQuery(Q);
          rs.next();
          i=rs.getInt(1);
          String Query="select distinct(sid) from student_group where group_id="+Integer.parseInt(jtf[0].getText())+";";
          Statement st1=MyConnection.conn.createStatement();
         ResultSet rs1=st1.executeQuery(Query);
         rs1.next();
         str1=new String[i];
         for(int j=0;j<i;j++)
          {
              int k= rs1.getInt(1);
             
              str1[j]="STD"+k;
             rs1.next();
          }
          jsp1.setVisible(false);
          jlst1=new JList(str1);
          jlst1.setFont(f);
          jlst1.setForeground(Color.BLUE);
          jsp1=new JScrollPane(jlst1);
          jsp1.setBounds((int)(d.width/4.8),(int)(d.height/7.2),(int)(d.width/7.5),(int)(d.height/4));
          add(jsp1);
          MultiplePanels.m.setVisible(false);
           MultiplePanels.m.setVisible(true);
         }
         catch(Exception e)
         {           
           e.printStackTrace();
         }
         
     }
    
     public void getIds2()
     { 
        int i;
        Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
       Font f=new Font("Arial",Font.BOLD,15);
         try
         { 
          String Q="select count(distinct(iid)) from instructor_group where group_id="+Integer.parseInt(jtf[0].getText())+";";
          Statement st=MyConnection.conn.createStatement();
          ResultSet rs=st.executeQuery(Q);
          rs.next();
          i=rs.getInt(1);
          String Query="select distinct(iid) from instructor_group where group_id="+Integer.parseInt(jtf[0].getText())+";";
          Statement st1=MyConnection.conn.createStatement();
         ResultSet rs1=st1.executeQuery(Query);
         rs1.next();
          str2=new String[i];
         for(int j=0;j<i;j++)
          {
              int k= rs1.getInt(1);
            // jtf[6].setText("INST"+k);
              str2[j]="INST"+k;
             rs1.next();
          }
         jsp2.setVisible(false);
          jlst2=new JList(str2);
          jlst2.setFont(f);
          jlst2.setForeground(Color.BLUE);
          jsp2=new JScrollPane(jlst2);
          jsp2.setBounds((int)(d.width/4.8),(int)(d.height/2),(int)(d.width/7.5),(int)(d.height/4));
          add(jsp2);
          MultiplePanels.m.setVisible(false);
           MultiplePanels.m.setVisible(true);
         }
         catch(Exception e)
         {           
           e.printStackTrace();
         }
         
     }
}
class SubPanel3 extends JPanel implements ActionListener,MouseListener
{
    JTextField jtf[]=new JTextField[16];
    JLabel jl[]=new JLabel[16];
    JButton jb=new JButton("SUBMIT");
  //  Connection conn1=null,conn2=null;
    JLabel jlimage;
    ImageIcon imgic;
    int k;
    SubPanel3()
    {
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
        jtf[0]=new JTextField(15);
        jtf[1]=new JTextField(15);
        jtf[2]=new JTextField(15);
        jtf[3]=new JTextField(15);
        jtf[4]=new JTextField(15);
        jtf[5]=new JTextField(60);
        jtf[6]=new JTextField(15);
        jtf[7]=new JTextField(15);
        jtf[8]=new JTextField(50);
        jtf[9]=new JTextField(50);
        jtf[10]=new JTextField(15);
        jtf[11]=new JTextField(15);
        jtf[12]=new JTextField(15);
        jtf[13]=new JTextField(15);
        jtf[14]=new JTextField(15);
        jtf[15]=new JTextField(18);
        jl[0]=new JLabel("Enter Id");
        jl[1]=new JLabel("First Name");
        jl[2]=new JLabel("Last Name");
        jl[3]=new JLabel("Date of Birth");
        jl[4]=new JLabel("Gender");
        jl[5]=new JLabel("Address");
        jl[6]=new JLabel("Pin Code");
        jl[7]=new JLabel("Phone Number");
        jl[8]=new JLabel("Email ID");
        jl[9]=new JLabel("Institute Name");
        jl[10]=new JLabel("Branch");
        jl[11]=new JLabel("Semester");
        jl[12]=new JLabel("Year");
        jl[13]=new JLabel("Scholar No.");
        jl[14]=new JLabel("Group NO.");
        jl[15]=new JLabel("User Type");
        jlimage=new JLabel("Image Here");
        Font f=new Font("Arial",Font.BOLD,15);
        setLayout(null);
        jl[0].setBounds((int)(d.width/38.4),(int)(d.height/27),(int)(d.width/6.4),(int)(d.height/36));
        jl[0].setForeground(Color.red);
        jlimage.setBounds((int)(d.width/1.6),(int)(d.height/27),(int)(d.width/14),(int)(d.height/7));
        jtf[0].setBounds((int)(d.width/5.12),(int)(d.height/27),(int)(d.width/21.3),(int)(d.height/36));
        jtf[0].setForeground(Color.red);
        jb.setBounds((int)(d.width/4.085),(int)(d.height/27),(int)(d.width/19.2),(int)(d.height/36));
         jl[0].setFont(f);
         jtf[0].setFont(f);
        for(int i=1;i<16;i++)
        {
            k=90+30*i;
           jl[i].setBounds((int)(d.width/38.4),(int)(d.height*k)/(1080),(int)(d.width/6.4),(int)(d.height/36));
           jl[i].setForeground(Color.blue);
           if((i!=5)&&(i!=8)&&(i!=9)&&(i!=15))
            jtf[i].setBounds((int)(d.width/5.12),(int)(d.height*k/1080),(int)(d.width/2.74),(int)(d.height/36));
           else if(i==5)
            jtf[i].setBounds((int)(d.width/5.12),(int)(d.height*k/1080),(int)(d.width/2.74),(int)(d.height/36)); 
            else if(i==8)
            jtf[i].setBounds((int)(d.width/5.12),(int)(d.height*k/1080),(int)(d.width/2.74),(int)(d.height/36)); 
            else if(i==9)
            jtf[i].setBounds((int)(d.width/5.12),(int)(d.height*k/1080),(int)(d.width/2.74),(int)(d.height/36)); 
            else if(i==15)
             jtf[i].setBounds((int)(d.width/5.12),(int)(d.height*k/1080),(int)(d.width/2.74),(int)(d.height/36));   
           
           jtf[i].setForeground(Color.blue);
           jtf[i].setEditable(false);
           jl[i].setFont(f);
           jtf[i].setFont(f);
           add(jl[i]);
           add(jtf[i]);
        }
        add(jl[0]);
        add(jlimage);
        jb.addActionListener(this);     
        add(jb);
        jtf[0].addMouseListener(this);
        add(jtf[0]);
      this.setBounds(d.width/4,d.height/14,3*d.width/4,d.height);
   //   this.setBackground(Color.black);   
         
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("SUBMIT"))
        {
            if(!jtf[0].getText().equals("")){
             jtf[15].setForeground(Color.blue);  
            if(jtf[0].getText().substring(0,3).equals("STD"))
            {
            //  connect1();
              setstu();
            }
            else if(jtf[0].getText().substring(0,4).equals("INST"))
            {
              //connect2();
              setinst();
            }
            else
            {
              jtf[15].setForeground(Color.red);  
              jtf[15].setText("WRONG ID");
            }
            }
        }
    }
   
    
      public void setstu()
      {
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
     int flag=0;
         try
         {
           // System.out.println("hello");
            String Query="select * from student where sid="+Integer.parseInt(jtf[0].getText().substring(3))+";";
            String Query1="select count(distinct(group_id)) from student_group where sid="+Integer.parseInt(jtf[0].getText().substring(3))+";";
            String Query2="select group_id from student_group where sid="+Integer.parseInt(jtf[0].getText().substring(3))+";";
            Statement st=MyConnection.conn.createStatement();
            // System.out.println("vansh");
           ResultSet rs=st.executeQuery(Query);
           rs.next();
        
           
         //  s.login_id=lid;
           String fname=rs.getString(2);
           jtf[1].setText(fname);
           String lname=rs.getString(3);
           jtf[2].setText(lname);
           String dateofbirth=rs.getString(4);
           jtf[3].setText(dateofbirth);
           String sex=rs.getString(5);
           jtf[4].setText(sex);
           String add=rs.getString(6);
           jtf[5].setText(add);
           String pin=rs.getString(7);
           jtf[6].setText(pin);
           String ph=rs.getString(8);
           jtf[7].setText(ph);
           String mail=rs.getString(9);
           jtf[8].setText(mail);
           String insti=rs.getString(10);
           jtf[9].setText(insti);
           String bran=rs.getString(11);
           jtf[10].setText(bran);
           String sem=rs.getString(19);
           jtf[11].setText(sem);
           String yea=rs.getString(12);
           jtf[12].setText(yea);
           String scno=rs.getString(13);
           jtf[13].setText(scno);
            byte[] imagedata=rs.getBytes(18);
            jlimage.setVisible(false);
            imgic =new ImageIcon(imagedata);
            jlimage=new JLabel();
            jlimage.setIcon(imgic);
             jlimage.setBounds((int)(d.width/1.6),(int)(d.height/27),(int)(d.width/14),(int)(d.height/7));
             add(jlimage);
          // int i =rs.getInt(19);
          
           jtf[15].setText("STUDENT");
           jl[10].setText("Branch");
           Statement st1=MyConnection.conn.createStatement();
           int temp=0;
           String conc="";
           try
           {
             ResultSet rs1=st1.executeQuery(Query1);
             rs1.next();
             temp=rs1.getInt(1);
           }
           catch(Exception e)
           {
                  
           }
           try
           {
             Statement st2=MyConnection.conn.createStatement();
            ResultSet rs2=st2.executeQuery(Query2);             
             for(int i=0;i<temp;i++)
             {
                 rs2.next();
                conc=conc+"G"+rs2.getInt(1);
                conc=conc+",";
                flag=1;
             }
             if(flag==0)
             {
                conc="NO GROUP ALLOTED ";
             }
             
           }
           catch(Exception e)
           {
              conc="NO GROUP ALLOTED ";
           }
           String str=conc.substring(0,conc.length()-1);
           jtf[14].setText(str);
        }
         catch(Exception e)
         {
           jtf[15].setText("Doesn't Exist");
            jtf[15].setForeground(Color.red);
             for(int i=1;i<=14;i++)
                jtf[i].setText("");
           
         } 
           
      }
      public void setinst()
      {
           Toolkit tk=Toolkit.getDefaultToolkit();
           Dimension d=new Dimension();
            d=tk.getScreenSize();  
            int flag=0;
             try
         {
            
        String Query="select * from instructor where iid="+Integer.parseInt(jtf[0].getText().substring(4))+";";
        String Query1="select count(distinct(group_id)) from instructor_group where iid="+Integer.parseInt(jtf[0].getText().substring(4))+";";
        String Query2="select group_id from instructor_group where iid="+Integer.parseInt(jtf[0].getText().substring(4))+";";
        Statement st=MyConnection.conn.createStatement();
        ResultSet rs=st.executeQuery(Query);
        rs.next();
         //  s.login_id=lid;
           String fname=rs.getString(2);
           jtf[1].setText(fname);
           String lname=rs.getString(3);
           jtf[2].setText(lname);
           String dateofbirth=rs.getString(4);
           jtf[3].setText(dateofbirth);
           String sex=rs.getString(5);
           jtf[4].setText(sex);
           String add=rs.getString(6);
           jtf[5].setText(add);
           String pin=rs.getString(7);
           jtf[6].setText(pin);
           String ph=rs.getString(8);
           jtf[7].setText(ph);
           String mail=rs.getString(9);
           jtf[8].setText(mail);
           String insti=rs.getString(10);
           jtf[9].setText(insti);
           String bran=rs.getString(11);
           jtf[10].setText(bran);
         //  String sem=rs.getString(12);
           jtf[11].setText("NIL");
        //   String yea=rs.getString(13);
           jtf[12].setText("NIL");
           String scno=rs.getString(12);
           jtf[13].setText(scno);
           //int i =rs.getInt(17);
           //jtf[14].setText(""+i);
           jtf[15].setText("INSTRUCTOR");
           jl[10].setText("Department");
           byte[] imagedata=rs.getBytes(16);
            jlimage.setVisible(false);
            imgic =new ImageIcon(imagedata);
            jlimage=new JLabel();
            jlimage.setIcon(imgic);
             jlimage.setBounds((int)(d.width/1.6),(int)(d.height/27),(int)(d.width/14),(int)(d.height/7));
             add(jlimage);
          // int i =rs.getInt(19);
             int temp=0;
             Statement st1=MyConnection.conn.createStatement();
            try
            {
             ResultSet rs1=st1.executeQuery(Query1);
             rs1.next();
             temp=rs1.getInt(1);
            }
            catch(Exception e)
            {
              System.out.println(e);
              
            }
              String conc="";
              Statement st2=MyConnection.conn.createStatement();
             try{
             
              ResultSet rs2=st2.executeQuery(Query2);
                 for(int i=0;i<temp;i++)
                {
                   rs2.next();
                   flag=1;
                   conc=conc+"G"+rs2.getInt(1);
                   conc=conc+",";
                }
                 if(flag==0)
                 {
                    conc="NO GROUP ALLOTED ";
                 }
             }
             catch(Exception e)
             {
                 System.out.println("exception");
                
             }
            
             
             jtf[14].setText(conc);
            
        }
         catch(Exception e)
         {
           jtf[15].setText("Doesn't Exist");
            jtf[15].setForeground(Color.red);
            for(int i=1;i<=14;i++)
                jtf[i].setText("");
         } 
      }
      public void mouseClicked(MouseEvent e)
      {
         jtf[0].setText("");
      }
      public void mouseEntered(MouseEvent e)
      {
      
      }
      public void mouseExited( MouseEvent e)

      {
      
      }
      public void mousePressed(MouseEvent e)
      {
      
      }
      public void mouseReleased(MouseEvent e)
      {
      
      }
}
class MyJpanel3 extends JPanel
{
   MyJpanel3()
   {
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
      this.setBounds(d.width/4,d.height/14,d.width,d.height);
      this.setBackground(Color.black);
      
   }
}
class MyJpanel5 extends JPanel
{
   MyJpanel5()
   {
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension d=new Dimension();
      d=tk.getScreenSize();
      this.setBounds(d.width/4,d.height/14,d.width,d.height);
      this.setBackground(Color.black);
      
   }
}
class DummyPanel11 extends JPanel
{ 
     public DummyPanel11()
      {
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension d=new Dimension();
        d=tk.getScreenSize();
        this.setBounds(d.width/4,d.height/14,(d.width*3)/16,d.height);
       // this.setBackground(Color.black); 
      }
}
class DummyPanel12 extends JPanel
{
     public DummyPanel12()
      {
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension d=new Dimension();
        d=tk.getScreenSize();      
        this.setBounds((d.width*7)/16,d.height/14,(d.width*3)/16,d.height);
        //this.setBackground(Color.black);
      }
}
class DummyPanel13 extends JPanel
{
     public DummyPanel13()
      {
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension d=new Dimension();
        d=tk.getScreenSize();
        this.setBounds((5*d.width)/8,d.height/14,(d.width*3)/16,d.height);
       // this.setBackground(Color.black);
      }
}
class DummyPanel14 extends JPanel
{
     public DummyPanel14()
      {
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension d=new Dimension();
        d=tk.getScreenSize();
        this.setBounds(13*d.width/16,d.height/14,(d.width*3)/16,d.height*3/4);
       // this.setBackground(Color.black);
      }
}
class DummyPanel15 extends JPanel
{
     public DummyPanel15()
      {
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension d=new Dimension();
        d=tk.getScreenSize();
        this.setBounds(d.width*13/16,d.height/14+d.height*3/4,3*d.width/16,d.height);
       // this.setBackground(Color.black);
      }
}

public class MultiplePanels
{
    public static MyJframe m;
    public MultiplePanels()
    {
        MyConnection obj=new MyConnection();
        m=new MyJframe("ADMIN LOGIN");
    }    
}
