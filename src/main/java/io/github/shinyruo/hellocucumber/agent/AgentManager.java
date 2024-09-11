package io.github.shinyruo.hellocucumber.agent;

import io.github.shinyruo.hellocucumber.fw.PropertiesHandler;
import lombok.extern.log4j.Log4j2;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Log4j2
@Singleton
public class AgentManager {
    private final Map<String, Agent> agentMap;

    private final PropertiesHandler propertiesHandler;

    @Inject
    private AgentManager(PropertiesHandler propertiesHandler) {
        this.agentMap = new HashMap<>();
        this.propertiesHandler = propertiesHandler;
    }

    public Agent getOrCreateAgent(String agentName) {
        return agentMap.computeIfAbsent(agentName, this::createAgentInstance);
    }

    private Agent createAgentInstance(String agentName) {
        final Properties properties = propertiesHandler.getAgentProperties(agentName);
        final String className = propertiesHandler.getAgentClassName(agentName);
        try {
            Class<?> clazz = Class.forName(className);
            return (Agent) clazz.getConstructor(String.class, Properties.class).newInstance(agentName, properties);
        } catch (Exception e) {
            log.error("Failed to create agent instance for class:{}", properties.getProperty("class"), e);
            return null;
        }
    }

    public void cleanup() {
        agentMap.clear();
    }
}