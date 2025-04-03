package uk.co.ryanharrison.crudapi.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import uk.co.ryanharrison.crudapi.model.Shelf;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;


public class VendingMachine {
    private Map<String, Shelf> shelves = new LinkedHashMap<>();
    private Coins coins;
    private String availableShelfs = "01 02 03 04 05 06 07 08 09 10";
    private int shelfSize = 10;




    public VendingMachine() throws IOException {
    // updateShelfContent("A1", "Coke", 150); 
    }

    public void setCoins (Coins coins){
        this.coins = coins;
        // TODO: Löschen
        System.out.println(">>>>>>>> COINS ARE " + coins.toString());
    }

    // TODO: Änndern in "Stock"
    public void updateShelfContent(String shelfID, String drinkName, int price, int quantity) {
            if (availableShelfs.contains(shelfID) && quantity <= shelfSize){
                Shelf currentShelf = new Shelf(shelfID, drinkName, price, quantity);
                shelves.put(shelfID, currentShelf);
            }
            else {
                System.out.println("ERROR INVALID SHELF ID OR QUANTITY EXCEEDED");
            }
    }

    public void logContent(){
        for( Map.Entry<String,Shelf> entry : shelves.entrySet()){
            String key = entry.getKey();
            Shelf value = entry.getValue();
            System.out.println(">>>>>>>> SHELF CONTENTS ARE " + value.toString());
        }
    }

    public boolean removeDrinkWithSuccess(String shelfID){
        if (shelves.containsKey(shelfID)) {
            Shelf shelf = shelves.get(shelfID);
            if (shelf.getQuantity() > 0){
                shelf.decreaseQuantity();
                return true;
            } 
        } 
        return false;
    }


}
