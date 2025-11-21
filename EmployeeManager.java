// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {

        // Check number of arguments
        if (args.length != 1) {
            System.out.println("Error: Exactly one argument is required.");
            printUsage();
            return;
        }

        String command = args[0];

        switch (command.charAt(0)) {

            // LIST EMPLOYEES
            case 'l':
                listEmployees();
                break;

            // SHOW RANDOM EMPLOYEE
            case 's':
                showRandomEmployee();
                break;

            // ADD EMPLOYEE
            case '+':
                addEmployee(command.substring(1));
                break;

            // SEARCH EMPLOYEE
            case '?':
                searchEmployee(command.substring(1));
                break;

            // COUNT WORDS
            case 'c':
                countWords();
                break;

            // UPDATE EMPLOYEE
            case 'u':
                updateEmployee(command.substring(1));
                break;

            // DELETE EMPLOYEE
            case 'd':
                deleteEmployee(command.substring(1));
                break;

            default:
                System.out.println("Invalid command.");
        }
    }

    // -------------------------------
    //        FILE HELPER METHODS
    // -------------------------------

    private static String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE_NAME))
        );
        String data = reader.readLine();
        reader.close();
        return data;
    }

    private static void writeFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME)
        );
        writer.write(content);
        writer.close();
    }

    private static void appendToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME, true)
        );
        writer.write(content);
        writer.close();
    }

    // -------------------------------
    //        APPLICATION COMMANDS
    // -------------------------------

    private static void listEmployees() {
        System.out.println("Loading data ...");
        try {
            String fileContent = readFile();
            String[] employees = fileContent.split(",");

            for (String emp : employees) {
                System.out.println(emp.trim());
            }
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

            Random random = new Random();
            int index = random.nextInt(employees.length);

            System.out.println("Random Employee: " + employees[index].trim());
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
            String fileContent = readFile();
            String[] employees = fileContent.split(",");

            boolean found = false;

            for (String emp : employees) {
                if (emp.trim().equals(name)) {
                    System.out.println("Employee found!");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee not found.");
            }

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
        System.out.println("Data Loaded.");
    }

    private static void countWords() {
        System.out.println("Loading data ...");
        try {
            String fileContent = readFile();
            char[] chars = fileContent.toCharArray();

            boolean insideWord = false;
            int wordCount = 0;

            for (char ch : chars) {
                if (ch == ' ') {
                    if (!insideWord) {
                        wordCount++;
                        insideWord = true;
                    } else {
                        insideWord = false;
                    }
                }
            }

            System.out.println(wordCount + " word(s) found, total chars: " + chars.length);

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
        System.out.println("Data Loaded.");
    }

    private static void updateEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            String fileContent = readFile();
            String[] employees = fileContent.split(",");

            for (int i = 0; i < employees.length; i++) {
                if (employees[i].trim().equals(name)) {
                    employees[i] = "Updated";
                }
            }

            writeFile(String.join(",", employees));

        } catch (Exception e) {
            System.out.println("Error updating file.");
        }
        System.out.println("Data Updated.");
    }

    private static void deleteEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            String fileContent = readFile();
            String[] employees = fileContent.split(",");

            List<String> list = new ArrayList<>();

            for (String emp : employees) {
                if (!emp.trim().equals(name)) {
                    list.add(emp.trim());
                }
            }

            writeFile(String.join(",", list));

        } catch (Exception e) {
            System.out.println("Error deleting data.");
        }
        System.out.println("Data Deleted.");
    }

    // -------------------------------
    //            HELPERS
    // -------------------------------
    private static void printUsage() {
        System.out.println("Usage: ");
        System.out.println("   l          → list all employees");
        System.out.println("   s          → show a random employee");
        System.out.println("   +name      → add a new employee");
        System.out.println("   ?name      → search employee");
        System.out.println("   c          → count words");
        System.out.println("   uname      → update employee");
        System.out.println("   dname      → delete employee");
    }
}
