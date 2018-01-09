package studentpanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student
{
   int sid;
   String first_name;
   String last_name;
   String dob;
   String gender;
   String address;
   String pincode;
   String phonenumber;
   String email;
   String institute_name;
   String branch;
   String semester;
   String year;
   String scholar_no;
   String password;
   String recovery_question;
   String answer;
   byte [] image;
}
class UseStudent
{
    Connection conn=null;
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
     public Student getStudent(int lid)
     {
          Student s=new Student();
         try
         {
            
        String Query="select * from student where sid="+lid+";";
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery(Query);
           rs.next();
           s.sid=lid;
           String fname=rs.getString(2);
           s.first_name=fname;
           String lname=rs.getString(3);
           s.last_name=lname;
           String dateofbirth=rs.getString(4);
           s.dob=dateofbirth;
           String sex=rs.getString(5);
           s.gender=sex;
           String add=rs.getString(6);
           s.address=add;
           String pin=rs.getString(7);
           s.pincode=pin;
           String ph=rs.getString(8);
           s.phonenumber=ph;
           String mail=rs.getString(9);
           s.email=mail;
           String insti=rs.getString(10);
           s.institute_name=insti;
           String bran=rs.getString(11);
           s.branch=bran;
           String sem=rs.getString(12);
           s.semester=sem;
           String yea=rs.getString(13);
           s.year=yea;
           String scno=rs.getString(14);
           s.scholar_no=scno;
           String pas=rs.getString(15);
           s.password=pas;           
        }
         catch(Exception e)
         {
           System.out.println(e);
         }
         return s;
     }
     public void addStudent(Student s)
     {
       String Query="insert into student (first_name,last_name,dob,gender,address,pincode,phone_no,email_id,institute_name,branch,semester,year,scholar_no,image) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       try{
       PreparedStatement pst=conn.prepareStatement(Query);
       //pst.setInt(1,s.s_id); 
       pst.setString(1, s.first_name);
       pst.setString(2, s.last_name);
       pst.setString(3,s.dob);
       pst.setString(4,s.gender);
       pst.setString(5,s.address);
       pst.setString(6,s.pincode);
       pst.setString(7,s.phonenumber);
       pst.setString(8,s.email);
       pst.setString(9,s.institute_name);
       pst.setString(10,s.branch);
       pst.setString(11,s.semester);
       pst.setString(12,s.year);
       pst.setString(13,s.scholar_no);
       pst.setBytes(14,s.image);
       pst.executeUpdate();
        }
       catch(Exception e)
       {
          System.out.println(e);
       }
     }
            
}