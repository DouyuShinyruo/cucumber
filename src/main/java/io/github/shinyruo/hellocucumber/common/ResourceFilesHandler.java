package io.github.shinyruo.hellocucumber.common;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public class ResourceFilesHandler {
    private static final Logger logger = LogManager.getLogger(ResourceFilesHandler.class);
    private static final String PROFILES = "profiles";
    private static final String DATA = "data";
    private static final String PROFILE_SUFFIX = ".yml";

    private ResourceFilesHandler() {
    }


    public static URL getProfilesPath() {
        return Objects.requireNonNull(ResourceFilesHandler.class.getClassLoader().getResource(PROFILES));
    }

    public static URL getDataPath() {
        return Objects.requireNonNull(ResourceFilesHandler.class.getClassLoader().getResource(DATA));
    }

    public static File getProfilesFile(String profile) {
        return new File(getProfilesPath().getFile(), profile + PROFILE_SUFFIX);
    }
}
