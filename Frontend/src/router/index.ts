import { createRouter, createWebHistory } from 'vue-router'

import QuestionList from '../components/QuestionList.vue';
import QuestionDetails from '../components/QuestionDetails.vue';
import AddQuestion from '../components/AddQuestion.vue';
import AddAnswer from '../components/AddAnswer.vue';
import AnswerDetails from '../components/AnswerDetails.vue';

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