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
    private String content;
    private boolean isMultiSelect;

    public static Function<CreateQuestionRequest, Question> dtoToEntityMapper() {
        return  request -> Question.builder()
                .content(request.getContent())
                .isMultiSelect(request.isMultiSelect())
                .build();
    }
}
