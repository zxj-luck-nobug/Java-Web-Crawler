package com.crawler.parser;


/**
 * @author wencai.xu
 */
public class VideoParser extends CrawlerParser {
    @Override
    public void parser(String html) {
        //初始化文档解析
        super.init(html);
        //书写时打开
        System.out.println(html);
    }
}
