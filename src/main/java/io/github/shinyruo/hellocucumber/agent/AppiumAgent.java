package io.github.shinyruo.hellocucumber.agent;

import io.appium.java_client.AppiumDriver;

import java.util.Properties;

public class AppiumAgent extends AbstractAgent {
    private AppiumDriver driver;

    protected AppiumAgent(String agentName, Properties properties) {
        super(agentName, properties);
    }

}
