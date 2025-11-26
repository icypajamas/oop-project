import java.util.*;

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

    public Student(String studentID, String name, String program) {
        this.studentID = studentID;
        this.name = name;
        this.program = program;
        totalStudents++;
    }

    public Student() {
        studentID = "";
        name = "";
        program = "";
    }
    public void  addCourse(Course c){

    }

    public double calcGPA() {
        double gpa = transcript.getGPA();
        return gpa;
    }

    public void displayResult() {
        System.out.println();

    }

}

interface resultCalculator {

}

class Transcript {
    private List<resultEntry> results;
    public List<resultEntry> getResults() {
        return results;
    }

    public void setResults(List<resultEntry> results) {
        this.results = results;
    }

    public Transcript(List<resultEntry> results) {
        this.results = results;
    }
    public Transcript(){
        results = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Transcript{" +
                "results=" + results +
                '}';
    }

    public void addResultEntry(resultEntry r) {
        results.add(r);
    }
    public double getGPA(){
        double points = 0;
        double totalHours = 0;
        for(resultEntry r:results) {
            double creditHours = r.getCourse().getCreditHours();
            double marks = r.getMarksObtained();
            points += (creditHours * marksToGradePoints(marks));
            totalHours += creditHours;
        }if(totalHours != 0) {
            double gpa = points / totalHours;
            return gpa;
        }else{
            return -1;
        }
    }
    public double marksToGradePoints(double marks){
        if(marks >= 90){
            return 4.0;
        }
        else if(marks >= 80){
            return 3.7;
        }
        else if(marks >= 70){
            return 3.0;
        }
        else if(marks >= 60){
            return 2.0;
        }
        else if(marks >= 50){
            return 1.0;
        }
        else{
            return 0.0;
        }
    }
    public double getTotalMarks() {
        double totalMarks = 0;
        for (resultEntry r : results) {
            totalMarks += r.getMarksObtained();
        }
        return totalMarks;
    }
}

class resultEntry {
    private double marksObtained;
    private Course course;

    public double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(double marksObtained) {
        if(marksObtained >0 && marksObtained <= 100) {
            this.marksObtained = marksObtained;
        }
        else{
            System.out.println("MARKS SHOULD BE IN THE RANGE OF 0 TO 100");
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public resultEntry(double marksObtained, Course course) {
        this.marksObtained = marksObtained;
        this.course = course;
    }

    public resultEntry() {
        marksObtained = 0;
        course = new Course();
    }

    @Override
    public String toString() {
        return "resultEntry{" +
                "marksObtained=" + marksObtained +
                ", course=" + course +
                '}';
    }
}

class Course {
    private String courseCode;
    private String title;
    private double creditHours;
    static int totalCourses = 0;
    private CourseInstructor courseInstructor;

    public Course() {
        courseCode = "";
        title = "";
        creditHours = 0.0;
        courseInstructor = new CourseInstructor();
    }

    public Course(String courseCode, String title, double creditHours, CourseInstructor courseInstructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHours = creditHours;
        this.courseInstructor = courseInstructor;
        totalCourses++;

    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCreditHours() {
        return this.creditHours;
    }

    public void setCreditHours(double creditHours) {
        this.creditHours = creditHours;
    }

    public void displayCourseDetails() {
        System.out.println("-------Course Details-------");
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Title: " + title);
        System.out.println("Credit Hours: " + creditHours);

    }

    @Override
    public String toString() {
        return "{" +
                " courseCode='" + getCourseCode() + "'" +
                ", title='" + getTitle() + "'" +
                ", creditHours='" + getCreditHours() + "'" +
                "}";
    }
}

class CourseInstructor {
    String name;
    String qualification;

    public CourseInstructor() {
        name = "";
        qualification = "";
    }

    public CourseInstructor(String name, String qualification) {
        this.name = name;
        this.qualification = qualification;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return this.qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void displayInstructorDetails() {
        System.out.println("Instructor name: " + name);
        System.out.println("Instructor Qualification: " + qualification);
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", qualification='" + getQualification() + "'" +
                "}";
    }

}

class ScienceStudent extends Student {

}

class ArtsStudent extends Student {

}

class EngineeringStudent extends Student {

}

public class Main {
    public static void main(String[] args) {

    }
}