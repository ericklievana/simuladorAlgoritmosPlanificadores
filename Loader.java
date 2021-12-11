import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Loader{
    private File file;
    private Scanner lector;
    private String path;

    public Loader(String path){
        this.path = path;
    }

    public ArrayList<Process> cargarProcesos() throws Exception{
        ArrayList<Process> procesos = new ArrayList<Process>();
        file = new File(path);
        lector = new Scanner(file);
        String tmp;
        String[] tmpProcess;
        String id;
        int duration;
        int arriveTime;
        int priority;
        while(lector.hasNextLine()){
            tmp = lector.nextLine();
            tmpProcess = tmp.split(",");
            id = tmpProcess[0];
            priority = Integer.parseInt(tmpProcess[1]);
            duration = Integer.parseInt(tmpProcess[3]);
            arriveTime = Integer.parseInt(tmpProcess[2]);
            procesos.add(new Process(id,priority,duration,arriveTime));
        }
        return procesos;
    }
}
