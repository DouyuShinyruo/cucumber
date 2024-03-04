package io.github.shinyruo.hellocucumber.fw;


import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.net.URL;
import java.util.Objects;

@Log4j2
public class ResourceFilesHandler {
    private static final String PROFILES = "profiles";
    private static final String DATA = "data";
    private static final String PROFILE_SUFFIX = ".yml";

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
