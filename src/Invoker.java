public class Invoker {
    Command[] commands;
    private int i = 0;
    public void setCommand(Command c){
        commands[i] = c;
        i++;
    }

}
