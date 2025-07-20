import java.io.Serializable;
import java.time.LocalDate;

/**
 * Description: QAP 4 - Database and File Handling - Event Class
 * Author: Joseph Gallant
 * Date(s): July 17, 2025
 */

public class Event implements Serializable {
    private String name;
    private String location;
    private LocalDate date;

    public Event(String name, String location, LocalDate date){
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("[Name: " + this.name + "], [Location: " + this.location + "], [Date: " + this.date + "]");
    }
}
