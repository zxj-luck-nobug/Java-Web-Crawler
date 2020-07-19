package com.crawler.config.boot;

import com.crawler.config.bloom.BloomUriFilter;
import com.crawler.config.bloom.Filter;
import com.crawler.https.HttpCrawlerClient;
import com.crawler.model.HttpHeader;
import com.crawler.parser.IndexParser;
import com.crawler.parser.VideoParser;
import com.crawler.utils.PropertiesUtils;
import com.crawler.utils.UriUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 程序启动器
 *
 * @author wencai.xu
 */
public class ApplicationBoot {

    public static void init(){}

    private static final String HEADERS="headers.";

    private static PropertiesUtils properties = PropertiesUtils.getInstance();
    private static Filter<String> uriFilter = new BloomUriFilter();

    private List<HttpHeader> loadHttpHeaders(String prefix){
        Map<String, String> headerMap = properties.getKeyStartWith(prefix);
        List<HttpHeader> headers = new ArrayList<>();
        if(headerMap.isEmpty()){
            return headers;
        }
        headerMap.keySet().forEach(key->{
            HttpHeader header = new HttpHeader();
            header.setKey(key.replace(prefix,""));
            header.setValue(headerMap.get(key));
            headers.add(header);
        });
        return headers;
    }

    public void start(String uri) throws IOException {
        HttpCrawlerClient client = new HttpCrawlerClient();
        if(uriFilter.filter(uri)){
            String string = client.doGet(uri, loadHttpHeaders(HEADERS)).body().string();
            if(UriUtils.hasVideo(uri)){
                new VideoParser().parser(string);
            }else {
                new IndexParser().parser(string);
            }
        }
    }
}
