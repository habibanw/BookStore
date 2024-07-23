<script setup lang="ts">
import type { CategoryItem } from "@/types";
import {useCategoryStore} from "@/stores/category";
const categoryStore = useCategoryStore();
const categoryImageFileName = function (category: CategoryItem): string {
  let name = category.name.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.jpg`;
};



// const apiUrl =
//     `${location.protocol}//${location.hostname}:` +
//     `${location.port === '5173' ? '8080' : location.port}` +
//     `${import.meta.env.BASE_URL}/api`
// console.log(apiUrl)


</script>

<style scoped>

.lists {
  align-items: center;
}

ul {
  justify-content: center;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 1em;
}

li {
  text-align: center;
  cursor: pointer;
  text-decoration: none;
}

li div {
  padding: 0.5em 0;
  /* background: rgba(0, 0, 0, 0.8); */
  color: white;
  background-color: rgba(60, 136, 2, 0.68);
  transform: translateY(-2.25em);
  margin-bottom: -2em;
}
.router-link {
  text-decoration: none;
}
</style>

<template>

  <ul class ="lists">
    <li v-for="category in categoryStore.categoryList" :key="category.categoryId">
      <router-link :to="'../category/' + category.name" class="router-link">
        <img
          :src="'category-images/' + categoryImageFileName(category)"
          :alt="category.name + ' category'"
        />
        <div>{{ category.name }}</div>
      </router-link>
    </li>
  </ul>

</template>
