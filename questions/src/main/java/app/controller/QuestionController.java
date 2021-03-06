package app.controller;

import app.dto.CreateQuestionRequest;
import app.dto.GetQuestionResponse;
import app.dto.GetQuestionsResponse;
import app.dto.UpdateQuestionRequest;
import app.model.Question;
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
@RequestMapping("api/questions")
public class QuestionController {
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<GetQuestionsResponse> getQuestions() {
        List<Question> all = questionService.getAll();
        Function<Collection<Question>,GetQuestionsResponse> mapper = GetQuestionsResponse.entityToDtoMapper();
        GetQuestionsResponse response = mapper.apply(all);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetQuestionResponse> getQuestion(@PathVariable("id") int id) {
        Optional<Question> quest = questionService.find(id);
        if(quest.isPresent()) {
            Question q = quest.get();
            return ResponseEntity.ok(GetQuestionResponse.entityToDtoMapper().apply(q));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //  Add simplified question inside Answers microservice
    @PostMapping
    public ResponseEntity<Void> createQuestion(@RequestBody CreateQuestionRequest request, UriComponentsBuilder builder) {
        Question question = CreateQuestionRequest
                .dtoToEntityMapper()
                .apply(request);

        question = questionService.add(question);

        return ResponseEntity.created(builder.pathSegment("api","questions","{id}")
                .buildAndExpand(question.getId()).toUri())
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateQuestion(@PathVariable("id") int id, @RequestBody UpdateQuestionRequest request) {
        Optional<Question> question = questionService.find(id);
        if(question.isPresent()) {
            questionService.update(UpdateQuestionRequest.dtoToEntityUpdater().apply(question.get(), request));
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") int id) {
        Optional<Question> question = questionService.find(id);
        if(!question.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        questionService.delete(question.get());
        return ResponseEntity.ok().build();
    }
}
