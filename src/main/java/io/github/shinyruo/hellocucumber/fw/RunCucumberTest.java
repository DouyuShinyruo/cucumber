package io.github.shinyruo.hellocucumber.fw;

import io.cucumber.guice.GuiceFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
                "html:target/cucumber-report/cucumber.html"
        },
        glue = "io.github.shinyruo.hellocucumber.stepdefs",
        features = "src/main/resources/features",
        objectFactory = GuiceFactory.class
)
@Log4j2
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    @BeforeSuite
    public void beforeSuite() {
        log.info("================ BEFORE SUITE ================");
    }

    @AfterSuite
    public void afterSuite() {
        log.info("================ AFTER SUITE ================");
    }
}
