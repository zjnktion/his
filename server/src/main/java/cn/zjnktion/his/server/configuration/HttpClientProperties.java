package cn.zjnktion.his.server.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @author zjnktion
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(HttpClientProperties.PREFIX)
public class HttpClientProperties {

    public static final String PREFIX = "http.client";

    @NonNull
    private Integer maxTotal = 1000;
    @NonNull
    private Integer defaultMaxPerRoute = 20;
    @NonNull
    private Long connectionTimeout = TimeUnit.SECONDS.toMillis(1);
    @NonNull
    private Long connectionRequestTimeout = TimeUnit.SECONDS.toMillis(2);
    @NonNull
    private Long socketTimeout = TimeUnit.SECONDS.toMillis(2);
}
