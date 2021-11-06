package app.service;

import app.model.Answer;
import app.model.Question;
import app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return repository.findAll();
    }

    public Optional<Question> find(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public Question add(Question item) {
        return repository.save(item);
    }

    @Transactional
    public void update(Question quest) {
        repository.save(quest);
    }

    public void delete(Question item) {
        repository.delete(item);
    }
}
