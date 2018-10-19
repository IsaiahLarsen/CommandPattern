public class RemoveCommand implements Command {
    Database database;
    private String key;

    public RemoveCommand(Database database){
        this.database = database;
    }

    @Override
    public void execute() {
        database.remove(key);
    }

    @Override
    public void undo() {

    }

    public void setKey(String key) {
        this.key = key;
    }
}
