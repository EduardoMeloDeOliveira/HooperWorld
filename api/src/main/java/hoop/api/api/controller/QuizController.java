package hoop.api.api.controller;

import hoop.api.api.domain.quiz.DTO.QuizReponseDTO;
import hoop.api.api.domain.quiz.DTO.QuizRequestDTO;
import hoop.api.api.domain.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Quiz created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
    @PostMapping
    public ResponseEntity<QuizReponseDTO> createQuiz(@RequestBody @Valid QuizRequestDTO requestDTO) {
        return ResponseEntity.created(null).body(quizService.createQuiz(requestDTO));
    }

    @Operation(summary = "Get all quizzes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of quizzes"),
            @ApiResponse(responseCode = "204", description = "No quizzes found")
    })
    @GetMapping
    public ResponseEntity<List<QuizReponseDTO>> getAllQuizzes() {
        List<QuizReponseDTO> quizDto = quizService.getAllQuiz();

        if (quizDto.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(quizDto);
    }


    @Operation(summary = "Get quiz by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz found"),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
    })
    @GetMapping("/{quizId}")
    public ResponseEntity<QuizReponseDTO> getQuizById(@PathVariable Long quizId) {

        return ResponseEntity.ok(quizService.getQuizById(quizId));
    }


    @Operation(summary = "Delete quiz by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Quiz deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
    })
    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.noContent().build();
    }
}
