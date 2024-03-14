package io.github.shinyruo.hellocucumber.agent.selenium;

import io.github.shinyruo.hellocucumber.agent.AbstractAgent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

@Getter
public class SeleniumAgent extends AbstractAgent {
    private WebDriver driver;

    public SeleniumAgent(String agentName, Properties properties) {
        super(agentName, properties);
        this.driver = null;
    }

    public void setupDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void quitDriver() {
        this.driver.quit();
    }

}
