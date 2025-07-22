import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        final String EVENT_FILENAME = "events.dat";
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
        // Predefined values
        Event beachEvent = new Event("Beach Party", "Party with us at the beach!",
                "Sandy Cove Beach, NL", LocalDate.parse("2025-08-10"));
        Event graduationEvent = new Event("Graduation Party", "Celebrate our graduation of 2025!",
                "Keyin College, St. John's, NL", LocalDate.parse("2025-11-15"));
        Event marathonEvent = new Event("Marathon for Life", "Encourage healthier habits. Join us!",
                "Downtown St. John's", LocalDate.parse("2025-10-18"));

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
        // Create an Event list to store the Event objects be read from a file
        final ArrayList<Event> events = new ArrayList<>();

        // Read the event objects from a file
        try {
            FileInputStream eventFis = new FileInputStream(eventFilename);
            ObjectInputStream eventOis = new ObjectInputStream(eventFis);

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
        // Query statement to insert parts
        // The ID is set as SERIAL in the database, so we don't have to provide the ID in the INSERT statement
        final String query = "INSERT INTO parts (name, description, category, unit_price, quantity_on_hand) VALUES (?,?,?,?,?)";
        final ArrayList<Part> parts = new ArrayList<>();

        // Predefined values
        Part cpuPart = new Part( "AMD Ryzen 5700G",
                "AMD Ryzen 5700G Processor, Socket: AM4.", "cpu", 209.99, 10);
        Part mirrorPart = new Part("Chevrolet Spark 2019 Mirror (R)",
                "Right mirror for the 2019 Chevrolet Spark.", "mirror", 199.99, 2);
        Part absPipe = new Part("1/2\" ABS Pipe 8'",
                "Generic 8' 1/2\" ABS Pipe", "pipe", 15.99,20);

        parts.add(cpuPart);
        parts.add(mirrorPart);
        parts.add(absPipe);

        // Set up the connection and insert every part to the database
        try {
            Connection con = DatabaseConnection.getConnection();

            for(Part part : parts) {
                PreparedStatement statement = con.prepareStatement(query);

                statement.setString(1, part.getName());
                statement.setString(2, part.getDescription());
                statement.setString(3, part.getCategory());
                statement.setDouble(4, part.getUnitPrice());
                statement.setInt(5, part.getQuantityOnHand());

                statement.executeUpdate();

                System.out.println(part.getName() + " successfully added to the database.");
            }

        } catch (SQLException se) {
            System.out.println("Error while saving the parts to the database.");
            se.printStackTrace();
        }
    }

    private static void readDataDB() {
        // Prepare a query to read all the entries in the parts table
        final String query = "SELECT * FROM parts";
        final ArrayList<Part> parts = new ArrayList<>();

        // Prepare the connection to read from the database
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement statement = con.prepareStatement(query);

            ResultSet resSet = statement.executeQuery();

            System.out.println("\nReading data from the database...");

            // Read each record row until the end, and add each record to the parts list
            while(resSet.next()) {
                Part part = new Part(
                        resSet.getString("name"),
                        resSet.getString("description"),
                        resSet.getString("category"),
                        resSet.getDouble("unit_price"),
                        resSet.getInt("quantity_on_hand")
                        );

                // Use the ID that is stored in the database
                part.setId(resSet.getInt("id"));

                parts.add(part);
                System.out.println(part.getName() + " has been read and added to the list successfully.");
            }

            System.out.println("\nParts in the list:");
            for (Part part: parts) {
                System.out.println(part);
            }
        } catch (Exception e) {
            System.out.println("Error while reading the parts from the database.");
            e.printStackTrace();
        }
    }
}