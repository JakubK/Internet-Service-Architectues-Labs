package app;

import app.model.Answer;
import app.service.AnswerService;
import app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private AnswerService answerService;
    private QuestionService questionService;
    @Autowired
    public CommandLine(AnswerService answerService, QuestionService questionService)
    {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("MENU:");
            System.out.println("1:List all questions");
            System.out.println("2:List all answers");
            System.out.println("3:Add answer");
            System.out.println("4:Delete answer");
            System.out.println("5:Exit");
            int choice = scanner.nextInt();
            if(choice == 1)
            {
                questionService.getAll().forEach(question -> {
                    System.out.println(question);
                });
            }
            if(choice == 2)
            {
                answerService.getAll().forEach(answer -> {
                    System.out.println(answer);
                });
            }
            if(choice == 3)
            {
                System.out.println("Pass id of question you wish to add answer to");
                int questionId = scanner.nextInt();
                System.out.println("Now pass new id, text and if answer is correct");


                int ansId = scanner.nextInt();
                String text = scanner.next();
                boolean isCorrect = scanner.nextBoolean();


                Answer newAns = Answer.builder()
                        .id(ansId)
                        .text(text)
                        .isCorrect(isCorrect)
                        .question(questionService.find(questionId).get())
                        .build();
                answerService.add(newAns);
            }
            if(choice == 4)
            {
                System.out.println("Pass id of answer to be deleted");
                int id = scanner.nextInt();
                answerService.delete(answerService.find(id).get());
            }
            if(choice == 5)
                break;
        }
        scanner.close();
    }
}
