package com.studies.spring_boot;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args);

    // Calls the order service and pass an object of the dependency for each
    var newOrder1 = new OrderService(new PaypalPaymentService());
    newOrder1.placeOrder();

    var newOrder2 = new OrderService(new StripePaymentService());
    newOrder2.placeOrder();
	}
}
