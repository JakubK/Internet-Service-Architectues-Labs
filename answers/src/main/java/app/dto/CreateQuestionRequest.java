package app.dto;

import app.model.Question;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateQuestionRequest {
    private int questionId;

    public static Function<CreateQuestionRequest, Question> dtoToEntityMapper() {
        return  request -> Question.builder()
                .id(request.getQuestionId())
                .build();
    }
}
