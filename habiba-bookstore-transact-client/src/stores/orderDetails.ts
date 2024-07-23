import { defineStore } from 'pinia';
import type { OrderDetails } from '@/types'; // Replace '@/types' with the actual path to your types file

const ORDER_DETAIL_STORAGE_KEY = 'orderDetail';

export const useOrderDetailsStore = defineStore('orderDetails', {
    state: () => ({
        orderDetails: {} as OrderDetails, // Default value is an empty object
    }),
    actions: {
        clearOrderDetails() {
            sessionStorage.removeItem(ORDER_DETAIL_STORAGE_KEY);
            this.orderDetails = {} as OrderDetails;
        },
        setOrderDetails(orderDetails: OrderDetails) {
            this.orderDetails = orderDetails;
            sessionStorage.setItem(ORDER_DETAIL_STORAGE_KEY, JSON.stringify(orderDetails));
        },
        hasOrderDetails() {
            return Object.keys(this.orderDetails).length > 0;
        },
    },
});
