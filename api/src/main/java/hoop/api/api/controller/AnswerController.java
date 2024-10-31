package hoop.api.api.controller;

import hoop.api.api.domain.answer.DTO.AnswerRequestDTO;
import hoop.api.api.domain.answer.DTO.AnswerResponseDTO;
import hoop.api.api.domain.answer.sevice.AnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/{questionId}")
    public ResponseEntity<AnswerResponseDTO> atributeAnswerToQuestion
            (@PathVariable Long questionId,
             @RequestBody @Valid AnswerRequestDTO requestDTO) {
        return ResponseEntity.ok(answerService.atributeAnswerToQuestion(questionId, requestDTO));
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        answerService.deleteAnswerById(answerId);
        return ResponseEntity.noContent().build();
    }
}
