package com.crawler;



import java.io.IOException;

/**
 * Hello world!
 */
public class Entrance
{
    /**
     * Main
     *
     * @param args args
     */
    public static void main( String[] args ) throws IOException {
        HttpCrawlerClient client = new HttpCrawlerClient();
        String string = client.doGet("http://39.106.113.6:8888/seeAllMovies", null).body().string();
        System.out.println(string);
    }
}
