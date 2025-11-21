// File: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println(Constants.MSG_ARG_ERROR);
            System.out.println(Constants.USAGE);
            return;
        }

        switch (args[0].charAt(0)) {

            case 'l': listEmployees(); break;
            case 's': showRandomEmployee(); break;
            case '+': addEmployee(args[0].substring(1)); break;
            case '?': searchEmployee(args[0].substring(1)); break;
            case 'c': countWords(); break;
            case 'u': updateEmployee(args[0].substring(1)); break;
            case 'd': deleteEmployee(args[0].substring(1)); break;

            default: System.out.println(Constants.MSG_INVALID_COMMAND);
        }
    }

    // -------------------------------
    // FILE HELPER METHODS
    // -------------------------------
    private static String readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_NAME))) {
            return reader.readLine();
        }
    }

    private static void writeFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME))) {
            writer.write(content);
        }
    }

    private static void appendToFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME, true))) {
            writer.write(content);
        }
    }

    // -------------------------------
    // APPLICATION COMMANDS
    // -------------------------------
    private static void listEmployees() {
        System.out.println("Loading data ...");
        try {
            Arrays.stream(readFile().split(","))
                    .map(String::trim)
                    .forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
        System.out.println("Data Loaded.");
    }

    private static void showRandomEmployee() {
        System.out.println("Loading data ...");
        try {
            String[] employees = readFile().split(",");
            System.out.println("Random Employee: " + employees[new Random().nextInt(employees.length)].trim());
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
        System.out.println("Data Loaded.");
    }

    private static void addEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            appendToFile(", " + name);
        } catch (Exception e) {
            System.out.println("Error writing to file.");
        }
        System.out.println("Data Loaded.");
    }

    private static void searchEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            boolean found = Arrays.stream(readFile().split(","))
                    .map(String::trim)
                    .anyMatch(emp -> emp.equals(name));
            System.out.println(found ? "Employee found!" : "Employee not found.");
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
        System.out.println("Data Loaded.");
    }

    private static void countWords() {
        System.out.println("Loading data ...");
        try {
            char[] chars = readFile().toCharArray();
            int count = 0;
            boolean insideWord = false;
            for (char ch : chars) {
                if (ch == ' ') { count += insideWord ? 0 : 1; insideWord = !insideWord; }
            }
            System.out.println(count + " word(s) found, total chars: " + chars.length);
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
        System.out.println("Data Loaded.");
    }

    private static void updateEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            String[] employees = readFile().split(",");
            for (int i = 0; i < employees.length; i++)
                if (employees[i].trim().equals(name)) employees[i] = "Updated";
            writeFile(String.join(",", employees));
        } catch (Exception e) {
            System.out.println("Error updating file.");
        }
        System.out.println("Data Updated.");
    }

    private static void deleteEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            writeFile(String.join(",", Arrays.stream(readFile().split(","))
                    .map(String::trim)
                    .filter(emp -> !emp.equals(name))
                    .toArray(String[]::new)));
        } catch (Exception e) {
            System.out.println("Error deleting data.");
        }
        System.out.println("Data Deleted.");
    }
}
