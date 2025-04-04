package uk.co.ryanharrison.crudapi.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Coins {

    private int tenCentCoins;
    private int twentyCentCoins;
    private int fiftyCentCoins;
    private int oneEuroCoins;
    private int twoEuroCoins;

    public int getTenCentCoins() {
        return this.tenCentCoins;
    }

    public void setTenCentCoins(int tenCentCoins) {
        this.tenCentCoins = tenCentCoins;
    }

    public int getTwentyCentCoins() {
        return this.twentyCentCoins;
    }

    public void setTwentyCentCoins(int twentyCentCoins) {
        this.twentyCentCoins = twentyCentCoins;
    }

    public int getFiftyCentCoins() {
        return this.fiftyCentCoins;
    }

    public void setFiftyCentCoins(int fiftyCentCoins) {
        this.fiftyCentCoins = fiftyCentCoins;
    }

    public int getOneEuroCoins() {
        return this.oneEuroCoins;
    }

    public void setOneEuroCoins(int oneEuroCoins) {
        this.oneEuroCoins = oneEuroCoins;
    }

    public int getTwoEuroCoins() {
        return this.twoEuroCoins;
    }

    public void setTwoEuroCoins(int twoEuroCoins) {
        this.twoEuroCoins = twoEuroCoins;
    }

}
