import java.util.ArrayList;

public class RoundRobin implements Runable{
    private ArrayList<Process> cola;
    private Simulator simulador;
    private InformationGui info;
    private int quantum;
    private int contador;

    public RoundRobin(Simulator simulador,InformationGui info){
        this.simulador = simulador;
        this.info = info;
        cola = new ArrayList<Process>();
        contador = 0;
    }

    public void encolar(Process proceso){
        cola.add(proceso);
    }

    public void setQuantum(int quantum){
        this.quantum = quantum;
    }

    public void run(){
        setQuantum(simulador.getQuantum());
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
            simulador.compute();
            contador++;
            if(contador == quantum){
                contador = 0;
                cola.add(simulador.current);
                simulador.current = cola.remove(0);
                if(simulador.current != null){
                    if(simulador.current.getCpuTime() == 0){
                        simulador.current.setStartTime(simulador.time);
                    }
                }
            }
            info.setLabelTiempo(String.valueOf(simulador.time));
            if(simulador.current != null){
                if(simulador.current.isDone()){
                    simulador.current.setEndTime(simulador.time);
                    simulador.current = null;
                    contador = 0;
                }
            }
        }while(!simulador.isFinish());
    }
}
