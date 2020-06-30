package com.crawler.config.bloom;

import com.google.common.hash.BloomFilter;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;

import static com.google.common.hash.BloomFilter.*;
import static com.google.common.hash.Funnels.*;

/**
 * 布隆过滤器
 *
 * @author wencai.xu
 */
public class BloomUriFilter implements Filter<String>{

    private BloomFilter<String> filter;

    public BloomUriFilter() {
        int expectedInsertions = 10000;
        filter = create(stringFunnel(Charset.defaultCharset()), expectedInsertions);
    }

    @Override
    public boolean filter(String url) {
        if(StringUtils.isEmpty(url)){
            return true;
        }
        if(filter.mightContain(url)){
            return false;
        }else{
            filter.put(url);
            return true;
        }
    }
}
