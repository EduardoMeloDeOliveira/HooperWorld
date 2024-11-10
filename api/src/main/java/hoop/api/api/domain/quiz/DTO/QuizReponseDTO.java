package hoop.api.api.domain.quiz.DTO;

import hoop.api.api.domain.question.DTO.QuestionResponseDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record QuizReponseDTO(
        Long quizId,
        String quizTitle,
        String quizDescription,
        List<QuestionResponseDTO> questions

) {
}
