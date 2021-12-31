package app.repository;

import app.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class QuestionEventRepository {
    private RestTemplate answersRestTemplate;
    private RestTemplate filesRestTemplate;

    @Autowired
    public QuestionEventRepository(@Value("${answers.url}") String answersUrl, @Value("${files.url}") String filesUrl) {
        answersRestTemplate = new RestTemplateBuilder().rootUri(answersUrl).build();
        filesRestTemplate = new RestTemplateBuilder().rootUri(filesUrl).build();
    }

    // Remove answers for such question, with simplified question record itself
    public void delete(Question question) {
        answersRestTemplate.delete("/questions/" + question.getId());
        filesRestTemplate.delete("/files/" + question.getId());
    }

    // Notify answers microservice to create simplified record
    public void create(Question question) {
        answersRestTemplate.postForLocation("/questions", question.getId());
    }
}
