package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {
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
        System.out.println(title);
    }
}
