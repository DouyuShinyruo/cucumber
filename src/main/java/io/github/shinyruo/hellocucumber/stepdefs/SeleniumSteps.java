package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.en.*;
import io.github.shinyruo.hellocucumber.agent.AgentManager;
import io.github.shinyruo.hellocucumber.agent.SeleniumAgent;
import io.github.shinyruo.hellocucumber.common.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SeleniumSteps {
    private static final Logger logger = LogManager.getLogger(SeleniumSteps.class);
    private final AgentManager agentManagerInstance = AgentManager.getInstance();

    @Given("{word} has setup a driver for {browser}")
    public void aBrowserDriverIsSetup(String agentName, WebDriver browserDriver) {
        final SeleniumAgent seleniumAgent = (SeleniumAgent) agentManagerInstance.getOrCreatAgent(agentName);
        seleniumAgent.setupDriver(browserDriver);
        logger.info("Agent {} has setup a driver for {}", agentName, browserDriver);
    }

    @Then("{word} guide to website {string}")
    public void guideToWebsite(String agentName, String url) {
        final String processedUrl = ScenarioContext.getInstance().processString(url);
        final SeleniumAgent seleniumAgent = (SeleniumAgent) agentManagerInstance.getOrCreatAgent(agentName);
        seleniumAgent.getDriver().get(processedUrl);
        logger.info("Agent {} guide to website {}", agentName, url);
    }

    @Then("{word} get title of the page")
    public void getTitleOfThePage(String agentName) {
        final SeleniumAgent seleniumAgent = (SeleniumAgent) agentManagerInstance.getOrCreatAgent(agentName);
        final String title = seleniumAgent.getDriver().getTitle();
        logger.info("Agent {} get title of the page: {}", agentName, title);
    }

    @Then("{word} was quit")
    public void quitDriver(String agentName) {
        final SeleniumAgent seleniumAgent = (SeleniumAgent) agentManagerInstance.getOrCreatAgent(agentName);
        seleniumAgent.quitDriver();
        logger.info("Agent {} was quit", agentName);
    }

}
