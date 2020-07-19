package com.crawler.parser;

import com.crawler.config.AttributeKeys;
import com.crawler.config.TagStore;
import com.crawler.config.constant.Constants;
import com.crawler.utils.UriUtils;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.collect.Maps;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.net.HttpRetryException;
import java.util.*;
import java.util.function.Function;

/**
 * @author wencai.xu
 */
public class CrawlerParser implements Parser{

    private static final int MAP_INITIAL_SIZE = 10;

    Document document;
    protected List<String> urls;
    protected Map<String,Object> attrs;


    private Document getDefaultDocument(String json){

        return Jsoup.parse(json);
    }

    void init(String html){
       document = getDefaultDocument(html);
       attrs = new HashMap<>(MAP_INITIAL_SIZE);
       urls = new LinkedList<>();
    }

    @Override
    public void parser(String html) {
        init(html);
        Element channelMenu = document.getElementById(Constants.APP);
        Elements elementsByClass = channelMenu.getElementsByClass("bili-wrapper");
        Element subNav = elementsByClass.get(0).getElementById("subnav");
        Elements li = subNav.getElementsByTag("li");

        for(int i = 0; i < li.size(); i++){
            String text = li.get(i).text();
            Element child = li.get(i).child(0);
            Attributes attributes = child.attributes();
            String href = attributes.get("href");
            System.out.println(UriUtils.createUri(href));
        }
    }

    void classifySetIndexUriToCacheByDocument(Document document) {
        Elements aElements = document.getElementsByTag(TagStore.a.name());
        if(aElements.isEmpty()){
            return;
        }
        //修改为异步
        int i = 0;
        for(Element element : aElements){
            String href = UriUtils.createUri(element.attr(AttributeKeys.href.name()));
            if(UriUtils.hasVideo(href)){
                System.out.println(href);
                i++;
                continue;
            }
            if(UriUtils.hasChannel(href)){
                //放入到缓存中
            }
        }
        System.out.println(i);
    }
}
