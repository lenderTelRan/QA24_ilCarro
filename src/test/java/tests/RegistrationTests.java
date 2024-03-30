package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.jws.soap.SOAPBinding;
import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if(app.getHelperUser().isLogged())
            app.getHelperUser().logOut();
    }

    @Test
    public void registrationSuccess() {
        int i = new Random().nextInt(1000);

        User user = new User()
                .withFirstName("Piter")
                .withLastName("Parker")
                .withEmail("piter" + i + "@gmail.com")
                .withPassword("Parker12345@");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkBox();
        app.getHelperUser().submit();

        app.getHelperCar().pause(2);
        Assert.assertTrue(app.getHelperCar().getMessage().contains("You are logged in success"));
    }
    @Test
    public void registrationWrongEmail() {
        User user = new User()
                .withFirstName("Piter")
                .withLastName("Parker")
                .withEmail("pitergmail.com")
                .withPassword("Parker12345@");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkBox();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getTextError().contains("Wrong email format"));
    }
    @Test
    public void registrationWrongPassword() {
        User user = new User()
                .withFirstName("Piter")
                .withLastName("Parker")
                .withEmail("piter@gmail.com")
                .withPassword("123456");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkBox();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getTextError().contains("Password must contain"));
    }
    @Test
    public void registrationEmptyName() {
        User user = new User()
                .withFirstName("")
                .withLastName("Parker")
                .withEmail("piter@gmail.com")
                .withPassword("Parker12345@");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkBox();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getTextError().contains("Name is required"));
    }

    @Test
    public void registrationEmptyEmail() {
        User user = new User()
                .withFirstName("Piter")
                .withLastName("Parker")
                .withEmail("")
                .withPassword("Parker12345@");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkBox();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getTextError().contains("Email is required"));
    }
    @Test
    public void registrationEmptyLastName() {
        User user = new User()
                .withFirstName("Piter")
                .withLastName("")
                .withEmail("piter@gmail.com")
                .withPassword("Parker12345@");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkBox();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getTextError().contains("Last name is required"));
    }

    @AfterMethod
    public void postConditions() {
        app.getHelperUser().clickOk();
    }
}
