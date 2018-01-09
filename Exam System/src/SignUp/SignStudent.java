package SignUp;

import java.awt.Color;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import javax.swing.*;
import java.util.Scanner;
class MyFrame extends JFrame
{
	public static JFrame frame;
   MyFrame(int choice)
   {
	   frame = this;
	   frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	   frame.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension scr= tk.getScreenSize();
      setBounds(0,0,scr.width,scr.height);
       Container ct= this.getContentPane();
 
//        System.out.println("Press: 1 for Student Signup and Press: 2 for Instructor Signup");
       // Scanner kb=new Scanner(System.in);
        
        if(choice==1)
        {
          MyJpanel mjp =new MyJpanel(ct);
          ct.add(mjp);
          this.setVisible(true);
        }
        else
        {
          Signinst si=new Signinst(ct);
          ct.add(si);
          this.setVisible(true);
        }
      
      
   }
}
class MyJpanel extends JPanel implements MouseListener,ActionListener,ItemListener,KeyListener//ash
{
   public JLabel []jl=new JLabel[21];
   public  JTextField jtf[]=new JTextField[10];
   public JRadioButton jr1,jr2,jr3;
   public ButtonGroup bg;
   public String s1="Male",s2="Female",s3="Others";
   public JComboBox jcb1,jcb2,jcb3;
   public JCheckBox jchb;
   public JButton j,j1,j2;  
   String str1[]={"1","2","3","4","5","6","7","8","9","10"};
   String str2[]={"CSE","ME","CHEM","ECE","EE","MSME","CIVIL","ARCHITECTURE"};
   String str3[]={"FIRST","SECOND","THIRD","FOURTH","FIFTH"};
   public static String firstName,lastName,dob,gender="",address,pinCode,phoneNo,emailId,instituteName,branch,sem,year,scholarNo ;//ashu
   MyJpanel inner;
   int i;
   int x=0;
   int temp=0;
   long size;//ashu
   static int male=0,female=0,others=0;
   Container c;
   Connection conn1;
    public JDesktopPane jdp;
    public JLabel imagelabel; 
    public JButton jb;
    public ImageIcon format=null;
    public  String filename=null;
    int s=0;
   public byte []person_image;
   public static JLabel image1,image2;
   MyJpanel(Container ct)
   {
       c=ct;
       jdp=new JDesktopPane();
     imagelabel=new JLabel("Image Here");
     jb=new JButton("Upload Image");
     image1= new JLabel("Image");   
     
     jb.addActionListener(this);
     
      Toolkit tk=Toolkit.getDefaultToolkit();
      Dimension scr = tk.getScreenSize();
     // this.setBackground(Color.cyan);
      j= new JButton("SUBMIT");
      j1=new JButton("BACK");
      j2=new JButton("CONFIRM");
      jr1= new JRadioButton("");
      jr2= new JRadioButton("");
      jr3= new JRadioButton(""); 
      bg= new ButtonGroup();
      bg.add(jr1);
      bg.add(jr2);
      bg.add(jr3);
      jchb=new JCheckBox();
      jcb1=new JComboBox(str1);
      jcb2=new JComboBox(str2);
      jcb3=new JComboBox(str3);
      jtf[0]= new JTextField(10); 
      jtf[1]=new JTextField(10);
      jtf[2]=new JTextField(10);
      jtf[3]=new JTextField(10);
      jtf[4]=new JTextField(10);
      jtf[5]=new JTextField(10);
      jtf[6]=new JTextField(10);
      jtf[7]=new JTextField(100);
      jtf[8]=new JTextField(100);
      jtf[9]=new JTextField(30);
      Font f2 =new Font ("Arial",Font.PLAIN,15);
       
       Font f3=new Font("Arial",Font.BOLD,15);
      setLayout(null);
    // jr1.setBackground(Color.cyan);
    // jr2.setBackground(Color.cyan);
    // jr3.setBackground(Color.cyan);  
     //jchb.setBackground(Color.CYAN);
      jl[0]=new JLabel("STUDENT SIGN UP");
      jl[1]=new JLabel("First Name");
      jl[2]=new JLabel("Last Name");
      jl[3]=new JLabel("Branch");
      jl[4]=new JLabel("Semester");
      jl[5]=new JLabel("Year");
      jl[6]=new JLabel("Date of Birth");
      jl[7]=new JLabel("Scholar Number");
      jl[8]=new JLabel("Email Id");
      jl[9]=new JLabel("Pin Code");
      jl[10]=new JLabel("Institute Name");
      jl[11]=new JLabel("Gender");
      jl[12]=new JLabel("Phone Number");
      jl[13]=new JLabel("Academic Details  :");
      jl[14]=new JLabel("Personal Details  :");
      jl[15]=new JLabel("Address");
      jl[16]=new JLabel("Other");
      jl[17]=new JLabel("Male");
      jl[18]=new JLabel("Female");
      jl[19]=new JLabel("Others");
      jl[20]=new JLabel("I Agree To Terms And Conditions ");
      Font f=new Font("Arial",Font.BOLD,15);
      Font f1=new Font("Arial",Font.BOLD,15);
  
      for(i=1;i<=20;i++)
      {
         jl[i].setFont(f);
      }
      jl[0].setFont(f3);
      imagelabel.setFont(f);
      image1.setFont(f);
      imagelabel.setForeground(Color.BLUE);
      for(i=0;i<=9;i++)
      {
          jtf[i].setFont(f2);
      }
      for(i=1;i<=20;i++)
      {
      jl[i].setForeground(Color.blue);
      }
      String s="YYYY-MM-DD";
      jtf[2].setForeground(Color.GRAY);
      jtf[2].setText(s);
     // Dimension d=new Dimension();
     // d.height=200;
      //d.width=30;
     jr1.setBounds((int)(scr.width/1.67),(int)(scr.height/6.28),(int)(scr.width/64),(int)(scr.height/27));//
     jr2.setBounds((int)(scr.width/1.51),(int)(scr.height/6.28),(int)(scr.width/64),(int)(scr.height/27));//female
     jr3.setBounds((int)(scr.width/1.36),(int)(scr.height/6.28),(int)(scr.width/64),(int)(scr.height/27));
     jtf[0].setBounds((int)(scr.width/2.6),(int)(scr.height/8.3),(int)(scr.width/9.6),(int)(scr.height/36));    //text field f name
     jl[0].setBounds((int)(scr.width/2.37),(int)(scr.height/54),(int)(scr.width/3.2),(int)(scr.height/21.6));  //sign up
     jl[2].setBounds((int)(scr.width/2),(int)(scr.height/25.1),(int)(scr.width/9.6),(int)(scr.height/5.4));   //last name
     jl[1].setBounds((int)(scr.width/3.37),(int)(scr.height/25.1),(int)(scr.width/9.6),(int)(scr.height/5.4));  //first name
     jl[6].setBounds((int)(scr.width/3.37),(int)(scr.height/12),(int)(scr.width/9.6),(int)(scr.height/5.4));  //dob
     jl[14].setBounds((int)(scr.width/4.57),(int)(scr.height/108),(int)(scr.width/6.4),(int)(scr.height/5.4));  //personal details
     jtf[1].setBounds((int)(scr.width/1.66),(int)(scr.height/8.3),(int)(scr.width/9.6),(int)(scr.height/36));  //text field l name
     jtf[2].setBounds((int)(scr.width/2.6),(int)(scr.height/6.17),(int)(scr.width/9.6),(int)(scr.height/36));//dob t field
     jl[11].setBounds((int)(scr.width/2),(int)(scr.height/12),(int)(scr.width/9.6),(int)(scr.height/5.4));//  gender
     jl[17].setBounds((int)(scr.width/1.61),(int)(scr.height/7.45),(int)(scr.width/21.3),(int)(scr.height/12));//  male
     jl[18].setBounds((int)(scr.width/1.47),(int)(scr.height/7.45),(int)(scr.width/21.3),(int)(scr.height/12));//female
     jl[19].setBounds((int)(scr.width/1.33),(int)(scr.height/7.45),(int)(scr.width/21.3),(int)(scr.height/12));//others
     jl[15].setBounds((int)(scr.width/3.37),(int)(scr.height/8),(int)(scr.width/9.6),(int)(scr.height/5.4));//address
     jtf[7].setBounds((int)(scr.width/2.6),(int)(scr.height/4.9),(int)(scr.width/2.46),(int)(scr.height/36));//address
     jl[9].setBounds((int)(scr.width/3.37),(int)(scr.height/6),(int)(scr.width/9.6),(int)(scr.height/5.4));//pincode
     jtf[3].setBounds((int)(scr.width/2.6),(int)(scr.height/4.07),(int)(scr.width/9.6),(int)(scr.height/36));//pincode
     jl[12].setBounds((int)(scr.width/2),(int)(scr.height/6),(int)(scr.width/9.6),(int)(scr.height/5.4));//contact no.  
     jtf[4].setBounds((int)(scr.width/1.66),(int)(scr.height/4.1),(int)(scr.width/9.6),(int)(scr.height/36));//contact number
     jl[8].setBounds((int)(scr.width/3.37),(int)(scr.height/4.7),(int)(scr.width/9.6),(int)(scr.height/5.4));//emaili id
     jtf[9].setBounds((int)(scr.width/2.6),(int)(scr.height/3.48),(int)(scr.width/4.26),(int)(scr.height/36));//email.id
     jl[13].setBounds((int)(scr.width/4.57),(int)(scr.height/3.86),(int)(scr.width/6.4),(int)(scr.height/5.4));//academic details
     jl[10].setBounds((int)(scr.width/3.37),(int)(scr.height/3.45),(int)(scr.width/9.6),(int)(scr.height/5.4));//institute
     jl[3].setBounds((int)(scr.width/3.37),(int)(scr.height/3.02),(int)(scr.width/9.6),(int)(scr.height/5.4));//branch
     jl[5].setBounds((int)(scr.width/3.37),(int)(scr.height/2.68),(int)(scr.width/9.6),(int)(scr.height/5.4));//year
     jl[4].setBounds((int)(scr.width/2),(int)(scr.height/3.02),(int)(scr.width/9.6),(int)(scr.height/5.4));//semester
     jl[7].setBounds((int)(scr.width/2),(int)(scr.height/2.68),(int)(scr.width/9.6),(int)(scr.height/5.4));//scholar number
     jtf[5].setBounds((int)(scr.width/1.66),(int)(scr.height/2.2),(int)(scr.width/9.6),(int)(scr.height/36));//scholar number
     jtf[8].setBounds((int)(scr.width/2.6),(int)(scr.height/2.7),(int)(scr.width/2.46),(int)(scr.height/36));//institute name
     jcb1.setBounds((int)(scr.width/1.66),(int)(scr.height/2.43),(int)(scr.width/9.6),(int)(scr.height/36));//semester
     jcb2.setBounds((int)(scr.width/2.6),(int)(scr.height/2.43),(int)(scr.width/9.6),(int)(scr.height/36));//branch
     jcb3.setBounds((int)(scr.width/2.6),(int)(scr.height/2.2),(int)(scr.width/9.6),(int)(scr.height/36));//year
     jchb.setBounds((int)(scr.width/2.4),(int)(scr.height/1.74),(int)(scr.width/96),(int)(scr.height/54));// checkbox
     jl[20].setBounds((int)(scr.width/2.3),(int)(scr.height/1.75),(int)(scr.width/3.84),(int)(scr.height/36));//terms and conditions
     j.setBounds((int)(scr.width/2.18),(int)(scr.height/1.59),(int)(scr.width/9.6),(int)(scr.height/21.6));//j button submit
     j2.setBounds((int)(scr.width/2.18),(int)(scr.height/1.86),(int)(scr.width/9.6),(int)(scr.height/21.6));// Confirm button
     j1.setBounds((int)(scr.width/2.18),(int)(scr.height/1.59),(int)(scr.width/9.6),(int)(scr.height/21.6));//j1 back button
      imagelabel.setBounds((int)(scr.width/1.6),(int)(scr.height/3.48),(int)(scr.width/10),(int)(scr.height/36));//image label
     jb.setBounds((int)(scr.width/1.4)+170,(int)(scr.height/3.48)+50,(int)(scr.width/15)+30,(int)(scr.height/33));//upload button
     jdp.setBounds((int)(scr.width/1.19),(int)(scr.height/6),(int)(scr.width/14),(int)(scr.height/7));//Desktop Pane
     image1.setBounds((int)(scr.width/1.17),(int)(scr.height/6),(int)(scr.width/14),(int)(scr.height/7));
     jchb.setEnabled(false);
     //ash
      jtf[0].addKeyListener(this);
      jtf[1].addKeyListener(this);
      jtf[2].addKeyListener(this);
      jtf[3].addKeyListener(this);
      jtf[4].addKeyListener(this);
      jtf[7].addKeyListener(this);
      jtf[8].addKeyListener(this);
     // jtf[2].setEditable(false);
      //ash
     add(image1);
      add(jtf[0]);
      add(jtf[1]);
      add(jtf[2]);
      add(jtf[7]);
      add(jl[0]);
      add(jl[1]);
      add(jl[14]);
      add(jl[2]);
      add(jl[6]);
      add(jl[11]);
      add(jl[17]);
      add(jl[18]);
      add(jl[19]);
      add(jl[15]);
      add(jr1);
      add(jr2);
      add(jr3);
      add(jl[9]);     
      add(jl[12]);
      add(jl[8]);
      add(jtf[9]);
      add(jtf[3]);
      add(jtf[4]);
      add(jl[10]);
      add(jl[13]);
      add(jl[4]);
      add(jl[7]);
      add(jl[3]);
      add(jl[5]);
      add(jtf[5]);
      add(jtf[6]);
      add(jtf[8]);
      add(jcb1);
      add(jcb2);
      add(jcb3);
      add(jchb);
      add(jl[20]);
      add(j);
      add(j1);
      add(j2);
 //     add(imagelabel);
     add(jb);
    // add(jdp);
      j1.setVisible(false);
      j2.setVisible(false);
      jl[20].addMouseListener(this);
    //  SubmitandCheckListener sub=new SubmitandCheckListener();
      j.addActionListener(this);   
      j1.addActionListener(this);
      j2.addActionListener(this);
      jchb.addItemListener(this);
      jcb1.addActionListener(this);
      jcb2.addActionListener(this);
      jcb3.addActionListener(this);
      jr1.addActionListener(this);
      jr2.addActionListener(this);
      jr3.addActionListener(this);
      jtf[2].addMouseListener(this);
   }
   public void mouseClicked(MouseEvent m)
   {
       if(m.getSource()==jl[20])
       {
       int ans=0;
       ans=JOptionPane.showConfirmDialog(null,"1)Use of Mobile Phones is strictly Prohibited\n2)Be aware of Submit Button\n3)All Questions are Compulsory\n4)In academic details use college id no. for scholar number\n5)Correct answer will lead to +4 marks and wrong will lead to -1 marks","Terms And Conditions",JOptionPane.YES_NO_OPTION);       
      if(ans==JOptionPane.YES_OPTION)
      {
         x=1;
        jl[20].setForeground(Color.black);
         jchb.setEnabled(true);
      }
       }
       if(m.getSource()==jtf[2])
       {
         // jtf[2].setText("");
          CalenderProgram cp ;
          cp=new CalenderProgram(jtf[2]);
         System.out.print(cp.date+"      1");
         jtf[2].setText(cp.date);
       }
      
   } 
   
