package uk.co.ryanharrison.crudapi;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.ryanharrison.crudapi.model.VendingMachine;
import uk.co.ryanharrison.crudapi.model.Coins;

@SpringBootApplication
public class SpringBootCrudApiApplication {

    public static void main(String[] args) {

        // Instanz erzeugen
        VendingMachine newVendingMachine = new VendingMachine();
        newVendingMachine.setLocation("Cottbus Landesmuseum");

        // Maschine mit Münzen befüllen
        Coins coins = new Coins();
        coins.setTenCentCoins(10);
        coins.setTwentyCentCoins(10);
        coins.setFiftyCentCoins(10);
        coins.setOneEuroCoins(10);
        coins.setTwoEuroCoins(10);
        newVendingMachine.setCoins(coins);

        // Maschine befüllen
        newVendingMachine.updateShelfContent("01", "Coke", 150, 10); 
        newVendingMachine.updateShelfContent("A4", "Kaffee", 150, 10); 
        newVendingMachine.logContent();
        
    }


}
