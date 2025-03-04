package JavaUtils;

import Constants.ApplicationConstant;
import Constants.PathConstant;

import java.io.*;
import java.util.Properties;

public class GenericJavaUtilities {

    public static Properties properties;
    public static File file;


    public void DynamicPropertiesManager(String propertiesFile) {
        properties = new Properties();
        String fileName = PathConstant.Current_Execution_Properties_File + propertiesFile + ".properties";
        file = new File(fileName);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                properties.load(fis);
                System.out.println("Loaded properties from: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
                System.out.println("Created new properties file: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "");
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    public void saveProperties() {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            properties.store(fos, "Updated Properties File");
            System.out.println("Saving properties file at: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


