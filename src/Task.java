import java.time.LocalDateTime;
class Task implements Comparable<Task> {
    private final String name;
    private final int priority;
    final int burstTime;
    private final LocalDateTime arrivalDateTime;

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
        String space = "";
        for (int i = name.length(); i < 23; i++)
            space += " ";
        return getName() + space + "priority= " + getPriority() + "\t burstTime= " + getBurstTime()
                + "\t arrivalDateTime= " + arrivalDateTime.toString();
    }

    @Override
    public int compareDateTo(Task other) {
        if (arrivalDateTime.isAfter(other.arrivalDateTime))
            return 1;
        else if (arrivalDateTime.isEqual(other.arrivalDateTime))
            return 0;
        else
            return -1;
    }

    @Override
    public int comparePriorityTo(Task other) {
        if (this.priority > other.priority)
            return 1;
        else if (this.priority == other.priority)
            return 0;
        else
            return -1;
    }

    @Override
    public int compareBurstTimeTo(Task other) {
        if (this.burstTime > other.burstTime)
            return 1;
        else if (this.burstTime == other.burstTime)
            return 0;
        else
            return -1;
    }
}
