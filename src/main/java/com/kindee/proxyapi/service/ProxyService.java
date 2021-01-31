package com.kindee.proxyapi.service;

import com.kindee.proxyapi.model.ApiResponse;
import com.kindee.proxyapi.model.Proxy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.kindee.proxyapi.spider.ProxyGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixuezhao on 2018/5/25.
 */
@Service
@AllArgsConstructor
@Slf4j
public class ProxyService {

    @Autowired
    private ProxyGenerator proxyGenerator;

    @Scheduled(fixedDelay = 1 * 60 * 60 * 1000)
    private void refreshFreeIPQueue(){
        log.info("开始更新代理ip队列");
        proxyGenerator.refreshFreeIPQueue();
    }

    public Object getProxy(String type, int num) {
        List<Proxy> proxies = null;
        if (type.equals("0")){
            proxies = proxyGenerator.getChargeProxy(num);
        } else{
            proxies = proxyGenerator.getFreeProxy(num);
        }

        if (proxies == null || proxies.size() == 0)
            return ApiResponse.getFailureResponse();
        JSONArray jsonArray = new JSONArray();
        for (Proxy p: proxies) {
            jsonArray.add(p);
        }
        return ApiResponse.getSuccessResonse(jsonArray);
    }

}
