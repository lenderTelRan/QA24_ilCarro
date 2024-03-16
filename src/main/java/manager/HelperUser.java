package manager;

import org.openqa.selenium.By;
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

    public void submitLogin() {
        click(By.xpath("//button[@type='submit']"));
    }

    public void submitLogout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public boolean isLogged() {
        if(isElementPresent(By.xpath("//h2[text()='Logged in success']"))) {
            click(By.xpath("//button[text()='Ok']"));
            return true;
        }
        return false;
    }
    public boolean notLogged() {
        if(isElementPresent(By.xpath("//h2[text()='\"Login or Password incorrect\"']"))) {
            click(By.xpath("//button[text()='Ok']"));
            return true;
        }
        return false;
    }

}
