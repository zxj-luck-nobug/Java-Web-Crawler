package com.crawler.config.bloom;


/**
 * 过滤器接口
 *
 * @author wencai.xu
 */
public interface Filter<T> {
    boolean filter(T url);
}
