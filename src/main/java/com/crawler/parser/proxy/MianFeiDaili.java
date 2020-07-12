package com.crawler.parser.proxy;


import com.crawler.config.TagStore;
import com.crawler.model.IpAddress;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class MianFeiDaili {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String HOST = "https://ip.jiangxianli.com/?page=2";
    private final int pages = 2;

    public List<IpAddress> getProxy() {
        List<IpAddress> addresses = new ArrayList<>();
        int j = 1;
        //while(j <= pages){
            try {
                String body = Parser.execute(HOST, null);
                if(StringUtils.isNotEmpty(body)){
                    Document parse = Jsoup.parse(body);
                    Elements tr = parse.getElementsByTag(TagStore.table.name()).get(0).select(TagStore.tbody.name()).select(TagStore.tr.name());
                    for(int i = 0; i < tr.size(); i++){
                        Elements tds = tr.get(i).getElementsByTag(TagStore.td.name());
                        IpAddress address = new IpAddress();
                        address.setIp(tds.get(0).text());
                        address.setPort(Integer.parseInt(tds.get(1).text()));
                        address.setVisible(true);
                        address.setHttp("HTTP".equals(tds.get(3).text()));
                        address.setPosition(tds.get(5).text());
                        address.setCarrier(tds.get(4).text());
                        address.setRtt(getRtt(tds.get(7).text()));
                        addresses.add(address);
                    }
                    j++;
                }
            }catch (Exception e){
                logger.warn(e.getMessage());
            }
        //}
        return addresses;
    }

    private float getRtt(String rtt){
        if(rtt.endsWith("毫秒")){
            return Float.parseFloat(rtt.replace("毫秒",""));
        }
        return Float.parseFloat(rtt.replace("秒",""));
    }
}
