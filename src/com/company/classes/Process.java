package com.company.classes;

public class Process{
    private int id;
    private String name;
    private int priority;
    private int time; //число работы в тактах
    private int memory; //кол-во памяти
    private int arrivalTime; //время захода
    private int burstTime; // Общее время, необходимое центральному процессору для выполнения всего процесса (не включает в себя время ожидания)
    private State state;
    private MemoryBlock memoryBlock;
    private Resource resource;

    //заменить все цифры на константы
    public Process(int id) {
        this.id = id;
        this.memory = Utils.getRandomInteger(10,Configuration.memoryVolume/4);  //кол-во памяти, требуемое для процесса
        this.priority = Utils.getRandomInteger( Configuration.maxPriority);
        this.time = Utils.getRandomInteger(10,100);
        this.arrivalTime = TactGenerator.getTime();
        this.burstTime = 0;
        this.name = "P" + this.id;
        this.state = State.New;
    }

    public Process() {
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getTime() {
        return time;
    }

    public int getMemory() {
        return memory;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public State getState() {
        return state;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setState(State state) {
        this.state = state;
        if(state == State.Running){
            // Start timer

        }
        if(state == State.Terminated){        }
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Resource getCore() {
        return resource;
    }

    public void setCore(Resource resource) {
        this.resource = resource;
    }

    public MemoryBlock getMemoryBlock() {
        return memoryBlock;
    }

    public void setMemoryBlock(MemoryBlock memoryBlock) {
        this.memoryBlock = memoryBlock;
    }

    public void releaseMemory(){
        this.memoryBlock.availableMemory += this.memory;
    }

    @Override
    public String toString() {
        return id +
                "{ name='" + name + '\'' +
                ", priority=" + priority +
                ", time=" + time +
                ", memory=" + memory +
                ", timeIn=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", state=" + state +
                (resource != null ? (", core number=" + resource.getNumber()) : "") +
                '}' + "\n";
    }

    //public static Comparator<Process> byPriority = ((o1, o2) -> o1.priority - o2.priority);
    //public static Comparator<Process> byArrivalTime = ((o1, o2) -> o1.arrivalTime - o2.arrivalTime);
}
