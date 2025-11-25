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
    }

    public Student() {
        studentID = "";
        name = "";
        program = "";
    }

    public void addCourse() {

    }

    public double calcGPA() {
        double gpa = 0;
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

    public void addResultEntry(resultEntry r) {
        results.add(r);

    }
    // public double getGPA(){
    // public double getTotalMarks(){

}

class resultEntry {
    private double marksObtained;
    private Course course;

    public double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(double marksObtained) {
        this.marksObtained = marksObtained;
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

        public void displayInstructorDetails() {
            System.out.println("Instructor name: " + name);
            System.out.println("Instructor Qualification: " + qualification);
        }

    }

    public Course() {
        courseCode = "";
        title = "";
        creditHours = 0.0;
        this.courseInstructor = new CourseInstructor();
    }

    public Course(String courseCode, String title, double creditHours) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHours = creditHours;
        this.courseInstructor = new CourseInstructor();
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
        courseInstructor.displayInstructorDetails();

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