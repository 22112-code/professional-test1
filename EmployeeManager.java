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

        String command = args[0];

        switch (command.charAt(0)) {

            case 'l': listEmployees(); break;
            case 's': showRandomEmployee(); break;
            case '+': addEmployee(command.substring(1)); break;
            case '?': searchEmployee(command.substring(1)); break;
            case 'c': countWords(); break;
            case 'u': updateEmployee(command.substring(1)); break;
            case 'd': deleteEmployee(command.substring(1)); break;

            default:
                System.out.println(Constants.MSG_INVALID_COMMAND);
        }
    }

    // -------------------------------
    // FILE HELPER METHODS
    // -------------------------------
    private static String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_NAME));
        String data = reader.readLine();
        reader.close();
        return data;
    }

    private static void writeFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME));
        writer.write(content);
        writer.close();
    }

    private static void appendToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME, true));
        writer.write(content);
        writer.close();
    }

    // -------------------------------
    // APPLICATION COMMANDS
    // -------------------------------
    private static void listEmployees() {
        System.out.println("Loading data ...");
        try {
            String fileContent = readFile();
            String[] employees = fileContent.split(",");
            for (String emp : employees) System.out.println(emp.trim());
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
        System.out.println("Data Loaded.");
    }

    private static void showRandomEmployee() {
        System.out.println("Loading data ...");
        try {
            String fileContent = readFile();
            String[] employees = fileContent.split(",");
            int index = new Random().nextInt(employees.length);
            System.out.println("Random Employee: " + employees[index].trim());
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
        System.out.println("Data Loaded.");
    }

    private static void addEmployee(String name) {
        System.out.println("Loading data ...");
        try { appendToFile(", " + name); } 
        catch (Exception e) { System.out.println("Error writing to file."); }
        System.out.println("Data Loaded.");
    }

    private static void searchEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            String[] employees = readFile().split(",");
            boolean found = Arrays.stream(employees).anyMatch(emp -> emp.trim().equals(name));
            System.out.println(found ? "Employee found!" : "Employee not found.");
        } catch (Exception e) { System.out.println("Error reading file."); }
        System.out.println("Data Loaded.");
    }

    private static void countWords() {
        System.out.println("Loading data ...");
        try {
            char[] chars = readFile().toCharArray();
            boolean insideWord = false;
            int wordCount = 0;
            for (char ch : chars) {
                if (ch == ' ') {
                    if (!insideWord) { wordCount++; insideWord = true; } 
                    else insideWord = false;
                }
            }
            System.out.println(wordCount + " word(s) found, total chars: " + chars.length);
        } catch (Exception e) { System.out.println("Error reading file."); }
        System.out.println("Data Loaded.");
    }

    private static void updateEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            String[] employees = readFile().split(",");
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].trim().equals(name)) employees[i] = "Updated";
            }
            writeFile(String.join(",", employees));
        } catch (Exception e) { System.out.println("Error updating file."); }
        System.out.println("Data Updated.");
    }

    private static void deleteEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            String[] employees = readFile().split(",");
            List<String> list = new ArrayList<>();
            for (String emp : employees) if (!emp.trim().equals(name)) list.add(emp.trim());
            writeFile(String.join(",", list));
        } catch (Exception e) { System.out.println("Error deleting data."); }
        System.out.println("Data Deleted.");
    }
}
