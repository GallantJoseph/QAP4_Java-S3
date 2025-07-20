import java.util.Scanner;

/**
 * Description: QAP 4 - Database and File Handling - Main program and Menu
 * Author: Joseph Gallant
 * Date(s): July 17, 2025
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Program header
        System.out.println("***** Database and File Handling *****\n");

        MainMenu(scanner);

        System.out.println("\n*** Thank you for using my program. Have a nice day! ***");
    }

    public static void MainMenu(Scanner scanner) {
        final int MIN_OPT = 1;
        final int MAX_OPT = 5;
        int option = 0;

        while (option != 5) {
            // Header
            System.out.println("              Main Menu               ");
            System.out.println("**************************************");
            System.out.println("1. Save data to a file");
            System.out.println("2. Read data from a file");
            System.out.println("3. Save data to a database");
            System.out.println("4. Read data from a database");
            System.out.println("5. Quit");
            System.out.println("**************************************\n");
            System.out.print("Make a selection: ");

            // Input validation
            while (true){
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a number.");
                    scanner.next();
                }

                option = scanner.nextInt();

                if (option >= MIN_OPT && option <= MAX_OPT){
                    break;
                } else {
                    System.out.println("Please enter a valid option: (" + MIN_OPT + "-" + MAX_OPT + ").");
                }
            }

            switch (option){
                case 1:
                    saveDataFile();
                    break;
                case 2:
                    readDataFile();
                    break;
                case 3:
                    saveDataDB();
                    break;
                case 4:
                    readDataDB();
                    break;
                default:
                    break;
            }
        }
    }

    private static void readDataDB() {

    }

    private static void saveDataDB() {

    }

    private static void readDataFile() {

    }

    private static void saveDataFile() {

    }
}