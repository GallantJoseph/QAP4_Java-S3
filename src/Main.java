import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

    private static void MainMenu(Scanner scanner) {
        final int MIN_OPT = 1;
        final int MAX_OPT = 5;
        final String EVENT_FILENAME = "event.txt";
        int option = 0;

        while (option != 5) {
            // Header
            System.out.println("\n              Main Menu               ");
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
                    saveDataFile(EVENT_FILENAME);
                    break;
                case 2:
                    readDataFile(EVENT_FILENAME);
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

    private static void saveDataFile(String eventFilename) {
        // Create new Events to save to a file
        Event beachEvent = new Event("Beach Party", "Sandy Cove Beach, NL", LocalDate.parse("2025-08-10"));
        Event graduationEvent = new Event("Graduation Party", "Keyin College, St. John's, NL", LocalDate.parse("2025-11-15"));
        Event marathonEvent = new Event("Marathon for Life", "Downtown St. John's", LocalDate.parse("2025-10-18"));

        // Save the Event objects to a file
        try {
            FileOutputStream eventFos = new FileOutputStream(eventFilename);
            ObjectOutputStream eventOos = new ObjectOutputStream(eventFos);

            eventOos.writeObject(beachEvent);
            eventOos.writeObject(graduationEvent);
            eventOos.writeObject(marathonEvent);

            eventFos.close();
            eventOos.close();

            System.out.println("\nEvent objects saved successfully to " + eventFilename + ":\n");
            System.out.println(beachEvent);
            System.out.println(graduationEvent);
            System.out.println(marathonEvent);
        } catch (IOException ioe) {
            System.out.println("\nError while saving the Event objects to " + eventFilename);
            ioe.printStackTrace();
        }
    }

    private static void readDataFile(String eventFilename) {

        // Read the event objects from a file
        try {
            FileInputStream eventFis = new FileInputStream(eventFilename);
            ObjectInputStream eventOis = new ObjectInputStream(eventFis);

            // Create an Event list to store the Event objects be read from a file
            ArrayList<Event> events = new ArrayList<>();

            while (true) {
                try {
                    events.add((Event) eventOis.readObject());
                } catch (ClassNotFoundException | EOFException e) {
                    break;
                }
            }

            eventFis.close();
            eventOis.close();

            System.out.println("\nEvent objects read successfully from " + eventFilename + ":\n");

            // Print each Event from the list
            events.forEach(System.out::println);
        }  catch (FileNotFoundException fnfe) {
            System.out.println("\nThe file " + eventFilename + " doesn't exist.");
        }
        catch (IOException ioe) {
            System.out.println("\nError while reading the Event objects from " + eventFilename);
            ioe.printStackTrace();
        }
    }

    private static void saveDataDB() {

    }

    private static void readDataDB() {

    }
}