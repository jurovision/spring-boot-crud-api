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
    public Coins machineCoins;
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

    public Coins getCoins (){
        return machineCoins;
    }

    // TODO: Änndern in "Stock"
    public void updateShelfContent(String shelfID, String drinkName, int price, int quantity) {
            if (availableShelfs.contains(shelfID) && quantity <= shelfSize){
                Shelf currentShelf = new Shelf(shelfID, drinkName, price, quantity);
                shelves.put(shelfID, currentShelf);
            }
            // Exception
            else {
                System.out.println("ERROR INVALID SHELF ID OR QUANTITY EXCEEDED");
            }
    }

    public void logContent(){
        System.out.println("-------- Machine contents are: "); 
        for( Map.Entry<String,Shelf> entry : shelves.entrySet()){
            Shelf value = entry.getValue();
            System.out.println(value.toString());
        }
    }

    public boolean removeDrinkWithSuccess(String shelfID, Coins purChaseCoins){
        if (shelves.containsKey(shelfID)) {
            Shelf shelf = shelves.get(shelfID);

            if (shelf.getQuantity() > 0){
                Coins changeCoins = new Coins();
                Coins tempChangeCalculationCoins = machineCoins;
                int totalCoinsValue = calculateCoinsValue(purChaseCoins);
                int drinkPrice = shelf.getPrice();
                int openChange =  totalCoinsValue - drinkPrice;

                // WECHSELSGELD
                while (openChange >= 0){
                    while (openChange % 200 != openChange && openChange > 0){
                        if (tempChangeCalculationCoins.getTwoEuroCoins() > 0){
                            tempChangeCalculationCoins.setTwoEuroCoins(tempChangeCalculationCoins.getTwoEuroCoins() - 1);
                            openChange -= 200;
                            changeCoins.setTwoEuroCoins(changeCoins.getTwoEuroCoins() + 1);
                        } else {
                            break;
                        }
                    }
                    while (openChange % 100 != openChange && openChange > 0){
                        if (tempChangeCalculationCoins.getOneEuroCoins() > 0){
                            tempChangeCalculationCoins.setOneEuroCoins(tempChangeCalculationCoins.getOneEuroCoins() - 1);
                            openChange -= 100;
                            changeCoins.setOneEuroCoins(changeCoins.getOneEuroCoins() + 1);

                        } else {
                            break;
                        }
                    }
                    while (openChange % 50 != openChange && openChange > 0){
                        if (tempChangeCalculationCoins.getFiftyCentCoins() > 0){
                            tempChangeCalculationCoins.setFiftyCentCoins(tempChangeCalculationCoins.getFiftyCentCoins() - 1);
                            openChange -= 50;
                            changeCoins.setFiftyCentCoins(changeCoins.getFiftyCentCoins() + 1);
                        } else {
                            break;
                        }
                    }
                    while (openChange % 20 != openChange && openChange > 0){
                        if (tempChangeCalculationCoins.getTwentyCentCoins() > 0){
                            tempChangeCalculationCoins.setTwentyCentCoins(tempChangeCalculationCoins.getTwentyCentCoins() - 1);
                            openChange -= 20;
                            changeCoins.setTwentyCentCoins(changeCoins.getTwentyCentCoins() + 1);
                        } else {
                            break;
                        }
                    }
                    while (openChange % 10 != openChange && openChange > 0){
                        if (tempChangeCalculationCoins.getTenCentCoins() > 0){
                            tempChangeCalculationCoins.setTenCentCoins(tempChangeCalculationCoins.getTenCentCoins() - 1);
                            openChange -= 10;
                            changeCoins.setTenCentCoins(changeCoins.getTenCentCoins() + 1);
                        } else {
                            break;
                        }
                    }

                    break;
                    }
                    if (openChange == 0){
                        // gezahlte Münzen der Macshine hinzufügen
                        machineCoins = tempChangeCalculationCoins;

                        machineCoins.setTenCentCoins(machineCoins.getTenCentCoins() + purChaseCoins.getTenCentCoins());
                        machineCoins.setTwentyCentCoins(machineCoins.getTwentyCentCoins() + purChaseCoins.getTwentyCentCoins());
                        machineCoins.setFiftyCentCoins(machineCoins.getFiftyCentCoins() + purChaseCoins.getFiftyCentCoins());
                        machineCoins.setOneEuroCoins(machineCoins.getOneEuroCoins() + purChaseCoins.getOneEuroCoins());
                        machineCoins.setTwoEuroCoins(machineCoins.getTwoEuroCoins() + purChaseCoins.getTwoEuroCoins());

                        shelf.decreaseQuantity();
                        System.out.println(">>>>>>>>>> Enjoy your " + shelf.getDrinkName() + " drink! You gave " + 
                        (totalCoinsValue * 0.01) + " Euro and the drink cost was " + (drinkPrice * 0.01) + " Euro. Your change is: " + changeCoins.toString());
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
