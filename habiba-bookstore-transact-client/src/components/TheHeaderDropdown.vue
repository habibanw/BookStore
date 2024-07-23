<script setup lang="ts">
import {useCategoryStore} from "@/stores/category";
const categoryStore = useCategoryStore();
</script>

<style scoped>
.header-dropdown {
  position: relative;
}

.header-dropdown ul {
  position: absolute;
  right: 0;
  background-color: white;
  color: black;
  display: none;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
}

.header-dropdown a {
  color: black;
  text-decoration: none;
}

.header-dropdown li:hover {
  background: lightgray;
}

.header-dropdown:hover ul {
  display: block;
  position: absolute;
  min-width: 8em;
  z-index: 1;
  cursor: pointer;
  box-shadow: 0 0 10px #023e8a;
}

.btn {
  border: 0;
  background-color: #3C8802;
  width: 160px;
  height: 100%;
  border-radius: 5px;
}
.gg-menu {
  float: left;
  margin-top: 10px;
}

</style>

<template>
  <div class="header-dropdown">
    <button class="btn">Categories <i class="gg-menu"></i></button>
    <ul>
      <li v-for="category in categoryStore.categoryList" :key="category.categoryId">
        <router-link :to="'/category/' + category.name">
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </div>
</template>
