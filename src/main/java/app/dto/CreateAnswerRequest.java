package app.dto;

import app.model.Answer;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateAnswerRequest {
    private String text;
    private boolean isCorrect;

    public static Function<CreateAnswerRequest, Answer> dtoToEntityMapper() {
        return  request -> Answer.builder()
                .text(request.getText())
                .isCorrect(request.isCorrect())
                .build();
    }
}
