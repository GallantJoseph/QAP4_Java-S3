import java.io.Serializable;
import java.time.LocalDate;

/**
 * Description: QAP 4 - Database and File Handling - Event Class
 * Author: Joseph Gallant
 * Date(s): July 17, 2025
 */

public class Event implements Serializable {
    // Private attributes
    private String name;
    private String description;
    private String location;
    private LocalDate date;

    // Constructor
    public Event(String name, String description, String location, LocalDate date){
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    // Getters and Setters
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
        return String.format("[Name: %s], [Location: %s], [Date: %s]", this.name, this.location, this.date);
    }
}
