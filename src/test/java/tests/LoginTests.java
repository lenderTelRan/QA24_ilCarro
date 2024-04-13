package tests;

import manager.ApplicationManager;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if(app.getHelperUser().isLogged())
            app.getHelperUser().logOut();
    }

    String email = "test_telran@gmail.com";
    String password = "Test@12345";
    String wrongEmail = "test_telran@gmailcom";
    String wrongPassword = "123456";


    @Test(priority = 1)
    public void loginFormPositive() {

        app.getHelperUser().findLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());
//        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
//        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//a[text()=' Logout ']")));
    }

    @Test(priority = 2)
    public void loginFormPositiveWithUser() {  // test with user

        User user = new User()
                .withEmail(email)
                .withPassword(password);

        app.getHelperUser().findLoginForm();
        app.getHelperUser().fillLoginFormWithUser(user);
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());
//        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
//        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//a[text()=' Logout ']")));
    }

    @Test
    public void loginFormWrongPassword() {
        app.getHelperUser().findLoginForm();
        app.getHelperUser().fillLoginForm(email, wrongPassword);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        Assert.assertTrue(app.getHelperUser().notLogged());
    }
    @Test
    public void loginFormWrongLogin() {
        app.getHelperUser().findLoginForm();
        app.getHelperUser().fillLoginForm(wrongEmail, password);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getTextError(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertTrue(app.getHelperUser().notLogged());
    }
    @Test
    public void loginFormUnregisteredUser() {
        app.getHelperUser().findLoginForm();
        app.getHelperUser().fillLoginForm("t-telran@gmail.com", password);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        Assert.assertTrue(app.getHelperUser().notLogged());
    }
    @AfterMethod
    public void postConditions() {
        app.getHelperUser().clickOk();
    }
}
