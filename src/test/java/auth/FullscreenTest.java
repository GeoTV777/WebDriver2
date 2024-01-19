package auth;

import factory.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.WaitTools;

import java.time.Duration;

public class FullscreenTest {
    static Logger logger = (Logger) LogManager.getLogger("WebDriver2");
    private WebDriver driver;
    private String baseUrl = System.getProperty("base.url1");
    private WebDriverWait webDriverWait;
    private WaitTools waitTools;
// Удалить, если использовать фабрику и перед каждым тестом устанавливать снова
//    @BeforeAll
//    public static void driverSetup() {
//        WebDriverManager.chromedriver().setup();
//        logger.info("Установка драйвера");
//    }


    @BeforeEach
    public void driverInstallFoKiosk(){
        driver = new DriverFactory().create();
        waitTools =new WaitTools();
//      webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ChromeOptions options = new ChromeOptions();
//      Открыть Chrome в режиме киоска
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);
//        driver.get(baseUrl);
        logger.info("Открытие браузера в режиме fullscreen");
    }

    @AfterEach
    public void driverStop() {
        if (driver != null) {
            logger.info("Закрытие браузера");
            driver.close();
            driver.quit();
        }

    }
    @Test

    public void openFullscreen() {
//      Переходим на сайт:
      driver.get("https://p.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
//      Кликаем картинку
        WebElement elPicture = driver.findElement(By.xpath("//li[contains(@data-id,'id-1')]//a"));
        elPicture.click();
//      Проверяем открытие картинки в модальном окне

        WebElement elModalPicture = driver.findElement(By.cssSelector(".pp_content"));
//        webDriverWait.until(ExpectedConditions.visibilityOf(elModalPicture));
//        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".pp_content"))));
        Assertions.assertTrue(elModalPicture.isDisplayed(),"No image in modal window");
//        Assertions.assertTrue(waitTools.waitForCondition(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".pp_content"))));
    }
 }