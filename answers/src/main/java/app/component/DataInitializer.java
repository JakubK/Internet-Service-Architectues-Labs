package app.component;

import app.model.Answer;
import app.model.Question;
import app.service.AnswerService;
import app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private QuestionService quest;
    private AnswerService answer;

    @Autowired
    public DataInitializer(QuestionService questions, AnswerService answers)
    {
        quest = questions;
        answer = answers;
    }

    @PostConstruct
    public void Feed()
    {
        System.out.println("Feeding data");
        Question q = Question.builder()
                .build();

        quest.add(q);
        Answer firstAns = Answer.builder()
                .text("Answer A")
                .question(q)
                .isCorrect(true).build();

        Answer secondAns = Answer.builder()
                .text("Answer B")
                .question(q)
                .isCorrect(false).build();

        Answer thirdAns = Answer.builder()
                .text("Answer C")
                .question(q)
                .isCorrect(true).build();
        answer.add(firstAns);
        answer.add(secondAns);
        answer.add(thirdAns);
    }

}
