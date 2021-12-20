package app.controller;

import app.dto.CreateQuestionRequest;

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
public class QuestionAnswersController {
    private QuestionService questionService;

    @Autowired
    public QuestionAnswersController(QuestionService questionService) {
        this.questionService = questionService;
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
