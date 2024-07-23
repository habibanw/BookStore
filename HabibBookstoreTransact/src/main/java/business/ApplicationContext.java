
package business;

import business.book.BookDao;
import business.book.BookDaoJdbc;
import business.category.CategoryDao;
import business.category.CategoryDaoJdbc;
import business.customer.CustomerDao;
import business.customer.CustomerDaoJdbc;
import business.order.*;

public class ApplicationContext {

    private CategoryDao categoryDao;
    private BookDao bookDao;
    private OrderService orderService;
    private OrderDao orderDao;
    private CustomerDao customerDao;
    private LineItemDao lineItemDao;

    public static ApplicationContext INSTANCE = new ApplicationContext();

    private ApplicationContext() {
        categoryDao = new CategoryDaoJdbc();
        bookDao = new BookDaoJdbc();

        orderDao = new OrderDaoJdbc();
        lineItemDao = new LineItemDaoJdbc();
        customerDao = new CustomerDaoJdbc();

        orderService = new DefaultOrderService();
        ((DefaultOrderService)orderService).setBookDao(bookDao);
        ((DefaultOrderService) orderService).setOrderDao(orderDao);
        ((DefaultOrderService) orderService).setLineItemDao(lineItemDao);
        ((DefaultOrderService) orderService).setCustomerDao(customerDao);
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public BookDao getBookDao() { return bookDao; }

    public OrderService getOrderService() { return orderService;}

    public LineItemDao setLineItemDao() {
        return lineItemDao;
    }

    public CustomerDao customerDao() {
        return customerDao;
    }

    public OrderDao orderDao() {
        return orderDao;
    }

}


