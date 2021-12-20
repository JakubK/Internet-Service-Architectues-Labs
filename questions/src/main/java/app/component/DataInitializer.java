package app.component;

import app.model.Question;
import app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private QuestionService quest;

    @Autowired
    public DataInitializer(QuestionService questions)
    {
        quest = questions;
    }

    @PostConstruct
    public void Feed()
    {
        System.out.println("Feeding data");
        Question q = Question.builder()
                .content("Some tricky question")
                .build();
        quest.add(q);
    }

}
