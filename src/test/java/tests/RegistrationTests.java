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

        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }
    @AfterMethod
    public void postConditions() {
        app.getHelperUser().clickOk();
    }
}
