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
    private Coins machineCoins;
    private String availableShelfs = "01 02 03 04 05 06 07 08 09 10";
    private int shelfSize = 10;




    public VendingMachine() throws IOException {
    // updateShelfContent("A1", "Coke", 150); 
    }

    public void setCoins (Coins coins){
        this.machineCoins = coins;
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
            System.out.println("-------- SHELF CONTENTS ARE " + value.toString());
        }
    }

    public boolean removeDrinkWithSuccess(String shelfID, Coins purChaseCoins){
        if (shelves.containsKey(shelfID)) {
            Shelf shelf = shelves.get(shelfID);

            if (shelf.getQuantity() > 0){
                Coins changeCoins = new Coins();
                int totalCoinsValue = calculateCoinsValue(purChaseCoins);
                int drinkPrice = shelf.getPrice();
                int openChange =  totalCoinsValue - drinkPrice;

                // WECHSELSGELD
                while (openChange >= 0){
                    while (openChange % 200 != openChange && openChange > 0){
                        if (machineCoins.getTwoEuroCoins() > 0){
                            machineCoins.setTwoEuroCoins(machineCoins.getTwoEuroCoins() - 1);
                            openChange -= 200;
                            changeCoins.setTwoEuroCoins(changeCoins.getTwoEuroCoins() + 1);
                        } else {
                            break;
                        }
                    }
                    while (openChange % 100 != openChange && openChange > 0){
                        if (machineCoins.getOneEuroCoins() > 0){
                            machineCoins.setOneEuroCoins(machineCoins.getOneEuroCoins() - 1);
                            openChange -= 100;
                            changeCoins.setOneEuroCoins(changeCoins.getOneEuroCoins() + 1);

                        } else {
                            break;
                        }
                    }
                    while (openChange % 50 != openChange && openChange > 0){
                        if (machineCoins.getFiftyCentCoins() > 0){
                            machineCoins.setFiftyCentCoins(machineCoins.getFiftyCentCoins() - 1);
                            openChange -= 50;
                            changeCoins.setFiftyCentCoins(changeCoins.getFiftyCentCoins() + 1);
                        } else {
                            break;
                        }
                    }
                    while (openChange % 20 != openChange && openChange > 0){
                        if (machineCoins.getTwentyCentCoins() > 0){
                            machineCoins.setTwentyCentCoins(machineCoins.getTwentyCentCoins() - 1);
                            openChange -= 20;
                            changeCoins.setTwentyCentCoins(changeCoins.getTwentyCentCoins() + 1);
                        } else {
                            break;
                        }
                    }
                    while (openChange % 10 != openChange && openChange > 0){
                        if (machineCoins.getTenCentCoins() > 0){
                            machineCoins.setTenCentCoins(machineCoins.getTenCentCoins() - 1);
                            openChange -= 10;
                            changeCoins.setTenCentCoins(changeCoins.getTenCentCoins() + 1);
                        } else {
                            break;
                        }
                    }

                    break;
                    }
                    if (openChange == 0){

                        shelf.decreaseQuantity();
                        System.out.println(">>>>>>>>>> Enjoy your " + shelf.getDrinkName() + " drink! You gave " + (totalCoinsValue * 0.01) + " Euro and the drink cost was " + (drinkPrice * 0.01) + " Euro. Your change is: " + changeCoins.toString());
                        return true;
                }
            }

                
        }
        System.out.println("FAIL");
        return false;
    }

    public int calculateCoinsValue(Coins coins) {
        int totalValue = 0;
        totalValue += coins.getTenCentCoins() * 10;
        totalValue += coins.getTwentyCentCoins() * 20;
        totalValue += coins.getFiftyCentCoins() * 50;
        totalValue += coins.getOneEuroCoins() * 100;
        totalValue += coins.getTwoEuroCoins() * 200;
        return totalValue;
    }

}
