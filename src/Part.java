import java.io.Serializable;

/**
 * Description: QAP 4 - Database and File Handling - Part Class
 * Author: Joseph Gallant
 * Date(s): July 17, 2025
 */

public class Part implements Serializable {
    // Private attributes
    private String name;
    private String description;
    private String category;
    private double unitPrice;
    private int quantityOnHand;

    // Constructor
    public Part(String name, String description, String category, double unitPrice, int quantityOnHand){
        this.name = name;
        this.description = description;
        this.category = category;
        this.unitPrice = unitPrice;
        this.quantityOnHand = quantityOnHand;
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
        return String.format("[Name: %s], [Description: %s], [Category: %s], [Unit Price: %.2f], [Quantity on Hand: %d]",
                this.name, this.description, this.category, this.unitPrice, this.quantityOnHand);
    }
}
