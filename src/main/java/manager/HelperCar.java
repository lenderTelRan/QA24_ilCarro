package manager;

import models.Car;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));

        String[] from = dateFrom.split("/");    // sptit date MM/dd/yyyy
        String[] to = dateTo.split("/");

        click(By.id("dates"));
        click(By.xpath("//div[text()=' " + from[1] + " ']"));
        click(By.xpath("//div[text()=' " + to[1] + " ']"));
    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));     // date String --> date localDate

        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffMonthFrom = from.getMonthValue() - month;

        if(diffMonthFrom > 0)
            clickNextMonthButton(diffMonthFrom);

        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        int diffMonthTo = to.getDayOfMonth() - from.getDayOfMonth();
        if(diffMonthTo > 0)
            clickNextMonthButton(diffMonthTo);

        click(By.xpath("//div[text()=' " + to.getDayOfMonth() + " ']"));


    }

    private void clickNextMonthButton(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));

    //*** dateFrom
        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYear = from.getYear() - now.getYear();
        int diffMonth;

        if(diffYear == 0) {
            diffMonth = from.getMonthValue() - now.getMonthValue();
        } else {
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue();
        }
        clickNextMonthButton(diffMonth);
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

    //*** dateTo
        diffYear = to.getYear() - from.getYear();
        if(diffYear == 0) {
            diffMonth = to.getMonthValue() - from.getMonthValue();
        } else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }
        clickNextMonthButton(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));
    }

    public void searchNotValidPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        pause(1);
        clearTextBox(By.id("dates"));
        type(By.id("dates"), dateFrom + " - " + dateTo);
        click(By.cssSelector("div.cdk-overlay-backdrop"));
    }
}
