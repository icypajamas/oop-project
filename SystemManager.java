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
    }

    public RecordList getStudents() {
        return students;
    }

    public RecordList getCourses() {
        return courses;
    }

    public void loadData() {
        students.setItems(new ArrayList<>(studentStore.readFromFile("Students.dat")));
        courses.setItems(new ArrayList<>(courseStore.readFromFile("Courses.dat")));
    }

    public void saveData() {
        studentStore.saveToFile("Students.dat", students.getItems());
        courseStore.saveToFile("Courses.dat", courses.getItems());
    }

    public static void main(String[] args) {

    }
}