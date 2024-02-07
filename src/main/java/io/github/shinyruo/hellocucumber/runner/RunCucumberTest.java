package io.github.shinyruo.hellocucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
                "html:target/cucumber-report/cucumber.html"
        },
        glue = "io.github.shinyruo.hellocucumber.stepdefs",
        features = "src/main/resources/features"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    private static final Logger logger = LogManager.getLogger(RunCucumberTest.class);

    @BeforeSuite
    public void beforeSuite() {
        logger.info("================ BEFORE SUITE ================");

    }

    @AfterSuite
    public void afterSuite() {
        logger.info("================ AFTER SUITE ================");
    }
}
