package webutils;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadProperties {

    public static final String WEB_PROPERTY_PATH = "src/test/java/web/resources/WebParams.properties";
    public static final String URL_PROPERTY_PATH = "src/test/java/web/resources/urls.properties";
    public static final String ENVIRONMENT_PROPERTY_PATH = "src/test/resources/environment.properties";
    public static final String ALLURE_PROPERTY_PATH = "src/test/resources/allure.properties";
    public static Properties prop = new Properties();

    static {

        loadProperties(WEB_PROPERTY_PATH);
        loadProperties(URL_PROPERTY_PATH);
        loadProperties(ENVIRONMENT_PROPERTY_PATH);
        loadProperties(ALLURE_PROPERTY_PATH);
    }

    /**
    * Load Properties file
    * @param pathToFile - file of the path
    */
    private static void loadProperties(String pathToFile){
        if (StringUtils.isEmpty(pathToFile))
            throw new RuntimeException("Path to the properties file is invalid! Please check -> " +pathToFile);
        try {
            prop.load(new FileInputStream(pathToFile));
        }catch (Exception e){
            System.out.println(e.getMessage());

        }

    }

}
