package com.idas.springbootinit.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.idas.springbootinit.constant.OpenAiConstant.*;

@Service
public class OpenAiManager {

    public String chatWithAi(String message) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Create JSON payload
        String json = "{\n" +
                "  \"model\": \"gpt-4o-mini\",\n" +  // Use a valid model name
                "  \"messages\": [\n" +
                "    {\"role\": \"system\", \"content\": \"" + ASSISTANT_MESSAGE.replace("\n", "\\n").replace("\"", "\\\"") + "\"},\n" +
                "    {\"role\": \"user\", \"content\": \"" + message.replace("\n", "\\n").replace("\"", "\\\"") + "\"}\n" +
                "  ],\n" +
                "  \"max_tokens\": 6000\n" +
                "}";

        // Build request
        Request request = new Request.Builder()
                .url(API_URL)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        String text = "";
        // Make the request
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                // Parse JSON response
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                text = jsonNode.get("choices").get(0).get("message").get("content").asText();
                System.out.println("Response: " + text.trim());

            } else {
                System.err.println("Request failed: " + response.body().string());
            }
        }
        return text.trim();
    }
}
