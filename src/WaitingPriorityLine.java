public class WaitingPriorityLine {
    private final PriorityQueueInterface<Task> line;

    public WaitingPriorityLine() {
        line = new PriorityQueue<>();
    }

    public void addTasks(LinkedList<Task> taskList) {
        for (int i = 1; i <= taskList.getLength(); i++)
            line.add(taskList.getEntry(i));
    }

    public void simulation(int numberOfExecution) {

        if (numberOfExecution >= 0 && numberOfExecution <= line.getSize()) {
            while (!line.isEmpty()) {
                if (numberOfExecution > line.getSize())
                    numberOfExecution = line.getSize();

                for (int i = 1; i <= numberOfExecution; i++) {
                    if (!line.isEmpty())
                        line.remove();
                }
                displayResults();
            }

        } else
            throw new IndexOutOfBoundsException("Illegal number is given to simulation() method.");
    }

    public void displayResults() {
        System.out.println();
        if (line.isEmpty()) {
            System.out.println("\u001B[33mWaiting Priority Line is completed.\u001B[0m");
            return;
        }
        System.out.println("\u001B[33mWaiting Priority Line \u001B[37m(" + line.getSize() + ")\u001B[0m");
        LinkedList<Task> tempList = new LinkedList<>();
        while (!line.isEmpty()) { // remove, show and add the tasks.
            Task current = line.remove();
            System.out.println(current.toString());
            tempList.add(current);
        }

        while (!tempList.isEmpty()) // Adding (collecting) back the tasks.
            line.add(tempList.remove(1));

    }

}
