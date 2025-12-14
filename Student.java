import java.io.Serializable;

interface resultCalculator {
    double passMarks = 50;

    double calculateTotal();

    double calculatePercentage();

    String calculateGrade();

}

abstract class Student implements resultCalculator, Serializable {
    protected String studentID;
    protected String name;
    protected String program;

    public static int getTotalStudents() {
        return totalStudents;
    }

    protected static int totalStudents;

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    protected Transcript transcript;

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", program='" + program + '\'' +
                '}';
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Student(String studentID, String name, String program, Transcript transcript) {
        this.studentID = studentID;
        this.name = name;
        this.program = program;
        this.transcript = transcript;
        totalStudents++;
    }

    public Student(String studentID, String name, String program) {
        this.studentID = studentID;
        this.name = name;
        this.program = program;
        this.transcript = new Transcript();
        totalStudents++;
    }

    public Student() {
        studentID = "";
        name = "";
        program = "";
        transcript = new Transcript();
        totalStudents++;
    }

    public void addCourse(Course c, double marksObtained) {
        System.out.println("------COURSE ADDITION-------");
        ResultEntry r = new ResultEntry(marksObtained, c);
        transcript.addResultEntry(r);
        System.out.println(
                "COURSE " + c.getTitle() + " WITH MARKS " + marksObtained + " ADDED" + " FOR STUDENT: " + getName());
    }

    public double calculateGPA() {
        return transcript.getGPA();
    }

    public String getStudentString() {
        StringBuilder text = new StringBuilder();
        text.append("------- STUDENT RESULT -------\n");
        text.append("Student ID: ").append(getStudentID()).append("\n");
        text.append("Name: ").append(getName()).append("\n\n");
        return text.toString();
    }
    public String toReportString(){
        StringBuilder text = new StringBuilder();
        text.append("Percentage: ").append(calculatePercentage()).append("\n");
        text.append("Grade: ").append(calculateGrade()).append("\n");
        return text.toString();
    }

    @Override
    public double calculatePercentage() {
        return calculateTotal() * 100;
    }

    public double calculateTotal() {
        double totalMarksObtained = transcript.getTotalMarks();
        int numCourses = transcript.getResults().size();
        return totalMarksObtained / (numCourses * 100);
    }

    public abstract String getDepartment();

    @Override
    public String calculateGrade() {
        double totalMarksObtained = calculateTotal() * 100;
        if (totalMarksObtained >= 85) {
            return "A";
        } else if (totalMarksObtained >= 80) {
            return "A-";
        } else if (totalMarksObtained >= 75) {
            return "B+";
        } else if (totalMarksObtained >= 71) {
            return "B";
        } else if (totalMarksObtained >= 68) {
            return "B-";
        } else if (totalMarksObtained >= 64) {
            return "C+";
        } else if (totalMarksObtained >= 61) {
            return "C";
        } else if (totalMarksObtained >= 58) {
            return "C-";
        } else if (totalMarksObtained >= 54) {
            return "D+";
        } else if (totalMarksObtained >= 50) {
            return "D";
        } else
            return "F";
    }
}

class ScienceStudent extends Student {
    public ScienceStudent() {
        super();
    }

    public ScienceStudent(String studentID, String name, String program, Transcript transcript) {
        super(studentID, name, program, transcript);
    }

    public ScienceStudent(String studentID, String name, String program) {
        super(studentID, name, program);
    }

    public String getDepartment() {
        return "Science";
    }
}

class ArtsStudent extends Student {
    public ArtsStudent() {
        super();
    }

    public ArtsStudent(String studentID, String name, String program, Transcript transcript) {
        super(studentID, name, program, transcript);
    }

    public ArtsStudent(String studentID, String name, String program) {
        super(studentID, name, program);
    }

    public String getDepartment() {
        return "Arts";
    }
}

class EngineeringStudent extends Student {
    public EngineeringStudent() {
        super();
    }

    public EngineeringStudent(String studentID, String name, String program, Transcript transcript) {
        super(studentID, name, program, transcript);
    }

    public EngineeringStudent(String studentID, String name, String program) {
        super(studentID, name, program);
    }

    public String getDepartment() {
        return "Engineering";
    }
}