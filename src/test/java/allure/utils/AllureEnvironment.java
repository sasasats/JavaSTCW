package allure.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class AllureEnvironment {

    private AllureEnvironment() {

    }

    public static void generate() throws IOException {
        Properties properties = new Properties();

        properties.setProperty("os", System.getProperty("os.name"));
        properties.setProperty("os_version", System.getProperty("os.version"));
        properties.setProperty("os_architecture", System.getProperty("os.arch"));

        properties.setProperty("java_version", System.getProperty("java.version"));
        properties.setProperty("java_vendor", System.getProperty("java.vendor"));

        properties.setProperty("user", System.getProperty("user.name"));
        properties.setProperty("working_directory", System.getProperty("user.dir"));

        Path allureResults = Paths.get("allure-results");

        Files.createDirectories(allureResults);

        Path environmentFile = allureResults.resolve("environment.properties");

        try (OutputStream outputStream = Files.newOutputStream(environmentFile)) {
            properties.store(outputStream, "Test Environment");
        }
    }
}