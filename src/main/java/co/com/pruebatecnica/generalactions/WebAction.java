package co.com.pruebatecnica.generalactions;


import co.com.pruebatecnica.exceptions.WebActionsException;
import co.com.pruebatecnica.helpers.Browsers;
import co.com.pruebatecnica.logs.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

public class WebAction {

    public WebAction(String nameFolder, String projectFolder) {
        Log.initLogs(Paths.get(System.getProperty("user.dir"), nameFolder), projectFolder);
        Log.LOGGER.info("============================ Start Test ==============================");
    }

    private WebDriver driver = null;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void sendText(WebElement element, String text, int timeout) throws WebActionsException {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new WebActionsException("An error occurred while performing sendText action", e);
        }

    }

    public void moveTo(WebElement element, int timeout) throws WebActionsException {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
            wait.until(driver -> element.findElement(By.xpath(".")));
            new Actions(driver).moveToElement(element).perform();
        } catch (Exception e) {
            throw new WebActionsException("An error occurred while performing moveTo action.", e);
        }
    }

    public boolean isVisible(WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException | NoSuchElementException | NullPointerException e) {
            return false;
        }
    }

    public boolean isInvisible(WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (TimeoutException | NoSuchElementException | NullPointerException e) {
            return false;
        }
    }

    public void click(WebElement element, int timeout) throws WebActionsException {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (NoSuchElementException | NullPointerException | TimeoutException e) {
            throw new WebActionsException("An error occurred while performing click action", e);
        }
    }

    public String getText(WebElement element, int timeout) throws WebActionsException {
        String value = "";
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            value = element.getText();
        } catch (NoSuchElementException | NullPointerException | TimeoutException e) {
            throw new WebActionsException("An error occurred getting the text", e);
        }
        return value;
    }

    public void waitVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void startWebApp(Browsers browser, String url) throws WebActionsException {
        try {
            switch (browser) {
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
            }
            driver.manage().window().maximize();
            driverNavigation(url);
        } catch (Exception e) {
            throw new WebActionsException("An error occurred while configuring the browser", e);
        }
    }

    private void driverNavigation(String url) {
        setDriver(driver);
        driver.get(url);
    }

    public void closeBrowser() {
        if (driver != null) {
            try {
                driver.close();
            } catch (Exception e) {
                Log.LOGGER.warn("Window handlers already closed.");
            }
            driver.quit();
        }
        Log.LOGGER.info("============================= End Test ================================\n\n");
    }
}
