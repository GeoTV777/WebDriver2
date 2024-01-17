package auth;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MaximizeTest {
    static Logger logger = (Logger) LogManager.getLogger("WebDriverDZ");

    private WebDriver driver;

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
        logger.info("Установка драйвера");
    }
    @BeforeEach
    public void driverMiximize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        logger.info("Открытие браузера в режиме полного окна");
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
    public void openMaximaze() {
//     Открыть Chrome в режиме полного экрана
//     Перейти на https://otus.ru
        driver.get("https://otus.ru");
//     Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
//     log:tvgeo777@gmail.com
//     pass: tvGEO777#777
        autorization();
//     Вывести в лог все cookie
        System.out.println("Это куки: " + driver.manage().getCookies());
//
    }
    private void autorization() {
//     Нажимаем кнопку Войти на главной странице
        WebElement elEnter = driver.findElement(By.xpath("//button[text()='Войти']"));
        elEnter.click();
//     На странице регистрации находим и кликаем поле Электронная почта
        WebElement elFocus = driver.findElement(By.xpath("//input[@name]/.."));
        elFocus.click();
//     Вводим email в поле Электронная почта
        WebElement elEmail = driver.findElement(By.xpath("//input[@name]"));
        elEmail.sendKeys("tvgeo777@gmail.com");
//     На странице регистрации находим и кликаем поле Пароль
        elFocus = driver.findElement(By.xpath("//input[@type='password']/.."));
        elFocus.click();
//     Вводим пароль в поле Пароль
        WebElement elPassword = driver.findElement(By.xpath("//input[@type='password']"));
        elPassword.sendKeys("tvGEO777#777");
//     Нажимаем кнопку Войти
        elEnter = driver.findElement(By.xpath("//button[./*[text()='Войти']]"));
        elEnter.click();
    }
}
