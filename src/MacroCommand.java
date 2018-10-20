import java.util.ArrayList;
import java.util.List;

public class MacroCommand implements Command {
    List<Command> commands;
    private String name = "Macro";

    public MacroCommand(List<Command> commands) {
        this.commands = commands;
    }

    public String getName(){
        return name;
    }
    @Override
    public void execute() {
        for(Command c : commands){
            c.execute();
        }
    }

    @Override
    public void undo() {
        for(int i = commands.size() - 1; i > commands.size(); i--){
            commands.get(i).undo();
        }
    }
}
