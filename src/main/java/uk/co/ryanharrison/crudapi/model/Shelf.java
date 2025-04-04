package uk.co.ryanharrison.crudapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Shelf {

    private String shelfID;
    private String drinkName;
    private int price;
    private int quantity;

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
