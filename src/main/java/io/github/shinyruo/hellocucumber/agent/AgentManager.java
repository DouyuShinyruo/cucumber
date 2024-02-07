package io.github.shinyruo.hellocucumber.agent;

import io.github.shinyruo.hellocucumber.common.PropertiesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AgentManager {
    private static final Logger logger = LogManager.getLogger(AgentManager.class);
    private final Map<String, Agent> agentMap;

    private AgentManager() {
        this.agentMap = new HashMap<>();
    }

    private static class AgentManagerHolder {
        private static final AgentManager INSTANCE = new AgentManager();
    }

    public static AgentManager getInstance() {
        return AgentManagerHolder.INSTANCE;
    }

    public Agent getOrCreatAgent(String agentName) {
        return agentMap.computeIfAbsent(agentName, this::createAgentInstance);
    }

    private Agent createAgentInstance(String agentName) {
        final Properties properties = PropertiesHandler.getInstance().getAgentProperties(agentName);
        final String className = PropertiesHandler.getInstance().getAgentClassName(agentName);
        try {
            Class<?> clazz = Class.forName(className);
            return (Agent) clazz.getConstructor(String.class, Properties.class).newInstance(agentName, properties);
        } catch (Exception e) {
            logger.error("Failed to create agent instance for class:{}", properties.getProperty("class"), e);
            return null;
        }
    }
}