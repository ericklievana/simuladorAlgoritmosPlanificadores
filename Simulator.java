import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Simulator{
    public ArrayList<Process> procesos;
    public int time;
    public Process current;
    private Process total;
    private Process promedio;
    private Runable algoritmo;
    private InformationGui info;
    private TableTimeGui timeTable;
    private ResultsTableGui resultados;
    private int quantum;
    private Process[] promedios;

    public Simulator(ArrayList<Process> procesos){
        this.procesos = procesos;
        time = 0;
        current = null;
        promedio = null;
        total = null;
        promedios = new Process[5];
    }

    public void setInfo(InformationGui info){
        this.info = info;
    }

    public void setTimeTable(TableTimeGui timeTable){
        this.timeTable = timeTable;
    }

    public void setResultados(ResultsTableGui resultados){
        this.resultados = resultados;
    }

    public void setQuantum(int quantum){
        this.quantum = quantum;
    }

    public int getQuantum(){
        return quantum;
    }

    public void compute(){
        time++;
        if(current == null) return;
        current.compute();
        timeTable.table.setValueAt(current.getId(),2,((time-1)*2+1));
    }

    public int totalTime(){
        int totalTime = 0;
        for(int i = 0; i < procesos.size(); i++){
            totalTime += procesos.get(i).getDuration();
        }
        return totalTime;
    }

    public int numeroProcesos(){
        return procesos.size()+2;
    }

    public void recibirProcesos(){
        Process tmp;
        String cell = " ";
        for(int i = 0; i < procesos.size(); i++){
            tmp = procesos.get(i);
            if(tmp.getArriveTime() == time){
                cell += tmp.getId()+" ";
                algoritmo.encolar(tmp);
            }
        }
        timeTable.table.setValueAt(cell,0,time*2);
    }

    public boolean isFinish(){
        Process tmp;
        for(int i = 0; i < procesos.size(); i++){
            tmp = procesos.get(i);
            if(!tmp.isDone()) return false;
        }
        return true;
    }

    public void calcularResultados(){
        float output = 0;
        float response = 0;
        float waste = 0;
        float penalty = 0;
        float wait = 0;
        Process tmp;
        for(int i = 0; i < procesos.size(); i++){
            tmp = procesos.get(i);
            tmp.calculate();
            output += tmp.getOutput();
            response += tmp.getResponse();
            waste += tmp.getWaste();
            penalty += tmp.getPenalty();
            wait += tmp.getWait();
        }
        total = new Process("Total",output,response,waste,penalty,wait);
        output /= (procesos.size());
        response /= (procesos.size());
        waste /= (procesos.size());
        penalty /= (procesos.size());
        wait /= (procesos.size());
        promedio = new Process("Promedio",output,response,waste,penalty,wait);
    }

    public void reset(){
        current = null;
        promedio = null;
        total = null;
        algoritmo = null;
        time = 0;
        timeTable.reset();
        resultados.reset();
        for(int i = 0; i < procesos.size(); i++){
            procesos.get(i).reset();
        }
    }

    public void imprimir(){
        String[] tmp;
        for(int i = 0; i < procesos.size(); i++){
            tmp = procesos.get(i).imprimir();
            for(int j = 0; j < tmp.length; j++){
                resultados.table.setValueAt(tmp[j],i,j);
            }
        }
        tmp = total.imprimir();
        for(int i = 0; i < tmp.length; i++){
            resultados.table.setValueAt(tmp[i],procesos.size(),i);
        }
        tmp = promedio.imprimir();
        for(int i = 0; i < tmp.length; i++){
            resultados.table.setValueAt(tmp[i],procesos.size()+1,i);
        }
    }

    public void start(String opcion){
        switch(opcion){
            case Nombres.fcfs:
                algoritmo = new FCFS(this,info);
                break;
            case Nombres.srt:
                algoritmo = new SRT(this,info);
                break;
            case Nombres.sjf:
                algoritmo = new SJF(this,info);
                break;
            case Nombres.prioridades:
                algoritmo = new Prioridades(this,info);
                break;
            case Nombres.roundRobin:
                algoritmo = new RoundRobin(this,info);
                break;
            default:
                return;
        }
        algoritmo.run();
        calcularResultados();
        imprimir();
        switch(opcion){
            case Nombres.fcfs:
                promedios[0] = promedio;
                break;
            case Nombres.srt:
                promedios[1] = promedio;
                break;
            case Nombres.sjf:
                promedios[2] = promedio;
                break;
            case Nombres.prioridades:
                promedios[4] = promedio;
                break;
            case Nombres.roundRobin:
                promedios[3] = promedio;
                break;
            default:
                return;
        }
    }

    public String mejor(){
        int opc;
        int i;
        for(i = 0; i < promedios.length ; i++){
            if(promedios[i] != null) break;
        }
        float min = promedios[i].performance();
        opc = i;
        float tmp;
        for(int j = i+1; j < promedios.length; j++){
            if(promedios[j] != null){
                tmp = promedios[j].performance();
                if(tmp < min){
                    min = tmp;
                    opc = j;
                }
            }
        }
        switch(opc){
            case 0:
                return Nombres.fcfs;
            case 1:
                return Nombres.srt;
            case 2:
                return Nombres.sjf;
            case 3:
                return Nombres.roundRobin;
            case 4:
                return Nombres.prioridades;
            default:
                break;
        }
        return "";
    }
}
