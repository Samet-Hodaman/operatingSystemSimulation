import java.io.*;
import java.time.LocalDateTime;

public class FileIO {
    public Task[] readTasksFromFile(String fileName) {
        try {
            File file = new File(fileName);
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            
            while (true) {
                String[] parts;
                do {
                    String line;
                    if ((line = bReader.readLine()) == null) {
                        bReader.close();
                        return null;
                    }

                    parts = line.split(",");
                } while (parts.length != 4);

                String name = parts[0].trim();
                int burstTime = Integer.parseInt(parts[1].trim());
                String datePart = parts[2].trim();
                String clockPart = parts[3].trim();
                String[] date = datePart.split("/");
                String[] clock = clockPart.split(":");

                int day = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                int hour = Integer.parseInt(clock[0]);
                int minute = Integer.parseInt(clock[1]);
                int priority = getPriority(name);

                if (priority == 0)
                    System.out.println("HHEyeyeyeyeyeyeyeee there is an error occurred here (FileIO 36)");


                LocalDateTime arrivalDateTime = LocalDateTime.of(year,month,day,hour,minute);
                Task task = new Task(name,priority,burstTime,arrivalDateTime);

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private int getPriority(String name){
        switch (name) {
            case "Security Management" -> { return 6;}
            case "Process Management" -> { return 5;}
            case "Memory Management" -> { return 4;}
            case "User Management" -> { return 3;}
            case "Device Management" -> { return 2;}
            case "File Management" -> { return 1;}
            default -> { return 0;}
        }
    }
}
