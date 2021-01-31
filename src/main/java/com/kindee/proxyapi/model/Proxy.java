package com.kindee.proxyapi.model;


import com.kindee.proxyapi.spider.utils.DateUtil;
import com.kindee.proxyapi.spider.utils.MD5Util;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import net.sf.json.JSONString;
import org.springframework.stereotype.Component;

/**
 * Created by lixuezhao on 2018/5/24.
 */
@Component
@Data
public class Proxy implements JSONString{

    private String ip;
    private String port;
    private String type;
    private String updateTime;
    private int updateNum;
    private String recordMd5;
    private String source;


    public Proxy(){}
    public Proxy(String ip, String port, String type) {
        this.ip = ip;
        this.port = port;
        this.type = type;
    }

    public Proxy(String ip, String port, String type, String updateTime, int updateNum, String recordMd5, String source) {
        this.ip = ip;
        this.port = port;
        this.type = type;
        this.updateTime = updateTime;
        this.updateNum = updateNum;
        this.recordMd5 = recordMd5;
        this.source = source;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Proxy) {
            Proxy proxy = (Proxy) obj;
            return (this.getRecordMd5().equals(proxy.getRecordMd5()));
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return this.getRecordMd5().hashCode();
    }

    @Override
    public String toJSONString() {

        return "{\"ip\":\"" + ip + "\",\"port\":\"" + port + "\",\"type\":\"" + type + "\"}";

    }


    public static void main(String[] args) {
        Proxy p = new Proxy("111", "222", "免费代理", DateUtil.getCurrentTime(), 1, MD5Util.md5("111" + "222"), "111");
        Proxy p2 = new Proxy("111", "222", "免费代理", DateUtil.getCurrentTime(), 1, MD5Util.md5("111" + "222"), "111");

        System.out.println(p.equals(p2));
        System.out.println(p.hashCode()==p2.hashCode());

    }



}
