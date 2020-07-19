package com.crawler.parser.proxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * @author wencai.xu
 */
public class ProxyParser implements Parser {

    protected Document document;

    private Document getDefaultDocument(String json){

        return Jsoup.parse(json);
    }

    protected void init(String uri){
        String html = Parser.execute(uri,null);
        document = getDefaultDocument(html);
    }

    @Override
    public void parser(String uri) {}
}
