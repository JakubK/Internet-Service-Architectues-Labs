package app.dto;

import app.model.Question;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateQuestionRequest {

    private String content;
    private boolean isMultiSelect;

    public static BiFunction<Question, UpdateQuestionRequest, Question> dtoToEntityUpdater() {
        return (quest, request) -> {
            quest.setContent(request.getContent());
            quest.setMultiSelect(request.isMultiSelect());
            return quest;
        };
    }
}
