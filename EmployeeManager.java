public static void main(String[] args) {

    // -------------------------------
    // ARGUMENT CHECK
    // -------------------------------
    // Ensure exactly one argument is provided and it's not empty
    if (args.length != 1 || args[0].isEmpty()) {
        System.out.println(Constants.MSG_ARG_ERROR); // "Error: Exactly one argument is required."
        System.out.println(Constants.USAGE);         // Show usage instructions
        return;
    }

    String userCommand = args[0]; // Store the command entered by the user

    // -------------------------------
    // COMMAND EXECUTION
    // -------------------------------
    // Determine which operation to perform based on the first character of the command
    switch (userCommand.charAt(0)) {

        // List all employees
        case 'l': 
            listEmployees(); 
            break;

        // Show a random employee
        case 's': 
            showRandomEmployee(); 
            break;

        // Add a new employee
        case '+': 
            if (userCommand.length() > 1) addEmployee(userCommand.substring(1));
            else System.out.println("Error: No employee name provided for addition."); 
            break;

        // Search for an employee
        case '?': 
            if (userCommand.length() > 1) searchEmployee(userCommand.substring(1));
            else System.out.println("Error: No employee name provided for search."); 
            break;

        // Count words in the file
        case 'c': 
            countWords(); 
            break;

        // Update an existing employee
        case 'u': 
            if (userCommand.length() > 1) updateEmployee(userCommand.substring(1));
            else System.out.println("Error: No employee name provided for update."); 
            break;

        // Delete an employee
        case 'd': 
            if (userCommand.length() > 1) deleteEmployee(userCommand.substring(1));
            else System.out.println("Error: No employee name provided for deletion."); 
            break;

        // Invalid command
        default:
            System.out.println(Constants.MSG_INVALID_COMMAND); // "Invalid command."
    }
}
