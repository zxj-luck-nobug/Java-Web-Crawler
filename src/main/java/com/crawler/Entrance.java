package com.crawler;



import com.crawler.model.HttpHeader;
import com.crawler.parser.PageParser;
import com.crawler.parser.UrlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class Entrance
{
    /**
     * Main
     *
     * @param args args
     * @throws IOException io exception
     */
    public static void main( String[] args ) throws IOException {
        HttpCrawlerClient client = new HttpCrawlerClient();
        HttpHeader header = new HttpHeader();
        header.setKey("User-Agent");
        header.setValue("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36");
        List<HttpHeader> headers = new ArrayList<>();
        headers.add(header);
        String string = client.doGet("https://www.bilibili.com/v/cinephile/?spm_id_from=333.5.b_7072696d6172794368616e6e656c4d656e75.84", headers).body().string();
        new PageParser().parser(string);
        new UrlParser().parser(string);
    }
}
