import java.io.Serializable;
import java.time.LocalDate;

/**
 * Description: QAP 4 - Database and File Handling - Event Class
 * Author: Joseph Gallant
 * Date(s): July 17, 2025 - July 25, 2025
 */

public class Event implements Serializable {
    // Private attributes
    private static int idCounter = 1;
    private int id;
    private String name;
    private String description;
    private String location;
    private LocalDate date;

    // Constructor
    public Event(String name, String description, String location, LocalDate date){
        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    // If an ID is provided, assume it's the last value and assign it and increment the idCounter
    public Event(int id, String name, String description, String location, LocalDate date){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;

        idCounter = id + 1;
    }

    // Getters and Setters
    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Event.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Methods
    @Override
    public String toString() {
        return String.format("[ID: %d], [Name: %s], [Description: %s], [Location: %s], [Date: %s]", this.id, this.name,this.description, this.location, this.date);
    }
}
