package app.dto;

import app.model.Answer;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateAnswerRequest {
    private String text;
    private boolean isCorrect;

    public static BiFunction<Answer, UpdateAnswerRequest, Answer> dtoToEntityUpdater() {
        return (ans, request) -> {
            ans.setText(request.getText());
            ans.setCorrect(request.isCorrect());
            return ans;
        };
    }
}
