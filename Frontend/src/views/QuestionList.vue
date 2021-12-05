<script setup lang="ts">
import { QuestionsResponse } from '../models/question';
import { onMounted, ref, Ref } from 'vue';
import { Delete, Get } from '../api/request';

const questions: Ref<QuestionsResponse> = ref({questions: []});

onMounted(async() => {
  //Download questions
  const data = await Get("questions");
  questions.value = await data.json();
});

const onDelete = async(e: MouseEvent, id: number) => {
  //send DELETE
  await Delete(`questions/${id}`);
  //update array locally
  questions.value.questions.splice(questions.value.questions.findIndex(x => x.id === id), 1);
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
      <tr v-for="quest in questions.questions" :key="quest.id">
        <th>{{ quest.id }}</th>
        <th>{{ quest.content }}</th>
        <th>
          <router-link :to="{path: 'question', query: { id: quest.id }}">
            <button>Details</button>
          </router-link>
          <router-link :to="{path: '/question/edit', query: { id: quest.id }}">
            <button>Edit</button>
          </router-link>
          <button @click="onDelete($event, quest.id)">Delete</button>
        </th>
      </tr>
    </tbody>
  </table>
</template>