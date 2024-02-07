package io.github.shinyruo.hellocucumber.agent;

import java.util.Properties;

public abstract class AbstractAgent implements Agent {

    private final String agentName;
    private final Properties properties;

    protected AbstractAgent(String agentName, Properties properties) {
        this.agentName = agentName;
        this.properties = properties;
    }

    @Override
    public String getAgentName() {
        return this.agentName;
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }
}