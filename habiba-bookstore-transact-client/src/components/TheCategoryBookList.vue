<script setup lang="ts">
import {useBookStore} from "@/stores/book";
import CategoryBookListItem from "@/components/CategoryBookListItem.vue";

const bookStore = useBookStore();

</script>

<style scoped>
ul {
  display: flex;
  flex-wrap: wrap;
  padding: 1em;
  gap: 1em;
  justify-content: center;
  margin-top: 20px;
}

</style>

<template>
  <ul>
    <template v-for="book in bookStore.bookList" :key="book.bookId">
      <hr class="line"/>
      <category-book-list-item :book="book"></category-book-list-item>
    </template>
  </ul>
</template>
