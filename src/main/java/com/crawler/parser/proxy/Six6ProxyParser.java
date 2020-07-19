package com.crawler.parser.proxy;

import com.crawler.config.AttributeKeys;
import com.crawler.config.TagStore;
import com.crawler.https.HttpCrawlerClient;
import com.crawler.model.AreaAddress;
import com.crawler.model.IpAddress;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * https://github.com/jhao104/proxy_pool
 * @author wencai.xu
 */
public class Six6ProxyParser extends ProxyParser implements Parser {

    private static Logger logger = LoggerFactory.getLogger(Six6ProxyParser.class);

    private static List<IpAddress> ips = new ArrayList<>();
    private static List<AreaAddress> areas = new ArrayList<>();
    private static Set<String> pages = new HashSet<>();
    private static List<String> other = Arrays.asList("http://www.66ip.cn/","http://www.66ip.cn/index");
    private static final String hosts = "http://www.66ip.cn";

    @Override
    public void parser(String uri) {
        super.init(uri);
        Elements tablesElements = document.getElementsByTag(TagStore.table.name());
        Element table = tablesElements.get(2);
        Elements trs = table.getElementsByTag(TagStore.tr.name());

        for(int n = 1; n < trs.size(); n++){
            Elements tds = trs.get(n).getElementsByTag(TagStore.td.name());
            ips.add(new IpAddress(tds.get(0).text(),Integer.parseInt(tds.get(1).text()),tds.get(2).text(),1,true,false,""));
        }
        ips.forEach(System.out::println);
    }

    public static void executeHostParser(String host){
        String body = Parser.execute(host, null);
        Document parse = Jsoup.parse(body);
        Elements tablesElements = parse.getElementsByTag(TagStore.table.name());
        Element areasElement = tablesElements.get(1);
        Elements areasLis = areasElement.getElementsByTag(TagStore.li.name());

        for(int i = 1; i <areasLis.size(); i++){
            String href = hosts+areasLis.get(i).child(0).attr(AttributeKeys.href.name());
            String addr = areasLis.get(i).child(0).text();
            areas.add(new AreaAddress(href,addr));
        }

        Element pageList = parse.getElementById("PageList");
        Elements children = pageList.children();

        for(int i = 1; i < children.size(); i++){
            String attr = children.get(i).attr(AttributeKeys.href.name());
            if(StringUtils.isNotEmpty(attr) && !"javascript:void();".equals(attr)){
                pages.add(hosts+attr);
                System.out.println(attr);
            }
        }
    }

    static List<String> getSockets(String uri){
        HttpCrawlerClient client = new HttpCrawlerClient();
        String body = null;
        try {
            body = client.doGet(uri, null).body().string();
        } catch (IOException e) {
            logger.warn("XiCi Proxy {}",e.getMessage());
        }
        new Six6ProxyParser().parser(body);
        return ips.stream()
                     .map(x -> x.getIp() + ":" + x.getPort())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        new Six6ProxyParser().parser("http://www.66ip.cn/");
    }
}

