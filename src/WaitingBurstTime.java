public class WaitingBurstTime {
    private final StackInterface<Task> pile;

    public WaitingBurstTime() {
        pile = new Stack<>();
    }

    public void addTasks(LinkedList<Task> taskList) {
        for (int i = 1; i <= taskList.getLength(); i++) { // Insertion Sort.
            Task burstest = taskList.getEntry(i); // find the task that has the greatest burst time.
            for (int j = i; j <= taskList.getLength(); j++) { // Compare the burstest task to the others task
                Task current = taskList.getEntry(j);
                if (burstest.compareBurstTimeTo(current) == 0) { // If both burst time equal, compare their arrival date time.
                    if (burstest.comparePriorityTo(current) > 0) {
                        burstest = taskList.replace(j, burstest);
                        taskList.replace(i, burstest);
                    }
                    if (burstest.comparePriorityTo(current) == 0) {
                        if (burstest.compareDateTo(current) < 0) {
                            burstest = taskList.replace(j, burstest);
                            taskList.replace(i, burstest);
                        }
                    }
                } // Change the burstest task
                if (burstest.compareBurstTimeTo(current) < 0) {
                    burstest = taskList.replace(j, burstest);
                    taskList.replace(i, burstest);
                }
            }
            pile.push(burstest);
        }
    }

    public void simulation(int numberOfExecution) {
        if (numberOfExecution >= 0 && numberOfExecution <= pile.getLength()) {
            while (!pile.isEmpty()) {
                if (numberOfExecution > pile.getLength())
                    numberOfExecution = pile.getLength();

                for (int i = 1; i <= numberOfExecution; i++) {

                    if (!pile.isEmpty())
                        pile.pop();
                }
                displayResults();
            }

        } else
            throw new IndexOutOfBoundsException("Illegal number is given to simulation() method.");
    }

    public void displayResults() {
        System.out.println();
        if (pile.isEmpty()) {
            System.out.println("\u001B[33mPile of Waiting Burst Time is completed.\u001B[0m");
            return;
        }
        System.out.println("\u001B[33mPile of Waiting Burst Time \u001B[37m(" + pile.getLength() + ")\u001B[0m");
        StackInterface<Task> tempPile = new Stack<>();
        while (!pile.isEmpty()) { // pop, show and add the tasks.
            Task current = pile.pop();
            System.out.println(current.toString());
            tempPile.push(current);
        }

        while (!tempPile.isEmpty()) // Adding (collecting) back.
            pile.push(tempPile.pop());

    }
}
