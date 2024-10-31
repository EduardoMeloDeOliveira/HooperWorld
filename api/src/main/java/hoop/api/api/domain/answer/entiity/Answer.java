package hoop.api.api.domain.answer.entiity;


import hoop.api.api.domain.question.entity.Question;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String answerContent;

    private Boolean isCorrectAnswer;

    @ManyToOne
    private Question question;

}
