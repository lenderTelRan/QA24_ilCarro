package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase {
    @Test(groups = {"smoke"})
    public void searchCurrentMonthSuccess() {
        app.getHelperCar().searchCurrentMonth("Tel Aviv, Israel", "7/25/2024", "7/28/2024");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess() {
        app.getHelperCar().searchCurrentYear("Tel Aviv, Israel", "8/25/2024", "9/12/2024");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess() {
        app.getHelperCar().searchAnyPeriod("Tel Aviv, Israel", "12/20/2024", "2/12/2025");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test(groups = {"smoke"})
    public void negativeSearch() {
        app.getHelperCar().searchNotValidPeriod("Tel Aviv, Israel", "1/10/2024", "1/14/2024");
        app.getHelperCar().submit();
//        Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperCar().getTextError(), "You can't pick date before today");
    }

    @AfterMethod(alwaysRun = true)
    public void postConditions() {
    app.getHelperCar().navigateByLogo();
    }
}
