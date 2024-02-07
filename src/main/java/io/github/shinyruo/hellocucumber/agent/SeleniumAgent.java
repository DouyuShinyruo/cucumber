package io.github.shinyruo.hellocucumber.agent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class SeleniumAgent extends AbstractAgent {
    private WebDriver driver;

    public SeleniumAgent(String agentName, Properties properties) {
        super(agentName, properties);
        this.driver = null;
    }

    public void setupDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElementByXpath(String xpath) {
        return this.driver.findElement(By.xpath(xpath));
    }

    public WebElement findElementById(String id) {
        return this.driver.findElement(By.id(id));
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void quitDriver() {
        this.driver.quit();
    }

}
