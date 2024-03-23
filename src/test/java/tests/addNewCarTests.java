package tests;

import models.Car;
import org.testng.annotations.Test;

import java.util.Random;

public class addNewCarTests extends TestBase {
    int i = new Random().nextInt(999);

    @Test
    public void addNewCarPositive() {
        Car car = Car.builder()
                .location("Haifa, Israel")
                .manufacture("Mazda")
                .model("CX-5")
                .year("2021")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("33-541-" + i)
                .price(58500.)
                .about("No text")
                .build();
    }
}
