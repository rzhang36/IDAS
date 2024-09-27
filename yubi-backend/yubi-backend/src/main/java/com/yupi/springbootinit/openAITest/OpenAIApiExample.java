package com.yupi.springbootinit.openAITest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

import static com.yupi.springbootinit.constant.OpenAiConstant.API_KEY;
import static com.yupi.springbootinit.constant.OpenAiConstant.API_URL;

public class OpenAIApiExample {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Create JSON payload
        String json = "{\n" +
                "  \"model\": \"gpt-4o-mini\",\n" +  // Use a valid model name
                "  \"messages\": [\n" +
                "    {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},\n" +
                "    {\"role\": \"user\", \"content\": \"Translate the following English text to Mandarin: 'What's up?'\"}\n" +
                "  ],\n" +
                "  \"max_tokens\": 60\n" +
                "}";

        // Build request
        Request request = new Request.Builder()
                .url(API_URL)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        // Make the request
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                // Parse JSON response
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                String text = jsonNode.get("choices").get(0).get("message").get("content").asText();
                System.out.println("Response: " + text.trim());
            } else {
                System.err.println("Request failed: " + response.body().string());
            }
        }
    }
}
