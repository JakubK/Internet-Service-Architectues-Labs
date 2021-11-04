package app.service;

import app.model.Answer;
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

    public Optional<Answer> find(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public void add(Answer item) {
        repository.save(item);
    }

    public void delete(Answer item) {
        repository.delete(item);
    }
}
