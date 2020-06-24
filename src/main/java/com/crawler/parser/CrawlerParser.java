package com.crawler.parser;

import com.crawler.config.constant.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * @author wencai.xu
 */
public class CrawlerParser implements Parser{

    private static final int MAP_INITIAL_SIZE = 10;

    private Document document;
    protected List<String> urls;
    protected Map<String,Object> attrs;


    protected Document getDefaultDocument(String json){
        Document document = Jsoup.parse(json);
        return document;
    }

    private void init(String html){
       document = getDefaultDocument(html);
       attrs = new HashMap<>(MAP_INITIAL_SIZE);
       urls = new LinkedList<>();
    }

    @Override
    public void parser(String html) {
        init(html);
        Element channelMenu = document.getElementById(Constants.APP);
        Elements elementsByClass = channelMenu.getElementsByClass("sub-channel-m");
        Attributes attributes = elementsByClass.get(0).attributes();
        for(Attribute attribute : attributes){
            System.out.println(attribute.getKey() + " " + attribute.getValue());
        }
    }

}
