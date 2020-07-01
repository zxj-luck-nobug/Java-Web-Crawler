package com.crawler.parser.proxy;

import com.crawler.config.TagStore;
import com.crawler.https.HttpCrawlerClient;
import com.crawler.model.IpAddress;
import com.crawler.parser.CrawlerParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/jhao104/proxy_pool)参考
 * @author wencai.xu
 */
public class Six6ProxyParser extends CrawlerParser {

    private static Logger logger = LoggerFactory.getLogger(Six6ProxyParser.class);

    private static List<IpAddress> ips = new ArrayList<>();

    @Override
    public void parser(String html) {
        super.init(html);
        Element table = document.getElementsByTag(TagStore.table.name()).get(2);
        Elements trs = table.getElementsByTag(TagStore.tr.name());
        for(int n = 1; n < trs.size(); n++){
            Elements tds = trs.get(n).getElementsByTag(TagStore.td.name());
            ips.add(new IpAddress(tds.get(0).text(),Integer.parseInt(tds.get(1).text())));
        }
    }

    public static void main(String[] args) {
        HttpCrawlerClient client = new HttpCrawlerClient();
        String body = null;
        try {
            body = client.doGet("http://www.66ip.cn/", null).body().string();
        } catch (IOException e) {
            logger.warn("XiCi Proxy {}",e.getMessage());
        }
        new Six6ProxyParser().parser(body);
        ips.forEach(x-> System.out.println(x.getIp()+":"+x.getPort()));
    }
}

