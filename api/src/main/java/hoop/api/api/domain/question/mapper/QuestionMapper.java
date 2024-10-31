package hoop.api.api.domain.question.mapper;

import hoop.api.api.domain.answer.DTO.AnswerResponseDTO;
import hoop.api.api.domain.answer.mapper.AnswerMapper;
import hoop.api.api.domain.question.DTO.QuestionRequestDTO;
import hoop.api.api.domain.question.DTO.QuestionResponseDTO;
import hoop.api.api.domain.question.entity.Question;

import java.util.stream.Collectors;

public class QuestionMapper {

    public static QuestionResponseDTO toDTO(Question question) {

        return QuestionResponseDTO.builder()
                .questionId(question.getId())
                .questionContent(question.getQuestionContent())
                .answers(question.getAnswers().stream().map(respostas -> AnswerMapper.toDTO(respostas)).collect(Collectors.toList()))
                .build();
    }

    public static Question toEntity(QuestionRequestDTO requestDTO) {

        return Question.builder()
                .id(null)
                .questionContent(requestDTO.questionContent())
                .build();
    }
}
