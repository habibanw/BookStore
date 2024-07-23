<script setup lang="ts">

import {reactive, ref} from "vue";
import useVuelidate from "@vuelidate/core";
import {email, helpers, maxLength, minLength, numeric, required} from "@vuelidate/validators";
import {useCartStore} from "@/stores/cart";
// import { isCreditCard, isMobilePhone, credit } from "@/utils";
import CheckoutFieldError from "@/components/CheckoutFieldError.vue";
import router from "@/router";
import {isMobilePhone, isEmail, isCreditCard} from "@/validators";
import {asDollarsAndCents} from "../utils";
import {useCategoryStore} from "@/stores/category";
import type {OrderDetails, ServerErrorResponse} from "@/types";

const defaultServerErrorMessage = "An unexpected error occurred, please try again."
const serverErrorMessage = ref(defaultServerErrorMessage);
const cartStore = useCartStore();
const cart = cartStore.cart;
const categoryStore = useCategoryStore();
const months: string[] = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

const form = reactive({
  name: "",
  address: "",
  phone: "",
  email: "",
  ccNumber: "",
  ccExpiryMonth: new Date().getMonth() + 1,
  ccExpiryYear: new Date().getFullYear(),
  checkoutStatus: "",
});

const rules = {
  name: {
    required: helpers.withMessage("Please provide a name.", required),
    minLength: helpers.withMessage(
        "Name must have at least 4 letters.",
        minLength(4)
    ),
    maxLength: helpers.withMessage(
        "Name can have at most 45 letters.",
        maxLength(45)
    ),
  },
  phone: {
    required: helpers.withMessage("Please provide a phone number.", required),
    phone: helpers.withMessage("Please provide a valid phone number", isMobilePhone),
  },
  ccExpiryMonth: {
    required: helpers.withMessage("Please select the expiration month.", required),
  },
  ccExpiryYear: {
    required: helpers.withMessage("Please select the expiration year.", required),
  },
  email: {
    required: helpers.withMessage("Please provide an email address.", required),
    email: helpers.withMessage("Please provide a valid email address.", isEmail),
  },
  address: {
    required: helpers.withMessage("Please provide an address.", required),
  },
  ccNumber: {
    required: helpers.withMessage("Please provide a credit card number.", required),
    numeric: helpers.withMessage("Credit card number must consist of numeric digits.", numeric),
    creditCard: helpers.withMessage("Please provide a valid credit card number.", isCreditCard),
    maxLength: helpers.withMessage("Credit card number must be 16 digits long.", maxLength(16)),
    minLength: helpers.withMessage("Credit card number must be 16 digits long.", minLength(16)),

  },
};
const v$ = useVuelidate(rules, form);

async function submitOrder() {
  console.log("Submit order");
  const isFormCorrect = await v$.value.$validate();
  if (!isFormCorrect) {
    form.checkoutStatus = "ERROR";
  } else {
    try {
      form.checkoutStatus = "PENDING";
      serverErrorMessage.value = defaultServerErrorMessage;

      const placeOrderResponse: OrderDetails | ServerErrorResponse =
          await cartStore
              .placeOrder({
                name: form.name,
                address: form.address,
                phone: form.phone,
                email: form.email,
                ccNumber: form.ccNumber,
                ccExpiryMonth: form.ccExpiryMonth,
                ccExpiryYear: form.ccExpiryYear,
              })

      if ("error" in placeOrderResponse) {
        form.checkoutStatus = "SERVER_ERROR";
        serverErrorMessage.value = placeOrderResponse.message
        console.log("Error placing order", placeOrderResponse);
      } else {
        form.checkoutStatus = "OK";
        await router.push({name: "confirmation-view"});
      }

    } catch (e) {
      form.checkoutStatus = "SERVER_ERROR";
      serverErrorMessage.value = defaultServerErrorMessage;
      console.log("Error placing order", e);
    }
  }
}


function yearFrom(index: number) {
  return new Date().getFullYear() + index;
}

const years = ref<Array<number>>([]);
for (let i = 0; i <= 15; i++) {
  years.value.push(yearFrom(i));
}

</script>

<style scoped>
.checkout-page {
  color: black;
  background: aliceblue;
  display: flex;
  justify-content: center;
}

.checkout-page-body {
  display: flex;
  padding: 1em;
}

form {
  display: flex;
  flex-direction: column;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
}

