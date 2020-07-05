package com.crawler.parser.proxy;


import com.crawler.https.HttpCrawlerClient;
import com.crawler.model.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public interface Parser {

   Logger logger = LoggerFactory.getLogger(Parser.class);

   static String execute(String uri, List<HttpHeader> headers){
      HttpCrawlerClient client = new HttpCrawlerClient();
      String body = null;
      try {
         body = client.doGet(uri, headers).body().string();
      } catch (IOException e) {
         logger.warn("executor {} proxy {}","平台",e.getMessage());
      }
      return body;
   }

   void parser(String uri);
}
