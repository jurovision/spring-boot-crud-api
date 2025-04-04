package uk.co.ryanharrison.crudapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import uk.co.ryanharrison.crudapi.model.Coins;
import uk.co.ryanharrison.crudapi.model.Product;
import uk.co.ryanharrison.crudapi.model.ProductFilter;
import uk.co.ryanharrison.crudapi.model.VendingMachine;
import uk.co.ryanharrison.crudapi.service.ProductService;
import uk.co.ryanharrison.crudapi.util.JsonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class VendingMachineTest {

    private VendingMachine newVendingMachine;
    private Coins purchaseCoins;

    @BeforeEach
    void setUp() {
        try {
            this.newVendingMachine = new VendingMachine();
        } catch (IOException e) {
            System.out.println("Error while trying to write the machine log. " +
                    "Please try loading the Vendo-Mazing 666 again.");
            return;
        }

        Coins coins = new Coins();
        coins.setTenCentCoins(10);
        coins.setTwentyCentCoins(10);
        coins.setFiftyCentCoins(10);
        coins.setOneEuroCoins(10);
        coins.setTwoEuroCoins(10);

        // Shelf shelf = new Shelf("A1", "Kaffee", 200);

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
