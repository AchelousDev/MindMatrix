package ca.technoMinds.mindmatrix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ca.technoMinds.mindmatrix.beans.Flashcard;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

//Imports omitted for brevity

@Service
public class FlashcardService {

	private final RestTemplate restTemplate;

	@Autowired
	public FlashcardService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Flashcard> generateFlashcards(String subject) {
		String apiUrl = "https://api.openai.com/v1/completions";
		String apiKey = getApiKey(); // Retrieve API key from a secure source
		String prompt = "Generate a question and answer about " + subject + ":";

		String requestBody = createRequestBody(prompt);

		HttpHeaders headers = createHeaders(apiKey);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl,
				new HttpEntity<>(requestBody, headers), String.class);
		if (!responseEntity.getStatusCode().is2xxSuccessful()) {
			// Handle non-successful response
			// Maybe throw an exception or return empty list
			return Collections.emptyList();
		}

		String responseBody = responseEntity.getBody();
		String generatedText = parseGeneratedTextFromResponse(responseBody);

		return extractFlashcards(generatedText);
	}

	private String getApiKey() {
		// Retrieve API key from a secure source (e.g., environment variable,
		// configuration file)
		return "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";
	}

	private String createRequestBody(String prompt) {
		return "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 100}";
	}

	private HttpHeaders createHeaders(String apiKey) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(apiKey);
		return headers;
	}

	private String parseGeneratedTextFromResponse(String responseBody) {
		// Implement parsing logic
		return "";
	}

	private List<Flashcard> extractFlashcards(String generatedText) {
		List<Flashcard> flashcards = new ArrayList<>();
		String[] parts = generatedText.split(":");
		if (parts.length >= 2) {
			String question = parts[0].trim();
			String answer = parts[1].trim();
			flashcards.add(new Flashcard(question, answer));
		}
		return flashcards;
	}
}
