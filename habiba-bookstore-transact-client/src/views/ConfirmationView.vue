<!-- Confirmation.vue -->
<template>
  <div class="confirmation-page">

    <div class="confirmation-details">
    <h1 class="title">Confirmation Page</h1>
    <p class="message">Thank you for your order!</p>

      <ul class="order-info">
      <li><strong>Confirmation #:</strong> {{ orderDetails.order.confirmationNumber }}</li>
        <li><strong>Time:</strong> {{ orderDateTime }}</li>
        <li><strong>Total:</strong> {{ asDollarsAndCents(orderDetails.order.amount) }}</li>
      </ul>

      <div class="customer-info">
        <h2 class="headings">Customer Information</h2>
        <ul>
      <li>{{ orderDetails.customer.customerName }}</li>
      <li>{{ orderDetails.customer.address }}</li>
      <li>{{ orderDetails.customer.email }}</li>
      <li>{{ orderDetails.customer.ccNumber.slice(-4) }} ({{ ccExpMonth }}-{{ ccExpYear }})</li>
    </ul>
  </div>
    </div>
    <h3>Books Bought!</h3>
    <confirmation-table :orderDetails="orderDetails"></confirmation-table>

  </div>

</template>

<script setup lang="ts">
import ConfirmationTable from '@/components/ConfirmationTable.vue';
import { useOrderDetailsStore } from '@/stores/orderDetails';
import { computed } from 'vue';
import {asDollarsAndCents} from "../utils";
import {useCartStore} from "../stores/cart";

const orderDetailsStore = useOrderDetailsStore();
const orderDetails = orderDetailsStore.orderDetails;
console.log('Order details in ConfirmationView:', orderDetails);
const orderDateTime = new Date(orderDetails.order.dateCreated).toLocaleString();

// Computed properties
const orderDate = computed(() => {
  const date = new Date(orderDetails.order.dateCreated);
  return date.toLocaleString();
});

const ccExpDate = computed(() => {
  return new Date(orderDetails.customer.ccExpDate);
});

const ccExpYear = computed(() => {
  return ccExpDate.value.getFullYear();
});

const ccExpMonth = computed(() => {
  const month = ccExpDate.value.getMonth() + 1;
  return month.toString().padStart(2, '0');
});

console.log(orderDetails);

</script>

<style>

.confirmation-page {
  color: #333;
  font-family: 'Arial', sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: aliceblue;

}

.header {
  text-align: center;
  margin-bottom: 30px;
}

.title {
  font-size: 36px;
  font-weight: bold;
  margin-bottom: 10px;
}

.message {
  font-size: 18px;
  margin-bottom: 20px;
}

.customer-info {
  width: 100%;
  max-width: 600px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-bottom: 30px;
}

.customer-info h2 {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
}

.customer-info ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.customer-info li {
  font-size: 16px;
  margin-bottom: 5px;
}

.order-info {
  width: 100%;
  max-width: 400px;
}

.order-info ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.order-info li {
  font-size: 16px;
  margin-bottom: 5px;
}



</style>
