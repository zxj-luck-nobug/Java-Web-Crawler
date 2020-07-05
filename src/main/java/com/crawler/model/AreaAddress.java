package com.crawler.model;


public class AreaAddress {

    private String uri;

    private String address;

    public AreaAddress(String uri, String address) {
        this.uri = uri;
        this.address = address;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
