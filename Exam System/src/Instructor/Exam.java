package Instructor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Login.dbConnector;

public class Exam {
	static dbConnector dbc = new dbConnector();

	int examId;
	int numberOfQuestions;
	int totalMarks;
	ArrayList<Question> questions;
	String title;
	int duration;
	int groupid;
	String datetime;
	int i; // This is counter variable for questions[]
	Exam()
	{
		numberOfQuestions = 0;
		duration =0 ;
		questions = new ArrayList<Question>();
		i=0;
	}
	
	public int getExamId() {
        return examId;
    }
	
    public int getNumberOfQuestions(){
    	//return numberOfQuestions;
    	return questions.size();
    }
	public int getTotalMarks() {
        return totalMarks;
    }

    public Question[] getQuestions(){
    	Question q[] = new Question[numberOfQuestions];
    	return questions.toArray(q);
    }
    public String getName()
    {
    	return(title);
    }
    public int getDuration() {
        return duration;
    }
    
    public String getDateTime() {
        return datetime;
    }
    public int getGroup() {
        return groupid;
    }
    public void setDateTime(String datetime) {
        this.datetime= datetime;
    }
    public void setName(String title)
    {
    	this.title = title;
    }
    public void setGroupId(int groupid) {
        this.groupid = groupid;
    }
    
	public void setExamId(int examId) {
        this.examId = examId;
    }

    public void setNumberOfQuestions(int numberOfQuestions)
    {
    	 //this.numberOfQuestions = numberOfQuestions;
    	 
    	
    	 //questions = new Question[100];
    	 i=0;
    }
	public void setTotalMarks(int totalMarks) 
	{
        this.totalMarks = totalMarks;
    }
	public void setDuration(int duration) 
	{
        this.duration = duration;
    }
    public void addQuestion(Question q){
    	numberOfQuestions++;
    	questions.add(q);
    	i++;
    }
    
    public void displayQues()
    {
    	int n;
    	n=questions.size();
    	System.out.println("dislay start");
    	for(int i=0;i<n;i++)
    	{
    		System.out.println(questions.get(i).getQuestion());
    	}
    	System.out.println("dislay end");
    }
    
    public void deleteQuestion(int qId)
    {
    	Question q[];
    	q= getQuestions();
    	for(int j=0;j<numberOfQuestions;j++)
    	{
    		//if(q[j].getQuestionId()==qId)
    		if(q[j].getQuestionId()==qId)
    		{
    			questions.remove(j);
    			//questions[i]= null;
    			/*for(int k=j;k<(i-1);k++)  //Delete Question and shift rest of the questions
    			{
    				questions[k]=questions[k+1];
    				questions[k].setQuestionId(k+1);
    			} */   			
    			i--;
    			numberOfQuestions --;
    			break;
    		}
    	}
    }
    public void fetchFromDataBase(int id)
    {
    	
    	String qry1 = "SELECT * FROM exam WHERE eid = "+id;
    	try {
			Statement stmt = dbc.conn.createStatement();
			ResultSet rs = stmt.executeQuery(qry1);
			
			while(rs.next())
			{
				int iid = rs.getInt(1);
				int eid = rs.getInt(2);
				String ename= rs.getString(3);
				String edate_time = rs.getString(4);
				int eduration = rs.getInt(5);
				int gid = rs.getInt(6);
				
				setExamId(eid);
				setGroupId(gid);
				setName(ename);
				setDateTime(edate_time);
				setDuration(eduration);
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
		String qry2=  "Select A.qid, A.qquestion,A.qop1, A.qop2, A.qop3, A.qop4, A.qcorrect_answer "
				 +"From Question A, ExamQuestionTable B "+
				 "Where B.EId = "+id+" and A.Qid = B.QId";
		
    	numberOfQuestions = 0;
    	i=0;
		try {
			Statement stmt = dbc.conn.createStatement();
			ResultSet rs = stmt.executeQuery(qry2);
			while(rs.next())
			{
				Question q = new Question();
				q.setQuestionId(rs.getInt(1));
				q.setQuestion(rs.getString(2));
				q.setAnswer1(rs.getString(3));
				q.setAnswer2(rs.getString(4));
				q.setAnswer3(rs.getString(5));
				q.setAnswer4(rs.getString(6));
				q.setCorrectAns(rs.getInt(7));
				// Marks
				addQuestion(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayQues();
		
    	
    }
}
