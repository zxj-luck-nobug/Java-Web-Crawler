package com.crawler.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Page parser
 *
 * @author wencai.xu
 */
public class PageParser extends CrawlerParser implements Parser {
    @Override
    public void parser(String html) {
        Document parse = Jsoup.parse(html);
        Element app = parse.getElementById("app");
        Attributes attributes = app.attributes();
        for(Attribute attribute : attributes){
            System.out.println(attribute.getKey() + " " + attribute.getValue());
        }
    }
}
