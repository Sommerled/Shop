package com.challange.shop.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.challange.shop.domain.OrderItem;
import com.challange.shop.domain.OrderSummary;
import com.challange.shop.domain.OrderSummaryItem;
import com.challange.shop.entity.Item;

@RestController
public class OrderController {

	@PostMapping("/Order")
	public OrderSummary Order(@RequestBody List<OrderItem> orderItems) {
		OrderSummary orderSummary = new OrderSummary();
		List<OrderSummaryItem> orderSummaryItems = new ArrayList<OrderSummaryItem>();
		Float total = 0.0F;
		
		orderSummary.setOrderItems(orderSummaryItems);
		
		for(OrderItem o : orderItems) {
			Item item = this.findItem(o.getId());
			
			//make sure that item exists
			if(item != null) {
				Float itemsTotal = this.totalWithOffer(o.getId(), o.getQuantity(), item.getPrice());
				
				OrderSummaryItem orderSummaryItem = OrderSummaryItem.builder()
						.name(item.getName())
						.quantity(o.getQuantity())
						.total(itemsTotal)
						.build();
				
				orderSummaryItems.add(orderSummaryItem);
				total = total + itemsTotal;
			}
		}
		
		orderSummary.setTotal(total);
		
		return orderSummary;
	}
	
	private Float totalWithOffer(Long id, Long quantity, Float price) {
		Float total = 0.0F;
		Long offer = 0L;
		
		if(id.equals(1234L)) {
			//apples
			offer = quantity / 2; 
		}else if(id.equals(5678L)) {
			//oranges		
			offer = quantity / 3;
		}
		
		total = (quantity - offer) * price;
		
		return total;
	}
	
	private Item findItem(Long id) {
		
		if(id.equals(1234L)) {
			//apples
			return Item.builder()
					.id(1234L)
					.price(0.6F)
					.name("apple")
					.build();
		}else if(id.equals(5678L)) {
			//oranges
			return Item.builder()
					.id(5678L)
					.price(0.25F)
					.name("orange")
					.build();			
		}else {
			return null;
		}
		
	}
}
