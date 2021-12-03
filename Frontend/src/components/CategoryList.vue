<script setup lang="ts">
import { Question } from '../models/question';
import { onMounted, ref, Ref } from 'vue';

import router from '../router';

onMounted(() => {
  //Download questions
  console.log('xd');
});

const questions: Ref<Array<Question>> = ref([]);
questions.value = [{
  id: 1,
  content: 'test',
  isMultiSelect: true
}]

const onRowClick = (id: number) => {
  //redirect to question details
  router.push({
    path: 'question',
    query: {
      id
    }
  })
}

const onDelete = (e: MouseEvent, id: number) => {
  e.stopPropagation();
  //send DELETE

  //update array locally
}

const onEdit = (e: MouseEvent, id: number) => {
  e.stopPropagation();
}
</script>

<template>
  <button>Add new Question</button>
  <table class="category-list">
    <thead>
      <tr>
        <th>Id</th>
        <th>Content</th>
        <th>Is Multiselect?</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <tr @click="onRowClick(quest.id)" v-for="quest in questions" :key="quest.id">
        <th>{{ quest.id }}</th>
        <th>{{ quest.content }}</th>
        <th>{{ quest.isMultiSelect }}</th>
        <th>
          <button @click="onEdit($event, quest.id)">Edit</button>
          <button @click="onDelete($event, quest.id)">Delete</button>
        </th>
      </tr>
    </tbody>
  </table>
</template>