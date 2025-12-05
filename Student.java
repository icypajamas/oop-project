
interface resultCalculator {
    double passMarks = 50;

    double calculateTotal();
    double calculatePercentage();
    String calculateGrade();

}

abstract class Student implements resultCalculator {
    protected String studentID;
    protected String name;
    protected String program;
    protected static int totalStudents;
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

    public Student() {
        studentID = "";
        name = "";
        program = "";
        transcript = new Transcript();
        totalStudents++;
    }
    public void addCourse(Course c){

    }

    public double calculateGPA() {
        return transcript.getGPA();
    }

    public void displayResult() {
        System.out.println("-------Bachay ke kartoot-------");
        System.out.println("Total Obtained marks: " + calculateTotal());
        System.out.println("Obtained Percentage: " + calculatePercentage());
        System.out.println("Obtained Grade: " + calculateGrade());
    }

    @Override
    public double calculatePercentage() {
        return calculateTotal()*100;
    }

    @Override
    public double calculateTotal() {
        double totalMarksObtained = transcript.getTotalMarks();
        return (totalMarksObtained / (transcript.getResultCount()*100));
    }

    @Override
    public String calculateGrade() {
        double totalMarksObtained = calculateTotal();
        if(totalMarksObtained >= 85){
            return "A";
        } else if (totalMarksObtained >= 80) {
            return "A-";
        } else if (totalMarksObtained >= 75) {
            return "B+";
        } else if (totalMarksObtained >= 71) {
            return "B";
        }else if (totalMarksObtained >= 68) {
            return "B-";
        }else if (totalMarksObtained >= 64) {
            return "C+";
        }else if (totalMarksObtained >= 61) {
            return "C";
        }else if (totalMarksObtained >= 58) {
            return "C-";
        }else if (totalMarksObtained >= 54) {
            return "D+";
        }else if (totalMarksObtained >= 50) {
            return "D";
        }
        else return "F";
    }
}

class ScienceStudent extends Student {
    public ScienceStudent(){
        super();
    }

}

class ArtsStudent extends Student {
    public ArtsStudent(){
        super();
    }

}

class EngineeringStudent extends Student {
    public EngineeringStudent(){

    }
}
