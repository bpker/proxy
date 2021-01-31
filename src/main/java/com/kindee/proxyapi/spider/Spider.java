package com.kindee.proxyapi.spider;

import com.kindee.proxyapi.model.Proxy;
import com.kindee.proxyapi.spider.utils.DateUtil;
import com.kindee.proxyapi.spider.utils.MD5Util;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixuezhao on 2018/5/25.
 */
public class Spider {
    private final String ChARGE_PROXY_URL = "http://dps.kuaidaili.com/api/getdps/?orderid=957918250256056&num={#num}&ut=1&format=xml&sep=1";
    private final String FREE_PROXY__URL = "http://www.89ip.cn/tqdl.html?num=600";
    private JsoupDownloader downloader = new JsoupDownloader();
    public List<Proxy> crawlFreeProxy(){

        Document doc = null;
        try {
            doc = downloader.get(FREE_PROXY__URL);
//            System.out.println(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        RegexSelector selector = new RegexSelector(doc.outerHtml());
        List<String> ipStrs = selector.selectList("\\d+.\\d+.\\d+.\\d+:\\d{1,5}");
        List<Proxy> proxies = new ArrayList<Proxy>();
        for (String ipStr: ipStrs){
            selector = new RegexSelector(ipStr);
            String ip = selector.selectOne("(.*):(.*)", 1);
            String port = selector.selectOne("(.*):(.*)", 2);

            if (ip.length() > 0 && port.length() > 0 )
                proxies.add(new Proxy(ip, port, "免费代理", DateUtil.getCurrentTime(), 1, MD5Util.md5(ip + port), FREE_PROXY__URL));

        }

        return proxies;
    }

    public List<Proxy> crawlChargeProxy(int num){

        Document doc = null;
        try {
            String url = ChARGE_PROXY_URL.replace("{#num}", String.valueOf(num));
            doc = downloader.get(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        RegexSelector selector = new RegexSelector(doc.outerHtml());
        List<String> ipStrs = selector.selectList("\\d+.\\d+.\\d+.\\d+:\\d{1,5}");
        List<Proxy> proxies = new ArrayList<Proxy>();
        for (String ipStr: ipStrs){
            selector = new RegexSelector(ipStr);
            String ip = selector.selectOne("(.*):(.*)", 1);
            String port = selector.selectOne("(.*):(.*)", 2);

            if (ip.length() > 0 && port.length() > 0 && !proxies.contains(ip))
                proxies.add(new Proxy(ip, port, "收费代理"));

        }

        return proxies;
    }

}
