package hoop.api.api.controller;

import hoop.api.api.domain.answer.DTO.AnswerRequestDTO;
import hoop.api.api.domain.answer.DTO.AnswerResponseDTO;
import hoop.api.api.domain.answer.sevice.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Atribuir resposta a pergunta", description = "Atribui uma resposta à pergunta especificada.")
    public ResponseEntity<AnswerResponseDTO> atributeAnswerToQuestion
            (@PathVariable Long questionId,
             @RequestBody @Valid AnswerRequestDTO requestDTO) {
        return ResponseEntity.ok(answerService.atributeAnswerToQuestion(questionId, requestDTO));
    }
    @DeleteMapping("/{answerId}")
    @Operation(summary = "Atribuir resposta a pergunta", description = "Atribui uma resposta à pergunta especificada.")

    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        answerService.deleteAnswerById(answerId);
        return ResponseEntity.noContent().build();
    }
}
