package com.crawler;

import com.crawler.model.IpAddress;
import com.crawler.parser.proxy.MianFeiDaili;
import com.crawler.parser.proxy.Six6ProxyParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<IpAddress> proxy1 = new com.crawler.parser.proxy.Main().getProxy();
        for(IpAddress address : proxy1){
            System.out.println(address.toString());
        }
        List<IpAddress> proxy = new MianFeiDaili().getProxy();
        for(IpAddress address : proxy){
            System.out.println(address.toString());
        }

        new Six6ProxyParser().parser("http://www.66ip.cn/");
    }

}
