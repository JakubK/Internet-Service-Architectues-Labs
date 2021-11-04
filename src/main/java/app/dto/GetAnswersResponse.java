package app.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAnswersResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Answer {
        private String text;
        private boolean isCorrect;
    }

    @Singular
    private List<Answer> answers;

    public static Function<Collection<Answer>,GetAnswersResponse> entityToDtoMapper() {
        return answers -> {
            GetAnswersResponseBuilder response = GetAnswersResponse.builder();
            answers.stream()
                    .map(ans -> Answer.builder()
                            .text(ans.getText())
                            .isCorrect(ans.isCorrect())
                            .build())
                    .forEach(response::answer);
            return response.build();
        };
    }
}
