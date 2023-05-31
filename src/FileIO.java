import java.io.*;
import java.time.LocalDateTime;

public class FileIO {
    public static SortedList<Task> readTasksFromFile(String fileName) {
        try {
            File file = new File(fileName);
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            SortedList<Task> sortedTaskList = new SortedList<>();
            while (true) {
                String[] parts;
                do {
                    String line;
                    if ((line = bReader.readLine()) == null) {
                        bReader.close();
                        return sortedTaskList;
                    }
                    parts = line.split(",");
                } while (parts.length != 4);

                String name = parts[0].trim();
                int burstTime = Integer.parseInt(parts[1].trim());
                String datePart = parts[2].trim();
                String clockPart = parts[3].trim();
                String[] date = datePart.split("/");
                String[] clock = clockPart.split(":");

                int day = Integer.parseInt(date[0]);   //Parsing the arrival date time
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                int hour = Integer.parseInt(clock[0]);
                int minute = Integer.parseInt(clock[1]);

                int priority = 0;
                switch (name) {
                    case "security management" -> priority = 6;
                    case "process management" -> priority = 5;
                    case "memory management" -> priority = 4;
                    case "user management" -> priority = 3;
                    case "device management" -> priority = 2;
                    case "file management" -> priority = 1;
                    default -> { }
                }

                LocalDateTime arrivalDateTime = LocalDateTime.of(year,month,day,hour,minute);
                Task task = new Task(name,priority,burstTime,arrivalDateTime);
                sortedTaskList.add(task);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
