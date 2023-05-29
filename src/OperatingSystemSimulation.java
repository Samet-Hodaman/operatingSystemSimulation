public class OperatingSystemSimulation {
    public static void main(String[] args) {
        // Step 1: Read the tasks file and Create list
        SortedListInterface<Task> sortedTaskList = FileIO.readTasksFromFile("src/tasks.txt");
        LinkedList<Task> taskList = new LinkedList<>();

        System.out.println("************************************");

        // Step 2: Print listed tasks
        System.out.println("Sorted tasks list:");
        for (int i = 1; i <= sortedTaskList.getLength(); i++) {
            Task current = sortedTaskList.getEntry(i);
            System.out.println(current);
            taskList.add(current);  // taskList is created in order to distribute tasks into the wait lines.
        }

        System.out.println();
        System.out.println("************************************");


        // Step 3: Create waiting priority line and pile of waiting burst time
        WaitingPriorityLine waitingPriorityLine = new WaitingPriorityLine();
        WaitingBurstTime waitingBurstTime = new WaitingBurstTime();


        // Step 4: Add tasks to waiting priority line and pile of waiting burst time
        waitingBurstTime.addTasks(taskList);
        waitingPriorityLine.addTasks(taskList);

        // Step 4.a: Print waiting priority line
        waitingPriorityLine.displayResults();
        waitingPriorityLine.simulation(5,0.3);

        System.out.println();
        System.out.println("************************************");


        // Step 4.b: Print waiting burst time pile
        waitingBurstTime.displayResults();
        waitingBurstTime.simulation(5,0.5);


        System.out.println("\n\u001B[32mAll tasks have been completed !\u001B[0m");
    }
}