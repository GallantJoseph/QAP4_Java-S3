import java.io.Serializable;

/**
 * Description: QAP 4 - Database and File Handling - Part Class
 * Author: Joseph Gallant
 * Date(s): July 17, 2025 - July 25, 2025
 */

public class Part implements Serializable {
    // Private attributes
    private static int idCounter = 1;
    private int id;
    private String name;
    private String description;
    private String category;
    private double unitPrice;
    private int quantityOnHand;

    // Constructors
    public Part(String name, String description, String category, double unitPrice, int quantityOnHand){
        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.category = category;
        this.unitPrice = unitPrice;
        this.quantityOnHand = quantityOnHand;
    }

    // If an ID is provided, assume it's the last value and assign it and increment the idCounter
    public Part(int id, String name, String description, String category, double unitPrice, int quantityOnHand){
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.unitPrice = unitPrice;
        this.quantityOnHand = quantityOnHand;

        idCounter = id + 1;
    }

    // Getters and Setters
    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Part.idCounter = idCounter;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String type) {
        this.category = type;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public void incrementQuantityOnHand(int quantity) {
        this.quantityOnHand += quantity;
    }

    // Methods
    @Override
    public String toString() {
        return String.format("[ID: %d], [Name: %s], [Description: %s], [Category: %s], [Unit Price: %.2f], [Quantity on Hand: %d]",
                this.id, this.name, this.description, this.category, this.unitPrice, this.quantityOnHand);
    }
}
