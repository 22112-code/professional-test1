// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Check arguments
        if (args.length == 0) {
            System.out.println("Error: No argument provided.");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));

                String line = r.readLine();
                String[] employees = line.split(",");

                for (String emp : employees) {
                    System.out.println(emp);
                }

                r.close();
            } catch (Exception e) {
                System.out.println("Error loading file.");
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));

                String line = r.readLine();
                System.out.println(line);

                String[] employees = line.split(",");
                Random rand = new Random();

                int idx = rand.nextInt(employees.length);
                System.out.println(employees[idx]);

                r.close();
            } catch (Exception e) {
                System.out.println("Error loading file.");
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) {

            System.out.println("Loading data ...");
            try {
                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt", true));

                String name = command.substring(1);
                w.write(", " + name);
                w.close();
            } catch (Exception e) {
                System.out.println("Error writing file.");
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("?")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));

                String line = r.readLine();
                String[] employees = line.split(",");

                String search = command.substring(1);
                boolean found = false;

                for (String emp : employees) {
                    if (emp.equals(search)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Employee not found.");
                }

                r.close();
            } catch (Exception e) {
                System.out.println("Error reading file.");
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("c")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));

                String line = r.readLine();
                char[] chars = line.toCharArray();

                boolean inWord = false;
                int count = 0;

                for (char ch : chars) {
                    if (ch == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }

                System.out.println(count + " word(s) found " + chars.length);

                r.close();
            } catch (Exception e) {
                System.out.println("Error reading file.");
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("u")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));

                String line = r.readLine();
                String[] employees = line.split(",");

                String name = command.substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(name)) {
                        employees[i] = "Updated";
                    }
                }

                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt"));

                w.write(String.join(",", employees));
                w.close();
                r.close();

            } catch (Exception e) {
                System.out.println("Error updating file.");
            }
            System.out.println("Data Updated.");

        } else if (command.startsWith("d")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));

                String line = r.readLine();
                String[] employees = line.split(",");

                String name = command.substring(1);
                List<String> list = new ArrayList<>(Arrays.asList(employees));

                list.remove(name);

                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt"));

                w.write(String.join(",", list));

                w.close();
                r.close();

            } catch (Exception e) {
                System.out.println("Error deleting data.");
            }
            System.out.println("Data Deleted.");

        } else {
            System.out.println("Invalid command.");
        }
    }
}
