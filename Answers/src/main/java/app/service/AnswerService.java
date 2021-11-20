package app.service;

import app.model.Answer;
import app.model.Question;
import app.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private AnswerRepository repository;
    @Autowired
    public  AnswerService(AnswerRepository repo)
    {
        repository = repo;
    }

    public List<Answer> getAll() {
        return repository.findAll();
    }

    public List<Answer> getAllByQuestion(Question question) {
        return repository.findAllByQuestion(question);
    }

    public Optional<Answer> find(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public Answer add(Answer item) {
        return repository.save(item);
    }

    @Transactional
    public void update(Answer answer) {
        repository.save(answer);
    }

    public void delete(Answer item) {
        repository.delete(item);
    }
}
