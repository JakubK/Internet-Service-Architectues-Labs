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
public class GetQuestionsResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Question {

        private String content;
        private boolean isMultiselect;

    }

    @Singular
    private List<Question> questions;

    public static Function<Collection<Question>,GetQuestionsResponse> entityToDtoMapper() {
        return questions -> {
            GetQuestionsResponseBuilder response = GetQuestionsResponse.builder();
            questions.stream()
                    .map(quest -> Question.builder()
                        .content(quest.getContent())
                        .isMultiselect(quest.isMultiselect())
                            .build())
                    .forEach(response::question);
            return response.build();
        };
    }
}
