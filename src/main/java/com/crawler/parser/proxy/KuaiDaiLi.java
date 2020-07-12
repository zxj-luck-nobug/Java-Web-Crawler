package com.crawler.parser.proxy;


import com.crawler.config.TagStore;
import com.crawler.model.IpAddress;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * 爬取快代理的免费代理
 */
public class KuaiDaiLi  implements Parser {

    //创建队列暂存页面接口
    private static Queue<String> pageUrls = new LinkedList<>();

    private static Set<String> afterParser = new HashSet<>();
    private static final String hosts = "https://www.kuaidaili.com";

    public static void main(String[] args) {
        int count = 0;
        //首次爬取设置host url
        pageUrls.add("https://www.kuaidaili.com/free/");

        System.out.println(pageUrls.isEmpty());
        while (!pageUrls.isEmpty()) {
            count++;
            if(count == 5){
                break;
            }
            String currentUri = pageUrls.poll();
            String execute = Parser.execute(currentUri, null);
            if(StringUtils.isEmpty(execute)){
                continue;
            }
            Document document = Jsoup.parse(execute);
            Elements tablesElements = document.select("div[id=list]").select(TagStore.table.name());
            if(!tablesElements.isEmpty()){
                Element table = tablesElements.get(0);
                Elements tbody = table.select(TagStore.tbody.name());
                if(!tbody.isEmpty()){
                    Elements trs = tbody.get(0).select(TagStore.tr.name());
                    if (!trs.isEmpty()) {
                        // 0,1,3,5
                        for (int n = 1; n < trs.size(); n++) {
                            Elements tds = trs.get(n).getElementsByTag(TagStore.td.name());
                            IpAddress ipAddress = new IpAddress(
                                    tds.get(0).text(),
                                    Integer.parseInt(tds.get(1).text()),
                                    StringUtils.split(tds.get(4).text())[0],
                                    Float.parseFloat(tds.get(5).text().replace("秒","")),
                                    "HTTP".equals(tds.get(3).text()),
                                    false,
                                    StringUtils.split(tds.get(4).text())[1]);
                            System.out.println(ipAddress.toString());
                        }
                    }
                    //获取分页
                    Elements as = document.select("div[id=listnav]").select("a");
                    for (int i = 0; i < as.size(); i++) {
                        String href = as.get(i).attr("href");
                        System.out.println(hosts + href);
                        if (StringUtils.isNotEmpty(href)) {
                            if (!afterParser.contains(hosts + href)) {
                                pageUrls.add(hosts + href);
                                afterParser.add(hosts + href);
                            }
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
