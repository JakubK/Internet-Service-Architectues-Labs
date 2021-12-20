<script setup lang="ts">
import { Ref, ref } from 'vue';
import { Post } from '../api/request';
import { CreateQuestionRequest } from '../models/question';
import router from '../router';
const newQuestion: Ref<CreateQuestionRequest> = ref({
  content: '',
  multiSelect: false
});

const addQuestion = async () => {
  await Post('questions', newQuestion.value);
  router.go(-1);
}
</script>

<template>
  <div class="question-add">
    <label>Question content</label>
    <input type="text" v-model="newQuestion.content" />
    <br />
    <label>Question multiSelect</label>
    <input type="checkbox" v-model="newQuestion.multiSelect" />
  </div>
  <button @click="addQuestion">Add question</button>
</template>