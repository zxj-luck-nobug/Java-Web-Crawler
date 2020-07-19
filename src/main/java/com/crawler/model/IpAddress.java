package com.crawler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 抓取代理信息
 *
 * @author wencai.xu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IpAddress {
    /**
     * 代理IP地址
     */
    private String ip;
    /**
     * 端口号
     */
    private int port;
    /**
     * 代理位置
     */
    private String position;
    /**
     * 代理响应时间
     */
    private float rtt;
    /**
     * 是否透明
     */
    private boolean visible;
    /**
     * 是Https还是http
     */
    private boolean http;
    /**
     * 代理商
     */
    private String carrier;
}
