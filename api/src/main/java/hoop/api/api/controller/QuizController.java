package hoop.api.api.controller;

import hoop.api.api.domain.quiz.DTO.QuizReponseDTO;
import hoop.api.api.domain.quiz.DTO.QuizRequestDTO;
import hoop.api.api.domain.quiz.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizReponseDTO> createQuiz(@RequestBody @Valid QuizRequestDTO requestDTO) {
        return ResponseEntity.created(null).body(quizService.createQuiz(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<QuizReponseDTO>> getAllQuizzes() {
        List<QuizReponseDTO> quizDto = quizService.getAllQuiz();

        if (quizDto.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(quizDto);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizReponseDTO> getQuizById(@PathVariable Long quizId) {

        return ResponseEntity.ok(quizService.getQuizById(quizId));
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.noContent().build();
    }
}
