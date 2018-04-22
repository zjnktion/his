package cn.zjnktion.his.server.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

/**
 * @author zjnktion
 */
@RequiredArgsConstructor
@Slf4j
public class HttpClientPool {

    private final HttpClientProperties httpClientProperties;

    private PoolingHttpClientConnectionManager cm;

    @PostConstruct
    public void init() {
        LayeredConnectionSocketFactory lcsf = null;
        try {
            lcsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        }
        catch (NoSuchAlgorithmException e) {
            log.warn("Build SslConnectionSocketFactory error.", e);
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("https", lcsf).register("http", new PlainConnectionSocketFactory()).build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(httpClientProperties.getMaxTotal());
        cm.setDefaultMaxPerRoute(httpClientProperties.getDefaultMaxPerRoute());
        cm.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(httpClientProperties.getConnectionTimeout().intValue()).build());
    }

    public CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(httpClientProperties.getConnectionTimeout().intValue()).setConnectionRequestTimeout(httpClientProperties.getConnectionRequestTimeout().intValue()).build()).setConnectionManager(cm).build();
    }
}
