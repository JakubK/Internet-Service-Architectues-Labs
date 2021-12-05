<script setup lang="ts">
  import { Ref, ref } from 'vue';
  import { useRoute } from 'vue-router';
import { Post } from '../api/request';
  import { CreateAnswerRequest } from '../models/answer';
  import router from '../router';

  const route = useRoute();
  const newAnswer: Ref<CreateAnswerRequest> = ref({
    text: '',
    correct: false,
    questionId: +route.query.id!
  });

  const addAnswer = async() => {
    await Post('answers', newAnswer.value);
    router.go(-1);
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