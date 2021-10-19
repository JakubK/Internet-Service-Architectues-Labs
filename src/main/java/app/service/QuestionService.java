package app.service;

import app.model.Answer;
import app.model.Question;
import app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private QuestionRepository repository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository)
    {
        repository = questionRepository;
    }

    public List<Question> getAll() {
        return repository.getAll();
    }

    public Optional<Question> find(Integer id) {
        return repository.find(id);
    }

    public void add(Question item) {
        repository.add(item);
    }

    public void delete(Question item) {
        repository.delete(item);
    }
}
