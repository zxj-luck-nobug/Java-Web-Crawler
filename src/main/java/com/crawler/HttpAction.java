package com.crawler;

import com.crawler.model.HttpHeader;
import okhttp3.Response;

import java.util.List;

/**
 * Http action
 *
 * @author wencaixu<br />
 * @since JDK 1.8
 */
public interface HttpAction {

    /**
     * Do get
     *
     * @param url     url
     * @param headers headers
     * @return the response
     */
    Response doGet(String url, List<HttpHeader> headers);

    /**
     * Do post
     */
    void doPost(String url, List<HttpHeader> headers);

    /**
     * Do delete
     */
    void doDelete(String url, List<HttpHeader> headers);

    /**
     * Do put
     */
    void doPut(String url, List<HttpHeader> headers);
}
