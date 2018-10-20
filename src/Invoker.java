public class Invoker {
    Command command;

    public void setCommand(Command c){
        command = c;

    }

    public void performCommand(){

        command.execute();
    }

    public void undoCommand(){
        command.undo();
    }

}
