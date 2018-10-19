import java.util.HashMap;
import java.util.Map;

public class Database implements DatabaseInterface {
    Map<String, String> data = new HashMap<>();
    String prevCommand = null;
    String id;


    public Database(String id) {
        this.id = id;
    }


    @Override
    public void add(String key, String value) {
        if(data.containsKey(key)){
            System.out.println("Database key already exist");
        }else {
            data.put(key, value);
            prevCommand = "A";
            display();
        }
    }

    @Override
    public void update(String key, String value) {
        if (data.containsKey(key) == false) {
            System.out.println("No key named" + key + " exist");
        } else {
            data.replace(key, value);
            prevCommand = "U";
            display();
        }
    }

    @Override
    public void remove(String key) {
       if(data.containsKey(key)){
           data.remove(key);
           prevCommand = "R";
           display();
       }else{
           System.out.println("This key doesn't exist cannot remove");
       }
    }

    @Override
    public void display() {
        System.out.println("Database " + id);
        System.out.println(data.toString());
    }
}
