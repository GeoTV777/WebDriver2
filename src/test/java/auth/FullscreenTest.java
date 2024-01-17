package auth;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FullscreenTest {
    static Logger logger = (Logger) LogManager.getLogger("WebDriverDZ");

    private WebDriver driver;

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
        logger.info("Установка драйвера");
    }

    @BeforeEach
    public void driverInstallFoKiosk(){
        ChromeOptions options = new ChromeOptions();
//      Открыть Chrome в режиме киоска
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);
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
        WebElement elPicture = driver.findElement(By.xpath("//h3[text()='This is a title']"));
        elPicture.click();
//      Проверяем открытие картинки в модальном окне
        WebElement elModalPicture = driver.findElement(By.xpath("//img[contains(@src,'p7.jpg')]"));
        Assertions.assertTrue(elModalPicture.isDisplayed(),"Нет изображения в модальном окне");
    }
}