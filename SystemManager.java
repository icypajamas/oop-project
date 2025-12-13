import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SystemManager {

    private RecordList<Student> students;
    private RecordList<Course> courses;

    private DataStore<Student> studentStore;
    private DataStore<Course> courseStore;

    public SystemManager() {
        students = new RecordList<>();
        courses = new RecordList<>();

        studentStore = new DataStore<>();
        courseStore = new DataStore<>();

        loadData();
    }

    public RecordList getStudents() {
        return students;
    }

    public RecordList getCourses() {
        return courses;
    }

    public void loadData() {
        students.setItems(new ArrayList<>(studentStore.readFromFile("students.dat")));
        courses.setItems(new ArrayList<>(courseStore.readFromFile("courses.dat")));
    }

    public void saveData() {
        studentStore.saveToFile("students.dat", students.getItems());
        courseStore.saveToFile("courses.dat", courses.getItems());
    }

    public String createReportString(String id) {
        Student student = students.search(id);
        if (student == null) {
            return null;
        }
        Transcript transcript = student.getTranscript();
        if (transcript == null) {
            return "No transcript found for this student.";
        }
        StringBuilder report = new StringBuilder();
        report.append("Student ID: ").append(student.getStudentID()).append("\n");
        report.append("Name: ").append(student.getName()).append("\n\n");

        report.append(transcript.toReportString());

        return report.toString();
    }

    public boolean deleteStudent(String id) {
        Student student = students.search(id);
        if (student != null) {
            students.remove(id);
            Student.totalStudents--;
            saveData();
            return true;
        } else
            return false;
    }

    public void addStudent(Student student) {
        students.add(student);
        saveData();
    }

    public boolean addCourse(Course course) {
        if (courses.search(course.getCourseCode()) != null) {
            return false;
        }
        courses.add(course);
        saveData();
        return true;
    }

    public boolean deleteCourse(String code) {
        Course course = courses.search(code);
        if (course != null) {
            courses.remove(code);
            Course.totalCourses--;
            saveData();
            return true;
        } else
            return false;
    }

    public void assignInstructor(Course course, CourseInstructor instructor) {
        course.setCourseInstructor(instructor);
        saveData();
    }

    public boolean studentHasResultForCourse(String studentId, Course course) {
        Student student = students.search(studentId);
        if (student == null || course == null) {
            return false;
        }

        for (ResultEntry r : student.getTranscript().getResults()) {
            if (r.getCourse().getCourseCode()
                    .equalsIgnoreCase(course.getCourseCode())) {
                return true;
            }
        }
        return false;
    }

}

/*
 * import java.util.*;
 * import java.io.*;
 * 
 * public class SystemManager {
 * public static void main(String[] args) {
 * // COURSE INSTRUCTORS
 * CourseInstructor first = new CourseInstructor("abc", "phd");
 * CourseInstructor second = new CourseInstructor("def", "MS");
 * CourseInstructor third = new CourseInstructor("ghi", "BS");
 * CourseInstructor fourth = new CourseInstructor("jkl", "MS");
 * CourseInstructor fifth = new CourseInstructor("mno", "phd");
 * CourseInstructor sixth = new CourseInstructor("pqr", "MS");
 * // COURSES
 * Course one = new Course("101", "PF", 3, first);
 * Course two = new Course("102", "OOP", 3, second);
 * Course three = new Course("103", "ML", 3, third);
 * Course four = new Course("104", "DISCRETE", 3, fourth);
 * Course five = new Course("105", "MATHS", 2, fifth);
 * Course six = new Course("106", "CALCULUS", 3, sixth);
 * // RESULT ENTRY
 * ResultEntry r1 = new ResultEntry(98, one);
 * ResultEntry r2 = new ResultEntry(90, two);
 * ResultEntry r3 = new ResultEntry(89, three);
 * ResultEntry r4 = new ResultEntry(95, four);
 * ResultEntry r5 = new ResultEntry(100, five);
 * ResultEntry r6 = new ResultEntry(99, six);
 * ArrayList<ResultEntry> r = new ArrayList<>(3);
 * r.add(r1);
 * r.add(r2);
 * r.add(r3);
 * ArrayList<ResultEntry> rr = new ArrayList<>(3);
 * rr.add(r4);
 * rr.add(r5);
 * rr.add(r6);
 * // TRANSCRIPT
 * Transcript t1 = new Transcript(r);
 * Transcript t2 = new Transcript(rr);
 * Transcript[] t = { t1, t2 };
 * // STUDENT
 * Student[] s = new Student[3];
 * s[0] = new ScienceStudent("01", "ABC", "CS", t1);
 * s[1] = new EngineeringStudent("21", "DEF", "EE", t2);
 * s[2] = new ArtsStudent("31", "jdd", "psychology", t1);
 * for (int i = 0; i < 3; i++) {
 * s[i].displayResult();
 * }
 * s[0].addCourse(four, 95);
 * s[0].displayResult();
 * System.out.println(Student.getTotalStudents());
 * one.displayCourseDetails();
 * t1.getGPA();
 * RecordList<Student> studentRecords = new RecordList<>(new ArrayList<>());
 * for (int i = 0; i < 3; i++) {
 * studentRecords.add(s[i]);
 * }
 * // COURSE
 * Course[] c = new Course[3];
 * c[0] = one;
 * c[1] = two;
 * c[2] = three;
 * RecordList<Course> courseRecords = new RecordList<>(new ArrayList<>());
 * for (int i = 0; i < 3; i++) {
 * courseRecords.add(c[i]);
 * }
 * studentRecords.add(new EngineeringStudent("001", "kjg", "EE", t2));
 * studentRecords.remove("21");
 * studentRecords.getAll();
 * DataStore<Student> studentStore = new DataStore<>();
 * DataStore<Course> courseStore = new DataStore<>();
 * courseStore.saveToFile("courses.dat", courseRecords.getItems());
 * System.out.println("COURSES SAVED.");
 * studentStore.saveToFile("students.dat", studentRecords.getItems());
 * System.out.println("STUDENTS SAVED.");
 * List<Student> loadedStudents = studentStore.readFromFile("students.dat");
 * List<Course> loadedCourses = courseStore.readFromFile("courses.dat");
 * System.out.println("LOADED STUDENTS");
 * for (Student all : loadedStudents) {
 * System.out.println(all);
 * }
 * System.out.println(studentRecords.search("001"));
 * System.out.println(studentRecords.search("000"));
 * }
 * }
 */