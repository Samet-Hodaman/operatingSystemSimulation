public class OperatingSystemSimulation {
    public static void main(String[] args) {
        // Step 1: Read the tasks file and Create list
        SortedListInterface<Task> sortedTaskList = FileIO.readTasksFromFile("src/tasks.txt");

        LinkedList<Task> taskList = new LinkedList<>();
        System.out.println("************************************");
        System.out.println();

        // Step 2: Print listed tasks
        System.out.println("Listed Tasks:");
        for (int i = 1; i <= sortedTaskList.getLength(); i++) {
            Task current = sortedTaskList.getEntry(i);
            taskList.add(current);
            System.out.println(current);
        }


        System.out.println();
        System.out.println("************************************");


        // Step 3: Create waiting priority line and pile of waiting burst time
        PriorityQueue<Task> waitingPriorityLine = new PriorityQueue<>();
        StackInterface<Task> waitingBurstTimePile = new Stack<>();

        // Step 4: Add tasks to waiting priority line and pile of waiting burst time
        addToPile(waitingBurstTimePile, taskList);
        for (int i = 1; i <= taskList.getLength(); i++)
            waitingPriorityLine.add(taskList.getEntry(i));

        // Step 4.a: Print waiting priority line
        System.out.println();
        System.out.println("Waiting Priority Line");
        while (!waitingPriorityLine.isEmpty()) {
            System.out.println("Remaining tasks: "+waitingPriorityLine.getSize());
            waitingPriorityLine.showData();
            System.out.println();
            for (int i = 1; i <= 5; i++)
                waitingPriorityLine.remove();
        }
        System.out.println();
        System.out.println("************************************");


        // Step 4.b: Print waiting burst time pile
        System.out.println();
        System.out.println("Pile of Waiting Burst Time");
        while (!waitingBurstTimePile.isEmpty()) {
            System.out.println("Remaining tasks: " + waitingBurstTimePile.getLength());
            waitingBurstTimePile.showData();
            System.out.println();
            for (int i = 1; i <= 5; i++)
                waitingBurstTimePile.pop();
        }
        System.out.println("All tasks completed !");
    }

    private static void addToPile(StackInterface<Task> waitingBurstTimePile, LinkedList<Task> taskList) {
        for (int i = 1; i <= taskList.getLength(); i++) {
            Task burstest = taskList.getEntry(i); // find the task that has the greatest burst time.
            for (int j = i; j <= taskList.getLength(); j++) { // Compare the burstest task to the others task
                Task current = taskList.getEntry(j);
                if (burstest.compareBurstTimeTo(current) == 0) { // If both burst time equal, compare their arrival date time.
                    if (burstest.compareDateTo(current) < 0) {
                        burstest = taskList.replace(j, burstest);
                        taskList.replace(i, burstest);
                    }
                } // Change the burstest task
                if (burstest.compareBurstTimeTo(current) > 0) {
                    burstest = taskList.replace(j, burstest);
                    taskList.replace(i, burstest);
                }
            }
            waitingBurstTimePile.push(burstest);
        }
    }
}