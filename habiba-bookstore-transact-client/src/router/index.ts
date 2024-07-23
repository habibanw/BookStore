import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import CategoryView from "@/views/CategoryView.vue";
import CartView from "@/views/CartView.vue";
import CheckoutView from "@/views/CheckoutView.vue";
import ConfirmationView from "@/views/ConfirmationView.vue";
import NotFound from "@/views/NotFound.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/home",
      name: "home-view",
      component: HomeView,
      alias: ["/", "/index.html"],
    },
    {
      path: "/category/:name",
      name: "category-view",
      component: CategoryView,
    },

    {
      path: "/cart",
      name: "cart-view",
      component: CartView,
    },
    {
      path: "/checkout",
      name: "checkout-view",
      component: CheckoutView,
    },
    {
      path: "/confirmation",
      name: "confirmation-view",
      component: ConfirmationView,
    },
    {
      path: "/category/",
      redirect: "/category/comedy",
    },

    {
      path: "/:pathMatch(.*)*",
      name: "not-found",
      component: NotFound,
    },
  ],
});

export default router;
