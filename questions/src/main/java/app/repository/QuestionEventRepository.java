package app.repository;

import app.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class QuestionEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public QuestionEventRepository(@Value("${answers.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    // Remove answers for such question, with simplified question record itself
    public void delete(Question question) {
        restTemplate.delete("/questions/" + question.getId());
    }

    // Notify answers microservice to create simplified record
    public void create(Question question) {
        restTemplate.postForLocation("/questions", question.getId());
    }
}
