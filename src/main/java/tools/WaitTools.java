package tools;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitTools {
//    private int waitTimeoutSeconds = Integer.parseInt(System.getProperty("wait.timeout.seconds"));
    private WebDriver driver;
    public boolean waitForCondition(ExpectedCondition condition) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10));
            return true;
        }catch (TimeoutException ignore){
            return false;
        }
    }
}
