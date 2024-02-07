package io.github.shinyruo.hellocucumber.stepdefs.common;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import io.github.shinyruo.hellocucumber.common.ScenarioContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CucumberHooks {
    private static final Logger logger = LogManager.getLogger(CucumberHooks.class);

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("=======Starting scenario: {} =======", scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        ScenarioContext.getInstance().cleanup();
        logger.info("=======Ending scenario: {} =======", scenario.getName());
    }

}
