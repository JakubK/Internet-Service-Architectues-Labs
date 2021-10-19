package app.repository;

import app.model.Answer;
import app.store.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnswerRepository implements app.repository.Repository<Answer, Integer> {
    private DataStore store;
    @Autowired
    public AnswerRepository(DataStore dataStore) {
        store = dataStore;
    }

    @Override
    public List<Answer> getAll() {
        return store.findAllAnswers();
    }

    @Override
    public Optional<Answer> find(Integer id) {
        return store.findAnswer(id);
    }

    @Override
    public void add(Answer item) {
        store.createAnswer(item);
    }

    @Override
    public void delete(Answer item) {
        store.deleteAnswer(item.getId());
    }
}
