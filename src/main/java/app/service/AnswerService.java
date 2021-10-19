package app.service;

import app.model.Answer;
import app.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.getAll();
    }

    public Optional<Answer> find(Integer id) {
        return repository.find(id);
    }

    public void add(Answer item) {
        repository.add(item);
    }

    public void delete(Answer item) {
        repository.delete(item);
    }
}
