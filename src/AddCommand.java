public class AddCommand implements Command{
    Database database;
    private String key;
    private String value;
    private String prevKey;
    private String name = "AddCommand";

    public AddCommand(Database database){
        this.database = database;
    }

    public String getName(){
        return name;
    }

    public void execute(){

        database.add(key,value);
        prevKey = key;
    }
    public void undo(){
        database.remove(prevKey);
    }


    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
