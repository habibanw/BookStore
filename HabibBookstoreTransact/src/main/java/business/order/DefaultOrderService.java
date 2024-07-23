package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public class DefaultOrderService implements OrderService {

	private BookDao bookDao;
	private CustomerDao customerDao;
	private LineItemDao lineItemDao;
	private OrderDao orderDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setLineItemDao(LineItemDao lineItemDao) {
		this.lineItemDao = lineItemDao;
	}




	@Override
	public OrderDetails getOrderDetails(long orderId) {
		Order order = orderDao.findByOrderId(orderId);
		Customer customer = customerDao.findByCustomerId(order.customerId());
		List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
		List<Book> books = lineItems
				.stream()
				.map(lineItem -> bookDao.findByBookId(lineItem.bookId()))
				.toList();

		return new OrderDetails(order, customer, lineItems, books);
	}

	@Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);


		try (Connection connection = JdbcUtils.getConnection()) {
			Date ccExpDate = getCardExpirationDate(
					customerForm.getCcExpiryMonth(),
					customerForm.getCcExpiryYear());
			return performPlaceOrderTransaction(
					customerForm.getName(),
					customerForm.getAddress(),
					customerForm.getPhone(),
					customerForm.getEmail(),
					customerForm.getCcNumber(),
					ccExpDate, cart, connection);
		} catch (SQLException e) {

			throw new BookstoreDbException("Error during close connection for customer order", e);
		}



	}

//	private Date getCardExpirationDate(String monthString, String yearString) {
//		return new Date(); // TODO Implement this correctly
//	}

	private Date getCardExpirationDate(String monthString, String yearString) {
		try {
			int month = Integer.parseInt(monthString);
			int year = Integer.parseInt(yearString);
			YearMonth expYearMonth = YearMonth.of(year, month);

			if (expYearMonth.isBefore(YearMonth.now())) {
				throw new ApiException.ValidationFailure("ccExpiryMonth", "Please enter a valid expiration date");
			}

			LocalDateTime expirationDateTime = expYearMonth.atDay(1).atStartOfDay();
			Instant instant = expirationDateTime.toInstant(ZoneOffset.UTC);

			return Date.from(instant);
		} catch (NumberFormatException | DateTimeException e) {
			throw new ApiException.ValidationFailure("ccExpiryMonth", "Invalid credit card expiration date", e);
		}
	}
	private long performPlaceOrderTransaction(
			String name, String address, String phone,
			String email, String ccNumber, Date date,
			ShoppingCart cart, Connection connection) {
		try {
			connection.setAutoCommit(false);
			long customerId = customerDao.create(
					connection, name, address, phone, email,
					ccNumber, date);
			long customerOrderId = orderDao.create(
					connection,
					cart.getComputedSubtotal() + cart.getSurcharge(),
					generateConfirmationNumber(), customerId);
			for (ShoppingCartItem item : cart.getItems()) {
				lineItemDao.create(connection, customerOrderId,
						item.getBookId(), item.getQuantity());
			}
			connection.commit();

			// Return the customer order ID, which is a long value
			return customerOrderId;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BookstoreDbException("Failed to roll back transaction", e1);
			}
			return 0; // Return 0 or throw an exception to indicate an error during order placement
		}
	}


	private int generateConfirmationNumber() {
		// Generate a random confirmation number between 0 and 999999999
		return ThreadLocalRandom.current().nextInt(999999999);
	}


	private void validateCustomer(CustomerForm customerForm) {

    	String name = customerForm.getName();

		if (name == null || name.equals("") || name.length() > 45) {
			throw new ApiException.ValidationFailure("name", "Invalid name field");
		}

		String phone = customerForm.getPhone();

		if (phone == null || phone.equals("")) {
			throw new ApiException.ValidationFailure("phone", "Invalid phone field");

		}
		String adjPhone = phone.replaceAll("\\D", "");

		if (adjPhone.length() != 10) {
			throw new ApiException.ValidationFailure("phone", "Invalid phone field (Length)");
		}

		String email = customerForm.getEmail();

		if (email == null || !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
			throw new ApiException.ValidationFailure("email", "Invalid email field");
		}

		if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
			throw new ApiException.ValidationFailure("ccExpiryMonth","Please enter a valid expiration date");
		}

		String ccNumber = customerForm.getCcNumber();

		if (ccNumber == null || !ccNumber.matches("\\d{16}")) {
			throw new ApiException.ValidationFailure("ccNumber", "Invalid credit card number");
		}

		String address = customerForm.getAddress();

		if (address == null || address.trim().isEmpty() || address.length() > 100) {
			throw new ApiException.ValidationFailure("address", "Please enter a valid Address!");
		}

	}

	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
		// HINT: Use Integer.parseInt and the YearMonth class
		// TODO: return true when the provided month/year is before the current month/yeaR
		try {
			int month = 0;
			int year = 0;
			try {
				month = Integer.parseInt(ccExpiryMonth);
				year = Integer.parseInt(ccExpiryYear);
			} catch (NumberFormatException e) {
				return true;
			}

			YearMonth currentYearMonth = YearMonth.now();
			YearMonth expYearMonth = YearMonth.of(year, month);

			return expYearMonth.isBefore(currentYearMonth);
		} catch (Exception e) {
			return true;
		}
	}

	private void validateCart(ShoppingCart cart) {

		if (cart.getItems().size() <= 0) {
			throw new ApiException.ValidationFailure("Cart is empty.");
		}

		cart.getItems().forEach(item-> {
			if (item.getQuantity() < 0 || item.getQuantity() > 99) {
				throw new ApiException.ValidationFailure("Invalid quantity" + item.getBookId());
			}
			Book databaseBook = bookDao.findByBookId(item.getBookId());
			// TODO: complete the required validations

			if (databaseBook == null) {
				throw new ApiException.ValidationFailure("Book not found in the database for item with ID: " + item.getBookId());
			}

			if (item.getBookForm().getPrice() != databaseBook.price()) {
				throw new ApiException.ValidationFailure("Invalid price for item with ID: " + item.getBookId());
			}

			if (item.getBookForm().getCategoryId() != databaseBook.categoryId()) {
				throw new ApiException.ValidationFailure("Invalid category for item with ID: " + item.getBookId());
			}

		});

	}

}
