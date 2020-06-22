package com.crawler.config.anno;

public class AnnoTest {
    @InjectionParam(value = "hello chen")
    public String anno;

    public String getAnno() {
        return anno;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
