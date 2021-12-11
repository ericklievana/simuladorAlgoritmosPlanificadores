public class Process{
    private String id;
    private int duration;
    private int arriveTime;
    private int priority;
    private int cpuTime;
    private int startTime;
    private int endTime;
    private float output;
    private float response;
    private float waste;
    private float penalty;
    private float wait;

    public Process(String id,float output,float response,float waste,float penalty,float wait){
        this.id = id;
        duration = 0;
        arriveTime = 0;
        priority = 0;
        cpuTime = 0;
        startTime = 0;
        endTime = 0;
        this.output = output;
        this.response = response;
        this.waste = waste;
        this.penalty = penalty;
        this.wait = wait;
    }

    public Process(String id,int priority,int duration,int arriveTime){
        this.id = id;
        this.duration = duration;
        this.arriveTime = arriveTime;
        this.priority = priority;
        cpuTime = 0;
    }

    public String getId(){
        return id;
    }

    public int getDuration(){
        return duration;
    }

    public int getArriveTime(){
        return arriveTime;
    }

    public int getPriority(){
        return priority;
    }

    public int getCpuTime(){
        return cpuTime;
    }

    public void reset(){
        cpuTime = 0;
        startTime = 0;
        endTime = 0;
    }

    public void compute(){
        cpuTime++;
    }

    public int getStartTime(){
        return startTime;
    }

    public int getEndTime(){
        return endTime;
    }

    public float getOutput(){
        return output;
    }

    public float getResponse(){
        return response;
    }

    public float getWaste(){
        return waste;
    }

    public float getPenalty(){
        return penalty;
    }

    public float getWait(){
        return wait;
    }

    public void setEndTime(int endTime){
        this.endTime = endTime;
    }

    public void setStartTime(int startTime){
        this.startTime = startTime;
    }

    public boolean isDone(){
        if(cpuTime >= duration) return true;
        return false;
    }

    public void calculate(){
        output = endTime - startTime;
        response = endTime - arriveTime;
        waste = response - duration;
        penalty = response / duration;
        wait = startTime - arriveTime;
    }

    public String[] imprimir(){
        String[] datos = new String[11];
        datos[0] = id;
        datos[1] = String.valueOf(priority);
        datos[2] = String.valueOf(arriveTime);
        datos[3] = String.valueOf(duration);
        datos[4] = String.valueOf(startTime);
        datos[5] = String.valueOf(endTime);
        datos[6] = String.valueOf(output);
        datos[7] = String.valueOf(response);
        datos[8] = String.valueOf(waste);
        datos[9] = String.valueOf(penalty);
        datos[10] = String.valueOf(wait);
        return datos;
    }

    public float performance(){
        float peso = output + response + waste + penalty + wait;
        peso /= 5;
        return peso;
    }

    public String toString(){
        return "ID: "+id+"\nPrioridad: "+priority+"\nDuracion: "+duration+"\nLlegada: "+arriveTime;
    }
}
