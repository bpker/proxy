package com.kindee.proxyapi.controller;

import com.kindee.proxyapi.service.ProxyService;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lixuezhao on 2018/4/26.
 */

@RestController
@Slf4j
public class ProxyController {

    @Autowired
    private ProxyService proxyService;

    @PostMapping(value = "/kingdee/proxy")
    public Object getProxy(@RequestParam String type, @RequestParam int num, HttpServletRequest request){
        String address = request.getRemoteHost();
        String typeMessage  = type.equals("0")? "收费":"免费";
        log.info("收到{}请求,当前请求{}个{}代理ip", address, num, typeMessage);
        return proxyService.getProxy(type, num);

    }

}
