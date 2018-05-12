package cn.zjnktion.his.server.util;

import cn.zjnktion.his.server.configuration.HttpClientPool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zjnktion
 */
@Configuration
public class HttpConnectionConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public HttpConnectionUtil httpConnectionUtil(HttpClientPool httpClientPool) {
        return new HttpConnectionUtil(httpClientPool);
    }
}
