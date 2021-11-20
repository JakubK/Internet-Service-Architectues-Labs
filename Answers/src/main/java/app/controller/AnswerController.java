package app.controller;

import app.dto.CreateAnswerRequest;
import app.dto.GetAnswerResponse;
import app.dto.GetAnswersResponse;
import app.dto.UpdateAnswerRequest;
import app.model.Answer;
import app.model.Question;
import app.service.AnswerService;
import app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/answers")
public class AnswerController {
    private AnswerService answerService;
    private QuestionService questionService;

    @Autowired
    public AnswerController(QuestionService questionService, AnswerService answerService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }


    //  Get answers associated to question by given Id
    @GetMapping("question/{questionId}")
    public ResponseEntity getAnswersForQuestion(@PathVariable("questionId") int questionId) {
        Optional<Question> quest = questionService.find(questionId);
        if(quest.isPresent()) {
            Question q = quest.get();
            return ResponseEntity.ok(q.getAnswers());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<GetAnswersResponse> getAnswers() {
        List<Answer> all = answerService.getAll();
        Function<Collection<Answer>, GetAnswersResponse> mapper = GetAnswersResponse.entityToDtoMapper();
        GetAnswersResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<GetAnswerResponse> getAnswer(@PathVariable("id") int id) {
        return  answerService.find(id)
                .map(value -> ResponseEntity.ok(GetAnswerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createAnswer(@RequestBody CreateAnswerRequest request, UriComponentsBuilder builder) {
        Answer answer = CreateAnswerRequest
                .dtoToEntityMapper()
                .apply(request);
        answer.setQuestion(questionService.find(request.getQuestionId()).get());
        answer = answerService.add(answer);

        return ResponseEntity.created(builder.pathSegment("api", "answers", "{id}")
                .buildAndExpand(answer.getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAnswer(@RequestBody UpdateAnswerRequest request, @PathVariable("id") int id) {
        Optional<Answer> answer = answerService.find(id);
        if (answer.isPresent()) {
            answerService.update(UpdateAnswerRequest.dtoToEntityUpdater().apply(answer.get(), request));
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable("id") int id) {
        Optional<Answer> answer = answerService.find(id);
        if(!answer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        answerService.delete(answer.get());
        return ResponseEntity.ok().build();
    }
}
