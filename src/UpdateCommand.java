public class UpdateCommand implements Command {
    Database database;
    private String key;
    private String value;


    public UpdateCommand(Database database){
        this.database = database;
    }

    public void execute(){
        database.update(key, value);

    }
    public void undo(){

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
