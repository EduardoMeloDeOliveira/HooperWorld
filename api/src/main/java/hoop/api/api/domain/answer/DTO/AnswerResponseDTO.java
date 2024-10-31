package hoop.api.api.domain.answer.DTO;

import lombok.Builder;

@Builder
public record AnswerResponseDTO (
        Long answerId,
        String answerContent,
        Boolean isCorrect
){
}
