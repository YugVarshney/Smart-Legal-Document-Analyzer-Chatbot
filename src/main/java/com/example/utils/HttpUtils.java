package com.example.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClients;

public class HttpUtils {
    public static String executePost(String url, HttpEntity entity) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(entity);

            return client.execute(post, response -> EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to send request: " + e.getMessage(), e);
        }
    }
}
