package com.crawler.parser.proxy;


import com.crawler.config.TagStore;
import com.crawler.model.IpAddress;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 爬取66ip代理网站
 */
public class Main implements Parser {
    //创建队列暂存页面接口
    private static Queue<String> pageUrls = new LinkedList<>();
    private static Set<String> afterParser = new HashSet<>();
    private static final String hosts = "http://www.66ip.cn";

    //先实现，后封装
    public static void main(String[] args) {
        pageUrls.add("http://www.66ip.cn/");//首次爬取设置host url
        while (!pageUrls.isEmpty()) {
            String currentUri = pageUrls.poll();
            System.out.println(currentUri);
            Document document = Jsoup.parse(Parser.execute(currentUri, null));
            Elements tablesElements = document.select("div[id=main]").select(TagStore.table.name());
            if(!tablesElements.isEmpty()){
                Element table = tablesElements.get(0);
                Elements trs = table.getElementsByTag(TagStore.tr.name());

                if (!trs.isEmpty()) {
                    for (int n = 1; n < trs.size(); n++) {
                        Elements tds = trs.get(n).getElementsByTag(TagStore.td.name());
                        System.out.println(new IpAddress(tds.get(0).text(), Integer.parseInt(tds.get(1).text()), tds.get(2).text(),1,true,true,"").toString());
                    }
                }
                Elements as = document.select("div[id=PageList]").select("a");
                for (int i = 0; i < as.size(); i++) {
                    String href = as.get(i).attr("href");
                    if (StringUtils.isNotEmpty(href) && !"javascript:void();".equals(href) && !"/index.html".equals(href)) {
                        if (!afterParser.contains(hosts + href)) {
                            pageUrls.add(hosts + href);
                            afterParser.add(hosts + href);
                        }
                    }
                }
            }

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parser(String uri) {

    }
}
