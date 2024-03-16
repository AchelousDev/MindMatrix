package ca.technoMinds.mindmatrix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.technoMinds.mindmatrix.beans.Flashcard;
import ca.technoMinds.mindmatrix.service.FlashcardService;

@RestController
public class FlashcardController {

    private final FlashcardService flashcardService;

    @Autowired
    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping("/createFlashcard")
    public ResponseEntity<List<Flashcard>> createFlashcard(@RequestParam String subject) {
        List<Flashcard> flashcards = flashcardService.generateFlashcards(subject);
        return ResponseEntity.ok(flashcards);
    }
}