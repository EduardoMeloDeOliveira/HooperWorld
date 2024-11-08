package hoop.api.api.domain.question.entity;

import hoop.api.api.domain.answer.entiity.Answer;
import hoop.api.api.domain.quiz.entity.Quiz;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionContent;

    @ManyToOne
    private Quiz quiz;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Answer> answers;


    public List<Answer> getAnswers() {
        return answers == null ? new ArrayList<>() : answers;
    }
}
