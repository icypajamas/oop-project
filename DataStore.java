import java.util.*;
import java.io.*;

public class DataStore <T extends  Serializable>{
    public void saveToFile(String fileName, List<T> items){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
            out.writeObject(items);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<T> readFromFile(String fileName){
        List<T> items = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            items = (List<T>) in.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }

        return items;
    }
}
