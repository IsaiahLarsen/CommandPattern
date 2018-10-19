public class CommandObj {
    private String command;
    private String id;
    private String key;
    private String value;

    public CommandObj(String command){
        this.command = command;
    }

    public CommandObj(String command, String id, String key) {
        this.command = command;
        this.id = id;
        this.key = key;
    }

    public CommandObj(String command, String id, String key, String value) {
        this.command = command;
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public String getCommand() {
        return command;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
