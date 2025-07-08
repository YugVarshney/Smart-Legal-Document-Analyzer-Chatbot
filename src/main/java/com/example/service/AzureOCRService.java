package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AzureOCRService {

    @Value("${azure.vision.endpoint}")
    private String endpoint;

    @Value("${azure.vision.key}")
    private String key;

    public String extractTextFromImage(MultipartFile file) throws IOException {
        String uri = endpoint + "/vision/v3.2/read/analyze";

        HttpPost request = new HttpPost(uri);
        request.setHeader("Ocp-Apim-Subscription-Key", key);
        request.setHeader("Content-Type", "application/octet-stream");
        request.setEntity(new ByteArrayEntity(file.getBytes()));

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {
            String operationLocation = response.getFirstHeader("Operation-Location").getValue();

            Thread.sleep(2000);

            HttpGet resultRequest = new HttpGet(operationLocation);
            resultRequest.setHeader("Ocp-Apim-Subscription-Key", key);

            try (CloseableHttpResponse resultResponse = client.execute(resultRequest)) {
                String jsonResult = EntityUtils.toString(resultResponse.getEntity());
                System.out.println("DEBUG: OCR JSON response:\n" + jsonResult);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(jsonResult);

                StringBuilder extractedText = new StringBuilder();

                JsonNode analyzeResult = root.path("analyzeResult");
                if (!analyzeResult.isMissingNode()) {
                    JsonNode readResults = analyzeResult.path("readResults");
                    if (readResults.isArray()) {
                        for (JsonNode page : readResults) {
                            JsonNode lines = page.path("lines");
                            if (lines.isArray()) {
                                for (JsonNode line : lines) {
                                    String text = line.path("text").asText();
                                    extractedText.append(text).append("\n");
                                }
                            }
                        }
                    }
                }

                return extractedText.toString().trim();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Interrupted while waiting for OCR result", e);
        }
    }
}
