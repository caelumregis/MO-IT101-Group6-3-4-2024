import java.io.File; //imports the 'File' class from the 'java.io' package allowing the program to work with files
import java.io.FileNotFoundException; //This line imports the FileNotFoundException class, which is an exception type thrown when an attempt to open the file fails because the file does not exist or is inaccessible.
import java.text.ParseException; //This line imports the ParseException class, which is an exception type thrown when an error occurs during parsing using SimpleDateFormat.
import java.text.SimpleDateFormat; //This line imports the SimpleDateFormat class, which allows formatting and parsing of dates in a specific pattern.
import java.util.ArrayList; //This line imports the ArrayList class from the java.util package, which is used to store a dynamic list of elements.
import java.util.Date; //This line imports the Date class, which represents a specific instant in time, with millisecond precision.
import java.util.List; //This line imports the List interface from the java.util package, which represents an ordered collection of elements.
import java.util.Scanner; //This line imports the Scanner class from the java.util package, which is used to parse primitive types and strings from input sources.

public class hoursWorked {
    public static void main(String[] args) {
        //This line starts a try block to handle potential exceptions.
        try {
            File file = new File("C:\\Users\\Ejat\\Desktop\\MotorPh\\attendance.csv"); //This line creates a new File object representing the CSV file located at the specified path.
            Scanner scanner = new Scanner(file); //This line creates a new Scanner object to read from the file.

            // Read and print header
            String headerLine = scanner.nextLine(); //This line reads the header line from the CSV file.
            System.out.println("Header: " + headerLine); //This line prints the header line to the console.

            // Prompt user for employee number
            Scanner userInputScanner = new Scanner(System.in); //This line creates a new Scanner object to read user input from the console.
            System.out.print("Enter employee number: "); //This line prompts the user to enter an employee number.
            String employeeNumber = userInputScanner.nextLine(); //This line reads the employee number entered by the user.

            // Read and print data
            boolean employeeFound = false; //This line initializes a boolean variable to track whether the employee was found in the CSV file
            String firstName = ""; //This line initializes a string variable to store the first name of the employee.
            String lastName = ""; //This line initializes a string variable to store the last name of the employee.
            List<String[]> employeeRecords = new ArrayList<>(); //This line initializes an ArrayList to store the records of the employee found in the CSV file.

            //This line starts a while loop to iterate through each line of the CSV file.
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // This line reads a line from the CSV file.
                String[] parts = line.split(","); //This line splits the line into an array of strings using a comma as the delimiter.
                if (parts[0].equals(employeeNumber)) { // This line checks if the first element of the array (employee number) matches the input employee number.
                    employeeFound = true; //This line sets the employeeFound flag to true since the employee was found.
                    firstName = parts[2]; //This line stores the first name of the employee.
                    lastName = parts[1]; //This line stores the last name of the employee.
                    employeeRecords.add(parts); //This line adds the array of strings representing the employee's record to the list of employee records.
                }
            }

            if (employeeFound) {    //This line checks if the employee was found in the CSV file
                System.out.println("Employee Number: " + employeeNumber); //This line prints the employee number.
                System.out.println("First Name: " + firstName); //This line prints the first name of the employee.
                System.out.println("Last Name: " + lastName); //This line prints the last name of the employee.

                // Calculate total hours worked
                double totalHoursWorked = calculateTotalHours(employeeRecords); //This line calculates the total hours worked by the employee using the calculateTotalHours method
                System.out.println("Total Hours Worked: " + totalHoursWorked + " hours"); //This line prints the total hours worked by the employee.
            } else {
                System.out.println("Employee not found."); //This line is the else block for when the employee is not found in the CSV file.
            }

            scanner.close(); //this line closes the Scanner object used to read from the file.
            userInputScanner.close(); //This line closes the Scanner object used to read user input.
        } catch (FileNotFoundException e) { //This line catches a FileNotFoundException if the file specified cannot be found.
            System.out.println("File not found: " + e.getMessage()); //This line prints an error message indicating that the file was not found.
        }
    }

    // Method to calculate total hours worked given employee records
    private static double calculateTotalHours(List<String[]> employeeRecords) { //This line declares the calculateTotalHours method, which calculates the total hours worked given a list of employee records.
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm"); //This line creates a SimpleDateFormat object with the specified date and time pattern.
        double totalHours = 0; //This line initializes a variable to store the total hours worked.
        try {
            for (String[] record : employeeRecords) { //This line starts a for loop to iterate through each employee record.
                Date startDate = dateFormat.parse(record[3] + " " + record[4]); //This line parses the start date and time from the employee record.
                Date endDate = dateFormat.parse(record[3] + " " + record[5]); //This line parses the end date and time from the employee record.
                long durationMillis = endDate.getTime() - startDate.getTime(); //This line calculates the duration between the start and end times in milliseconds.
                totalHours += durationMillis / (1000.0 * 60 * 60); //This line calculates the total hours worked by converting milliseconds to hours and adding it to the total.
            }
        } catch (ParseException e) { //This line catches a ParseException if there is an error parsing the dates.
            e.printStackTrace(); //This line prints the stack trace of the exception.
        }
        return totalHours; //This line returns the total hours worked by the employee.
    }

}

//"C:\\Users\\Ejat\\Desktop\\MotorPh\\attendance.csv"
