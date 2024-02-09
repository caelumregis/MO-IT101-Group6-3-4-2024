import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class hoursWorked {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Ejat\\Desktop\\MotorPh\\attendance.csv");
            Scanner scanner = new Scanner(file);

            // Read and print header
            String headerLine = scanner.nextLine();
            System.out.println("Header: " + headerLine);

            // Prompt user for employee number
            Scanner userInputScanner = new Scanner(System.in);
            System.out.print("Enter employee number: ");
            String employeeNumber = userInputScanner.nextLine();

            // Read and print data
            boolean employeeFound = false;
            String firstName = "";
            String lastName = "";
            List<String[]> employeeRecords = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(employeeNumber)) { // Check if the employee number matches
                    employeeFound = true;
                    firstName = parts[1];
                    lastName = parts[2];
                    employeeRecords.add(parts);
                }
            }

            if (employeeFound) {
                System.out.println("Employee Number: " + employeeNumber);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);

                // Calculate total hours worked
                double totalHoursWorked = calculateTotalHours(employeeRecords);
                System.out.println("Total Hours Worked: " + totalHoursWorked + " hours");
            } else {
                System.out.println("Employee not found.");
            }

            scanner.close();
            userInputScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Method to calculate total hours worked given employee records
    private static double calculateTotalHours(List<String[]> employeeRecords) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        double totalHours = 0;
        try {
            for (String[] record : employeeRecords) {
                Date startDate = dateFormat.parse(record[3] + " " + record[4]);
                Date endDate = dateFormat.parse(record[3] + " " + record[5]);
                long durationMillis = endDate.getTime() - startDate.getTime();
                totalHours += durationMillis / (1000.0 * 60 * 60);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return totalHours;
    }

}

//"C:\\Users\\Ejat\\Desktop\\MotorPh\\attendance.csv"