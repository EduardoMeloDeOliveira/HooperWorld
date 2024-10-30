package hoop.api.api.domain.quiz.entity;

import hoop.api.api.domain.question.entity.Question;
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
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quizTitle;

    private String quizDescription;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    List<Question> questions = new ArrayList<>();
}
