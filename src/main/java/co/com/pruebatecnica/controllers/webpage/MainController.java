package co.com.pruebatecnica.controllers.webpage;

import co.com.pruebatecnica.exceptions.WebActionsException;
import co.com.pruebatecnica.helpers.Browsers;
import co.com.pruebatecnica.helpers.Properties;
import co.com.pruebatecnica.logs.Log;
import co.com.pruebatecnica.pages.PageTest;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.support.PageFactory;

import static co.com.pruebatecnica.stepsdefinitions.step_test.StepTest.webAction;

public class MainController {

    private final Properties properties = new Properties();
    private PageTest pageTest;
    SoftAssertions assertions = new SoftAssertions();

    public void startBrowser(Browsers browser) {
        try {
            webAction.startWebApp(browser, properties.getFieldProperties("urlApplication"));
        } catch (Throwable e) {
            Log.LOGGER.error("An error occurred while setting up the browser" + e);
        }
    }

    public void homeBlankFactor() {

        Log.LOGGER.info("Opening URL BlankFactor");
        pageTest = PageFactory.initElements(webAction.getDriver(), PageTest.class);
        try {
            pageTest.clickBtnAcceptCookies();
            assertions.assertThat(pageTest.isPresentBtnContact())
                    .as("We are not on the main page")
                    .isEqualTo(true);
            Log.LOGGER.info("The home page loaded correctly");
        } catch (WebActionsException | InterruptedException e) {
            Log.LOGGER.error("An error occurred when loading the main page.");
            e.printStackTrace();
        }
    }

    public void clickOnBlogSection() {
        pageTest = PageFactory.initElements(webAction.getDriver(), PageTest.class);
        try {
            pageTest.HoverOverInsights();
            pageTest.clickBtnGoToBlog();
            pageTest.findAndClickBlogTitle();
            Log.LOGGER.info("The blog was entered correctly defined");
        } catch (WebActionsException | InterruptedException e) {
            Log.LOGGER.error("An error occurred in the search process and login to the blog.");
            e.printStackTrace();
        }
    }

    public void validations() {
        pageTest = PageFactory.initElements(webAction.getDriver(), PageTest.class);
        try {
            assertions.assertThat(pageTest.getUrl())
                    .as("The URL is not correct")
                    .isEqualTo(properties.getFieldProperties("urlValidate"));
            assertions.assertThat(pageTest.getTitleBlog())
                    .as("The Title blog is not correct")
                    .isEqualTo(properties.getFieldProperties("titleBlog"));
            assertions.assertThat(pageTest.getBy())
                    .as("The author is not correct")
                    .isEqualTo("Sofia Gonzalez");
            Log.LOGGER.info("The validations were executed");
        } catch (WebActionsException e) {
            Log.LOGGER.error("An error occurred while performing validations.");
            e.printStackTrace();
        }
    }

    public void emailSubscription() {
        pageTest = PageFactory.initElements(webAction.getDriver(), PageTest.class);
        try {
            pageTest.enterEmail();
            pageTest.clickBtnSubscribe();
            assertions.assertThat(pageTest.getTextEmailSubscribed())
                    .as("Something happened validating the subscriber's message")
                    .isEqualTo(true);
            Log.LOGGER.info("The email was subscribed");
        } catch (WebActionsException e) {
            Log.LOGGER.error("An error occurred: " + e);
            e.printStackTrace();
        }
    }

    public void countTitles() {
        pageTest = PageFactory.initElements(webAction.getDriver(), PageTest.class);
        try {
            pageTest.HoverOverInsights();
            pageTest.clickBtnGoToBlog();
            pageTest.printListTitles();
        } catch (WebActionsException | InterruptedException e) {
            Log.LOGGER.error("An error occurred: " + e);
            e.printStackTrace();
        }
        assertions.assertAll();
    }


}
