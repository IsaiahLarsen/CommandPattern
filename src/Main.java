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
        List<DatabaseObj> databaseObjs = new ArrayList<>();
        DatabaseObj obj;
        System.out.println("Please enter file name for commands: ");
        fileName = in.next();
        String st;
        String[] s;
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
                    obj = new DatabaseObj(s[0]);
                    databaseObjs.add(obj);
                }else if(s.length == 4){
                    val = s[3];
                    obj = new DatabaseObj(s[0],s[1],s[2],val);
                    databaseObjs.add(obj);
                }else if(s.length == 3){//Remove has no value
                    obj = new DatabaseObj(s[0],s[1],s[2]);
                    databaseObjs.add(obj);
                } else {// value has a space
                    val = "";
                    for (int i = 3; i < s.length; i++) {
                       val += s[i] + " ";
                    }
                    obj = new DatabaseObj(s[0],s[1],s[2],val);
                    databaseObjs.add(obj);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }//end of reading commands

        //loop through to perform commands on databases
        for(DatabaseObj d : databaseObjs){

            //Database exist already
          if(databaseCreated.contains(d.getId())){
              Invoker invoker = new Invoker();
              int index = databaseCreated.indexOf(d.getId());
              switch(d.getCommand()){
                  case "U":
                      UpdateCommand update = new UpdateCommand(databases.get(index));
                      update.setKey(d.getKey());
                      update.setValue(d.getValue());
                      invoker.setCommand(update);
                      invoker.performCommand();
                      break;
                  case "R":
                  case "B":
                      System.out.println("Macro Begin");
                  case "E":
                      System.out.println("Macro End");
                      default:
                          System.out.println("No Command Given");
              }
          }else {
              //no database with that id. Create one
              Invoker invoker = new Invoker();
              Database db = new Database(d.getId());
              databases.add(db);
              databaseCreated.add(d.getId());
              switch (d.getCommand()) {
                  case "A":
                      AddCommand add = new AddCommand(db);
                      add.setKey(d.getKey());
                      add.setValue(d.getValue());
                      invoker.setCommand(add);
                      invoker.performCommand();
                      break;
                  default:
                      System.out.println("No command given");
              }
          }
        }
    }
}
