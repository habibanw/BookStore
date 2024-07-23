<script setup lang="ts">
import {defineProps} from "vue";
import type {BaseValidation} from "@vuelidate/core";

const props = defineProps<{
  fieldName: BaseValidation;
}>();
</script>

<style scoped>
form > .error {
  color: red;
  text-align: right;
}

form > .error {
  color: red;
  text-align: right;
}

.error {
  color: red;
  font-size: 0.8em;
  font-style: italic;
  margin-bottom: 0.4em;
}



</style>

<template>
  <template v-if="fieldName.$error">
    <span
        class="error"
        v-for="error of fieldName.$errors"
        :key="error.$uid"
    >{{ error.$message }}</span
    >
  </template>
</template>
