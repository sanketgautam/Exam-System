package Instructor;

public class Question 
{
    private int questionId;
    private int type;
    private String question;
    private String answer1; // In case of non mcq save answer in answer1 and do not display it.
    						//in this case correctAns will always be 1 
    private String answer2;
    private String answer3;
    private String answer4;
    private int correctAns;
    private int marks;

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public String getQuestion() {
        return question;
    }

    public int getQuestionId() {
        return questionId;
    }
    public int getType() {
        return type;
    }
    public int getMarks() {
        return marks;
    }

    
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }
}