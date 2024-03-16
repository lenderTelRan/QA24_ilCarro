package tests;

import manager.ApplicationManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    String emailValid = "test_telran@gmail.com";
    String passwordValid = "Test@12345";
    String emailInvalid = "test_telran@gmailcom";
    String passwordInvalid = "123456";

    @Test
    public void loginFormPositive() {
        app.getHelperUser().findLoginForm();
        app.getHelperUser().fillLoginForm(emailValid, passwordValid);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
//        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//a[text()=' Logout ']")));

        app.getHelperUser().submitLogout();
    }
    @Test
    public void loginFormNegativePassword() {
        app.getHelperUser().findLoginForm();
        app.getHelperUser().fillLoginForm(emailValid, passwordInvalid);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().notLogged());
    }
    @Test
    public void loginFormNegativeLogin() {
        app.getHelperUser().findLoginForm();
        app.getHelperUser().fillLoginForm(emailInvalid, passwordValid);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().notLogged());
    }
}
