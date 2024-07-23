<script setup lang="ts">
import CartTable from "@/components/CartTable.vue";
import { useCartStore } from "@/stores/cart";
const cartStore = useCartStore();
const clearCart = function () {
  cartStore.clearCart();
};
</script>
<style scoped>

h2 {
  margin: 1em auto;
  text-align: center;
  color: black;
}
</style>

<template>
  <h2>Cart Page</h2>
  <cart-table></cart-table>
</template>
