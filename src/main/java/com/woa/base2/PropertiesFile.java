package com.woa.base2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {
    public static String getInfo( String value) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/config.properties"));

        String info = properties.getProperty(value);

        return info;
    }

}
