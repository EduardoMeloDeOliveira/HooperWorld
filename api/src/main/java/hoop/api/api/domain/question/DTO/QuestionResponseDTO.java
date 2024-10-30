package hoop.api.api.domain.question.DTO;

import lombok.Builder;

@Builder
public record QuestionResponseDTO (
        Long questionId,
        String questionContent

        //TODO implementar a lista de answer responseDto's
){
}
