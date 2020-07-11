package com.crawler.model;

import lombok.Data;
import lombok.ToString;

public class IpAddress {

    private String ip;

    private int port;

    private String position;

    public IpAddress(String ip, int port, String position) {
        this.ip = ip;
        this.port = port;
        this.position = position;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString(){
        return "[\"ip:\""+this.ip+",\"port\":"+this.port+",\"position\":"+this.position+"]";
    }
}
