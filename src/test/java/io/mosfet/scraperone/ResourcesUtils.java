package io.mosfet.scraperone;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourcesUtils {

    public static String getResource(String pathfileName) throws IOException {
        ClassLoader classLoader = ResourcesUtils.class.getClassLoader();
        return Files.readString(Path.of(new File(classLoader.getResource(pathfileName).getFile()).getPath()));
    }
}