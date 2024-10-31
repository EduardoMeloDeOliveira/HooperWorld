package hoop.api.api.domain.answer.sevice;

import hoop.api.api.domain.answer.DTO.AnswerRequestDTO;
import hoop.api.api.domain.answer.DTO.AnswerResponseDTO;
import hoop.api.api.domain.answer.entiity.Answer;
import hoop.api.api.domain.answer.mapper.AnswerMapper;
import hoop.api.api.domain.answer.repository.AnswerRepository;
import hoop.api.api.domain.question.entity.Question;
import hoop.api.api.domain.question.service.QuestionService;
import hoop.api.api.handler.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionService questionService;

    public AnswerResponseDTO atributeAnswerToQuestion(Long questionId, AnswerRequestDTO requestDTO) {

        Question question = questionService.existsById(questionId);
        Answer answer = AnswerMapper.toEntity(requestDTO);

        answer.setQuestion(question);

        answerRepository.save(answer);

        return AnswerMapper.toDTO(answer);

    }

    public void deleteAnswerById(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(()-> new ObjectNotFoundException("Answer not found"));

        answerRepository.delete(answer);

    }
}
