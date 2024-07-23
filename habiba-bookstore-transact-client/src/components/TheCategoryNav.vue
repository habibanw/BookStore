<script setup lang="ts">
import {useCategoryStore} from "@/stores/category";
const categoryStore = useCategoryStore();

</script>
<style scoped>

.category-buttons {
  display: flex;
  flex-direction: row;
  background-color: #103E79;
  padding-bottom: 5px;
  padding-top: 5px;
  text-align: center;
  justify-content: space-evenly;
  border: none;
}

.router-link {
  border: none;
}

.button.category-button.router-link-active {
  background-color: #009dff;
}

.button.category-button {
  background-color: #3C8802;
}

.button {
  border: none;
}

.button.category-button:hover,
.button.category-button:active {
  background-color: rgba(0, 157, 255, 0.16);
}
</style>

<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <li v-for="category in categoryStore.categoryList" :key="category.categoryId">
        <router-link
          :to="'/category/' + category.name"
          class="button category-button "
        >
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </nav>
</template>
