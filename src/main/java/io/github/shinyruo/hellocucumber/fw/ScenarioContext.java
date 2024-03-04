package io.github.shinyruo.hellocucumber.fw;

import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.SessionScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Singleton
public class ScenarioContext {

    private final Map<String, Object> scenarioContextMap;
    private final Properties properties;

    @Inject
    private ScenarioContext(PropertiesHandler propertiesHandler) {
        this.scenarioContextMap = new HashMap<>();
        this.properties = propertiesHandler.getProperties();
    }

    public String processString(String input) {
        return switch (input.charAt(0)) {
            case '@' -> {
                String variableName = input.substring(1);
                yield scenarioContextMap.get(variableName).toString();
            }
            case '$' -> {
                String propertyName = input.substring(1);
                yield properties.getProperty(propertyName);
            }
            default -> input;
        };
    }

    public void setContext(String key, Object value) {
        scenarioContextMap.put(key, value);
    }

    public void cleanup() {
        scenarioContextMap.clear();
    }
}