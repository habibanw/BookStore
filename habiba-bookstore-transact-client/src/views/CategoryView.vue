<script setup lang="ts">
import TheCategoryNav from "@/components/TheCategoryNav.vue";
import TheCategoryBookList from "@/components/TheCategoryBookList.vue";
import {useRoute} from "vue-router";
import {useBookStore} from "@/stores/book";
import {watch} from "vue";
import router from "@/router";

const route = useRoute();

const bookStore = useBookStore();
//
// watch(
//     () => route.params.name,
//     (newName) => {
//       bookStore.fetchBooks(newName as string);
//     },
//     { immediate: true }
// );

watch(
    () => route.params.name,
    async (newName) => {
      try {
        await bookStore.fetchBooks(newName as string)
       bookStore.fetchBooks(newName as string)
      } catch (e) {
        await router.push('/not-found')
      }
    },
    { immediate: true }
);


</script>

<style scoped>

.category-container {
  position: relative;
  align-items: center;
  max-height: calc(200vh - 200px);
  overflow-y: auto;
  background-color: aliceblue;

}

.category-background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
}


</style>

<template>
  <div class="category-container">
    <the-category-nav></the-category-nav>
    <the-category-book-list></the-category-book-list>
  </div>
</template>
