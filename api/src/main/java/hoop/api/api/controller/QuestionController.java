package hoop.api.api.controller;

import hoop.api.api.domain.question.DTO.QuestionRequestDTO;
import hoop.api.api.domain.question.DTO.QuestionResponseDTO;
import hoop.api.api.domain.question.service.QuestionService;
import hoop.api.api.domain.quiz.DTO.QuizReponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @Operation(summary = "Assign a question to a quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
    })
    @PostMapping("/{quizId}")
    public ResponseEntity<QuestionResponseDTO> atributeQuestionToQuiz
            (@PathVariable Long quizId,
             @RequestBody
             @Valid
             QuestionRequestDTO requestDTO) {
        QuestionResponseDTO responseDto = questionService.atributeQuestionToQuiz(quizId, requestDTO);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Update question by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question updated successfully"),
            @ApiResponse(responseCode = "404", description = "Question not found")
    })
    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionResponseDTO> updateQuestion
            (@PathVariable Long questionId,
             @RequestBody @Valid QuestionRequestDTO requestDTO) {
        QuestionResponseDTO responseDto = questionService.updateQuestion(questionId, requestDTO);
        return ResponseEntity.ok(responseDto);
    }
}
