<script setup lang="ts">
import { onMounted, ref, Ref } from 'vue';
import { QuestionDetailsResponse } from '../models/question';
import { Answer } from '../models/answer';
import { useRoute } from 'vue-router'
import router from '../router';
import { Delete, Get, Put } from '../api/request';

const question: Ref<QuestionDetailsResponse> = ref({})
const route = useRoute();
const answers: Ref<Answer[]> = ref([]);

const readOnly = !route.fullPath.includes('edit');

onMounted(async () => {
  //fetch details of question
  const data = await Get(`questions/${route.query.id}`);
  question.value = await data.json();

  if(readOnly) {
    //Download questions
    const data = await Get(`question/${route.query.id}`);
    answers.value = await data.json();
  }
});

const applyChanges = async () => {
  await Put(`questions/${route.query.id}`, {
    content: question.value.content,
    multiSelect: question.value.multiSelect
  });
  router.go(-1);
}

const deleteAnswer = async(id:number) => {
  await Delete(`answers/${id}`);
  answers.value.splice(answers.value.findIndex(x => x.id === id), 1);
}

</script>

<template>
  <router-link v-if="readOnly" :to="{ path: '/answer/new', query: { id: route.query.id }}">
    <button>Add new Answer</button>
  </router-link>
  <table class="category-details">
    <tr>
      <th>Id</th>
      <td>{{ route.query.id }}</td>
    </tr>
    <tr>
      <th>Content</th>
      <td v-if="readOnly">{{question.content}}</td>
      <td v-else><input type="text" v-model="question.content"/></td>
    </tr>
    <tr>
      <th>Is multiselectable?</th>
      <td v-if="readOnly">{{question.multiSelect}}</td>
      <td v-else><input type="checkbox" v-model="question.multiSelect"/></td>
    </tr>
  </table>

  <button v-if="!readOnly" @click="applyChanges()">Apply changes</button>
  <div v-else>
    <hr/>
    <table>
      <thead>
        <tr>
          <th>Id</th>
          <th>Text</th>
          <td>Actions</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="ans in answers" :key="ans.id">
          <td>{{ ans.id }}</td>
          <td>{{ ans.text }}</td>
          <td>
            <router-link :to="{path:'/answer', query: { id: ans.id }}">
              <button>Details</button>
            </router-link>
            <router-link :to="{path:'/answer/edit', query: { id: ans.id }}">
              <button>Edit</button>
            </router-link>
            <button @click="deleteAnswer(ans.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>