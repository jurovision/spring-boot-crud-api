package uk.co.ryanharrison.crudapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uk.co.ryanharrison.crudapi.model.Product;
import uk.co.ryanharrison.crudapi.model.Shelf;
import uk.co.ryanharrison.crudapi.model.VendingMachine;
import uk.co.ryanharrison.crudapi.model.Coins;
import uk.co.ryanharrison.crudapi.repository.CoinsRepository;
import uk.co.ryanharrison.crudapi.repository.ProductRepository;

import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootApplication
public class SpringBootCrudApiApplication {

    public static void main(String[] args) {


        VendingMachine newVendingMachine;
        try {
            newVendingMachine = new VendingMachine();
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

        newVendingMachine.setCoins(coins);
        newVendingMachine.updateShelfContent("01", "Coke", 150, 15); 
        newVendingMachine.updateShelfContent("A4", "Kaffee", 150, 10); 
        newVendingMachine.logContent();
        
        SpringApplication.run(SpringBootCrudApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository, CoinsRepository coinsRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("product1")
                    .type("car")
                    .createdBy("me")
                    .createdAt(LocalDateTime.now())
                    .build());
            productRepository.save(Product.builder()
                    .name("product2")
                    .type("car")
                    .createdBy("me")
                    .createdAt(LocalDateTime.now())
                    .build());
            productRepository.save(Product.builder()
                    .name("product3")
                    .type("widget")
                    .createdBy("bob")
                    .createdAt(LocalDateTime.now())
                    .build());
            coinsRepository.save(Coins.builder()
                    .tenCentCoins(15)
                    .twentyCentCoins(15)
                    .fiftyCentCoins(15)
                    .oneEuroCoins(15)
                    .twoEuroCoins(15)
                    .build());
        };
    }







}
