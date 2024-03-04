package io.github.shinyruo.hellocucumber.agent;

import lombok.Getter;

import java.util.Properties;

@Getter
public abstract class AbstractAgent implements Agent {

    private final String agentName;
    private final Properties properties;

    protected AbstractAgent(String agentName, Properties properties) {
        this.agentName = agentName;
        this.properties = properties;
    }

}