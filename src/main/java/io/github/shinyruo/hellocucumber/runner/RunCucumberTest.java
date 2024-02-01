package io.github.shinyruo.hellocucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
                "html:target/cucumber-report/cucumber.html"
        },
        glue = "io.github.shinyruo.hellocucumber.stepdefs",
        features = "src/main/resources/features",
        monochrome = true
)
public class CucumberBddRunner extends AbstractTestNGCucumberTests {
}
