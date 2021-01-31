package com.kindee.proxyapi.spider;


import com.kindee.proxyapi.dao.ProxyDao;
import com.kindee.proxyapi.model.Proxy;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by lixuezhao on 2018/5/24.
 */
@Component
public class ProxyGenerator {

    @Autowired
    private ProxyDao proxyDao;

    private static final Logger log = LoggerFactory.getLogger(ProxyGenerator.class);

    private final String VALIDATE_URL = "https://news.163.com/";
    private JsoupDownloader downloader = new JsoupDownloader();
    private Schedule freeIPQueue= new Schedule();
    private ExecutorService exec = Executors.newFixedThreadPool(50);
    private Spider spider = new Spider();

    public ProxyGenerator() {
        refreshFreeIPQueue();
    }

    /**
     * 取队首,放队尾
     * @return
     */
    public List<Proxy> getFreeProxy(int num){
//        if (num < 1 || freeIPQueue.size() == 0) return null;
        int counter = 0;
        List<Proxy> proxies = new ArrayList<Proxy>();
        while (freeIPQueue.hasNext() && ++counter <= num && counter <= freeIPQueue.size()){
            Proxy proxy = (Proxy)freeIPQueue.get();
            proxies.add(proxy);
            freeIPQueue.put(proxy);
        }

        return proxies;
    }

    /**
     * 取队首,放队尾
     * @return
     */
    public List<Proxy> getChargeProxy(int num){
        return spider.crawlChargeProxy(num);
    }

    /**
     * 验证入队列
     */
    public void refreshFreeIPQueue(){

        List<Proxy>  proxies = spider.crawlFreeProxy();
//        System.out.println(proxies);
        //采集失败，直接return
        if (proxies == null || proxies.size() == 0)
            return;
        freeIPQueue.removeAll();
        for (final Proxy p: proxies){

            exec.submit(new Runnable() {
                public void run() {
                    //都存储
                    proxyDao.upsertProxy(p);
                    if (validateProxy(p)){
                        freeIPQueue.put(p);
                        log.info("{}有效，当前ip队列有{}个有效ip", p, freeIPQueue.size());
                    }
                }
            });
        }
//        exec.shutdown();

    }

    private boolean validateProxy(Proxy p){
        JsoupDownloader d = new JsoupDownloader();
        Map<String,String> proxy = new HashMap<String, String>();
        proxy.put("IP", p.getIp());
        proxy.put("PORT", p.getPort());
        try {
            Document document = d.get(VALIDATE_URL, null, proxy);
//            System.out.println(document);
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

}
