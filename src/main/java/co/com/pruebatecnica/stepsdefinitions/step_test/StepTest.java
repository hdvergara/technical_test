package co.com.pruebatecnica.stepsdefinitions.step_test;

import co.com.pruebatecnica.controllers.webpage.MainController;
import co.com.pruebatecnica.generalactions.WebAction;
import co.com.pruebatecnica.helpers.Browsers;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepTest {

    public static WebAction webAction;
    private MainController mainController;

    @Before
    public void setUp() {
        webAction = new WebAction("Logs", "BlankFactor");
    }

    @After
    public void tearDown() {
        webAction.closeBrowser();
    }

    @Given("^We are on the BlankFactor main page$")
    public void weAreOnTheBlankFactorMainPage() {
        mainController = new MainController();
        mainController.startBrowser(Browsers.CHROME);
        mainController.homeBlankFactor();
    }

    @When("^When we go to the blog section and search for a blog$")
    public void whenWeGoToTheBlogSectionAndSearchForABlog() {
        mainController.clickOnBlogSection();
    }

    @Then("^Validate that the page loaded is correct$")
    public void validateThatThePageLoadedIsCorrect() throws InterruptedException {
        mainController.validations();
    }

    @And("^We subscribe by entering an email$")
    public void weSubscribeByEnteringAnEmail() {
        mainController.emailSubscription();
        mainController.countTitles();
    }
}
