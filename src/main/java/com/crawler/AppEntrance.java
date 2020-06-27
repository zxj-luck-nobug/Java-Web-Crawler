package com.crawler;



import com.crawler.config.boot.ApplicationBoot;
import com.crawler.https.HttpCrawlerClient;
import com.crawler.model.HttpHeader;
import com.crawler.parser.IndexParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wencai.xu
 */
public class AppEntrance {

    static {
        ApplicationBoot.init();
    }

    public static void main( String[] args ) throws IOException {
        HttpCrawlerClient client = new HttpCrawlerClient();
        HttpHeader header = new HttpHeader();
        header.setKey("User-Agent");
        header.setValue("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36");
        List<HttpHeader> headers = new ArrayList<>();
        headers.add(header);
        String string = client.doGet("https://www.bilibili.com/", headers).body().string();
        //抓取之前使用布隆过滤器
        new IndexParser().parser(string);

    }
}
