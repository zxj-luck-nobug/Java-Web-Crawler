package com.crawler.parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Url parser
 *
 * @author wencai.xu
 */
public class UrlParser extends CrawlerParser implements Parser {
    private Set<String> urls=new HashSet<>();

    @Override
    public void parser(String html) {
        Document document = Jsoup.parse(html);

        Elements as = document.getElementsByTag("a");
        if(Objects.nonNull(as)){
            for(Element element : as){
                System.out.println(element.attributes().get("href"));
            }
        }
    }
}
