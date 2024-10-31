package hoop.api.api.domain.answer.mapper;

import hoop.api.api.domain.answer.DTO.AnswerRequestDTO;
import hoop.api.api.domain.answer.DTO.AnswerResponseDTO;
import hoop.api.api.domain.answer.entiity.Answer;

public class AnswerMapper {

    public static Answer toEntity (AnswerRequestDTO requestDTO){

        return  Answer.builder()
                .id(null)
                .answerContent(requestDTO.answerContent())
                .isCorrectAnswer(requestDTO.isCorret())
                .build();
    }



    public static AnswerResponseDTO toDTO (Answer answer){

        return AnswerResponseDTO.builder()
                .answerId(answer.getId())
                .answerContent(answer.getAnswerContent())
                .isCorrect(answer.getIsCorrectAnswer())
                .build();
    }
}
