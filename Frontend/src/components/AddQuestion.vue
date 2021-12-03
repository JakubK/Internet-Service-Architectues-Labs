<script setup lang="ts">
  import { Ref, ref } from 'vue';
  import { CreateQuestionRequest } from '../models/question';
  const newQuestion: Ref<CreateQuestionRequest> = ref({
    content: '',
    multiSelect: false
  });

  const addQuestion = async() => {
    await fetch(`http://localhost:8080/api/questions`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newQuestion.value)
    });
  }
</script>

<template>
  <div class="question-add">
    <input type="text" v-model="newQuestion.content"/>
    <input type="checkbox" v-model="newQuestion.multiSelect"/>
  </div>
  <button @click="addQuestion">Add question</button>
</template>