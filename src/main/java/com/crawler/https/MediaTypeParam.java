package com.crawler.https;

/**
 * Media type
 *
 * @author wencai.xu
 */
public enum MediaTypeParam {
    /**
     * JSON格式
     */
    JSON("application/json; charset=utf-8"),
    /**
     * MarkDown格式
     */
    MARKDOWN("text/x-markdown; charset=utf-8");

    MediaTypeParam(String fullname){
        this.fullname=fullname;
    }
    private String fullname;

    /**
     * Gets fullname *
     *
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Sets fullname *
     *
     * @param fullname fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
