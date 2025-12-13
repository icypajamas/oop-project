import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecordList<T> implements Serializable {
    ArrayList<T> items;

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = new ArrayList<>(items);
    }

    public RecordList(ArrayList<T> items) {
        this.items = items;
    }

    public RecordList() {
        items = new ArrayList<>(0);
    }

    public void add(T item) {
        items.add(item);
    }

    public void remove(String id) {
        System.out.println("------REMOVING AN ITEM------");
        boolean found = false;
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if (item instanceof Student) {
                if (((Student) item).getStudentID().equalsIgnoreCase(id)) {
                    Student s = (Student) item;
                    items.remove(i);
                    found = true;
                    System.out.println("STUDENT REMOVED");
                    break;
                }
            } else if (item instanceof Course) {
                if (((Course) item).getCourseCode().equalsIgnoreCase(id)) {
                    Course s = (Course) item;
                    items.remove(i);
                    found = true;
                    System.out.println("COURSE REMOVED");
                    break;
                }
            }
        }
            if (!found) {
                System.out.println("ID NOT FOUND");
            }
    }
    public T search(String ID) {
        System.out.println("------------SEARCHING--------------");
        for (T item : items) {
            if (item instanceof Student) {
                if (((Student) item).getStudentID().equalsIgnoreCase(ID)) {
                    System.out.println("FOUND");
                    return item;
                }
            }
            else if (item instanceof Course) {
                if (((Course) item).getCourseCode().equalsIgnoreCase(ID)) {
                    System.out.println("NOT FOUND");
                    return item;
                }
            }
        }
        return null;
    }
}