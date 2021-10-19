package app.store;

import app.model.Answer;
import app.model.Question;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataStore {
    private Set<Answer> answers = new HashSet<>();
    private Set<Question> questions = new HashSet<>();

    public List<Answer> findAllAnswers() {
        return new ArrayList<>(answers);
    }

    public List<Question> findAllQuestions() {
        return new ArrayList<>(questions);
    }

    public Optional<Answer> findAnswer(int id) {
        return answers.stream()
                .filter(ans -> ans.getId() == id)
                .findFirst();
    }

    public Optional<Question> findQuestion(int id) {
        return questions.stream()
                .filter(quest -> quest.getId() == id)
                .findFirst();
    }

    public void deleteQuestion(int id) {
        findQuestion(id).ifPresentOrElse(
                origin -> questions.remove(origin),
                () -> {
                }
        );
    }

    public void deleteAnswer(int id) {
        findAnswer(id).ifPresentOrElse(
                origin -> answers.remove(origin),
                () -> {
                }
        );
    }

    public void createQuestion(Question question) {
        findQuestion(question.getId()).ifPresentOrElse(
                origin -> {

                },
                () -> questions.add(question)
        );
    }

    public void createAnswer(Answer ans) {
        findAnswer(ans.getId()).ifPresentOrElse(
                origin -> {

                },
                () -> answers.add(ans)
        );
    }
}
