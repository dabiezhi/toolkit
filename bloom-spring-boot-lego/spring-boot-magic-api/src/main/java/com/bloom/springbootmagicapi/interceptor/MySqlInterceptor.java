package com.bloom.springbootmagicapi.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.ssssssss.magicapi.core.context.RequestEntity;
import org.ssssssss.magicapi.modules.db.BoundSql;
import org.ssssssss.magicapi.modules.db.inteceptor.DefaultSqlInterceptor;

/**
 * @author curry
 * Created by on 2022-11-08 下午2:34
 */
@Component
public class MySqlInterceptor extends DefaultSqlInterceptor {

    @Override
    public Object postHandle(BoundSql boundSql, Object result, RequestEntity requestEntity) {
        Logger logger = LoggerFactory.getLogger(requestEntity == null ? "Unknown"
            : requestEntity.getMagicScriptContext().getScriptName());
        logger.info("执行完毕SQL：===============>");
        return result;
    }
}