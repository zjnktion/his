package cn.zjnktion.his.server.util;

import cn.zjnktion.his.server.configuration.HttpClientPool;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author zjnktion
 */
@Slf4j
public class HttpConnectionUtil {

    @Autowired
    private HttpClientPool httpClientPool;

    public String postWithJson(String url, String json) {
        String result = StringUtils.EMPTY;
        CloseableHttpClient httpClient = httpClientPool.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        StringEntity requestEntity = new StringEntity(json, "utf-8");
        requestEntity.setContentEncoding("UTF-8");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(requestEntity);
        try {
            @Cleanup CloseableHttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        catch (IOException e) {
            log.error("Error in postWithJson.", e);
        }
        return result;
    }
}
