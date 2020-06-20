package com.crawler;

/**
 * @author wencaixu<br />
 * @since JDK 1.8
 */
public interface HttpAction {

    void doGet();

    void doPost();

    void doDelete();

    void doPut();
}
