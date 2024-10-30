package hoop.api.api.domain.question.entity;

import hoop.api.api.domain.quiz.entity.Quiz;
import jakarta.persistence.*;
import lombok.*;

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
    Long id;

    private String questionContent;

    @ManyToOne
    private Quiz quiz;
}
