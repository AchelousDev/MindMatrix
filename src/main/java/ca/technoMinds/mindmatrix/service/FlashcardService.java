package ca.technoMinds.mindmatrix.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ca.technoMinds.mindmatrix.beans.Flashcard;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class FlashcardService {

    private final RestTemplate restTemplate;

    public FlashcardService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Flashcard> generateFlashcards(String subject) {
        // Call ChatGPT API to generate questions and answers based on the subject
        String apiUrl = "https://api.openai.com/v1/completions";
        String apiKey = "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"; // Replace with your API key
        String prompt = "Generate a question and answer about " + subject + ":";
        
        // Build request payload
        String requestBody = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 100}";
        
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        
        // Make POST request to ChatGPT API
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, new HttpEntity<>(requestBody, headers), String.class);
        String responseBody = responseEntity.getBody();
        
        // Parse response and extract question and answer
        // You need to implement this part based on the response structure of the ChatGPT API
        // For example, if the response contains JSON with generated text, you need to parse it accordingly
        
        List<Flashcard> flashcards = new ArrayList<>();
        // Assuming responseBody contains generated text in JSON format
        String generatedText = parseGeneratedTextFromResponse(responseBody);
        // Extract question and answer from generated text
        // Example logic:
        String[] parts = generatedText.split(":");
        if (parts.length >= 2) {
            String question = parts[0].trim();
            String answer = parts[1].trim();
            flashcards.add(new Flashcard(question, answer));
        }
        
        return flashcards;
    }
    
    private String parseGeneratedTextFromResponse(String responseBody) {
        // Implement logic to parse generated text from the response body
        // This will depend on the structure of the response returned by the ChatGPT API
        return ""; // Placeholder
    }
}
