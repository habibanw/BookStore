<script setup lang="ts">
import type { BookItem } from "@/types";
import { useCartStore } from "@/stores/cart";
import router from "@/router";
const cartStore = useCartStore();

const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.jpg`; // Important for adding images we changed them to Jpeg after now they are GIFS
};
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images/`;


const updateCart = function (book: BookItem, quantity: number) {
  cartStore.cart.update(book, quantity);
};

const continueShopping = () => {
  router.push({ name: 'home' });
};

const proceedToCheckout = () => {
  router.push({ path: 'checkout' });
};

const clearCart = () => {
  cartStore.clearCart();
};


</script>

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 20em) repeat(3, max-content);
  row-gap: 1em;
  width: fit-content;
  margin: 0 auto;
  background-color: aliceblue;
}

ul {
  display: contents;
}

ul > li {
  display: contents;
}

.table-heading {
  background-color: #333;
  color: white;
}

.table-heading > * {
  background-color: #023e8a;
  padding: 0.5em;
}

.heading-book {
  grid-column: 1 / 3;
}

.heading-price {
  grid-column: 3 / 5;
  text-align: right;
}

.heading-subtotal {
  text-align: right;
  grid-column: -2 / -1;
}

.cart-book-image {
  padding: 0 1em;
}

.cart-book-image > * {
  margin-left: auto;
  margin-right: 0;
}

img {
  display: block;
  width: 100px;
  height: auto;
}

.rect {
  background-color: var(--primary-color-dark);
}

.narrow-rect {
  width: 75px;
  height: 100px;
}

.square {
  width: 100px;
  height: 100px;
}

.wide-rect {
  width: 125px;
  height: 100px;
}

.cart-book-price {
  padding-left: 1em;
  text-align: right;
  color: #023e8a;
}

.cart-book-quantity {
  padding-left: 1em;
  padding-right: 1em;
  color: green;
}

.cart-book-subtotal {
  text-align: right;
  padding-left: 1em;
  padding-right: 1em;
  color: #046b04;
  font-family: "Apple SD Gothic Neo";
}

/* Row separators in the table */

.line-sep {
  display: block;
  height: 2px;
  background-color: gray;
  grid-column: 1 / -1;
}

/* Increment/decrement buttons */

.icon-button {
  border: none;
  cursor: pointer;
  background: none;
}

.inc-button {
  font-size: 1.125rem;
  color: #023e8a;
  margin-right: 0.25em;
}

.inc-button:hover {
  color: #009dff;
}

.dec-button {
  font-size: 1.125rem;
  color: #023e8a;
}

.dec-button:hover {
  color: #009dff;
}

/* Chevron buttons */

.dec-arrow-button,
.inc-arrow-button {
  font-size: 1rem;
  color: var(--primary-color);
}

.dec-arrow-button:hover,
.inc-arrow-button:hover {
  color: var(--primary-color-dark);
}

input[type="number"] {
  width: 4em;
}

select {
  background-color: var(--primary-color);
  color: white;
  border: 2px solid var(--primary-color-dark);
  border-radius: 3px;
}

.cart-book-title {
  color: #046b04;
  font-family: "Poppins", sans-serif;
}


.btn4 {
  border: 0;
  background-color: #023e8a;
}

.btn0 {
  display: flex;
  justify-content: space-evenly;
}

.empty-cart-message {
  text-align: center;
  font-size: 18px;
  margin-top: 1em;
  position: relative;
  color: #333; /* Adjust the color to match your site's color theme */
  display: flex;
  align-items: center;
  justify-content: center;
}

.empty-cart-message::before,
.empty-cart-message::after {
  content: "";
  display: inline-block;
  width: 20px;
  height: 2px;
  background-color: #333; /* Adjust the color to match your site's color theme */
  margin: 0 10px;
  vertical-align: middle;
}

.empty-cart-message::before {
  margin-right: 20px;
}

.empty-cart-message::after {
  margin-left: 20px;
}

.clear-cart-button {
  margin-left: 30px;
}



.primary-button {
  background-color: #023e8a;
  color: white;
  border: none;
  padding: 0.5em 1em;
  font-weight: bold;
  font-size: 1em;
  cursor: pointer;
}

.secondary-button {
  background-color: #e5e5e5;
  color: #333;
  border: none;
  padding: 0.5em 1em;
  font-weight: bold;
  font-size: 1em;
  cursor: pointer;
}

.tertiary-button {
  background-color: transparent;
  color: black;
  border: none;
  padding: 0.5em 1em;
  font-weight: bold;
  font-size: 1em;
  cursor: pointer;
}

.cart-summary {
  color: black;
}

.empty-cart-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 300px;
}

.empty-cart-message {
  text-align: center;
  margin-bottom: 1em;
}

.continue-shopping-button {
  background-color: #023e8a;
  color: white;
  border: none;
  padding: 0.5em 1em;
  font-weight: bold;
  font-size: 1em;
  cursor: pointer;
}

</style>

<template>


  <div class="cart-table">

    <li class="line-sep"></li>

    <p class="cart-summary " v-if="cartStore.cart.numberOfItems > 0">Items in Cart: {{ cartStore.cart.numberOfItems }}</p>
    <p class="cart-summary" v-if="cartStore.cart.numberOfItems > 0">Subtotal: ${{ (cartStore.cart.subtotal / 100).toFixed(2) }}</p>
    <button class="clear-cart-button btn btn4 tertiary-button" v-if="cartStore.cart.numberOfItems > 0" @click="clearCart">Clear Cart</button>

    <p class="empty-cart-message" v-else>Your cart is empty.</p>



    <li class="line-sep"></li>

    <ul v-if="cartStore.cart.numberOfItems > 0">
      <li class="table-heading">
        <div class="heading-book">Book</div>
        <div class="heading-price">Price / Quantity</div>
        <div class="heading-subtotal">Amount</div>
      </li>

      <template v-for="item in cartStore.cart.items" :key="item.book.bookId">
        <li>
          <div class="cart-book-image">

            <img
                :src="`${bookImagePrefix}${bookImageFileName(item.book)}`"
                :alt="item.book.title"
                width="50px"
                height="auto"
            />
          </div>
          <div class="cart-book-title">{{ item.book.title }}</div>
          <div class="cart-book-price">
            ${{ (item.book.price / 100).toFixed(2) }}
          </div>
          <div class="cart-book-quantity">
            <span class="quantity">{{ item.quantity }}</span
            >&nbsp;
            <button
                class="icon-button inc-button"
                @click="updateCart(item.book, item.quantity + 1)"
            >
              <i class="fas fa-plus-circle"></i>
            </button>
            <button
                class="icon-button dec-button"
                @click="updateCart(item.book, item.quantity - 1)"
            >
              <i class="fas fa-minus-circle"></i>
            </button>
          </div>
          <div class="cart-book-subtotal">${{ ((item.book.price / 100).toFixed(2) * item.quantity).toFixed(2) }}
          </div>
        </li>
        <li class="line-sep"></li>
      </template>

    </ul>
  </div>

  <div class="btn0">
    <button class="continue-button btn btn4 secondary-button" @click="continueShopping">Continue Shopping</button>
    <button class="proceed-button btn btn4 primary-button"  v-if="cartStore.cart.numberOfItems > 0" @click="proceedToCheckout">Proceed to Checkout</button>
  </div>
</template>
