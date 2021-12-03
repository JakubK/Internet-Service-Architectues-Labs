<script setup lang="ts">
import { QuestionsResponse } from '../models/question';
import { onMounted, ref, Ref } from 'vue';

import router from '../router';
const questions: Ref<QuestionsResponse> = ref({questions: []});

onMounted(async() => {
  //Download questions
  const data = await fetch("http://localhost:8080/api/questions", {
    method: 'GET'
  });
  questions.value = await data.json();
});

const onRowClick = (id: number) => {
  //redirect to question details
  router.push({
    path: 'question',
    query: {
      id
    }
  })
}

const onDelete = async(e: MouseEvent, id: number) => {
  e.stopPropagation();
  //send DELETE
  await fetch(`http://localhost:8080/api/questions/${id}`, {
    method: 'DELETE'
  });
  //update array locally
  questions.value.questions.splice(questions.value.questions.findIndex(x => x.id === id), 1);
}

const onEdit = (e: MouseEvent, id: number) => {
  e.stopPropagation();
  router.push({
    path: '/question/edit',
    query: {
      id
    }
  })
}
</script>

<template>
  <router-link to="/question/new">
    <button>Add new Question</button>
  </router-link>
  <table class="category-list">
    <thead>
      <tr>
        <th>Id</th>
        <th>Content</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <tr @click="onRowClick(quest.id)" v-for="quest in questions.questions" :key="quest.id">
        <th>{{ quest.id }}</th>
        <th>{{ quest.content }}</th>
        <th>
          <button @click="onEdit($event, quest.id)">Edit</button>
          <button @click="onDelete($event, quest.id)">Delete</button>
        </th>
      </tr>
    </tbody>
  </table>
</template>