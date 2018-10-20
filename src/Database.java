import java.util.HashMap;
import java.util.Map;

public class Database implements DatabaseInterface {
    Map<String, String> data = new HashMap<>();
    String id;


    public Database(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }

    @Override
    public void add(String key, String value) {
        if(data.containsKey(key)){
            System.out.println("Database key already exist");
        }else {
            data.put(key, value);
        }
    }

    @Override
    public void update(String key, String value) {
        if (data.containsKey(key) == false) {
            System.out.println("No key named" + key + " exist");
        } else {
            data.replace(key, value);
        }
    }

    @Override
    public void remove(String key) {
       if(data.containsKey(key)){
           data.remove(key);
       }else{
           System.out.println("This key doesn't exist cannot remove");
       }
    }

    @Override
    public void display() {
        System.out.println("Database " + id);
        System.out.println(data.toString() + "\n");
    }

}
