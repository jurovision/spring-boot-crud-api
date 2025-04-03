package uk.co.ryanharrison.crudapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String shelfID;
    private String drinkName;
    private int quantity;
    private int price;

    public Shelf(String shelfID, String drinkName, int price) {
        this.shelfID = shelfID;
        this.drinkName = drinkName;
        this.price = price;
        // TODO wahrscheinlich weg
        quantity = 10;
    }

    public String getShelfID() {
        return this.shelfID;
    }

    public void setShelfID(String shelfID) {
        this.shelfID = shelfID;
    }

    public String getDrinkName() {
        return this.drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
