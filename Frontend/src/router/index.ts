import { createRouter, createWebHistory } from 'vue-router'

import CategoryList from '../components/CategoryList.vue';
import CategoryDetails from '../components/CategoryDetails.vue';

const routes = [
  {
    path: '/',
    component: CategoryList
  },
  {
    path: '/question',
    component: CategoryDetails
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;