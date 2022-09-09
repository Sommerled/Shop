package com.challange.shop.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.challange.shop.domain.OrderItem;
import com.challange.shop.domain.OrderSummary;

@SpringBootTest
class OrderControllerTest {
	
	//Autowired is ok for now as I do not have Mocks to inject
	@Autowired
	private OrderController controller;
	
	@BeforeAll
	public static void runBeforeAll() {
		
	}
	
	@Test
	void applesAndOrangesTest() {	
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		Long appleId = 1234L;
		Long orangeId = 5678L;
		Long quantity = 2L;
		Float expected = 1.7F;
		
		orderItems.add(OrderItem.builder()
				.id(appleId)
				.quantity(quantity)
				.build());
		
		orderItems.add(OrderItem.builder()
				.id(orangeId)
				.quantity(quantity)
				.build());
		
		OrderSummary summary = this.controller.Order(orderItems);
		
		assertThat(summary.getTotal()).isEqualTo(expected);
	}
	
	@Test
	void applesTest() {	
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		Long appleId = 1234L;
		Long quantity = 2L;
		Float expected = 1.2F;
		
		orderItems.add(OrderItem.builder()
				.id(appleId)
				.quantity(quantity)
				.build());
		
		OrderSummary summary = this.controller.Order(orderItems);
		
		assertThat(summary.getTotal()).isEqualTo(expected);
	}
	
	@Test
	void orangesTest() {	
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		Long orangeId = 5678L;
		Long quantity = 2L;
		Float expected = 0.5F;
		
		orderItems.add(OrderItem.builder()
				.id(orangeId)
				.quantity(quantity)
				.build());
		
		OrderSummary summary = this.controller.Order(orderItems);
		
		assertThat(summary.getTotal()).isEqualTo(expected);
	}
	
	@Test
	void itemNotFoundTest() {	
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		Long otherId = 56780L;
		Long quantity = 2L;
		Float expected = 0.0F;
		
		orderItems.add(OrderItem.builder()
				.id(otherId)
				.quantity(quantity)
				.build());
		
		OrderSummary summary = this.controller.Order(orderItems);
		
		assertThat(summary.getTotal()).isEqualTo(expected);
	}

}
