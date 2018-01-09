package SignUp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class Instructor 
{
    //int login_id;
    int iid;
   String first_name;
   String last_name;
   String dob;
   String gender;
   String address;
   String pincode;
   String phonenumber;
   String email;
   String institute_name;
   String department; 
   String instructorId;
   String password;
   byte []image;
}
class UseInstructor
{
    Connection conn=null;
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
     public Instructor getInstructor(int lid)
     {
          Instructor s=new Instructor();
         try
         {
            
        String Query="select * from instructor where iid="+lid+";";
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery(Query);
           rs.next();
           s.iid=lid;
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
           String deptt=rs.getString(11);
           s.department=deptt;
           //String sem=rs.getString(12);
           //s.semester=sem;
           //String yea=rs.getString(13);
           //s.year=yea;
           String iid=rs.getString(12);
           s.instructorId=iid;
           String pas=rs.getString(13);
           s.password=pas;           
        }
         catch(Exception e)
         {
           System.out.println(e);
         }
         return s;
     }
     public void addInstructor(Instructor i)
     {
       String Query="insert into instructor(first_name,last_name,dob,gender,address,pincode,phone_no,email_id,institute_name,department,instructor_id,image) values(?,?,?,?,?,?,?,?,?,?,?,?)";
       try{
       PreparedStatement pst=conn.prepareStatement(Query);
       //pst.setInt(1,s.s_id); 
       pst.setString(1, i.first_name);
       pst.setString(2, i.last_name);
       pst.setString(3,i.dob);
       pst.setString(4,i.gender);
       pst.setString(5,i.address);
       pst.setString(6,i.pincode);
       pst.setString(7,i.phonenumber);
       pst.setString(8,i.email);
       pst.setString(9,i.institute_name);
       pst.setString(10,i.department);
       //pst.setString(11,i.semester);
       //pst.setString(12,i.year);
       pst.setString(11,i.instructorId);  
       pst.setBytes(12,i.image);
       pst.executeUpdate();
        }
       catch(Exception e)
       {
          System.out.println(e); 
       }
     }
            
}