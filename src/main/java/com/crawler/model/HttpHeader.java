package com.crawler.model;

/**
 * Http header
 * @author Administrator
 */
public class HttpHeader {

    private String key;
    private String value;

    /**
     * Http header
     */
    public HttpHeader() {
    }

    /**
     * Http header
     *
     * @param key   key
     * @param value value
     */
    public HttpHeader(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets key *
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key *
     *
     * @param key key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets value *
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value *
     *
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
