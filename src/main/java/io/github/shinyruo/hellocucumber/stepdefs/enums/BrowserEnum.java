package io.github.shinyruo.hellocucumber.stepdefs.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public enum BrowserEnum {

    CHROME("chrome") {
        @Override
        public WebDriver createDriver() {
            return new ChromeDriver();
        }
    },
    EDGE("edge") {
        @Override
        public WebDriver createDriver() {
            return new EdgeDriver();
        }
    },
    FIREFOX("firefox") {
        @Override
        public WebDriver createDriver() {
            return new FirefoxDriver();
        }
    },
    IE("ie") {
        @Override
        public WebDriver createDriver() {
            return new InternetExplorerDriver();
        }
    },
    SAFARI("safari") {
        @Override
        public WebDriver createDriver() {
            return new SafariDriver();
        }
    };
    private static final Logger logger = LogManager.getLogger(BrowserEnum.class);
    private final String browserName;

    BrowserEnum(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public abstract WebDriver createDriver();
}