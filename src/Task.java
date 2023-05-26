import java.time.LocalDateTime;
class Task {
    private String name;
    private int priority;
    private int burstTime;
    private LocalDateTime arrivalDateTime;
    public Task(){
        this(null,0,0,null);
    }

    LocalDateTime datee = LocalDateTime.of(2023,5,26,15,30);

    public Task(String name, int priority, int burstTime, LocalDateTime arrivalDateTime) {
        this.name = name;
        this.priority = priority;
        this.burstTime = burstTime;
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    @Override
    public String toString() {
        return "Task [name=" + name + ", priority=" + priority + ", burstTime=" + burstTime + ", arrivalDateTime="
                + arrivalDateTime + "]";
    }
}
