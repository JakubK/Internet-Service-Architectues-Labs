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
public class GetAnswerResponse {
    private String text;
    private boolean isCorrect;

    public static Function<Answer, GetAnswerResponse> entityToDtoMapper() {
        return ans -> GetAnswerResponse.builder()
                .text(ans.getText())
                .isCorrect(ans.isCorrect())
                .build();
    }
}
