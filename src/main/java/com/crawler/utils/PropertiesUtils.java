package com.crawler.utils;

import com.crawler.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Supplier;

/**
 * @author wencai.xu
 */
public class PropertiesUtils {

    private static PropertiesUtils instance = new PropertiesUtils();
    private static Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);

    public static PropertiesUtils getInstance(){
        return instance;
    }

    public Map<String,String> getKeyStartWith(String prefix){
        Map<String,String> paramPairs = new HashMap<>(3);
        try(InputStream resourceAsStream = getBundleResourceAsStream()) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            Set<String> propertyNames = properties.stringPropertyNames();
            for(String name : propertyNames){
                paramPairs.put(name,properties.getProperty(name));
            }
        } catch (IOException ex) {
            if(LOGGER.isWarnEnabled()){
                LOGGER.warn("{} IO exception {}","Properties Utils",ex.getMessage());
            }
        }
        return paramPairs;
    }

    public String getValueBy(String key){
        try(InputStream resourceAsStream = getBundleResourceAsStream()){
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            return properties.getProperty(key);
        } catch (IOException e) {
            if(LOGGER.isWarnEnabled()){
                LOGGER.warn("{} IO exception {}","Properties Utils",e.getMessage());
            }
        }
        return null;
    }

    private static InputStream getBundleResourceAsStream(){
        return
                PropertiesUtils.class
                        .getClassLoader()
                    .getResourceAsStream(Configuration.APPLICATION_WEBSITE_PROPERTIES);
    }
}
