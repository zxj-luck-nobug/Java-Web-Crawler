package com.crawler.parser.proxy;


import com.crawler.https.HttpCrawlerClient;
import com.crawler.model.HttpHeader;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public interface Parser {

   Logger logger = LoggerFactory.getLogger(Parser.class);

   static String execute(String uri, List<HttpHeader> headers){
      HttpCrawlerClient client = new HttpCrawlerClient();
      String body = null;
      try {
         ResponseBody response = client.doGet(uri, headers).body();
         if(Objects.isNull(response)){
            return null;
         }
         body = response.string();
      } catch (IOException e) {
         logger.warn("executor {} proxy {}","平台",e.getMessage());
      } catch (Exception ex){
         logger.warn("NLP {}",ex.getMessage());
      }
      return body;
   }

   void parser(String uri);
}
