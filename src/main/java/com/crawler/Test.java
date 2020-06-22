package com.crawler;

import com.crawler.config.ConfigFilePath;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test {

    public static void main(String[] args) throws IOException {
        System.out.println(PropertiesUtils.getKeyStartWith("anno"));
    }
}
