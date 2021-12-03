export type Question = {id:number} & QuestionDetailsResponse;

export interface QuestionsResponse {
  questions: Question[];
}

export type QuestionDetailsResponse = Partial<{
  multiSelect: boolean
  content: string;
}>;

