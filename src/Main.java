import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String fileName;
        List<Database> databases = new LinkedList<>();
        List<CommandObj> commandObjs = new ArrayList<>();
        CommandObj obj;
        MacroCommand mc;
        System.out.println("Please enter file name for commands: ");
        fileName = in.next();
        String st;
        String[] s;
        Invoker invoker = new Invoker();
        List<String> databaseCreated = new ArrayList<>();
        String val = null;
        //Read commands into a list of objects
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((st = br.readLine()) != null ){
                //command, id, key, value(can have spaces)
                s = st.split(" ");
                if(s.length < 2){// A beginning or ending of a macro
                    obj = new CommandObj(s[0]);
                    commandObjs.add(obj);
                }else if(s.length == 4){
                    val = s[3];
                    obj = new CommandObj(s[0],s[1],s[2],val);
                    commandObjs.add(obj);
                }else if(s.length == 3){//Remove has no value
                    obj = new CommandObj(s[0],s[1],s[2]);
                    commandObjs.add(obj);
                } else {// value has a space
                    val = "";
                    for (int i = 3; i < s.length; i++) {
                       val += s[i] + " ";
                    }
                    obj = new CommandObj(s[0],s[1],s[2],val);
                    commandObjs.add(obj);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }//end of reading commands

        boolean macro = false;
        int index = 0;
        List<Command> commands = new ArrayList<>();
        //loop through to perform commands on databases
        for(CommandObj d : commandObjs){

 /*--------------------------------------Macro Section -------------------------------------*/
            if(macro) {
                //Database exist already
                if (databaseCreated.contains(d.getId())) {
                    index = databaseCreated.indexOf(d.getId());
                    switch (d.getCommand()) {
                        case "A":
                            AddCommand add = new AddCommand(databases.get(index));
                            add.setKey(d.getKey());
                            add.setValue(d.getValue());
                            commands.add(add);
                            break;
                        case "U":
                            UpdateCommand update = new UpdateCommand(databases.get(index));
                            update.setKey(d.getKey());
                            update.setValue(d.getValue());
                            commands.add(update);
                            break;
                        case "R":
                            RemoveCommand remove = new RemoveCommand(databases.get(index));
                            remove.setKey(d.getKey());
                            commands.add(remove);
                            break;
                        case "E":
                            System.out.println("End of Macro");
                            mc = new MacroCommand(commands);
                            invoker.setCommand(mc);
                            invoker.performCommand();
                            macro = false;
                            break;
                        default:
                            System.out.println("No Command Given");
                    }
                } else {
                    //no database with that id. Create one
                    if (d.getId() != null) {
                        Database db = new Database(d.getId());
                        databases.add(db);
                        databaseCreated.add(d.getId());
                        switch (d.getCommand()) {
                            case "A":
                                AddCommand add = new AddCommand(db);
                                add.setKey(d.getKey());
                                add.setValue(d.getValue());
                                break;
                            case "R":
                                System.out.println("That database doesn't exist");
                            case "U":

                            default:
                                System.out.println("No command given");
                        }
                    } else if (d.getId() == null && d.getCommand().equals("E")) {
                        System.out.println("End of Macro");
                        mc = new MacroCommand(commands);
                        invoker.setCommand(mc);
                        invoker.performCommand();
                        macro = false;
                    }

                }
            }//-------------------------------------- End Macro Section -------------------------------------

            //---------------------------------No Macro Section------------------------------------
            else {//no macro

                //Database exist already

                if (databaseCreated.contains(d.getId())) {
                    index = databaseCreated.indexOf(d.getId());
                    switch (d.getCommand()) {
                        case "A":
                            AddCommand add = new AddCommand(databases.get(index));
                            add.setKey(d.getKey());
                            add.setValue(d.getValue());
                            invoker.setCommand(add);
                            break;
                        case "U":
                            UpdateCommand update = new UpdateCommand(databases.get(index));
                            update.setKey(d.getKey());
                            update.setValue(d.getValue());
                            invoker.setCommand(update);
                            break;
                        case "R":
                            RemoveCommand remove = new RemoveCommand(databases.get(index));
                            remove.setKey(d.getKey());
                            invoker.setCommand(remove);
                            break;
                        case "B":
                            System.out.println("Starting Macro");
                            macro = true;
                            break;
                        default:
                            System.out.println("No Command Given");
                    }
                    invoker.performCommand();
                } else {

                    if(d.getId() != null){
                        Database db = new Database(d.getId());
                        databases.add(db);
                        databaseCreated.add(d.getId());
                        switch (d.getCommand()) {
                            case "A":
                                AddCommand add = new AddCommand(db);
                                add.setKey(d.getKey());
                                add.setValue(d.getValue());
                                invoker.setCommand(add);
                                break;
                            default:
                                System.out.println("No command given");
                        }
                        //Perform command
                        invoker.performCommand();

                        //else Your starting a macro
                    }else if(d.getId() == null && d.getCommand().equals("B")){
                            System.out.println("Starting Macro");
                            macro = true;
                    }
                }//no macro new database creation
            }//no macro else

        }//End of For Loop
        System.out.println("Final Status of Databases");
        for(Database data: databases){
            data.display();
        }
    }//End main

}//End Main