form > div > input,
form > div > select {
  background-color: #666666;
  margin-left: 0.5em;
}

form > .error {
  color: red;
  text-align: right;
}


.error {
  color: red;
  font-size: 0.8em;
  font-style: italic;
  margin-bottom: 0.4em;
}

.checkoutStatusBox {
  margin: 1em;
  padding: 1em;
  text-align: center;
}

.checkout-details {
  margin-bottom: 10px;
  margin-top: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.checkout-details {
  background-color: #f5f5f5;
  border: 1px solid #ddd;
  padding: 10px;
  margin-bottom: 20px;
  text-align: center;
}

.complete-purchase {
  border: 0;
  background-color: #023e8a;
  color: white;
  height: 50px;
}

.form-field {
  display: flex;
  flex-direction: column;
  margin-bottom: 0.5em;
}


</style>

<template>
  <div class="checkout-page">

    <section class="checkout-page-body" v-if="!cart.empty">
      <form id="checkout-form" @submit.prevent="submitOrder">
        <div>
          <label for="name">Name</label>
          <input
              type="text"
              size="20"
              id="name"
              name="name"
              v-model.lazy="v$.name.$model"
          />
        </div>
        <template v-if="v$.name.$error">
          <span
              class="error"
              v-for="error of v$.name.$errors"
              :key="error.$uid"
          >{{ error.$message }}</span
          >
        </template>

        <div>
          <label for="phone">Phone</label>
          <input
              class="textField"
              type="text"
              size="20"
              id="phone"
              name="phone"
              v-model.lazy="v$.phone.$model"
          />
        </div>
        <CheckoutFieldError :field-name="v$.phone"/>

        <div>
          <label for="email">Email</label>
          <input
              type="text"
              size="20"
              id="email" name="email"
              v-model.lazy="v$.email.$model"/>
        </div>

        <CheckoutFieldError :field-name="v$.email"/>

        <div>
          <label for="ccNumber">Credit card</label>
          <input type="text"
                 size="20"
                 id="ccNumber"
                 name="ccNumber"
                 v-model.lazy="v$.ccNumber.$model"
          />
        </div>
        <CheckoutFieldError :field-name="v$.ccNumber"/>

        <div class="form-field">
          <label for="address" class="">Address</label>
          <input type="text" size="20" id="address" name="address" v-model.lazy="v$.address.$model"/>
        </div>

        <CheckoutFieldError :field-name="v$.address"/>
<!--        select v-model="v$.ccExpiryMonth"-->

        <div class="expiry-selectors">
          <label for="expiry">Exp. Date</label>
          <select v-model="form.ccExpiryMonth">
            <option v-for="(month, index) in months" :key="index" :value="index + 1">
              {{ month }} ({{ index + 1 }})
            </option>
            <CheckoutFieldError :field-name="v$.ccExpiryMonth"/>
          </select>

          <select v-model="form.ccExpiryYear">
            <option v-for="year in years" :key="year" :value="year">{{ year }}</option>
            <CheckoutFieldError :field-name="v$.ccExpiryYear"/>
          </select>
        </div>

        <section class="checkout-details">
          <div>
            Price: {{ asDollarsAndCents(cart.subtotal) }}
          </div>
          <div>
            Shipping: {{ asDollarsAndCents(cart.surcharge) }}
          </div>
          <div>
            Total: {{ asDollarsAndCents(cart.subtotal + cart.surcharge) }}
          </div>
        </section>
        <input
            type="submit"
            name="submit"
            class="complete-purchase"
            :disabled="form.checkoutStatus === 'PENDING'"
            value="Complete Purchase"
        />
      </form>


      <section v-show="form.checkoutStatus !== ''" class="checkoutStatusBox">
        <div v-if="form.checkoutStatus === 'ERROR'">
          Error: Please fix the problems above and try again.
        </div>

        <div v-else-if="form.checkoutStatus === 'PENDING'">Processing...</div>

        <div v-else-if="form.checkoutStatus === 'OK'">Order placed...</div>

        <div v-else>{{ serverErrorMessage }}</div>
      </section>

    </section>

    <section class="empty-cart-section" v-else>
      <div class="empty-cart-message">
        <h2>Your cart is empty</h2>
        <p>Please add items to your cart to proceed to checkout.</p>
      </div>
      <button class="continue-shopping-button" @click="continueShopping">Continue Shopping</button>
    </section>

  </div>


</template>
