package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void findLoginForm() {
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }

    public void fillLoginFormWithUser(User user) {
        type(By.xpath("//input[@id='email']"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword()); // with user
    }

    public void clickOk() {
        if(isElementPresent(By.xpath("//button[text()='Ok']")))
        click(By.xpath("//button[text()='Ok']"));
    }

    public void logOut() {
        if(isElementPresent(By.xpath("//a[text()=' Logout ']")))
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public boolean isLogged() {
        if(isElementPresent(By.xpath("//h2[text()='Logged in success']"))) {
            clickOk();
            return true;
        }
        return false;
    }
    public boolean notLogged() {
        if(isElementPresent(By.xpath("//h2[text()='\"Login or Password incorrect\"']"))) {
            clickOk();
            return true;
        }
        return false;
    }

    //----------registration----------

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkBox() {
        if(!wd.findElement(By.id("terms-of-use")).isSelected()) {
            JavascriptExecutor js = (JavascriptExecutor) wd;
            js.executeScript("document.querySelector('#terms-of-use').click()");
        }
    }
    public void checkBoxXY() {
        if(!wd.findElement(By.id("terms-of-use")).isSelected()) {
            WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use]"));
            Actions action = new Actions(wd);
            int width = (int) label.getRect().getWidth();
            int xOffset = -width / 2;                                                      // sdvig vlevo
            action.moveToElement(label, xOffset, 0).click().release().perform();    //sdvig na polovinu shirini iz centra konteinera label
        }
    }

    public void login(User user) {
        findLoginForm();
        fillLoginFormWithUser(user);
        submit();
        clickOk();
    }
}
