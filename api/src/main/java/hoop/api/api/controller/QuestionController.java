package hoop.api.api.controller;

import hoop.api.api.domain.question.DTO.QuestionRequestDTO;
import hoop.api.api.domain.question.DTO.QuestionResponseDTO;
import hoop.api.api.domain.question.service.QuestionService;
import hoop.api.api.domain.quiz.DTO.QuizReponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @PostMapping("/{quizId}")
    public ResponseEntity<QuestionResponseDTO> atributeQuestionToQuiz
            (@PathVariable Long quizId,
             @RequestBody
             @Valid
             QuestionRequestDTO requestDTO) {
        QuestionResponseDTO responseDto = questionService.atributeQuestionToQuiz(quizId, requestDTO);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionResponseDTO> updateQuestion
            (@PathVariable Long questionId,
             @RequestBody @Valid QuestionRequestDTO requestDTO) {
        QuestionResponseDTO responseDto = questionService.updateQuestion(questionId, requestDTO);
        return ResponseEntity.ok(responseDto);
    }
}
