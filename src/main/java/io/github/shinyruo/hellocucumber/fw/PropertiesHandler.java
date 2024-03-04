package io.github.shinyruo.hellocucumber.fw;

import com.google.inject.Inject;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import jakarta.inject.Singleton;

@Log4j2
@Singleton
public class PropertiesHandler {
    private static final String SYSPROP_PROFILE = "profile";
    private static final String PROFILE_LOCAL = "local";
    private static final String AGENTS = "Agents";
    private static final String CLASS = "class";
    @Getter
    private final Properties properties = new Properties();
    @Getter
    private final String profile;
    private final Map<String, Object> yamlMap;

    @Inject
    private PropertiesHandler() {
        this.profile = System.getProperty(SYSPROP_PROFILE, PROFILE_LOCAL);
        this.yamlMap = yamlFileAsMap(ResourceFilesHandler.getProfilesFile(profile));
        this.properties.putAll(yamlFileToFlattenedMap(ResourceFilesHandler.getProfilesFile(profile)));
    }

    public String getAgentClassName(String agentName) {
        return getAgentProperties(agentName).getProperty(CLASS);
    }

    public Properties getAgentProperties(String agentName) {
        final Properties agentProperties = new Properties();
        agentProperties.putAll((Map<?, ?>) getAllAgentsProperties().get(agentName));
        return agentProperties;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getAllAgentsProperties() {
        return (Map<String, Object>) this.yamlMap.get(AGENTS);
    }

    private static Map<String, Object> yamlFileAsMap(File yamlFile) {
        final Path path = Paths.get(yamlFile.getAbsolutePath());
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            result = asMap(new Yaml().load(Files.newBufferedReader(path)));
        } catch (IOException e) {
            log.error("File not found as {}", yamlFile.getAbsolutePath(), e);
        }
        return result;
    }

    private static Map<String, Object> yamlFileToFlattenedMap(File yamlFile) {
        return getFlattenedMap(yamlFileAsMap(yamlFile));
    }

    static Map<String, Object> asMap(Object object) {
        Map<String, Object> result = new LinkedHashMap<>();
        if (!(object instanceof Map<?, ?> map)) {
            result.put("document", object);
            return result;
        }
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                value = asMap(value);
            }
            Object key = entry.getKey();
            if (key instanceof CharSequence) {
                result.put(key.toString(), value);
            } else {
                result.put("[" + key.toString() + "]", value);
            }
        }
        return result;
    }

    static Map<String, Object> getFlattenedMap(Map<String, Object> source) {
        Map<String, Object> result = new LinkedHashMap<>();
        buildFlattenedMap(result, source, null);
        return result;
    }

    private static void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, @Nullable String path) {
        source.forEach((key, value) -> {
            if (path != null && !path.isBlank()) {
                if (key.startsWith("[")) {
                    key = path + key;
                } else {
                    key = path + '.' + key;
                }
            }
            if (value instanceof String) {
                result.put(key, value);
            } else if (value instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) value;
                buildFlattenedMap(result, map, key);
            } else if (value instanceof Collection<?> collection) {
                if (collection.isEmpty()) {
                    result.put(key, "");
                } else {
                    int count = 0;
                    for (Object object : collection) {
                        buildFlattenedMap(result, Collections.singletonMap("[" + (count++) + "]", object), key);
                    }
                }
            } else {
                result.put(key, (value != null ? value : ""));
            }
        });
    }
}