<script setup lang="ts">
import { onMounted, ref, Ref } from 'vue';
import { CreateAnswerRequest } from '../models/answer';
import { useRoute } from 'vue-router'

const answer: Ref<Partial<CreateAnswerRequest>> = ref({})
const route = useRoute();

const readOnly = !route.fullPath.includes('edit');

onMounted(async () => {
  //fetch details of answer
  const data = await fetch(`http://localhost:8080/api/answers/${route.query.id}`, {
    method: 'GET'
  });
  answer.value = await data.json();
});

const applyChanges = async () => {
  await fetch(`http://localhost:8080/api/answers/${route.query.id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(answer.value)
  });
}
</script>

<template>
  <table class="category-details">
    <tr>
      <th>Id</th>
      <td>{{ route.query.id }}</td>
    </tr>
    <tr>
      <th>Text</th>
      <td v-if="readOnly">{{answer.text}}</td>
      <td v-else><input type="text" v-model="answer.text"/></td>
    </tr>
    <tr>
      <th>Is correct?</th>
      <td v-if="readOnly">{{answer.correct}}</td>
      <td v-else><input type="checkbox" v-model="answer.correct"/></td>
    </tr>
  </table>

  <button v-if="!readOnly" @click="applyChanges()">Apply changes</button>
</template>