import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Database implements DatabaseInterface {
    Map<String, String> data = new HashMap<>();
    String prevCommand;

    public Database(String id) {

    }

    @Override
    public void add(String key, String value) {
        if(data.containsKey(key)){
            System.out.println("Database already exist");
        }
        data.put(key,value);
        prevCommand = "A";
    }

    @Override
    public void update(String key, String value) {
        prevCommand = "U";
    }

    @Override
    public void remove(String key) {
        prevCommand = "R";
    }

    @Override
    public void display(OutputStream stdOut) {

    }
}
