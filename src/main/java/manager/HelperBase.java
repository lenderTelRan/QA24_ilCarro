package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        WebElement element = wd.findElement(locator);
        element.click();
    }
    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        if(text != null && !text.isEmpty())
            element.sendKeys(text);
    }

    public boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }
}
