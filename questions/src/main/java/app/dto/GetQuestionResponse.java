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
public class GetQuestionResponse {
    private String content;
    private boolean isMultiSelect;
    private int id;

    public static Function<Question, GetQuestionResponse> entityToDtoMapper() {
        return quest -> GetQuestionResponse.builder()
                .id(quest.getId())
                .content(quest.getContent())
                .isMultiSelect(quest.isMultiSelect())
                .build();
    }
}
