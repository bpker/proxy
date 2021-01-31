package com.kindee.proxyapi.dao;

import com.kindee.proxyapi.model.Proxy;
import com.kindee.proxyapi.spider.utils.DateUtil;
import com.kindee.proxyapi.spider.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by lixuezhao on 2018/5/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyDaoTest {

    @Autowired
    private ProxyDao proxyDao;

    @Test
    public void upsertProxy() throws Exception {
        Proxy p = new Proxy("111", "222", "免费代理", DateUtil.getCurrentTime(), 1, MD5Util.md5("111" + "222"), "111");
        proxyDao.upsertProxy(p);
    }

}