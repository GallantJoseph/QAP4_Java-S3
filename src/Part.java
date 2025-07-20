import java.io.Serializable;

/**
 * Description: QAP 4 - Database and File Handling - Part Class
 * Author: Joseph Gallant
 * Date(s): July 17, 2025
 */

public class Part implements Serializable {
    // Private attributes
    private int id;
    private String name;
    private String description;
    private String category;
    private double unitPrice;
    private int quantityOnHand;

    // Constructor
    public Part(String name, String description, String type, double unitPrice, int quantityOnHand){
        this.name = name;
        this.description = description;
        this.category = type;
        this.unitPrice = unitPrice;
        this.quantityOnHand = quantityOnHand;
    }

    // Getters and Setters
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

    public String getType() {
        return category;
    }

    public void setType(String type) {
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
