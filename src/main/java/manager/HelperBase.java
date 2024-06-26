package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
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

    public void pause(int sec) {
        try {
            Thread.sleep(1000L * sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if(text != null && !text.isEmpty())
            element.sendKeys(text);
    }

    private void clearNew(WebElement element) {     // for registrationEmptyName & registrationEmptyLastName
        element.sendKeys("a");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }

    public boolean isYallaButtonNotActive() {
        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        return !element.isEnabled();
    }

    public String getMessage() {
        pause(2);
        return wd.findElement(By.cssSelector(".dialog-container")).getText();
    }

    public void submit() {
        click(By.xpath("//button[@type='submit']"));
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getTextError() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public void clearTextBox(By locator) {
        WebElement element = wd.findElement(locator);
        String os = System.getProperty("os.name");      // OS --> Windows or Mac
        System.out.println(os);

        if(os.startsWith("Win")) {
            element.sendKeys(Keys.CONTROL, "a");
            element.sendKeys(Keys.DELETE);
        } else {
            element.sendKeys(Keys.COMMAND, "a");
            element.sendKeys(Keys.DELETE);
        }
    }
}
