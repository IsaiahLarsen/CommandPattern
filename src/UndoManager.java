import java.util.Stack;

public class UndoManager {
   private Stack<Command> undoCmd = new Stack<>();

    public void addUndoCommand(Command c){
        undoCmd.push(c);
    }


    public Command getUndoCommand(){
        return undoCmd.pop();
    }
    public boolean isStackEmpty(){
        return undoCmd.isEmpty();
    }

}
