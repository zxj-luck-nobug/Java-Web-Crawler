package com.crawler;

import com.crawler.config.ConfigFilePath;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author wencai.xu
 */
public class PropertiesUtils {

    public static Map<String,String> getKeyStartWith(String prefix){
        //结项时需要修改
        Map<String,String> paramPairs = new HashMap<>(3);
        try(InputStream resourceAsStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(ConfigFilePath.ANNO_PATH)) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            Set<String> propertyNames = properties.stringPropertyNames();
            for(String name : propertyNames){
                paramPairs.put(name,properties.getProperty(name));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return paramPairs;
    }
}
