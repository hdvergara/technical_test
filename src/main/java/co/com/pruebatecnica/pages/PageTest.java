package co.com.pruebatecnica.pages;

import co.com.pruebatecnica.exceptions.WebActionsException;
import co.com.pruebatecnica.logs.Log;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static co.com.pruebatecnica.stepsdefinitions.step_test.StepTest.webAction;

public class PageTest {

    public PageTest() {
    }

    private int DEFAULT_TIME = 30;

    @FindBy(how = How.XPATH, using = "//*[@id='hs-eu-confirmation-button']")
    private WebElement btnAcceptCookies;

    @FindBy(how = How.XPATH, using = "//*[@title='Contact']")
    private WebElement btnContac;

    @FindBy(how = How.XPATH, using = "//li[@id='menu-item-4436']")
    private WebElement lnkInsights;

    @FindBy(how = How.XPATH, using = "//*[@class='page-item__title' and contains(text(), 'Blog')]")
    private WebElement lnkBlog;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Why Fintech in Latin America Is Having a Boom')]")
    private WebElement lnkWhyFintechTitle;

    @FindBy(how = How.XPATH, using = "//*[@class='btn btn-large btn-blue btn-blue-hover-blue-dark btn-load-more btn-load-more-posts']")
    private WebElement btnLoadMore;

    @FindBy(how = How.CLASS_NAME, using = "ajax-spinner")
    private WebElement spinner;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Why Fintech in Latin America Is Having a Boom')]")
    private WebElement lblTitleBlog;

    @FindBy(how = How.XPATH, using = "//*[@title='Sofia Gonzalez' and contains(text(), 'Sofia Gonzalez')]")
    private WebElement lblBy;

    @FindBy(how = How.XPATH, using = "//*[@placeholder='Your email address']")
    private WebElement inputEmail;

    @FindBy(how = How.XPATH, using = "//*[@class='btn btn-dark' and contains(text(),' Subscribe ')]")
    private WebElement btnSubscribe;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Thank you for subscribing! Stay tuned.')]")
    private WebElement lblSubscribed;

    @FindBy(how = How.XPATH, using = "//div[@class='posts-list']//h2")
    private List<WebElement> gridTitles;

    public void clickBtnAcceptCookies() throws WebActionsException {
        if (webAction.isVisible(btnAcceptCookies, 15)) {
            webAction.click(btnAcceptCookies, 5);
        }
    }

    public void HoverOverInsights() throws WebActionsException, InterruptedException {
        webAction.moveTo(lnkInsights, DEFAULT_TIME);
    }

    public void clickBtnGoToBlog() throws WebActionsException {
        webAction.moveTo(lnkBlog, DEFAULT_TIME);
        webAction.click(lnkBlog, DEFAULT_TIME);
    }

    public void findAndClickBlogTitle() throws WebActionsException {
        while (!webAction.isVisible(lnkWhyFintechTitle, 0)) {
            webAction.click(btnLoadMore, 5);
            webAction.isInvisible(spinner, 5);
        }
        webAction.click(lnkWhyFintechTitle, DEFAULT_TIME);
    }

    public String getUrl() {
        return webAction.getDriver().getCurrentUrl();
    }

    public String getTitleBlog() throws WebActionsException {
        return webAction.getText(lblTitleBlog, DEFAULT_TIME);
    }

    public String getBy() throws WebActionsException {
        return webAction.getText(lblBy, DEFAULT_TIME);
    }

    public boolean isPresentBtnContact() throws InterruptedException {
        webAction.waitVisible(btnContac, DEFAULT_TIME);
        return webAction.isVisible(btnContac, DEFAULT_TIME);
    }

    public void enterEmail() throws WebActionsException {
        Faker faker = new Faker();
        webAction.sendText(inputEmail, faker.internet().emailAddress(), DEFAULT_TIME);
    }

    public void clickBtnSubscribe() throws WebActionsException {
        webAction.isInvisible(spinner, DEFAULT_TIME);
        webAction.click(btnSubscribe, DEFAULT_TIME);
    }

    public Boolean getTextEmailSubscribed() throws WebActionsException {
        return webAction.isVisible(lblSubscribed, DEFAULT_TIME);
    }

    public void printListTitles() {
        for (WebElement element : gridTitles) {
            Log.LOGGER.info("Title: " + element.getText());
        }
    }

}
