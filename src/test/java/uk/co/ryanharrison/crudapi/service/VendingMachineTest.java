package uk.co.ryanharrison.crudapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import uk.co.ryanharrison.crudapi.model.Coins;
import uk.co.ryanharrison.crudapi.model.VendingMachine;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
public class VendingMachineTest {

    private VendingMachine newVendingMachine;
    private Coins purchaseCoins;

    @BeforeEach
    void setUp() {
            this.newVendingMachine = new VendingMachine();

        Coins coins = new Coins();
        coins.setTenCentCoins(10);
        coins.setTwentyCentCoins(10);
        coins.setFiftyCentCoins(10);
        coins.setOneEuroCoins(10);
        coins.setTwoEuroCoins(10);

        this.newVendingMachine.setCoins(coins);
        this.newVendingMachine.updateShelfContent("01", "Club Mate", 150, 10); 
        this.newVendingMachine.updateShelfContent("02", "Coffee", 90, 10); 
        this.newVendingMachine.updateShelfContent("03", "Dubai Chocolate drink ", 1490, 10); 
        this.newVendingMachine.logContent();

    }

    @Test
    void tryPurchaseWithWayTooManyCoins() {
        purchaseCoins = new Coins();
        purchaseCoins.setTenCentCoins(0);
        purchaseCoins.setTwentyCentCoins(0);
        purchaseCoins.setFiftyCentCoins(1);
        purchaseCoins.setOneEuroCoins(1);
        purchaseCoins.setTwoEuroCoins(200);
        assertThat(newVendingMachine.removeDrinkWithSuccess("01", purchaseCoins)).isFalse();
    }

    @Test
    void tryPurchaseWithExactlyFittingAmount() {
        purchaseCoins = new Coins();
        purchaseCoins.setTenCentCoins(0);
        purchaseCoins.setTwentyCentCoins(0);
        purchaseCoins.setFiftyCentCoins(1);
        purchaseCoins.setOneEuroCoins(1);
        purchaseCoins.setTwoEuroCoins(0);
        assertThat(newVendingMachine.removeDrinkWithSuccess("01", purchaseCoins)).isTrue();
    }

    @Test
    void buyExpensiveDrinkSuccessfully() {
        purchaseCoins = new Coins();
        purchaseCoins.setTenCentCoins(0);
        purchaseCoins.setTwentyCentCoins(0);
        purchaseCoins.setFiftyCentCoins(10);
        purchaseCoins.setOneEuroCoins(6);
        purchaseCoins.setTwoEuroCoins(2);
        assertThat(newVendingMachine.removeDrinkWithSuccess("03", purchaseCoins)).isTrue();
    }

    @Test
    void checkMachineCoinCalculation() {
        purchaseCoins = new Coins();
        purchaseCoins.setTenCentCoins(0);
        purchaseCoins.setTwentyCentCoins(0);
        purchaseCoins.setFiftyCentCoins(0);
        purchaseCoins.setOneEuroCoins(0);
        purchaseCoins.setTwoEuroCoins(1);
        newVendingMachine.removeDrinkWithSuccess("01", purchaseCoins);
        int machineTwoEuroCoins = newVendingMachine.getCoins().getTwoEuroCoins();
        int machineFiftyCentCoins = newVendingMachine.getCoins().getFiftyCentCoins();
        boolean twoEuroCoinsAreCorrect = machineTwoEuroCoins == 11;
        boolean fiftyCentCoinsAreCorrect = machineFiftyCentCoins == 9;
        assertThat(twoEuroCoinsAreCorrect).isTrue();
        assertThat(fiftyCentCoinsAreCorrect).isTrue();
    }

}