   public void mouseEntered(MouseEvent m)
   {
       if(m.getSource()==jl[20])
       {
       if(x!=1)
       jl[20].setForeground(Color.RED);
       }
   }
   public void mouseExited(MouseEvent m)
   {
       if(m.getSource()==jl[20])
       {
       if(x!=1)
       jl[20].setForeground(Color.BLUE);
       }
   }
   public void mousePressed(MouseEvent m)
   {
   
   }
   public void mouseReleased(MouseEvent m)
   {
   
   }
   public void actionPerformed(ActionEvent e)
  {
     // MyJpanel abhi; 
    //  System.out.println("abhijeet");
      
      if(e.getActionCommand().equals("Upload Image"))
        {
            JFileChooser Chooser=new JFileChooser();
            Chooser.showOpenDialog(null);
            File f=Chooser.getSelectedFile();
            filename=f.getAbsolutePath();
            try
            {
               File image= new File(filename);
                 size=image.length();
                //System.out.println(a);//ashu
               FileInputStream fis=new FileInputStream(image);
               ByteArrayOutputStream bos=new ByteArrayOutputStream();
               byte []buf=new byte[1024];
               for(int readNum;(readNum=fis.read(buf))!=-1;)  
               {
                 bos.write(buf,0,readNum);
               }
               person_image=bos.toByteArray();
               image1.setVisible(false);
               if(image2!=null)
               {
                   image2.setVisible(false);
               }
               image2=new JLabel(new ImageIcon(filename));
               Toolkit tk=Toolkit.getDefaultToolkit();
               Dimension scr = tk.getScreenSize();
               image2.setBounds((int)(scr.width/1.17),(int)(scr.height/6),(int)(scr.width/14),(int)(scr.height/7));
               add(image2);
            }
            catch(Exception exc)
            {
               System.out.println(exc);
               exc.printStackTrace();
            }
        }
   
       if(e.getSource()==jr1)
      {
          male=1;
          female=0;
          others=0;
         
          
      }
       if(e.getSource()==jr2)
      {
          male=0;
          female=1;
          others=0;
      }
        if(e.getSource()==jr3)
      {
          male=0;
          female=0;
          others=1;
      }
      if(e.getActionCommand().equals("SUBMIT"))
      {
            if(temp%2==0)
              {
                JOptionPane.showMessageDialog(null,"Please Check out terms and Conditions","ERROR!",JOptionPane.ERROR_MESSAGE);     
              }
            else
           {
              
           firstName=jtf[0].getText();
           lastName=jtf[1].getText();
           dob=jtf[2].getText();
           if(male==1)
           {
                gender="M";
           }
             if(female==1)
           {
                gender="F";
           }
               if(others==1)
           {
                gender="O";
           }
           address=jtf[7].getText();
           pinCode=jtf[3].getText() ;
           phoneNo=jtf[4].getText();
           emailId=jtf[9].getText();
           instituteName=jtf[8].getText();
           branch=(String)jcb2.getSelectedItem();
           year=(String)jcb3.getSelectedItem();
           sem=(String)jcb1.getSelectedItem();
           scholarNo=jtf[5].getText();
           //ashu
          
           if(size>40000||filename==null||phoneNo.length()!=10||pinCode.length()!=6||firstName.equals("")||lastName.equals("")||dob.equals("")||gender.equals("")||address.equals("")||pinCode.equals("")||phoneNo.equals("")||emailId.equals("")||instituteName.equals("")||scholarNo.equals("")||branch.equals("")||year.equals("")||sem.equals(""))//
           {
                if(firstName.equals("")||lastName.equals("")||dob.equals("")||gender.equals("")||address.equals("")||pinCode.equals("")||phoneNo.equals("")||emailId.equals("")||instituteName.equals("")||scholarNo.equals("")||branch.equals("")||year.equals("")||sem.equals(""))//
            
                {
                    JOptionPane.showMessageDialog(null,"All Fields must be Filled","ERROR!",JOptionPane.ERROR_MESSAGE);
             //ashu
                }
              if(pinCode.length()!=6 )
             {
                JOptionPane.showMessageDialog(null,"Pin Code Must Be Of 6 Digits","ERROR!",JOptionPane.ERROR_MESSAGE);
             }
             if(phoneNo.length()!=10)
             {
                 JOptionPane.showMessageDialog(null,"Phone Number Must Be Of 10 Digits","ERROR!",JOptionPane.ERROR_MESSAGE);
             }
             if(filename==null)
             {
                JOptionPane.showMessageDialog(null,"Please upload the Image","ERROR!",JOptionPane.ERROR_MESSAGE);
             }
              if(size>40000)
             {
                JOptionPane.showMessageDialog(null,"Size should be less than 40 Kb","ERROR!",JOptionPane.ERROR_MESSAGE);
             }
           }
           else
           {
           
          /* if(inner==null)
           {
             System.out.println("abhijeet");
           }*/
           this.setVisible(false);
           //inner=this;
           MyJpanel mjp2=new MyJpanel(c);
          Toolkit tk=Toolkit.getDefaultToolkit();
          Dimension scr = tk.getScreenSize();
           mjp2.jtf[5].setText(scholarNo);
           mjp2.jtf[5].setEditable(false);
            mjp2.jtf[0].setText(firstName);
            mjp2.jtf[0].setEditable(false);
             mjp2.jtf[1].setText(lastName);
             mjp2.jtf[1].setEditable(false);
           mjp2.jtf[2].setText(dob);
           mjp2.jtf[2].setEditable(false);
            mjp2.jtf[7].setText(address);
            mjp2.jtf[7].setEditable(false);
             mjp2.jtf[3].setText(pinCode);
             mjp2.jtf[3].setEditable(false);
            mjp2.jtf[4].setText(phoneNo);
            mjp2.jtf[4].setEditable(false);
             mjp2.jtf[9].setText(emailId);
             mjp2.jtf[9].setEditable(false);
              mjp2.jtf[8].setText(instituteName);
              mjp2.jtf[8].setEditable(false);
              mjp2.jcb1.setSelectedItem(sem);
              //mjp2.jcb1.setEditable(false);
              mjp2.jcb2.setSelectedItem(branch);
              //mjp2.jcb2.setEditable(false);
              mjp2.jcb3.setSelectedItem(year);
              //mjp2.jcb3.setEditable(false);
              mjp2.jl[20].setVisible(false);
              mjp2.jchb.setVisible(false);
              mjp2.remove(jchb);
              mjp2.j.setVisible(false);
              mjp2.j2.setVisible(true);
              mjp2.j1.setVisible(true);
              mjp2.jb.setVisible(false);
              mjp2.imagelabel.setVisible(false);
             // mjp2.image2=new JLabel();
            //ashu
              if(image2!=null)
              {
              mjp2.image2.setBounds((int)(scr.width/1.17),(int)(scr.height/6),(int)(scr.width/14),(int)(scr.height/7));
               mjp2.add(image2);
              }
             // mjp2.image2.setIcon(new ImageIcon(filename));
             
              mjp2.image1.setVisible(false);
              mjp2.person_image=person_image;
             if(male==1)
             {
                mjp2.jr1.setSelected(true);
             }
             if(female==1)
             {
                mjp2.jr2.setSelected(true);
             }
             if(others==1)
             {
                mjp2.jr3.setSelected(true);
             }
             
           c.add(mjp2);
           }
          
           
     }
     
    }
      if(e.getActionCommand().equals("BACK"))
      {
         
         
           this.jtf[5].setEditable(true);
            this.jtf[0].setEditable(true);
             this.jtf[1].setEditable(true);
           this.jtf[2].setEditable(true);
            this.jtf[7].setEditable(true);
             this.jtf[3].setEditable(true);
            this.jtf[4].setEditable(true);
             this.jtf[9].setEditable(true);
              this.jtf[8].setEditable(true);
              this.jcb1.getSelectedItem();
              this.jcb1.setEditable(true);
              this.jcb2.setSelectedItem(branch);
              this.jcb2.setEditable(true);
              this.jcb3.setSelectedItem(year);
              this.jcb3.setEditable(true);
              this.jl[20].setVisible(true);
              this.jchb.setVisible(true);
              this.add(jcb1);
              this.add(jcb2);
              this.add(jcb3);
              this.add(jchb);
              
             //1 this.j.setBounds(880,680,200,50);
            this.j.setVisible(true);  
            this.j1.setVisible(false);
            this.j2.setVisible(false);
       //ashu 
            if(image2!=null)
            {
            this.image2.setVisible(true);
            }
            this.image1.setVisible(false);
            //this.image1.setText(null);
            
             // this.j.setText("SUBMIT");
             if(male==1)
             {
                this.jr1.setSelected(true);
             }
             if(female==1)
             {
                this.jr2.setSelected(true);
             }
             if(others==1)
             {
                this.jr3.setSelected(true);
             } 
             this.imagelabel.setVisible(true);
             this.jb.setVisible(true);
      }
      if(e.getActionCommand().equals("CONFIRM"))
      {
          Student s=new Student();
          s.first_name=firstName;
          s.last_name=lastName;
          s.dob=dob;
          s.gender=gender;
          s.address=address;
          s.pincode=pinCode;
          s.email=emailId;
          s.institute_name=instituteName;
          s.branch=branch;
          s.year=year;
          s.semester=sem;
          s.scholar_no=scholarNo;
          s.phonenumber=phoneNo;
          s.image=this.person_image;
          UseStudent u=new UseStudent();
          u.connect();
          u.addStudent(s);
          this.setVisible(false);
          PassStudent p=new PassStudent();
          c.add(p);
      }
   
  }
   public void itemStateChanged(ItemEvent it)
    {
        temp++;
    }
   //ash
   public void keyTyped(KeyEvent k)
   { 
     
   }
   public void keyPressed(KeyEvent k)
   {
     
   }
   public void keyReleased(KeyEvent k)
   {
       if(k.getSource()==jtf[0])
       {
     if(k.getKeyChar()=='0'||k.getKeyChar()=='1'||k.getKeyChar()=='2'||k.getKeyChar()=='3'||k.getKeyChar()=='4'||k.getKeyChar()=='5'||k.getKeyChar()=='6'||k.getKeyChar()=='7'||
k.getKeyChar()=='8'||k.getKeyChar()=='9'||k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar
()=='%'||k.getKeyChar()=='^'||k.getKeyChar()=='&'||k.getKeyChar()=='*'||k.getKeyChar()=='('||k.getKeyChar()==')'||k.getKeyChar()=='_'||k.getKeyChar()=='-'||k.getKeyChar()=='+'||
k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==':'||k.getKeyChar()==';'||k.getKeyChar()=='"'||k.getKeyChar
()=='<'||k.getKeyChar()=='>'||k.getKeyChar()==','||k.getKeyChar()=='.'||k.getKeyChar()=='?'||k.getKeyChar()=='/'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
     { 
         String str=jtf[0].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[0].setText(str21);
     }
       }
       if(k.getSource()==jtf[1])
       {
     if(k.getKeyChar()=='0'||k.getKeyChar()=='1'||k.getKeyChar()=='2'||k.getKeyChar()=='3'||k.getKeyChar()=='4'||k.getKeyChar()=='5'||k.getKeyChar()=='6'||k.getKeyChar()=='7'||
k.getKeyChar()=='8'||k.getKeyChar()=='9'||k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar
()=='%'||k.getKeyChar()=='^'||k.getKeyChar()=='&'||k.getKeyChar()=='*'||k.getKeyChar()=='('||k.getKeyChar()==')'||k.getKeyChar()=='_'||k.getKeyChar()=='-'||k.getKeyChar()=='+'||
k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==':'||k.getKeyChar()==';'||k.getKeyChar()=='"'||k.getKeyChar
()=='<'||k.getKeyChar()=='>'||k.getKeyChar()==','||k.getKeyChar()=='.'||k.getKeyChar()=='?'||k.getKeyChar()=='/'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
     { 
         String str=jtf[1].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[1].setText(str21);
     }
       }
       if(k.getSource()==jtf[7])
       {
          if(k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar()=='%'||k.getKeyChar()=='^'||k.getKeyChar
()=='*'||k.getKeyChar()=='_'||k.getKeyChar()=='+'||k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==';'||
k.getKeyChar()=='<'||k.getKeyChar()=='>'||k.getKeyChar()=='?'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
       {
   
   
       String str=jtf[7].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[7].setText(str21);
     }

       }
       if(k.getSource()==jtf[8])
       {
        if(k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar()=='%'||k.getKeyChar()=='^'||k.getKeyChar
()=='*'||k.getKeyChar()=='_'||k.getKeyChar()=='+'||k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==':'||
k.getKeyChar()==';'||k.getKeyChar()=='"'||k.getKeyChar()=='<'||k.getKeyChar()=='>'||k.getKeyChar()=='?'||k.getKeyChar()=='/'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
       {
       String str=jtf[8].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[8].setText(str21);    
       }
           
       }
       if(k.getSource()==jtf[3])
       {
           String str4=jtf[3].getText();
       if(str4.length()<7)
       {
    if(k.getKeyChar()==' '||k.getKeyChar()=='a'||k.getKeyChar()=='b'||k.getKeyChar()=='c'||k.getKeyChar()=='d'||k.getKeyChar()=='e'||k.getKeyChar()=='f'||k.getKeyChar()=='g'||k.getKeyChar
()=='h'||k.getKeyChar()=='i'||k.getKeyChar()=='j'||k.getKeyChar()=='k'||k.getKeyChar()=='l'||k.getKeyChar()=='m'||k.getKeyChar()=='n'||k.getKeyChar()=='o'||k.getKeyChar()=='p'||
k.getKeyChar()=='q'||k.getKeyChar()=='r'||k.getKeyChar()=='s'||k.getKeyChar()=='t'||k.getKeyChar()=='u'||k.getKeyChar()=='v'||k.getKeyChar()=='w'||k.getKeyChar()=='x'||k.getKeyChar
()=='y'||k.getKeyChar()=='z'||k.getKeyChar()=='A'||k.getKeyChar()=='B'||k.getKeyChar()=='C'||k.getKeyChar()=='D'||k.getKeyChar()=='E'||k.getKeyChar()=='F'||k.getKeyChar()=='G'||
k.getKeyChar()=='H'||k.getKeyChar()=='I'||k.getKeyChar()=='J'||k.getKeyChar()=='K'||k.getKeyChar()=='L'||k.getKeyChar()=='M'||k.getKeyChar()=='N'||k.getKeyChar()=='O'||k.getKeyChar
()=='P'||k.getKeyChar()=='Q'||k.getKeyChar()=='R'||k.getKeyChar()=='S'||k.getKeyChar()=='T'||k.getKeyChar()=='U'||k.getKeyChar()=='V'||k.getKeyChar()=='W'||k.getKeyChar()=='X'||
k.getKeyChar()=='Y'||k.getKeyChar()=='Z'||k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar
()=='%'||k.getKeyChar()=='^'||k.getKeyChar()=='&'||k.getKeyChar()=='*'||k.getKeyChar()=='('||k.getKeyChar()==')'||k.getKeyChar()=='_'||k.getKeyChar()=='-'||k.getKeyChar()=='+'||
k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==':'||k.getKeyChar()==';'||k.getKeyChar()=='"'||k.getKeyChar
()=='<'||k.getKeyChar()=='>'||k.getKeyChar()==','||k.getKeyChar()=='.'||k.getKeyChar()=='?'||k.getKeyChar()=='/'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
    { 
        try{
       String str=jtf[3].getText();
     
        String str21=str.substring(0,str.length()-1);
        jtf[3].setText(str21);
        }
        catch(Exception e)
        {
            
        }
              
     }
       }
       else
       {
           String str=jtf[3].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[3].setText(str21);
           
       }
       }
       if(k.getSource()==jtf[4])
       {
            String str4=jtf[4].getText();
       if(str4.length()<11)
       {
    if(k.getKeyChar()==' '||k.getKeyChar()=='a'||k.getKeyChar()=='b'||k.getKeyChar()=='c'||k.getKeyChar()=='d'||k.getKeyChar()=='e'||k.getKeyChar()=='f'||k.getKeyChar()=='g'||k.getKeyChar
()=='h'||k.getKeyChar()=='i'||k.getKeyChar()=='j'||k.getKeyChar()=='k'||k.getKeyChar()=='l'||k.getKeyChar()=='m'||k.getKeyChar()=='n'||k.getKeyChar()=='o'||k.getKeyChar()=='p'||
k.getKeyChar()=='q'||k.getKeyChar()=='r'||k.getKeyChar()=='s'||k.getKeyChar()=='t'||k.getKeyChar()=='u'||k.getKeyChar()=='v'||k.getKeyChar()=='w'||k.getKeyChar()=='x'||k.getKeyChar
()=='y'||k.getKeyChar()=='z'||k.getKeyChar()=='A'||k.getKeyChar()=='B'||k.getKeyChar()=='C'||k.getKeyChar()=='D'||k.getKeyChar()=='E'||k.getKeyChar()=='F'||k.getKeyChar()=='G'||
k.getKeyChar()=='H'||k.getKeyChar()=='I'||k.getKeyChar()=='J'||k.getKeyChar()=='K'||k.getKeyChar()=='L'||k.getKeyChar()=='M'||k.getKeyChar()=='N'||k.getKeyChar()=='O'||k.getKeyChar
()=='P'||k.getKeyChar()=='Q'||k.getKeyChar()=='R'||k.getKeyChar()=='S'||k.getKeyChar()=='T'||k.getKeyChar()=='U'||k.getKeyChar()=='V'||k.getKeyChar()=='W'||k.getKeyChar()=='X'||
k.getKeyChar()=='Y'||k.getKeyChar()=='Z'||k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar
()=='%'||k.getKeyChar()=='^'||k.getKeyChar()=='&'||k.getKeyChar()=='*'||k.getKeyChar()=='('||k.getKeyChar()==')'||k.getKeyChar()=='_'||k.getKeyChar()=='-'||k.getKeyChar()=='+'||
k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==':'||k.getKeyChar()==';'||k.getKeyChar()=='"'||k.getKeyChar
()=='<'||k.getKeyChar()=='>'||k.getKeyChar()==','||k.getKeyChar()=='.'||k.getKeyChar()=='?'||k.getKeyChar()=='/'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
       {
      try{     
       String str=jtf[4].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[4].setText(str21);
      }
      catch(Exception e)
      {
      
      }
     }
       }
       else
       {
           String str=jtf[4].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[4].setText(str21);
           
       }
       }
       
       
  }


 //ashu  
}
class Signinst extends JPanel implements ActionListener,MouseListener,ItemListener,KeyListener
{
     public JLabel []jl=new JLabel[21];
   public JTextField jtf[]=new JTextField[10];
   public JRadioButton jr1,jr2,jr3;
   public ButtonGroup bg;
   public String s1="Male",s2="Female",s3="Others";
   public JComboBox jcb1;
   public JCheckBox jchb;
   public JButton j,j1,j2;
   long size;
   
   String str1[]={"CSE","ME","CHEM","ECE","EE","MSME","CIVIL","ARCHITECTURE"};
  
   public static String firstName,lastName,dob,gender="",address,pinCode,phoneNo,emailId,instituteName,department,instructorId ;
    
   int i;
   int x=0;
   int temp=0;
   static  int male=0,female=0,others=0;
   Container c;
    public JDesktopPane jdp;
    public JLabel imagelabel; 
    public JButton jb;
    public ImageIcon format=null;
    public String filename=null;
    int s=0;
   public byte []person_image;
   public static JLabel image2,image1;
   Signinst(Container ct)
   {
       c=ct;
       jdp=new JDesktopPane();
     imagelabel=new JLabel("Image Here");
     jb=new JButton("Upload Image");
     image1= new JLabel("Image");
      jb.addActionListener(this);
      Toolkit tk1=Toolkit.getDefaultToolkit();
      Dimension scr= tk1.getScreenSize();
      j= new JButton("SUBMIT");
      j1=new JButton("BACK");
      j2=new JButton("CONFIRM");
      jr1= new JRadioButton("");
      jr2= new JRadioButton("");
      jr3= new JRadioButton("");
      bg= new ButtonGroup();
      bg.add(jr1);
      bg.add(jr2);
      bg.add(jr3);
      jchb=new JCheckBox();
      jcb1=new JComboBox(str1);      
      jtf[0]= new JTextField(10); 
      jtf[1]=new JTextField(10);
      jtf[2]=new JTextField(10);
      jtf[3]=new JTextField(10);
      jtf[4]=new JTextField(10);
      jtf[5]=new JTextField(10);
      jtf[6]=new JTextField(10);
      jtf[7]=new JTextField(100);
      jtf[8]=new JTextField(100);
      jtf[9]=new JTextField(30);
      Font f2 =new Font ("Arial",Font.PLAIN,15);
       
       Font f3=new Font("Arial",Font.BOLD,15);
      setLayout(null);
      
      jl[0]=new JLabel("INSTRUCTOR SIGN UP");
      jl[1]=new JLabel("First Name");
      jl[2]=new JLabel("Last Name");
      jl[3]=new JLabel("Department");
      jl[4]=new JLabel("");
      jl[5]=new JLabel("");
      jl[6]=new JLabel("Date of Birth");
      jl[7]=new JLabel("Instructor ID");
      jl[8]=new JLabel("Email Id");
      jl[9]=new JLabel("Pin Code");
      jl[10]=new JLabel("Institute Name");
      jl[11]=new JLabel("Gender");
      jl[12]=new JLabel("Phone Number");
      jl[13]=new JLabel("Professional Details  :");
      jl[14]=new JLabel("Personal Details  :");
      jl[15]=new JLabel("Address");
      jl[16]=new JLabel("Other");
      jl[17]=new JLabel("Male");
      jl[18]=new JLabel("Female");
      jl[19]=new JLabel("Others");
      jl[20]=new JLabel("I Agree To Terms And Conditions ");
      Font f=new Font("Arial",Font.BOLD,15);
      Font f1=new Font("Arial",Font.BOLD,15);
      image1.setFont(f);
      image1.setForeground(Color.black);
      imagelabel.setFont(f);
      imagelabel.setForeground(Color.blue);
      for(i=1;i<=20;i++)
      {
         jl[i].setFont(f);
      }
      jl[0].setFont(f3);
      for(i=0;i<=9;i++)
      {
          jtf[i].setFont(f2);
      }
      for(i=1;i<=20;i++)
      {
      jl[i].setForeground(Color.blue);
      }
      String s="YYYY-MM-DD";
      jtf[2].setForeground(Color.GRAY);
      jtf[2].setText(s);
     // Dimension d=new Dimension();
     // d.height=200;
      //d.width=30;
     jr1.setBounds((int)(scr.width/1.67),(int)(scr.height/6.28),(int)(scr.width/64),(int)(scr.height/27));//male
     jr2.setBounds((int)(scr.width/1.51),(int)(scr.height/6.28),(int)(scr.width/64),(int)(scr.height/27));//female
     jr3.setBounds((int)(scr.width/1.36),(int)(scr.height/6.28),(int)(scr.width/64),(int)(scr.height/27));
     jtf[0].setBounds((int)(scr.width/2.6),(int)(scr.height/8.3),(int)(scr.width/9.6),(int)(scr.height/36));    //text field f name
     jl[0].setBounds((int)(scr.width/2.37),(int)(scr.height/54),(int)(scr.width/3.2),(int)(scr.height/21.6));  //sign up
     jl[2].setBounds((int)(scr.width/2),(int)(scr.height/25.1),(int)(scr.width/9.6),(int)(scr.height/5.4));   //last name
     jl[1].setBounds((int)(scr.width/3.37),(int)(scr.height/25.1),(int)(scr.width/9.6),(int)(scr.height/5.4));  //first name
     jl[6].setBounds((int)(scr.width/3.37),(int)(scr.height/12),(int)(scr.width/9.6),(int)(scr.height/5.4));  //dob
     jl[14].setBounds((int)(scr.width/4.57),(int)(scr.height/108),(int)(scr.width/6.4),(int)(scr.height/5.4));  //personal details
     jtf[1].setBounds((int)(scr.width/1.66),(int)(scr.height/8.3),(int)(scr.width/9.6),(int)(scr.height/36));  //text field l name
     jtf[2].setBounds((int)(scr.width/2.6),(int)(scr.height/6.17),(int)(scr.width/9.6),(int)(scr.height/36));//dob t field
     jl[11].setBounds((int)(scr.width/2),(int)(scr.height/12),(int)(scr.width/9.6),(int)(scr.height/5.4));//  gender
     jl[17].setBounds((int)(scr.width/1.61),(int)(scr.height/7.45),(int)(scr.width/21.3),(int)(scr.height/12));//  male
     jl[18].setBounds((int)(scr.width/1.47),(int)(scr.height/7.45),(int)(scr.width/21.3),(int)(scr.height/12));//female
     jl[19].setBounds((int)(scr.width/1.33),(int)(scr.height/7.45),(int)(scr.width/21.3),(int)(scr.height/12));//others
     jl[15].setBounds((int)(scr.width/3.37),(int)(scr.height/8),(int)(scr.width/9.6),(int)(scr.height/5.4));//address
     jtf[7].setBounds((int)(scr.width/2.6),(int)(scr.height/4.9),(int)(scr.width/2.46),(int)(scr.height/36));//address
     jl[9].setBounds((int)(scr.width/3.37),(int)(scr.height/6),(int)(scr.width/9.6),(int)(scr.height/5.4));//pincode
     jtf[3].setBounds((int)(scr.width/2.6),(int)(scr.height/4.07),(int)(scr.width/9.6),(int)(scr.height/36));//pincode
     jl[12].setBounds((int)(scr.width/2),(int)(scr.height/6),(int)(scr.width/9.6),(int)(scr.height/5.4));//contact no.  
     jtf[4].setBounds((int)(scr.width/1.66),(int)(scr.height/4.1),(int)(scr.width/9.6),(int)(scr.height/36));//contact number
     jl[8].setBounds((int)(scr.width/3.37),(int)(scr.height/4.7),(int)(scr.width/9.6),(int)(scr.height/5.4));//emaili id
     jtf[9].setBounds((int)(scr.width/2.6),(int)(scr.height/3.48),(int)(scr.width/4.26),(int)(scr.height/36));//email.id
     jl[13].setBounds((int)(scr.width/4.57),(int)(scr.height/3.86),(int)(scr.width/6.4),(int)(scr.height/5.4));//academic details
     jl[10].setBounds((int)(scr.width/3.37),(int)(scr.height/3.45),(int)(scr.width/9.6),(int)(scr.height/5.4));//institute
     jl[3].setBounds((int)(scr.width/3.37),(int)(scr.height/3.02),(int)(scr.width/9.6),(int)(scr.height/5.4));//branch
     //jl[5].setBounds((int)(scr.width/3.37),(int)(scr.height/2.68),(int)(scr.width/9.6),(int)(scr.height/5.4));//year
     //jl[4].setBounds((int)(scr.width/2),(int)(scr.height/3.02),(int)(scr.width/9.6),(int)(scr.height/5.4));//semester
     jl[7].setBounds((int)(scr.width/2),(int)(scr.height/3.02),(int)(scr.width/9.6),(int)(scr.height/5.4));//scholar number
     jtf[5].setBounds((int)(scr.width/1.66),(int)(scr.height/2.43),(int)(scr.width/9.6),(int)(scr.height/36));//scholar number
     jtf[8].setBounds((int)(scr.width/2.6),(int)(scr.height/2.7),(int)(scr.width/2.46),(int)(scr.height/36));//institute name
     jcb1.setBounds((int)(scr.width/2.6),(int)(scr.height/2.43),(int)(scr.width/9.6),(int)(scr.height/36));//semester
     //jcb2.setBounds((int)(scr.widt/2.6),(int)(scr.height/2.43),(int)(scr.width/9.6),(int)(scr.height/36));//branch
     //jcb3.setBounds((int)(scr.width/2.6),(int)(scr.height/2.2),(int)(scr.width/9.6),(int)(scr.height/36));//year
     jchb.setBounds((int)(scr.width/2.4),(int)(scr.height/2.08),(int)(scr.width/96),(int)(scr.height/54));// checkbox
     jl[20].setBounds((int)(scr.width/2.3),(int)(scr.height/2.09),(int)(scr.width/3.84),(int)(scr.height/36));//terms and conditions
     j.setBounds((int)(scr.width/2.18),(int)(scr.height/1.86),(int)(scr.width/9.6),(int)(scr.height/21.6));//j button submit
     j2.setBounds((int)(scr.width/2.18),(int)(scr.height/1.86),(int)(scr.width/9.6),(int)(scr.height/21.6));// Confirm button
     j1.setBounds((int)(scr.width/2.18),(int)(scr.height/1.59),(int)(scr.width/9.6),(int)(scr.height/21.6));//j1 back button
     imagelabel.setBounds((int)(scr.width/1.6),(int)(scr.height/3.48),(int)(scr.width/10),(int)(scr.height/36));//image label
     jb.setBounds((int)(scr.width/1.4),(int)(scr.height/3.48),(int)(scr.width/15),(int)(scr.height/33));//upload button
     jdp.setBounds((int)(scr.width/1.19),(int)(scr.height/6),(int)(scr.width/14),(int)(scr.height/7));//Desktop Pane
     image1.setBounds((int)(scr.width/1.17),(int)(scr.height/6),(int)(scr.width/14),(int)(scr.height/7));
     jtf[0].addKeyListener(this);//ashu
     jtf[1].addKeyListener(this);
     jtf[2].addKeyListener(this);
     jtf[3].addKeyListener(this);
     jtf[4].addKeyListener(this);
     jtf[7].addKeyListener(this);
     jtf[8].addKeyListener(this);//ashu
     jchb.setEnabled(false);
      add(jtf[0]);
      add(jtf[1]);
      add(jtf[2]);
      add(jtf[7]);
      add(jl[0]);
      add(jl[1]);
      add(jl[14]);
      add(jl[2]);
      add(jl[6]);
      add(jl[11]);
      add(jl[17]);
      add(jl[18]);
      add(jl[19]);
      add(jl[15]);
      add(jr1);
      add(jr2);
      add(jr3);
      add(jl[9]);     
      add(jl[12]);
      add(jl[8]);
      add(jtf[9]);
      add(jtf[3]);
      add(jtf[4]);
      add(jl[10]);
      add(jl[13]);
     // add(jl[4]);
      add(jl[7]);
      add(jl[3]);
     // add(jl[5]);
      add(jtf[5]);
      add(jtf[6]);
      add(jtf[8]);
      add(jcb1);
     // add(jcb2);
     // add(jcb3);
      add(jchb);
      add(jl[20]);
      add(j);
      add(j1);
      add(j2);
      add(image1);
      add(imagelabel);
      add(jb);
      j1.setVisible(false);
      j2.setVisible(false);
      jl[20].addMouseListener(this);
    //  SubmitandCheckListener sub=new SubmitandCheckListener();
      j.addActionListener(this);     
      j2.addActionListener(this); 
      j1.addActionListener(this); 
      jchb.addItemListener(this);
      jcb1.addActionListener(this);
     // jcb2.addActionListener(this);
     // jcb3.addActionListener(this);
      jr1.addActionListener(this);
      jr2.addActionListener(this);
      jr3.addActionListener(this);
      jtf[2].addMouseListener(this);
   }
   public void mouseClicked(MouseEvent m)
   {
       if(m.getSource()==jl[20])
       {
       int ans=0;
       ans=JOptionPane.showConfirmDialog(null,"1)Enter the Details Carefully\n2Mistakes will not be Entertained","Terms And Conditions",JOptionPane.YES_NO_OPTION);       
      if(ans==JOptionPane.YES_OPTION)
      {
         x=1;
        jl[20].setForeground(Color.black);
         jchb.setEnabled(true);
      }
       }
       if(m.getSource()==jtf[2])
       {
          if(m.getSource()==jtf[2])
       {
         // jtf[2].setText("");
          CalenderProgram cp ;
          cp=new CalenderProgram(jtf[2]);
         System.out.print(cp.date+"      1");
         jtf[2].setText(cp.date);
       }
       }
      
   } 
   
   public void mouseEntered(MouseEvent m)
   {
       if(m.getSource()==jl[20])
       {
       if(x!=1)
       jl[20].setForeground(Color.RED);
       }
       
   }
   public void mouseExited(MouseEvent m)
   {
       if(m.getSource()==jl[20])
       {
       if(x!=1)
       jl[20].setForeground(Color.BLUE);
       }
   }
   public void mousePressed(MouseEvent m)
   {
   
   }
   public void mouseReleased(MouseEvent m)
   {
   
   }
   
   public void actionPerformed(ActionEvent e)
  {
      if(e.getActionCommand().equals("Upload Image"))
        {
            JFileChooser Chooser=new JFileChooser();
            Chooser.showOpenDialog(null);
            File f=Chooser.getSelectedFile();
            filename=f.getAbsolutePath();
            try
            {
               File image= new File(filename);
               FileInputStream fis=new FileInputStream(image);
                size=image.length();//ashu
               ByteArrayOutputStream bos=new ByteArrayOutputStream();
               byte []buf=new byte[1024];
               for(int readNum;(readNum=fis.read(buf))!=-1;)  
               {
                 bos.write(buf,0,readNum);
               }
               person_image=bos.toByteArray();
               image1.setVisible(false);
               if(image2!=null)
               {
                   image2.setVisible(false);
               }
               image2=new JLabel(new ImageIcon(filename));
               Toolkit tk=Toolkit.getDefaultToolkit();
               Dimension scr = tk.getScreenSize();
               image2.setBounds((int)(scr.width/1.17),(int)(scr.height/6),(int)(scr.width/14),(int)(scr.height/7));
               add(image2);
            }
            catch(Exception exc)
            {
               System.out.println(exc);
            }
            
            
        }
       if(e.getSource()==jr1)
      {
          male=1;
          female=0;
          others=0;         
      }
       if(e.getSource()==jr2)
      {
          male=0;
          female=1;
          others=0;
      }
        if(e.getSource()==jr3)
      {
          male=0;
          female=0;
          others=1;
      }
      if(e.getActionCommand().equals("SUBMIT"))
      {
            if(temp%2==0)
            {
               JOptionPane.showMessageDialog(null,"Please Check out terms and Conditions","ERROR!",JOptionPane.ERROR_MESSAGE);     
            }
          else
         {
           firstName=jtf[0].getText();
           lastName=jtf[1].getText();
           dob=jtf[2].getText();
           if(male==1)
           {
                gender="M";
           }
             if(female==1)
           {
                gender="F";
           }
               if(others==1)
           {
                gender="O";
           }
           address=jtf[7].getText();
           pinCode=jtf[3].getText() ;
           phoneNo=jtf[4].getText();
           emailId=jtf[9].getText();
           instituteName=jtf[8].getText();
         //  branch=(String)jcb2.getSelectedItem();
          // year=(String)jcb3.getSelectedItem();
           department=(String)jcb1.getSelectedItem();
           instructorId=jtf[5].getText();
           if(size>40000||filename==null||phoneNo.length()!=10||pinCode.length()!=6||firstName.equals("")||lastName.equals("")||dob.equals("")||gender.equals("")||address.equals("")||pinCode.equals("")||phoneNo.equals("")||emailId.equals("")||instituteName.equals("")||department.equals("")||instructorId.equals(""))
           {
               if(firstName.equals("")||lastName.equals("")||dob.equals("")||gender.equals("")||address.equals("")||pinCode.equals("")||phoneNo.equals("")||emailId.equals("")||instituteName.equals("")||department.equals("")||instructorId.equals(""))
               {
                   JOptionPane.showMessageDialog(null,"All Fields must be Filled","ERROR!",JOptionPane.ERROR_MESSAGE);
               }
                   if(pinCode.length()!=6 )
             {
                JOptionPane.showMessageDialog(null,"Pin Code Must Be Of 6 Digits","ERROR!",JOptionPane.ERROR_MESSAGE);
             }
             if(phoneNo.length()!=10)
             {
                 JOptionPane.showMessageDialog(null,"Phone Number Must Be Of 10 Digits","ERROR!",JOptionPane.ERROR_MESSAGE);
             }
             if(filename==null)
             {
                JOptionPane.showMessageDialog(null,"Please upload the Image","ERROR!",JOptionPane.ERROR_MESSAGE);
             }
              if(size>40000)
             {
                JOptionPane.showMessageDialog(null,"Size should be less than 40 Kb","ERROR!",JOptionPane.ERROR_MESSAGE);
             }
           }
           else
           {
               Toolkit ki=Toolkit.getDefaultToolkit();
               Dimension df=new Dimension();
               df=ki.getScreenSize();
           this.setVisible(false);
           Signinst mjp2=new Signinst(c);
           
           mjp2.jtf[5].setText(instructorId);
           mjp2.jtf[5].setEditable(false);
            mjp2.jtf[0].setText(firstName);
            mjp2.jtf[0].setEditable(false);
             mjp2.jtf[1].setText(lastName);
             mjp2.jtf[1].setEditable(false);
           mjp2.jtf[2].setText(dob);
           mjp2.jtf[2].setEditable(false);
            mjp2.jtf[7].setText(address);
            mjp2.jtf[7].setEditable(false);
             mjp2.jtf[3].setText(pinCode);
             mjp2.jtf[3].setEditable(false);
            mjp2.jtf[4].setText(phoneNo);
            mjp2.jtf[4].setEditable(false);
             mjp2.jtf[9].setText(emailId);
             mjp2.jtf[9].setEditable(false);
              mjp2.jtf[8].setText(instituteName);
              mjp2.jtf[8].setEditable(false);
              mjp2.jcb1.setSelectedItem(department);
               mjp2.imagelabel.setVisible(false);
           //    mjp2.image2.setBounds((int)(scr.width/1.17),(int)(scr.height/6),(int)(scr.width/14),(int)(scr.height/7));
               if(image2!=null)
               {
             mjp2.image2.setBounds((int)(df.width/1.17),(int)(df.height/6),(int)(df.width/14),(int)(df.height/7));
             mjp2.add(image2);
               }
               mjp2.jb.setVisible(false);
               mjp2.image1.setVisible(false);
           //   mjp2.image1.setIcon(new ImageIcon(filename));
               mjp2.person_image=person_image;
     
              mjp2.jl[20].setVisible(false);
              mjp2.jchb.setVisible(false);
              mjp2.remove(jchb);
              mjp2.j.setVisible(false);
              mjp2.j1.setVisible(true);
              mjp2.j2.setVisible(true);
             
             
             // mjp2.j.setBounds(880,580,200,50);
              
             // mjp2.j.setText("CONFIRM");
             if(male==1)
             {
                mjp2.jr1.setSelected(true);
             }
             if(female==1)
             {
                mjp2.jr2.setSelected(true);
             }
             if(others==1)
             {
                mjp2.jr3.setSelected(true);
             }
             
           c.add(mjp2);
           }
           
     }
     
    }
   if(e.getActionCommand().equals("BACK"))
   {
      
      
       
           this.jtf[5].setEditable(true);
                        this.jtf[0].setEditable(true);
                         this.jtf[1].setEditable(true);
                     this.jtf[2].setEditable(true);
                      this.jtf[7].setEditable(true);
                       this.jtf[3].setEditable(true);
                      this.jtf[4].setEditable(true);
                       this.jtf[9].setEditable(true);
                       this.jtf[8].setEditable(true);
             this.jcb1.setSelectedItem(department);
             this.jcb1.setEditable(true);
           //   mjp2.jcb1.setEditable();
            //  mjp2.jcb2.setSelectedItem(branch);
            //  mjp2.jcb2.setEditable(false);
            //  mjp2.jcb3.setSelectedItem(year);
            //  mjp2.jcb3.setEditable(false);
              this.jl[20].setVisible(true);
              this.jchb.setVisible(true);
              this.add(jchb);
              this.j.setVisible(true);
              this.j1.setVisible(false);
              this.j2.setVisible(false);
              this.jb.setVisible(true);
              if(image2!=null)
              {
                        this.image2.setVisible(true);
              }
                        this.image1.setVisible(false);
              this.imagelabel.setVisible(true);
             // mjp2.j.setBounds(880,580,200,50);
              
             // mjp2.j.setText("CONFIRM");
             if(male==1)
             {
                this.jr1.setSelected(true);
             }
             if(female==1)
             {
                this.jr2.setSelected(true);
             }
             if(others==1)
             {
                this.jr3.setSelected(true);
             }
             
   }
   if(e.getActionCommand().equals("CONFIRM"))
   {
          Instructor i=new Instructor();
          i.first_name=firstName;
          i.last_name=lastName;
          i.dob=dob;
          i.gender=gender;
          i.address=address;
          i.pincode=pinCode;
          i.email=emailId;
          i.institute_name=instituteName;
          i.department=department;
          i.instructorId=instructorId;
          i.phonenumber=phoneNo;
          i.image=this.person_image;
          UseInstructor u=new UseInstructor();
          u.connect();
          u.addInstructor(i);
          this.setVisible(false);
          PassInstructor p=new PassInstructor();
          c.add(p);
   
   }
  }
   
   public void itemStateChanged(ItemEvent it)
    {
        temp++;
    }
   //ashu
   public void keyTyped(KeyEvent k)
   { 
     
   }
   public void keyPressed(KeyEvent k)
   {
     
   }
   public void keyReleased(KeyEvent k)
   {
       if(k.getSource()==jtf[0])
       {
     if(k.getKeyChar()=='0'||k.getKeyChar()=='1'||k.getKeyChar()=='2'||k.getKeyChar()=='3'||k.getKeyChar()=='4'||k.getKeyChar()=='5'||k.getKeyChar()=='6'||k.getKeyChar()=='7'||
k.getKeyChar()=='8'||k.getKeyChar()=='9'||k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar
()=='%'||k.getKeyChar()=='^'||k.getKeyChar()=='&'||k.getKeyChar()=='*'||k.getKeyChar()=='('||k.getKeyChar()==')'||k.getKeyChar()=='_'||k.getKeyChar()=='-'||k.getKeyChar()=='+'||
k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==':'||k.getKeyChar()==';'||k.getKeyChar()=='"'||k.getKeyChar
()=='<'||k.getKeyChar()=='>'||k.getKeyChar()==','||k.getKeyChar()=='.'||k.getKeyChar()=='?'||k.getKeyChar()=='/'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
     { 
         String str=jtf[0].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[0].setText(str21);
     }
       }
       if(k.getSource()==jtf[1])
       {
     if(k.getKeyChar()=='0'||k.getKeyChar()=='1'||k.getKeyChar()=='2'||k.getKeyChar()=='3'||k.getKeyChar()=='4'||k.getKeyChar()=='5'||k.getKeyChar()=='6'||k.getKeyChar()=='7'||
k.getKeyChar()=='8'||k.getKeyChar()=='9'||k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar
()=='%'||k.getKeyChar()=='^'||k.getKeyChar()=='&'||k.getKeyChar()=='*'||k.getKeyChar()=='('||k.getKeyChar()==')'||k.getKeyChar()=='_'||k.getKeyChar()=='-'||k.getKeyChar()=='+'||
k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==':'||k.getKeyChar()==';'||k.getKeyChar()=='"'||k.getKeyChar
()=='<'||k.getKeyChar()=='>'||k.getKeyChar()==','||k.getKeyChar()=='.'||k.getKeyChar()=='?'||k.getKeyChar()=='/'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
     { 
         String str=jtf[1].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[1].setText(str21);
     }
       }
       if(k.getSource()==jtf[7])
       {
          if(k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar()=='%'||k.getKeyChar()=='^'||k.getKeyChar
()=='*'||k.getKeyChar()=='_'||k.getKeyChar()=='+'||k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==';'||
k.getKeyChar()=='<'||k.getKeyChar()=='>'||k.getKeyChar()=='?'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
       {
   
   
       String str=jtf[7].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[7].setText(str21);
     }

       }
       if(k.getSource()==jtf[8])
       {
        if(k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar()=='%'||k.getKeyChar()=='^'||k.getKeyChar
()=='*'||k.getKeyChar()=='_'||k.getKeyChar()=='+'||k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==':'||
k.getKeyChar()==';'||k.getKeyChar()=='"'||k.getKeyChar()=='<'||k.getKeyChar()=='>'||k.getKeyChar()=='?'||k.getKeyChar()=='/'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
       {
       String str=jtf[8].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[8].setText(str21);    
       }
           
       }
       if(k.getSource()==jtf[3])
       {
           String str4=jtf[3].getText();
       if(str4.length()<7)
       {
    if(k.getKeyChar()==' '||k.getKeyChar()=='a'||k.getKeyChar()=='b'||k.getKeyChar()=='c'||k.getKeyChar()=='d'||k.getKeyChar()=='e'||k.getKeyChar()=='f'||k.getKeyChar()=='g'||k.getKeyChar
()=='h'||k.getKeyChar()=='i'||k.getKeyChar()=='j'||k.getKeyChar()=='k'||k.getKeyChar()=='l'||k.getKeyChar()=='m'||k.getKeyChar()=='n'||k.getKeyChar()=='o'||k.getKeyChar()=='p'||
k.getKeyChar()=='q'||k.getKeyChar()=='r'||k.getKeyChar()=='s'||k.getKeyChar()=='t'||k.getKeyChar()=='u'||k.getKeyChar()=='v'||k.getKeyChar()=='w'||k.getKeyChar()=='x'||k.getKeyChar
()=='y'||k.getKeyChar()=='z'||k.getKeyChar()=='A'||k.getKeyChar()=='B'||k.getKeyChar()=='C'||k.getKeyChar()=='D'||k.getKeyChar()=='E'||k.getKeyChar()=='F'||k.getKeyChar()=='G'||
k.getKeyChar()=='H'||k.getKeyChar()=='I'||k.getKeyChar()=='J'||k.getKeyChar()=='K'||k.getKeyChar()=='L'||k.getKeyChar()=='M'||k.getKeyChar()=='N'||k.getKeyChar()=='O'||k.getKeyChar
()=='P'||k.getKeyChar()=='Q'||k.getKeyChar()=='R'||k.getKeyChar()=='S'||k.getKeyChar()=='T'||k.getKeyChar()=='U'||k.getKeyChar()=='V'||k.getKeyChar()=='W'||k.getKeyChar()=='X'||
k.getKeyChar()=='Y'||k.getKeyChar()=='Z'||k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar
()=='%'||k.getKeyChar()=='^'||k.getKeyChar()=='&'||k.getKeyChar()=='*'||k.getKeyChar()=='('||k.getKeyChar()==')'||k.getKeyChar()=='_'||k.getKeyChar()=='-'||k.getKeyChar()=='+'||
k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==':'||k.getKeyChar()==';'||k.getKeyChar()=='"'||k.getKeyChar
()=='<'||k.getKeyChar()=='>'||k.getKeyChar()==','||k.getKeyChar()=='.'||k.getKeyChar()=='?'||k.getKeyChar()=='/'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
    { 
        try{
       String str=jtf[3].getText();
     
        String str21=str.substring(0,str.length()-1);
        jtf[3].setText(str21);
        }
        catch(Exception e)
        {
            
        }
              
     }
       }
       else
       {
           String str=jtf[3].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[3].setText(str21);
           
       }
       }
       if(k.getSource()==jtf[4])
       {
            String str4=jtf[4].getText();
       if(str4.length()<11)
       {
    if(k.getKeyChar()==' '||k.getKeyChar()=='a'||k.getKeyChar()=='b'||k.getKeyChar()=='c'||k.getKeyChar()=='d'||k.getKeyChar()=='e'||k.getKeyChar()=='f'||k.getKeyChar()=='g'||k.getKeyChar
()=='h'||k.getKeyChar()=='i'||k.getKeyChar()=='j'||k.getKeyChar()=='k'||k.getKeyChar()=='l'||k.getKeyChar()=='m'||k.getKeyChar()=='n'||k.getKeyChar()=='o'||k.getKeyChar()=='p'||
k.getKeyChar()=='q'||k.getKeyChar()=='r'||k.getKeyChar()=='s'||k.getKeyChar()=='t'||k.getKeyChar()=='u'||k.getKeyChar()=='v'||k.getKeyChar()=='w'||k.getKeyChar()=='x'||k.getKeyChar
()=='y'||k.getKeyChar()=='z'||k.getKeyChar()=='A'||k.getKeyChar()=='B'||k.getKeyChar()=='C'||k.getKeyChar()=='D'||k.getKeyChar()=='E'||k.getKeyChar()=='F'||k.getKeyChar()=='G'||
k.getKeyChar()=='H'||k.getKeyChar()=='I'||k.getKeyChar()=='J'||k.getKeyChar()=='K'||k.getKeyChar()=='L'||k.getKeyChar()=='M'||k.getKeyChar()=='N'||k.getKeyChar()=='O'||k.getKeyChar
()=='P'||k.getKeyChar()=='Q'||k.getKeyChar()=='R'||k.getKeyChar()=='S'||k.getKeyChar()=='T'||k.getKeyChar()=='U'||k.getKeyChar()=='V'||k.getKeyChar()=='W'||k.getKeyChar()=='X'||
k.getKeyChar()=='Y'||k.getKeyChar()=='Z'||k.getKeyChar()=='~'||k.getKeyChar()=='`'||k.getKeyChar()=='!'||k.getKeyChar()=='@'||k.getKeyChar()=='#'||k.getKeyChar()=='$'||k.getKeyChar
()=='%'||k.getKeyChar()=='^'||k.getKeyChar()=='&'||k.getKeyChar()=='*'||k.getKeyChar()=='('||k.getKeyChar()==')'||k.getKeyChar()=='_'||k.getKeyChar()=='-'||k.getKeyChar()=='+'||
k.getKeyChar()=='='||k.getKeyChar()=='{'||k.getKeyChar()=='['||k.getKeyChar()=='}'||k.getKeyChar()==']'||k.getKeyChar()==':'||k.getKeyChar()==';'||k.getKeyChar()=='"'||k.getKeyChar
()=='<'||k.getKeyChar()=='>'||k.getKeyChar()==','||k.getKeyChar()=='.'||k.getKeyChar()=='?'||k.getKeyChar()=='/'||k.getKeyChar()=='\\'||k.getKeyChar()=='|')
       {
      try{     
       String str=jtf[4].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[4].setText(str21);
      }
      catch(Exception e)
      {
      
      }
     }
       }
       else
       {
           String str=jtf[4].getText();
        String str21=str.substring(0,str.length()-1);
         jtf[4].setText(str21);
           
       }
       }
   
   
    
}
   
}   
public class SignStudent
{
    public SignStudent(int choice)
    {      
        MyFrame fr=new MyFrame(choice);
    }    
}
