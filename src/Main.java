import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Description: QAP 4 - Database and File Handling - Main program and Menu
 * Author: Joseph Gallant
 * Date(s): July 17, 2025 - July 25, 2025
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Event> eventsList = new ArrayList<>();
        ArrayList<Part> partsList = new ArrayList<>();

        // Program header
        System.out.println("***** Database and File Handling *****\n");

        MainMenu(scanner, eventsList, partsList);

        System.out.println("\n*** Thank you for using my program. Have a nice day! ***");
    }

    private static void MainMenu(Scanner scanner, ArrayList<Event> eventsList, ArrayList<Part> partsList) {
        final int MIN_OPT = 1;
        final int MAX_OPT = 6;
        final String EVENT_FILENAME = "events.dat";
        int option = 0;

        while (option != MAX_OPT) {
            // Header
            System.out.println("\n              Main Menu               ");
            System.out.println("**************************************");
            System.out.println("1. Save data to a file");
            System.out.println("2. Read data from a file");
            System.out.println("3. Add an Event to the list");
            System.out.println("4. Save data to a database");
            System.out.println("5. Read data from a database");
            System.out.println("6. Quit");
            System.out.println("**************************************\n");
            System.out.print("Make a selection: ");

            // Input validation
            while (true){
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a number.");
                    scanner.next();
                }

                option = scanner.nextInt();
                scanner.nextLine();

                if (option >= MIN_OPT && option <= MAX_OPT){
                    break;
                } else {
                    System.out.println("Please enter a valid option: (" + MIN_OPT + "-" + MAX_OPT + ").");
                }
            }

            switch (option){
                case 1:
                    saveDataFile(EVENT_FILENAME, eventsList);
                    break;
                case 2:
                    eventsList = readDataFile(EVENT_FILENAME);
                    break;
                case 3:
                    addEvent(scanner, eventsList);
                    break;
                case 4:
                    saveDataDB(scanner);
                    break;
                case 5:
                    partsList = readDataDB();
                    break;
                default:
                    break;
            }
        }
    }

    // Save all the elements stored in the eventsList to a file
    private static void saveDataFile(String eventFilename, ArrayList<Event> eventsList) {
        // If there is nothing in the list, display a message and return to the main menu
        if (eventsList.isEmpty()) {
            System.out.println("\nNo Events to save. Add some first.");
            return;
        }

        // Save the Event objects to a file
        System.out.println("\nEvent objects saved successfully to " + eventFilename + ":\n");

        try {
            FileOutputStream eventFos = new FileOutputStream(eventFilename);
            ObjectOutputStream eventOos = new ObjectOutputStream(eventFos);

            // Write each Event from the list to the file
            for (Event event: eventsList){
                eventOos.writeObject(event);
                System.out.println(event);
            }

            eventFos.close();
            eventOos.close();
        } catch (IOException ioe) {
            System.out.println("\nError while saving the Event objects to " + eventFilename + ": " + ioe.getMessage());
        }
    }

    // Read the file and add each Event to a list that will be returned
    private static ArrayList<Event> readDataFile(String eventFilename) {
        ArrayList<Event> eventsList = new ArrayList<>();

        // Read the event objects from a file and add them to the list
        try {
            FileInputStream eventFis = new FileInputStream(eventFilename);
            ObjectInputStream eventOis = new ObjectInputStream(eventFis);

            // The Events should be stored with their ID in order
            // The setIdCounter can then take the last value that was read, to keep track of the last ID
            while (true) {
                try {
                    Event event = (Event) eventOis.readObject();
                    eventsList.add(event);
                } catch (ClassNotFoundException | EOFException e) {
                    break;
                }
            }

            // If there were Events objects in the file, use the last ID that was read
            // as a reference for the next ID to use
            if (!eventsList.isEmpty()){
                Event.setIdCounter(eventsList.getLast().getId() + 1);
            }

            eventFis.close();
            eventOis.close();

            // If the file contains Event object, show each of them
            if (!eventsList.isEmpty()){
                System.out.println("\nEvent objects read successfully from " + eventFilename + "\n");

                // Print each Event from the list
                eventsList.forEach(System.out::println);
            } else{
                System.out.println("\nNo Event objects stored in " + eventFilename + ".\n");
            }
        }  catch (FileNotFoundException fnfe) {
            System.out.println("\nThe file " + eventFilename + " doesn't exist.");
        }
        catch (IOException ioe) {
            System.out.println("\nError while reading the Event objects from " + eventFilename + ": " + ioe.getMessage());
        }

        return eventsList;
    }

    // Let the user add an Event manually with the use of a Scanner
    private static void addEvent(Scanner scanner, ArrayList<Event> eventsList){
        String name;
        String description;
        String location;
        LocalDate date;

        // Header
        System.out.println("\nNew Event Details:");
        System.out.println("--------------------------------------");

        System.out.print("Name: ");
        name = scanner.nextLine();

        System.out.print("Description: ");
        description = scanner.nextLine();

        System.out.print("Location: ");
        location = scanner.nextLine();

        System.out.print("Date (YYYY-MM-DD): ");

        // Date validation
        while (true){
            try {
                date = LocalDate.parse(scanner.nextLine());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please try again.");
            }
        }

        try {
            Event event = new Event(name, description, location, date);
            eventsList.add(event);

            System.out.println("\nEvent " + event.getName() + " added successfully.\n");
        } catch (Exception e) {
            System.out.println("\nError while adding the Event to the list: " + e.getMessage() + "\n");
        }
    }

    private static void saveDataDB(Scanner scanner) {
        // Query statement to insert the new Part
        // The ID is set as SERIAL in the database, so we don't have to provide the ID in the INSERT statement
        final String query = "INSERT INTO parts (name, description, category, unit_price, quantity_on_hand) VALUES (?,?,?,?,?)";

        // Allow the user to input the data to be inserted
        String name;
        String description;
        String category;
        double unitPrice;
        int quantityOnHand;

        // Header
        System.out.println("\nNew Part Details:");
        System.out.println("--------------------------------------");

        System.out.print("Name: ");
        name = scanner.nextLine();

        System.out.print("Description: ");
        description = scanner.nextLine();

        System.out.print("Category: ");
        category = scanner.nextLine();

        System.out.print("Unit Price: ");

        // Unit Price validation
        while (true) {
            while (!scanner.hasNextDouble()){
                System.out.println("Please enter a valid number.");
                scanner.next();
            }

            unitPrice = scanner.nextDouble();
            scanner.nextLine();

            if (unitPrice < 0) {
                System.out.println("Please enter a positive value.");
            } else
                break;
        }

        System.out.print("Quantity on Hand: ");

        // Quantity on Hand validation
        while (true) {
            while (!scanner.hasNextInt()){
                System.out.println("Please enter a valid number.");
                scanner.next();
            }

            quantityOnHand = scanner.nextInt();
            scanner.nextLine();

            if (quantityOnHand < 0) {
                System.out.println("Please enter a positive value.");
            } else
                break;
        }

        // Set up the connection and insert the part to the database
        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, name);
            statement.setString(2, description);
            statement.setString(3, category);
            statement.setDouble(4, unitPrice);
            statement.setInt(5, quantityOnHand);

            statement.executeUpdate();

            System.out.println("\n" + name + " added successfully to the database.\n");

        } catch (SQLException se) {
            System.out.println("\nError while saving the parts to the database: " + se.getMessage() + "\n");
        } catch (NullPointerException npe) {
            System.out.println("\nError while connecting to the database: " + npe.getMessage() + "\n");
        }
    }

    private static ArrayList<Part> readDataDB() {
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
                        resSet.getInt("id"),
                        resSet.getString("name"),
                        resSet.getString("description"),
                        resSet.getString("category"),
                        resSet.getDouble("unit_price"),
                        resSet.getInt("quantity_on_hand")
                        );

                parts.add(part);
                System.out.println(part.getName() + " has been read and added to the list successfully.");
            }

            // If there are Part rows in the database, use the last ID that was read
            // as a reference for the next ID to use
            if (!parts.isEmpty()){
                Part.setIdCounter(parts.getLast().getId() + 1);
            }

            System.out.println("\nParts in the list:");
            for (Part part: parts) {
                System.out.println(part);
            }
        } catch (SQLException e) {
            System.out.println("Error while reading the parts from the database: " + e.getMessage());
        } catch (NullPointerException npe) {
            System.out.println("\nError while connecting to the database: " + npe.getMessage() + "\n");
        }

        return parts;
    }
}