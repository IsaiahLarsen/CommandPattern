public class UpdateCommand implements Command {
    Database database;
    private String key;
    private String value;
    private String prevKey;
    private String preValue;
    private final String name = "UpdateCommand";

    public UpdateCommand(Database database){
        this.database = database;
    }
    public String getName(){
        return name;
    }

    public void execute(){
        prevKey = key;
        preValue = database.data.get(key);
        database.update(key, value);

    }
    public void undo(){
        //reverse of update is update but just update to previous state
        database.update(prevKey, preValue);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
