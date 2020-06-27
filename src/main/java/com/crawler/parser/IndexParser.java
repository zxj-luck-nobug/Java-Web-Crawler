package com.crawler.parser;


/**
 * 首页解析
 * @author wencai.xu
 */
public class IndexParser extends CrawlerParser{

    @Override
    public void parser(String html) {
        //初始化文档解析
        init(html);
        //Uri获取并放到缓存中
        classifySetIndexUriToCacheByDocument(document);
    }
}
