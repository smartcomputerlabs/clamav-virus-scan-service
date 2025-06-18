package org.javacommunity.scan.service;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VirusTotalService {

    /*private final String apiKey = "YOUR_VIRUSTOTAL_API_KEY";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public boolean isClean(File file) throws IOException, InterruptedException {
        String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.virustotal.com/api/v3/files"))
                .header("x-apikey", apiKey)
                .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                .*//*POST(HttpRequest.BodyPublishers.ofMultipart(
                        java.util.Map.of(
                                "file",
                                HttpRequest.BodyPublishers.ofFile(file.toPath())
                        )*//*
                    POST(HttpRequest.BodyPublishers.ofFile(file.toPath()))
                ))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonResponse = objectMapper.readTree(response.body());
        String analysisId = jsonResponse.get("data").get("id").asText();

        // Poll for analysis results
        HttpRequest analysisRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://www.virustotal.com/api/v3/analyses/" + analysisId))
                .header("x-apikey", apiKey)
                .GET()
                .build();

        HttpResponse<String> analysisResponse = null;
        JsonNode analysisJsonResponse = null;
        String status = "";
        do {
            Thread.sleep(5000);
            analysisResponse = client.send(analysisRequest, HttpResponse.BodyHandlers.ofString());
            analysisJsonResponse = objectMapper.readTree(analysisResponse.body());
            status = analysisJsonResponse.get("data").get("attributes").get("status").asText();
        } while(!status.equals("completed"));

        JsonNode stats = analysisJsonResponse.get("data").get("attributes").get("stats");
        int malicious = stats.get("malicious").asInt();

        return malicious == 0;
    }*/
}