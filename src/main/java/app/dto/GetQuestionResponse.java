package app.dto;

import app.model.Answer;
import app.model.Question;
import app.service.AnswerService;
import lombok.*;

import java.util.List;
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
    private List<Answer> answers;

    public static Function<Question, GetQuestionResponse> entityToDtoMapper() {
        return quest -> GetQuestionResponse.builder()
                .id(quest.getId())
                .content(quest.getContent())
                .isMultiSelect(quest.isMultiSelect())
                .answers(quest.getAnswers())
                .build();
    }
}
