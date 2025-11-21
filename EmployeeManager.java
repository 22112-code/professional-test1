public static void main(String[] args) {

    // Check number of arguments
    if (args.length != 1 || args[0].isEmpty()) {
        System.out.println(Constants.MSG_ARG_ERROR); // যেমন: "Error: Exactly one argument is required."
        System.out.println(Constants.USAGE);         // usage instructions
        return;
    }

    String command = args[0];

    switch (command.charAt(0)) {

        case 'l': listEmployees(); break;
        case 's': showRandomEmployee(); break;
        case '+': 
            if (command.length() > 1) addEmployee(command.substring(1));
            else System.out.println("Error: No employee name provided for addition."); 
            break;
        case '?': 
            if (command.length() > 1) searchEmployee(command.substring(1));
            else System.out.println("Error: No employee name provided for search."); 
            break;
        case 'c': countWords(); break;
        case 'u': 
            if (command.length() > 1) updateEmployee(command.substring(1));
            else System.out.println("Error: No employee name provided for update."); 
            break;
        case 'd': 
            if (command.length() > 1) deleteEmployee(command.substring(1));
            else System.out.println("Error: No employee name provided for deletion."); 
            break;

        default:
            System.out.println(Constants.MSG_INVALID_COMMAND); // যেমন: "Invalid command."
    }
}
