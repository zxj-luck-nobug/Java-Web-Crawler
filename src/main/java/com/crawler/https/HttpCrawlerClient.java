package com.crawler.https;

import com.crawler.model.HttpHeader;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.List;

/**
 * Http crawler client
 *
 * @author wencaixu<br />
 * @since JDK 1.8
 */
public class HttpCrawlerClient implements HttpAction {

    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse(MediaTypeParam.JSON.name());
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpCrawlerClient.class);

    @Override
    public Response doGet(String url, List<HttpHeader> headers) {
        Request.Builder httpBuilder = new Request.Builder();
        if(null != headers && headers.size() > 0){
            headers.forEach(header->{
                httpBuilder.addHeader(header.getKey(),header.getValue());
            });
        }
        Request request = httpBuilder.url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()){
                throw new HttpRetryException("HttpRetry failure",response.code());
            }
            return response;
        } catch (IOException e) {
            LOGGER.warn("IO");
        }
        return null;
    }

    @Override
    public void doPost(String url, List<HttpHeader> headers) {

    }

    @Override
    public void doDelete(String url, List<HttpHeader> headers) {

    }

    @Override
    public void doPut(String url, List<HttpHeader> headers) {

    }

}
