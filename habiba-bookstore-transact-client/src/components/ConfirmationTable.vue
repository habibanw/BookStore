<!-- ConfirmationTable.vue -->
<template>
  <div class="table-container">
  <table class="info">
    <thead>
    <tr>
      <th>Title</th>
      <th>Quantity</th>
      <th>Price</th>
    </tr>
    </thead>
    <tr v-for="(item, index) in orderDetails.lineItems" :key="item.orderId">
      <td>{{ bookAt(orderDetails, index).title }}</td>
      <td>{{ item.quantity }}</td>
      <td>{{ asDollarsAndCents(bookAt(orderDetails, index).price) }}</td>
    </tr>
  </table>
  </div>
</template>

<script setup lang="ts">

import { defineProps, toRaw } from 'vue';
import { useOrderDetailsStore } from '@/stores/orderDetails';
import { BookItem, OrderDetails } from '@/types';

const orderDetailsStore = useOrderDetailsStore();
const orderDetails: OrderDetails = orderDetailsStore.orderDetails;
console.log('Order details in ConfirmationTable:', orderDetails);

const bookAt = (orderDetails: OrderDetails, index: number): BookItem => {
  return orderDetails.books[index];
};

const asDollarsAndCents = (price: number): string => {
  return (price / 100).toFixed(2);
};
</script>

<style>
/* Your custom styles for the confirmation table */
.table-container {
  display: flex;
  justify-content: center;
  margin-bottom: 15px;
}

.info {
  color: black;
  border-collapse: collapse;
  width: 80%;
  margin-top: 20px;
  border-radius: 8px;
  overflow: hidden;
}

.info th,
.info td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.info th {
  background-color: #f2f2f2;
}

.info tr:hover {
  background-color: #f5f5f5;
}


</style>
