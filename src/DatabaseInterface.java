import java.io.OutputStream;

public interface DatabaseInterface {
    public void add(String key, String value);
    public void update(String key, String value);
    public void remove(String key);
    public void display();
}
