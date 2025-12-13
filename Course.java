import java.io.Serializable;

class Course implements Serializable {
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

    public CourseInstructor getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(CourseInstructor courseInstructor) {
        this.courseInstructor = courseInstructor;
    }


    @Override
    public String toString() {
        return courseCode + " - " + title + " (" + creditHours + " CH)" + " - " + getCourseInstructor().name;
    }
}

// Composed by Course
class CourseInstructor implements Serializable {
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

    @Override
    public String toString() {
        return "CourseInstructor{" +
                "name='" + name + '\'' +
                ", qualification='" + qualification + '\'' +
                '}';
    }
}
