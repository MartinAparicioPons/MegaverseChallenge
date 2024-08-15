package com.example.megaverse.astral.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.megaverse.astral.Astral;
import com.example.megaverse.astral.Cometh;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ComethService implements AstralService {
    @Value("${base_url}")
    private String baseUrl;

    @Value("${candidate_id}")
    private String candidateId;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final RateLimiter rateLimiter;

    public ComethService(RestTemplate restTemplate, RateLimiter rateLimiter) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void create(Astral astral) {
        if (!(astral instanceof Cometh)) {
            throw new IllegalArgumentException("Invalid Astral type.");
        }
        Cometh cometh = (Cometh) astral;
        String url = baseUrl + "/api/comeths";

        // Create the JSON payload
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("row", String.valueOf(cometh.getCoordinate().x));
        requestBody.put("column", String.valueOf(cometh.getCoordinate().y));
        requestBody.put("direction", String.valueOf(cometh.getDirection()).toLowerCase());
        requestBody.put("candidateId", candidateId);

        try {
            while (!rateLimiter.tryAcquire()) {
                Thread.sleep(50);
            }
            // Send the POST request
            restTemplate.postForObject(url, requestBody, String.class);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void delete(Astral astral) {
        if (!(astral instanceof Cometh)) {
            throw new IllegalArgumentException("Invalid Astral type.");
        }
        Cometh cometh = (Cometh) astral;
        String url = baseUrl + "/api/comeths";

        // Create the JSON payload
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("row", String.valueOf(cometh.getCoordinate().x));
        requestBody.put("column", String.valueOf(cometh.getCoordinate().y));
        requestBody.put("candidateId", candidateId);

        try {

            while (!rateLimiter.tryAcquire()) {
                Thread.sleep(50);
            }

            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Convert the payload to a JSON string
                String jsonInputString = objectMapper.writeValueAsString(requestBody);

                // Create the HttpDelete request
                HttpDeleteWithBody deleteRequest = new HttpDeleteWithBody(url);
                deleteRequest.setEntity(new StringEntity(jsonInputString, ContentType.APPLICATION_JSON));

                // Execute the request
                try (CloseableHttpResponse response = httpClient.execute(deleteRequest)) {
                    int statusCode = response.getCode();
                    if (statusCode == 200) {
                        System.out.println("Delete successful");
                    } else {
                        System.out.println("Delete failed with HTTP code: " + statusCode + " for cometh " + cometh);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete Cometh", e);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // HttpDelete does not support body by default, so we need to extend it
    public static class HttpDeleteWithBody extends HttpPost {
        public HttpDeleteWithBody(final String uri) {
            super(uri);
        }

        @Override
        public String getMethod() {
            return HttpDelete.METHOD_NAME;
        }
    }
}
