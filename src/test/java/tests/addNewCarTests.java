package tests;

import models.Car;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class addNewCarTests extends TestBase {
    @BeforeClass(alwaysRun = true)
    public void preConditions() {
        if(!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User()
                    .withEmail("test_telran@gmail.com")
                    .withPassword("Test@12345"));
    }

    int i = new Random().nextInt(999);

    @Test(groups = {"smoke"})
    public void addNewCarPositiveAllFields() {
        Car car = Car.builder()
                .location("Haifa, Israel")
                .manufacture("Mazda")
                .model("CX-5")
                .year("2021")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("33-541-" + i)
                .price(58.500)
                .about("No text")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("D:\\QA24\\QA24_ilCarro\\mazda_cx-5.jpg");
        app.getHelperCar().submit();

//        Assert.assertEquals(app.getHelperCar()
//                .getMessage(), car.getManufacture() + " " + car.getModel() + " added successful");
        Assert.assertTrue(app.getHelperCar().getMessage().contains("Car added"));
    }
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
                .price(58.500)
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains(car.getManufacture() + " " + car.getModel()));
        Assert.assertTrue(app.getHelperCar().getMessage().contains("Car added"));
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.getHelperCar().clickSearchCars();
    }
}
