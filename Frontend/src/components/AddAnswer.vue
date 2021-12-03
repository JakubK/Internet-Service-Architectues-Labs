<script setup lang="ts">
  import { Ref, ref } from 'vue';
  import { useRoute } from 'vue-router';
  import { CreateAnswerRequest } from '../models/answer';

  const route = useRoute();
  const newAnswer: Ref<CreateAnswerRequest> = ref({
    text: '',
    correct: false,
    questionId: +route.query.id!
  });

  const addAnswer = async() => {
    await fetch(`http://localhost:8080/api/answers`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newAnswer.value)
    });
  }
</script>

<template>
  <div class="answer-add">
    <label>Answer text</label>
    <input type="text" v-model="newAnswer.text"/><br>
    <label>Answer correct</label>
    <input type="checkbox" v-model="newAnswer.correct"/><br>
  </div>
  <button @click="addAnswer">Add answer</button>
</template>