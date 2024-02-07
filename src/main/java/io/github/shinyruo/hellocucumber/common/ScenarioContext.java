package io.github.shinyruo.hellocucumber.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ScenarioContext {

    private final Map<String, Object> scenarioContextMap;
    private final Properties properties;

    private static final ThreadLocal<ScenarioContext> instance = new ThreadLocal<>();

    private ScenarioContext() {
        this.scenarioContextMap = new HashMap<>();
        this.properties = PropertiesHandler.getInstance().getProperties();
    }

    public static ScenarioContext getInstance() {
        if (instance.get() == null) {
            instance.set(new ScenarioContext());
        }
        return instance.get();
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
        instance.remove();
    }


}