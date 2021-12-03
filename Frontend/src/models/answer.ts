export interface Answer {
  id: number;
  text: string;
}

export interface CreateAnswerRequest {
  text: string;
  correct: boolean;
  questionId: number;
}