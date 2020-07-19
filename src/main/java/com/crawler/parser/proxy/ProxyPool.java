package com.crawler.parser.proxy;


import com.crawler.config.bloom.BloomUriFilter;
import com.crawler.config.bloom.Filter;

import java.util.List;
import java.util.Random;

public class ProxyPool {

    private static List<String>[] uris;

    private static Filter<String> uriFilter = new BloomUriFilter();

    static {
       // uris[0] = Six6ProxyParser.instance.getSockets("http://www.66ip.cn/");
    }

    private String randomProxy(){
        int random = new Random().nextInt() * (uris.length + 1);
        return uris[random].get(0);
    }
}
