export type Question = {id:number} & QuestionDetailsResponse;

export interface QuestionsResponse {
  questions: Question[];
}

export type CreateQuestionRequest = {
  multiSelect: boolean
  content: string;
};

export type QuestionDetailsResponse = Partial<CreateQuestionRequest>;

