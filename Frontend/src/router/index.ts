import { createRouter, createWebHistory } from 'vue-router'

import QuestionList from '../views/QuestionList.vue';
import QuestionDetails from '../views/QuestionDetails.vue';
import AddQuestion from '../views/AddQuestion.vue';
import AddAnswer from '../views/AddAnswer.vue';
import AnswerDetails from '../views/AnswerDetails.vue';

const routes = [
  {
    path: '/',
    component: QuestionList
  },
  {
    path: '/question',
    component: QuestionDetails
  },
  {
    path: '/question/edit',
    component: QuestionDetails
  },
  {
    path: '/question/new',
    component: AddQuestion
  },
  {
    path: '/answer',
    component: AnswerDetails
  },
  {
    path: '/answer/new',
    component: AddAnswer
  },
  {
    path: '/answer/edit',
    component: AnswerDetails
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;