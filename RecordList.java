import java.util.ArrayList;

public class RecordList<T> {
    ArrayList<T> items;

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
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
        boolean found = false;
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if (item.toString().equalsIgnoreCase(id)) {
                items.remove(i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("ID NOT FOUND");
        }
    }

    public void getAll() {
        for (T stuff : items) {
            System.out.println(stuff);
        }
    }
}
