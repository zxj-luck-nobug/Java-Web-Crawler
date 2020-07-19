package com.crawler.utils;

import com.google.common.collect.Maps;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class Main {
    /**
     * 函数
     * @return
     */
    private static String parserHtmlBy(Document document, Function<Document, String> idFunction){
        if(Objects.isNull(idFunction) || Objects.isNull(document)){
            return "";
        }
        return idFunction.apply(document);
    }

    public static void main(String[] args) throws IOException {


        OkHttpClient client = new OkHttpClient();
        String uri = "https://www.bilibili.com/";
        Response httpBody = client.newCall(new Request.Builder().url(uri)
                        .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")
                        .build()).execute();
        if(!httpBody.isSuccessful()){
            throw new HttpRetryException("Http Request Exception",httpBody.code());
        }
        Document html = Jsoup.parse(httpBody.body().string());
        // 不同的页面可能使用Function不一致，所以这里使用Function对函数进行封装
        // 行为参数化，行为idFunction对html参数进行处理
        String headWrapper = parserHtmlBy(html, document -> {
            Element idToValue = html.getElementById("reportFirst1");
            Map<String, String> attrs = Maps.newHashMap();
            Optional.ofNullable(idToValue).ifPresent(t -> {
                idToValue.attributes().forEach(attribute -> {
                    attrs.put(attribute.getKey(), attribute.getValue());
                });
            });
            return new GsonBuilder().create().toJson(attrs);
        });
        System.out.println("哔哩哔哩 " + headWrapper);
    }
}
