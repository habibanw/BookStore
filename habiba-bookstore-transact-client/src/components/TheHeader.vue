<script setup lang="ts">
import TheHeaderDropdown from "@/components/TheHeaderDropdown.vue";
import TheHeaderSearchBar from "@/components/TheHeaderSearchBar.vue";
import {useCartStore} from "@/stores/cart";
import {useRouter} from "vue-router";
const cartStore = useCartStore();
const router = useRouter()
</script>

<style scoped>

header.container {
  //width: 1200px;
  background-color: #023e8a;
  //background-color: #00a59b;
  display: flex;
  justify-content: space-between;
  padding: 1em;
  gap: 1em;
  height: 170px;
}

header > section {
  flex-basis: 100%;
}

.bookstore-logo {
  text-align: left;
  height: 100%;
}

.title-and-search-bar {
  text-align: center;
}

.header-dropdown-and-cart {
  text-align: right;
}

.text-logo {
  font-family: "Playfair";
  font-size: 40px;
}

@media (max-width: 720px) {
  .text-logo {
    font-size: 30px;
  }
}


@media (max-width: 700px) {
  header {
  }
}

.search {
  margin-left: 25px;
}

.btn2 {
  border: 0;
  background: #023e8a;
  font-size: 30px;
}


.button {
  display: inline-block;
  margin: 4px 2px;
  background-color: #444;
  font-size: 14px;
  padding-left: 32px;
  padding-right: 32px;
  height: 50px;
  line-height: 50px;
  text-align: center;
  color: white;
  text-decoration: none;
  cursor: pointer;
}

.button:hover {
  transition-duration: 0.4s;
  -moz-transition-duration: 0.4s;
  -webkit-transition-duration: 0.4s;
  -o-transition-duration: 0.4s;
  background-color: #009dff;
  color: black;
}

.search-container {
  position: relative;
  display: inline-block;
  margin: 4px 2px;
  height: 50px;
  width: 50px;
  vertical-align: bottom;
  float: left;
}

.mGlass {
  display: inline-block;
  pointer-events: none;
  -webkit-transform: rotate(-45deg);
  -moz-transform: rotate(-45deg);
  -o-transform: rotate(-45deg);
  -ms-transform: rotate(-45deg);
}

.searchButton {
  position: relative;
  font-size: 22px;
  width: 100%;
  border: 0;
  margin-top: 20px;
  padding: 0;
  background-color: #3C8802;

}

.search {
  position: absolute;
  background-color: white;
  outline: none;
  border: none;
  padding: 0;
  width: 0;
  height: 100%;
  z-index: 10;
  margin-top: 20px;
  transition-duration: 0.4s;
  -moz-transition-duration: 0.4s;
  -webkit-transition-duration: 0.4s;
  -o-transition-duration: 0.4s;
}

.search:focus {
  width: 300px; /* Bar width+1px */
  padding: 0 16px 0 0;
}

.bookstore-logo {
  margin-bottom: 10px;
}

#lblCartCount {
  font-size: 16px; /* Increase the font size */
  background: #3C8802;
  color: #fff;
  padding: 0 8px; /* Adjust the padding if needed */
  vertical-align: top;
  margin-left: -10px;
}

.badge {
  padding-left: 10px; /* Adjust the padding if needed */
  padding-right: 10px; /* Adjust the padding if needed */
  -webkit-border-radius: 10px; /* Increase the border radius */
  -moz-border-radius: 10px; /* Increase the border radius */
  border-radius: 10px; /* Increase the border radius */
}



</style>

<template>
  <header class="container">
    <section class="bookstore-logo">

        <router-link to="/">
        <img
            src="/site-images/bookstore-logo.png"
            alt="Another Bookstore Logo"
            width="200"
            height="auto"
        />
        </router-link>
    </section>


    <section class="search-container title-and-search-bar">
      <h1 class="text-logo">The Reading Room</h1>
      <div class="search-container">
        <form action="/search" method="get">
          <input class="search" id="searchLeft" type="search" name="q" placeholder="Search">
          <label class="button searchButton" for="searchLeft"><span class="mGlass">&#9906;</span></label>
        </form>
      </div>
    </section>
    <section class="header-dropdown-and-cart">
      <button class="btn2" @click="router.push({path: '/cart'})">

        <i class="fa btn0" style="font-size: 40px;">&#xf07a;</i> <!-- Increase the font size -->
        <span class='badge badge-warning' id='lblCartCount'> {{ cartStore.count }} </span>
      </button>

      <button class="btn2" ><i class="fa fa-user" style="font-size: 37px"></i></button>
      <the-header-dropdown></the-header-dropdown>
    </section>
  </header>
</template>
