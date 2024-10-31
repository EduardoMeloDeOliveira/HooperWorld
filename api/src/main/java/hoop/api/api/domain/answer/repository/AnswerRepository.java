package hoop.api.api.domain.answer.repository;

import hoop.api.api.domain.answer.entiity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
