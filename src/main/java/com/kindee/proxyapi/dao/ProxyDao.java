package com.kindee.proxyapi.dao;

import com.kindee.proxyapi.model.Proxy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by lixuezhao on 2018/5/31.
 */
@Repository
@AllArgsConstructor
public class ProxyDao {

    private JdbcTemplate jdbcTemplate;


    public void upsertProxy(Proxy proxy){

        String upsertSql = "insert into ip_proxy (ip, port, update_time, update_num, record_md5, source) values(?, ?, ?, ?, ?, ?)" +
                "on duplicate key UPDATE update_time=?, update_num=update_num + 1";

        Object[] params = { proxy.getIp(), proxy.getPort(), proxy.getUpdateTime(),
                            proxy.getUpdateNum(), proxy.getRecordMd5(), proxy.getSource(),
                            proxy.getUpdateTime()
                          };
        jdbcTemplate.update(upsertSql, params);

    }


}
