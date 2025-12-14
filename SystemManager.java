import java.util.ArrayList;

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
        Student.totalStudents = students.getItems().size();
        Course.totalCourses = courses.getItems().size();
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
        report.append(student.getStudentString());
        report.append(transcript.transcriptInfo());
        report.append(transcript.totalMarks());
        report.append(student.toReportString());
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

    public int totalPassingStudents() {
        ArrayList<Student> array = students.getItems();
        int passCount = 0;
        for (Student s : array) {
            if (!s.calculateGrade().equals("F")) {
                passCount++;
            }
        }
        return passCount;
    }

    public int totalFailStudents() {
        ArrayList<Student> array = students.getItems();
        int failCount = 0;
        for (Student s : array) {
            if (s.calculateGrade().equals("F")) {
                failCount++;
            }
        }
        return failCount;
    }

    public double passStudentPercentage() {
        int total = totalFailStudents() + totalPassingStudents();
        if (total == 0.0)
            return 0.0;
        double percentage = ((double) totalPassingStudents() / total) * 100;
        return Math.round(percentage * 100.0) / 100.0;
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