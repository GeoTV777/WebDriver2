package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private String browserName = System.getProperty("browser.mame");

    public WebDriver create() {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "chrome":{
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
        }
    }
}
