package com.crawler;



import com.crawler.config.boot.ApplicationBoot;

import java.io.IOException;

/**
 * 程序入口
 * @author wencai.xu
 */
public class AppEntrance {

    static {
        ApplicationBoot.init();
    }

    public static void main( String[] args ) throws IOException {
        ApplicationBoot applicationBoot = new ApplicationBoot();
        applicationBoot.start("https://www.bilibili.com/");
        applicationBoot.start("https://www.bilibili.com/video/BV1qa4y1h7rG");
    }
}
