import java.util.Map;
import java.util.Set;

public class RemoveCommand implements Command {
    Database database;
    private String key;
    private String prevKey;
    private String prevValue;
    private String name = "RemoveCommand";

    public RemoveCommand(Database database){
        this.database = database;
    }

    public String getName(){
        return name;
    }
    @Override
    public void execute() {
        prevValue = database.data.get(key);
        database.remove(key);
        prevKey = key;


    }

    @Override
    public void undo() {
       database.add(prevKey, prevValue);
    }

    public void setKey(String key) {
        this.key = key;
    }
}
