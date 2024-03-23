package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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

    public void submit() {
        click(By.xpath("//button[@type='submit']"));
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

    public String getMessage() {
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }


    public String getTextError() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    //----------registration----------

    public void openRegistrationForm() {
        click(By.xpath("//button[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkBox() {
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }
}
