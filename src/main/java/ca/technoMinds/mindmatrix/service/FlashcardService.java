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
        String apiUrl = "https://api.openai.com/v1/completions";
        String apiKey = ""; 
        String prompt = "Generate a question and answer about " + subject + ":";
        
        String requestBody = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 100}";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, new HttpEntity<>(requestBody, headers), String.class);
        String responseBody = responseEntity.getBody();
        
     
        List<Flashcard> flashcards = new ArrayList<>();
        // Assuming responseBody contains generated text in JSON format
        String generatedText = parseGeneratedTextFromResponse(responseBody);

        String[] parts = generatedText.split(":");
        if (parts.length >= 2) {
            String question = parts[0].trim();
            String answer = parts[1].trim();
            flashcards.add(new Flashcard(question, answer));
        }
        
        return flashcards;
    }
    
    private String parseGeneratedTextFromResponse(String responseBody) {
       return "";
    }
}
