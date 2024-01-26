package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SeleniumSteps extends BaseStepDefinition {
    private static final Logger LOGGER = LogManager.getLogger(SeleniumSteps.class);
    WebDriver driver;

    @When("a chromedriver is setup")
    public void aChromedriverIsSetup() {
        this.driver = new ChromeDriver();
    }

    @Then("guide to website {string}")
    public void guideToWebsite(String website) {
        driver.get(website);
    }

    @And("get title of the page")
    public void getTitleOfThePage() {
        String title = driver.getTitle();
        LOGGER.info("Title of the page is: " + title);
    }
}
