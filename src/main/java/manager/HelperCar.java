package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(1);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.cssSelector("#make"), car.getManufacture());
        type(By.cssSelector("#model"), car.getModel());
        type(By.cssSelector("#year"), car.getYear());

        select(By.id("fuel"), car.getFuel());
        type(By.cssSelector("#seats"), String.valueOf(car.getSeats()));
        type(By.cssSelector("#class"), car.getCarClass());
        type(By.cssSelector("#serialNumber"), car.getCarRegNumber());
        type(By.cssSelector("#price"), car.getPrice() + "");
        type(By.cssSelector("#about"), car.getAbout());
    }

    private void select(By locator, String options) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(options);
    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void clickSearchCars() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.cssSelector("#photos")).sendKeys(link);
    }
}
