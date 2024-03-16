package tests;

import manager.ApplicationManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    String email = "test_telran@gmail.com";
    String password = "Test@12345";

    @Test
    public void loginFormPositive() {
        app.getHelperUser().findLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
//        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//a[text()=' Logout ']")));
    }
}
