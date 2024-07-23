<script setup lang="ts">
// import { defineProps } from "vue";
import type { BookItem } from "@/types";
import { useCartStore} from "@/stores/cart";
const cartStore = useCartStore();
const props = defineProps<{
  book: BookItem;
}>();
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images/`;

const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.jpg`; // Important for adding images we changed them to Jpeg after now they are GIFS
};
</script>
<style scoped>


.book-box {
  display: flex;
  width: 175px;
  flex-direction: column;
  //background-color: rgba(0, 0, 128);
  background-color: #4169E1;
  padding: 1em;
  border: rgba(214, 135, 230, 0.668) solid 1px;
  gap: 0.25em;
  align-items: center;
}

.book-image {
  width: 150px;
  display: flex;
  justify-content: center;
}

.book-title {
  font-weight: bold;
  text-align: center;
  margin-top: 10px;

}

.book-author {
  font-style: italic;
  margin-top: 10px;
  text-align: center;
}

.book-price {
  text-align: center;
  margin-bottom: 10px;
  margin-top: 10px;
}

.plus {
  font-size: 25px;
  border-radius: 50%;
  float: left;
}

.addCart {
  margin: auto;
  border: solid 1px black;
  color: #ffff;
  background: #099dff;
  background: #3C8802;
  border-radius: 50%;
  cursor: pointer;

}

.book-image {
  position: relative;
}
.book-image img {
  display: block;

}
.book-image .fa-download {
  position: absolute;
  top:0;
  left:0;
  margin-top: -10px;
  margin-left: -10px;
  color: yellow;
}

</style>

<template>

  <li class="book-box">

    <div class="book-image">

      <i v-if="book.isPublic" class="fas fa-download">Read</i>

      <img
          :src="`${bookImagePrefix}${bookImageFileName(book)}`"
          :alt="book.title"
          />

    </div>


    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">{{ book.author }}</div>
    <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>

    <button class="addCart" v-on:click="cartStore.addToCart(book)"><span class="plus">&plus;</span>
    </button>
  </li>
</template>
