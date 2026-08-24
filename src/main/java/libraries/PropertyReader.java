package libraries;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private String propertyFilePath = "src/main/resources/Configurations/Configuration.properties";
    public Properties properties = new Properties();

    public PropertyReader() {
        // Try file reader first
        File file = new File(propertyFilePath);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                properties.load(reader);
                return;
            } catch (IOException e) {
                System.err.println("Failed to read config from file: " + file.getAbsolutePath());
                e.printStackTrace();
            }
        }

        // Fallback to classpath resource loader
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("Configurations/Configuration.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Could not find Configuration.properties on classpath or filesystem.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String str) {
        String value = properties.getProperty(str);
        if (value != null) {
            return value;
        } else {
            throw new RuntimeException("Property '" + str + "' is not specified in the Configuration.properties file.");
        }
    }
}
