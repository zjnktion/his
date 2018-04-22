package cn.zjnktion.his.server.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zjnktion
 */
@Configuration
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientConfiguration {

    @Bean
    @ConditionalOnMissingBean(HttpClientPool.class)
    public HttpClientPool httpClientPool(HttpClientProperties httpClientProperties) {
        return new HttpClientPool(httpClientProperties);
    }
}
