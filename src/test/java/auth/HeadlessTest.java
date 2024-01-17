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

public class HeadlessTest {
    static Logger logger = (Logger) LogManager.getLogger("WebDriverDZ");

    private WebDriver driver;

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
        logger.info("Установка драйвера");
    }
    @BeforeEach
    public void driverInstallFoHeadless() {
        ChromeOptions options = new ChromeOptions();
//      открыть Chrome в headless режиме headless
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        logger.info("Открытие браузера в режиме headless");
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
    public void openHeadless() {
// Перейти на https://duckduckgo.com/
        driver.get("https://duckduckgo.com");
//      Определяем элемент поисковая строка и кликаем по нему
        WebElement elEnter = driver.findElement(By.cssSelector("#searchbox_input"));
        elEnter.click();
//      В поисковой строке вводим "ОТУС" и кликаем по нему
        WebElement elInput = driver.findElement(By.cssSelector("#searchbox_input"));
        elInput.sendKeys("ОТУС");
        elEnter.submit();
//      В открывшемся окне проверяем, что в поисковой выдаче первый результат
//      Онлайн‑курсы для профессионалов,дистанционное обучение

        WebElement elResult = driver.findElement(By.xpath("//span[contains(text(),'Онлайн‑курсы для профессионалов, дистанционное обучение современным ...')]"));
        Assertions.assertTrue(elResult.getText().contains("Онлайн‑курсы для профессионалов, дистанционное обучение"),
                "В поисковой выдаче нет данного текста");
    }
}
