package hoop.api.api.domain.question.DTO;

import hoop.api.api.domain.answer.DTO.AnswerResponseDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionResponseDTO (
        Long questionId,
        String questionContent,
        List<AnswerResponseDTO> answers

        //TODO implementar a lista de answer responseDto's
){
}
