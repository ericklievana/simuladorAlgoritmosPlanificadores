import java.util.ArrayList;

public class FCFS implements Runable{
    private ArrayList<Process> cola;
    private Simulator simulador;
    private InformationGui info;

    public FCFS(Simulator simulador,InformationGui info){
        this.simulador = simulador;
        this.info = info;
        cola = new ArrayList<Process>();
    }

    public void encolar(Process proceso){
        cola.add(proceso);
    }

    public void run(){
        do{
            simulador.recibirProcesos();
            if(simulador.current == null && !cola.isEmpty()){
                simulador.current = cola.remove(0);
                info.setLabelActual(simulador.current.getId());
                if(!cola.isEmpty()){
                    info.setLabelCola(cola.get(0).getId());
                }
                simulador.current.setStartTime(simulador.time);
            }
            simulador.compute();
            info.setLabelTiempo(String.valueOf(simulador.time));
            if(simulador.current != null){
                if(simulador.current.isDone()){
                    simulador.current.setEndTime(simulador.time);
                    simulador.current = null;
                }
            }
        }while(!simulador.isFinish());
    }
}
