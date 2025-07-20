import java.io.Serializable;

/**
 * Description: QAP 4 - Database and File Handling - Part Class
 * Author: Joseph Gallant
 * Date(s): July 17, 2025
 */

public class Part implements Serializable {
    private String name;
    private String description;
    private String category;
    private double unitPrice;
    private int quantityOnHand;

    public Part(String name, String description, String type, double unitPrice, int quantityOnHand){
        this.name = name;
        this.description = description;
        this.category = type;
        this.unitPrice = unitPrice;
        this.quantityOnHand = quantityOnHand;
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
}
