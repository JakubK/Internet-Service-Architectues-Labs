package app.repository;

import app.model.Answer;
import app.model.Question;
import app.store.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public class QuestionRepository implements app.repository.Repository<Question, Integer> {
//    private DataStore store;
//    @Autowired
//    public QuestionRepository(DataStore dataStore) {
//        store = dataStore;
//    }
//
//    @Override
//    public List<Question> getAll() {
//        return store.findAllQuestions();
//    }
//
//    @Override
//    public Optional<Question> find(Integer id) {
//        return store.findQuestion(id);
//    }
//
//    @Override
//    public void add(Question item) {
//        store.createQuestion(item);
//    }
//
//    @Override
//    public void delete(Question item) {
//        store.deleteQuestion(item.getId());
//    }
//
//}

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}