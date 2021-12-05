<script setup lang="ts">
import { onMounted, ref, Ref } from 'vue';
import { CreateAnswerRequest } from '../models/answer';
import { useRoute } from 'vue-router'
import router from '../router';
import { Get, Put } from '../api/request';

const answer: Ref<Partial<CreateAnswerRequest>> = ref({})
const route = useRoute();

const readOnly = !route.fullPath.includes('edit');

onMounted(async () => {
  //fetch details of answer
  const data = await Get(`answers/${route.query.id}`);
  answer.value = await data.json();
});

const applyChanges = async () => {
  await Put(`answers/${route.query.id}`, answer.value);
  router.go(-1)
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
      <td v-if="readOnly">{{ answer.text }}</td>
      <td v-else>
        <input type="text" v-model="answer.text" />
      </td>
    </tr>
    <tr>
      <th>Is correct?</th>
      <td v-if="readOnly">{{ answer.correct }}</td>
      <td v-else>
        <input type="checkbox" v-model="answer.correct" />
      </td>
    </tr>
  </table>

  <button v-if="!readOnly" @click="applyChanges()">Apply changes</button>
</template>