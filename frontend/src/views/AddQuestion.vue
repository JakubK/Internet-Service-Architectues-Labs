<script setup lang="ts">
import { Ref, ref } from 'vue';
import { MultipartPost, Post } from '../api/request';
import { CreateQuestionRequest } from '../models/question';
import router from '../router';
const newQuestion: Ref<CreateQuestionRequest> = ref({
  content: '',
  multiSelect: false
});

const addQuestion = async () => {
  const response = await Post('questions', newQuestion.value);
  const postedUrl = response.headers.get('location')!;
  const segments = postedUrl.split('/');
  const id = +segments[segments.length-1];
  await handleUpload(+id!);
  router.go(-1);
}

let file:File;

const handleUpload = async (id: number) => {
  const form = new FormData();
  form.append("file", file);
  form.append("id", id+"");
  await MultipartPost("files", form);
}

const switchFile = (event: any) => {
  file = event.target.files[0]; 
}
</script>

<template>
  <div class="question-add">
    <label>Question content</label>
    <input type="text" v-model="newQuestion.content" />
    <br />
    <label>Question multiSelect</label>
    <input type="checkbox" v-model="newQuestion.multiSelect" />
    <br />
    <label>Question image</label>
    <input ref="file" name="file" type="file" @change="switchFile"/>
    <br/>
    <button @click="addQuestion">Add question</button>
  </div>
</template>