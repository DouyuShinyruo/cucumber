package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.shinyruo.hellocucumber.agent.selenium.SeleniumAgent;
import io.github.shinyruo.hellocucumber.stepdefs.common.BaseStepDefinition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class SeleniumSteps extends BaseStepDefinition {

    @Given("{word} has setup a driver for {browser}")
    public void aBrowserDriverIsSetup(String agentName, WebDriver browserDriver) {
        final SeleniumAgent seleniumAgent = (SeleniumAgent) agentManager.getOrCreateAgent(agentName);
        seleniumAgent.setupDriver(browserDriver);
        log.info("Agent {} has setup a driver for {}", agentName, browserDriver);
    }

    @Then("{word} guide to website {string}")
    public void guideToWebsite(String agentName, String url) {
        final String processedUrl = scenarioContext.processString(url);
        final SeleniumAgent seleniumAgent = (SeleniumAgent) agentManager.getOrCreateAgent(agentName);
        seleniumAgent.getDriver().get(processedUrl);
        log.info("Agent {} guide to website {}", agentName, url);
    }

    @Then("{word} get title of the page")
    public void getTitleOfThePage(String agentName) {
        final SeleniumAgent seleniumAgent = (SeleniumAgent) agentManager.getOrCreateAgent(agentName);
        final String title = seleniumAgent.getDriver().getTitle();
        log.info("Agent {} get title of the page: {}", agentName, title);
    }

    @Then("{word} was quit")
    public void quitDriver(String agentName) {
        final SeleniumAgent seleniumAgent = (SeleniumAgent) agentManager.getOrCreateAgent(agentName);
        seleniumAgent.quitDriver();
        log.info("Agent {} was quit", agentName);
    }

}
