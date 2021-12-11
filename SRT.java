import java.util.ArrayList;

public class SRT implements Runable{
    private ArrayList<Process> cola;
    private Simulator simulador;
    private InformationGui info;

    public SRT(Simulator simulador,InformationGui info){
        this.simulador = simulador;
        this.info = info;
        cola = new ArrayList<Process>();
    }

    public void encolar(Process proceso){
        int tmpRemain;
        if(simulador.current != null){
            tmpRemain = simulador.current.getDuration() - simulador.current.getCpuTime();
            if(tmpRemain > proceso.getDuration()){
                cola.add(0,simulador.current);
                simulador.current = proceso;
                proceso.setStartTime(simulador.time);
                return;
            }
        }
        Process tmp;
        for(int i = 0; i < cola.size(); i++){
            tmp = cola.get(i);
            tmpRemain = tmp.getDuration() - tmp.getCpuTime();
            if(tmpRemain > proceso.getDuration()){
                cola.add(i,proceso);
                return;
            }
        }
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
                if(simulador.current.getCpuTime() == 0){
                    simulador.current.setStartTime(simulador.time);
                }
            }
            if(simulador.current != null){
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
