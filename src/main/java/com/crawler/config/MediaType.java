package com.crawler.config;

/**
 *
 * @author wencai.xu
 */
public enum MediaType {
    /** JSON格式 */
    JSON("application/json; charset=utf-8"),
    /** MarkDown格式*/
    MARKDOWN("text/x-markdown; charset=utf-8");

    MediaType(String fullname){
        this.fullname=fullname;
    }
    private String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
